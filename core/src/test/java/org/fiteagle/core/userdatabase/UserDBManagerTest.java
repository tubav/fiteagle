package org.fiteagle.core.userdatabase;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import junit.framework.Assert;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.fiteagle.core.userdatabase.JPAUserDB.DuplicateEmailException;
import org.fiteagle.core.userdatabase.JPAUserDB.DuplicateUsernameException;
import org.fiteagle.core.userdatabase.JPAUserDB.UserNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDBManagerTest {
  
  UserDBManager userDBManager ;
  
  User testUser;  
  
  @Before
  public void setUp() throws Exception {
   
    userDBManager = UserDBManager.getInstance();
    testUser = new User("test1@localhost", "test", "testName", "test@test.org", "testAffiliation", "password", new ArrayList<UserPublicKey>());
    try{
      userDBManager.add(testUser);
    } catch(DuplicateUsernameException | DuplicateEmailException e){  
      userDBManager.delete(testUser);
      userDBManager.add(testUser);
    }
  }
    
  @Test
  public void testCreateUser() throws DuplicateUsernameException, NoSuchAlgorithmException, DatabaseException, IOException { 
    Assert.assertEquals("test1@localhost", testUser.getUsername());
    Assert.assertEquals("test", testUser.getFirstName());    
    Assert.assertEquals("testName", testUser.getLastName());
    Assert.assertEquals("test@test.org", testUser.getEmail());
    Assert.assertNotNull(testUser.getPasswordHash());
    Assert.assertNotNull(testUser.getPasswordSalt());
  }  
 
  @Test
  public void testVerifyPassword() throws DuplicateUsernameException, NoSuchAlgorithmException, DatabaseException, IOException{    
    Assert.assertTrue(userDBManager.verifyPassword("password",testUser.getPasswordHash(),testUser.getPasswordSalt()));    
  }
  
  @Test
  public void testVerifyCredentials() throws UserNotFoundException, DatabaseException, IOException, NoSuchAlgorithmException{    
    Assert.assertTrue(userDBManager.verifyCredentials(testUser.getUsername(), "password"));    
  } 
  
  @Test
  public void testCreateUserCertAndPrivateKey() throws Exception{
    Assert.assertTrue(userDBManager.createUserPrivateKeyAndCertAsString(testUser.getUsername(), "my passphrase").contains("ENCRYPTED"));
  }  
  
  @Test
  public void testCreateUserCertAndPrivateKeyWithoutPasshphrase() throws Exception{
    Assert.assertFalse(userDBManager.createUserPrivateKeyAndCertAsString(testUser.getUsername(), "").contains("ENCRYPTED"));
  }  
  
  @Test
  public void testCreateCertForPublicKey() throws Exception{
    UserPublicKey key = new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCarsTCyAf8gYXwei8rhJnLTqYI6P88weRaY5dW9j3DT4mvfQPna79Bjq+uH4drmjbTD2n3s3ytqupFfNko1F0+McstA2EEkO8pAo5NEPcreygUcB2d49So032GKGPETB8chRkDsaBCm/KKL2vXdQoicofli8JJRPK2kXfUW34qww==", "my first key");
    userDBManager.addKey(testUser.getUsername(), key);
    Assert.assertNotNull(userDBManager.createUserCertificateForPublicKey(testUser.getUsername(), key.getDescription()));
  }
  
  @After
  public void deleteTestUser(){
    userDBManager.delete(testUser);
  }
  
}
