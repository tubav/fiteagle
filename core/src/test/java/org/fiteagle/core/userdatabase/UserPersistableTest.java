package org.fiteagle.core.userdatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;

import org.fiteagle.core.userdatabase.UserPersistable.DatabaseException;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicatePublicKeyException;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicateUsernameException;
import org.fiteagle.core.userdatabase.UserPersistable.InValidAttributeException;
import org.fiteagle.core.userdatabase.UserPersistable.UserNotFoundException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public abstract class UserPersistableTest {

	protected static UserPersistable database;
	
	protected static ArrayList<UserPublicKey> KEYS1;
	protected static ArrayList<UserPublicKey> KEYS2;	
	protected static User USER1;
	protected static User USER2;
	protected static User USER3;
	protected static User USER4;
	
	@BeforeClass
	public static void createUsers() throws DatabaseException, DuplicateUsernameException, NoSuchAlgorithmException, IOException, InvalidKeySpecException{
	  KEYS1 = new ArrayList<UserPublicKey>();
	  KEYS2 = new ArrayList<UserPublicKey>(); 
	  KEYS1.add(new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCarsTCyAf8gYXwei8rhJnLTqYI6P88weRaY5dW9j3DT4mvfQPna79Bjq+uH4drmjbTD2n3s3ytqupFfNko1F0+McstA2EEkO8pAo5NEPcreygUcB2d49So032GKGPETB8chRkDsaBCm/KKL2vXdQoicofli8JJRPK2kXfUW34qww==", "key1"));
    KEYS1.add(new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCOHoq0DYsW793kyhbW1sj6aNm5OWeRn3HQ6nZxU9ax3FnDmtJsxvq2u0RwtPQki5JEMG58aqJPs3s4Go6LrTyw4jqnodKyOfcFupUYHTbQYnzxudLwyU59RfBmH01cLiyu26ECdVNXX+Y1mgofRUx72thBTtY6vyuM5nR1l7UNTw==", "key2"));
    KEYS2.add(new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQDKpQJGxnReKal3p7d/95G5d3RQb002gso1QJrjxFKED+1cD157FsT2bCPcWpTYdLeTFRWBDUQa91yUPdkjkvoMsL2e3ah7nugRD6QfrFki0Po9oENrbujzaExPV8SAvXSuqcCG4/pidqEqjXJlAMXrphJcoFdKSzXLJtjUwfxyEw==", "key3"));
	  USER1 = new User("test1", "mitja", "nikolaus", "mitja@test.org", "mitjasAffiliation", "mitjasPassword", KEYS1);
	  USER2 = new User("test2", "hans", "herbert", "hschmidt@test.org", "hansAffiliation", "hansPassword", KEYS2);
	  USER3 = new User("test2", "herbert", "herbert", "hschmidt@test.org", "herbertAffiliation", "herbertsPassword", KEYS1);
	  USER4 = new User("test4", "mitja", "nikolaus", "mitja@test.org", "mitjaAffiliation", "mitjasPassword");	  
	}
	
	@Before
	public void setUpAndCreateUsers() throws DatabaseException, DuplicateUsernameException, NoSuchAlgorithmException, IOException, InvalidKeySpecException{
	  setUpConnection();
	  createUsers();
  }
	
	protected abstract void setUpConnection();
	
  @Test
	public void testAdd() throws DatabaseException{
		int numberOfUsers = database.getNumberOfUsers();
		database.add(USER1);
		assertEquals(numberOfUsers+1,database.getNumberOfUsers());
	}
	
	@Test(expected=UserPersistable.DuplicateUsernameException.class)
	public void testAddFails() throws DatabaseException{
		database.add(USER2);
		database.add(USER3);
	}
	
	@Test
	public void testGet() throws DatabaseException{		
		database.add(USER1);		
		assertTrue(USER1.equals(database.get(USER1)));		
	}

	@Test
	public void testGetUserWhoHasNoKeys() throws DatabaseException, DuplicateUsernameException, NoSuchAlgorithmException{
		database.add(USER4);
		assertTrue(USER4.equals(database.get(USER4)));
	}
	
	@Test(expected=UserPersistable.UserNotFoundException.class)
	public void testGetFails() throws DatabaseException{
		database.add(USER1);
		database.get(USER2);
	}
	
	@Test(expected=UserPersistable.UserNotFoundException.class)
	public void testDelete() throws DatabaseException{
		database.add(USER1);		
		database.delete(USER1);		
		database.get(USER1);
	}
		
	@Test
	public void testUpdate() throws DatabaseException, InterruptedException{
		database.add(USER2);
		Thread.sleep(1);
		database.update(USER3);
		User updatedUser = database.get(USER3);
		assertTrue(USER3.equals(updatedUser));
		Date created = updatedUser.getCreated();
		Date lastModified = updatedUser.getLast_modified();
		assertTrue(created.before(lastModified));
	}
	
	@Test
	public void testUpdateWithFewArguments(){
	  database.add(USER1);
	  database.update(new User("test1", "martin", null, null, null, null, null, null, null, null));
	  assertEquals("martin", database.get(USER1.getUsername()).getFirstName());
	  assertEquals("nikolaus", database.get(USER1.getUsername()).getLastName());
	}
	
	@Test(expected=UserPersistable.UserNotFoundException.class)
	public void testUpdateFails() throws DatabaseException{
		database.update(USER3);
	}
	
	@Test
	public void testAddKey() throws DatabaseException, UserNotFoundException, InValidAttributeException, DuplicatePublicKeyException, InvalidKeySpecException, NoSuchAlgorithmException, IOException{
	  database.add(USER1);		
		database.addKey(USER1.getUsername(), new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCQf/Ub9v6jR/8C58zC2MMakX5sOHfpl6asymHBnYBQ5xqL+P94A3lrViXRbss/G4ozBgGINvshdLAMjclmwgK7wSOcTlIAORhggU+iBM7V+YCa5Dj0gR0mMzDBxL71l9dCQ3wL+GWMI/bwoeuq+83rLes1T1Yyk7Fp27gR+P05VQ==", "key4"));
		assertTrue(database.get(USER1).getPublicKeys().contains(new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCQf/Ub9v6jR/8C58zC2MMakX5sOHfpl6asymHBnYBQ5xqL+P94A3lrViXRbss/G4ozBgGINvshdLAMjclmwgK7wSOcTlIAORhggU+iBM7V+YCa5Dj0gR0mMzDBxL71l9dCQ3wL+GWMI/bwoeuq+83rLes1T1Yyk7Fp27gR+P05VQ==", "key4")));
	}
		
	@Test(expected = DuplicatePublicKeyException.class)
	public void testAddDuplicateKeys() throws DatabaseException{
		database.add(USER1);	
		database.addKey(USER1.getUsername(), KEYS1.get(0));
	}
	
	@Test(expected = DuplicatePublicKeyException.class)
  public void testAddDuplicateKeysWithDifferentDescription() throws DatabaseException, UserNotFoundException, InValidAttributeException, DuplicatePublicKeyException, InvalidKeySpecException, NoSuchAlgorithmException, IOException{
    database.add(USER1);  
    database.addKey(USER1.getUsername(), new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCQf/Ub9v6jR/8C58zC2MMakX5sOHfpl6asymHBnYBQ5xqL+P94A3lrViXRbss/G4ozBgGINvshdLAMjclmwgK7wSOcTlIAORhggU+iBM7V+YCa5Dj0gR0mMzDBxL71l9dCQ3wL+GWMI/bwoeuq+83rLes1T1Yyk7Fp27gR+P05VQ==", "key5"));
    database.addKey(USER1.getUsername(), new UserPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCQf/Ub9v6jR/8C58zC2MMakX5sOHfpl6asymHBnYBQ5xqL+P94A3lrViXRbss/G4ozBgGINvshdLAMjclmwgK7wSOcTlIAORhggU+iBM7V+YCa5Dj0gR0mMzDBxL71l9dCQ3wL+GWMI/bwoeuq+83rLes1T1Yyk7Fp27gR+P05VQ==", "key6"));
  }
	
	@Test
	public void testDeleteKey() throws DatabaseException{
	  String key = KEYS2.get(0).getDescription();
	  database.add(USER2);
	  database.deleteKey(USER2.getUsername(), key);
	  assertTrue(!database.get(USER2).getPublicKeys().contains(key));
	}	
}
