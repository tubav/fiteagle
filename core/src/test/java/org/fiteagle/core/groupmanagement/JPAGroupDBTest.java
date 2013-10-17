package org.fiteagle.core.groupmanagement;

import org.fiteagle.core.groupmanagement.JPAGroupDB.CouldNotFindGroup;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JPAGroupDBTest {

	private static Group g;
	private static JPAGroupDB groupDB;

	@BeforeClass
	public static void setUp() throws Exception {
		g = new Group("testGr", "testOwner");
		groupDB = JPAGroupDB.getInMemoryInstance();
		groupDB.add(g); 
	}

	@After
	public void tearDown() throws Exception {
		
	}

	
	
	@Test
	public void testGetGroup(){
		Group g2 = groupDB.get(g.getGroupId());
		System.out.println(g2);
		Assert.assertNotNull(g2);
	}
	
	@Test(expected=CouldNotFindGroup.class)
	public void testDeleteGroup(){
		groupDB.delete(g);
		Group g3 = groupDB.get(g.getGroupId());
		System.out.println(g3);
		
	}

}
