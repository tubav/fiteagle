package de.fhg.fokus.ptm.example.swallow;

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

/* 
 * This adapter manages swallows (Birds, whose sole purpose in life is to carry coconuts).
 * They can be freely instantiated (meaning a new one is born). Upon birth, they can be
 * given a coconut to carry around. If none is given, they will have a new one created for them
 * Furthermore, they expose a read-only attribute "airspeed" which is based on the weight of the
 * coconut they carry.
 * For this implementation, we have chosen to implement Swallows as their own class, 
 * so please have a look at Swallow.java as well.
 * 
 */

public class SwallowAdapter extends DelegatingResourceAdapter 
{
	//This time, a bit more information is stored in the Resource objects themselves, so we'll keep them around
	private Map<String, Swallow> swallows = new HashMap<String, Swallow>();
	
	public SwallowAdapter(Identifier parentId, Manager manager)
			throws PTMException 
	{
		super(parentId, manager, "swallow");
	}

	@Override
	public Resource addResource(Identifier parentId, String name, String type,
			Map<String, Object> config) throws PTMException {

		if (name != null)
			throw new PTMException("sorry, swallows are auto-named");
	
		//if a coconut reference has been provided by the config, get it
		//otherwise, the swallow will create one
		Resource coconut = (Resource)config.get("coconut");
		
		Swallow swallow = new Swallow(this, coconut);
		swallows.put(swallow.getName(), swallow);
		return swallow;
	}

	@Override
	public Swallow getResource(Identifier identifier) throws PTMException 
	{
		//if no such swallow exists, null will be returned which will be transmitted to the client as "no such resource"
		return swallows.get(identifier.getName()); 
	}

	@Override
	public Resource[] listResources(Identifier parentId, String type)
			throws PTMException 
	{
		//simply, return all the swallows in our hashmap
		return swallows.values().toArray(new Resource[0]); //While we wait for this to happen, allow me to mention
														   //how utterly pathetic I think java's generics are
	}
	
	//Note that we did not implement getConfiguration and the like this time
	//those will be automatically delegated to the resource instance
	
	
	public static void main(String[] args) throws PTMException, IOException
	{
		Logger l = Logger.getLogger("de.fhg.fokus");
		l.setLevel(Level.DEBUG);
		l.debug("starting");
		
		XMLRPCServer server = new XMLRPCServer(
				new URL("http://ptm:8000"), 			//URL of the PTM 
				InetAddress.getByName("127.0.0.1"), 	//IP of this machine, needs to be accessible by the PTM
				8009);									//port on which to run the adapter on
		
		SwallowAdapter ra = new SwallowAdapter(null, server.getManager());
		
		server.start();
	}

}


