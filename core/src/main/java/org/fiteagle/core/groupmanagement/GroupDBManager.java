package org.fiteagle.core.groupmanagement;

import java.sql.SQLException;

import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.fiteagle.core.groupmanagement.SQLiteGroupDatabase.CouldNotCreateGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupDBManager {

  private static GroupDBManager gm = null;
  private GroupPersistable groupDatabase;
  private FiteaglePreferences preferences = new FiteaglePreferencesXML(this.getClass());
  private Logger log = LoggerFactory.getLogger(getClass());
  private static enum databaseType {
    InMemory, SQLite
  }
  private static final String DEFAULT_DATABASE_TYPE = databaseType.SQLite.name();
  private GroupDBManager() throws SQLException{
    if (preferences.get("databaseType") == null) {
      preferences.put("databaseType", DEFAULT_DATABASE_TYPE);
    }
    if (preferences.get("databaseType").equals(databaseType.SQLite.name())) {
      groupDatabase = new SQLiteGroupDatabase();
    }else if(preferences.get("databaseType").equals(databaseType.InMemory.name())){
      groupDatabase = new InMemoryGroupDatabase();
    }
  }
  public void addGroup(Group group) throws CouldNotCreateGroup {
    
    groupDatabase.addGroup(group);
  }

  public Group getGroup(String groupId) {
    return groupDatabase.getGroup(groupId);
  }

  public void deleteGroup(String groupId) {
      groupDatabase.deleteGroup(groupId);
  }
  
  public static GroupDBManager getInstance()  {
    if(gm == null ){
      try {
        gm = new GroupDBManager();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    return gm;
  }
public void updateGroup(Group g3) {
	groupDatabase.updateGroup(g3);
}
public void deleteResourceFromGroup(String resourceId) {
	groupDatabase.deleteResourceFromGroup(resourceId);
	
}
public static class GroupNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GroupNotFound(String message) {
		super(message);
	}

}
  
}
