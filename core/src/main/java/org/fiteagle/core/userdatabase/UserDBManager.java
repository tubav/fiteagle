package org.fiteagle.core.userdatabase;

import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.security.auth.x500.X500Principal;

import net.iharder.Base64;

import org.fiteagle.core.aaa.KeyManagement;
import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.fiteagle.core.userdatabase.UserDB.DuplicateUIDException;
import org.fiteagle.core.userdatabase.UserDB.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDBManager {
  
  private UserDB database;
  private FiteaglePreferences preferences = new FiteaglePreferencesXML(this.getClass());
  
  private static enum databaseType {InMemory, SQLite} 
  private static final String DEFAULT_DATABASE_TYPE = databaseType.InMemory.name();
  Logger log = LoggerFactory.getLogger(getClass());
  public UserDBManager() throws SQLException{
    if(preferences.get("databaseType") == null){
      preferences.put("databaseType", DEFAULT_DATABASE_TYPE);
    }
    if(preferences.get("databaseType").equals(databaseType.SQLite.name())){
      database = new SQLiteUserDB();
    }
    else{
      database = new InMemoryUserDB();
      try {
        
        User u = createUser("fiteagle.av.test","test","testUser","test");
        add(u);
      } catch (DuplicateUIDException | NoSuchAlgorithmException | IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
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
  
  public void addKey(String UID, String key) throws RecordNotFoundException, SQLException{
    database.addKey(UID, key);
  }
  
  public User get(String UID) throws RecordNotFoundException, SQLException{
    return database.get(UID);
  }
  
  public User get(User u) throws RecordNotFoundException, SQLException{
    return database.get(u);
  }
  
  public User getUserFromCert(X509Certificate userCert) {
   try {
    String username = "";
    Collection<List<?>> alternativeNames =  userCert.getSubjectAlternativeNames();
    if(alternativeNames == null){
      X500Principal prince = userCert.getSubjectX500Principal();
      username = getCN(prince);
    }else{
      Iterator it = alternativeNames.iterator();
      while(it.hasNext()){
        List<?> altName = (List<?>) it.next();
        if(altName.get(0).equals(Integer.valueOf(0))){
          username = new String((byte[])altName.get(1));
        }
      }
    }
      
    User identifiedUser = get(username);
    return identifiedUser;
   } catch (CertificateParsingException e1) {
    
   throw new NonParsableNamingFormat();
  }
   
    catch (SQLException e) {
      throw new DatabaseException();
    }
  }

  private String getCN(X500Principal prince) {
    String uuid = prince.getName();
    LdapName ldapDN = getLdapName(uuid);
   
    for(Rdn rdn: ldapDN.getRdns()) {
        if(rdn.getType().equals("CN")){
          return (String) rdn.getValue();
        }
    }
    throw new NonParsableNamingFormat();
  }

  private LdapName getLdapName(String uuid) {
    try {
      LdapName ldapDN = new LdapName(uuid);
      return ldapDN;
    } catch (InvalidNameException e) {
  
       throw new NonParsableNamingFormat();
    }
   
  }
  
  
  public User createUser(String uuid, String firstName, String lastName,String password) throws DuplicateUIDException, SQLException, NoSuchAlgorithmException, IOException{
    
   
    SecureRandom random = new SecureRandom();
    byte[] salt = random.generateSeed(20);
    String passwordSalt = Base64.encodeBytes(salt);
   
    byte[] passwordBytes = createHash(salt, password);
    String passwordHash =  Base64.encodeBytes(passwordBytes);
    KeyManagement keyManagement = new KeyManagement();
    KeyPair keypair =keyManagement.generateKeyPair();
    PublicKey pubKey =  keypair.getPublic();
    List<String> keys = new ArrayList<>();
    keys.add(keyManagement.encodePublicKey(pubKey));
    return new User(uuid, firstName, lastName,passwordHash,passwordSalt,keys );
   
  }
  public User createUser(String uuid, String firstName, String lastName,String password, List<String> keys) throws DuplicateUIDException, NoSuchAlgorithmException, SQLException, IOException{
    User u = createUser(uuid, firstName, lastName, password);
    for(String key:keys){
      u.addPublicKey(key);
    }
    return u;
  }
    
  public boolean verifyPassword(String password, String passwordHash, String passwordSalt) throws IOException, NoSuchAlgorithmException {
    byte[] passwordHashBytes = Base64.decode(passwordHash);
    byte[] passwordSaltBytes = Base64.decode(passwordSalt);
    byte[] proposedDigest = createHash(passwordSaltBytes, password);
    return Arrays.equals(passwordHashBytes, proposedDigest);
  }

 
  public String getOwnerURN(User u) {
    
    String[] split = u.getUID().split("\\.");
    String user = split[split.length-1];
    String returnString = "urn:publicid:IDN"; 
    String domain = "";
    for(int i = 0; i< split.length-1;i++){
      if(i>0){
        domain+= ":"+split[i];
      }
      else{
        domain+=split[i];
      }
    }
    
    String plus = domain.length()>0 ? "+" :"";
    returnString = returnString + plus + domain + "+user+" +user;
    return returnString;
  }
  
  private byte[] createHash(byte[] salt,String password) throws NoSuchAlgorithmException{
    MessageDigest  digest = MessageDigest.getInstance("SHA-256");
    digest.reset();
    digest.update(salt);
    return digest.digest(password.getBytes());
  }

 
  
  private class NonParsableNamingFormat extends RuntimeException{
    
    private static final long serialVersionUID = -3819932831236493248L;
    
  }
  
  public class DatabaseException extends RuntimeException {

    private static final long serialVersionUID = -8002909402748409082L;
    
  }

 

 

 
}
