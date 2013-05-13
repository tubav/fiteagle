package org.fiteagle.interactors.sfa.common;

public class GeniBestEffortOption extends SFAOption {
  @Override
  public Boolean getValue() {
    return (Boolean) this.value;
  }
  
  public GeniBestEffortOption(boolean value) {
    this.value = new Boolean(value);
    this.name = "geni_best_effort";
    this.isOptional = true;
  }
  
}
