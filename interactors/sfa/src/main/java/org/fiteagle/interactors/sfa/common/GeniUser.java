package org.fiteagle.interactors.sfa.common;

import java.util.ArrayList;

public class GeniUser {
  
  private String urn;
  private ArrayList<String> keys;
  
  public GeniUser() {
    this.setUrn("");
    this.setKeys(new ArrayList<String>());
  }

  public String getUrn() {
    return urn;
  }

  public void setUrn(String urn) {
    this.urn = urn;
  }

  public ArrayList<String> getKeys() {
    return keys;
  }

  public void setKeys(ArrayList<String> keys) {
    this.keys = keys;
  }
  
  public void addKey(String key){
    this.keys.add(key);
  }
  
  
  
}
