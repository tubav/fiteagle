package org.fiteagle.interactors.groupmanagement;

import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.groupmanagement.SQLiteGroupDatabase.CouldNotCreateGroup;
import org.fiteagle.interactors.api.GroupManagerBoundary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupManager implements GroupManagerBoundary {
  private Logger log = LoggerFactory.getLogger(getClass());
  private GroupDBManager groupDBManager;
  
  public GroupManager() {
    
    this.groupDBManager = GroupDBManager.getInstance();
    
  }
  
  @Override
  public Group getGroup(String groupId) {
    return groupDBManager.getGroup(groupId);
  }
  
  @Override
  public void addGroup(String groupId, Group group) {
    
    groupDBManager.addGroup(group);
    
  }
  
  @Override
  public void deleteGroup(String groupId) {
    groupDBManager.deleteGroup(groupId);
    
  }
  
}
