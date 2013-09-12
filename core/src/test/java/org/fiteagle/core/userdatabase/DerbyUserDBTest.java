package org.fiteagle.core.userdatabase;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;

import org.fiteagle.core.aaa.KeyManagement.CouldNotParse;
import org.fiteagle.core.userdatabase.UserPersistable.DatabaseException;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicateEmailException;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicatePublicKeyException;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicateUsernameException;
import org.fiteagle.core.userdatabase.UserPersistable.InValidAttributeException;
import org.fiteagle.core.userdatabase.UserPersistable.NotEnoughAttributesException;
import org.fiteagle.core.userdatabase.UserPersistable.PublicKeyNotFoundException;
import org.fiteagle.core.userdatabase.UserPersistable.UserNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DerbyUserDBTest {
  
  protected static UserPersistable database;
  
  protected static ArrayList<UserPublicKey> KEYS1;
  protected static ArrayList<UserPublicKey> KEYS2;  
  protected static User USER1;
  protected static User USER2;
  protected static User USER3;
  protected static User USER4;
  
  protected void setUpConnection() throws DatabaseException{
    database = DerbyUserDB.getInstance();
  }
  
  private void createUser1() {
    KEYS1 = new ArrayList<UserPublicKey>();
    try {
      KEYS1.add(new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCarsTCyAf8gYXwei8rhJnLTqYI6P88weRaY5dW9j3DT4mvfQPna79Bjq+uH4drmjbTD2n3s3ytqupFfNko1F0+McstA2EEkO8pAo5NEPcreygUcB2d49So032GKGPETB8chRkDsaBCm/KKL2vXdQoicofli8JJRPK2kXfUW34qww==", "key1"));
      KEYS1.add(new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCOHoq0DYsW793kyhbW1sj6aNm5OWeRn3HQ6nZxU9ax3FnDmtJsxvq2u0RwtPQki5JEMG58aqJPs3s4Go6LrTyw4jqnodKyOfcFupUYHTbQYnzxudLwyU59RfBmH01cLiyu26ECdVNXX+Y1mgofRUx72thBTtY6vyuM5nR1l7UNTw==", "key2"));
    } catch (NotEnoughAttributesException | InvalidKeySpecException | NoSuchAlgorithmException | CouldNotParse | IOException e) {
      e.printStackTrace();
    }
    USER1 = User.createUser("test1", "mitja", "nikolaus", "mitja@test.org", "mitjasAffiliation", "mitjasPassword", KEYS1);
  }
  
  private void createUser2() {
    KEYS2 = new ArrayList<UserPublicKey>(); 
    try {
      KEYS2.add(new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQDKpQJGxnReKal3p7d/95G5d3RQb002gso1QJrjxFKED+1cD157FsT2bCPcWpTYdLeTFRWBDUQa91yUPdkjkvoMsL2e3ah7nugRD6QfrFki0Po9oENrbujzaExPV8SAvXSuqcCG4/pidqEqjXJlAMXrphJcoFdKSzXLJtjUwfxyEw==", "key3"));
    } catch (NotEnoughAttributesException | InvalidKeySpecException | NoSuchAlgorithmException | CouldNotParse | IOException e) {
      e.printStackTrace();
    }
    USER2 = User.createUser("test2", "hans", "herbert", "hschmidt@test.org", "hansAffiliation", "hansPassword", KEYS2);
  }
  
  private void createUser3() {
     USER3 = User.createUser("test3", "mitja", "nikolaus", "mitja@test.org", "mitjaAffiliation", "mitjasPassword", new ArrayList<UserPublicKey>());    
  }
  
  private void createUser4() {
     USER4 = User.createUser("test4", "mitja", "nikolaus", "mitja@test.org", "mitjaAffiliation", "mitjasPassword", new ArrayList<UserPublicKey>());
  }
  
  @Before
  public void setUpAndCreateUsers() throws DatabaseException, DuplicateUsernameException, NoSuchAlgorithmException, IOException, InvalidKeySpecException{
    setUpConnection();
  }
  
  @Test
  public void testAdd() throws DatabaseException{
    createUser1();
    int numberOfUsers = database.getNumberOfUsers();
    database.add(USER1);
    assertEquals(numberOfUsers+1,database.getNumberOfUsers());
  }
  
  @Test(expected=UserPersistable.DuplicateUsernameException.class)
  public void testAddFailsDuplicateUsername() throws DatabaseException{
    createUser2();
    database.add(USER2);
    USER2.setEmail("hans@mail.org");
    database.add(USER2);
  }
  
  @Test(expected=UserPersistable.DuplicateEmailException.class)
  public void testAddFailsDuplicateEmail() throws DatabaseException{
    createUser2();
    database.add(USER2);
    USER2.setUsername("test3");
    database.add(USER2);
  }
  
  @Test
  public void testGet() throws DatabaseException{   
    createUser1();
    database.add(USER1);    
    assertTrue(USER1.equals(database.get(USER1)));    
  }
  
  @Test
  public void testGetUserWhoHasNoKeys() throws DatabaseException, DuplicateUsernameException, NoSuchAlgorithmException{
    createUser3();
    database.add(USER3);
    assertTrue(USER3.equals(database.get(USER3)));
  }
  
  @Test(expected=UserPersistable.UserNotFoundException.class)
  public void testGetFails() throws DatabaseException{
    createUser1();
    createUser2();
    database.add(USER1);
    database.get(USER2);
  }
  
  @Test(expected=UserPersistable.UserNotFoundException.class)
  public void testDelete() throws DatabaseException{
    createUser1();
    database.add(USER1);    
    database.delete(USER1);   
    database.get(USER1);
  }
    
  @Test
  public void testUpdate() throws DatabaseException, InterruptedException{
    createUser2();
    database.add(USER2);
    Thread.sleep(1);
    database.update(USER2.getUsername(), "herbert", null, null, null, null, null);
    User updatedUser = database.get(USER2);
    assertTrue("herbert".equals(updatedUser.getFirstName()));
    Date created = updatedUser.getCreated();
    Date lastModified = updatedUser.getLast_modified();
    assertTrue(created.before(lastModified));
  }
  
  @Test(expected=UserPersistable.UserNotFoundException.class)
  public void testUpdateFails() throws DatabaseException{
    createUser2();
    database.update(USER2.getUsername(), null, null, null, null, null, null);
  }
  
  @Test
  public void testAddKey() throws DatabaseException, UserNotFoundException, InValidAttributeException, DuplicatePublicKeyException, InvalidKeySpecException, NoSuchAlgorithmException, IOException{
    createUser1();
    database.add(USER1);    
    database.addKey(USER1.getUsername(), new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCQf/Ub9v6jR/8C58zC2MMakX5sOHfpl6asymHBnYBQ5xqL+P94A3lrViXRbss/G4ozBgGINvshdLAMjclmwgK7wSOcTlIAORhggU+iBM7V+YCa5Dj0gR0mMzDBxL71l9dCQ3wL+GWMI/bwoeuq+83rLes1T1Yyk7Fp27gR+P05VQ==", "key4"));
    assertTrue(database.get(USER1).getPublicKeys().contains(new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCQf/Ub9v6jR/8C58zC2MMakX5sOHfpl6asymHBnYBQ5xqL+P94A3lrViXRbss/G4ozBgGINvshdLAMjclmwgK7wSOcTlIAORhggU+iBM7V+YCa5Dj0gR0mMzDBxL71l9dCQ3wL+GWMI/bwoeuq+83rLes1T1Yyk7Fp27gR+P05VQ==", "key4")));
  }
    
  @Test(expected = DuplicatePublicKeyException.class)
  public void testAddDuplicateKeys() throws DatabaseException{
    createUser1();
    database.add(USER1);  
    database.addKey(USER1.getUsername(), KEYS1.get(0));
  }
  
  @Test(expected = DuplicatePublicKeyException.class)
  public void testAddDuplicateKeysWithDifferentDescription() throws DatabaseException, UserNotFoundException, InValidAttributeException, DuplicatePublicKeyException, InvalidKeySpecException, NoSuchAlgorithmException, IOException{
    createUser1();
    database.add(USER1);  
    database.addKey(USER1.getUsername(), new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCQf/Ub9v6jR/8C58zC2MMakX5sOHfpl6asymHBnYBQ5xqL+P94A3lrViXRbss/G4ozBgGINvshdLAMjclmwgK7wSOcTlIAORhggU+iBM7V+YCa5Dj0gR0mMzDBxL71l9dCQ3wL+GWMI/bwoeuq+83rLes1T1Yyk7Fp27gR+P05VQ==", "key5"));
    database.addKey(USER1.getUsername(), new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCQf/Ub9v6jR/8C58zC2MMakX5sOHfpl6asymHBnYBQ5xqL+P94A3lrViXRbss/G4ozBgGINvshdLAMjclmwgK7wSOcTlIAORhggU+iBM7V+YCa5Dj0gR0mMzDBxL71l9dCQ3wL+GWMI/bwoeuq+83rLes1T1Yyk7Fp27gR+P05VQ==", "key6"));
  }
  
  @Test
  public void testDeleteKey() throws DatabaseException{
    createUser2();
    String key = KEYS2.get(0).getDescription();
    database.add(USER2);
    database.deleteKey(USER2.getUsername(), key);
    assertTrue(!database.get(USER2).getPublicKeys().contains(key));
  } 
  
  @Test
  public void testRenameKey() {
    createUser1();
    database.add(USER1);
    database.renameKey(USER1.getUsername(), "key1", "my new description");
  }
  
  @Test(expected = DuplicatePublicKeyException.class)
  public void testRenameKeyDuplicateDescription() {
    createUser1();
    database.add(USER1);
    database.renameKey(USER1.getUsername(), "key1", "key2");
  }
  
  @Test(expected = PublicKeyNotFoundException.class)
  public void testRenameKeyNotFound() {
    createUser1();
    database.add(USER1);
    database.renameKey(USER1.getUsername(), "key5", "my new description");
  }
  
  @Test(expected = DuplicateEmailException.class)
  public void testDuplicateEmailExeptionWhenUpdate(){
    createUser2();
    createUser4();
    database.add(USER2);
    database.add(USER4);
    database.update(USER4.getUsername(), "mitja", "nikolaus", "hschmidt@test.org", "mitjaAffiliation", "mitjasPassword", null);
  }
  
  
  @After
  public void cleanUp() {
    if(USER1 != null){
      database.delete("test1");
      USER1 = null;
    }
    if(USER2 != null){
      database.delete("test2");
      USER2 = null;
    }
    if(USER3 != null){
      database.delete("test3");
      USER3 = null;
    }
    if(USER4 != null){
      database.delete("test4");
      USER4 = null;
    }
  }   
  
  
}
