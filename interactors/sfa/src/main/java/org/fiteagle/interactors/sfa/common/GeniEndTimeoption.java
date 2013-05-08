package org.fiteagle.interactors.sfa.common;

public class GeniEndTimeoption extends SFAOption{
  
  public GeniEndTimeoption(String value) {
    this.name = "geni_end_time";
    this.value = value;
    this.isOptional = true;
  }

  @Override
  public String getValue() {
    return (String) this.value;
  }
  
  
  
  
}
