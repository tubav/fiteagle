package org.fiteagle.interactors.sfa.performoperationalaction;

import org.fiteagle.interactors.sfa.common.SFAOptionsService;

public class PerformOperationalActionOptionsService extends SFAOptionsService {

	private PerformOperationalActionOptions options;

	public PerformOperationalActionOptionsService(
			PerformOperationalActionOptions options) {
		this.options = options;
	}

	@Override
	public boolean areOptionsValid() {
		return true;
	}

	public void checkOptions() {
		// TODO: define needed options for the framework such as "geni_best_effford" and check them.
	}

}
