package org.fiteagle.core.userdatabase;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.Date;
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

	@SuppressWarnings("unused")
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
	
	private SQLiteUserDB() throws DatabaseException, SQLException{
	  Connection connection = getConnection();
		try{	   
  		createTable(connection,"CREATE TABLE IF NOT EXISTS Users (username, firstName, lastName, email, affiliation, passwordHash, passwordSalt, created, lastModified , PRIMARY KEY (username), UNIQUE (email))");
  		createTable(connection,"CREATE TABLE IF NOT EXISTS Keys (username, description, key, created, PRIMARY KEY (username, key), UNIQUE (username, description))");  		
	  } catch(SQLException e){	    
	    throw new DatabaseException(e.getMessage());
	  }finally{
		  connection.close();
	  }
	}
	
	@Override
	public void add(User u) throws DuplicateUsernameException, DatabaseException, NotEnoughAttributesException, InValidAttributeException, DuplicatePublicKeyException {
	  try{
  		addUserToDatabase(u);		
  		addKeysToDatabase(u.getUsername(),u.getPublicKeys());
	  } catch(IOException | SQLException e){
	    throw new DatabaseException(e.getMessage());
	  }
	}

	private void addUserToDatabase(User u) throws SQLException {
    ArrayList<Object> params = new ArrayList<>();
    params.add(u.getUsername());
    params.add(u.getFirstName());
    params.add(u.getLastName());
    params.add(u.getEmail());
    params.add(u.getAffiliation());
    params.add(u.getPasswordHash());
    params.add(u.getPasswordSalt());
    params.add(new java.sql.Date(u.getCreated().getTime()));
    params.add(new java.sql.Date(u.getLast_modified().getTime()));
    Connection connection = getConnection();
    try{
      executeSQLString(connection, "INSERT INTO Users VALUES (?,?,?,?,?,?,?,?,?)", params);    
    } catch(SQLException e){
      if(e.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (column username is not unique)")){
        throw new DuplicateUsernameException();
      }
      if(e.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (column email is not unique)")){
        throw new DuplicateEmailException();
      }
      throw e;
    }
    finally{
    	connection.close();
    }
  }
	
	private void addKeysToDatabase(String username, List<UserPublicKey> keys) throws SQLException, IOException {	 		
		for(UserPublicKey key: keys){		  
		  addKeyToDatabase(username, key);			  
		}				
	}

  private void addKeyToDatabase(String username, UserPublicKey key) throws SQLException, IOException {
    ArrayList<Object> params = new ArrayList<>();
    params.add(username);
    params.add(key.getDescription());
    params.add(key.getPublicKeyString());
    params.add(new java.sql.Date(key.getCreated().getTime()));    
    Connection connection = getConnection();
    try{
      executeSQLString(connection,"INSERT INTO Keys VALUES (?,?,?,?)", params);
    } catch(SQLException e){
      if(e.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (columns username, description are not unique)")
          || e.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (columns username, key are not unique)")){
        throw new DuplicatePublicKeyException();
      }      
      throw e;      
    }finally{
    	connection.close();
    }
  }
	
	private void deleteKeyFromDatabase(String username, String description) throws SQLException {
	  ArrayList<Object> params = new ArrayList<>();
	  params.add(username);
	  params.add(description);
	  Connection connection = getConnection();
	  try{
      executeSQLString(connection, "DELETE FROM Keys WHERE username=? AND description=?", params);   
	  }finally{
		  connection.close();
	  }
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
	  ArrayList<Object> params = new ArrayList<>();
    params.add(username);
    Connection connection = getConnection();
    try{
		executeSQLString(connection,"DELETE FROM Keys WHERE username=?", params);		
	}finally{
		connection.close();
	}
	}

	private void deleteUserFromDatabase(String username) throws SQLException {
	  ArrayList<Object> params = new ArrayList<>();
    params.add(username);
    Connection connection = getConnection();
    try{
	  executeSQLString(connection,"DELETE FROM Users WHERE username=?", params);	 
	}finally{
		connection.close();
	}
	}

	@Override
	public void update(String username, String firstName, String lastName, String email, String affiliation, String password, List<UserPublicKey> publicKeys) throws UserNotFoundException, DatabaseException, NotEnoughAttributesException, InValidAttributeException, DuplicatePublicKeyException {
	  User user = get(username);
    user.update(firstName, lastName, email, affiliation, password, publicKeys);	  
	  try{	    
	    updateUserInDatabase(user);
	    deleteKeysFromDatabase(user.getUsername());    
	    addKeysToDatabase(user.getUsername(), user.getPublicKeys());	   
	  } catch(IOException | SQLException e){
	    if(e.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (column email is not unique)")){
        throw new DuplicateEmailException();
      }
      else{
        throw new DatabaseException(e.getMessage());
      }	   
	  }		
	}

	private void updateUserInDatabase(User u) throws SQLException {
	  ArrayList<Object> params = new ArrayList<>();
	  params.add(u.getFirstName());
    params.add(u.getLastName());
    params.add(u.getEmail());
    params.add(u.getAffiliation());
    params.add(u.getPasswordHash());
    params.add(u.getPasswordSalt());    
    params.add(new java.sql.Date(java.util.Calendar.getInstance().getTimeInMillis()));
    params.add(u.getUsername());
    Connection connection = getConnection();
    try{
		if(executeSQLUpdateString(connection,"UPDATE Users SET firstName=?, lastname=?, email=?, affiliation=?, passwordHash=?, passwordSalt=?, lastModified=? WHERE username=?", params) == 0){
			throw new UserNotFoundException();
		}		
    }finally{
    	connection.close();
    }
	}

	@Override
	public void addKey(String username, UserPublicKey key) throws UserNotFoundException, DatabaseException, InValidAttributeException, DuplicatePublicKeyException {
	  get(username);	  
	  try{  		
  		addKeyToDatabase(username,key);  		
	  } catch(IOException | SQLException e){
	    throw new DatabaseException(e.getMessage());
	  }
	}
	
  @Override
  public void deleteKey(String username, String description) throws UserNotFoundException, DatabaseException {
    get(username);    
    try{
      deleteKeyFromDatabase(username, description);
    } catch(SQLException e){
      throw new DatabaseException(e.getMessage());
    }    
  }
  
  @Override
  public void renameKey(String username, String description, String newDescription) throws UserNotFoundException, DatabaseException, DuplicatePublicKeyException, InValidAttributeException, PublicKeyNotFoundException {
    get(username);    
    
    try {
      renameKeyInDatabase(username, description, newDescription);
    } catch (SQLException e) {
      if(e.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (columns username, description are not unique)")){
        throw new DuplicatePublicKeyException();
      }
      throw new DatabaseException(e.getMessage());
    }   
  }

  private void renameKeyInDatabase(String username, String description, String newDescription) throws SQLException {
    ArrayList<Object> params = new ArrayList<>();
    params.add(newDescription);
    params.add(username);
    params.add(description);
    Connection connection = getConnection();
    try{
    if(executeSQLUpdateString(connection,"UPDATE Keys SET description=? WHERE username=? AND description = ?", params) == 0){
      throw new PublicKeyNotFoundException();
    }
    }finally{
    	connection.close();
    }
  } 
	
	@Override
	public User get(String username) throws UserNotFoundException, DatabaseException {		
    try {
      return getUserFromDatabase(username);
    } catch (SQLException e) {
      throw new DatabaseException(e.getMessage());
    }
	}
	
	private User getUserFromDatabase(String username) throws SQLException {
		Connection connection = getConnection();
	  PreparedStatement ps = connection.prepareStatement("SELECT Users.username, Users.firstname, Users.lastname, Users.email, Users.affiliation, Users.passwordHash, Users.passwordSalt, Users.created, Users.lastModified, Keys.description, Keys.key, Keys.created FROM Users LEFT OUTER JOIN Keys ON Users.username=Keys.username WHERE Users.username=?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();	
		try{
  		if(rs.next()){
  			return evaluateResultSet(rs);			
  		}
  		else{		  
  		  throw new UserNotFoundException();
  		}
		} finally{
		  ps.close();
      connection.commit();
      connection.close();      
		}		
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
		List<UserPublicKey> publicKeys;
    try {
      publicKeys = getPublicKeysFromResultSet(rs);
    } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
      throw new DatabaseException(e.getMessage());
    }	

    	if(passwordHash!=null){
    		return User.createUserWithExistingPassword(username, firstname, lastname, email, affiliation, passwordHash, passwordSalt, created, lastModified, publicKeys);
    	}else{
    		User u =  User.createUser(username);
    		u.setPublicKeys(publicKeys);
    		return u;
    	}
	}

  private List<UserPublicKey> getPublicKeysFromResultSet(ResultSet rs) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, IOException {
    ArrayList<UserPublicKey> keys = new ArrayList<>();    
    String description = rs.getString(10);
    String key = rs.getString(11);		
    Date created = rs.getDate(12);		
    if(created != null){     
      keys.add(new UserPublicKey(key, description, new java.util.Date(created.getTime())));
    }
    while(rs.next()){  
      description = rs.getString(10);
      key = rs.getString(11);
      created = rs.getDate(12);
      keys.add(new UserPublicKey(key, description, new java.util.Date(created.getTime())));
    } 
    return keys;
  }

	@Override
	public User get(User u) throws UserNotFoundException, DatabaseException {		
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
