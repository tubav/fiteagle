package org.fiteagle.interactors.sfa.common;

import java.util.ArrayList;

import org.fiteagle.core.util.URN;

public class GeniUser {
  
  private URN urn;
  private ArrayList<String> keys;
  
  public GeniUser() {
    this.setUrn(null);
    this.setKeys(new ArrayList<String>());
  }

  public URN getUrn() {
    return urn;
  }

  public void setUrn(URN urn2) {
    this.urn = urn2;
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
