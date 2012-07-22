package de.fhg.fokus.ptm.registry;

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

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.PTMException;

class Node<Payload>
{
	class PayloadEntry
	{
		public PayloadEntry(Payload payload, boolean wildcard)
		{
			this.payload = payload;
			this.wildcard = wildcard;
		}
		
		public Payload payload;
		public boolean wildcard;
	}
	
	private Map<String, PayloadEntry> payload = new HashMap<String, PayloadEntry>();
	private Map<String, Node<Payload>> children = new HashMap<String, Node<Payload>>();
	
	public Node()
	{
	}
	
	public Node<Payload> getChild(String name)
	{
		return children.get(name);
	}
	
	public void setChild(String name, Node<Payload> child)
	{
		if (child == null || children.put(name, child) != null)
			throw new AssertionError("Overwrote child or child was null: " + child);
	}
	
	public PayloadEntry getPayload(String name)
	{
		return payload.get(name);
	}
	
	public Map<String, PayloadEntry> getPayload()
	{
		return payload;
	}
	
	public PayloadEntry getPayloadFor(String name)
	{
		if (name == null || name.length() == 0)
			return getPayload("");
		
		PayloadEntry p = payload.get(name);
		if (p != null)
			return p;
		
		return payload.get("");
	}

	public void setPayload(String name, Payload payload, boolean wildcard)
	{
		this.payload.put(name, new PayloadEntry(payload, wildcard));
	}
}

public class Registry<Payload>
{

	protected static final Logger logger = Logger.getLogger(Registry.class);
	
	//private Binding parentRegistry;
	//private PayloadFactory<Payload> payloadFactory;
	private Node<Payload> root = new Node<Payload>();
	
	//TODO: Integrate this into the tree
	private HashMap<Identifier, Payload> fullIds = new HashMap<Identifier, Payload>();
	/*
	public Registry(PayloadFactory<Payload> payloadFactory, Binding parentRegistry)
	{
		this.parentRegistry = parentRegistry;
		this.payloadFactory = payloadFactory;
	}*/
	
	/*public void setPayloadFactory(PayloadFactory<Payload> payloadFactory)
	{
		this.payloadFactory = payloadFactory;
	}*/
	
	/*public void registerRemote(Identifier uuid, URL url) throws RegistryException
	{
		logger.info("Register: " + uuid + " => " + url);
		if (parentRegistry != null)
			parentRegistry.register(uuid, url);
		//logger.debug("Remote register done");
		//return registerLocal(uuid, url);
	}*/
			
	public Payload registerLocal(Identifier uuid, Payload payload)
		throws PTMException
	{		
		if (!uuid.isAbsolute())
			throw new RegistryException("I need an absolute identifier, not this: " + uuid);
		
		if (!uuid.isAdapter())
		{
			Payload oldPayload = fullIds.get(uuid);
			if (oldPayload != null)
				logger.warn("Replacing: " + oldPayload);
			fullIds.put(uuid, payload);
	/*		if (recurse && parentRegistry != null)
				parentRegistry.register(uuid, url);*/
			return payload;
		}
		
		String[] parts = uuid.getParts();
		Node<Payload> node = root;
		for (int i = 1; i < parts.length - 1; ++i)
		{
			String name = parts[i];
			Node<Payload> n = node.getChild(name);
			if (n == null)
			{
				n = new Node<Payload>();
				node.setChild(name, n);
			}
			node = n;
		}
		
		Node<Payload>.PayloadEntry old = node.getPayload(uuid.getTypename());
		
		//if (old != null)
//			logger.debug("Replacing payload: " + old);
//		logger.info("Register: " + uuid + " => " + url);
		
		node.setPayload(uuid.getTypename(), payload, uuid.isWildcard());
		
		
		return payload;
	}
	
	public Payload resolve(Identifier uuid) throws PTMException
	{
		if (!uuid.isAbsolute())
			throw new RegistryException("I need an absolute id, not this: " + uuid);
		
		if (!uuid.isAdapter() && fullIds.containsKey(uuid))
			return fullIds.get(uuid);
		
		uuid = uuid.getManagerName();
		
		//logger.info("Resolving: " + uuid);
		
		Payload result = null;
		String[] parts = uuid.getParts();
		Node<Payload> node = root;
		//TODO: debug
		for (int i = 1; i < parts.length - 1; ++i)
		//for (int i = 1; i < parts.length; ++i)			
		{
			String typename = parts[i].split(Identifier.TYPE_SEPARATOR, 2)[0];
			Node<Payload>.PayloadEntry e = node.getPayloadFor(typename);
			
			if (e != null && e.wildcard)
				result = e.payload;
			
			node = node.getChild(parts[i]);
			if (node == null)
				break;
		}
		
		Node<Payload>.PayloadEntry e = node.getPayloadFor(uuid.getTypename());
		if (e != null)
			result = e.payload;
				
		if (result == null)
			throw new RegistryException("No adapter found for: " + uuid);
		
		//return node.getPayloadFor(uuid.getTypename()).payload;
		return result;
	}
	
