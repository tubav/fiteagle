package org.fiteagle.interactors.sfa.common;

public class Credentials {

	public String getGeni_type() {
		return geni_type;
	}
	public void setGeni_type(String geni_type) {
		this.geni_type = geni_type;
	}
	public String getGeni_version() {
		return geni_version;
	}
	public void setGeni_version(String geni_version) {
		this.geni_version = geni_version;
	}
	public String getGeni_value() {
		return geni_value;
	}
	public void setGeni_value(String geni_value) {
		this.geni_value = geni_value;
	}
	
	//eg SFA etc
	private String geni_type;
	//
	private String geni_version;
	//the certificate(s) as string
	private String geni_value;
	
	
	
	
	
}
