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

import de.fhg.fokus.ptm.Client;
import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.IdentifierException;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;
import de.fhg.fokus.ptm.ResourceAdapter;

public class BasicResource implements Resource {
	private ResourceAdapter adapter;
	private Identifier identifier;
	
	protected BasicResource(ResourceAdapter adapter, Identifier identifier)
	{
		this.identifier = identifier;
		this.adapter = adapter;
	}
	
	public BasicResource(BasicAbstractResourceAdapter adapter, Identifier parentId,
			String type, String name) throws IdentifierException 
	{
		if (type == null)
		{
			type = getClass().getName().toLowerCase();
			int i = type.lastIndexOf(".");
			if (i >= 0)
				type = type.substring(i + 1);
		}
		
		if (parentId == null)
			parentId = adapter.getParentId();
		
		identifier = parentId.makeChildIdentifier(type, name);
		this.adapter = adapter;		
	}

	protected ResourceAdapter getAdapter()
	{
		return adapter;
	}
	
	public String getName()
	{
		return identifier.getName();
	}

	public String getTypename()
	{
		return identifier.getTypename();
	}
	
	@Override
	public final void acquire(Resource resource) throws PTMException {
		//TODO: implement
		//	getAdapter().acquireResource(resource.getIdentifier(), null, false);
	}

	@Override
	public final void delete() throws PTMException {
		getAdapter().deleteResource(identifier, null, true);

	}

	@Override
	public final Object getAttribute(String name) throws PTMException {
		return getAdapter().getAttribute(identifier, name);
	}

	@Override
	public Map<String, Object> getConfiguration() throws PTMException {
		return getAdapter().getConfiguration(identifier);
	}

	@Override
	public final Resource getParent() throws PTMException {
		if (identifier == null || identifier.isRoot())
			return null;
		return getAdapter().getClient().getResource(identifier.getParentId());
	}

	@Override
	public final Identifier getIdentifier() {
		return identifier;
	}

	@Override
	public final void setAttribute(String name, Object value) throws PTMException {
		getAdapter().setAttribute(identifier, name, value);

	}

	@Override
	public final void setConfiguration(Map<String, Object> configuration)
			throws PTMException {
		getAdapter().setConfiguration(identifier, configuration);

	}

	@Override
	public final void acquire(Identifier owner, boolean weak) throws PTMException {
		getAdapter().acquireResource(identifier, owner, weak);
		
	}

	@Override
	public final void acquire(Resource owner, boolean weak) throws PTMException {
		getAdapter().acquireResource(identifier, owner.getIdentifier(), weak);
		
	}

	@Override
	public final void release() throws PTMException {
		getAdapter().releaseResource(identifier, null);
		
	}

	@Override
	public final void release(Identifier owner) throws PTMException {
		getAdapter().releaseResource(identifier, owner);
		
	}

	@Override
	public final void release(Resource owner) throws PTMException {
		getAdapter().releaseResource(identifier, owner.getIdentifier());
		
	}

	@Override
	public final void delete(Identifier owner, boolean force) throws PTMException {
		getAdapter().deleteResource(identifier, owner, force);
	}

	public Client getClient()
	{
		return getAdapter().getClient();
	}
}
