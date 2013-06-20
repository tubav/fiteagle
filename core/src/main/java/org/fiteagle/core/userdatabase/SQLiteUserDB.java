package org.fiteagle.core.userdatabase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.fiteagle.core.persistence.SQLiteDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SQLiteUserDB extends SQLiteDatabase implements UserPersistable {


	private static Logger log = LoggerFactory.getLogger(SQLiteUserDB.class);

	public SQLiteUserDB() throws DatabaseException, SQLException{
	  try{
	   
  		createTable("CREATE TABLE IF NOT EXISTS Users (username, firstName, lastName, email, passwordHash, passwordSalt,created, lastModified , PRIMARY KEY (username))");
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
		PreparedStatement ps = connection.prepareStatement("INSERT INTO Users VALUES (?,?,?,?,?,?,?,?)");
		ps.setString(1, u.getUsername());
		ps.setString(2, u.getFirstName());
		ps.setString(3, u.getLastName());
		ps.setString(4, u.getEmail());
		ps.setString(5, u.getPasswordHash());
		ps.setString(6, u.getPasswordSalt());
		ps.setDate(7, new java.sql.Date(u.getCreated().getTime()));
		ps.setDate(8, new java.sql.Date(u.getLast_modified().getTime()));
		
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

	private void addKeysToDatabase(String username, List<String> Keys) throws SQLException {	
	  Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement("INSERT INTO Keys VALUES (?,?)");
		for(String key: Keys){
			ps.setString(1, username);
			ps.setString(2, key);
			ps.addBatch();
		}		
		ps.executeBatch();		
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
		  Connection connection = getConnection();
      deleteUserFromDatabase(username);
      deleteKeysFromDatabase(username);
      connection.commit();
      connection.close();
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
	  Connection connection =getConnection();
		PreparedStatement ps = connection.prepareStatement("DELETE FROM Users WHERE username=?");
		ps.setString(1, username);
		ps.execute();
		ps.close();
		connection.commit();
		connection.close();
	}

	@Override
	public void update(User u) throws RecordNotFoundException, DatabaseException, NotEnoughAttributesException, InValidAttributeException {
	  User oldUser = get(u.getUsername());
	  u = User.createMergedUser(oldUser, u);
	  u.checkAttributes();
	  try{
	    Connection connection = getConnection();
	    updateUserInDatabase(u);
	    deleteKeysFromDatabase(u.getUsername());    
	    addKeysToDatabase(u.getUsername(), u.getPublicKeys());
	    connection.commit();
	    connection.close();
	  } catch(SQLException e){
	    throw new DatabaseException();
	  }
		
	}

	private void updateUserInDatabase(User u) throws SQLException {
	  Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement("UPDATE Users SET firstName=?, lastname=?, email=?, passwordHash=?, passwordSalt=?, last_modified=? WHERE username=?");
		ps.setString(1, u.getFirstName());
		ps.setString(2, u.getLastName());
		ps.setString(3, u.getEmail());
		ps.setString(4, u.getPasswordHash());
		ps.setString(5, u.getPasswordSalt());
		ps.setDate(6, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		ps.setString(7, u.getUsername());
		if(ps.executeUpdate() == 0){
			ps.close();
			throw new RecordNotFoundException();
		}
		ps.close();
		connection.commit();
		connection.close();
	}

	@Override
	public void addKey(String username, String key) throws DatabaseException, InValidAttributeException {
	  if(key == null || key.length() == 0){
      throw new InValidAttributeException();
    }
	  try{
  		if(!get(username).getPublicKeys().contains(key)){
  			ArrayList<String> keys = new ArrayList<String>();			
  			keys.add(key);
  			addKeysToDatabase(username,keys);
  			
  		}
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
	  PreparedStatement ps = connection.prepareStatement("SELECT Users.username, Users.firstname, Users.lastname, Users.email, Users.passwordHash, Users.passwordSalt,Users.created, Users.lastModified, Keys.key FROM Users LEFT OUTER JOIN Keys ON Users.username=Keys.username WHERE Users.username=?");
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
		String passwordHash = rs.getString(5);
		String passwordSalt = rs.getString(6);
		Date created = rs.getDate(7);
		Date lastModified = rs.getDate(8);
		String key1 = rs.getString(9);
		if(key1 != null){
			keys.add(key1);
		}
		while(rs.next()){		
			keys.add(rs.getString(7));
		}			
		return new User(username, firstname, lastname, email, passwordHash, passwordSalt,created,lastModified, keys);
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

	public void deleteAllEntries() throws DatabaseException{
	  try{
	    Connection connection = getConnection();
  		Statement st = connection.createStatement();
  		st.executeUpdate("DELETE FROM Users");	
  		st.executeUpdate("DELETE FROM Keys");	
  		st.close();
  		connection.commit();
  		connection.close();
	  } catch(SQLException e){
	    throw new DatabaseException();
	  }
	}
}
