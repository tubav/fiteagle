package org.fiteagle.delivery.xmlrpc.util;

import java.lang.reflect.Method;
import java.util.List;

import org.fiteagle.interactors.sfa.ISFA;

public class SFARegistryHandler extends SFAHandler {

	
	public SFARegistryHandler(ISFA interactor){
		this.interactor = interactor;
	}
	
	
	@Override
	public Object invoke(String method, List arguments) throws Throwable {
		Method calledMethod = getMethod(method);
	//	Object result = 
		return null;
	}


	@Override
	protected void xmlStructToObject(Object object, Object object2) {
		// TODO Auto-generated method stub
		
	}

}
