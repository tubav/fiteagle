package org.fiteagle.interactors.sfa.getversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fiteagle.interactors.sfa.common.AMValue;
import org.fiteagle.interactors.sfa.common.GeniCredentialType;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class GetVersionValue extends AMValue {

//	private Map<String, GeniAPIVersion> geni_api_versions = new HashMap<>();
	private Map<Integer, String> geni_api_versions = new HashMap<Integer, String>();
	private List<GeniRequestRSpecVersions> geni_request_rspec_versions = new ArrayList<>();
	private List<GeniAdRSpecVersions> geni_ad_rspec_versions = new ArrayList<>();
	// add in GAPI_AM_API_V3
	private List<GeniCredentialType> geni_credential_types = new ArrayList<>();
	private int geni_single_allocation;
	private String geni_allocate;

	private int geni_api;
	private Map<Object, Object> genericAttributes;
	
	//F4F extensions
	private String f4f_describe_testbed;
	private String f4f_testbed_homepage;
	private String f4f_testbed_picture;
	private List<F4FEndorsedTools> f4f_endorsed_tools;
	
	public GetVersionValue() {

	}

	public void addGenericAttribute(String name, Object value) {
		if (genericAttributes == null) {
			genericAttributes = new HashMap<>();
		}
		
		genericAttributes.put(name, value);
	}


	@JsonAnyGetter
	public Map<Object, Object> any(){
		return genericAttributes;
	}
	public int getGeni_single_allocation() {
		return geni_single_allocation;
	}

	public void setGeni_single_allocation(int geni_single_allocation) {
		this.geni_single_allocation = geni_single_allocation;
	}

	public String getGeni_allocate() {
		return geni_allocate;
	}

	public void setGeni_allocate(String geni_allocate) {
		this.geni_allocate = geni_allocate;
	}

	public List<GeniRequestRSpecVersions> getGeni_request_rspec_versions() {
		return this.geni_request_rspec_versions;
	}

	public void setGeni_request_rspec_versions(
			final List<GeniRequestRSpecVersions> geni_request_rspec_versions) {
		this.geni_request_rspec_versions = geni_request_rspec_versions;
	}

	public List<GeniAdRSpecVersions> getGeni_ad_rspec_versions() {
		return this.geni_ad_rspec_versions;
	}

	public void setGeni_ad_rspec_versions(
			final List<GeniAdRSpecVersions> geni_ad_rspec_versions) {
		this.geni_ad_rspec_versions = geni_ad_rspec_versions;
	}

	public int getGeni_api() {
		return this.geni_api;
	}

	public void setGeni_api(final int geni_api) {
		this.geni_api = geni_api;
	}

//	public Map<String, GeniAPIVersion> getGeni_api_versions() {
//		return this.geni_api_versions;
//	}
//
//	public void setGeni_api_versions(
//			final Map<String, GeniAPIVersion> geni_api_versions) {
//		this.geni_api_versions = geni_api_versions;
//	}

	public List<GeniCredentialType> getGeni_credential_types() {
		return geni_credential_types;
	}

	public void setGeni_credential_types(
			List<GeniCredentialType> geni_credential_types) {
		this.geni_credential_types = geni_credential_types;
	}

  public Map<Integer, String> getGeni_api_versions() {
    return geni_api_versions;
  }

  public void setGeni_api_versions(Map<Integer, String> geniApiVersions) {
    this.geni_api_versions = geniApiVersions;
  }

public String getF4f_describe_testbed() {
	return f4f_describe_testbed;
}

public void setF4f_describe_testbed(String f4f_describe_testbed) {
	this.f4f_describe_testbed = f4f_describe_testbed;
}

public String getF4f_testbed_homepage() {
	return f4f_testbed_homepage;
}

public void setF4f_testbed_homepage(String f4f_testbed_homepage) {
	this.f4f_testbed_homepage = f4f_testbed_homepage;
}

public String getF4f_testbed_picture() {
	return f4f_testbed_picture;
}

public void setF4f_testbed_picture(String f4f_testbed_picture) {
	this.f4f_testbed_picture = f4f_testbed_picture;
}

public List<F4FEndorsedTools> getF4f_endorsed_tools() {
	return f4f_endorsed_tools;
}

public void setF4f_endorsed_tools(List<F4FEndorsedTools> f4f_endorsed_tools) {
	this.f4f_endorsed_tools = f4f_endorsed_tools;
}

}
