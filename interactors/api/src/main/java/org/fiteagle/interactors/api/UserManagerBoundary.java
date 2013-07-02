package org.fiteagle.interactors.api;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserPersistable.DatabaseException;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicatePublicKeyException;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicateUsernameException;
import org.fiteagle.core.userdatabase.UserPersistable.InValidAttributeException;
import org.fiteagle.core.userdatabase.UserPersistable.NotEnoughAttributesException;
import org.fiteagle.core.userdatabase.UserPersistable.RecordNotFoundException;

public interface UserManagerBoundary {
  
  public abstract void add(User u) throws DuplicateUsernameException, DatabaseException, NotEnoughAttributesException,
      InValidAttributeException;
  
  public abstract void delete(String username) throws DatabaseException;
  
  public abstract void delete(User u) throws DatabaseException;
  
  public abstract void update(User u) throws RecordNotFoundException, DatabaseException, NotEnoughAttributesException,
      InValidAttributeException;
  
  public abstract void addKey(String username, String key) throws RecordNotFoundException, DatabaseException,
      InValidAttributeException, DuplicatePublicKeyException;
  
  public abstract void deleteKey(String username, String key) throws RecordNotFoundException, DatabaseException,
      InValidAttributeException;
  
  public abstract User get(String username) throws RecordNotFoundException, DatabaseException;
  
  public abstract User get(User u) throws RecordNotFoundException, DatabaseException;
  
  public abstract User getUserFromCert(X509Certificate userCert);
  
  public abstract boolean verifyPassword(String password, String passwordHash, String passwordSalt) throws IOException,
      NoSuchAlgorithmException;
  
  public abstract boolean verifyCredentials(String username, String password) throws NoSuchAlgorithmException,
      IOException, RecordNotFoundException, DatabaseException;
  
  public abstract String createUserPrivateKeyAndCertAsString(String username, String passphrase) throws Exception;
  
  public abstract String createUserCertificate(String uid, String publicKeyEncoded) throws Exception;
  
}