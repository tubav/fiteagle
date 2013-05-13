package org.fiteagle.interactors.sfa.status;

import org.fiteagle.interactors.sfa.common.SFAOptionsService;

public class StatusOptionsService extends SFAOptionsService {
  
  private StatusOptions options;

  public StatusOptionsService(StatusOptions options) {
    this.options=options;
  }

  @Override
  public boolean areOptionsValid() {
    return true;
  }
  
  public void checkOptions() {
    //TODO: define needed options for the framework and check them.
  }
  
}
