package org.fiteagle.interactors.sfa.common;

import java.util.ArrayList;

public class GeniUserList {
  
  public GeniUserList() {
    this.geniUserList = new ArrayList<GeniUser>();
  }
  
  private ArrayList<GeniUser> geniUserList;

  public ArrayList<GeniUser> getGeniUserList() {
    return geniUserList;
  }

  public void setGeniUserList(ArrayList<GeniUser> geniUserList) {
    this.geniUserList = geniUserList;
  }
  
  public void addUser(GeniUser user){
    this.geniUserList.add(user);
  }
  
}
