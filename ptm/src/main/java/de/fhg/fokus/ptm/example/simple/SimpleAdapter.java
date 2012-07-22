package de.fhg.fokus.ptm.example.simple;

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

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;
import de.fhg.fokus.ptm.server.DelegatingResourceAdapter;
import de.fhg.fokus.ptm.server.Manager;
import de.fhg.fokus.ptm.xmlrpc.server.XMLRPCServer;

public class SimpleAdapter extends DelegatingResourceAdapter
{
	public SimpleAdapter(Identifier parent, Manager manager) throws PTMException
	{
		super(parent, manager, "simple"); //We are responsible for the "simple" resource
		SimpleResource r = new SimpleResource(null, "preexisting", "testdata", this);
		resources.put(r.getName(), r);
	}

	HashMap<String, SimpleResource> resources = new HashMap<String, SimpleResource>();
	
	@Override
	public Resource addResource(Identifier parentId, String name, String type,
			Map<String, Object> config) throws PTMException
	{
		if (name == null || "".equals("name"))
			name = "test";
		if (resources.containsKey(name))
			throw new PTMException("Name already exists: " + name);
		Object data = config.get("data");
		SimpleResource r = new SimpleResource(parentId, name, data == null ? null : data.toString(), this);
		resources.put(name, r);
		return r;
	}

	@Override
	public SimpleResource getResource(Identifier uuid) throws PTMException
	{
		return resources.get(uuid.getName());
	}

	@Override
	public Resource[] listResources(Identifier parentId, String type)
			throws PTMException
	{
		logger.debug("Simple list");
		return resources.values().toArray(new Resource[0]);
	}

	/**
	 * 
	 * 
	 * @param args
	 * @throws PTMException 
	 * @throws UUIDException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws PTMException, IOException
	{
		Logger l = Logger.getLogger("de.fhg.fokus");
		l.setLevel(Level.DEBUG);
		l.debug("starting");
		
		XMLRPCServer server = new XMLRPCServer(
				new URL("http://ptm:7000"), 			//URL of the PTM 
				InetAddress.getByName("127.0.0.1"), 	//IP of this machine, needs to be accessible by the PTM
				8008);									//port on which to run the adapter on
		
		SimpleAdapter ra = new SimpleAdapter(null, server.getManager());
		
		server.start();
	}

}
