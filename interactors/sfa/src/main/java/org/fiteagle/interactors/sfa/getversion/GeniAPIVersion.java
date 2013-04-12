package org.fiteagle.interactors.sfa.getversion;

public class GeniAPIVersion {
	private String url;
	private int version;
	
	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public GeniAPIVersion(int version, String url) {
		this.version = version;
		this.url = url;
	}
}
