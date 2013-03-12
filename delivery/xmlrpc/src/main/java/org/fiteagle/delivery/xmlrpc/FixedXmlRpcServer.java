package org.fiteagle.delivery.xmlrpc;

import java.io.InputStream;
import java.io.Writer;

import redstone.xmlrpc.XmlRpcDispatcher;
import redstone.xmlrpc.XmlRpcException;
import redstone.xmlrpc.XmlRpcServer;

public class FixedXmlRpcServer extends XmlRpcServer {

	@Override
	public void execute(InputStream paramInputStream, Writer paramWriter) throws XmlRpcException {
		 XmlRpcDispatcher localXmlRpcDispatcher = new FixedXmlRpcDispatcher(this, "(unknown)");
		 localXmlRpcDispatcher.dispatch(paramInputStream, paramWriter);
	}
	
	
}
