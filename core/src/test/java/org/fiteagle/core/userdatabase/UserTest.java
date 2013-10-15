package org.fiteagle.core.userdatabase;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {
  
  @Test(expected=User.NotEnoughAttributesException.class)
  public void testCreateUserWithoutPassword() {
    new User("test1", "test1", "test1", "test1@test.de", "test1", "", null);
  }
  
  @Test
  public void testCreateDefaultUser(){
    User user = User.createDefaultUser("test1");
    Assert.assertEquals("default", user.getAffiliation());
  }
  
  @Test(expected=User.InValidAttributeException.class)
  public void testCreateUserWithInvalidUsername(){
    new User("test!", "test1", "test1", "test1@test.de", "test1", "test1", null);
  }
  
  @Test(expected=User.InValidAttributeException.class)
  public void testCreateUserWithInvalidEmail(){
    new User("test1", "test1", "test1", "te@st1@test.de", "test1", "test1", null);
  }
  
  @Test(expected=User.InValidAttributeException.class)
  public void testCreateUserWithInvalidEmail2(){
    new User("test1", "test1", "test1", "test1@testde", "test1", "test1", null);
  }
  
}
