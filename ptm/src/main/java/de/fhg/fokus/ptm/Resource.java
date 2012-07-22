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

public interface Resource
{
	/**
	 * get the ID of this Resource
	 * @return the ID of the resource, never null
	 */
	Identifier getIdentifier();
	
	Resource getParent() throws PTMException;

	/**
	 * 
	 * @return a map of Configuration data, never null
	 * @throws PTMException
	 */
	Map<String, Object> getConfiguration() throws PTMException;
	
	/**
	 * 
	 * @param configuration - a map of new configuration data
	 * @throws PTMException
	 */
	void setConfiguration(Map<String, Object> configuration) throws PTMException;
	
	/**
	 * Get a single configuration attribute by name
	 * @param name - the attributes name
	 * @return the attribute, never null
	 * @throws PTMException
	 */
	Object getAttribute(String name) throws PTMException;
	
	/**
	 * Set a single configuration attribute by name
	 * @param name - the attributes name
	 * @param value - its value
	 * @throws PTMException
	 */
	void setAttribute(String name, Object value) throws PTMException;
	
	/**
	 * Deletes resource
	 * @throws PTMException
	 */
	void delete() throws PTMException;
	void delete(Identifier owner, boolean force) throws PTMException;

	void acquire(Resource owner) throws PTMException;
	void acquire(Identifier owner, boolean weak) throws PTMException;
	void acquire(Resource owner, boolean weak) throws PTMException;

	void release() throws PTMException;
	void release(Identifier owner) throws PTMException;
	void release(Resource owner) throws PTMException;
	
	String getName();
	String getTypename();
}
