package de.fhg.fokus.ptm;

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


public interface ResourceAdapter
{
	/**
	 * Get a resource by UUID
	 * @param uuid
	 * @return a resource, never null
	 * @throws PTMException
	 */
	Resource getResource(Identifier identifier) throws PTMException;
	
	/**
	 * Add a resource with given name, type and configuration
	 * @param parentId - the parentId of the Resource, usually the same as the adapters Id
	 * @param name - a name for the resource. Can be null in which case the adapter must assign a name
	 * @param type - type of the resource
	 * @param config - configuration data
	 * @return the new resource. Never null
	 * @throws PTMException
	 */
	Resource addResource(Identifier parentId, String name, String type, Map<String, Object> config, Identifier owner) throws PTMException;
	
	/**
	 * 
	 * @param parentId 
	 * @param type
	 * @return
	 * @throws PTMException
	 */
	Resource[] listResources(Identifier parentId, String type) throws PTMException;
	
	//UUID getParentId();
	
	//Resource getParent() throws PTMException;
	
	Client getClient();

	Map<String, Object> getConfiguration(Identifier identifier) throws PTMException;


	void setConfiguration(Identifier identifier, Map<String, Object> config) throws PTMException;
	
	Object getAttribute(Identifier identifier, String name) throws PTMException;
	
	void setAttribute(Identifier identifier, String name, Object value) throws PTMException;
	
	void acquireResource(Identifier identifier, Identifier owner, boolean weak) throws PTMException;
	void releaseResource(Identifier identifier, Identifier owner) throws PTMException;
	
	void deleteResource(Identifier identifier, Identifier owner, boolean force) throws PTMException;
	
	void strongDeleted(Identifier identifier, Identifier ref);
	void weakDeleted(Identifier identifier, Identifier ref);
	void childDeleted(Identifier identifier, Identifier child);
	
	Owners getOwners(Identifier identifier) throws PTMException;
	
	boolean haveResource (Identifier identifier) throws PTMException;
}
