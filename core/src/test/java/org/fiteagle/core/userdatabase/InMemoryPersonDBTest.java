package org.fiteagle.core.userdatabase;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class InMemoryPersonDBTest {

	private static PersonDB database;
	
	private static final ArrayList<String> KEYS1 = new ArrayList<String>();
	private static final ArrayList<String> KEYS2 = new ArrayList<String>();
	private static final Person PERSON1 = new Person("mnikolaus", "mitja", "nikolaus", KEYS1);
	private static final Person PERSON2 = new Person("hschmidt", "hans", "schmidt", KEYS2);
	private static final Person PERSON3 = new Person("hschmidt", "herbert", "schmidt", KEYS1);	
	
	@BeforeClass
	public static void setUp() throws SQLException{
		database = new InMemoryPersonDB();
		KEYS1.add("ssh-rsa AAAAB3NzaC1ydzkACAADAQABAAABAQCybYW812Eb9aTxrXnFgIG7etEijX3/+pWlurrYpvqXi6rl0LZWnotWaC0TeBKWMwDAwPDnSeMxGtYDrZXQJNurrdsmYtzJSL79hhLJqsQCv4s5tK+d/GPRsPSfsGI0A+ckDiQ7yXErUSIgcmGXC4Jo6tuN0QI3x3wIlivDMwkVxZm4m82LwqVECtodnvzbct13a9rIhgjGTRyXXsLVt+X1MB45OlQJ+CWWkaO3emRHDDktZAjkhXNXYKeDtXj4yIhy+jPLTSKwsghCQD79U+sQEDY+RBPu7Td5GzQx8tFdFAjghZaWgeD3iRmpcr8tukR+jG1ynL0zrzumFf4Cg359 mitja@mitja-Precision-WorkStation-370");
		KEYS1.add("ssh-rsa AAAAB3NzaC1yc2EACAADATZCAAABAQCybYW812Eb9aTxrXnFgIG7etEijX3/+pWlurrYpvqXi6rl0LZWnotWaC0TeBKWMwDAwPDnSeMxGtYDrZXQJNurrdsmYtzJSL79hhLJqsQCv4s5tK+d/GPRsPSfsGI0A+ckDiQ7yXErUSIgcmGXC4Jo6tuN0QI3x3wIlivDMwkVxZm4m82LwqVECtodnvzbct13a9rIhgjGTRyXXsLVt+X1MB45OlQJ+CWWkaO3emRHDDktZAjkhXNXYKeDtXj4yIhy+jPLTSKiObJnCQD79U+sQEDY+RBPu7Td5GzQx8tFd34gesatWgeDiRmpcr8tukR+jG1ynL0zrzumFf4Cg359 mitja@mitja-Precision-WorkStation-370");
		KEYS2.add("ssh-rsa AAAAB3NzaC1yc2EACAADAQABAAABAQCybYW812Eb9aTxrXnFgIG7etEijX3/+pWlurrYpvqXi6rl0LZWnotWaC0TeBKWMwDAwPDnSeMxGtYDrZXQJNurrdsmYtzJSL79hhLJqsQCv4s5tK+d/GPRsPSfsGI0A+ckDiQ7yXErUSIgcmGXC4Jo6tuN0QI3x3wIlivDMwkVxZm4m82LwqVECtodnvzbct13a9rIhgjGTRyXXsLVt+X1MB45OlQJ+CWWkaO3emRHDDktZAjkhXNXYKeDtXj4yIhy+jPLTSKiObJnCQD79U+sQEDY+RBPu7Td5GzQx8tFdFAjghZaWgeDiRmpcr8tukR+jG1ynL0zrzumFf4Cg359 mitja@mitja-Precision-WorkStation-370");
	}
	
	@Test
	public void testAdd() throws SQLException{
		assertEquals(0,database.getNumberOfUsers());
		database.add(PERSON1);
		assertEquals(1,database.getNumberOfUsers());
	}
	
	@Test(expected=PersonDB.DuplicateUIDException.class)
	public void testAddFails() throws SQLException{
		database.add(PERSON2);
		database.add(PERSON3);
	}
	
	@Test
	public void testGet() throws SQLException{		
		database.add(PERSON1);
		assertTrue(PERSON1.equals(database.get(PERSON1)));
	}
	
	@Test(expected=PersonDB.RecordNotFoundException.class)
	public void testGetFails() throws Exception{
		database.add(PERSON1);
		database.get(PERSON2);
	}
	
	@Test(expected=PersonDB.RecordNotFoundException.class)
	public void testDelete() throws SQLException{
		database.add(PERSON1);
		database.delete(PERSON1);
		database.get(PERSON1);
	}
		
	@Test
	public void testUpdate() throws SQLException{
		database.add(PERSON2);
		database.update(PERSON3);
		assertTrue(PERSON3.equals(database.get(PERSON3)));
	}
	
	@Test(expected=PersonDB.RecordNotFoundException.class)
	public void testUpdateFails() throws SQLException{
		database.update(PERSON3);
	}
	
	@Test
	public void testAddKey() throws SQLException{
		database.add(PERSON1);		
		database.addKey(PERSON1, KEYS2.get(0));
		assertEquals(KEYS2.get(0), database.get(PERSON1).getPublicKeys().get(2));
	}
	
	@Test
	public void testAddDeleteMany() throws SQLException{
		database.add(PERSON1);
		database.add(PERSON3);
		database.update(PERSON2);
		database.delete(PERSON1.getUID());
		database.add(PERSON1);
		assertTrue(PERSON1.equals(database.get(PERSON1)));
	}
	
	@Test
	public void testAddDuplicateKeys() throws SQLException{
		database.add(PERSON1);		
		database.addKey(PERSON1, KEYS1.get(0));
		assertTrue(PERSON1.equals(database.get(PERSON1)));
	}
	
	@After
	public void cleanUp() throws SQLException{
		database.deleteAllEntries();
	}	
}