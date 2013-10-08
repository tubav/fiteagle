package org.fiteagle.core.userdatabase;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;

import org.fiteagle.core.aaa.KeyManagement.CouldNotParse;
import org.fiteagle.core.userdatabase.JPAUserDB.DuplicateEmailException;
import org.fiteagle.core.userdatabase.JPAUserDB.DuplicatePublicKeyException;
import org.fiteagle.core.userdatabase.JPAUserDB.DuplicateUsernameException;
import org.fiteagle.core.userdatabase.JPAUserDB.NotEnoughAttributesException;
import org.fiteagle.core.userdatabase.JPAUserDB.UserNotFoundException;
import org.fiteagle.core.userdatabase.User.PublicKeyNotFoundException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class JPAUserDBTest {
  
  protected static ArrayList<UserPublicKey> KEYS1;
  protected static ArrayList<UserPublicKey> KEYS2; 
  protected static User USER1;
  protected static User USER2;
  protected static User USER3;
  protected static User USER4;
  
  private static JPAUserDB manager;
  
  private void createUser1() {
    KEYS1 = new ArrayList<UserPublicKey>();
    try {
      KEYS1.add(new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCarsTCyAf8gYXwei8rhJnLTqYI6P88weRaY5dW9j3DT4mvfQPna79Bjq+uH4drmjbTD2n3s3ytqupFfNko1F0+McstA2EEkO8pAo5NEPcreygUcB2d49So032GKGPETB8chRkDsaBCm/KKL2vXdQoicofli8JJRPK2kXfUW34qww==", "key1"));
      KEYS1.add(new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCOHoq0DYsW793kyhbW1sj6aNm5OWeRn3HQ6nZxU9ax3FnDmtJsxvq2u0RwtPQki5JEMG58aqJPs3s4Go6LrTyw4jqnodKyOfcFupUYHTbQYnzxudLwyU59RfBmH01cLiyu26ECdVNXX+Y1mgofRUx72thBTtY6vyuM5nR1l7UNTw==", "key2"));
    } catch (NotEnoughAttributesException | InvalidKeySpecException | NoSuchAlgorithmException | CouldNotParse | IOException e) {
      e.printStackTrace();
    }
    USER1 = new User("test1", "mitja", "nikolaus", "test1@test.org", "mitjasAffiliation", "mitjasPassword", KEYS1);
  }
  
  private void createUser2() {
    KEYS2 = new ArrayList<UserPublicKey>(); 
    try {
      KEYS2.add(new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQDKpQJGxnReKal3p7d/95G5d3RQb002gso1QJrjxFKED+1cD157FsT2bCPcWpTYdLeTFRWBDUQa91yUPdkjkvoMsL2e3ah7nugRD6QfrFki0Po9oENrbujzaExPV8SAvXSuqcCG4/pidqEqjXJlAMXrphJcoFdKSzXLJtjUwfxyEw==", "key3"));
    } catch (NotEnoughAttributesException | InvalidKeySpecException | NoSuchAlgorithmException | CouldNotParse | IOException e) {
      e.printStackTrace();
    }
    USER2 = new User("test2", "hans", "schmidt", "hschmidt@test.org", "hansAffiliation", "hansPassword", KEYS2);
  }
  
  private void createUser3() {
     USER3 = new User("test3", "mitja", "nikolaus", "mitja@test.org", "mitjaAffiliation", "mitjasPassword", new ArrayList<UserPublicKey>());    
  }
  
  private void createUser4() {
     USER4 = new User("test4", "mitja", "nikolaus", "mitja@test.org", "mitjaAffiliation", "mitjasPassword", new ArrayList<UserPublicKey>());
  }
  
  
  @BeforeClass
  public static void setUp(){
    manager = new JPAUserDB();
  }
  
  @Test
  public void testGet(){   
    createUser1();
    manager.add(USER1);    
    assertTrue(USER1.equals(manager.get(USER1)));    
  }
  
//  
//  @Test
//  public void testAdd() throws DatabaseException{
//    createUser1();
//    int numberOfUsers = manager.getNumberOfUsers();
//    manager.add(USER1);
//    assertEquals(numberOfUsers+1,manager.getNumberOfUsers());
//  }
//  
  @Test(expected=DuplicateUsernameException.class)
  public void testAddFails() {
    createUser2();
    manager.add(USER2);
    USER2.setEmail("hans@mail.org");
    manager.add(USER2);
  }

  @Test
  public void testGetUserWhoHasNoKeys() throws DuplicateUsernameException, NoSuchAlgorithmException{
    createUser3();
    manager.add(USER3);
    assertTrue(USER3.equals(manager.get(USER3)));
  }
  
  @Test(expected=UserNotFoundException.class)
  public void testGetFails() {
    createUser1();
    createUser2();
    manager.add(USER1);
    manager.get(USER2);
  }
  
  @Test(expected=JPAUserDB.UserNotFoundException.class)
  public void testDelete(){
    createUser1();
    manager.add(USER1);    
    manager.delete(USER1);   
    manager.get(USER1);
  }
    
  @Test
  public void testUpdate() throws InterruptedException{
    createUser2();
    manager.add(USER2);
    Thread.sleep(1);
    manager.update(USER2.getUsername(), "herbert", null, null, null, null, null);
    User updatedUser = manager.get(USER2);
    assertTrue("herbert".equals(updatedUser.getFirstName()));
    Date created = updatedUser.getCreated();
    Date lastModified = updatedUser.getLastModified();
    assertTrue(created.before(lastModified));
  }
  
  @Test(expected=UserNotFoundException.class)
  public void testUpdateFails() {
    manager.update("test1", null, null, null, null, null, null);
  }
  
  @Test
  public void testAddKey() throws UserNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException, IOException{
    createUser1();
    manager.add(USER1);    
    manager.addKey(USER1.getUsername(), new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCQf/Ub9v6jR/8C58zC2MMakX5sOHfpl6asymHBnYBQ5xqL+P94A3lrViXRbss/G4ozBgGINvshdLAMjclmwgK7wSOcTlIAORhggU+iBM7V+YCa5Dj0gR0mMzDBxL71l9dCQ3wL+GWMI/bwoeuq+83rLes1T1Yyk7Fp27gR+P05VQ==", "key4"));
    assertTrue(manager.get(USER1).getPublicKeys().contains(new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCQf/Ub9v6jR/8C58zC2MMakX5sOHfpl6asymHBnYBQ5xqL+P94A3lrViXRbss/G4ozBgGINvshdLAMjclmwgK7wSOcTlIAORhggU+iBM7V+YCa5Dj0gR0mMzDBxL71l9dCQ3wL+GWMI/bwoeuq+83rLes1T1Yyk7Fp27gR+P05VQ==", "key4")));
  }
    
  @Test(expected = DuplicatePublicKeyException.class)
  public void testAddDuplicateKeys() {
    createUser1();
    manager.add(USER1);  
    manager.addKey(USER1.getUsername(), KEYS1.get(0));
  }
  
  @Test(expected = DuplicatePublicKeyException.class)
  public void testAddDuplicateKeysWithDifferentDescription() throws UserNotFoundException, DuplicatePublicKeyException, InvalidKeySpecException, NoSuchAlgorithmException, IOException{
    createUser1();
    manager.add(USER1);  
    manager.addKey(USER1.getUsername(), new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCQf/Ub9v6jR/8C58zC2MMakX5sOHfpl6asymHBnYBQ5xqL+P94A3lrViXRbss/G4ozBgGINvshdLAMjclmwgK7wSOcTlIAORhggU+iBM7V+YCa5Dj0gR0mMzDBxL71l9dCQ3wL+GWMI/bwoeuq+83rLes1T1Yyk7Fp27gR+P05VQ==", "key5"));
    manager.addKey(USER1.getUsername(), new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCQf/Ub9v6jR/8C58zC2MMakX5sOHfpl6asymHBnYBQ5xqL+P94A3lrViXRbss/G4ozBgGINvshdLAMjclmwgK7wSOcTlIAORhggU+iBM7V+YCa5Dj0gR0mMzDBxL71l9dCQ3wL+GWMI/bwoeuq+83rLes1T1Yyk7Fp27gR+P05VQ==", "key6"));
  }

  @Test
  public void testDeleteKey() {
    createUser2();
    String key = KEYS2.get(0).getDescription();
    manager.add(USER2);
    manager.deleteKey(USER2.getUsername(), key);
    assertTrue(!manager.get(USER2).getPublicKeys().contains(key));
  } 
  
  @Test
  public void testRenameKey() {
    createUser2();
    manager.add(USER2);
    manager.renameKey(USER2.getUsername(), "key3", "my new description");
    assertEquals("my new description", manager.get(USER2).getPublicKeys().get(0).getDescription());
  }
  
  @Test(expected = DuplicatePublicKeyException.class)
  public void testRenameKeyDuplicateDescription() {
    createUser1();
    manager.add(USER1);
    manager.renameKey(USER1.getUsername(), "key1", "key2");
  }
  
  @Test(expected = PublicKeyNotFoundException.class)
  public void testRenameKeyNotFound() {
    createUser1();
    manager.add(USER1);
    manager.renameKey(USER1.getUsername(), "key5", "my new description");
  }
  
  @Test(expected = DuplicateEmailException.class)
  public void testDuplicateEmailExeptionWhenAdd(){
    createUser3();
    createUser4();
    manager.add(USER3);
    manager.add(USER4);
  }

  @Test(expected = DuplicateEmailException.class)
  public void testDuplicateEmailExeptionWhenUpdate(){
    createUser1();
    createUser4();
    manager.add(USER1);
    manager.add(USER4);
    manager.update(USER4.getUsername(), "mitja", "nikolaus", "test1@test.org", "mitjaAffiliation", "mitjasPassword", null);
  }
  
  @After
  public void deleteUsers() {
    try{
      manager.delete("test1");
    }catch (UserNotFoundException e){}
    try{
      manager.delete("test2");
    }catch (UserNotFoundException e){}
    try{
      manager.delete("test3");
    }catch (UserNotFoundException e){}
    try{
      manager.delete("test4");
    }catch (UserNotFoundException e){}
  }   
  
}
