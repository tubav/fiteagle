package org.fiteagle.api.xmlrpc.util;

import java.util.List;



public class MethodCall {

	String methodName;
	List params;
	
	
public MethodCall() {
	// TODO Auto-generated constructor stub
}
public String getMethodName() {
	
	return methodName;
}
public void setMethodName(String methodName) {
	this.methodName = methodName;
}

public List getParams() {
	return params;
}
public void setParams(List params) {
	this.params = params;
}
}
