package org.fiteagle.core.groupmanagement;

import java.sql.SQLException;
import java.util.List;

public interface GroupPersistable {
  
  public void addGroup(Group group) throws SQLException;
  
  public Group getGroup(String groupId) throws SQLException;
  
  public List<Group> getGroups();
  
  public void deleteGroup(String groupId) throws SQLException;

  public void updateGroup(Group g3) throws SQLException;

public void deleteResourceFromGroup(String resourceId) throws SQLException;
  
}
