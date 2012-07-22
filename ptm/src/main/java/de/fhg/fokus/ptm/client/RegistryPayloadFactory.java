package de.fhg.fokus.ptm.client;

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

import org.apache.log4j.Logger;

import de.fhg.fokus.ptm.Client;
import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.ResourceAdapter;
import de.fhg.fokus.ptm.registry.PayloadFactory;
import de.fhg.fokus.ptm.xmlrpc.client.XMLRPCShadow;

public class RegistryPayloadFactory implements PayloadFactory<ResourceAdapter> {
	private Client client;
	
	private static Logger logger = Logger.getLogger(RegistryPayloadFactory.class);
	
	public RegistryPayloadFactory(Client client)
	{
		this.client = client;
	}
	
	@Override
	public ResourceAdapter make(Identifier uuid, URL url, ResourceAdapter old)
			throws PTMException {
		if (old instanceof Shadow && ((Shadow)old).getURL().equals(url))
			return old;
		if (old != null && !(old instanceof Shadow))
			return old; //TODO: be smarter here
		logger.debug("Creating shadow for " + uuid + " at " + url);
		return new XMLRPCShadow(url, client);
	}

}
