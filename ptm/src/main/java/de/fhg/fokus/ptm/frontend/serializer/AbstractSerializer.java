package de.fhg.fokus.ptm.frontend.serializer;

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

import de.fhg.fokus.ptm.Client;
import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.IdentifierException;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;
import de.fhg.fokus.ptm.frontend.FrontendException;
import de.fhg.fokus.ptm.frontend.Serializer;

public abstract class AbstractSerializer implements Serializer
{
	private Client client;
	
	public AbstractSerializer(Client client)
	{
		this.client = client;
	}
	
	public Client getPTMClient()
	{
		return client;
	}
	
	public Resource getRemoteResource(String uuid) throws FrontendException
	{
		try
		{
			return getRemoteResource(new Identifier(uuid));
		}
		catch (IdentifierException e)
		{
			throw new FrontendException("Illegal uuid: ", e);
		}
	}
	
	public Resource getRemoteResource(Identifier uuid) throws FrontendException
	{
		try
		{
			return client.getResource(uuid);
		}
		catch (PTMException e)
		{
			throw new FrontendException(e);
		}
	}
}
