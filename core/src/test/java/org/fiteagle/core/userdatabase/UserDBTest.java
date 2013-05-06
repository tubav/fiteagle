package org.fiteagle.core.userdatabase;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDBTest {

	private static UserDB database;
	
	private static final ArrayList<String> KEYS1 = new ArrayList<String>();
	private static final ArrayList<String> KEYS2 = new ArrayList<String>();
	static{
		KEYS1.add("ssh-rsa AAAAB3NzaC1ydzkACAADAQABAAABAQCybYW812Eb9aTxrXnFgIG7etEijX3/+pWlurrYpvqXi6rl0LZWnotWaC0TeBKWMwDAwPDnSeMxGtYDrZXQJNurrdsmYtzJSL79hhLJqsQCv4s5tK+d/GPRsPSfsGI0A+ckDiQ7yXErUSIgcmGXC4Jo6tuN0QI3x3wIlivDMwkVxZm4m82LwqVECtodnvzbct13a9rIhgjGTRyXXsLVt+X1MB45OlQJ+CWWkaO3emRHDDktZAjkhXNXYKeDtXj4yIhy+jPLTSKwsghCQD79U+sQEDY+RBPu7Td5GzQx8tFdFAjghZaWgeD3iRmpcr8tukR+jG1ynL0zrzumFf4Cg359 mitja@mitja-Precision-WorkStation-370");
		KEYS1.add("ssh-rsa AAAAB3NzaC1yc2EACAADATZCAAABAQCybYW812Eb9aTxrXnFgIG7etEijX3/+pWlurrYpvqXi6rl0LZWnotWaC0TeBKWMwDAwPDnSeMxGtYDrZXQJNurrdsmYtzJSL79hhLJqsQCv4s5tK+d/GPRsPSfsGI0A+ckDiQ7yXErUSIgcmGXC4Jo6tuN0QI3x3wIlivDMwkVxZm4m82LwqVECtodnvzbct13a9rIhgjGTRyXXsLVt+X1MB45OlQJ+CWWkaO3emRHDDktZAjkhXNXYKeDtXj4yIhy+jPLTSKiObJnCQD79U+sQEDY+RBPu7Td5GzQx8tFd34gesatWgeDiRmpcr8tukR+jG1ynL0zrzumFf4Cg359 mitja@mitja-Precision-WorkStation-370");
		KEYS2.add("ssh-rsa AAAAB3NzaC1yc2EACAADAQABAAABAQCybYW812Eb9aTxrXnFgIG7etEijX3/+pWlurrYpvqXi6rl0LZWnotWaC0TeBKWMwDAwPDnSeMxGtYDrZXQJNurrdsmYtzJSL79hhLJqsQCv4s5tK+d/GPRsPSfsGI0A+ckDiQ7yXErUSIgcmGXC4Jo6tuN0QI3x3wIlivDMwkVxZm4m82LwqVECtodnvzbct13a9rIhgjGTRyXXsLVt+X1MB45OlQJ+CWWkaO3emRHDDktZAjkhXNXYKeDtXj4yIhy+jPLTSKiObJnCQD79U+sQEDY+RBPu7Td5GzQx8tFdFAjghZaWgeDiRmpcr8tukR+jG1ynL0zrzumFf4Cg359 mitja@mitja-Precision-WorkStation-370");
	}
	private static final User USER1 = new User("mnikolaus", "mitja", "nikolaus", KEYS1);
	private static final User USER2 = new User("hschmidt", "hans", "schmidt", KEYS2);
	private static final User USER3 = new User("hschmidt", "herbert", "schmidt", KEYS1);	
	
	@BeforeClass
	public static void setUp() throws SQLException{
		database = new InMemoryUserDB();
	}
	
	@Test
	public void testAdd() throws SQLException{
		assertEquals(0,database.getNumberOfUsers());
		database.add(USER1);
		assertEquals(1,database.getNumberOfUsers());
	}
	
	@Test(expected=UserDB.DuplicateUIDException.class)
	public void testAddFails() throws SQLException{
		database.add(USER2);
		database.add(USER3);
	}
	
	@Test
	public void testGet() throws SQLException{		
		database.add(USER1);
		assertTrue(USER1.equals(database.get(USER1)));
	}

	@Test
	public void testGetUserWhoHasNoKeys() throws SQLException{
		User p = new User("mnikolaus", "mitja", "nikolaus", new ArrayList<String>());
		database.add(p);
		assertTrue(p.equals(database.get(p)));
	}
	
	@Test(expected=UserDB.RecordNotFoundException.class)
	public void testGetFails() throws Exception{
		database.add(USER1);
		database.get(USER2);
	}
	
	@Test(expected=UserDB.RecordNotFoundException.class)
	public void testDelete() throws SQLException{
		database.add(USER1);
		database.delete(USER1);
		database.get(USER1);
	}
		
	@Test
	public void testUpdate() throws SQLException{
		database.add(USER2);
		database.update(USER3);
		assertTrue(USER3.equals(database.get(USER3)));
	}
	
	@Test(expected=UserDB.RecordNotFoundException.class)
	public void testUpdateFails() throws SQLException{
		database.update(USER3);
	}
	
	@Test
	public void testAddKey() throws SQLException{
		database.add(USER1);		
		database.addKey(USER1, KEYS2.get(0));
		assertEquals(KEYS2.get(0), database.get(USER1).getPublicKeys().get(2));
	}
		
	@Test
	public void testAddDuplicateKeys() throws SQLException{
		database.add(USER1);		
		database.addKey(USER1, KEYS1.get(0));
		assertTrue(USER1.equals(database.get(USER1)));
	}
	
	@After
	public void cleanUp() throws SQLException{
		database.deleteAllEntries();
	}	
}