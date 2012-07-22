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
import de.fhg.fokus.ptm.IdentifierException;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.server.AbstractResource;
import de.fhg.fokus.ptm.server.BasicAbstractResourceAdapter;

public class PersonImpl extends AbstractResource implements Person
{
	private String city;
	
	public PersonImpl(Identifier uuid, String city, BasicAbstractResourceAdapter adapter)
	{
		super(uuid, adapter);
		this.city = city;
	}

	public PersonImpl(Identifier parentId, String name, String city, BasicAbstractResourceAdapter adapter)
			throws IdentifierException
	{
		super(parentId, "Person", name, adapter);
		this.city = city;
	}
	
	/* (non-Javadoc)
	 * @see de.fhg.fokus.ptm.example.simple.Person#getCity()
	 */
	public String getCity()
	{
		return city;
	}

	@Override
	public Map<String, Object> _getConfiguration() throws PTMException
	{
		HashMap<String, Object> configuration = new HashMap<String, Object>();
		configuration.put("name", getName());
		configuration.put("city", getCity());
		return configuration;
	}	

	@Override
	public void _setAttribute(String name, Object value) throws PTMException
	{
		if ("city".equals(name))
			city = value.toString();
		else if ("name".equals(name))
			throw new AttributeException("name is read only");
		else 
			throw new AttributeException("Unknown attribute: " + name);
	}
	
	@Override
	public void _delete(){
		// TODO
	}	
}
