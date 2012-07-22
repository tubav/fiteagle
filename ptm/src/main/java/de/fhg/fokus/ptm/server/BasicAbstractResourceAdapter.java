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

import org.apache.log4j.Logger;

import de.fhg.fokus.ptm.Client;
import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.IdentifierException;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;
import de.fhg.fokus.ptm.ResourceAdapter;

public abstract class BasicAbstractResourceAdapter implements ResourceAdapter
{
	protected static Logger logger = Logger.getLogger(BasicAbstractResourceAdapter.class);
	
	private Manager manager;
	private Identifier parentId;
	
	public BasicAbstractResourceAdapter(Identifier parentId, Manager manager, String... types) throws PTMException 
	{
		this.manager = manager;
		this.parentId = new Identifier(parentId);
		
		if (parentId != null && !parentId.isRoot() && (!parentId.isAbsolute() || parentId.isAdapter() ))
			throw new IdentifierException("Illegal value for parentId: " + parentId);
		
		for (String t : types)
			manager.addAdapter(new Identifier(parentId).slash(t), this);
	}
	
	public BasicAbstractResourceAdapter(String parentId, Manager manager, String... types) throws PTMException
	{
		this(new Identifier(parentId), manager, types);
	}
	
	public Manager getManager()
	{
		return manager;
	}
	
	public Identifier getParentId()
	{
		return parentId;
	}
	
	public Resource getParent() throws PTMException
	{
		if (parentId == null)
			return null;
		return getClient().getResource(parentId);
	}
	
	public Client getClient()
	{
		return manager.getClient();
	}
	
	public Logger getLogger()
	{
		return logger;
	}
	
	@Override
	public void childDeleted(Identifier identifier, Identifier child) {	}
	
	@Override
	public void strongDeleted(Identifier identifier, Identifier ref) {}

	@Override
	public void weakDeleted(Identifier identifier, Identifier ref) {}
	
	protected void resourceAdded(Identifier identifier) {}
	protected void resourceDeleted(Identifier identifier) {}
}
