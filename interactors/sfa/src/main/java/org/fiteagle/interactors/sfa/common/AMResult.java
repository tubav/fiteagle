package org.fiteagle.interactors.sfa.common;

public  class AMResult {
	
	AMCode am_code;
	String output;
	Object am_value;
	
	public void setCode(AMCode code){
		this.am_code = code;
	}

	public AMCode getCode(){
		return this.am_code;
	}

	public String getOutput(){
		return this.output;
	}

	public void setOutput(String output){
		this.output = output;
	}

	public Object getValue(){
		return this.am_value;
	}

	public void setValue(Object value){
		this.am_value = value;
	}
	
	
	//public abstract boolean checkValid();

}
