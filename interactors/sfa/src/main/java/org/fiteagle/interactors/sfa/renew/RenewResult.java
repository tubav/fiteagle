package org.fiteagle.interactors.sfa.renew;

import java.util.ArrayList;

import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.GeniSlivers;

public class RenewResult extends AMResult {
	
	
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

//	private RenewValue value;
//
//	public RenewValue getValue() {
//		return value;
//	}
//
//	public void setValue(RenewValue value) {
//		this.value = value;
//	}

}
