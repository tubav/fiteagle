package org.fiteagle.adapter.openstackvmadapter.client;

/*
 * #%L
 * FITeagle
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2012 - 2013 Architektur der Vermittlungsknoten, TU-Berlin
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.fiteagle.adapter.openstackvmadapter.client.model.Images;
import org.fiteagle.adapter.openstackvmadapter.client.model.Server;

import com.woorea.openstack.base.client.Entity;
import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.base.client.OpenStackSimpleTokenProvider;
import com.woorea.openstack.connector.JerseyConnector;
import com.woorea.openstack.glance.Glance;
import com.woorea.openstack.keystone.Keystone;
import com.woorea.openstack.keystone.api.TokensResource;
import com.woorea.openstack.keystone.model.Access;
import com.woorea.openstack.keystone.model.Tenant;
import com.woorea.openstack.keystone.model.Tenants;
import com.woorea.openstack.keystone.model.authentication.TokenAuthentication;
import com.woorea.openstack.keystone.model.authentication.UsernamePassword;
import com.woorea.openstack.nova.Nova;
import com.woorea.openstack.nova.api.ServersResource.AssociateFloatingIp;
import com.woorea.openstack.nova.model.Flavor;
import com.woorea.openstack.nova.model.Flavors;
import com.woorea.openstack.nova.model.FloatingIp;
import com.woorea.openstack.nova.model.FloatingIpDomains;
import com.woorea.openstack.nova.model.FloatingIpPools;
import com.woorea.openstack.nova.model.FloatingIpPools.FloatingIpPool;
import com.woorea.openstack.nova.model.KeyPairs;
import com.woorea.openstack.quantum.Quantum;
import com.woorea.openstack.quantum.model.Network;
import com.woorea.openstack.quantum.model.Networks;

/**
 * this client uses the woorea client and offers simple methods using openstack API.
 *
 */
public class OpenstackClient {

	OpenstackParser openstackParser;
	String tenantId = "";
	private String networkId = "";

	public OpenstackClient() {
		this.openstackParser = new OpenstackParser();
	}

	public Flavors listFlavors() {
		Access access = getAccessWithTenantId();

		Flavors flavors = null;
		Nova novaClient = new Nova(Utils.NOVA_ENDPOINT.concat("/").concat(
				tenantId));
		novaClient.token(access.getToken().getId());

		flavors = novaClient.flavors().list(true).execute();

		return flavors;
	}

