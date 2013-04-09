package org.fiteagle.core.userdatabase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class UserDatabaseTest {

	private InMemoryPersonDB database;
	
	private final ArrayList<String> KEYS = new ArrayList<String>();
	private final Person PERSON1 = new Person("nia", "mitja", "nikolaus", KEYS);
	private final Person PERSON2 = new Person("hschmidt", "hans", "schmidt", KEYS);
	private final Person PERSON3 = new Person("hschmidt", "herbert", "schmidt", KEYS);
	
	
	@Before
	public void setUp(){
		this.database = new InMemoryPersonDB();
	}
		
	@Test
	public void testAdd(){		
		addPerson(PERSON1);
		assertTrue(database.getSize() == 1);
	}
	
	@Test(expected=PersonDB.DuplicateUID.class)
	public void testAddFails(){
		database.add(PERSON2);
		database.add(PERSON3);
	}
		
	private void addPerson(Person p){
		try {		
			database.add(p);
		} catch (PersonDB.DuplicateUID e){
			fail();
			e.printStackTrace();
		}
	}	
	
	
	@Test
	public void testGet(){		
		addPerson(PERSON1);		
		try {
			assertEquals(PERSON1, database.get(PERSON1));
		} catch (PersonDB.RecordNotFound e) {
			fail();
			e.printStackTrace();
		}
	}
	
	@Test(expected=PersonDB.RecordNotFound.class)
	public void testGetFails() throws PersonDB.RecordNotFound{
		addPerson(PERSON1);
		database.get(PERSON2);
	}

	@Test(expected=PersonDB.RecordNotFound.class)
	public void testDelete() throws PersonDB.RecordNotFound{
		addPerson(PERSON1);
		database.delete(PERSON1);
		database.get(PERSON1);
	}
		
	@Test
	public void testUpdate(){
		addPerson(PERSON2);
		try {
			database.update(PERSON3);
		} catch (PersonDB.RecordNotFound e) {
			fail();
			e.printStackTrace();
		}
	}
	
	@Test(expected=PersonDB.RecordNotFound.class)
	public void testUpdateFails() throws PersonDB.RecordNotFound{
		database.update(PERSON3);
	}
	
	@Test
	public void testAddKey(){
		addPerson(PERSON1);
		database.addKey(PERSON1, "a public key");
	}
}
