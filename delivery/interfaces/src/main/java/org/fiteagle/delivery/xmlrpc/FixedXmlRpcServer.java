package org.fiteagle.delivery.xmlrpc;

import java.io.InputStream;
import java.io.Writer;
import java.security.cert.X509Certificate;

import redstone.xmlrpc.XmlRpcException;
import redstone.xmlrpc.XmlRpcServer;

public class FixedXmlRpcServer extends XmlRpcServer {

	
	
	public FixedXmlRpcServer(){
		
	}
	
	
	public void execute(InputStream paramInputStream, Writer paramWriter, String path, X509Certificate x509Certificate) throws XmlRpcException {
		FixedXmlRpcDispatcher dispatcher = new FixedXmlRpcDispatcher(this, path);
		dispatcher.dispatch(paramInputStream, paramWriter, x509Certificate);
		
	}

  
  
	
	
}
