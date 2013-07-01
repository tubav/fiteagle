package org.fiteagle.core.userdatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.fiteagle.core.persistence.SQLiteDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SQLiteUserDB extends SQLiteDatabase implements UserPersistable {


	private static Logger log = LoggerFactory.getLogger(SQLiteUserDB.class);

	private static SQLiteUserDB sqliteUserDB;
	
	public static SQLiteUserDB getInstance() throws DatabaseException{
	  if(sqliteUserDB == null){
	    try{
	      sqliteUserDB = new SQLiteUserDB();
	    } catch (SQLException e){
	      throw new DatabaseException();
	    }
	  }
	  return sqliteUserDB;
	}
	
	public SQLiteUserDB() throws DatabaseException, SQLException{
	  try{	   
  		createTable("CREATE TABLE IF NOT EXISTS Users (username, firstName, lastName, email, affiliation, passwordHash, passwordSalt, created, lastModified , PRIMARY KEY (username))");
  		createTable("CREATE TABLE IF NOT EXISTS Keys (username, key)");  		
	  } catch(SQLException e){	    
	    log.error(e.getMessage(),e);
	    throw new DatabaseException();
	  }
	}
	
	@Override
	public void add(User u) throws DuplicateUsernameException, DatabaseException, NotEnoughAttributesException, InValidAttributeException {
	  u.checkAttributes();
	  try{
  		addUserToDatabase(u);		
  		addKeysToDatabase(u.getUsername(),u.getPublicKeys());

	  } catch(SQLException e){
	    throw new DatabaseException();
	  }
	}

	private void addUserToDatabase(User u) throws SQLException {
	  Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement("INSERT INTO Users VALUES (?,?,?,?,?,?,?,?,?)");
		ps.setString(1, u.getUsername());
		ps.setString(2, u.getFirstName());
		ps.setString(3, u.getLastName());
		ps.setString(4, u.getEmail());
		ps.setString(5, u.getAffiliation());
		ps.setString(6, u.getPasswordHash());
		ps.setString(7, u.getPasswordSalt());
		ps.setDate(8, new java.sql.Date(u.getCreated().getTime()));
		ps.setDate(9, new java.sql.Date(u.getLast_modified().getTime()));
		
		try{
			ps.execute();
		} catch(SQLException e){
		    if(e.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (column username is not unique)")){
		      throw new DuplicateUsernameException();
		    }
		    else{
		      throw e;
		    }
		} finally{
			ps.close();
			connection.commit();
			connection.close();
		}
	}

	private void addKeysToDatabase(String username, List<String> keys) throws SQLException {	
	  Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement("INSERT INTO Keys VALUES (?,?)");
		for(String key: keys){
			ps.setString(1, username);
			ps.setString(2, key);
			ps.addBatch();
		}		
		ps.executeBatch();		
		ps.close();
		connection.commit();
		connection.close();
	}
	
	private void deleteKeyFromDatabase(String username, String key) throws SQLException {
	  Connection connection = getConnection();
	  
    PreparedStatement ps = connection.prepareStatement("DELETE FROM Keys WHERE username=? AND key=?");
    ps.setString(1, username);
    ps.setString(2, key);
    ps.execute();       
    ps.close();
    connection.commit();
    connection.close();
	}

	@Override
	public void delete(User u) throws DatabaseException {		
	  delete(u.getUsername());   
	}
	
	@Override
	public void delete(String username) throws DatabaseException {		
		try {		  
      deleteUserFromDatabase(username);
      deleteKeysFromDatabase(username);    
    } catch (SQLException e) {
      throw new DatabaseException();
    }		
	}

	private void deleteKeysFromDatabase(String username) throws SQLException {
	  Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement("DELETE FROM Keys WHERE username=?");
		ps.setString(1, username);
		ps.execute();
		ps.close();
		connection.commit();
		connection.close();
	}

	private void deleteUserFromDatabase(String username) throws SQLException {
	  Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement("DELETE FROM Users WHERE username=?");
		ps.setString(1, username);
		ps.execute();
		ps.close();
		connection.commit();
		connection.close();
	}

	@Override
	public void update(User u) throws RecordNotFoundException, DatabaseException, NotEnoughAttributesException, InValidAttributeException {
	  User newUser = get(u.getUsername());
    newUser.mergeWithUser(u);        	  
	  
	  try{	    
	    updateUserInDatabase(newUser);
	    deleteKeysFromDatabase(newUser.getUsername());    
	    addKeysToDatabase(newUser.getUsername(), newUser.getPublicKeys());	   
	  } catch(SQLException e){
	    throw new DatabaseException();
	  }		
	}

	private void updateUserInDatabase(User u) throws SQLException {
	  Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement("UPDATE Users SET firstName=?, lastname=?, email=?, affiliation=?, passwordHash=?, passwordSalt=?, lastModified=? WHERE username=?");
		ps.setString(1, u.getFirstName());
		ps.setString(2, u.getLastName());
		ps.setString(3, u.getEmail());
		ps.setString(4, u.getAffiliation());
		ps.setString(5, u.getPasswordHash());
		ps.setString(6, u.getPasswordSalt());		
		java.util.Calendar now = java.util.Calendar.getInstance();
		ps.setDate(7, new java.sql.Date(now.getTimeInMillis()));
		ps.setString(8, u.getUsername());
		if(ps.executeUpdate() == 0){
			ps.close();
			throw new RecordNotFoundException();
		}
		ps.close();
		connection.commit();
		connection.close();
	}

	@Override
	public void addKey(String username, String key) throws RecordNotFoundException, DatabaseException, InValidAttributeException {
	  if(key == null || key.length() == 0){
      throw new InValidAttributeException();
    }
	  User user = get(username);
	  if(user == null){
	    throw new RecordNotFoundException();
	  }
	  try{
  		if(!user.getPublicKeys().contains(key)){
  			ArrayList<String> keys = new ArrayList<String>();			
  			keys.add(key);
  			addKeysToDatabase(username,keys);  			
  		}
	  } catch(SQLException e){
	    throw new DatabaseException();
	  }
	}

  @Override
  public void deleteKey(String username, String key) throws RecordNotFoundException, DatabaseException,
      InValidAttributeException {
    if(key == null || key.length() == 0){
      throw new InValidAttributeException();
    }
    User user = get(username);
    if(user == null){
      throw new RecordNotFoundException();
    }
    try{
      deleteKeyFromDatabase(username, key);
    } catch(SQLException e){
      throw new DatabaseException();
    }    
  }
	
	@Override
	public User get(String username) throws RecordNotFoundException, DatabaseException {		
		User u;
    try {
      u = getUserFromDatabase(username);
    } catch (SQLException e) {
      throw new DatabaseException();
    }
		if(u == null){
			throw new UserPersistable.RecordNotFoundException();
		}
		return u;
	}
	
	private User getUserFromDatabase(String username) throws SQLException {
		Connection connection = getConnection();
	  PreparedStatement ps = connection.prepareStatement("SELECT Users.username, Users.firstname, Users.lastname, Users.email, Users.affiliation, Users.passwordHash, Users.passwordSalt, Users.created, Users.lastModified, Keys.key FROM Users LEFT OUTER JOIN Keys ON Users.username=Keys.username WHERE Users.username=?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();		
		User u = null;
		if(rs.next()){
			u = evaluateResultSet(rs);
		}
		ps.close();
		connection.commit();
		connection.close();
		return u;
	}

	private User evaluateResultSet(ResultSet rs) throws SQLException {
		ArrayList<String> keys = new ArrayList<String>();
		String username = rs.getString(1);
		String firstname = rs.getString(2);
		String lastname = rs.getString(3);
		String email = rs.getString(4);
		String affiliation = rs.getString(5);
		String passwordHash = rs.getString(6);
		String passwordSalt = rs.getString(7);
		java.util.Date created = new java.util.Date(rs.getDate(8).getTime());
		java.util.Date lastModified = new java.util.Date(rs.getDate(9).getTime());
		String key1 = rs.getString(10);
		if(key1 != null){
			keys.add(key1);
		}
		while(rs.next()){		
			keys.add(rs.getString(10));
		}			
		return new User(username, firstname, lastname, email, affiliation, passwordHash, passwordSalt, created, lastModified, keys);
	}

	@Override
	public User get(User u) throws RecordNotFoundException, DatabaseException {		
		return get(u.getUsername());
	}

	@Override
	public int getNumberOfUsers() throws DatabaseException {
	  try{
	    Connection connection = getConnection();
  		Statement st = connection.createStatement();
  		ResultSet rs = st.executeQuery("SELECT COUNT(*) AS NumberOfUsers FROM Users");
  		int size = 0;
  		if(rs.next()){
  			size = rs.getInt(1);			
  		}
  		st.close();
  		connection.commit();
  		connection.close();
  		return size;
	  } catch(SQLException e){
	    throw new DatabaseException();
	  }
	}	

}
