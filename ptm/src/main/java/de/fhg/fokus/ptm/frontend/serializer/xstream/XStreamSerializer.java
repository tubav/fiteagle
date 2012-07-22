package de.fhg.fokus.ptm.frontend.serializer.xstream;

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

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import de.fhg.fokus.ptm.Client;
import de.fhg.fokus.ptm.Resource;
import de.fhg.fokus.ptm.frontend.FrontendException;
import de.fhg.fokus.ptm.frontend.ResourceInfo;
import de.fhg.fokus.ptm.frontend.serializer.AbstractSerializer;

public class XStreamSerializer extends AbstractSerializer
{
	private SAXBuilder parser = new SAXBuilder();
	protected Logger logger = Logger.getLogger(XStreamSerializer.class);
	
	public XStreamSerializer(Client client)
	{
		super(client);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResourceInfo readConfiguration(String configuration)
			throws FrontendException
	{
		try
		{
			HashMap<String, Object> configMap = new HashMap<String, Object>(); 
			Document doc = parser.build(new StringReader(configuration));
			Element root = doc.getRootElement();
			String typeName = root.getName();
			Element xmlConfig = root.getChild("configuration");
			if (xmlConfig == null)
				xmlConfig = root;
			
			List<Element> children = xmlConfig.getChildren();
			for (Element child : children)
				configMap.put(child.getName(), makeValue(child));
			
			
			return new ResourceInfo(typeName, configMap);
		}
		catch (Exception e)
		{
			throw new FrontendException("Error reading configuration", e);
		}

	}

	protected Object makeValue(Element child) throws FrontendException
	{
		String text = child.getText();
		String type = child.getAttributeValue("type").toLowerCase();
		if (type == null || type.length() == 0)
			logger.warn("No type information attached for: " + child.getName() + ". Defaulting to string.");
		else if (type.equals("integer"))
			return Long.valueOf(text);
		else if (type.equals("boolean"))
			return Boolean.valueOf(text);
		else if (type.equals("float"))
			return Double.valueOf(text);
		else if (type.equals("reference"))
			return getRemoteResource(text);
		else if (!type.equals("string"))
			throw new FrontendException("Unknown type for: " + child.getName() + ": " + type);
		return text;
	}

	@Override
	public String writeConfiguration(ResourceInfo r) throws FrontendException
	{
		//Yes, its ugly. but arguably the quickest way
		String answer = "<" + r.getTypeName() + ">\n\t<configuration>";
		for (Map.Entry<String, Object> e : r.getConfiguration().entrySet())
		{
			answer += "\t\t<" + e.getKey() + " type=\"" + makeTypeinfo(e.getValue()) + "\">";
			if (e.getValue() instanceof Resource)
				answer += ((Resource)e.getValue()).getIdentifier();
			else
				answer += e.getValue();
			answer += "</" + e.getKey() + ">\n";
		}
		
		answer += "\t<configuration>\n<" + r.getTypeName() + ">";
		return answer;
	}
	
	public String makeTypeinfo(Object obj)
	{
		if (obj instanceof Integer)
			return "integer";
		else if (obj instanceof Float || obj instanceof Double)
			return "float";
		else if (obj instanceof Boolean)
			return "boolean";
		else if (obj instanceof Resource)
			return "reference";
		
		return "string";
	}

}
