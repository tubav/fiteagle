package org.fiteagle.delivery.rest.fiteagle;

import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.interactors.groupmanagement.GroupManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GroupPresenterTest {
  GroupPresenter pre;
  Group g;
  @Before
  public void setUp() throws Exception {
     pre = new GroupPresenter(new GroupManager());
     g = new Group("slice", "owner");
  }
  

  @Test
  public void testAddAndGetGroup(){
     
      pre.addGroup(g.getGroupId(),g);
      Group newGroup = pre.getGroup(g.getGroupId());
      Assert.assertNotNull(newGroup);
  }
  
  @Test(expected=Throwable.class)
  public void testDeleteGroup(){
    pre.deleteGroup(g.getGroupId());
    Group noGroup = pre.getGroup(g.getGroupId());
  }
  
}
