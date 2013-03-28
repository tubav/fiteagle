package org.fiteagle.interactors.sfa.listresources;

import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.common.SFAOptionsService;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class ListResourceOptionsService extends SFAOptionsService {

	private ListResourceOptions options;

	public ListResourceOptionsService(ListResourceOptions options) {
		this.options = options;
	}

	@Override
	public boolean optionsAreValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean optionsComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkRSpecVersion() {
		return this.options.getGeni_respec_version().getVersion().compareTo(new SFAv3RspecTranslator().getVersion())==0;
	}

	
	
}
