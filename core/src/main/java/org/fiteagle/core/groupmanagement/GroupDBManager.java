package org.fiteagle.core.groupmanagement;

import java.sql.SQLException;

import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.fiteagle.core.groupmanagement.SQLiteGroupDatabase.CouldNotCreateGroup;
import org.fiteagle.core.groupmanagement.SQLiteGroupDatabase.CouldNotDeleteGroup;
import org.fiteagle.core.groupmanagement.SQLiteGroupDatabase.CouldNotDeleteResource;
import org.fiteagle.core.groupmanagement.SQLiteGroupDatabase.CouldNotUpdateGroup;
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
    
    try {
		groupDatabase.addGroup(group);
	} catch (SQLException e) {
		throw new CouldNotCreateGroup();
	}
  }

  public Group getGroup(String groupId) throws GroupNotFound{
    try {
		return groupDatabase.getGroup(groupId);
	} catch (SQLException e) {
		throw new GroupNotFound(groupId);
	}
  }

  public void deleteGroup(String groupId) throws CouldNotDeleteGroup {
      try {
		groupDatabase.deleteGroup(groupId);
	} catch (SQLException e) {
		throw new CouldNotDeleteGroup();
	}
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
public void updateGroup(Group g3) throws CouldNotUpdateGroup{
	try {
		groupDatabase.updateGroup(g3);
	} catch (SQLException e) {
		throw new CouldNotUpdateGroup();
	}
}
public void deleteResourceFromGroup(String resourceId) throws  CouldNotDeleteResource{
	try {
		groupDatabase.deleteResourceFromGroup(resourceId);
	} catch (SQLException e) {
		throw new CouldNotDeleteResource();
	}
	
}
public static class GroupNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GroupNotFound(String message) {
		super(message);
	}

}
  
}
