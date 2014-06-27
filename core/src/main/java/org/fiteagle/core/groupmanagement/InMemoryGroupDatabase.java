package org.fiteagle.core.groupmanagement;

import java.sql.SQLException;
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
  public Group getGroup(String groupId) throws SQLException {
    Group g =  groupMap.get(groupId);
    if(g == null){
    	throw new SQLException();
    }
    return g;
  }
  
  @Override
  public List<Group> getGroups() {
    return new ArrayList<Group>(groupMap.values());
  }
  
  @Override
  public void deleteGroup(String groupId) {
    groupMap.remove(groupId);
  }

@Override
public void updateGroup(Group g3) {
	
	groupMap.put(g3.getGroupId(), g3);
}

@Override
public void deleteResourceFromGroup(String resourceId) {
	for(Group g: groupMap.values()){
		g.deleteResource(resourceId);
	}
	
}
  
  
}
