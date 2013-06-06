package org.fiteagle.core.groupmanagement;

import java.util.List;

public interface GroupDatabase {
  
  public void addGroup(Group group);
  
  public Group getGroup(String groupId);
  
  public List<Group> getGroups();
  
  public void deleteGroup(String groupId);
  
}
