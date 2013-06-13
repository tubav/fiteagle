package org.fiteagle.delivery.rest;

import org.fiteagle.core.groupmanagement.Group;

public interface RestGroupManagement {
  
  

  public Group getGroup(String groupId);
  public void addGroup(Group group);
  public void deleteGroup(String groupId);
  
}
