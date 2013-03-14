package org.fiteagle.delivery.xmlrpc;

import java.io.InputStream;
import java.io.Writer;

import redstone.xmlrpc.XmlRpcDispatcher;
import redstone.xmlrpc.XmlRpcException;
import redstone.xmlrpc.XmlRpcServer;

public class FixedXmlRpcServer extends XmlRpcServer {

	XmlRpcDispatcher dispatcher = null;
	
	public FixedXmlRpcServer(){
		this.dispatcher = new FixedXmlRpcDispatcher(this, "(unknown)");
	}
	
	@Override
	public void execute(InputStream paramInputStream, Writer paramWriter) throws XmlRpcException {
		 dispatcher.dispatch(paramInputStream, paramWriter);
	}
	
	
}
