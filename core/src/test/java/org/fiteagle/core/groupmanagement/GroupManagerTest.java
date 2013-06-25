package org.fiteagle.core.groupmanagement;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GroupManagerTest {
  GroupDBManager gm;
  Group group ;
  @Before
  public void setUp() throws Exception {
    gm = GroupDBManager.getInstance();
    group = new Group("testGroupId", "testOwnerID");
    gm.addGroup(group);
  }
  @After
  public void deleteGroup(){
    gm.deleteGroup(group.getGroupId());
  }
   
  @Test
  public void testGetGroup(){
   
    Group g2 = gm.getGroup(group.getGroupId());
    Assert.assertNotNull(g2);
   
  }
  
  
  
  
  
 
}
