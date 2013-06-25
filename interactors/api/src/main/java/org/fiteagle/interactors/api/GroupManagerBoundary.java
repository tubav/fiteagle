package org.fiteagle.interactors.api;

import org.fiteagle.core.groupmanagement.Group;

public interface GroupManagerBoundary {

  Group getGroup(String groupId);

  void addGroup(String groupId, Group group);

  void deleteGroup(String groupId);
  
}
