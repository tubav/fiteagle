package de.fhg.fokus.ptm.xmlrpc.client;

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
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import de.fhg.fokus.ptm.Client;
import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.Owners;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;
import de.fhg.fokus.ptm.client.Shadow;
import de.fhg.fokus.ptm.marshaller.Marshaller;
import de.fhg.fokus.ptm.registry.RegistryException;
import de.fhg.fokus.ptm.xmlrpc.TypeFactory;

public class XMLRPCShadow extends Shadow{
	private final XmlRpcClient xmlrpcclient;
	private final Marshaller marshaller;

	public XMLRPCShadow(URL url, Client client) {
		super(url, client);
		
	    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
	    config.setServerURL(url);
	    config.setEnabledForExceptions(true);
	    config.setEnabledForExtensions(true);
	    xmlrpcclient = new XmlRpcClient();
	    xmlrpcclient.setConfig(config);
	    xmlrpcclient.setTypeFactory(new TypeFactory(xmlrpcclient));
	    
	    marshaller = new Marshaller(getClient()); 
	}
	
	protected Object execute(String func, Object... args) throws PTMException
	{
		for (int i = 0; i < args.length; ++i)
			args[i] = marshaller.pack(args[i]);
		
		try
		{
			return xmlrpcclient.execute(func, args);
		}
		catch (XmlRpcException e)
		{
			getLogger().error("Error while calling remote method " + func + ": " + e);
			throw new PTMException("Error while calling remote method " + func + ": " + e);
		}
	}
	
	private Identifier checkId(Identifier id) throws PTMException
	{
		if (id == null || !id.isAbsolute() || id.isAdapter())
			throw new PTMException("Illegal identifier given: " + id);
		return id;
	}
	
	private String checkString(String o) throws PTMException
	{
		if (o == null || o.length() == 0)
			throw new PTMException("empty string given");
		return o;
	}
	
	protected Resource unpackResource(Object o) throws PTMException
	{
		if (!(o instanceof String))
			throw new PTMException("Expected identifier, got: " + o);
		return marshaller.unpackResource((String)o);
	}
	
	protected Identifier unpackIdentifier(Object o) throws PTMException
	{
		if (o == null)
			return null;
		if (!(o instanceof String))
			throw new PTMException("Expected identifier, got: " + o);
		return marshaller.unpackIdentifier((String)o);
	}
	
	protected Map<String, Object> unpackConfig(Object o) throws PTMException
	{
		if (!(o instanceof Map))
			throw new PTMException("Expected Map, got: " + o);
		return marshaller.unpackMap((Map<?, ?>) o);
	}

	@Override
	public void acquireResource(Identifier identifier, Identifier owner,
			boolean weak) throws PTMException {
		execute("acquire_resource", marshaller.packIdentifier(identifier), marshaller.packIdentifier(owner));
	}

	@Override
	public Resource addResource(Identifier parentId, String name, String type,
			Map<String, Object> config, Identifier owner) throws PTMException {
		if (type == null || type.length() == 0)
			throw new PTMException("no typename given");
		return unpackResource(execute("add_resource", parentId, name, type, config, owner));
	}

	@Override
	public void childDeleted(Identifier identifier, Identifier child) {
		try {
			execute("child_deleted", checkId(identifier), checkId(child));
		} catch (PTMException e) {
			getLogger().error("Error during childDeleted notify", e);
		}
	}

	@Override
	public void deleteResource(Identifier identifier, Identifier owner,
			boolean force) throws PTMException {
		execute("delete_resource", checkId(identifier), owner, force);
	}

	@Override
	public Object getAttribute(Identifier identifier, String name)
			throws PTMException {
		return marshaller.unpackAttribute(execute("get_attribute", checkId(identifier), checkString(name)));
	}

	@Override
	public Map<String, Object> getConfiguration(Identifier identifier)
			throws PTMException {
		return unpackConfig(checkId(identifier));
	}


	@Override
	public Resource getResource(Identifier identifier) throws PTMException {
		return unpackResource(execute("get_resource", checkId(identifier)));
	}

	@Override
	public Resource[] listResources(Identifier parentId, String type)
			throws PTMException {
		Object answer = execute("list_resources", parentId, type);
		if (answer == null || !answer.getClass().isArray())
			throw new PTMException("Expected array, got: " + answer);
		Object[] ids = (Object[])answer;
		Resource[] resources = new Resource[ids.length];
		for (int i = 0; i < resources.length; ++i)
			if (!(ids[i] instanceof String || "".equals(ids[i])))
				throw new PTMException("Empty string in list result");
			else
				resources[i] = unpackResource(ids[i]); 
		return resources;
	}

	@Override
	public void releaseResource(Identifier identifier, Identifier owner)
			throws PTMException {
		execute("release_resource", checkId(identifier), owner);
	}

	@Override
	public void setAttribute(Identifier identifier, String name, Object value)
			throws PTMException {
		if (name == null || name.length() == 0)
			throw new PTMException("No attribute name given");
		execute("set_attribute", name, marshaller.packAttribute(value));
	}

	@Override
	public void setConfiguration(Identifier identifier,
			Map<String, Object> config) throws PTMException {
		if (config == null)
			throw new PTMException("No config given");
		execute("set_configuration", checkId(identifier), config);
		
	}

	@Override
	public void strongDeleted(Identifier identifier, Identifier ref) {
		try {
			execute("strong_deleted", identifier, checkId(ref));
		} catch (PTMException e) {
			getLogger().error("Error while calling notify", e);
		}	}

	@Override
	public void weakDeleted(Identifier identifier, Identifier ref) {
		try {
			execute("weak_deleted", identifier, checkId(ref));
		} catch (PTMException e) {
			getLogger().error("Error while calling notify", e);
		}
	}

	@Override
	public boolean haveResource(Identifier identifier) throws PTMException {
		 return marshaller.unpackBoolean(execute("have_resource", checkId(identifier)));
	}

	@Override
	public Owners getOwners(Identifier identifier) throws PTMException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void register(Identifier uuid, URL url) throws RegistryException 
	{
		try
		{
			xmlrpcclient.execute("register", new Object[] { uuid.toString(), url.toString() });
		}
		catch (Exception e)
		{
			throw new RegistryException("Error during register", e);
		}		
	}

	@Override
	public URL resolve(Identifier uuid) throws RegistryException
	{
		try
		{
			return new URL((String)xmlrpcclient.execute("resolve", new Object[] { uuid.toString() }));
		}
		catch (Exception e)
		{
			throw new RegistryException("Error during lookup", e);
		}
	}
	
}
