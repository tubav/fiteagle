package org.fiteagle.interactors.sfa.allocate;

import org.fiteagle.interactors.sfa.common.AMResult;

public class AllocateResult extends AMResult{
  
  private AllocateValue value;
  

  public AllocateValue getValue() {
    return value;
  }

  public void setValue(AllocateValue value) {
    this.value = value;
  }
  
}
