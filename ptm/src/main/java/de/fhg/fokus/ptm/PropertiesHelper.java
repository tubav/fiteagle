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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletContext;

public class PropertiesHelper
{
	public static InputStream getResourceAsStream(ServletContext context, String name) throws FileNotFoundException, MalformedURLException, IOException
	{
		return getResource(context, name).openStream();
	}
	
	public static URL getResource(ServletContext context, String name) throws FileNotFoundException, MalformedURLException
	{
		if (!name.startsWith("/"))
				name = "/" + name;
		
		URL url = context.getResource("/WEB-INF/classes" + name);
		if (url == null)
			url = context.getResource("/WEB-INF" + name);
		if (url == null)
			url = context.getResource(name);
		if (url == null)
			throw new FileNotFoundException(name);
		return url;
	}
	
	public static String getString(Properties properties, String name) throws PTMException
	{
		return getString(properties, name, null);
	}
	
	public static String getString(Properties properties, String name, String defaultValue) throws PTMException
	{
		String s = properties.getProperty(name, defaultValue);
		if (s == null)
			throw new PTMException("Key not found: " + name);
		return s;
	}
}
