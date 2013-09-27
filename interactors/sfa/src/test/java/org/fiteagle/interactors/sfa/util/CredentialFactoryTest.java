package org.fiteagle.interactors.sfa.util;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;

import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.fiteagle.core.aaa.CertificateAuthority;
import org.fiteagle.core.aaa.KeyStoreManagement;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Credential;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.SignedCredential;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CredentialFactoryTest {

	X509Certificate userCert;
	Credential c1;
	URN target = new URN("urn:publicid:IDN+av.tu-berlin.de+user+test");
	GroupDBManager groupManager;


	@Before
	public void setUp() throws Exception {
		userCert = createMock(X509Certificate.class);
		expect(userCert.getIssuerX500Principal()).andReturn(
				new X500Principal("cn=issuer"));
		expectLastCall().times(2);
		expect(userCert.getSubjectX500Principal()).andReturn(
				new X500Principal("cn=user"));
		expectLastCall().times(2);
		expect(userCert.getEncoded()).andReturn(new byte[] {});
		expectLastCall().times(6);
		expect(userCert.getSubjectAlternativeNames()).andReturn(
				createSubjectAlternativeNamesCollection());
		expectLastCall().times(3);
		replay(userCert);
		groupManager = EasyMock.createMock(GroupDBManager.class);
		
		CredentialFactory.setGroupDBManager(groupManager);
		X509Certificate sliceAuthCert = EasyMock.createMock(X509Certificate.class);
		EasyMock.expect(sliceAuthCert.getEncoded()).andReturn(new byte[]{});
		EasyMock.expectLastCall().anyTimes();
		EasyMock.replay(sliceAuthCert);
		KeyStoreManagement keyStoreManagement = EasyMock.createMock(KeyStoreManagement.class);
		EasyMock.expect(keyStoreManagement.getSliceAuthorityCert()).andReturn(sliceAuthCert);
		EasyMock.expectLastCall().anyTimes();
		EasyMock.expect(keyStoreManagement.getResourceCertificate(EasyMock.anyObject(String.class))).andReturn(sliceAuthCert);
		EasyMock.replay(keyStoreManagement);
		CredentialFactory.setKeystoreManagement(keyStoreManagement);
		
		
		c1 = CredentialFactory.newCredential(userCert, target);
		
		
		Group g = new Group("mySlice@localhost", "test@av.tu-berlin.de");
	//	groupManager.addGroup(g);
		//EasyMock.expectLastCall();
		EasyMock.expect(groupManager.getGroup(EasyMock.anyObject(String.class))).andReturn(g);
	
		EasyMock.replay(groupManager);
		CertificateAuthority ca = CertificateAuthority.getInstance();
		ca.createCertificate(g);

	}

	private Collection<List<?>> createSubjectAlternativeNamesCollection() {
		Collection<List<?>> collection = new LinkedList<List<?>>();
		List<Object> names = new LinkedList<>();
		names.add(new Integer(6));
		names.add(new URN("urn:publicid:IDN+av.tu-berlin.de+user+test")
				.toString());
		collection.add(names);
		return collection;
	}



	@Test
	public void checkTypePrivlege() {
		Assert.assertEquals("privilege", c1.getType());
	}

	@Test
	public void getOwnerGIDTest() {
		Assert.assertNotNull(c1.getOwnerGid());
		System.out.println("out: " + c1.getOwnerGid());
	}

	@Test
	public void getOwnerURNTest() {
		Assert.assertEquals("urn:publicid:IDN+av.tu-berlin.de+user+test",
				c1.getOwnerURN());
	}

	@Test
	public void getTargetGID() {
		Assert.assertNotNull(c1.getTargetGid());
	}

	@Test
	public void getSliceCredential() {
		Credential sliceCredential = CredentialFactory.newCredential(userCert,
				new URN("urn:publicid:IDN+localhost+slice+mySlice"));
		Assert.assertNotNull(sliceCredential);

	}

	@Test
	public void getTargetURNTest() {
		Assert.assertNotNull(c1.getTargetURN());
		System.out.println(c1.getTargetURN());
	}

	@Test
	public void getExpirationDateTest() {
		Assert.assertNotNull(c1.getExpires());
	}
	
	@Test
	public void getPrivilegesTest(){
		Assert.assertNotNull(c1.getPrivileges());
	}
	
	@Test
	public void signCredentialTest(){
		String signed = CredentialFactory.signCredential(c1);
		Assert.assertNotNull(signed);
		System.out.println(signed);
	}
	
	

	@After
	public void cleanUp() {
		
	}

	
}
