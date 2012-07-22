package de.fhg.fokus.ptm.xmlrpc.server;

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

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.server.Manager;
import de.fhg.fokus.ptm.xmlrpc.TypeFactory;

public class XMLRPCServer extends WebServer {
	private XMLRPCManager manager;
	
	public XMLRPCServer(InetAddress pAddr, int pPort) throws PTMException
	{
		this(null, pAddr, pPort);
	}
	
	public XMLRPCServer(URL registryUrl, InetAddress pAddr, int port) throws PTMException 
	{
		super(port, pAddr);
		try {
			URL myUrl = new URL("http://" + pAddr.getHostAddress() + ":" + Integer.toString(port));
			if (registryUrl == null)
				manager = new XMLRPCManager(myUrl);
			else
				manager = new XMLRPCManager(myUrl, registryUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new PTMException("Unexpected error", e);
		}
		XmlRpcServerConfigImpl config = new XmlRpcServerConfigImpl();
		config.setEnabledForExceptions(true);
		config.setEnabledForExtensions(true);
		
		getXmlRpcServer().setConfig(config);
		getXmlRpcServer().setTypeFactory(new TypeFactory(getXmlRpcServer()));
		getXmlRpcServer().setHandlerMapping(manager.getHandlerMapping());
	}
	
	public Manager getManager()
	{
		return manager;
	}
}
