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
	      throw new DatabaseException(e.getMessage());
	    }
	  }
	  return sqliteUserDB;
	}
	
	public SQLiteUserDB() throws DatabaseException, SQLException{
	  try{	   
  		createTable("CREATE TABLE IF NOT EXISTS Users (username, firstName, lastName, email, affiliation, passwordHash, passwordSalt, created, lastModified , PRIMARY KEY (username))");
  		createTable("CREATE TABLE IF NOT EXISTS Keys (username, description, key, created, PRIMARY KEY (username, key), UNIQUE (username, description))");  		
	  } catch(SQLException e){	    
	    log.error(e.getMessage(),e);
	    throw new DatabaseException(e.getMessage());
	  }
	}
	
	@Override
	public void add(User u) throws DuplicateUsernameException, DatabaseException, NotEnoughAttributesException, InValidAttributeException, DuplicatePublicKeyException {
	  u.checkAttributes();
	  try{
  		addUserToDatabase(u);		
  		addKeysToDatabase(u.getUsername(),u.getPublicKeys());
	  } catch(SQLException e){
	    throw new DatabaseException(e.getMessage());
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

	private void addKeysToDatabase(String username, List<PublicKey> keys) throws SQLException {	 		
		for(PublicKey key: keys){
		  try{
		    addKeyToDatabase(username, key);			
		  } catch(DuplicatePublicKeyException e){		    
		  }
		}				
	}

  private void addKeyToDatabase(String username, PublicKey key) throws SQLException {
    Connection connection = getConnection();
    PreparedStatement ps = connection.prepareStatement("INSERT INTO Keys VALUES (?,?,?,?)");
    ps.setString(1, username);
    ps.setString(2, key.getDescription());
    ps.setString(3, key.getPublicKey());
    ps.setDate(4, new java.sql.Date(key.getCreated().getTime()));
    try{
      ps.execute();
    } catch(SQLException e){
      if(e.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (columns username, description are not unique)")
          || e.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (columns username, key are not unique)")){
        throw new DuplicatePublicKeyException();
      }
      else{
        log.error(e.getMessage());
        throw e;
      }
    } finally {
      ps.close();
      connection.commit();
      connection.close();
    }
  }
	
	private void deleteKeyFromDatabase(String username, String description) throws SQLException {
	  Connection connection = getConnection();	  
    PreparedStatement ps = connection.prepareStatement("DELETE FROM Keys WHERE username=? AND description=?");
    ps.setString(1, username);
    ps.setString(2, description);
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
      throw new DatabaseException(e.getMessage());
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
	public void update(User u) throws RecordNotFoundException, DatabaseException, NotEnoughAttributesException, InValidAttributeException, DuplicatePublicKeyException {
	  User newUser = get(u.getUsername());
    newUser.mergeWithUser(u);        	  
	  
	  try{	    
	    updateUserInDatabase(newUser);
	    deleteKeysFromDatabase(newUser.getUsername());    
	    addKeysToDatabase(newUser.getUsername(), newUser.getPublicKeys());	   
	  } catch(SQLException e){
	    throw new DatabaseException(e.getMessage());
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
	public void addKey(String username, PublicKey key) throws RecordNotFoundException, DatabaseException, InValidAttributeException, DuplicatePublicKeyException {
	  if(key == null || key.getPublicKey().length() == 0){
      throw new InValidAttributeException("no valid key");
    }
	  User user = get(username);
	  if(user == null){
	    throw new RecordNotFoundException();
	  }
	  try{  		
  		addKeyToDatabase(username,key);  		
	  } catch(SQLException e){
	    throw new DatabaseException(e.getMessage());
	  }
	}

  @Override
  public void deleteKey(String username, String description) throws RecordNotFoundException, DatabaseException,
      InValidAttributeException {
    if(description == null || description.length() == 0){
      throw new InValidAttributeException("no valid key");
    }
    User user = get(username);
    if(user == null){
      throw new RecordNotFoundException();
    }
    try{
      deleteKeyFromDatabase(username, description);
    } catch(SQLException e){
      throw new DatabaseException(e.getMessage());
    }    
  }
	
	@Override
	public User get(String username) throws RecordNotFoundException, DatabaseException {		
		User u;
    try {
      u = getUserFromDatabase(username);
    } catch (SQLException e) {
      throw new DatabaseException(e.getMessage());
    }
		if(u == null){
			throw new UserPersistable.RecordNotFoundException();
		}
		return u;
	}
	
	private User getUserFromDatabase(String username) throws SQLException {
		Connection connection = getConnection();
	  PreparedStatement ps = connection.prepareStatement("SELECT Users.username, Users.firstname, Users.lastname, Users.email, Users.affiliation, Users.passwordHash, Users.passwordSalt, Users.created, Users.lastModified, Keys.description, Keys.key, Keys.created FROM Users LEFT OUTER JOIN Keys ON Users.username=Keys.username WHERE Users.username=?");
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
		String username = rs.getString(1);
		String firstname = rs.getString(2);
		String lastname = rs.getString(3);
		String email = rs.getString(4);
		String affiliation = rs.getString(5);
		String passwordHash = rs.getString(6);
		String passwordSalt = rs.getString(7);
		java.util.Date created = new java.util.Date(rs.getDate(8).getTime());
		java.util.Date lastModified = new java.util.Date(rs.getDate(9).getTime());
		List<PublicKey> publicKeys = getPublicKeysFromResultSet(rs);	
		return new User(username, firstname, lastname, email, affiliation, passwordHash, passwordSalt, created, lastModified, publicKeys);
	}

  private List<PublicKey> getPublicKeysFromResultSet(ResultSet rs) throws SQLException {
    ArrayList<PublicKey> keys = new ArrayList<>();    
		String key = rs.getString(11);	
		String description = rs.getString(10);
		java.util.Date created;
    if(key != null){     
      created = new java.util.Date(rs.getDate(12).getTime());
      keys.add(new PublicKey(key, description, created));
    }
    while(rs.next()){  
      description = rs.getString(10);
      key = rs.getString(11);
      created = new java.util.Date(rs.getDate(12).getTime());  
      keys.add(new PublicKey(key, description, created));
    } 
    return keys;
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
	    throw new DatabaseException(e.getMessage());
	  }
	}	

}
