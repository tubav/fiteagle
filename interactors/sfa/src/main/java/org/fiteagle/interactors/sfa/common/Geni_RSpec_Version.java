package org.fiteagle.interactors.sfa.common;

public class Geni_RSpec_Version extends SFAOption {

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	private String type;
	private String version;
	
	public Geni_RSpec_Version(){
		this.name = "geni_rspec_version";
		this.isOptional = false;
	}
	@Override
	public Geni_RSpec_Version getValue(){
		return this;
	}
	
	
	
	
}
