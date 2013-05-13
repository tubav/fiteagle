package org.fiteagle.interactors.sfa.status;

import java.util.ArrayList;
import java.util.List;

import org.fiteagle.interactors.sfa.common.AMValue;
import org.fiteagle.interactors.sfa.common.GeniSlivers;

public class StatusValue extends AMValue{
  
  private String geni_urn;
  private List<GeniSlivers> geni_slivers = new ArrayList<>();
  
  
  public String getGeni_urn() {
    return geni_urn;
  }
  public void setGeni_urn(String geni_urn) {
    this.geni_urn = geni_urn;
  }
  public List<GeniSlivers> getGeni_slivers() {
    return geni_slivers;
  }
  public void setGeni_slivers(List<GeniSlivers> geni_slivers) {
    this.geni_slivers = geni_slivers;
  }
  
}
