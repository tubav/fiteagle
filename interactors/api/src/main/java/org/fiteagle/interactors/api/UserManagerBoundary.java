package org.fiteagle.interactors.api;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.fiteagle.core.userdatabase.JPAUserDB.DuplicateEmailException;
import org.fiteagle.core.userdatabase.JPAUserDB.DuplicatePublicKeyException;
import org.fiteagle.core.userdatabase.JPAUserDB.DuplicateUsernameException;
import org.fiteagle.core.userdatabase.JPAUserDB.UserNotFoundException;
import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.User.InValidAttributeException;
import org.fiteagle.core.userdatabase.User.NotEnoughAttributesException;
import org.fiteagle.core.userdatabase.User.PublicKeyNotFoundException;
import org.fiteagle.core.userdatabase.UserPublicKey;

public interface UserManagerBoundary {
  
  public abstract void add(User u) throws DuplicateUsernameException, DuplicateEmailException, DatabaseException, User.NotEnoughAttributesException,
      User.InValidAttributeException, DuplicatePublicKeyException;
  
  public abstract void delete(String username) throws DatabaseException;
  
  public abstract void delete(User u) throws DatabaseException;
  
  public abstract void update(String username, String firstName, String lastName, String email, String affiliation, String password, List<UserPublicKey> publicKeys) throws UserNotFoundException, DuplicateEmailException, DatabaseException, User.NotEnoughAttributesException,
      User.InValidAttributeException, DuplicatePublicKeyException;
  
  public abstract void addKey(String username, UserPublicKey key) throws UserNotFoundException, DatabaseException,
      User.InValidAttributeException, DuplicatePublicKeyException;
  
  public abstract void deleteKey(String username, String description) throws UserNotFoundException, DatabaseException;
  
  public void renameKey(String username, String description, String newDescription) throws UserNotFoundException, DatabaseException, DuplicatePublicKeyException, User.InValidAttributeException, PublicKeyNotFoundException;
  
  public abstract User get(String username) throws UserNotFoundException, DatabaseException;
  
  public abstract User get(User u) throws UserNotFoundException, DatabaseException;
  
  public abstract User getUserFromCert(X509Certificate userCert);
  
  public abstract boolean verifyPassword(String password, String passwordHash, String passwordSalt) throws IOException,
      NoSuchAlgorithmException;
  
  public abstract boolean verifyCredentials(String username, String password) throws NoSuchAlgorithmException,
      IOException, UserNotFoundException, DatabaseException;
  
  public abstract String createUserKeyPairAndCertificate(String username, String passphrase) throws Exception;
  
  public abstract String createUserCertificateForPublicKey(String uid, String description) throws Exception, PublicKeyNotFoundException;
  
}