	public Images listImages() {

		Access access = getAccessWithTenantId();
		Nova novaClient = new Nova(Utils.NOVA_ENDPOINT.concat("/").concat(
				tenantId));
		novaClient.token(access.getToken().getId());

		OpenStackRequest<String> request = new OpenStackRequest<String>(
				novaClient, HttpMethod.GET, "/images/detail", null,
				String.class);
		
		String responseImagesString = novaClient.execute(request);
		System.out.println(responseImagesString);

		Images images;
		try {
			images = this.openstackParser.parseToImages(responseImagesString);
		} catch (JsonParseException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return images;
	}
	
	public void listOnlyPrivateImages(){
		
		Access access = getAccessWithTenantId();
		
		Glance glanceClient = new Glance(Utils.GLANCE_ENDPOINT.concat("/").concat(tenantId));
		
//		Nova novaClient = new Nova(Utils.NOVA_ENDPOINT.concat("/").concat(
//				tenantId));
		glanceClient.token(access.getToken().getId());

//		OpenStackRequest<String> request = new OpenStackRequest<String>(
//				novaClient, HttpMethod.GET, "/images/detail", null,
//				String.class);
		
		OpenStackRequest<String> request = new OpenStackRequest<String>(
				glanceClient, HttpMethod.GET, "/images", null,
				String.class);
		
		request.queryParam("visibility", "private");
		
		String responseImagesString = glanceClient.execute(request);
		System.out.println(responseImagesString);

		Images images;
		try {
			images = this.openstackParser.parseToImages(responseImagesString);
		} catch (JsonParseException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

//		return images;
	}

	private Access getAccessWithTenantId() {
		Keystone keystone = new Keystone(Utils.KEYSTONE_AUTH_URL,
				new JerseyConnector());
		TokensResource tokens = keystone.tokens();
		Access access = tokens
				.authenticate(
						new UsernamePassword(Utils.KEYSTONE_USERNAME,
								Utils.KEYSTONE_PASSWORD))
				.withTenantName(Utils.TENANT_NAME).execute();
		keystone.token(access.getToken().getId());

		Tenants tenants = keystone.tenants().list().execute();

		List<Tenant> tenantsList = tenants.getList();

		if (tenants.getList().size() > 0) {
			for (Iterator iterator = tenantsList.iterator(); iterator.hasNext();) {
				Tenant tenant = (Tenant) iterator.next();
				if (tenant.getName().compareTo(Utils.TENANT_NAME) == 0) {
					tenantId = tenant.getId();
					break;
				}
			}

		} else {
			throw new RuntimeException("No tenants found!");
		}

		access = tokens
				.authenticate(
						new TokenAuthentication(access.getToken().getId()))
				.withTenantId(tenantId).execute();

		return access;
	}

	public org.fiteagle.adapter.openstackvmadapter.client.model.Server createServer(String imageId, String flavorId, String serverName, String keyPairName) {

		Access access = getAccessWithTenantId();
		Nova novaClient = new Nova(Utils.NOVA_ENDPOINT.concat("/").concat(
				tenantId));
		novaClient.token(access.getToken().getId());

//		KeyPairs keysPairs = novaClient.keyPairs().list().execute();

		org.fiteagle.adapter.openstackvmadapter.client.model.ServerForCreate serverForCreate = new org.fiteagle.adapter.openstackvmadapter.client.model.ServerForCreate();
		serverForCreate.setName(serverName);
		serverForCreate.setFlavorRef(flavorId);
		serverForCreate.setImageRef(imageId);
		serverForCreate.setKeyName(keyPairName);
		serverForCreate
				.getSecurityGroups()
				.add(new org.fiteagle.adapter.openstackvmadapter.client.model.ServerForCreate.SecurityGroup(
						"default"));

		List<org.fiteagle.adapter.openstackvmadapter.client.model.ServerForCreate.Network> networkList = serverForCreate
				.getNetworks();
		org.fiteagle.adapter.openstackvmadapter.client.model.ServerForCreate.Network net_demo = new org.fiteagle.adapter.openstackvmadapter.client.model.ServerForCreate.Network();
		
		net_demo.setUuid(this.getNetworkId());
		
		networkList.add(net_demo);

		OpenStackRequest<org.fiteagle.adapter.openstackvmadapter.client.model.Server> createServerRequest = new OpenStackRequest<org.fiteagle.adapter.openstackvmadapter.client.model.Server>(
				novaClient,
				HttpMethod.POST,
				"/servers",
				Entity.json(serverForCreate),
				org.fiteagle.adapter.openstackvmadapter.client.model.Server.class);
		
		org.fiteagle.adapter.openstackvmadapter.client.model.Server responseServer = novaClient
				.execute(createServerRequest);
		return responseServer;
	}

	public Server getServerDetails(String id) {
		Access access = getAccessWithTenantId();
		Nova novaClient = new Nova(Utils.NOVA_ENDPOINT.concat("/").concat(
				tenantId));
		novaClient.token(access.getToken().getId());

		OpenStackRequest<org.fiteagle.adapter.openstackvmadapter.client.model.Server> request = new OpenStackRequest<org.fiteagle.adapter.openstackvmadapter.client.model.Server>(
				novaClient,
				HttpMethod.GET,
				new StringBuilder("/servers/").append(id).toString(),
				null,
				org.fiteagle.adapter.openstackvmadapter.client.model.Server.class);
		org.fiteagle.adapter.openstackvmadapter.client.model.Server serverDetail = novaClient
				.execute(request);
		return serverDetail;

	}

	public void allocateFloatingIpForServer(String serverId, String floatingIp) {
		Access access = getAccessWithTenantId();
		Nova novaClient = new Nova(Utils.NOVA_ENDPOINT.concat("/").concat(
				tenantId));
		novaClient.token(access.getToken().getId());
		
		com.woorea.openstack.nova.model.ServerAction.AssociateFloatingIp action = new com.woorea.openstack.nova.model.ServerAction.AssociateFloatingIp(floatingIp);
		AssociateFloatingIp associateFloatingIp = new AssociateFloatingIp(serverId, action);
		
		OpenStackRequest<com.woorea.openstack.nova.model.ServerAction.AssociateFloatingIp> request = new OpenStackRequest<com.woorea.openstack.nova.model.ServerAction.AssociateFloatingIp>(novaClient,
				HttpMethod.POST,"/servers/"+serverId+"/action",
				associateFloatingIp.json(action),
				com.woorea.openstack.nova.model.ServerAction.AssociateFloatingIp.class);
		
		try {
			novaClient.execute(request);
		} catch (Exception e) {
			//TODO: this can throw harmless exceptions, but check the exception if it is not harmless
			System.out.println(e);
		}
		
	}

	public FloatingIpPools getFloatingIpPools(){
		Access access = getAccessWithTenantId();
		Nova novaClient = new Nova(Utils.NOVA_ENDPOINT.concat("/").concat(tenantId));
		novaClient.token(access.getToken().getId());
		
		OpenStackRequest<FloatingIpPools> request = new OpenStackRequest<FloatingIpPools>(
				novaClient, HttpMethod.GET, "/os-floating-ip-pools", null,
				FloatingIpPools.class);
		FloatingIpPools floatingIpPools = novaClient.execute(request);
		return floatingIpPools;
	}
	
	public FloatingIp addFloatingIp(){
		
		String poolName="";
		
		if (Utils.FLOATINGIP_POOL_NAME.compareTo("")==0) {
			List<FloatingIpPool> poolList = this.getFloatingIpPools().getList();
			if (poolList!=null && poolList.size()>0) {
				poolName = poolList.get(0).getName();
			} else {
				throw new RuntimeException("there isn't any floating ip pool defined");
			}
		}else {
			poolName = Utils.FLOATINGIP_POOL_NAME;
		}
		
		Map<String, String> body = new HashMap<String, String>();
		body.put("pool", poolName);
		Entity<Map<String, String>> entity = Entity.json(body);
		
		Access access = getAccessWithTenantId();
		Nova novaClient = new Nova(Utils.NOVA_ENDPOINT.concat("/").concat(tenantId));
		novaClient.token(access.getToken().getId());
		
		OpenStackRequest<FloatingIp> request = new OpenStackRequest<FloatingIp>(
				novaClient, HttpMethod.POST, "os-floating-ips",entity ,
				FloatingIp.class);
		FloatingIp floatingIp = novaClient.execute(request);
		return floatingIp;
	}
	
	public void addKeyPair(String name, String publicKey){
		Access access = getAccessWithTenantId();

		Nova novaClient = new Nova(Utils.NOVA_ENDPOINT.concat("/").concat(
				tenantId));
		novaClient.token(access.getToken().getId());

		novaClient.keyPairs().create(name, publicKey).execute();
		
	}
	
	public void deleteKeyPair(String name){
		Access access = getAccessWithTenantId();

		Nova novaClient = new Nova(Utils.NOVA_ENDPOINT.concat("/").concat(
				tenantId));
		novaClient.token(access.getToken().getId());

		novaClient.keyPairs().delete(name).execute();
		
	}
	
	public void deleteServer(String id){
		
		Access access = getAccessWithTenantId();
		Nova novaClient = new Nova(Utils.NOVA_ENDPOINT.concat("/").concat(
				tenantId));
		novaClient.token(access.getToken().getId());

		OpenStackRequest<org.fiteagle.adapter.openstackvmadapter.client.model.Server> request = new OpenStackRequest<org.fiteagle.adapter.openstackvmadapter.client.model.Server>(
				novaClient,
				HttpMethod.DELETE,
				new StringBuilder("/servers/").append(id).toString(),
				null,
				org.fiteagle.adapter.openstackvmadapter.client.model.Server.class);
		org.fiteagle.adapter.openstackvmadapter.client.model.Server serverDetail = novaClient
				.execute(request);
		
	}

	public String getNetworkId() {
		if(this.networkId==null || this.networkId.compareTo("")==0)
			this.setNetworkId(getNetworkIdByName(Utils.NET_NAME));
		return networkId;
	}

	private String getNetworkIdByName(String networkName) {
		Access access = getAccessWithTenantId();
		Quantum quantum = new Quantum(Utils.NET_ENDPOINT);
		
		quantum.setTokenProvider(new OpenStackSimpleTokenProvider(access.getToken().getId()));
		Networks networks = quantum.networks().list().execute();
		List<Network> networkList = networks.getList();
		for (Iterator iterator = networkList.iterator(); iterator.hasNext();) {
			Network network = (Network) iterator.next();
			if(network.getName().compareToIgnoreCase(networkName)==0)
				return network.getId();
		}
		throw new RuntimeException("there isn't any network with the specified network name");
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	

}
