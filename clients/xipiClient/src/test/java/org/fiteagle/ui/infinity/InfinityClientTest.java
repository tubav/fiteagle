package org.fiteagle.ui.infinity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import junit.framework.Assert;

import org.fiteagle.ui.infinity.model.InfinityArrayList;
import org.fiteagle.ui.infinity.model.InfinityInfrastructure;
import org.fiteagle.ui.infinity.model.InfinityValueID;
import org.junit.Before;
import org.junit.Test;

public class InfinityClientTest {

	private InfinityClient client;

	@Before
	public void setup() throws URISyntaxException {
		URI uri = new URI("http://www.xipi.eu");
		this.client = new InfinityClientWeb(uri);
//		this.client = new InfinityClientMock();
	}

	@Test
	public void testGetInfrastructuresById() {
		InfinityInfrastructure result = this.client.getInfrastructuresById(900);
		Assert.assertEquals(900, result.getId());
		Assert.assertEquals("Autonomous Province of Trento",
				result.getOrganization());
	}
	
	@Test
	public void testSearchInfrastructures() {
		ArrayList<InfinityValueID> result = this.client.searchInfrastructures();
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testGetTechnicalComponents(){
		ArrayList<InfinityValueID> result = this.client.getTechnicalComponents();
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testGetComponentDetail(){
		String infrastructureId="900";
		String componentId="242";
		ArrayList<InfinityArrayList> result = this.client.getComponentDetail(infrastructureId, componentId);
		Assert.assertNotNull(result);
	}
	
	
	
}
