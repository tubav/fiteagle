package org.fiteagle.interactors.usermanagement;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.fiteagle.core.userdatabase.UserPersistable.DatabaseException;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicateUsernameException;
import org.fiteagle.core.userdatabase.UserPersistable.InValidAttributeException;
import org.fiteagle.core.userdatabase.UserPersistable.NotEnoughAttributesException;
import org.fiteagle.core.userdatabase.UserPersistable.RecordNotFoundException;
import org.fiteagle.interactors.api.UserManagerBoundary;

public class UserManager implements UserManagerBoundary{

  private  UserDBManager manager;
  
  public UserManager(){
    manager = UserDBManager.getInstance();
  }
  
  @Override
  public void add(User u) throws DuplicateUsernameException, DatabaseException, NotEnoughAttributesException,
      InValidAttributeException {
    manager.add(u);    
  }

  @Override
  public void delete(String username) throws DatabaseException {
    manager.delete(username);
  }

  @Override
  public void delete(User u) throws DatabaseException {
    manager.delete(u);
  }

  @Override
  public void update(User u) throws RecordNotFoundException, DatabaseException, NotEnoughAttributesException,
      InValidAttributeException {
    manager.update(u);
  }

  @Override
  public void addKey(String username, String key) throws RecordNotFoundException, DatabaseException,
      InValidAttributeException {
    manager.addKey(username, key);
  }

  public void deleteKey(String username, String key) throws RecordNotFoundException, DatabaseException,
  InValidAttributeException {
    manager.deleteKey(username, key);
  }
  
  @Override
  public User get(String username) throws RecordNotFoundException, DatabaseException {
    return manager.get(username);
  }

  @Override
  public User get(User u) throws RecordNotFoundException, DatabaseException {
    return manager.get(u);
  }

  @Override
  public User getUserFromCert(X509Certificate userCert) {
   return manager.getUserFromCert(userCert);
  }

  @Override
  public boolean verifyPassword(String password, String passwordHash, String passwordSalt) throws IOException,
      NoSuchAlgorithmException {
   return manager.verifyPassword(password, passwordHash, passwordSalt);
  }

  @Override
  public boolean verifyCredentials(String username, String password) throws NoSuchAlgorithmException, IOException,
      RecordNotFoundException, DatabaseException {
    return manager.verifyCredentials(username, password);
  }

  @Override
  public String createUserPrivateKeyAndCertAsString(String username, String passphrase) throws Exception {
    return manager.createUserPrivateKeyAndCertAsString(username, passphrase);
  }

  @Override
  public String createUserCertificate(String uid, String publicKeyEncoded) throws Exception {
    return manager.createUserCertificate(uid, publicKeyEncoded);
  }
  
}
