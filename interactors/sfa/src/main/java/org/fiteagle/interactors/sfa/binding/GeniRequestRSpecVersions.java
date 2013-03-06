package org.fiteagle.interactors.sfa.binding;

public class GeniRequestRSpecVersions {
	private String type = "GENI";
	private String version = "2";
	private String schema = "http://www.geni.net/resources/rspec/2/request.xsd";
	private String namespace = "http://www.geni.net/resources/rspec/2";
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
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
}