	public Map<Identifier, Payload> resolveAll(Identifier uuid)
		throws PTMException
	{
		boolean highlander = false;
		
		if (uuid.getTypename() != null)
		{
			for (Map.Entry<Identifier, Payload> e : fullIds.entrySet())
				if (e.getKey().getIdentifier().startsWith(uuid.getIdentifier()))
				{
					HashMap<Identifier, Payload> r = new HashMap<Identifier, Payload>();
					r.put(e.getKey(), e.getValue());
					return r;
				}
				
			highlander = true;
		}
		
		uuid = uuid.getManagerName();
		
		Map<Identifier, Payload> result = new HashMap<Identifier, Payload>();
		String[] parts = uuid.getParts();
		Node<Payload> node = root;
		boolean broke = false;	
		Identifier current = new Identifier(Identifier.DELIMITER);
		Node<Payload>.PayloadEntry payload;

		for (int i = 1; i < parts.length - 1; ++i)
		{
			String part = parts[i];
			String parttype = part.split(Identifier.TYPE_SEPARATOR, 2)[0];
			payload = node.getPayload(parttype);
			if (!(payload != null && payload.wildcard))
			{
				payload = node.getPayload("");
				parttype = null;
			}
			if (payload != null && payload.wildcard)
			{
				if (highlander)
					result.clear();
				if (parttype == null)
					result.put(current.slash(Identifier.WILDCARD), payload.payload);
				else
					result.put(current.slash(parttype + Identifier.WILDCARD), payload.payload);
			}
			
			/*
			for (Map.Entry<String, Node<Payload>.PayloadEntry> e : node.getPayload().entrySet())
			{
				String typename = e.getKey();
				payload = e.getValue();
				if (payload.wildcard && typename.equals(parttype) && result.values().contains(payload.payload))
				{
					Identifier id = current.slash(typename + Identifier.WILDCARD);
					assert (!result.keySet().contains(id));
					if (highlander)
						result.clear();
					result.put(id, payload.payload);
				}
			}
			*/
			
			node = node.getChild(part);
			
			if (node != null)
				current = current.slash(part).slash();
			else
			{
				broke = true;
				break;
			}
		}
		
		if (!broke)
		{
			//boolean gotcatchall = false;
			payload = node.getPayload("");
			if (payload != null)
			{
				//gotcatchall = true;
				result.clear();
				Identifier c;
				if (payload.wildcard)
					c = current.slash(Identifier.WILDCARD);
				else 
					c = current.slash();
				
				result.put(c, payload.payload);
			}
			for (Map.Entry<String, Node<Payload>.PayloadEntry> e : node.getPayload().entrySet())
			{
				String typename = e.getKey();
				payload = e.getValue();
				
				if (typename.length() != 0 && !result.containsValue(payload.payload ) && (uuid.getTypename() == null || uuid.getTypename().equals(typename)))
				{
					Identifier c;
					if (payload.wildcard)
						c = current.slash(typename + Identifier.WILDCARD);
					else
						c = current.slash(typename);
					
					if (highlander)
					{
						result.clear();
						result.put(c, payload.payload);
						return result;
					}
					
					result.put(c, payload.payload);
				}
			}
			
			if (highlander && !result.isEmpty())
				return result;
			
/*			Map<Identifier, Payload> parent_result = deferAll(uuid);
			for (Map.Entry<Identifier, Payload> e : parent_result.entrySet())
				if (!result.containsValue(e.getValue()) && (uuid.equals(e.getKey().getDirname()) || !gotcatchall))
					result.put(e.getKey(), e.getValue());
			*/
			for (Map.Entry<Identifier, Payload> e : fullIds.entrySet())
				if (e.getKey().getIdentifier().startsWith(uuid.getIdentifier()))
					result.put(e.getKey(), e.getValue());

		}

		return result;
	}
}
