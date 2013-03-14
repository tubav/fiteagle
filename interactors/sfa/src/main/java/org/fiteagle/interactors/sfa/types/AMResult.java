package org.fiteagle.interactors.sfa.types;

public abstract class AMResult {
	
	AMCode am_code;
	String output;
	AMValue am_value;
	
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

	public AMValue getValue(){
		return this.am_value;
	}

	void setValue(AMValue value){
		this.am_value = value;
	}
	
	
	public abstract boolean checkValid();

}
