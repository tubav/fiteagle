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

import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.IdentifierException;
import de.fhg.fokus.ptm.ResourceAdapter;

public class GenericResource extends BasicResource {
	private Map<String, Object> configuration;

	public GenericResource(ResourceAdapter adapter, Identifier identifier) {
		super(adapter, identifier);
	}
	
	public GenericResource(ResourceAdapter adapter, Identifier identifier,  Map<String, Object> configuration) {
		super(adapter, identifier);
		this.configuration = configuration;
	}

	public GenericResource(	BasicAbstractResourceAdapter adapter, String type, String name) throws IdentifierException {
		super(adapter, null, type, name);
	}

	public Map<String, Object> getConfiguration()
	{
		return configuration;
	}
}
