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




import java.net.URL;
import java.util.Map;



public interface Client
{
	Resource getResource(Identifier identifier) throws PTMException;
	
	Resource addResource(Resource parent, String name, String type, Map<String, Object> config, Identifier owner) throws PTMException;
	Resource addResource(Identifier parentId, String name, String type, Map<String, Object> config, Identifier owner) throws PTMException;
	Resource addResource(Resource parent, String name, String type, Map<String, Object> config,	Resource owner) throws PTMException;
	
	Resource[] listResources(Resource parent, String type) throws PTMException;
	Resource[] listResources(Identifier parent, String type) throws PTMException;

	ResourceAdapter getAdapter(Identifier identifier) throws PTMException;
	//ResourceAdapter[] getAdapters(Identifier identifier) throws PTMException;
	
	void register(Identifier identifier, URL url) throws PTMException;
//	void register(Identifier identifier, URL url, ResourceAdapter adapter) throws PTMException;

//	Map<Identifier, ResourceAdapter> getAdapterInfos(Identifier identifier)
	//		throws PTMException;

	Resource addAnonOwnedResource(Identifier identifier, String name,
			String typeName, Map<String, Object> configuration) throws PTMException;

	URL getUrl();
}
