package org.teagle.api.util;

import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;

import teagle.vct.util.AbstractProperties;

public class APIPropertiesTest {

	@Test
	public void testGetAPIProperties() {
		Assert.assertNotNull(APIProperties.getProperties());
		Assert.assertNotNull(APIProperties.getRepoUrl());
	}
	
	@Test
	public void testNotExistingProperty() {
		Properties expectedProperties = new Properties();
		Properties actualProperties = AbstractProperties.getProperties("");
		Assert.assertEquals(expectedProperties, actualProperties);
		
		String expectedString = null;
		String actualString = actualProperties.getProperty("foo");
		Assert.assertEquals(expectedString, actualString);
	}
}
