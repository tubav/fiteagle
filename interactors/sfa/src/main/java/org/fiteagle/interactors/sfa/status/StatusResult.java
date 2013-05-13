package org.fiteagle.interactors.sfa.status;

import org.fiteagle.interactors.sfa.common.AMResult;

public class StatusResult extends AMResult{
  
  private StatusValue value;

  public StatusValue getValue() {
    return value;
  }

  public void setValue(StatusValue value) {
    this.value = value;
  }
  
  
  
}
