package org.fiteagle.delivery.xmlrpc;

import java.io.InputStream;
import java.io.Writer;

import redstone.xmlrpc.XmlRpcException;
import redstone.xmlrpc.XmlRpcServer;

public class FixedXmlRpcServer extends XmlRpcServer {

	
	
	public FixedXmlRpcServer(){
		
	}
	
	
	public void execute(InputStream paramInputStream, Writer paramWriter, String path) throws XmlRpcException {
		FixedXmlRpcDispatcher dispatcher = new FixedXmlRpcDispatcher(this, path);
		dispatcher.dispatch(paramInputStream, paramWriter);
		
	}

  
  
	
	
}
