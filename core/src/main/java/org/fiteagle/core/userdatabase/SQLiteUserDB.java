package org.fiteagle.core.userdatabase;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;

public class SQLiteUserDB implements UserDB {
  
  private static final String DEFAULT_DATABASE_PATH = System.getProperty("user.home")+"/.fiteagle/db/";

	private Connection connection = null;	
	
	private FiteaglePreferences preferences = new FiteaglePreferencesXML(this.getClass());
	
	static {
        try {
            Class.forName("org.sqlite.JDBC"); 
        } catch (ClassNotFoundException e) {
        	//TODO error logging?
            System.err.println("Fehler beim Laden des JDBC-Treibers"); 
            e.printStackTrace();
        }
    }
	
	public SQLiteUserDB() throws SQLException{	 
		getConnection();
		createTableUsers();
		createTableKeys();
		connection.commit();
	}				

  private void createTableKeys() throws SQLException {
		Statement st = connection.createStatement();
		st.executeUpdate("CREATE TABLE IF NOT EXISTS Keys (UID, key)");
		st.close();
	}

	private void createTableUsers() throws SQLException {
		Statement st = connection.createStatement();
		st.executeUpdate("CREATE TABLE IF NOT EXISTS Users (UID, firstName, lastName,passwordHash,passwordSalt, PRIMARY KEY (UID))");
		st.close();
	}
	
	private void getConnection() throws SQLException{
		if(connection!=null){
		  connection.close();
		}
		connection = DriverManager.getConnection("jdbc:sqlite:" + getDatabasePath());
		connection.setAutoCommit(false);
	}
	
	private String getDatabasePath(){
	  if(preferences.get("userdatabasePath") == null){
	    preferences.put("userdatabasePath", DEFAULT_DATABASE_PATH);
	  }
	  String path = preferences.get("userdatabasePath");
	  File dir = new File(path);
	  dir.mkdirs();
	  return path + "userdatabase.db";
	}
	
	@Override
	public void add(User u) throws DuplicateUIDException, SQLException {			
		addUserToDatabase(u);		
		addKeysToDatabase(u.getUID(),u.getPublicKeys());
		connection.commit();
	}

	private void addUserToDatabase(User u) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO Users VALUES (?,?,?,?,?)");
		ps.setString(1, u.getUID());
		ps.setString(2, u.getFirstName());
		ps.setString(3, u.getLastName());
		ps.setString(4, u.getPasswordHash());
		ps.setString(5,u.getPasswordSalt());
		try{
			ps.execute();
		} catch(SQLException e){
			throw new DuplicateUIDException();
		} finally{
			ps.close();
		}
	}

	private void addKeysToDatabase(String UID,List<String> Keys) throws SQLException {		
		PreparedStatement ps = connection.prepareStatement("INSERT INTO Keys VALUES (?,?)");
		for(String key: Keys){
			ps.setString(1, UID);
			ps.setString(2, key);
			ps.addBatch();
		}		
		ps.executeBatch();		
		ps.close();
	}

	@Override
	public void delete(User u) throws SQLException {
		delete(u.getUID());
	}
	
	@Override
	public void delete(String UID) throws SQLException {		
		deleteUserFromDatabase(UID);		
		deleteKeysFromDatabase(UID);
		connection.commit();
	}

	private void deleteKeysFromDatabase(String UID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM Keys WHERE UID=?");
		ps.setString(1, UID);
		ps.execute();
		ps.close();
	}

	private void deleteUserFromDatabase(String UID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM Users WHERE UID=?");
		ps.setString(1, UID);
		ps.execute();
		ps.close();
	}

	@Override
	public void update(User u) throws RecordNotFoundException, SQLException {
		updateUserInDatabase(u);
		deleteKeysFromDatabase(u.getUID());		
		addKeysToDatabase(u.getUID(), u.getPublicKeys());
		connection.commit();
	}

	private void updateUserInDatabase(User u) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE Users SET firstName=?, lastname=?, passwordHash=?, passwordSalt=? WHERE UID=?");
		ps.setString(1, u.getFirstName());
		ps.setString(2, u.getLastName());
		ps.setString(3, u.getPasswordHash());
		ps.setString(4, u.getPasswordSalt());
		ps.setString(5, u.getUID());
		if(ps.executeUpdate() == 0){
			ps.close();
			throw new RecordNotFoundException();
		}
		ps.close();
	}

	@Override
	public void addKey(String UID, String key) throws SQLException {
		if(!get(UID).getPublicKeys().contains(key)){
			ArrayList<String> keys = new ArrayList<String>();			
			keys.add(key);
			addKeysToDatabase(UID,keys);
			connection.commit();
		}		
	}

	@Override
	public User get(String UID) throws RecordNotFoundException, SQLException {		
		User u = getUserFromDatabase(UID);
		if(u == null){
			throw new UserDB.RecordNotFoundException();
		}
		return u;
	}
	
	private User getUserFromDatabase(String UID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT Users.UID, Users.firstname, Users.lastname, Users.passwordHash, Users.passwordSalt, Keys.key FROM Users LEFT OUTER JOIN Keys ON Users.UID=Keys.UID WHERE Users.UID=?");
		ps.setString(1, UID);
		ResultSet rs = ps.executeQuery();		
		User u = null;
		if(rs.next()){
			u = evaluateResultSet(rs);
		}
		ps.close();
		return u;
	}

	private User evaluateResultSet(ResultSet rs) throws SQLException {
		ArrayList<String> keys = new ArrayList<String>();
		String UID = rs.getString(1);
		String firstname = rs.getString(2);
		String lastname = rs.getString(3);
		String passwordHash = rs.getString(4);
		String passwordSalt = rs.getString(5);
		String key1 = rs.getString(6);
		if(key1 != null){
			keys.add(key1);
		}
		while(rs.next()){		
			keys.add(rs.getString(4));
		}			
		return new User(UID, firstname, lastname,passwordHash,passwordSalt, keys);
	}

	@Override
	public User get(User u) throws RecordNotFoundException, SQLException {		
		return get(u.getUID());
	}

	@Override
	public int getNumberOfUsers() throws SQLException {	
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) AS NumberOfUsers FROM Users");
		int size = 0;
		if(rs.next()){
			size = rs.getInt(1);			
		}
		st.close();
		return size;
	}	

	public void deleteAllEntries() throws SQLException{	
		Statement st = connection.createStatement();
		st.executeUpdate("DELETE FROM Users");	
		st.executeUpdate("DELETE FROM Keys");	
		st.close();
		connection.commit();
	}
}