package de.fhg.fokus.ptm.server;

/*
Copyright (C) 2010 FhG Fokus

This file is part of the open source Teagle implementation.

Licensed under the Apache License, Version 2.0 (the "License"); 

you may not use this file except in compliance with the License. 

You may obtain a copy of the License at 



http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 

distributed under the License is distributed on an "AS IS" BASIS, 

WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 

See the License for the specific language governing permissions and 

limitations under the License. 

For further information please contact teagle@fokus.fraunhofer.de
*/

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import de.fhg.fokus.ptm.Client;
import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.NoSuchResourceException;
import de.fhg.fokus.ptm.Owners;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;
import de.fhg.fokus.ptm.ResourceAdapter;
import de.fhg.fokus.ptm.client.PTMClient;
import de.fhg.fokus.ptm.registry.Registry;

public class Manager implements ResourceAdapter
{
	protected static Logger logger = Logger.getLogger(Manager.class);
	
	private PTMClient ptmclient;
	private Registry<ResourceAdapter> registry;
	//private UUID uuid;
	private URL url;
	//private HashMap<String, ResourceAdapter> adapters = new HashMap<String, ResourceAdapter>();
		
	public Manager(ManagerProperties properties, PTMClient client) throws PTMException
	{
		this(properties.getURL(), client);
	}
	
	/**
	 * 
	 * @param url - The URL at which this manager is reachable
	 * @param client - A client to access the domain
	 * @throws PTMException
	 */
	public Manager(URL managerUrl, PTMClient client) throws PTMException
	{
		this.ptmclient = client;
		this.url = managerUrl;
		this.registry = new Registry<ResourceAdapter>();
	}
	
	public Manager(URL managerUrl, URL registryUrl) throws PTMException
	{
		this.ptmclient = new PTMClient(registryUrl);
		this.url = managerUrl;
		this.registry = new Registry<ResourceAdapter>();
	}
	
	public Manager(URL managerUrl) throws PTMException
	{
		this.ptmclient = new PTMClient();
		this.url = managerUrl;
		this.registry = new Registry<ResourceAdapter>();
	}
	
	public ResourceAdapter getLocalAdapter(Identifier identifier)
		throws PTMException
	{
		if (identifier == null)
			identifier = new Identifier("/");
	/*	logger.debug("getLocal: " + identifier);
		ResourceAdapter ra = ptmclient.getAdapter(identifier);
		assert (ra != null);
		if (!(ra instanceof Shadow))
			return ra;
 
		throw new PTMException("No such local adapter: " + identifier);*/
		return registry.resolve(identifier);
	}
	
	public void addAdapter(Identifier uuid, ResourceAdapter adapter) throws PTMException
	{
		registry.registerLocal(uuid, adapter);
		ptmclient.register(uuid, url);
	}
	
	public void addAdapter(String id, ResourceAdapter adapter) throws PTMException
	{
		Identifier uuid = new Identifier(id);
		registry.registerLocal(uuid, adapter);
		ptmclient.register(uuid, url);
	}
	
	//@Override
	public Resource addResource(Identifier parentId, String name, String type,
			Map<String, Object> config, Identifier owner) throws PTMException 
	{
		
		if (parentId == null)
			parentId = new Identifier(Identifier.DELIMITER);
		else if (parentId.isAdapter())
			throw new PTMException("Need a full id or null here, not: " + parentId);
		
		if (type == null || type.length() == 0)
			throw new PTMException("Illegal value for type: " + type);

		if (config == null)
		{
			logger.warn("Config is null, using empty Map");
			config = new HashMap<String, Object>();
		}
		else if (name == null)
		{
			Object n = config.remove("name");
			if (n != null)
				name = n.toString();
		}
		
		Identifier aid = parentId.slash(type);
		
		ResourceAdapter a = getLocalAdapter(aid); 
		Resource r = a.addResource(parentId, name, type, config, owner);
		if (r == null)
			throw new PTMException("Failure adding resource. Adapter returned null.");
		return r;
	}

	@Override
	public Resource getResource(Identifier identifier) throws PTMException 
	{
		assert_full(identifier);
		Resource r =  getLocalAdapter(identifier).getResource(identifier);
		if (r == null)
		{
			logger.warn("RA returned null for getResource. Interpreting as NoSuchResource. consider being explicit.");
			throw new NoSuchResourceException(identifier);
		}
		return r;
	}
	
	@Override
	public Resource[] listResources(Identifier parentId, String type) throws PTMException
	{
		logger.debug("Listresources: " + parentId);
		Identifier aid;
		if (parentId != null)
		{
			assert_full(parentId);
			aid = parentId.slash();
		}
		else
			aid = parentId = new Identifier(Identifier.DELIMITER);
			
		if (type != null && type.length() == 0)
			type = null;
		
		
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Map<Identifier, ResourceAdapter> adapters = getLocalAdapters(aid, type);
		
		for (Map.Entry<Identifier, ResourceAdapter> e : adapters.entrySet())
		{
			ResourceAdapter a = e.getValue();
			logger.debug("Listing: " + a + " - " + e.getKey());
			try
			{
				for (Resource r : a.listResources(parentId, type))
				{	
					logger.debug("adding resource: " + r);
					resources.add(r);
				}
			}
			catch (PTMException ex)
			{
				logger.error("Error while listing " + e.getKey(), ex);
			}
			logger.debug("complete list: " + resources);
		}

		Resource[] result = resources.toArray(new Resource[0]);
		logger.debug("Retunring: " + result + " " + result.length);
		return result;
	}
	
