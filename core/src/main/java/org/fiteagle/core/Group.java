package org.fiteagle.core;

import java.util.ArrayList;

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
  
}
