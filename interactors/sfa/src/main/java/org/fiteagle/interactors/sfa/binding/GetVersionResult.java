package org.fiteagle.interactors.sfa.binding;

public class GetVersionResult {
	private String geni_api = "2";
	private GetVersionCodeResult code = new GetVersionCodeResult(); 
	private GetVersionValueResult value = new GetVersionValueResult(); 
	private String output = "";
	
	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public GetVersionValueResult getValue() {
		return value;
	}

	public void setValue(GetVersionValueResult value) {
		this.value = value;
	}

	public GetVersionCodeResult getCode() {
		return code;
	}

	public void setCode(GetVersionCodeResult code) {
		this.code = code;
	}

	public String getGeni_api() {
		return geni_api;
	}

	public void setGeni_api(String geni_api) {
		this.geni_api = geni_api;
	}

}
