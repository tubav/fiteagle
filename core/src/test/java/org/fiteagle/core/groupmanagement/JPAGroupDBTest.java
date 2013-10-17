package org.fiteagle.core.groupmanagement;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JPAGroupDBTest {

	private Group g;
	private JPAGroupDB groupDB;

	@Before
	public void setUp() throws Exception {
		g = new Group("testGr", "testOwner");
		groupDB = JPAGroupDB.getInMemoryInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddGroup() {
		groupDB.add(g); 
	}
	
	@Test
	public void testGetGroup(){
		Group g2 = groupDB.get(g.getGroupId());
		System.out.println(g2);
		Assert.assertNotNull(g2);
	}

}
