package org.fiteagle.interactors.sfa.getversion;

public class GeniAdRSpecVersions {
	private String type = "GENI";
	private String version = "2";
	private String schema = "http://www.geni.net/resources/rspec/2/ad.xsd";
	private String namespace = "http://www.geni.net/resources/rspec/2";

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public String getSchema() {
		return this.schema;
	}

	public void setSchema(final String schema) {
		this.schema = schema;
	}

	public String getNamespace() {
		return this.namespace;
	}

	public void setNamespace(final String namespace) {
		this.namespace = namespace;
	}

}
