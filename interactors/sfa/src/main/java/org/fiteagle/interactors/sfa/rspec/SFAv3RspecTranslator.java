package org.fiteagle.interactors.sfa.rspec;

public class SFAv3RspecTranslator {

	private final String type = "GENI";
	private final String version = "3";
	private final String adRspecNamespace = "http://www.geni.net/resources/rspec/3" ;
	private final String adRspecSchema = "http://www.geni.net/resources/rspec/3/ad.xsd";
	private final String[] adRspecExtensions = new String[]{""};
	
	private final String requestRspecNamespace ="http://www.geni.net/resources/rspec/3";
	private final String requestRspecSchema = "http://www.geni.net/resources/rspec/3/request.xsd";
	private final String[] requestRspecExtensions = new String[]{""};
	public String getAdRspecNamespace() {
		return adRspecNamespace;
	}
	public String getAdRspecSchema() {
		return adRspecSchema;
	}
	public String[] getAdRspecExtensions() {
		return adRspecExtensions;
	}
	public String getRequestRspecNamespace() {
		return requestRspecNamespace;
	}
	public String getRequestRspecSchema() {
		return requestRspecSchema;
	}
	public String[] getRequestRspecExtensions() {
		return requestRspecExtensions;
	}
	public String getType() {
		return type;
	}
	public String getVersion() {
		return version;
	}
	
	
	
	
}
