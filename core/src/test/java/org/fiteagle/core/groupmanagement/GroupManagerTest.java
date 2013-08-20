package org.fiteagle.core.groupmanagement;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GroupManagerTest {
  GroupDBManager gm;
  Group group ;
  ResourceAdapter ra;
  @Before
  public void setUp() throws Exception {
    gm = GroupDBManager.getInstance();
    group = new Group("testGroupId", "testOwnerID");
    gm.addGroup(group);
    ra = EasyMock.createMock(ResourceAdapter.class);
    EasyMock.expect(ra.getId()).andReturn("dummyRA");
    EasyMock.expectLastCall().times(2);
    EasyMock.replay(ra);
  }
  @After
  public void deleteGroup(){
    gm.deleteGroup(group.getGroupId());
    gm.deleteGroup("g2");
  }
   
  @Test
  public void testGetGroup(){
    Group g2 = gm.getGroup(group.getGroupId());
    Assert.assertNotNull(g2);
  }

  @Test
  public void testAddGroupWithResources(){
	Group g2 = new Group("g2", "test");
	g2.addResource(ra);
	gm.addGroup(g2);
  }
  
 
  @Test
  public void testUpdateGroup(){
	  Group g3 = gm.getGroup(group.getGroupId());
	  Assert.assertNotNull(g3);
	  g3.addResource(ra);
	  gm.updateGroup(g3); 
	  Group g4 = gm.getGroup(group.getGroupId());
	  Assert.assertTrue(g4.contains(ra.getId()));
  }
  
  
  
  
  
 
}
