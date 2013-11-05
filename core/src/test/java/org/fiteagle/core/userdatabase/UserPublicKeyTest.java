package org.fiteagle.core.userdatabase;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import junit.framework.Assert;

import org.fiteagle.core.aaa.KeyManagement;
import org.junit.Test;

public class UserPublicKeyTest {
  
  private static final String PUBLICKEY_STRING = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCLq3fDWATRF8tNlz79sE4Ug5z2r5CLDG353SFneBL5z9Mwoub2wnLey8iqVJxIAE4nJsjtN0fUXC548VedJVGDK0chwcQGVinADbsIAUwpxlc2FGo3sBoGOkGBlMxLc/+5LT1gMH+XD6LljxrekF4xG6ddHTgcNO26VtqQw/VeGw==";
  private static final String PUBLICKEY_STRING_INVALID = "ssh-rsa AAAAA3NzaC1yc2EABAADAQABAAAAgQCLq3fDWATRF8tNlz79sE4Ug5z2r5CLDG353SFneBL5z9Mwoub2wnLey8iqVJxIAE4nJsjtN0fUXC548VedJVGDK0chwcQGVinADbsIAUwpxlc2FGo3sBoGOkGBlMxLc/+5LT1gMH+XD6LljxrekF4xG6ddHTgcNO26VtqQw/VeGw==";

  @Test(expected=User.NotEnoughAttributesException.class)
  public void createPublicKeyWithoutDescription() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
    new UserPublicKey(PUBLICKEY_STRING, "");
  }
  
  @Test(expected=User.InValidAttributeException.class)
  public void createPublicKeyWithInvalidDescription() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
    new UserPublicKey(PUBLICKEY_STRING, "key#1");
  }
  
  @Test(expected=KeyManagement.CouldNotParse.class)
  public void createPublicKeyWithInvalidKeyString() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException{
    new UserPublicKey(PUBLICKEY_STRING_INVALID, "invalidkey");
  }
  
  @Test
  public void createPublicKeyWithDifferentConstructors() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException{
    UserPublicKey key1 = new UserPublicKey(PUBLICKEY_STRING, "key1");
    UserPublicKey key2 = new UserPublicKey(key1.getPublicKey(), "key2");
    Assert.assertEquals(key1, key2);
    Assert.assertEquals(key1.getPublicKeyString(), key2.getPublicKeyString());
  }
}
