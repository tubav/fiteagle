package org.fiteagle.core.groupmanagement;

import java.util.List;

public interface GroupPersistable {
  
  public void addGroup(Group group);
  
  public Group getGroup(String groupId);
  
  public List<Group> getGroups();
  
  public void deleteGroup(String groupId);

  public void updateGroup(Group g3);

public void deleteResourceFromGroup(String resourceId);
  
}
