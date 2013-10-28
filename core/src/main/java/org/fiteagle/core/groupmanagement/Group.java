package org.fiteagle.core.groupmanagement;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name="GROUPS")
public class Group implements Serializable {
  
  @Id
  private String groupId;
  private String groupOwnerId;
  @JsonIgnore
  private List<String> resources;
  @JsonIgnore
  private List<String> authorizedUsers;

  @JsonIgnore
  static Logger log = LoggerFactory.getLogger(Group.class);

  protected Group() {
	
}
  public Group(String groupId, String groupOwnerId) {
    this.groupId = groupId;
    this.groupOwnerId = groupOwnerId;
    this.resources = new LinkedList<>();
  }
//
//  public Group(String urn, String groupOwnerId, List<ResourceAdapter> resourcesList) {
//    this.groupId=urn;
//    this.groupOwnerId=groupOwnerId;
//    this.resources=resourcesList;
//  }

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
  
  public List<String> getResources() {
    return resources;
  }
  
  public void setResources(List<String> resources) {
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
  public void addResource(String bookedId) {
    if(this.resources == null)
      this.resources = new ArrayList<String>();
    this.resources.add(bookedId);
  }
  
  public boolean contains(String bookedId){
    return resources.contains(bookedId);
  }
  
  
  public void deleteResource(String adapterId){
    resources.remove(adapterId);
    
  }
  
  @Override
  public String toString(){
	  return "Group: "+ this.groupId + "\n" + "Owner: "+ this.groupOwnerId;
  }
  
}
