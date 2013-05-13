package org.fiteagle.interactors.sfa.provision;

import java.util.ArrayList;
import java.util.List;

import org.fiteagle.interactors.sfa.common.GeniSlivers;

public class ProvisionValue {
  
  private Object geni_rspec;
  private List<GeniSlivers> geni_slivers = new ArrayList<>();

  public Object getGeni_rspec() {
    return geni_rspec;
  }

  public void setGeni_rspec(Object geni_rspec) {
    this.geni_rspec = geni_rspec;
  }

  public List<GeniSlivers> getGeni_slivers() {
    return geni_slivers;
  }

  public void setGeni_slivers(List<GeniSlivers> geni_slivers) {
    this.geni_slivers = geni_slivers;
  };
  
}
