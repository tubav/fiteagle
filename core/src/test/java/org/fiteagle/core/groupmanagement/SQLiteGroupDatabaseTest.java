package org.fiteagle.core.groupmanagement;

import org.junit.Before;
import org.junit.Test;

public class SQLiteGroupDatabaseTest {
  
  private SQLiteGroupDatabase database;
  private Group g;

  @Before
  public void setUp() throws Exception {
    database = new SQLiteGroupDatabase();
    g = new Group("test", "test");
  }
  
 
  
  @Test(expected=RuntimeException.class)
  public void testSetAndGetAndDeleteAndGetGroup(){
    database.addGroup(g);
    Group g2 = database.getGroup(g.getGroupId());
    database.deleteGroup(g2.getGroupId());
    Group g3 = database.getGroup(g.getGroupId());
    
  }
  
  
  
}
