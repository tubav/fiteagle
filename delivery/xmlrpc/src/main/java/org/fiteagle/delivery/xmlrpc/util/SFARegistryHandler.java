package org.fiteagle.delivery.xmlrpc.util;

import java.lang.reflect.Method;
import java.util.List;

import org.fiteagle.interactors.sfa.ISFA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SFARegistryHandler extends SFAHandler {
  
Logger log =  LoggerFactory.getLogger(this.getClass());

	public SFARegistryHandler(ISFA interactor){
		this.interactor = interactor;
	}
	
	
	@Override
	public Object invoke(String method, List arguments) throws Throwable {
		Method calledMethod = getMethod(method);
	//	Object result = 
		log.info("nearly done");
		String cert = "this is a cert";
		return introspect(cert);
	}


	@Override
	protected void xmlStructToObject(Object object, Object object2) {
		// TODO Auto-generated method stub
		
	}

}
