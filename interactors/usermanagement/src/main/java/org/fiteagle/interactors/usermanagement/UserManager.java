package org.fiteagle.interactors.usermanagement;

import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.fiteagle.interactors.api.UserManagerBoundary;
import org.fiteagle.core.userdatabase.JPAUserDB.DuplicateEmailException;
import org.fiteagle.core.userdatabase.JPAUserDB.DuplicatePublicKeyException;
import org.fiteagle.core.userdatabase.JPAUserDB.DuplicateUsernameException;
import org.fiteagle.core.userdatabase.JPAUserDB.InValidAttributeException;
import org.fiteagle.core.userdatabase.JPAUserDB.NotEnoughAttributesException;
import org.fiteagle.core.userdatabase.JPAUserDB.UserNotFoundException;
import org.fiteagle.core.userdatabase.User.PublicKeyNotFoundException;
import org.fiteagle.core.userdatabase.UserPublicKey;

public class UserManager implements UserManagerBoundary{

  private UserDBManager manager;
  
  
  public static UserManager usermanager;
  
  public static UserManager getInstance() {
    if(usermanager == null){
      usermanager = new UserManager();
    }
    return usermanager;
  }
  
  private UserManager(){
    manager = UserDBManager.getInstance();
  }
  
  @Override
  public void add(User u) throws DuplicateUsernameException, DuplicateEmailException, DatabaseException, NotEnoughAttributesException,
      InValidAttributeException, DuplicatePublicKeyException {
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
  public void update(String username, String firstName, String lastName, String email, String affiliation, String password, List<UserPublicKey> publicKeys) throws UserNotFoundException, DuplicateEmailException, DatabaseException, NotEnoughAttributesException,
      InValidAttributeException, DuplicatePublicKeyException {
    manager.update(username, firstName, lastName, email, affiliation, password, publicKeys);
  }

  @Override
  public void addKey(String username, UserPublicKey key) throws UserNotFoundException, DatabaseException,
      InValidAttributeException, DuplicatePublicKeyException {
    manager.addKey(username, key);
  }

  public void deleteKey(String username, String description) throws UserNotFoundException, DatabaseException {
    manager.deleteKey(username, description);
  }
  
  @Override
  public User get(String username) throws UserNotFoundException, DatabaseException {
    return manager.get(username);
  }

  @Override
  public User get(User u) throws UserNotFoundException, DatabaseException {
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
      UserNotFoundException, DatabaseException {
    return manager.verifyCredentials(username, password);
  }

  @Override
  public String createUserPrivateKeyAndCertAsString(String username, String passphrase) throws Exception {
    return manager.createUserPrivateKeyAndCertAsString(username, passphrase);
  }

  @Override
  public String createUserCertificateForPublicKey(String uid, String description) throws Exception, PublicKeyNotFoundException {
    return manager.createUserCertificateForPublicKey(uid, description);
  }

  @Override
  public void renameKey(String username, String description, String newDescription) throws UserNotFoundException,
      DatabaseException, DuplicatePublicKeyException, InValidAttributeException, PublicKeyNotFoundException {
    manager.renameKey(username, description, newDescription);    
  }
  
}
