package org.fiteagle.interactors.sfa.provision;

import org.fiteagle.interactors.sfa.common.AMResult;

public class ProvisionResult extends AMResult{
  
  private ProvisionValue value;
  

  public ProvisionValue getValue() {
    return value;
  }

  public void setValue(ProvisionValue value) {
    this.value = value;
  }
  
}
