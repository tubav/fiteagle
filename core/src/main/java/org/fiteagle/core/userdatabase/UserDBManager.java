package org.fiteagle.core.userdatabase;

import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.sql.SQLException;
import java.util.Arrays;

import net.iharder.Base64;

import org.fiteagle.core.aaa.CertificateAuthority;
import org.fiteagle.core.aaa.KeyManagement;
import org.fiteagle.core.aaa.x509.X509Util;
import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.fiteagle.core.userdatabase.UserPersistable.DatabaseException;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicateUsernameException;
import org.fiteagle.core.userdatabase.UserPersistable.InValidAttributeException;
import org.fiteagle.core.userdatabase.UserPersistable.NotEnoughAttributesException;
import org.fiteagle.core.userdatabase.UserPersistable.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDBManager {
  
  private UserPersistable database;
  private FiteaglePreferences preferences = new FiteaglePreferencesXML(this.getClass());
  
  private static enum databaseType {
    InMemory, SQLite
  }
  
  private static final String DEFAULT_DATABASE_TYPE = databaseType.InMemory.name();
  private static UserDBManager dbManager = null;
  static Logger log = LoggerFactory.getLogger(UserDBManager.class);
  KeyManagement keyManager = null;
  
  public static UserDBManager getInstance(){
    if(dbManager == null)
      try {
        dbManager =  new UserDBManager();
      } catch (DatabaseException | SQLException e) {
        log.error(e.getMessage(),e);
      }
    return dbManager;
  } 
  
  private UserDBManager() throws DatabaseException, SQLException {
    keyManager = KeyManagement.getInstance();
    if (preferences.get("databaseType") == null) {
      preferences.put("databaseType", DEFAULT_DATABASE_TYPE);
    }
    if (preferences.get("databaseType").equals(databaseType.SQLite.name())) {
      database = new SQLiteUserDB();
    } else {
      database = new InMemoryUserDB();
      addDefaultUser();
    }
  }

  private void addDefaultUser() {
    try {
      String key = "AAAAB3NzaC1yc2EAAAADAQABAAABAQCfnqNWBGSZGoxfUvBkbyGFs7ON4+UcA/pH9TTV9j0h9W0DltfbTuRoY/DhPsmycdv87m1EI1rJaeYAwRdzKvlth+Jc0r8IWVh4ihhqKFZZAUeKxz1xTlhWEUziThAbg1xjnlZ+iOh0kQDdxBjUYfOFPFTYUIwPa0zZeZQ651dk3jKJ4JVECfNcbTFB6forCmAZz1v2vtuwJ/Xm111xrlrzWBCU6swg3WsgjWU4wmSRd5qWCzjaV7kCdPr80PLvxJRzDbGeVUM1qGiG9FOVKxw4Mv9BueK/dpUMO+2Z/p1VABhgdLH379bT/BV5oV60p5E6aLrZFdPmw5Os9gs8+9v/";
      User u = new User("fiteagle.av.test", "test", "testUser", "test@test.org", "test");
      u.addPublicKey(key);
      add(u);
    } catch (DuplicateUsernameException e) {
      
    } catch (NoSuchAlgorithmException e) {
      log.error(e.getMessage());
    }
  }
  
  public void add(User u) throws DuplicateUsernameException, DatabaseException, NotEnoughAttributesException, InValidAttributeException {
    database.add(u);
  }
  
  public void delete(String username) throws DatabaseException {
    database.delete(username);
  }
  
  public void delete(User u) throws DatabaseException {
    database.delete(u);
  }
  
  public void update(User u) throws RecordNotFoundException, DatabaseException, NotEnoughAttributesException, InValidAttributeException {
    database.update(u);
  }
  
  public void addKey(String username, String key) throws RecordNotFoundException, DatabaseException, InValidAttributeException {
    database.addKey(username, key);
  }
  
  public User get(String username) throws RecordNotFoundException, DatabaseException {
    return database.get(username);
  }
  
  public User get(User u) throws RecordNotFoundException, DatabaseException {
    return database.get(u);
  }
  
  public User getUserFromCert(X509Certificate userCert) {
    try {
      String username = "";
      username = X509Util.getUserNameFromX509Certificate(userCert);
      
      User identifiedUser = get(username);
      return identifiedUser;
    } catch (CertificateParsingException e1) {
      throw new RuntimeException(e1);
    }
  }

    
  public boolean verifyPassword(String password, String passwordHash, String passwordSalt) throws IOException,
      NoSuchAlgorithmException {
    byte[] passwordHashBytes = Base64.decode(passwordHash);
    byte[] passwordSaltBytes = Base64.decode(passwordSalt);
    byte[] proposedDigest = createHash(passwordSaltBytes, password);
    return Arrays.equals(passwordHashBytes, proposedDigest);
  }
  
  public boolean verifyCredentials(String username, String password) throws NoSuchAlgorithmException, IOException, RecordNotFoundException, DatabaseException{
    User user = get(username);
    return verifyPassword(password, user.getPasswordHash(), user.getPasswordSalt());
  }

  private String createUserCertificate(String username, PublicKey publicKey) throws Exception {
    User u = get(username);
    CertificateAuthority ca = CertificateAuthority.getInstance();
    X509Certificate cert = ca.createCertificate(u, publicKey);
    return X509Util.getCertficateEncoded(cert);
  }
    
  private byte[] createHash(byte[] salt, String password) throws NoSuchAlgorithmException {
    
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    digest.reset();
    digest.update(salt);
    return digest.digest(password.getBytes());
  }
  
  public String createUserPrivateKeyAndCertAsString(String username, String passphrase) throws Exception {
    String returnString = "";
   
    KeyPair keypair = keyManager.generateKeyPair();
    String privateKeyEncoded =  keyManager.encryptPrivateKey(keypair.getPrivate(), passphrase);
    String pubKeyEncoded = keyManager.encodePublicKey(keypair.getPublic());
    addKey(username, pubKeyEncoded);
    String userCertString = createUserCertificate(username,keypair.getPublic()); 
    returnString = privateKeyEncoded + "\n" + userCertString;
   
    return returnString;
  }

  public String createUserCertificate(String uid, String publicKeyEncoded) throws Exception {
    String returnString = "";
    PublicKey pkey =  keyManager.decodePublicKey(publicKeyEncoded);
    addKey(uid, publicKeyEncoded);
    returnString = createUserCertificate(returnString, pkey);
    return returnString;
  }

  

}
