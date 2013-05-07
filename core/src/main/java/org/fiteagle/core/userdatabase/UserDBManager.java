package org.fiteagle.core.userdatabase;

import java.sql.SQLException;

import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.fiteagle.core.userdatabase.UserDB.DuplicateUIDException;
import org.fiteagle.core.userdatabase.UserDB.RecordNotFoundException;

public class UserDBManager {
  
  private UserDB database;
  private FiteaglePreferences preferences = new FiteaglePreferencesXML(this.getClass());
  
  private static enum databaseType {InMemory, SQLite} 
  private static final String DEFAULT_DATABASE_TYPE = databaseType.InMemory.name();
  
  public UserDBManager() throws SQLException{
    if(preferences.get("databaseType") == null){
      preferences.put("databaseType", DEFAULT_DATABASE_TYPE);
    }
    if(preferences.get("databaseType").equals(databaseType.SQLite.name())){
      database = new SQLiteUserDB();
    }
    else{
      database = new InMemoryUserDB();
    }   
  }
  
  public void add(User u) throws DuplicateUIDException, SQLException{
    database.add(u);
  }
  
  public void delete(String UID) throws SQLException{
    database.delete(UID);
  }
  
  public void delete(User u) throws SQLException{
    database.delete(u);
  }
  
  public void update(User u) throws RecordNotFoundException, SQLException{
    database.update(u);
  }
  
  public void addKey(User u, String key) throws RecordNotFoundException, SQLException{
    database.addKey(u, key);
  }
  
  public User get(String UID) throws RecordNotFoundException, SQLException{
    return database.get(UID);
  }
  
  public User get(User u) throws RecordNotFoundException, SQLException{
    return database.get(u);
  }
    
}
