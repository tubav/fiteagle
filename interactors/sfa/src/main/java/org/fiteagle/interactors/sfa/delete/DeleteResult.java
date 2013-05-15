package org.fiteagle.interactors.sfa.delete;

import java.util.ArrayList;

import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.describe.DescribeValue;

public class DeleteResult extends AMResult{
  
private ArrayList<GeniSlivers> value = new ArrayList<GeniSlivers>();
  
  public ArrayList<GeniSlivers> getValue() {
    return this.value;
  }
  

  public void setValue(ArrayList<GeniSlivers> value) {
    this.value = value;
  }
  
  public void addSliver(GeniSlivers sliver){
    this.value.add(sliver);
  }
  
}
