package org.fiteagle.interactors.sfa.getversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fiteagle.interactors.sfa.types.AMValue;

public class GetVersionValue extends AMValue {
	private int geni_api;
	private Map<String, GeniAPIVersion > geni_api_versions = new HashMap<>();
	private List<GeniRequestRSpecVersions> geni_request_rspec_versions = new ArrayList<>();
	private List<GeniAdRSpecVersions> geni_ad_rspec_versions = new ArrayList<>();

	public GetVersionValue() {
		this.geni_request_rspec_versions.add(new GeniRequestRSpecVersions());
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

	public Map<String,GeniAPIVersion> getGeni_api_versions() {
		return this.geni_api_versions;
	}

	public void setGeni_Api_versions(final Map<String,GeniAPIVersion> geni_api_versions) {
		this.geni_api_versions = geni_api_versions;
	}

}
