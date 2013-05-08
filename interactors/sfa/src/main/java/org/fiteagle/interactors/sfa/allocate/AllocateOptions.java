package org.fiteagle.interactors.sfa.allocate;

import java.util.ArrayList;
import java.util.List;

import org.fiteagle.interactors.sfa.common.GeniEndTimeoption;
import org.fiteagle.interactors.sfa.common.SFAOption;
import org.fiteagle.interactors.sfa.common.SFAOptionsStruct;

public class AllocateOptions extends SFAOptionsStruct{
  
//  geni_end_time
  private GeniEndTimeoption geni_end_time;
  
  @Override
  public List<? extends SFAOption> getOptions() {
    List<SFAOption> returnList = new ArrayList<>();
    returnList.add(getGeni_end_time());
    return returnList;
  }

  public GeniEndTimeoption getGeni_end_time() {
    return geni_end_time;
  }

  public void setGeni_end_time(GeniEndTimeoption geni_end_time) {
    this.geni_end_time = geni_end_time;
  }
  
}
