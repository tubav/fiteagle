package org.fiteagle.core.userdatabase;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import junit.framework.Assert;

import org.fiteagle.core.userdatabase.UserDB.DatabaseException;
import org.fiteagle.core.userdatabase.UserDB.DuplicateUsernameException;
import org.junit.Before;
import org.junit.Test;

public class UserDBManagerTest {
  
  UserDBManager userDBManager ;
  
  @Before
  public void setUp() throws Exception {
    userDBManager = UserDBManager.getInstance();
  }
  
  @Test
  public void testCreateUser() throws DuplicateUsernameException, NoSuchAlgorithmException, DatabaseException, IOException {
    User u = userDBManager.createUser("test", "test", "testName", "test@test.org", "password");
 
    Assert.assertEquals("test", u.getFirstName());
    Assert.assertEquals("test", u.getUsername());
    Assert.assertEquals("testName", u.getLastName());
    Assert.assertEquals("test@test.org", u.getEmail());
    Assert.assertNotNull(u.getPasswordHash());
    Assert.assertNotNull(u.getPasswordSalt());
  }  
 
  public void testVerifyPassword() throws DuplicateUsernameException, NoSuchAlgorithmException, DatabaseException, IOException{
    User u = userDBManager.createUser("test", "test", "testName", "test@test.org", "password", new ArrayList<String>());
    Assert.assertTrue(userDBManager.verifyPassword("password",u.getPasswordHash(),u.getPasswordSalt()));    
  }
  
 
}
