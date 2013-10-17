package org.fiteagle.core.groupmanagement;

import java.util.List;

public interface GroupPersistable {
  
  public void add(Group group) ;
  
  public Group get(String groupId) ;
  
  public List<Group> getGroups();
  
  public void delete(String groupId) ;

  public void update(Group g3) ;

public void deleteResourceFromGroup(String resourceId) ;
  
}
