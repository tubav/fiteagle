package org.fiteagle.ui.infinity;

import java.net.URI;
import java.net.URISyntaxException;

import junit.framework.Assert;

import org.fiteagle.ui.infinity.model.InfinityInfrastructure;
import org.junit.Before;
import org.junit.Test;

public class InfinityClientTest {

	private InfinityClient client;

	@Before
	public void setup() throws URISyntaxException {
		//URI uri = new URI("http://www.xipi.eu");
		//this.client = new InfinityClientWeb(uri);
		this.client = new InfinityClientMock();
	}

	@Test
	public void testGetInfrastructuresById() {
		InfinityInfrastructure result = this.client.getInfrastructuresById(900);
		Assert.assertEquals(900, result.getId());
		Assert.assertEquals("Autonomous Province of Trento",
				result.getOrganization());
	}
}
