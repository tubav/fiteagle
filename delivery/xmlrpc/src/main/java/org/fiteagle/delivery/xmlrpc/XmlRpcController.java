package org.fiteagle.delivery.xmlrpc;

import redstone.xmlrpc.XmlRpcInvocation;
import redstone.xmlrpc.XmlRpcInvocationInterceptor;

public class XmlRpcController implements XmlRpcInvocationInterceptor {

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
		// TODO Auto-generated method stub

	}

}
