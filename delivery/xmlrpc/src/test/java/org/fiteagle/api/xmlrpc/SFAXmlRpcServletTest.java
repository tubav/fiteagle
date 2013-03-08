package org.fiteagle.api.xmlrpc;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class SFAXmlRpcServletTest {

	private SFAXmlRpcServlet servlet;

	@Before
	public void setUp() {
		this.servlet = new SFAXmlRpcServlet();
	}
	
	@Test
	public void testGetVersionTemp() throws IOException {
		final String getVersionResult = this.servlet.getVersionStatic();
		Assert.assertNotNull(getVersionResult);
		Assert.assertFalse(getVersionResult.isEmpty()); 
	}

	@Test
	public void testListResourcesTemp() throws IOException {
		final String listResourcesResult = this.servlet.listResourcesStatic(); 
		Assert.assertNotNull(listResourcesResult);
		Assert.assertFalse(listResourcesResult.isEmpty());
	}

}