	private Map<Identifier, ResourceAdapter> getLocalAdapters(Identifier parentId, String typename)
		throws PTMException
	{
		if (parentId == null)
			parentId = new Identifier(Identifier.DELIMITER);
		if (typename != null)
			parentId = parentId.slash(typename);
		else
			parentId = parentId.slash();
		
		logger.debug("GetLocalAdapters: " + parentId);
		
		Map<Identifier, ResourceAdapter> adapters= new HashMap<Identifier, ResourceAdapter>();
		Map<Identifier, ResourceAdapter> all = getAdapterInfos(parentId);
		
		logger.debug("Got: " + all + " " + all.size());
/*		for (Map.Entry<Identifier, ResourceAdapter> e : all.entrySet())
			if (!(e.getValue() instanceof Shadow))
				adapters.put(e.getKey(), e.getValue());
		logger.debug("Filtered" + adapters + " " + adapters.size());
		return adapters;*/
		return all;
	}

	private Map<Identifier, ResourceAdapter> getAdapterInfos(Identifier identifier)
			throws PTMException {
		if (identifier == null)
			throw new PTMException("No identifier given");
		return registry.resolveAll(identifier);
	}

	public ResourceAdapter getAdapter(Identifier identifier) throws PTMException 
	{
		return ptmclient.getAdapter(identifier);
	}

	/*private ResourceAdapter[] getAdapters(Identifier identifier) throws PTMException
	{
		return getAdapterInfos(identifier).values().toArray(new ResourceAdapter[0]);
	}
	
	public void register(Identifier identifier, URL url) throws PTMException
	{
		ptmclient.register(identifier, url);
	}
	
	public void register(Identifier identifier, URL url, ResourceAdapter a) throws PTMException
	{
		ptmclient.register(identifier, url, a);
	}*/
	
	public URL getURL()
	{
		return url;
	}
	
	private void assert_full(Identifier identifier) throws PTMException
	{
		if (identifier == null || identifier.isAdapter())
			throw new PTMException("No valid identifier given: " + identifier);
	}

	@Override
	public void acquireResource(Identifier identifier, Identifier owner,
			boolean weak) throws PTMException {
		assert_full(identifier);
		if (owner != null)
			assert_id(owner);
		getLocalAdapter(identifier).acquireResource(identifier, owner, weak);
		
	}

	@Override
	public void deleteResource(Identifier identifier, Identifier owner,
			boolean force) throws PTMException {
		assert_full(identifier);
		if (owner != null)
			assert_id(owner);
		getLocalAdapter(identifier).deleteResource(identifier, owner, force);
	}

	@Override
	public Object getAttribute(Identifier identifier, String name)
			throws PTMException {
		assert_full(identifier);
		if (name == null || name.length() == 0)
			throw new PTMException("Attribute is empty");
		return getLocalAdapter(identifier).getAttribute(identifier, name);
	}

	@Override
	public Client getClient() {
		return ptmclient;
	}

	@Override
	public Map<String, Object> getConfiguration(Identifier identifier)
			throws PTMException {
		assert_full(identifier);
		Map<String, Object> config = getLocalAdapter(identifier).getConfiguration(identifier);
		if (config == null)
		{
			logger.warn("Adapter returned null for getConfiguration. Interpreting as NoSuchResource. Consider being explicit");
			throw new NoSuchResourceException(identifier);
		}
		logger.debug("Returning configuration: " + config);
		return config;
	}

	@Override
	public void releaseResource(Identifier identifier, Identifier owner)
			throws PTMException {
		assert_full(identifier);
		getLocalAdapter(identifier).releaseResource(identifier, owner);
	}

	@Override
	public void setAttribute(Identifier identifier, String name, Object value)
			throws PTMException {
		assert_full(identifier);
		if (name == null || name.length() == 0)
			throw new PTMException("Attribute is empty");
		getLocalAdapter(identifier).setAttribute(identifier, name, value);
	}

	@Override
	public void setConfiguration(Identifier identifier,
			Map<String, Object> config) throws PTMException {
		assert_full(identifier);
		if (config == null)
			throw new PTMException("conifg must not be null");
		getLocalAdapter(identifier).setConfiguration(identifier, config);
	}

	@Override
	public void childDeleted(Identifier identifier, Identifier child) {
		try {
			assert_full(identifier);
			assert_full(child);
			getLocalAdapter(identifier).childDeleted(identifier, child);
		} catch (PTMException e) {
			logger.error("Error during notify ", e);
			return;
		}
	}

	@Override
	public void strongDeleted(Identifier identifier, Identifier ref) {
		try {
			assert_id(identifier);
			assert_full(ref);
			getLocalAdapter(identifier).strongDeleted(identifier, ref);
		} catch (PTMException e) {
			logger.error("Error during notify ", e);
			return;
		}
	}

	@Override
	public void weakDeleted(Identifier identifier, Identifier ref) {
		try {
			assert_full(ref);
			assert_id(identifier);
			getLocalAdapter(identifier).weakDeleted(identifier, ref);
		} catch (PTMException e) {
			logger.error("Error during notify ", e);
			return;
		}
	}

	@Override
	public Owners getOwners(Identifier identifier)
			throws PTMException {
		assert_full(identifier);
		Owners owners = getLocalAdapter(identifier).getOwners(identifier);
		if (owners == null)
			throw new PTMException("RA failed to produce its owners");
		return owners;
	}

	@Override
	public boolean haveResource(Identifier identifier) throws PTMException {
		assert_full(identifier);
		return getLocalAdapter(identifier).haveResource(identifier);
	}
	
	private Identifier assert_id(Identifier identifier) throws PTMException
	{
		if (identifier == null)
				throw new PTMException("No identifier given");
		if (!identifier.isAbsolute())
			throw new PTMException("Need an absolute id here, not: " + identifier);
		return identifier;
	}
}
