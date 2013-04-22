package org.fiteagle.delivery.xmlrpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redstone.xmlrpc.XmlRpcInvocation;
import redstone.xmlrpc.XmlRpcInvocationInterceptor;

public class XmlRpcController implements XmlRpcInvocationInterceptor {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	public Object after(XmlRpcInvocation arg0, Object arg1) {
		return arg1;
	}

	@Override
	public boolean before(XmlRpcInvocation invocation) {
		
		String newmethodName =  Character.toLowerCase(invocation.getMethodName().charAt(0))
				+ (invocation.getMethodName().length() > 1 ? invocation.getMethodName().substring(1) : "");
		
		invocation.setMethodName(newmethodName);
		return true;
		
	}

	@Override
	public void onException(XmlRpcInvocation arg0, Throwable arg1) {
		log.error("Error on XmlRPCInvocation "+ arg0.getMethodName(), arg1);
	}

}
