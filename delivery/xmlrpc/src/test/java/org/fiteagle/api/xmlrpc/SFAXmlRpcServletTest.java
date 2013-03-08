package org.fiteagle.api.xmlrpc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
	public void testGetVersionStatic() throws IOException {
		final String getVersionResult = this.servlet.getVersionStatic();
		Assert.assertNotNull(getVersionResult);
		Assert.assertFalse(getVersionResult.isEmpty());
	}

	@Test
	public void testListResourcesStatic() throws IOException {
		final String listResourcesResult = this.servlet.listResourcesStatic();
		Assert.assertNotNull(listResourcesResult);
		Assert.assertFalse(listResourcesResult.isEmpty());
	}

	// @Test
	// TODO: @Daniel: to be fixed
	public void testListResourcesCall() throws IOException {
		OutputStream outputStream = new ByteArrayOutputStream();
		InputStream inputStream = SFAXmlRpcServlet
				.getFileAsInputStream("/org/fiteagle/delivery/xmlrpc/sfa/listresources_request.xml");
		this.servlet.handleRequest(inputStream, outputStream);
	}

	@Test
	public void testGetVersionCall() throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		InputStream inputStream = SFAXmlRpcServlet
				.getFileAsInputStream("/org/fiteagle/delivery/xmlrpc/sfa/getversion_request.xml");
		this.servlet.handleRequest(inputStream, outputStream);
		String resultXML = outputStream.toString();
		Assert.assertFalse(resultXML.isEmpty());
	}
}
