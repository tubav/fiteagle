package org.fiteagle.core.userdatabase;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
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

import org.fiteagle.core.aaa.CertificateAuthority;
import org.fiteagle.core.aaa.KeyManagement;
import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.fiteagle.core.userdatabase.UserDB.DatabaseException;
import org.fiteagle.core.userdatabase.UserDB.DuplicateUIDException;
import org.fiteagle.core.userdatabase.UserDB.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDBManager {
  
  private UserDB database;
  private FiteaglePreferences preferences = new FiteaglePreferencesXML(this.getClass());
  
  private static enum databaseType {
    InMemory, SQLite
  }
  
  private static final String DEFAULT_DATABASE_TYPE = databaseType.InMemory.name();
  private static UserDBManager dbManager = null;
  Logger log = LoggerFactory.getLogger(getClass());
  KeyManagement keyManager = null;
  public static UserDBManager getInstance(){
    if(dbManager == null)
        dbManager =  new UserDBManager();
    return dbManager;
  } 
  
  private UserDBManager() throws DatabaseException {
    keyManager = KeyManagement.getInstance();
    if (preferences.get("databaseType") == null) {
      preferences.put("databaseType", DEFAULT_DATABASE_TYPE);
    }
    if (preferences.get("databaseType").equals(databaseType.SQLite.name())) {
      database = new SQLiteUserDB();
    } else {
      database = new InMemoryUserDB();
      try {
        String key = "AAAAB3NzaC1yc2EAAAADAQABAAABAQCfnqNWBGSZGoxfUvBkbyGFs7ON4+UcA/pH9TTV9j0h9W0DltfbTuRoY/DhPsmycdv87m1EI1rJaeYAwRdzKvlth+Jc0r8IWVh4ihhqKFZZAUeKxz1xTlhWEUziThAbg1xjnlZ+iOh0kQDdxBjUYfOFPFTYUIwPa0zZeZQ651dk3jKJ4JVECfNcbTFB6forCmAZz1v2vtuwJ/Xm111xrlrzWBCU6swg3WsgjWU4wmSRd5qWCzjaV7kCdPr80PLvxJRzDbGeVUM1qGiG9FOVKxw4Mv9BueK/dpUMO+2Z/p1VABhgdLH379bT/BV5oV60p5E6aLrZFdPmw5Os9gs8+9v/";
        User u = createUser("fiteagle.av.test", "test", "testUser", "test");
        u.addPublicKey(key);
        add(u);
      } catch (DuplicateUIDException | NoSuchAlgorithmException | IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  public void add(User u) throws DuplicateUIDException, DatabaseException {
    database.add(u);
  }
  
  public void delete(String UID) throws DatabaseException {
    database.delete(UID);
  }
  
  public void delete(User u) throws DatabaseException {
    database.delete(u);
  }
  
  public void update(User u) throws RecordNotFoundException, DatabaseException {
    database.update(u);
  }
  
  public void addKey(String UID, String key) throws RecordNotFoundException, DatabaseException {
    database.addKey(UID, key);
  }
  
  public User get(String UID) throws RecordNotFoundException, DatabaseException {
    return database.get(UID);
  }
  
  public User get(User u) throws RecordNotFoundException, DatabaseException {
    return database.get(u);
  }
  
  public User getUserFromCert(X509Certificate userCert) {
    try {
      String username = "";
      Collection<List<?>> alternativeNames = userCert.getSubjectAlternativeNames();
      if (alternativeNames == null) {
        X500Principal prince = userCert.getSubjectX500Principal();
        username = getCN(prince);
      } else {
        Iterator<List<?>> it = alternativeNames.iterator();
        while (it.hasNext()) {
          List<?> altName = it.next();
          if (altName.get(0).equals(Integer.valueOf(6))) {
            username = (String) altName.get(1);
            username = getUIDFromURN(username);
          }
        }
      }
      
      User identifiedUser = get(username);
      return identifiedUser;
    } catch (CertificateParsingException e1) {
      throw new NonParsableNamingFormat();
    }
  }
  
  private String getUIDFromURN(String urn) {
    String userFromURN = urn.substring(urn.lastIndexOf("+") + 1);
    System.out.println(userFromURN);
    String domain = urn.substring(urn.indexOf("IDN") + 4, urn.indexOf("+user+"));
    domain = domain.replace(":", ".");
    System.out.println(domain);
    return domain + "." + userFromURN;
  }
  
  private String getCN(X500Principal prince) {
    String uuid = prince.getName();
    LdapName ldapDN = getLdapName(uuid);
    
    for (Rdn rdn : ldapDN.getRdns()) {
      if (rdn.getType().equals("CN")) {
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
  
  public User createUser(String uuid, String firstName, String lastName, String password) throws NoSuchAlgorithmException, IOException {
    
    SecureRandom random = new SecureRandom();
    byte[] salt = random.generateSeed(20);
    String passwordSalt = Base64.encodeBytes(salt);
    
    byte[] passwordBytes = createHash(salt, password);
    String passwordHash = Base64.encodeBytes(passwordBytes);
    
    List<String> keys = new ArrayList<>();
    return new User(uuid, firstName, lastName, passwordHash, passwordSalt, keys);
    
  }
  
  public User createUser(String uuid, String firstName, String lastName, String password, List<String> keys)
      throws NoSuchAlgorithmException, IOException {
	SecureRandom random = new SecureRandom();
	byte[] salt = random.generateSeed(20);
	String passwordSalt = Base64.encodeBytes(salt);
	    
	byte[] passwordBytes = createHash(salt, password);
	String passwordHash = Base64.encodeBytes(passwordBytes);
	    
    return new User(uuid, firstName, lastName, passwordHash, passwordSalt, keys);
  }
    
  public boolean verifyPassword(String password, String passwordHash, String passwordSalt) throws IOException,
      NoSuchAlgorithmException {
    byte[] passwordHashBytes = Base64.decode(passwordHash);
    byte[] passwordSaltBytes = Base64.decode(passwordSalt);
    byte[] proposedDigest = createHash(passwordSaltBytes, password);
    return Arrays.equals(passwordHashBytes, proposedDigest);
  }
  
  public String getOwnerURN(User u) {
    
    String[] split = u.getUID().split("\\.");
    String user = split[split.length - 1];
    String returnString = "urn:publicid:IDN";
    String domain = "";
    for (int i = 0; i < split.length - 1; i++) {
      if (i > 0) {
        domain += ":" + split[i];
      } else {
        domain += split[i];
      }
    }
    
    String plus = domain.length() > 0 ? "+" : "";
    returnString = returnString + plus + domain + "+user+" + user;
    return returnString;
  }
  
  private String createUserCertificate(String uid, PublicKey publicKey) throws Exception {
    User u = get(uid);
    CertificateAuthority ca = CertificateAuthority.getInstance();
    X509Certificate cert = ca.createCertificate(u, publicKey);
    return ca.getCertficateEncoded(cert);
  }
  
  
  
  
  
  private byte[] createHash(byte[] salt, String password) throws NoSuchAlgorithmException {
    
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    digest.reset();
    digest.update(salt);
    return digest.digest(password.getBytes());
  }
  
  private class NonParsableNamingFormat extends RuntimeException {
    
    private static final long serialVersionUID = -3819932831236493248L;
    
  }

  
  public String createUserPrivateKeyAndCertAsString(String uID, String passphrase) throws Exception {
    String returnString = "";
   
    KeyPair keypair = keyManager.generateKeyPair();
    String privateKeyEncoded =  keyManager.encryptPrivateKey(keypair.getPrivate(), passphrase);
    String pubKeyEncoded = keyManager.encodePublicKey(keypair.getPublic());
    addKey(uID, pubKeyEncoded);
    String userCertString = createUserCertificate(uID,keypair.getPublic()); 
    returnString = privateKeyEncoded + "\n" + userCertString;
   
    return returnString;
  }

  
  

}
