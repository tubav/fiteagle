package de.fhg.fokus.ptm.example.persondb;

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

import de.fhg.fokus.ptm.AttributeException;
import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.NoSuchResourceException;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;
import de.fhg.fokus.ptm.server.AbstractResource;
import de.fhg.fokus.ptm.server.DelegatingResourceAdapter;
import de.fhg.fokus.ptm.server.Manager;

public class PersonAdapter extends DelegatingResourceAdapter
{
	private HashMap<String, PersonImpl> persons = new HashMap<String, PersonImpl>(); 
	
	public PersonAdapter(Manager manager) throws PTMException
	{
		super(new Identifier("/persondb_0"), manager, "Person");
	}

	@Override
	public Resource addResource(Identifier parentId, String name, String type, Map<String, Object> config) 
		throws PTMException
	{
		if (persons.containsKey(name))
			throw new PTMException("A record by the name " + name + " already exists");
		if (name == null)
			throw new PTMException("Name is null");
		Object city = config.get("city");
		if (!(city instanceof String))
			throw new AttributeException("Illegal value for city: " + city);
		
		PersonImpl person = new PersonImpl(parentId, name, (String)city, this);
		persons.put(name, person);
		return person;	
	}

	@Override
	public AbstractResource getResource(Identifier uuid) throws PTMException
	{
		PersonImpl person = persons.get(uuid.getName());
		if (person == null)
			throw new NoSuchResourceException(uuid);
		return person;
	}

	@Override
	public Resource[] listResources(Identifier parentId, String type)
			throws PTMException
	{
		return persons.values().toArray(new Resource[0]);
	}

	@Override
	public Map<String, Object> getConfiguration(Identifier identifier)
			throws PTMException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConfiguration(Identifier identifier,
			Map<String, Object> config) throws PTMException {
		// TODO Auto-generated method stub
		
	}

/*	public static void main(String[] args) throws UUIDException, PTMException, IOException
	{
		XMLRPCServer server = new XMLRPCServer(
					new URL("http://127.0.0.1:8000"),
					8010
				);
		
		PersonAdapter ra = new PersonAdapter(server.getManager());
		server.start();
	}*/
}
