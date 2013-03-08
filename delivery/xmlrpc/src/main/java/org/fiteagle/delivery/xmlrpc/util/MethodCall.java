package org.fiteagle.delivery.xmlrpc.util;

import java.util.List;



public class MethodCall {

	String methodName;
	List params;
	
	
public MethodCall() {
	// TODO Auto-generated constructor stub
}
public String getMethodName() {
	return Character.toLowerCase(methodName.charAt(0))
			+ (methodName.length() > 1 ? methodName.substring(1) : "");
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
