package org.fiteagle.adapter.openstackvmadapter.client;

import static org.junit.Assert.*;

import org.fiteagle.adapter.openstackvmadapter.client.model.Images;
import org.fiteagle.adapter.openstackvmadapter.client.model.Server;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.woorea.openstack.nova.model.Flavors;

public class OfflineTestClientTest {
	
	OfflineTestClient offlineClient;
	@Before
	public void setup() {
		offlineClient = new OfflineTestClient();
	}

	@Test
	public void testListFlavors() {
		Flavors flavors = offlineClient.listFlavors();
		Assert.assertNotNull(flavors);
		Assert.assertTrue(flavors instanceof Flavors);
	}

	@Test
	public void testListImages() {
		Images images = offlineClient.listImages();
		Assert.assertNotNull(images);
		Assert.assertTrue(images instanceof Images);
	}

	@Test
	public void testCreateServer() {
		Server server = offlineClient.createServer();
		Assert.assertNotNull(server);
		Assert.assertTrue(server instanceof Server);
	}
	
	@Test
	public void testGetServerDetails(){
		Server server = offlineClient.getServerDetails("");
		Assert.assertNotNull(server);
		Assert.assertTrue(server instanceof Server);
	}

}
