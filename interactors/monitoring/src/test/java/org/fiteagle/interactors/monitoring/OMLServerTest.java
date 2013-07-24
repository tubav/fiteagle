package org.fiteagle.interactors.monitoring;

import static org.junit.Assert.*;

import java.net.URISyntaxException;

import org.fiteagle.interactors.monitoring.client.OMLClientMock;
import org.junit.Before;
import org.junit.Test;

public class OMLServerTest {
	
	OMLClientMock client;
	
	@Before
	public void setup() {
		client = new OMLClientMock();
	}

	@Test
	public void testPushMonitoringData() {
		client.run();
	}
	

}
