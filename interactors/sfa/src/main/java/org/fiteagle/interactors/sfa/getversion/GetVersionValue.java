package org.fiteagle.interactors.sfa.getversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fiteagle.interactors.sfa.common.AMValue;
import org.fiteagle.interactors.sfa.common.GeniCredentialType;

public class GetVersionValue extends AMValue {
	public boolean isGeni_single_allocation() {
		return geni_single_allocation;
	}

	public void setGeni_single_allocation(boolean geni_single_allocation) {
		this.geni_single_allocation = geni_single_allocation;
	}

	public String getGeni_allocate() {
		return geni_allocate;
	}

	public void setGeni_allocate(String geni_allocate) {
		this.geni_allocate = geni_allocate;
	}

	private int geni_api;
	private Map<String, GeniAPIVersion> geni_api_versions = new HashMap<>();
	private List<GeniRequestRSpecVersions> geni_request_rspec_versions = new ArrayList<>();
	private List<GeniAdRSpecVersions> geni_ad_rspec_versions = new ArrayList<>();
	// add in GAPI_AM_API_V3
	private List<GeniCredentialType> geni_credential_types = new ArrayList<>();
	private boolean geni_single_allocation;
	private String geni_allocate;

	public GetVersionValue() {
		
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

	public Map<String, GeniAPIVersion> getGeni_api_versions() {
		return this.geni_api_versions;
	}

	public void setGeni_api_versions(
			final Map<String, GeniAPIVersion> geni_api_versions) {
		this.geni_api_versions = geni_api_versions;
	}

	public List<GeniCredentialType> getGeni_credential_types() {
		return geni_credential_types;
	}

	public void setGeni_credential_types(
			List<GeniCredentialType> geni_credential_types) {
		this.geni_credential_types = geni_credential_types;
	}

}
