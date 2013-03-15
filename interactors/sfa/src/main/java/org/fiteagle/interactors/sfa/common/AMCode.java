package org.fiteagle.interactors.sfa.common;

public class AMCode {

	public String getAm_type() {
		return am_type;
	}

	public void setAm_type(String am_type) {
		this.am_type = am_type;
	}

	public int getAm_code() {
		return am_code;
	}

	public void setAm_code(int am_code) {
		this.am_code = am_code;
	}

	private GENI_CodeEnum geni_code;
	private String am_type= null;
	private int am_code  = -1;
	
	
	public GENI_CodeEnum retrieveGeni_code() {
		return geni_code;
	}
	
	public int getGeni_code(){
		return geni_code.getValue();
	}
	
	public void setGeni_code(GENI_CodeEnum geni_code) {
		this.geni_code = geni_code;
	}
	
	
}
