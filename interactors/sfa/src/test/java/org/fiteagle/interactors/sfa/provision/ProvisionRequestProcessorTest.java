package org.fiteagle.interactors.sfa.provision;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.easymock.EasyMock;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapterStatus;
import org.fiteagle.adapter.stopwatch.StopwatchAdapter;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.Credentials;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProvisionRequestProcessorTest {
	private ResourceAdapterManager resourceAdapterManager;
	private ListCredentials listCredentials;
	private ProvisionRequestProcessor processor;
	private List<String> urns;
	private URN sliceURN = new URN("urn:publicid:IDN+fiteagletest+slice+testtest");
	private URN notExistingSlice = new URN("urn:publicid:IDN+fiteagletest+slice+doesNotExist");
	private ProvisionOptions options;
	@Before
	public void setUp() throws Exception {
		processor = new ProvisionRequestProcessor();
		buildMocks();
		processor.setResourceManager(resourceAdapterManager);
		urns = new LinkedList();
		createOptionsRSv3();
		listCredentials = getListCredentials();
		Group g = new Group(sliceURN.getSubjectAtDomain(), "someone");
		GroupDBManager.getInstance().addGroup(g);
		
	}

	private void createOptionsRSv3() {
		options = new ProvisionOptions();
		Geni_RSpec_Version rspecVersion = new Geni_RSpec_Version();
		rspecVersion.setType("GENI");
		rspecVersion.setVersion("3");
		options.setGeni_rspec_version(rspecVersion);
	}

	private void buildMocks() {
		
		resourceAdapterManager = EasyMock.createMock(ResourceAdapterManager.class);
		ResourceAdapter adapter = new StopwatchAdapter();
		adapter.setExclusive(true);
		Date allocationExpirationTime = Calendar.getInstance().getTime();
		allocationExpirationTime.setTime(allocationExpirationTime.getTime() + 1 * 1000 * 60);
		adapter.setExpirationTime(allocationExpirationTime);
		adapter.setStatus(ResourceAdapterStatus.Reserved);
		List<ResourceAdapter> resourceAdapters = new LinkedList<>();
		resourceAdapters.add(adapter);
		EasyMock.expect(resourceAdapterManager.getResourceAdapterInstancesById((List<String>) EasyMock.anyObject())).andReturn(resourceAdapters);
		EasyMock.expectLastCall().anyTimes();
		resourceAdapterManager.renewExpirationTime(EasyMock.anyObject(String.class), EasyMock.anyObject(Date.class));
		EasyMock.replay(resourceAdapterManager);
		
	}

	@After
	public void tearDown() throws Exception {
		GroupDBManager.getInstance().deleteGroup(sliceURN.getSubjectAtDomain());
	}

	@Test
	public void testProvisionOfSlice() {
		urns.add(sliceURN.toString());
		ProvisionResult  result = processor.processRequest(urns, listCredentials, options);
		assertEquals(result.getCode().getGeni_code(), 0);
	}
	
	@Test
	public void testProvisionOfNotExistingSlice(){
		urns.add(notExistingSlice.toString());
		ProvisionResult result = processor.processRequest(urns, listCredentials, options);
		assertEquals(GENI_CodeEnum.SEARCHFAILED.getValue(), result.getCode().getGeni_code());
	}
	
	@Test
	public void testProvisionWithBadArguments(){
		urns.add("this is not an URN");
		ProvisionResult result = processor.processRequest(urns, listCredentials, options);
		assertEquals(GENI_CodeEnum.BADARGS.getValue(), result.getCode().getGeni_code());
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
