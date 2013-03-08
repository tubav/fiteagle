package org.fiteagle.delivery.xmlrpc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import junit.framework.Assert;

import org.fiteagle.delivery.xmlrpc.FITeagleServlet;
import org.fiteagle.delivery.xmlrpc.util.FITeagleUtils;
import org.junit.Before;
import org.junit.Test;

public class SFAXmlRpcServletTest {

	private FITeagleServlet servlet;

	@Before
	public void setUp() {
		this.servlet = new FITeagleServlet();
	}

	@Test
	public void testResultGetVersionStatic() throws IOException {
		final String getVersionResult = this.servlet
				.handleRequestGetVersionStatic();
		Assert.assertNotNull(getVersionResult);
		Assert.assertFalse(getVersionResult.isEmpty());
	}

	@Test
	public void testResultListResourcesStatic() throws IOException {
		final String listResourcesResult = this.servlet
				.handleRequestListResourcesStatic();
		Assert.assertNotNull(listResourcesResult);
		Assert.assertFalse(listResourcesResult.isEmpty());
	}

	// @Test
	// TODO: @Daniel: to be fixed
	public void testCallListResources() throws IOException {
		OutputStream outputStream = new ByteArrayOutputStream();
		InputStream inputStream = FITeagleUtils
				.getFileAsInputStream("/org/fiteagle/delivery/xmlrpc/sfa/listresources_request.xml");
		this.servlet.handleRequest(inputStream, outputStream);
		String resultXML = outputStream.toString();
		Assert.assertFalse(resultXML.isEmpty());
		//TODO: add a more sophisticated assertion here
	}

	@Test
	public void testCallGetVersion() throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		InputStream inputStream = FITeagleUtils
				.getFileAsInputStream("/org/fiteagle/delivery/xmlrpc/sfa/getversion_request.xml");
		this.servlet.handleRequest(inputStream, outputStream);
		String resultXML = outputStream.toString();
		Assert.assertFalse(resultXML.isEmpty());
		//TODO: add a more sophisticated assertion here
	}
}
