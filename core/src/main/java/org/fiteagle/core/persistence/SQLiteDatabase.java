package org.fiteagle.core.persistence;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SQLiteDatabase {

private  final String DEFAULT_DATABASE_PATH = System.getProperty("user.home")+"/.fiteagle/db/";
protected FiteaglePreferences preferences;
protected static Logger log = LoggerFactory.getLogger(SQLiteDatabase.class); 
static {
  try {
      Class.forName("org.sqlite.JDBC"); 
  } catch (ClassNotFoundException e) {
      
      log.error(e.getMessage(),e);
  }
}


 public  SQLiteDatabase() throws SQLException{
   preferences = new FiteaglePreferencesXML(SQLiteDatabase.class);  
 }
 
  
  protected Connection getConnection() throws SQLException{
	Connection connection =DriverManager.getConnection("jdbc:sqlite:" + getDatabasePath());
    connection.setAutoCommit(false);
    return connection;
  }
  
  private void updatePreferences() {
   this.preferences = new FiteaglePreferencesXML(SQLiteDatabase.class);    
  }
  
  
  
  private String getDatabasePath() {
    if(preferences.get("database_path") == null)
      preferences.put("database_path", DEFAULT_DATABASE_PATH);
    
    String path = preferences.get("database_path");
    File dir = new File(path);
    dir.mkdirs();
    return path + "database.db";
  }
  
  protected void createTable(Connection connection, String command) throws SQLException{
    
    Statement st = connection.createStatement();
    st.executeUpdate(command);
    st.close();
    connection.commit();
  }
  
  protected void executeSQLString(Connection connection, String SQLString, List<Object> params) throws SQLException {
  
    PreparedStatement ps = connection.prepareStatement(SQLString);
    for(int i = 0; i < params.size(); i++){
      ps.setObject(i+1, params.get(i));
    }
    ps.execute();
    connection.commit();
    ps.close();
    
  }
  
  protected int executeSQLUpdateString(Connection connection, String SQLString, List<Object> params) throws SQLException {
   
    PreparedStatement ps = connection.prepareStatement(SQLString);
    for(int i = 0; i < params.size(); i++){
      ps.setObject(i+1, params.get(i));
    }
    int count = ps.executeUpdate();
    connection.commit();
    ps.close();
    return count;
  }
}
