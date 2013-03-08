package org.fiteagle.interactors.sfa.binding;

public class GetVersionCodeResult {
	private int geni_code = 0;
	private String am_type = "";
	private String am_code = "";

	public String getAm_type() {
		return this.am_type;
	}

	public void setAm_type(final String am_type) {
		this.am_type = am_type;
	}

	public String getAm_code() {
		return this.am_code;
	}

	public void setAm_code(final String am_code) {
		this.am_code = am_code;
	}

	public int getGeni_code() {
		return this.geni_code;
	}

	public void setGeni_code(final int geni_code) {
		this.geni_code = geni_code;
	}
}
