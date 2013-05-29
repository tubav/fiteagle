package org.fiteagle.core.aaa;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
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
    userDBManager = UserDBManager.getInstance();
    keyManagement = new KeyManagement();
  }
  
  
  @Test
  public void testGetEncryptedPrivateKey() throws DuplicateUIDException, SQLException, IOException, GeneralSecurityException{
    User u = userDBManager.createUser("test", "test", "testName", "password");
    CertificateAuthority cA = CertificateAuthority.getInstance();
    
  
    KeyPair keyPair = keyManagement.generateKeyPair();
    String encryptedPrivateKey = keyManagement.encryptPrivateKey(keyPair, "password");
    System.out.println(encryptedPrivateKey);
    Assert.assertNotNull(encryptedPrivateKey);
    
  }
  
  //@Test 
  public void testVerifyEncryptedPrivateKey() throws DuplicateUIDException, SQLException, IOException, GeneralSecurityException{
    User u = userDBManager.createUser("test", "test", "testName", "password");

    KeyPair keyPair = keyManagement.generateKeyPair();
    String encryptedPrivateKey = keyManagement.encryptPrivateKey(keyPair, "passwordpasswordpassword");
    Assert.assertFalse(keyManagement.verifyPrivateKey(encryptedPrivateKey, "Wrongpassword"));
    Assert.assertTrue(keyManagement.verifyPrivateKey(encryptedPrivateKey, "passwordpasswordpassword"));
    
  }
}
