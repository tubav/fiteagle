package org.fiteagle.interactors.sfa.listresources;

import java.util.ArrayList;
import java.util.List;

import org.fiteagle.interactors.sfa.common.GeniAvailableOption;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.common.SFAOption;
import org.fiteagle.interactors.sfa.common.SFAOptionsStruct;

public class ListResourceOptions extends SFAOptionsStruct{

	private GeniAvailableOption geni_available;
	
	public boolean isGeni_available() {
		return geni_available.getValue();
	}

	public void setGeni_available(GeniAvailableOption geni_available) {
		this.geni_available = geni_available;
	}

	public boolean isGeni_compressed() {
		return geni_compressed.getValue();
	}

	public void setGeni_compressed(GeniCompressedOption geni_compressed) {
		this.geni_compressed = geni_compressed;
	}

	public Geni_RSpec_Version getGeni_respec_version() {
		return geni_respec_version;
	}

	public void setGeni_respec_version(Geni_RSpec_Version geni_respec_version) {
		this.geni_respec_version = geni_respec_version;
	}

	
	private GeniCompressedOption geni_compressed;
	
	private Geni_RSpec_Version geni_respec_version;

	@Override
	public List<? extends SFAOption> getOptions() {
		List<SFAOption> returnList = new ArrayList<>();
		returnList.add(geni_available);
		returnList.add(geni_compressed);
		returnList.add(geni_respec_version);
		return returnList;
	}
	
	
}
