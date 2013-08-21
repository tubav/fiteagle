package org.fiteagle.interactors.sfa.performoperationalaction;

import java.util.ArrayList;
import java.util.List;

import org.fiteagle.interactors.sfa.common.GeniBestEffortOption;
import org.fiteagle.interactors.sfa.common.SFAOption;
import org.fiteagle.interactors.sfa.common.SFAOptionsStruct;

public class PerformOperationalActionOptions extends SFAOptionsStruct {

	private GeniBestEffortOption geni_best_effort;
	
	@Override
	public List<? extends SFAOption> getOptions() {
		List<SFAOption> returnList = new ArrayList<>();
		return returnList;
	}

	public GeniBestEffortOption getGeni_best_effort() {
		return geni_best_effort;
	}

	public void setGeni_best_effort(GeniBestEffortOption geni_best_effort) {
		this.geni_best_effort = geni_best_effort;
	}

}
