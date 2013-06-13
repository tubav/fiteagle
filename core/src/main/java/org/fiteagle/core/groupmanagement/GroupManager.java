package org.fiteagle.core.groupmanagement;

import java.sql.SQLException;

import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupManager {

  private static GroupManager gm = null;
  private GroupDatabase groupDatabase;
  private FiteaglePreferences preferences = new FiteaglePreferencesXML(this.getClass());
  private Logger log = LoggerFactory.getLogger(getClass());
  private static enum databaseType {
    InMemory, SQLite
  }
  private static final String DEFAULT_DATABASE_TYPE = databaseType.InMemory.name();
  private GroupManager() throws SQLException{
    if (preferences.get("databaseType") == null) {
      preferences.put("databaseType", DEFAULT_DATABASE_TYPE);
    }
    if (preferences.get("databaseType").equals(databaseType.SQLite.name())) {
      groupDatabase = new SQLiteGroupDatabase();
    }else if(preferences.get("databaseType").equals(databaseType.InMemory.name())){
      groupDatabase = new InMemoryGroupDatabase();
    }
  }
  public void addGroup(Group group) {
    
    groupDatabase.addGroup(group);
  }

  public Group getGroup(String groupId) {
    return groupDatabase.getGroup(groupId);
  }

  public void deleteGroup(String groupId) {
      groupDatabase.deleteGroup(groupId);
    
  }
  public static GroupManager getInstance() throws SQLException {
    if(gm == null ){
      gm = new GroupManager();
    }
    return gm;
  }
  
  
}
