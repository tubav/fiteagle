package org.fiteagle.core.groupmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryGroupDatabase implements GroupPersistable {
  
  private static Map<String, Group> groupMap = new HashMap<>();
  
  @Override
  public void addGroup(Group group) {
    groupMap.put(group.getGroupId(), group);
  }
  
  @Override
  public Group getGroup(String groupId) {
    return groupMap.get(groupId);
  }
  
  @Override
  public List<Group> getGroups() {
    return new ArrayList<Group>(groupMap.values());
  }
  
  @Override
  public void deleteGroup(String groupId) {
    groupMap.remove(groupId);
  }
  
  
}
