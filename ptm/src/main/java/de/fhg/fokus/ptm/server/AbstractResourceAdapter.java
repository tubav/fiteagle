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
import de.fhg.fokus.ptm.NoSuchAttributeException;
import de.fhg.fokus.ptm.NoSuchResourceException;
import de.fhg.fokus.ptm.Owners;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;

public abstract class AbstractResourceAdapter extends BasicAbstractResourceAdapter {

	public AbstractResourceAdapter(Identifier parentId, Manager manager,
			String... types) throws PTMException {
		super(parentId, manager, types);
	}

	@Override
	public void acquireResource(Identifier identifier, Identifier owner,
			boolean weak) throws PTMException {
	}

	@Override
	public void releaseResource(Identifier identifier, Identifier owner)
			throws PTMException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public Owners getOwners(Identifier identifier) throws PTMException {
		// TODO
		return new LocalOwners();
	}
	
	@Override
	public boolean haveResource(Identifier identifier) throws PTMException {
		try
		{
			getResource(identifier);
		}
		catch (NoSuchResourceException e)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public Object getAttribute(Identifier identifier, String name) throws PTMException 
	{
		Map<String, Object> config = getConfiguration(identifier);
		if (!config.containsKey(name))
			throw new NoSuchAttributeException(name);
		return config.get(name);
	}
	
	@Override
	public final Resource addResource(Identifier parentId, String name, String type,
			Map<String, Object> config, Identifier owner) throws PTMException {
		Resource r = addResource(parentId, name, type, config);
		resourceAdded(r.getIdentifier());
		return r;
	}
	
	public final void deleteResource(Identifier identifier, Identifier owner, boolean force) throws PTMException
	{
		deleteResource(identifier);
		resourceDeleted(identifier);
	}
	
	@Override
	public void setConfiguration(Identifier identifier, Map<String, Object> config)
			throws PTMException {
		for (Map.Entry<String, Object> e : config.entrySet())
			setAttribute(identifier, e.getKey(), e.getValue());
	}
	
	protected abstract void deleteResource(Identifier identifier) throws PTMException;

	protected abstract Resource addResource(Identifier parentId, String name, String type,
			Map<String, Object> config) throws PTMException;
}
