package org.fiteagle.interactors.sfa.renew;

import java.util.List;

import org.fiteagle.interactors.sfa.common.GeniBestEffortOption;
import org.fiteagle.interactors.sfa.common.SFAOption;
import org.fiteagle.interactors.sfa.common.SFAOptionsStruct;

public class RenewOptions extends SFAOptionsStruct {
	  
	  private GeniBestEffortOption geni_best_effort;

	  @Override
	  public List<? extends SFAOption> getOptions() {
	    return null;
	  }

	  public GeniBestEffortOption getGeni_best_effort() {
	    return geni_best_effort;
	  }

	  public void setGeni_best_effort(GeniBestEffortOption geni_best_effort) {
	    this.geni_best_effort = geni_best_effort;
	  }
	  
	}
