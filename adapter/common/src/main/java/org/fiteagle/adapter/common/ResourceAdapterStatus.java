package org.fiteagle.adapter.common;

public enum ResourceAdapterStatus {

	Available("available"),Reserved("reserved"),InUse("inUse");
	
	private String name;

	private ResourceAdapterStatus(String name){
		this.name = name;
	}
	
	@Override 
	public String toString(){
		return this.name;
	}
}
