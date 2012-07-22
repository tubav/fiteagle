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

import java.util.HashMap;
import java.util.Map;

import de.fhg.fokus.ptm.AttributeException;
import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.IdentifierException;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.server.AbstractResource;
import de.fhg.fokus.ptm.server.BasicAbstractResourceAdapter;

public class SimpleResource extends AbstractResource
{
	String data;

	public SimpleResource(Identifier uuid, String data, BasicAbstractResourceAdapter adapter)
	{
		super(uuid, adapter);
		this.data = data;
	}

	public SimpleResource(Identifier parentId, String name, String data, BasicAbstractResourceAdapter adapter) throws IdentifierException
	{
		super(parentId, "simple", name, adapter); //We already know our typename. only parentId and name are subject to change
		this.data = data;
	}

	@Override
	public Map<String, Object> _getConfiguration() throws PTMException
	{
		HashMap<String, Object> config = new HashMap<String, Object>();
		config.put("data", data);
		return config;
	}

	@Override
	public void _setAttribute(String name, Object value) throws PTMException
	{
		if (!"data".equals(name))
			throw new AttributeException("unknown configuration key: " + name);
		this.data = value == null ? null : value.toString();
	}

	@Override
	public void _delete() throws PTMException
	{

	}
}
