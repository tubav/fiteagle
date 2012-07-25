package org.teagle.clients.api;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;

import org.junit.Assert;

import teagle.vct.model.ResourceInstance;
import teagle.vct.model.Vct;
import teagle.vct.util.OrchestrateReturn;

public class LegacyTeagleClientTest {

	private LegacyTeagleClient client;

	//@Before
	public void setup() throws MalformedURLException {
		final String repoUrl = "http://localhost:9080/repository/rest";
		final String reqUrl = "http://localhost:9000/reqproc";
		final String user = "root";
		final String pwd = "root";

		// todo: inject a local mock - do not test using external servers
		this.client = new LegacyTeagleClient(user, pwd, reqUrl, repoUrl);
	}

	//@Test
	public void testGetResourceInstances() {
		final Collection<ResourceInstance> actual = this.client
				.getResourceInstances();
		System.out.println(actual);
	}

	//@Test
	public void testGetVCTs() {
		final Collection<Vct> actual = this.client.getVCTs();
		Assert.assertFalse(actual.isEmpty());
	}

	//@Test
	public void testFoo() throws IOException {
		this.client.bookVct(new File("src/test/resources/vcts/simplevct.xml"));
		OrchestrateReturn result = this.client.getResult();
		Assert.assertEquals(0, result.status);
	}
}
