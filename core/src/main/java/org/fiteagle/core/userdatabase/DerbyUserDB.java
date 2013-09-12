package org.fiteagle.core.userdatabase;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DerbyUserDB implements UserPersistable {
  
 
  
  private  final String DEFAULT_DATABASE_PATH = System.getProperty("user.home")+"/.fiteagle/db/";
  
  private static Logger log = LoggerFactory.getLogger(DerbyUserDB.class);
  
  private FiteaglePreferences preferences = new FiteaglePreferencesXML(DerbyUserDB.class);  
  
  static {
    try {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {        
        log.error(e.getMessage(),e);
    }
  }
  
  private static DerbyUserDB userdb;
  private static Connection connection;
  
  public static DerbyUserDB getInstance() throws DatabaseException{
    if(userdb == null){
      try{
        userdb = new DerbyUserDB();
      } catch (SQLException e){
        throw new DatabaseException(e.getMessage());
      }
    }
    return userdb;
  }
  
  private DerbyUserDB() throws SQLException {    
    createTable("CREATE TABLE Users (username VARCHAR(200) NOT NULL, " +
                                           "firstName VARCHAR(200), " +
                                           "lastName VARCHAR(200), " +
                                           "email VARCHAR(200), " +
                                           "affiliation VARCHAR(200), " +
                                           "passwordHash VARCHAR(200), " +
                                           "passwordSalt VARCHAR(200), " +
                                           "created TIMESTAMP, " +
                                           "lastModified TIMESTAMP, " +
                                           "CONSTRAINT primary_key_username PRIMARY KEY (username), " +
                                           "CONSTRAINT unique_email UNIQUE (email))");
    createTable("CREATE TABLE Keys (username VARCHAR(200) NOT NULL, " +
        "description VARCHAR(200) NOT NULL, " +
        "publicKey VARCHAR(1000) NOT NULL, " +
        "created TIMESTAMP, " +
        "CONSTRAINT primary_keys_username_and_publickey PRIMARY KEY (username, publicKey), " +
        "CONSTRAINT unique_username_and_description UNIQUE (username, description))");
  }
  
  protected void createTable(String command) throws SQLException{
    connection = getConnection();
    Statement st = connection.createStatement();
    try{
      st.executeUpdate(command);           
    } catch (SQLException e){
      if(!e.getSQLState().equals("X0Y32")){
        st.close();
        connection.commit();
        connection.close();
        log.error(e.getMessage());
        throw new DatabaseException(e.getMessage()); 
      }           
    }
    st.close();
    connection.commit();
    connection.close();   
  }
  
  private Connection getConnection() throws SQLException{
    if(connection != null)
        connection.close();    
    connection = DriverManager.getConnection("jdbc:derby:" + getDatabasePath() + ";create=true");
    return connection;
  }
  
  private String getDatabasePath() {
    if(preferences.get("database_path") == null)
      preferences.put("database_path", DEFAULT_DATABASE_PATH);    
    String path = preferences.get("database_path");
    File dir = new File(path);
    dir.mkdirs();
    return path + "fiteagledatabase.db";
  }
  
  private void executeSQLString(String SQLString, List<Object> params) throws SQLException {
    Connection connection = getConnection();
    PreparedStatement ps = connection.prepareStatement(SQLString);
    for(int i = 0; i < params.size(); i++){
      ps.setObject(i+1, params.get(i));
    }
    ps.execute();
    
    connection.commit();
    ps.close();
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
  
  @Override
  public void add(User u) throws DuplicateUsernameException, DatabaseException, NotEnoughAttributesException,
      InValidAttributeException, DuplicatePublicKeyException, DuplicateEmailException {
    try{
      addUserToDatabase(u);   
      addKeysToDatabase(u.getUsername(),u.getPublicKeys());
    } catch(SQLException e){
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
    params.add(new java.sql.Timestamp(u.getCreated().getTime()));
    params.add(new java.sql.Timestamp(u.getLast_modified().getTime()));
    try{
      executeSQLString("INSERT INTO Users VALUES (?,?,?,?,?,?,?,?,?)", params);    
    } catch(SQLException e){
      if(e.getSQLState().equals("23505") && e.getMessage().contains("'PRIMARY_KEY_USERNAME' defined on 'USERS'")){
        throw new DuplicateUsernameException();
      }
      if(e.getSQLState().equals("23505") && e.getMessage().contains("'UNIQUE_EMAIL' defined on 'USERS'")){
        throw new DuplicateEmailException();
      }
      throw e;
    }
  }  

  private void addKeysToDatabase(String username, List<UserPublicKey> keys) throws SQLException {      
    for(UserPublicKey key: keys){     
      try {
        addKeyToDatabase(username, key);
      } catch (IOException e) {
        throw new DatabaseException(e.getMessage());
      }        
    }       
  }

  private void addKeyToDatabase(String username, UserPublicKey key) throws SQLException, IOException {
    ArrayList<Object> params = new ArrayList<>();
    params.add(username);
    params.add(key.getDescription());
    params.add(key.getPublicKeyString());
    params.add(new java.sql.Timestamp(key.getCreated().getTime()));    
    try{
      executeSQLString("INSERT INTO Keys VALUES (?,?,?,?)", params);
    } catch(SQLException e){
      if(e.getSQLState().equals("23505")){
        throw new DuplicatePublicKeyException();
      }      
      throw e;      
    }     
  }
  
  private void deleteKeyFromDatabase(String username, String description) throws SQLException {
    ArrayList<Object> params = new ArrayList<>();
    params.add(username);
    params.add(description);
    executeSQLString("DELETE FROM Keys WHERE username=? AND description=?", params);   
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
    executeSQLString("DELETE FROM Keys WHERE username=?", params);    
  }

  private void deleteUserFromDatabase(String username) throws SQLException {
    ArrayList<Object> params = new ArrayList<>();
    params.add(username);
    executeSQLString("DELETE FROM Users WHERE username=?", params);  
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
    PreparedStatement ps = connection.prepareStatement("SELECT Users.username, Users.firstname, Users.lastname, Users.email, Users.affiliation, Users.passwordHash, Users.passwordSalt, Users.created, Users.lastModified, Keys.description, Keys.publicKey, Keys.created FROM Users LEFT OUTER JOIN Keys ON Users.username=Keys.username WHERE Users.username=?");
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
    java.util.Date created = new java.util.Date(rs.getTimestamp(8).getTime());
    java.util.Date lastModified = new java.util.Date(rs.getTimestamp(9).getTime());
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
    Timestamp created = rs.getTimestamp(12);    
    if(created != null){     
      keys.add(new UserPublicKey(key, description, new java.util.Date(created.getTime())));
    }
    while(rs.next()){  
      description = rs.getString(10);
      key = rs.getString(11);
      created = rs.getTimestamp(12);
      keys.add(new UserPublicKey(key, description, new java.util.Date(created.getTime())));
    } 
    return keys;
  }

  @Override
  public User get(User u) throws UserNotFoundException, DatabaseException {   
    return get(u.getUsername());
  }
  
  @Override
  public void update(String username, String firstName, String lastName, String email, String affiliation, String password, List<UserPublicKey> publicKeys) throws UserNotFoundException, DatabaseException, NotEnoughAttributesException, InValidAttributeException, DuplicatePublicKeyException {
    User user = get(username);
    user.update(firstName, lastName, email, affiliation, password, publicKeys);   
    try{      
      updateUserInDatabase(user);
      deleteKeysFromDatabase(user.getUsername());    
      addKeysToDatabase(user.getUsername(), user.getPublicKeys());     
    } catch(SQLException e){
      if(e.getSQLState().equals("23505")){
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
    params.add(new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
    params.add(u.getUsername());
    
    if(executeSQLUpdateString("UPDATE Users SET firstName=?, lastname=?, email=?, affiliation=?, passwordHash=?, passwordSalt=?, lastModified=? WHERE username=?", params) == 0){
      throw new UserNotFoundException();
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
      if(e.getSQLState().equals("23505")){
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
    
    if(executeSQLUpdateString("UPDATE Keys SET description=? WHERE username=? AND description = ?", params) == 0){
      throw new PublicKeyNotFoundException();
    }
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
