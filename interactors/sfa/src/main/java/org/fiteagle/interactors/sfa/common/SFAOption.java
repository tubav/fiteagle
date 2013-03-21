package org.fiteagle.interactors.sfa.common;

public abstract class SFAOption {

	protected  String name;
	protected boolean isOptional;
	protected Object value;
	
	public  String getName(){
		return this.name;
	}

	public boolean isOptional(){
		return this.isOptional;
	}
	public Object getValue(){
		return this.value;
	}
	
}
