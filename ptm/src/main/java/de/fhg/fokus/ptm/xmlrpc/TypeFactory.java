package de.fhg.fokus.ptm.xmlrpc;

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

import org.apache.ws.commons.util.NamespaceContextImpl;
import org.apache.xmlrpc.common.TypeFactoryImpl;
import org.apache.xmlrpc.common.XmlRpcController;
import org.apache.xmlrpc.common.XmlRpcStreamConfig;
import org.apache.xmlrpc.parser.NullParser;
import org.apache.xmlrpc.parser.TypeParser;
import org.apache.xmlrpc.serializer.TypeSerializer;
import org.xml.sax.SAXException;

public class TypeFactory extends TypeFactoryImpl
{

	public TypeFactory(XmlRpcController pController)
	{
		super(pController);
	}

	@Override
	public TypeParser getParser(XmlRpcStreamConfig pConfig,
			NamespaceContextImpl pContext, String pURI, String pLocalName)
	{
		if ("".equals(pURI) && "nil".equals(pLocalName))
			return new NullParser();
		return super.getParser(pConfig, pContext, pURI, pLocalName);
	}
	
	@Override
	public TypeSerializer getSerializer(XmlRpcStreamConfig pConfig,
			Object pObject) throws SAXException {
		if (pObject == null)
			return new NullSerializer();
		return super.getSerializer(pConfig, pObject);
	}
}