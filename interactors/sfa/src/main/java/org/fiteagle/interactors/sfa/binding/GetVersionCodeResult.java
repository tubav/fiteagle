package org.fiteagle.interactors.sfa.binding;

public class GetVersionCodeResult {
	private String geni_code = "0";
	private String am_type = "";
	private String am_code = "";
		    		   
	public String getAm_type() {
		return am_type;
	}

	public void setAm_type(String am_type) {
		this.am_type = am_type;
	}

	public String getAm_code() {
		return am_code;
	}

	public void setAm_code(String am_code) {
		this.am_code = am_code;
	}

	public String getGeni_code() {
		return geni_code;
	}

	public void setGeni_code(String geni_code) {
		this.geni_code = geni_code;
	}
}
