package org.fiteagle.core.groupmanagement;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.fiteagle.adapter.common.ResourceAdapter;

public class Group {
  
  private String groupId;
  private String groupOwnerId;
  private List<ResourceAdapter> resources;
  private List<String> authorizedUsers;

  
  public Group(String groupId, String groupOwnerId) {
    this.groupId = groupId;
    this.groupOwnerId = groupOwnerId;
  }

  public Group(String urn, String groupOwnerId, List<ResourceAdapter> resourcesList) {
    this.groupId=urn;
    this.groupOwnerId=groupOwnerId;
    this.resources=resourcesList;
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
  
  public List<ResourceAdapter> getResources() {
    return resources;
  }
  
  public void setResources(List<ResourceAdapter> resources) {
    this.resources = resources;
  }
  
  public List<String> getAuthorizedUsers() {
    return authorizedUsers;
  }
 
  
  public void setAuthorizedUsers(List<String> authorizedUsers) {
    this.authorizedUsers = authorizedUsers;
  }
  public void addAuthorizedUser(String user){
    if(authorizedUsers == null)
      authorizedUsers =  new LinkedList<>();
      
    authorizedUsers.add(user);
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
