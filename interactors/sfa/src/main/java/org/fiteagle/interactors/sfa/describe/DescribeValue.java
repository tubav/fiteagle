package org.fiteagle.interactors.sfa.describe;

import java.util.ArrayList;
import java.util.List;

import org.fiteagle.interactors.sfa.common.AMValue;

public class DescribeValue extends AMValue {

	private String geni_rspec;
	private String geni_urn;
	private List<GeniSlivers> geni_slivers = new ArrayList<>();

	public String getGeni_rspec() {
		return geni_rspec;
	}

	public void setGeni_rspec(String geni_rspec) {
		this.geni_rspec = geni_rspec;
	}

	public String getGeni_urn() {
		return geni_urn;
	}

	public void setGeni_urn(String geni_urn) {
		this.geni_urn = geni_urn;
	}

	public List<GeniSlivers> getGeni_slivers() {
		return geni_slivers;
	}

	public void setGeni_slivers(List<GeniSlivers> geni_slivers) {
		this.geni_slivers = geni_slivers;
	};

}
