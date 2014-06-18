package org.fiteagle.interactors.sfa.delete;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.stopwatch.StopwatchAdapter;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.ResourceAdapterManager.ResourceNotFound;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.groupmanagement.GroupDBManager.GroupNotFound;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.common.Credentials;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class DeleteRequestProcessorTest {

	private DeleteRequestProcessor deleteProc;
	private DeleteOptions deleteOptions;
	private ListCredentials listCredentials;
	private URN sliceUrn;
	private URN sliverURN;
	private List<Credentials> credentialList;
	private Credentials credentials;
	private GroupDBManager groupManager;
	private ResourceAdapterManager resourceAdapterManager;
	private ResourceAdapter dummyAdapter;

	@Before
	public void setUp() throws Exception {
		deleteProc = new DeleteRequestProcessor();
		resourceAdapterManager = EasyMock.createMock(ResourceAdapterManager.class);
		groupManager = EasyMock.createMock(GroupDBManager.class);
		deleteProc.setGroupDBManager(groupManager);
		deleteProc.setResourceManager(resourceAdapterManager);
		deleteOptions = EasyMock.createMock(DeleteOptions.class);
		listCredentials = EasyMock.createMock(ListCredentials.class);
		credentialList = new ArrayList<>();
		credentials = EasyMock.createMock(Credentials.class);
		credentialList.add(credentials);
		sliceUrn = new URN("urn:publicid:IDN+localhost+slice+testSlice");
		sliverURN = new URN("urn:publicid:IDN+localhost+sliver+testSliver");
		dummyAdapter = EasyMock.createMock(ResourceAdapter.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProcessRequestNoCredentials() {
	
		List<String> urns = new LinkedList<>();
		urns.add(sliceUrn.toString());
		DeleteResult result = deleteProc.processRequest(urns, null, deleteOptions);
		Assert.assertEquals(GENI_CodeEnum.BADARGS.getValue(), result.getCode().getGeni_code());
	}

	@Test
	public void testProcessRequestOnNotExistingSlice() {
		List<String> urns = new LinkedList<>();
		setUpCredentialMock();
		EasyMock.expect(groupManager.getGroup((String) EasyMock.anyObject())).andThrow(new GroupNotFound(""));
	
		EasyMock.replay(groupManager);
		urns.add(sliceUrn.toString());
		DeleteResult result = deleteProc.processRequest(urns, listCredentials, deleteOptions);
		Assert.assertEquals(12, result.getCode().getGeni_code());
	}
	
	@Test
	public void testProcessRequestOnNotExistingSliver() {
//		List<String> urns = new LinkedList<>();
//		setUpCredentialMock();
//		resourceAdapterManager.deleteResource((String) EasyMock.anyObject());
//		EasyMock.expectLastCall().andThrow(new ResourceNotFound());
//	
//		EasyMock.replay(resourceAdapterManager);
//
//		urns.add(sliverURN.toString());
//		DeleteResult result = deleteProc.processRequest(urns, listCredentials, deleteOptions);
//		Assert.assertEquals(12, result.getCode().getGeni_code());
	}

	private void setUpCredentialMock() {
		EasyMock.expect(listCredentials.getCredentialsList()).andReturn(credentialList);
		EasyMock.expectLastCall().anyTimes();
		EasyMock.expect(credentials.getGeni_type()).andReturn("geni_sfa");
		EasyMock.replay(listCredentials);
		EasyMock.replay(credentials);
	}
	
	@Test
	public void testDeleteSliver(){
		List<String> urns = new LinkedList<>();
		urns.add(sliverURN.toString());
		setUpCredentialMock();
		resourceAdapterManager.deleteResource((String)EasyMock.anyObject());
		EasyMock.expectLastCall();
		EasyMock.replay(resourceAdapterManager);
	}
	
	
	

}
