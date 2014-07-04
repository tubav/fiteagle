package org.fiteagle.interactors.sfa.allocate;

import java.io.IOException;
import java.sql.NClob;
import java.util.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.stopwatch.StopwatchAdapter;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.Credentials;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.rspec.ext.ObjectFactory;
import org.fiteagle.interactors.sfa.rspec.ext.Property;
import org.fiteagle.interactors.sfa.rspec.ext.Resource;
import org.fiteagle.interactors.sfa.rspec.request.RSpecContents;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class AllocateTest {

	private AllocateRequestProcessor allocateRequestProcessor;
	private RSpecContents node;
//	private JAXBElement<NodeContents> jaxBNode;
//	private JAXBElement<Resource> jaxBResource;
//	private NodeContents nodeContents;
	private Resource resourceMock;
	Group g = null;
	private ListCredentials listCredentials;
	private RSpecContents testResourceRSpec;
	private URN urn;
	private ResourceAdapterManager resourceAdapterManager;

	@Before
	public void setUp() throws Exception {
		urn = new URN("urn:publicid:IDN+fiteagletest+slice+testtest");
		
		g = new Group("testtest@fiteagletest", "test@test");
		GroupDBManager.getInstance().addGroup(g);
	
		buildMockObjects();
		listCredentials = getListCredentials();
		testResourceRSpec = getTestResourceRspec();
		
		allocateRequestProcessor = new AllocateRequestProcessor();
		allocateRequestProcessor.setResourceManager(resourceAdapterManager);
	}

	private void buildMockObjects() {
		node = EasyMock.createMock(RSpecContents.class);
		resourceAdapterManager = EasyMock.createMock(ResourceAdapterManager.class);
		resourceAdapterManager.addResourceAdapterInstance((ResourceAdapter) EasyMock.anyObject());
		EasyMock.expectLastCall();
		ResourceAdapter adapter = new StopwatchAdapter();
		adapter.setExclusive(true);
		Date allocationExpirationTime = Calendar.getInstance().getTime();
		allocationExpirationTime
				.setTime(allocationExpirationTime.getTime() + 1 * 1000 * 60);
		adapter.setExpirationTime(allocationExpirationTime);
		List<ResourceAdapter> resourceAdapters = new LinkedList<>();
		resourceAdapters.add(adapter);
		EasyMock.expect(resourceAdapterManager.getResourceAdapterInstancesById((List<String>) EasyMock.anyObject())).andReturn(resourceAdapters);
		EasyMock.expectLastCall().anyTimes();
//
		EasyMock.expect(resourceAdapterManager.getResourceAdapterInstance("componentID")).andReturn(resourceAdapters.get(0));
		EasyMock.expectLastCall().anyTimes();
//		
		resourceAdapterManager.setExpires(EasyMock.anyObject(String.class), EasyMock.anyObject(Date.class));
		EasyMock.expectLastCall().anyTimes();
//	
//		
//		
		EasyMock.replay(resourceAdapterManager);
		
	}

	@After
	public void tearDown() throws Exception {
		GroupDBManager.getInstance().deleteGroup("testtest@fiteagletest");

	}

//	@Test
//	public void testAllocateRessource() throws IOException {
//		
//		resourceMock = EasyMock.createMock(Resource.class);
//		jaxBResource = EasyMock.createMock(JAXBElement.class);
//		LinkedList<Object> objects = new LinkedList<>();
//		objects.add(jaxBResource);
//		
//		EasyMock.expect(node.getAnyOrNodeOrLink()).andReturn(objects);
//		EasyMock.expectLastCall().anyTimes();
//		EasyMock.expect(jaxBResource.getValue()).andReturn(resourceMock);
//		EasyMock.expectLastCall().anyTimes();
//		EasyMock.expect(resourceMock.getClass());
//		List<Property> props = new LinkedList<Property>();
//		Property type = new Property();
//		type.setName("type");
//		type.setValue(StopwatchAdapter.class.getName());		
//		Property id = new Property();
//		id.setName("id");
//		id.setValue("componentID");
//		props.add(id);
//		props.add(type);
//		EasyMock.expect(resourceMock.getProperty()).andReturn(props);
//		EasyMock.replay(resourceMock);
//		EasyMock.replay(node);
//		EasyMock.replay(jaxBResource);
//		
//		AllocateResult allocateResult = allocateRequestProcessor.processRequest(urn.toString(), listCredentials, node, null);
//		
//		System.out.println(allocateResult.getValue().getGeni_rspec());
//		g = GroupDBManager.getInstance().getGroup(g.getGroupId());
//		Assert.assertNotNull(g.getResources().get(0));
//
//	}

	
//	@Test
//	public void testAllocateNode() throws IOException {
//		buildNodeMock();
//		AllocateResult allocateResult = allocateRequestProcessor.processRequest(urn.toString(), listCredentials, node, null);
//		System.out.println(allocateResult.getValue().getGeni_rspec());
//		g = GroupDBManager.getInstance().getGroup(g.getGroupId());
//		Assert.assertNotNull(g.getResources().get(0));
//	}

//	private void buildNodeMock() {
//		nodeContents = EasyMock.createMock(NodeContents.class);
//		jaxBNode = EasyMock.createMock(JAXBElement.class);
//		LinkedList<Object> objects = new LinkedList<>();
//		objects.add(jaxBNode);
//		
//		EasyMock.expect(node.getAnyOrNodeOrLink()).andReturn(objects);
//		EasyMock.expectLastCall().anyTimes();
//		EasyMock.expect(jaxBNode.getValue()).andReturn(nodeContents);
//		EasyMock.expectLastCall().anyTimes();
//		EasyMock.expect(nodeContents.getClass());
//		EasyMock.expect(nodeContents.getComponentId()).andReturn("componentID");
//		EasyMock.expectLastCall().anyTimes();
//		EasyMock.replay(node);
//		EasyMock.replay(jaxBNode);
//		EasyMock.replay(nodeContents);
//	}
	
//	@Test
//	public void testAllocateExclusiveNodeTwice(){
//		buildNodeMock();
//
//		AllocateResult allocateResult = allocateRequestProcessor.processRequest(urn.toString(), listCredentials, node, null);
//		AllocateResult allocateResult2 = allocateRequestProcessor.processRequest(urn.toString(), listCredentials, node, null);
//		System.out.println(allocateResult.getValue().getGeni_rspec());
//		System.out.println(allocateResult2.getValue().getGeni_rspec());
//		g = GroupDBManager.getInstance().getGroup(g.getGroupId());
//		Assert.assertEquals(1, g.getResources().size());
//		Assert.assertEquals(14, allocateResult2.getCode().getGeni_code());
//	}
	
//	@Test
//	public void testAllocateOnUnparsableURNlice(){
//		AllocateResult result = allocateRequestProcessor.processRequest("unparseableURN", listCredentials,testResourceRSpec, null);
//		Assert.assertEquals(1, result.getCode().getGeni_code());
//	}
//	
//	@Test
//	public void testAllocateForNotExistingSlice(){
//		URN doesNotExist = new URN("urn:publicid:IDN+fiteagletest+slice+doesnotexist");
//		AllocateResult result =  allocateRequestProcessor.processRequest(doesNotExist.toString(), listCredentials, testResourceRSpec, null);
//		Assert.assertEquals(12, result.getCode().getGeni_code());
//	}
	
	 

	private RSpecContents getTestResourceRspec() {

		RSpecContents testRSpec = new RSpecContents();
		List<Object> fiteagleResources = testRSpec.getAnyOrNodeOrLink();
		Resource fiteagleResource1 = new Resource();
		List<Property> properties = fiteagleResource1.getProperty();
		Property idProperty = new Property();
		idProperty.setName("id");
		idProperty.setValue("TestId");
		Property typeProperty = new Property();
		typeProperty.setName("type");
		typeProperty
				.setValue("org.fiteagle.adapter.stopwatch.StopwatchAdapter");

		properties.add(idProperty);
		properties.add(typeProperty);
		fiteagleResources.add(new ObjectFactory()
				.createResource(fiteagleResource1));
		return testRSpec;
	}

	private ListCredentials getListCredentials() {
		Credentials credentials = new Credentials();
		credentials.setGeni_type(Authorization.GENI_TYPE);
		credentials.setGeni_version(Authorization.GENI_VERSION);
		listCredentials = new ListCredentials();
		listCredentials.getCredentialsList().add(credentials);
		return listCredentials;
	}

}
