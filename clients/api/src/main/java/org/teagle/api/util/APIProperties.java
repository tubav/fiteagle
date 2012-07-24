package org.teagle.api.util;

import java.util.Properties;

import teagle.vct.util.AbstractProperties;

public class APIProperties extends AbstractProperties {
	private static final String API_PROPERTIES = "api.properties";
	private static final String PROP_PWD = "pwd";
	private static final String PROP_USER = "user";
	private static final String PROP_REQ = "requrl";
	private static final String PROP_REP = "repourl";
	
	public static String getRepoUrl() {
		return cleanProperties(APIProperties.getProperties().getProperty(
				APIProperties.PROP_REP));
	}

	public static String getReqUrl() {
		return cleanProperties(APIProperties.getProperties().getProperty(
				APIProperties.PROP_REQ));
	}

	public static String getUser() {
		return cleanProperties(APIProperties.getProperties().getProperty(
				APIProperties.PROP_USER));
	}

	public static String getPassword() {
		return cleanProperties(APIProperties.getProperties().getProperty(
				APIProperties.PROP_PWD));
	}
	
	public static Properties getProperties() {
		return getProperties(API_PROPERTIES);
	}	
}
