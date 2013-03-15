package org.fiteagle.interactors.sfa.listresources;

import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;

public class ListResourceOptions {

	public boolean isGeni_available() {
		return geni_available;
	}

	public void setGeni_available(boolean geni_available) {
		this.geni_available = geni_available;
	}

	public boolean isGeni_compressed() {
		return geni_compressed;
	}

	public void setGeni_compressed(boolean geni_compressed) {
		this.geni_compressed = geni_compressed;
	}

	public Geni_RSpec_Version getGeni_respec_version() {
		return geni_respec_version;
	}

	public void setGeni_respec_version(Geni_RSpec_Version geni_respec_version) {
		this.geni_respec_version = geni_respec_version;
	}

	private boolean geni_available;
	private boolean geni_compressed;
	
	private Geni_RSpec_Version geni_respec_version;
	
	
}
