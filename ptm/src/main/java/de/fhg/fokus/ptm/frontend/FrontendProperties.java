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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

import de.fhg.fokus.ptm.Client;
import de.fhg.fokus.ptm.PropertiesHelper;

public class FrontendProperties
{
	public static final String PROPERTIES_FILE = "ptmfrontend.properties";
	
	protected static Logger logger = Logger.getLogger(FrontendProperties.class);
	
	private URL registryUrl;
	private Class<? extends Client> clientClass;
	private Class<? extends Serializer> serializerClass;
	
	public FrontendProperties() throws FrontendException
	{
		this(PROPERTIES_FILE);
	}
	
	public FrontendProperties(String filename) throws FrontendException
	{
		this(FrontendProperties.class.getResource(filename));
	}
		
	public FrontendProperties(URL filename) throws FrontendException
	{
		if (filename == null)
			throw new FrontendException("URL is null");
		logger.debug("Loading Proeprties from: " + filename);
		try
		{
			InputStream is = filename.openStream();
			if (is == null)
				throw new FileNotFoundException("File not found: " + filename);
			Properties properties = new Properties();
			properties.load(is);
			registryUrl = new URL(PropertiesHelper.getString(properties, "ptm.frontend.registryUrl"));
			clientClass= Class.forName(PropertiesHelper.getString(properties, "ptm.frontend.clientClass")).asSubclass(Client.class);
			serializerClass = Class.forName(PropertiesHelper.getString(properties, "ptm.frontend.serializerClass")).asSubclass(Serializer.class);
		}
		catch (Exception e)
		{
			throw new FrontendException("Error loading properties file: " + filename, e);
		}
	}

	public URL getRegistryUrl()
	{
		return registryUrl;
	}

	public Class<? extends Client> getClientClass()
	{
		return clientClass;
	}

	public Class<? extends Serializer> getSerializerClass()
	{
		return serializerClass;
	}
}
