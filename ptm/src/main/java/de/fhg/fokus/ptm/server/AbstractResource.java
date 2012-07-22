package de.fhg.fokus.ptm.server;

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

import java.util.Map;

import de.fhg.fokus.ptm.AttributeException;
import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.IdentifierException;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.ResourceAdapter;

public abstract class AbstractResource extends BasicResource {
	public AbstractResource(Identifier identifier, ResourceAdapter adapter)
	{
		super(adapter, identifier);
	}

	public AbstractResource(String type, String name, BasicAbstractResourceAdapter adapter)
		throws IdentifierException
	{
		this(null, type, name, adapter);
	}
	
	public AbstractResource(Identifier parentId, String name, BasicAbstractResourceAdapter adapter)
		throws IdentifierException
	{
		this(parentId, null, name, adapter);
	}
	
	public AbstractResource(String name, BasicAbstractResourceAdapter adapter)
		throws IdentifierException
	{
		this(null, null, name, adapter);
	}
	
	public AbstractResource(Identifier parentId, String type, String name, BasicAbstractResourceAdapter adapter) throws IdentifierException
	{
		super(adapter, parentId, type, name);
	}
	
	public abstract Map<String, Object> _getConfiguration() throws PTMException;
	
	public Object _getAttribute(String name) 
		throws PTMException
	{
		Object o = null;
		Map <String, Object> config = _getConfiguration();
		
		if (config != null && (o = config.get(name)) != null)
			return o;
		
		throw new AttributeException("Attribute not found: " + name);
	}
	
	public void _setConfiguration(Map<String, Object> configuration)
			throws PTMException
	{
		if (configuration != null)
			for (Map.Entry<String, Object> e : configuration.entrySet())
				_setAttribute(e.getKey(), e.getValue());
	}

	public abstract void _setAttribute(String name, Object value) throws PTMException;

	public abstract void _delete() throws PTMException;
}
