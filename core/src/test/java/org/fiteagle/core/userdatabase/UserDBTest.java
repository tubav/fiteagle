package org.fiteagle.core.userdatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.fiteagle.core.userdatabase.UserDB.DatabaseException;
import org.fiteagle.core.userdatabase.UserDB.DuplicateUIDException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDBTest {

	private static UserDB database;
	private static UserDBManager userDBManager;
	
	private static final ArrayList<String> KEYS1 = new ArrayList<String>();
	private static final ArrayList<String> KEYS2 = new ArrayList<String>();	
	private static User USER1;
	private static User USER2;
	private static User USER3;
	private static User USER4;
	
	@BeforeClass
	public static void createUsers() throws DatabaseException, DuplicateUIDException, NoSuchAlgorithmException, IOException{
	  KEYS1.add("ssh-rsa AAAAB3NzaC1ydzkACAADAQABAAABAQCybYW812Eb9aTxrXnFgIG7etEijX3/+pWlurrYpvqXi6rl0LZWnotWaC0TeBKWMwDAwPDnSeMxGtYDrZXQJNurrdsmYtzJSL79hhLJqsQCv4s5tK+d/GPRsPSfsGI0A+ckDiQ7yXErUSIgcmGXC4Jo6tuN0QI3x3wIlivDMwkVxZm4m82LwqVECtodnvzbct13a9rIhgjGTRyXXsLVt+X1MB45OlQJ+CWWkaO3emRHDDktZAjkhXNXYKeDtXj4yIhy+jPLTSKwsghCQD79U+sQEDY+RBPu7Td5GzQx8tFdFAjghZaWgeD3iRmpcr8tukR+jG1ynL0zrzumFf4Cg359 mitja@mitja-Precision-WorkStation-370");
    KEYS1.add("ssh-rsa AAAAB3NzaC1yc2EACAADATZCAAABAQCybYW812Eb9aTxrXnFgIG7etEijX3/+pWlurrYpvqXi6rl0LZWnotWaC0TeBKWMwDAwPDnSeMxGtYDrZXQJNurrdsmYtzJSL79hhLJqsQCv4s5tK+d/GPRsPSfsGI0A+ckDiQ7yXErUSIgcmGXC4Jo6tuN0QI3x3wIlivDMwkVxZm4m82LwqVECtodnvzbct13a9rIhgjGTRyXXsLVt+X1MB45OlQJ+CWWkaO3emRHDDktZAjkhXNXYKeDtXj4yIhy+jPLTSKiObJnCQD79U+sQEDY+RBPu7Td5GzQx8tFd34gesatWgeDiRmpcr8tukR+jG1ynL0zrzumFf4Cg359 mitja@mitja-Precision-WorkStation-370");
    KEYS2.add("ssh-rsa AAAAB3NzaC1yc2EACAADAQABAAABAQCybYW812Eb9aTxrXnFgIG7etEijX3/+pWlurrYpvqXi6rl0LZWnotWaC0TeBKWMwDAwPDnSeMxGtYDrZXQJNurrdsmYtzJSL79hhLJqsQCv4s5tK+d/GPRsPSfsGI0A+ckDiQ7yXErUSIgcmGXC4Jo6tuN0QI3x3wIlivDMwkVxZm4m82LwqVECtodnvzbct13a9rIhgjGTRyXXsLVt+X1MB45OlQJ+CWWkaO3emRHDDktZAjkhXNXYKeDtXj4yIhy+jPLTSKiObJnCQD79U+sQEDY+RBPu7Td5GzQx8tFdFAjghZaWgeDiRmpcr8tukR+jG1ynL0zrzumFf4Cg359 mitja@mitja-Precision-WorkStation-370");
	  userDBManager = UserDBManager.getInstance();
    USER1 = userDBManager.createUser("mnikolaus", "mitja", "nikolaus", "mitja", KEYS1);
    USER2 = userDBManager.createUser("hschmidt", "herbert", "schmidt", "herbert", KEYS2);
    USER3 = userDBManager.createUser("hschmidt", "herbert", "schmidt", "herbert", KEYS1);
    USER4 = userDBManager.createUser("mnikolaus", "mitja", "nikolaus","mitja", new ArrayList<String>());
	}
	
	@Before
	public void setUp() throws DatabaseException{
		database = new InMemoryUserDB();
	}
	
	@Test
	public void testAdd() throws DatabaseException{
		assertEquals(0,database.getNumberOfUsers());
		database.add(USER1);
		assertEquals(1,database.getNumberOfUsers());
	}
	
	@Test(expected=UserDB.DuplicateUIDException.class)
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
	public void testGetUserWhoHasNoKeys() throws DatabaseException, DuplicateUIDException, NoSuchAlgorithmException{
		database.add(USER4);
		assertTrue(USER4.equals(database.get(USER4)));

	}
	
	@Test(expected=UserDB.RecordNotFoundException.class)
	public void testGetFails() throws DatabaseException{
		database.add(USER1);
		database.get(USER2);
	}
	
	@Test(expected=UserDB.RecordNotFoundException.class)
	public void testDelete() throws DatabaseException{
		database.add(USER1);
		database.delete(USER1);
		database.get(USER1);
	}
		
	@Test
	public void testUpdate() throws DatabaseException{
		database.add(USER2);
		database.update(USER3);
		assertTrue(USER3.equals(database.get(USER3)));
	}
	
	@Test(expected=UserDB.RecordNotFoundException.class)
	public void testUpdateFails() throws DatabaseException{
		database.update(USER3);
	}
	
	@Test
	public void testAddKey() throws DatabaseException{
		database.add(USER1);		
		database.addKey(USER1.getUID(), KEYS2.get(0));
		assertEquals(KEYS2.get(0), database.get(USER1).getPublicKeys().get(2));
	}
		
	@Test
	public void testAddDuplicateKeys() throws DatabaseException{
		database.add(USER1);		
		database.addKey(USER1.getUID(), KEYS1.get(0));
		assertTrue(USER1.equals(database.get(USER1)));
	}
	
	@After
	public void cleanUp() throws DatabaseException{
		database.deleteAllEntries();
	}	
}
