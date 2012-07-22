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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.NoSuchResourceException;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;
import de.fhg.fokus.ptm.server.AbstractResourceAdapter;
import de.fhg.fokus.ptm.server.GenericResource;
import de.fhg.fokus.ptm.server.Manager;
import de.fhg.fokus.ptm.xmlrpc.server.XMLRPCServer;

/*
 * 
 * This adapter implements management for coconuts (that brown hairy things that occasionally fall from trees).
 * Coconuts can be freely instantiated (meaning a new one is grown) and expose a single attribute: their weight.
 * Note that this example focuses on the resource adapter side of things. The actual configuration of coconut trees
 * is left as an exercise for the reader.
 * 
 */

public class CoconutAdapter extends AbstractResourceAdapter 
{

	//We will use this to store the weights of existing Coconuts
	HashMap<Identifier, Integer> nuts = new HashMap<Identifier, Integer>();
	
	public CoconutAdapter(Identifier parentId, Manager manager)
			throws PTMException 
	{
		//propagate the fact that we feel responsible for the "coconut" resource type
		super(parentId, manager, "coconut");
	}

	@Override
	public Resource addResource(Identifier parentId, String name, String type,
			Map<String, Object> config) throws PTMException 	
	{
		
		//We are only registered for the coconut resource, so nothing else can reach us
		assert("coconut".equals(type));
		//This case is covered by the framework. name is always null or not empty.
		assert(! "".equals(name));
		
		//if no name for the new instance was specified, we must generate one
		if (name == null)
			name = generateName();
		else if (coconutExists(name))
			//however, when a name is specified it must not already exist
			throw new PTMException("A coconut by the name of " + name + " already exists");
		
		//get specified configuration, use default value if missing
		//note, that this might create a ClassCastException, which will be reported as a configuration error to the client
		Integer weight = (Integer)config.get("weight");
		if (weight == null)
			weight = 42;
		
		createCoconut(name, weight);
		
		
		//At this point the actual resource instance has been created 
		//and we must now return an object representing it
		//First, to create an identifier for the object:
		Identifier newId = getParentId().makeChildIdentifier("coconut", name);
		
		//Since we have been to lazy to implement an actual class for our resource,
		//we will simply use the GenericResource class
		Resource newResource = new GenericResource(this, newId);
		
		//Another (shorter) way would be to let GenericResource puzzle together the Id info itself
		//Note: this line is completely equivalent to the one above
		//newResource = new GenericResource(this, "coconut", name);
		
		//remember the weight of the new coconut
		nuts.put(newId, weight);
		
		return newResource;
	}

	@Override
	public Resource getResource(Identifier identifier) throws PTMException 
	{
		//these are again ensured by the system
		assert(identifier.getParentId().equals(this.getParentId()));
		assert(identifier.getTypename().equals("coconut"));
		
		//check if the requested instance actually exists
		if (!coconutExists(identifier.getName()))
			throw new NoSuchResourceException(identifier);
		
		return new GenericResource( this, identifier);
	}

	//return a list of all existing coconuts
	@Override
	public Resource[] listResources(Identifier parentId, String type)
			throws PTMException 
	{
		ArrayList<Resource> result = new ArrayList<Resource>();
		for (Identifier id : nuts.keySet())
			result.add(new GenericResource(this, id));
		
		return result.toArray(new Resource[0]);
	}
	
	//get the configuration of an existing instance
	@Override
	public Map<String, Object> getConfiguration(Identifier identifier) throws PTMException
	{
		//these are again ensured by the system
		assert(identifier.getParentId().equals(this.getParentId()));
		assert(identifier.getTypename().equals("coconut"));
		
		Map<String, Object> config = new HashMap<String, Object>();
		config.put("weight", nuts.get(identifier));
		return config;
	}
	
	//reconfigure an existing instance
	@Override
	public void setConfiguration(Identifier identifier, Map<String, Object> config) throws PTMException
	{
		throw new PTMException("Sorry, coconuts are readonly");
	}

	private boolean coconutExists(String name) throws PTMException
	{
		return nuts.containsKey(new Identifier(null, "coconut", name));
	}
	
	private String generateName()
	{
		//TODO: generate a new and unique name for a coconut to be instantiated
		return "abc"; //dummy
	}
	
	private void createCoconut(String name, Integer weight)
	{
		//TODO: Do whatever is neccessary to create the actual coconut
	}

	//Delete an existing coconut
	@Override
	public void deleteResource(Identifier identifier) throws PTMException {
		//just forget it
		nuts.remove(identifier);
		
	}

	@Override
	public void setAttribute(Identifier identifier, String name, Object value)
			throws PTMException {
		throw new PTMException("sorry, read only");
	}
	
	public static void main(String[] args) throws PTMException, IOException
	{
		Logger l = Logger.getLogger("de.fhg.fokus");
		l.setLevel(Level.DEBUG);
		l.debug("starting");
		
		XMLRPCServer server = new XMLRPCServer(
				new URL("http://ptm:8000"), 			//URL of the PTM 
				InetAddress.getByName("127.0.0.1"), 	//IP of this machine, needs to be accessible by the PTM
				8008);									//port on which to run the adapter on
		
		CoconutAdapter ra = new CoconutAdapter(null, server.getManager());
		
		server.start();
	}
}
