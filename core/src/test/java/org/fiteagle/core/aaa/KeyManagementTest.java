package org.fiteagle.core.aaa;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.sql.SQLException;

import junit.framework.Assert;

import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicateUsernameException;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.junit.Before;
import org.junit.Test;

public class KeyManagementTest {
  UserDBManager userDBManager;
  KeyManagement keyManagement;
  @Before
  public void setUp() throws Exception {
    userDBManager = UserDBManager.getInstance();
    keyManagement = KeyManagement.getInstance();
  }
  
  
  @Test
  public void testGetEncryptedPrivateKey() throws DuplicateUsernameException, SQLException, IOException, GeneralSecurityException{
    User u = new User("test", "test", "testName", "test@test.org", "password");
    CertificateAuthority cA = CertificateAuthority.getInstance();
    
  
    KeyPair keyPair = keyManagement.generateKeyPair();
    String encryptedPrivateKey = keyManagement.encryptPrivateKey(keyPair.getPrivate(), "password");
    System.out.println(encryptedPrivateKey);
    Assert.assertNotNull(encryptedPrivateKey);
    
  }
  
  //@Test 
  public void testVerifyEncryptedPrivateKey() throws DuplicateUsernameException, SQLException, IOException, GeneralSecurityException{
    User u = new User("test", "test", "testName", "test@test.org", "password");

    KeyPair keyPair = keyManagement.generateKeyPair();
    String encryptedPrivateKey = keyManagement.encryptPrivateKey(keyPair.getPrivate(), "passwordpasswordpassword");
    Assert.assertFalse(keyManagement.verifyPrivateKey(encryptedPrivateKey, "Wrongpassword"));
    Assert.assertTrue(keyManagement.verifyPrivateKey(encryptedPrivateKey, "passwordpasswordpassword"));
    
  }
}
