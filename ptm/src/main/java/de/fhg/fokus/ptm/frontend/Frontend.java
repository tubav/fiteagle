package de.fhg.fokus.ptm.frontend;

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

import de.fhg.fokus.ptm.Client;
import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.IdentifierException;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;

public class Frontend
{
	private Client client;
	private Serializer serializer;
	
	public Frontend() throws FrontendException
	{
		this(new FrontendProperties());
	}
	
	public Frontend(URL properties) throws FrontendException
	{
		this(new FrontendProperties(properties));
	}
	
	public Frontend(FrontendProperties properties) throws FrontendException
	{
		
		try
		{
			Client client = properties.getClientClass().getConstructor(URL.class).newInstance(properties.getRegistryUrl());
			Serializer s = properties.getSerializerClass().getConstructor(Client.class).newInstance(client);
			init(client, s);
		}
		catch (Exception e)
		{
			throw new FrontendException(e);
		}

	}
	
	public Frontend(Client client, Serializer serializer)
	{
		init(client, serializer);
	}
	
	private void init(Client client, Serializer serializer)
	{
		this.client = client;
		this.serializer = serializer;
	}
	
	public String getResource(String uuid) throws FrontendException
	{
		try
		{
			Resource r = client.getResource(makeUUID(uuid));
			return serializer.writeConfiguration(new ResourceInfo(r));
		}
		catch (PTMException e)
		{
			throw new FrontendException(e);
		}
	}
	
	public String addResource(String parentId, String name, String config) throws FrontendException
	{
		ResourceInfo info = serializer.readConfiguration(config);
		try
		{
			Resource r = client.addAnonOwnedResource(new Identifier(parentId), name, info.getTypeName(), info.getConfiguration());
			return serializer.writeConfiguration(new ResourceInfo(r));
		}
		catch (PTMException e)
		{
			throw new FrontendException(e);
		}
	}
	
	protected Identifier makeUUID(String uuid) throws FrontendException
	{
		try
		{
			return new Identifier(uuid);
		}
		catch (IdentifierException e)
		{
			throw new FrontendException(e);
		}
		
	}
}
