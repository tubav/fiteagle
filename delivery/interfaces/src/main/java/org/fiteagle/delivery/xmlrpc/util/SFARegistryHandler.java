package org.fiteagle.delivery.xmlrpc.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.fiteagle.interactors.sfa.ISFA;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SFARegistryHandler extends SFAHandler {
  
Logger log =  LoggerFactory.getLogger(this.getClass());

	public SFARegistryHandler(ISFA interactor){
		this.interactor = interactor;
	}
	
	
	@Override
	public Object invoke(String methodName, List arguments) throws Throwable {
		
		ArrayList<String> argumentsList = new ArrayList<String>();
	
		
		Method calledMethod = getMethod(methodName);
		Object response = calledMethod.invoke(this.interactor, arguments.toArray());
		return response;
	}


	@Override
	public Object xmlStructToObject(Object object, Object object2) {
		return object2;
		
	}

}
