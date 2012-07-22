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
import de.fhg.fokus.ptm.PTMException;

public abstract class DelegatingResourceAdapter extends AbstractResourceAdapter {

	public abstract AbstractResource getResource(Identifier identifier) throws PTMException;
	
	public DelegatingResourceAdapter(Identifier parentId, Manager manager,
			String... types) throws PTMException {
		super(parentId, manager, types);
	}

	@Override
	public void deleteResource(Identifier identifier) throws PTMException {
		getResource(identifier)._delete();
	}

	@Override
	public Object getAttribute(Identifier identifier, String name)
			throws PTMException {
		return getResource(identifier)._getAttribute(name);
	}

	@Override
	public void setAttribute(Identifier identifier, String name, Object value)
			throws PTMException {
		getResource(identifier)._setAttribute(name, value);
	}

	@Override
	public Map<String, Object> getConfiguration(Identifier identifier)
			throws PTMException {
		return getResource(identifier)._getConfiguration();
	}

	@Override
	public void setConfiguration(Identifier identifier,
			Map<String, Object> config) throws PTMException {
		getResource(identifier)._setConfiguration(config);
	}
}
