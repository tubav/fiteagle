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
private static Connection connection;
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
    if(connection != null)
        connection.close();    
    connection = DriverManager.getConnection("jdbc:sqlite:" + getDatabasePath());
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
  
  protected void createTable(String command) throws SQLException{
    Connection connection = getConnection();
    Statement st = connection.createStatement();
    st.executeUpdate(command);
    st.close();
    connection.commit();
    connection.close();
  }
  
  protected void executeSQLString(String SQLString, List<Object> params) throws SQLException {
    Connection connection = getConnection();
    PreparedStatement ps = connection.prepareStatement(SQLString);
    for(int i = 0; i < params.size(); i++){
      ps.setObject(i+1, params.get(i));
    }
    ps.execute();
    ps.close();
    connection.commit();
    connection.close();
  }
  
  protected int executeSQLUpdateString(String SQLString, List<Object> params) throws SQLException {
    Connection connection = getConnection();
    PreparedStatement ps = connection.prepareStatement(SQLString);
    for(int i = 0; i < params.size(); i++){
      ps.setObject(i+1, params.get(i));
    }
    int count = ps.executeUpdate();
    ps.close();
    connection.commit();
    connection.close();
    return count;
  }
}
