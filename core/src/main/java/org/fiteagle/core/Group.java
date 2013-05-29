package org.fiteagle.core;

import java.util.ArrayList;
import java.util.Iterator;

import org.fiteagle.adapter.common.ResourceAdapter;

public class Group {
  
  private String groupId;
  private String groupOwnerId;
  private ArrayList<ResourceAdapter> resources;
  private ArrayList<String> authorizedUsers=new ArrayList<String>();
  
  public Group(String groupId, String groupOwnerId, ArrayList<ResourceAdapter> resources) {
    this.groupId = groupId;
    this.groupOwnerId = groupOwnerId;
    this.resources = resources;
  }
  
  public String getGroupId() {
    return groupId;
  }
  
  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }
  
  public String getGroupOwnerId() {
    return groupOwnerId;
  }
  
  public void setGroupOwnerId(String groupOwnerId) {
    this.groupOwnerId = groupOwnerId;
  }
  
  public ArrayList<ResourceAdapter> getResources() {
    return resources;
  }
  
  public void setResources(ArrayList<ResourceAdapter> resources) {
    this.resources = resources;
  }
  
  public ArrayList<String> getAuthorizedUsers() {
    return authorizedUsers;
  }
  
  public void setAuthorizedUsers(ArrayList<String> authorizedUsers) {
    this.authorizedUsers = authorizedUsers;
  }
  
  public void addResource(ResourceAdapter resource) {
    if(this.resources == null)
      this.resources = new ArrayList<ResourceAdapter>();
    this.resources.add(resource);
  }
  
  public boolean contains(String resourceAdapterId){
    for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
      ResourceAdapter resourceAdapter = (ResourceAdapter) iterator.next();
      if (resourceAdapter.getId().compareTo(resourceAdapterId)==0)
        return true;
    }
    return false;
  }
  
  public ResourceAdapter getResource(String adapterId){
    for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
      ResourceAdapter resourceAdapter = (ResourceAdapter) iterator.next();
      if (resourceAdapter.getId().compareTo(adapterId)==0)
        return resourceAdapter;
    }
    return null;
  }
  
  public void deleteResource(String adapterId){
    
    Iterator iterator = resources.iterator();
    while (iterator.hasNext()) {
      ResourceAdapter resourceAdapter = (ResourceAdapter) iterator.next();
      if (resourceAdapter.getId().compareTo(adapterId)==0){
        resources.remove(resourceAdapter);
        return;
      }
    }
    
  }
  
}
