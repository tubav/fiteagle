package org.fiteagle.adapter.nodeadapter.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.fiteagle.adapter.nodeadapter.client.model.Images;
import org.fiteagle.adapter.nodeadapter.client.model.Server;

import com.woorea.openstack.base.client.Entity;
import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.base.client.OpenStackSimpleTokenProvider;
import com.woorea.openstack.connector.JerseyConnector;
import com.woorea.openstack.keystone.Keystone;
import com.woorea.openstack.keystone.api.TokensResource;
import com.woorea.openstack.keystone.model.Access;
import com.woorea.openstack.keystone.model.Tenant;
import com.woorea.openstack.keystone.model.Tenants;
import com.woorea.openstack.keystone.model.authentication.TokenAuthentication;
import com.woorea.openstack.keystone.model.authentication.UsernamePassword;
import com.woorea.openstack.nova.Nova;
import com.woorea.openstack.nova.api.ServersResource.AssociateFloatingIp;
import com.woorea.openstack.nova.model.Flavors;
import com.woorea.openstack.nova.model.FloatingIp;
import com.woorea.openstack.nova.model.FloatingIpPools;
import com.woorea.openstack.nova.model.FloatingIpPools.FloatingIpPool;
import com.woorea.openstack.quantum.Quantum;
import com.woorea.openstack.quantum.model.Network;
import com.woorea.openstack.quantum.model.Networks;

public class OfflineTestClient extends OpenstackClient {

	OpenstackParser openstackParser;
	String tenantId = "";

	public OfflineTestClient() {
		this.openstackParser = new OpenstackParser();
	}

	public Flavors listFlavors() {

		try {
			String flavorsString = getRessourceString("/flavors.json");
			return openstackParser.parseToFlavors(flavorsString);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Images listImages() {
		String imagesString = getRessourceString("/images.json");
		try {
			return openstackParser.parseToImages(imagesString);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public org.fiteagle.adapter.nodeadapter.client.model.Server createServer() {
		String serverString = getRessourceString("/server.json");
		try {
			return openstackParser.parseToServer(serverString);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		// Server server = new Server();
		// Flavors flavors = this.listFlavors();
		// Images images = this.listImages();
		// server.setFlavor(flavors.getList().get(0));
		// server.setImage(images.getList().get(0));

	}

	public org.fiteagle.adapter.nodeadapter.client.model.Server createServer(
			String imageId, String flavorId, String serverName) {
		return this.createServer();
	}

	public Server getServerDetails(String id) {
		String serverString = getRessourceString("/serverDetail.json");
		try {
			return openstackParser.parseToServer(serverString);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Access getAccessWithTenantId() {
		// Keystone keystone = new Keystone(Utils.KEYSTONE_AUTH_URL,
		// new JaxRs20Connector());
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

	public void allocateFloatingIpForServer(String serverId, String floatingIp) {
	}

	public FloatingIpPools getFloatingIpPools() {
		Access access = getAccessWithTenantId();
		Nova novaClient = new Nova(Utils.NOVA_ENDPOINT.concat("/").concat(
				tenantId));
		novaClient.token(access.getToken().getId());

		// OpenStackRequest<FloatingIpDomains> request = new
		// OpenStackRequest<FloatingIpDomains>(
		// novaClient, HttpMethod.GET, "/os-floating-ip-dns", null,
		// FloatingIpDomains.class);

		OpenStackRequest<FloatingIpPools> request = new OpenStackRequest<FloatingIpPools>(
				novaClient, HttpMethod.GET, "/os-floating-ip-pools", null,
				FloatingIpPools.class);
		FloatingIpPools floatingIpPools = novaClient.execute(request);
		return floatingIpPools;

		// FloatingIpDomains floatingIpDomains = novaClient.execute(request);
		// return floatingIpDomains;
	}

	public FloatingIp addFloatingIp() {

		String floatingIpString = getRessourceString("/floatingIp.json");
		try {
			return openstackParser.parseToFloatingIp(floatingIpString);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void addKeyPair(String name, String publicKey) {
	}

	public void deleteKeyPair(String name) {

	}

	public void deleteServer(String id) {

	}

//	public String getNetworkId() {
//		if (this.networkId == null || this.networkId.compareTo("") == 0)
//			this.setNetworkId(getNetworkIdByName(Utils.NET_NAME));
//		return networkId;
//	}
//
//	private String getNetworkIdByName(String networkName) {
//		Access access = getAccessWithTenantId();
//		Quantum quantum = new Quantum(Utils.NET_ENDPOINT);
//
//		quantum.setTokenProvider(new OpenStackSimpleTokenProvider(access
//				.getToken().getId()));
//		Networks networks = quantum.networks().list().execute();
//		List<Network> networkList = networks.getList();
//		for (Iterator iterator = networkList.iterator(); iterator.hasNext();) {
//			Network network = (Network) iterator.next();
//			if (network.getName().compareToIgnoreCase(networkName) == 0)
//				return network.getId();
//		}
//		throw new RuntimeException(
//				"there isn't any network with the specified network name");
//	}
//
//	public void setNetworkId(String networkId) {
//		this.networkId = networkId;
//	}

	private String getRessourceString(String path) {
		InputStream in = this.getClass().getResourceAsStream(path);
		return convertStreamToString(in);
	}

	private String convertStreamToString(InputStream is) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

}
