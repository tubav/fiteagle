package org.fiteagle.core.userdatabase;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import junit.framework.Assert;

import org.fiteagle.core.userdatabase.UserDB.DuplicateUIDException;
import org.junit.Before;
import org.junit.Test;

public class UserDBManagerTest {
  UserDBManager userDBManager ;
  @Before
  public void setUp() throws Exception {
    userDBManager = new UserDBManager();
  }
  
  @Test
  public void testCreateUser() throws DuplicateUIDException, NoSuchAlgorithmException, SQLException, IOException {
    User u = userDBManager.createUser("test", "test", "testName", "password");
    Assert.assertEquals("test", u.getFirstName());
    Assert.assertEquals("test", u.getUID());
    Assert.assertEquals("testName", u.getLastName());
    Assert.assertNotNull(u.getPasswordHash());
    Assert.assertNotNull(u.getPasswordSalt());
  }
  
  @Test 
  public void testVerifyPassword() throws DuplicateUIDException, NoSuchAlgorithmException, SQLException, IOException{
    User  u = userDBManager.createUser("test", "test", "testName", "password");
    Assert.assertTrue(userDBManager.verifyPassword("password",u.getPasswordHash(),u.getPasswordSalt()));
    
  }
  
 
}
