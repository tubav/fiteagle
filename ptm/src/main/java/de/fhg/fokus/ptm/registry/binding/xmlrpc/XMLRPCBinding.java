package de.fhg.fokus.ptm.registry.binding.xmlrpc;

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
import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.registry.RegistryException;
import de.fhg.fokus.ptm.registry.binding.Binding;
import de.fhg.fokus.ptm.xmlrpc.TypeFactory;

public class XMLRPCBinding implements Binding
{
	private URL parentUrl;
	private XmlRpcClient client = new XmlRpcClient();
	
	public XMLRPCBinding(URL parentUrl)
	{
		this.parentUrl = parentUrl;
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(parentUrl);
		config.setEnabledForExceptions(true);
		config.setEnabledForExtensions(true);
		client.setConfig(config);
		client.setConfig(config);
		client.setTypeFactory(new TypeFactory(client));
	}

	@Override
	public URL resolve(Identifier uuid) throws RegistryException
	{
		try
		{
			return new URL((String)client.execute("resolve", new Object[] { uuid.toString() }));
		}
		catch (Exception e)
		{
			throw new RegistryException("Error during lookup", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<Identifier, URL> resolve_all_raw(Identifier uuid)
		throws RegistryException
	{
		try
		{
			Map<Object, Object> raw = (Map<Object, Object>)client.execute("resolve_all_raw", new Object[] { uuid.toString() });
			Map<Identifier, URL> result = new HashMap<Identifier, URL>();
			
			for (Map.Entry<Object, Object> e : raw.entrySet())
				result.put(new Identifier((String)e.getKey()), new URL((String)e.getValue()));
			
			return result;
		}
		catch (Exception e)
		{
			throw new RegistryException("Error during lookup", e);
		}
	}
	
	@Override
	public void register(Identifier uuid, URL url) throws RegistryException 
	{
		try
		{
			client.execute("register", new Object[] { uuid.toString(), url.toString() });
		}
		catch (Exception e)
		{
			throw new RegistryException("Error during register", e);
		}		
	}
	
	@Override
	public String toString()
	{
		return parentUrl.toString();
	}

}
