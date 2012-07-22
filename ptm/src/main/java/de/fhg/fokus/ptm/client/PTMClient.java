package de.fhg.fokus.ptm.client;

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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;

import de.fhg.fokus.ptm.Client;
import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;
import de.fhg.fokus.ptm.ResourceAdapter;
import de.fhg.fokus.ptm.xmlrpc.client.XMLRPCShadow;

public class PTMClient implements Client
{
	private static Logger logger = Logger.getLogger(PTMClient.class);
	private static URL defaultUrl;
	
	private Shadow hub;
	
	static 
	{
		try {
			defaultUrl = new URL("http://ptm:8000");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public PTMClient()
	{
		this(defaultUrl);
	}
	
	public PTMClient(URL registryUrl)
	{
		hub = new XMLRPCShadow(registryUrl, this);
	}
	
	protected Logger getLogger()
	{
		return logger;
	}
	
	@Override
	public void register(de.fhg.fokus.ptm.Identifier uuid, java.net.URL url) throws PTMException 
	{
		hub.register(uuid, url);
	}

	@Override
	public Resource addResource(Resource parent, String name, String type,
			Map<String, Object> config, Identifier owner) throws PTMException {
		return addResource(parent.getIdentifier(), name, type, config, owner);		
	}

	@Override
	public Resource addResource(Identifier parentId, String name, String type,
			Map<String, Object> config, Identifier owner) throws PTMException {
		return hub.addResource(parentId, name, type, config, owner); 
	}

	@Override
	public ResourceAdapter getAdapter(Identifier identifier)
	{
		return hub;
	}
	
	/*@Override
	public ResourceAdapter getAdapter(Identifier identifier)
			throws PTMException {
		if (identifier == null)
			throw new PTMException("No identifier given");
		return hub.resolve(identifier);
	}*/
/*
	@Override
	public Map<Identifier, ResourceAdapter> getAdapterInfos(Identifier identifier)
			throws PTMException {
		if (identifier == null)
			throw new PTMException("No identifier given");
		return registry.resolveAll(identifier);
	}
	
	@Override
	public ResourceAdapter[] getAdapters(Identifier identifier) throws PTMException
	{
		return getAdapterInfos(identifier).values().toArray(new ResourceAdapter[0]);
	}
*/
	@Override
	public Resource getResource(Identifier identifier) throws PTMException {
		return hub.getResource(identifier);
	}

	@Override
	public Resource[] listResources(Resource parent, String type)
			throws PTMException {
		return listResources(parent == null ? null : parent.getIdentifier(), type);
	}

	@Override
	public Resource[] listResources(Identifier parent, String type)
			throws PTMException {

		return hub.listResources(parent, type);
	}

	@Override
	public Resource addResource(Resource parent, String name, String type,
			Map<String, Object> config, Resource owner) throws PTMException {
		return addResource(parent, name, type, config, owner.getIdentifier());
	}

	@Override
	public Resource addAnonOwnedResource(Identifier parentId, String name,
			String typeName, Map<String, Object> configuration) throws PTMException {
		return addResource(parentId, name, typeName, configuration, null);
	}

	@Override
	public URL getUrl() {
		return hub.getURL();
	}
}
