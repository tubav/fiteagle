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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.PropertiesHelper;

public class ManagerProperties
{
	private URL url;
		
	public ManagerProperties(String filename) throws PTMException
	{
		this(filename, null);
	}
	
	public ManagerProperties(String filename, URL url) throws PTMException
	{
		this(ManagerProperties.class.getResourceAsStream(filename), url);
	}	
	
	public ManagerProperties(URL filename) throws PTMException, IOException
	{
		this(filename.openStream(), null);
	}
	
	public ManagerProperties(URL filename, URL url) throws PTMException, IOException
	{
		this(filename.openStream(), url);
	}
	
	public ManagerProperties(InputStream is) throws PTMException
	{
		this(is, null);
	}
	
	public ManagerProperties(InputStream is, URL url) throws PTMException
	{
		if (url == null)
			try
			{
				if (is == null && url == null)
					throw new PTMException("Properties file not found");
				else if (is == null)
					return;
				Properties properties = new Properties();
				properties.load(is);
				if (url == null)
					url = new URL(PropertiesHelper.getString(properties, "ptm.manager.url"));
			}
			catch (Exception e)
			{
				throw new PTMException("Error loading properties file ", e);
			}
			
		System.out.println("Propinti: " + url);
		this.url = url;
	}
	
	public URL getURL()
	{
		return url;
	}
}
