package de.fhg.fokus.ptm.marshaller;

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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import de.fhg.fokus.ptm.Client;
import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.Owners;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;
//import org.apache.log4j.Logger;

public class Marshaller {
	private final Client client;
	
	private static Logger logger = Logger.getLogger(Marshaller.class);
	
	public Marshaller(Client client)
	{
		this.client = client;
	}
	
	public Logger getLogger()
	{
		return logger;
	}
	
	public Object pack(Object o) throws PTMException
	{
		if (o instanceof Identifier)
			return packIdentifier((Identifier)o);
		if (o instanceof Resource)
			return packResource((Resource)o);
		if (o instanceof String)
			return o;
		return packAttribute(o);
	}
	
	public Object packAttribute(Object o) throws PTMException
	{
		if (o == null || o.getClass().isPrimitive() || (o instanceof Number) || (o instanceof Boolean) || (o instanceof Character))
			return o;
		if (o.getClass().isArray())
			return packArray(o);
		if (o instanceof Map)
			return packMap((Map<?, ?>)o);
		if (o instanceof Resource)
			return "r" + ((Resource)o).getIdentifier();
		if (o instanceof Identifier)
			return "i" + o;
		if (o instanceof String)
			return "s" + o;
		if (o instanceof Collection)
			return packArray(((Collection)o).toArray());
		throw new PTMException("Dont know how to marshal " + o); 
	}
	
	public Object packArray(Object o) throws PTMException
	{
		if (o.getClass().getComponentType().isPrimitive())
			return o;
 
		Object[] arr = (Object[])o;
		Object[] result = new Object[arr.length];
		for (int i = 0; i < arr.length; ++i)
			result[i] = packAttribute(arr[i]);
		return result;
	}

	public Object packMap(Map<?, ?> o)
		throws PTMException
	{
		logger.debug("Packing: " + o);
        Map<String, Object> map = new HashMap<String, Object>();
        for (Map.Entry<?, ?> e : o.entrySet())
    		if (!(e.getKey() instanceof String))
    	 		throw new PTMException("Illegal type for key: " + o);
    		else
    			map.put((String)e.getKey(), packAttribute(e.getValue()));
 
        return map;
	}
	
	public Object packResource(Resource r)
	{
		return packIdentifier(r.getIdentifier());
	}
	
	public Object packOwners(Owners owners) throws PTMException
	{
		return new Object[] {
				owners.getAnnonymousOwners(),
				owners.getOutsideOwners(),
				owners.getStrongOwners(),
				owners.getWeakOwners()
		};
	}
	
	public Owners unpackOwners(Object o) throws PTMException
	{
		return new RemoteOwners(o);
	}
	
	public Object packIdentifier(Identifier identifier)
	{
		if (identifier == null)
			return null;
		return identifier.getIdentifier();
	}
    
	public Map<String, Object> unpackMap(Map<?, ?> map) throws PTMException
	{
		Map<String, Object> result = new HashMap<String, Object>();
		for (Map.Entry<?, ?> e : map.entrySet())
			result.put(e.getKey().toString(), unpackAttribute(e.getValue()));
		return result;
	}

	public Object unpackAttribute(Object o) throws PTMException
	{
		if (o instanceof String)
		{
			String s = (String) o;
			if (s.length() == 0)
				throw new PTMException("Missing string prefix");
			switch(s.charAt(0))
			{
			case 's':
				return s.substring(1);
			case 'i':
				return new Identifier(s.substring(1));
			case 'r':
				Identifier id = new Identifier(s.substring(1));
				return new RemoteResource(client.getAdapter(id), id);
			default:
				throw new PTMException("Illegal string prefix on " + s);
			}
		}
		if (o == null || o.getClass().isPrimitive() || (o instanceof Number) || (o instanceof Boolean) || (o instanceof Character))
			return o;
		if (o.getClass().isArray())
			if (o.getClass().getComponentType().isPrimitive())
				return o;
			else 
				return unpackArray((Object[])o);
		if (o instanceof Map)
			return unpackMap((Map<?, ?>)o);

		throw new PTMException("Dont know how to unmarshall " + o);
	}
	
	public Object[] unpackArray(Object[] objects) throws PTMException
    {
			for (int i = 0; i < objects.length; ++i)
            	 objects[i] = unpackAttribute(objects[i]);
            return objects; 
    }
 
	public Identifier unpackIdentifier(String id, boolean need_full) throws PTMException
	{
		if (id == null)
			if (need_full)
				throw new PTMException("No Identifier given");
			else
				return null;
		return new Identifier(id, need_full);
	}
	
	public Identifier unpackIdentifier(String id) throws PTMException
	{
		return unpackIdentifier(id, false);
	}
	
	public Resource unpackResource(String identifier) throws PTMException
	{
		if (identifier == null  || "".equals(identifier))
			throw new PTMException("Missing identifier");
		Identifier id = unpackIdentifier(identifier, true);
		return new RemoteResource(client.getAdapter(id), id);
	}
	
	public boolean unpackBoolean(Object o) throws PTMException
	{
		if (o instanceof Boolean)
			return (Boolean)o;
		if (o instanceof Integer)
		{
			int i = ((Integer)o).intValue();
			if (i == 0 || i == 1)
				return i != 0;
		}
		if (o instanceof String)
		{
			String s = ((String)o).toLowerCase();
			if (s.equals("true") || s.equals("false"))
				return s.equals("true");
		}
			
		throw new PTMException("Unexpected answer for boolean: " + o);
	}
}
