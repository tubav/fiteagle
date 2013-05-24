package org.fiteagle.core.aaa;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import junit.framework.Assert;

import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserDB.DuplicateUIDException;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.junit.Before;
import org.junit.Test;

public class KeyManagementTest {
  UserDBManager userDBManager;
  KeyManagement keyManagement;
  @Before
  public void setUp() throws Exception {
    userDBManager = new UserDBManager();
    keyManagement = new KeyManagement();
  }
  
  
  @Test
  public void testGetEncyrptedPrivateKey() throws DuplicateUIDException, NoSuchAlgorithmException, SQLException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
    User u = userDBManager.createUser("test", "test", "testName", "password");
    String encryptedPrivateKey = keyManagement.getEncryptedPrivateKey(u, "password");
    Assert.assertNotNull(encryptedPrivateKey);
    
  }
  
  @Test 
  public void testVerifyEncryptedPrivateKey() throws DuplicateUIDException, NoSuchAlgorithmException, SQLException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidAlgorithmParameterException{
    User u = userDBManager.createUser("test", "test", "testName", "password");
   
    String encryptedPrivateKey = keyManagement.getEncryptedPrivateKey(u, "password");
    Assert.assertFalse(keyManagement.verifyPrivateKey(encryptedPrivateKey, "Wrongpassword"));
    Assert.assertTrue(keyManagement.verifyPrivateKey(encryptedPrivateKey, "password"));
    
  }
}
