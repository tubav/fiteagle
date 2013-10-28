package org.fiteagle.core;

import static org.junit.Assert.fail;

import java.util.List;

import org.easymock.EasyMock;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.ResourceAdapterManager.ResourceNotFound;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ResourceAdapterManagerTest {

	ResourceAdapterManager ram;
	ResourceAdapter ra;
	@Before
	public void setUp() throws Exception {
		ram = ResourceAdapterManager.getInstance(false);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testCreation() {
		Assert.assertNotNull(ram);
	}
	
	@Test
	public void testGetResourceAdapter(){
		List<ResourceAdapter> adapters = getMockAdapterList();
	}
	
	@Test
	public void testAddResourceAdapter(){
		addExlusiveMockAdapter();
	}
	
	@Test
	public void testAddAndGetResourceAdapter(){
		addExlusiveMockAdapter();
		List<ResourceAdapter> adapters = getMockAdapterList();
		Assert.assertEquals(1, adapters.size());
		
	}
	
	@Test
	public void testGetResourceAdaperById(){
		addExlusiveMockAdapter();
		ra = ram.getResourceAdapterById("mockup");
		Assert.assertNotNull(ra);
	}
	
	@Test
	public void testDeleteResourceAdapter(){
		addExlusiveMockAdapter();
		ram.deleteResourceAdapter("mockup");
		List<ResourceAdapter> adapters= getMockAdapterList();
		Assert.assertEquals(0, adapters.size());
	}
	
	@Test(expected=ResourceNotFound.class)
	public void testFindNonExistingResource(){
		ResourceAdapter doesNotExist = ram.getResourceAdapterById("DoesNotExist");
	}
	
//	@Test
//	public void testBookResource(){
//		addExlusiveMockAdapter();
//		ram.bookResource("mockup");
//		ResourceAdapter ra = ram.getResourceAdapterInstance("mockup");
//		Assert.assertNotNull(ra);
//	}
	
//	@Test(expected=RuntimeException.class)
//	public void testBookExclusiveResourceTwice(){
//		addExlusiveMockAdapter();
//		ram.bookResource("mockup");
//		ram.bookResource("mockup");
//	}
//	
	

	private List<ResourceAdapter> getMockAdapterList() {
		List<ResourceAdapter> adapters = ram.getResourceAdapters();
		Assert.assertNotNull(adapters);
		return adapters;
	}

	private void addExlusiveMockAdapter() {
		ra = EasyMock.createMock(ResourceAdapter.class);
		EasyMock.expect(ra.getId()).andReturn("mockup");
		EasyMock.expectLastCall().anyTimes();
		EasyMock.expect(ra.isAvailable()).andReturn(true);
		EasyMock.expect(ra.isExclusive()).andReturn(true);
		ra.setAvailable(false);
		EasyMock.expectLastCall();
		EasyMock.expect(ra.isAvailable()).andReturn(false);
		EasyMock.replay(ra);
		ram.addResourceAdapter(ra);
	}
	
	private void addNonExlusiveMockAdapter() {
		ra = EasyMock.createMock(ResourceAdapter.class);
		EasyMock.expect(ra.getId()).andReturn("mockup");
		EasyMock.expectLastCall().anyTimes();
		EasyMock.expect(ra.isAvailable()).andReturn(true);
		EasyMock.expectLastCall().anyTimes();
		EasyMock.expect(ra.isExclusive()).andReturn(false);
		EasyMock.expectLastCall().anyTimes();

		EasyMock.expectLastCall();
		EasyMock.replay(ra);
		ram.addResourceAdapter(ra);
	}

}
