package org.fiteagle.interactors.sfa.binding;

public class GetVersionResult {
	private String geni_api = "2";
	private GetVersionCodeResult code = new GetVersionCodeResult();
	private GetVersionValueResult value = new GetVersionValueResult();
	private String output = "";

	public String getOutput() {
		return this.output;
	}

	public void setOutput(final String output) {
		this.output = output;
	}

	public GetVersionValueResult getValue() {
		return this.value;
	}

	public void setValue(final GetVersionValueResult value) {
		this.value = value;
	}

	public GetVersionCodeResult getCode() {
		return this.code;
	}

	public void setCode(final GetVersionCodeResult code) {
		this.code = code;
	}

	public String getGeni_api() {
		return this.geni_api;
	}

	public void setGeni_api(final String geni_api) {
		this.geni_api = geni_api;
	}

}
