package org.teagle.clients.api;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import teagle.vct.model.ModelManager;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.Vct;
import teagle.vct.util.OrchestrateReturn;

public class LegacyTeagleClientTest {

	private LegacyTeagleClient client;

	@Before
	public void setup() throws MalformedURLException {
		ModelManager repoClient = EasyMock.createMock(ModelManager.class);
		EasyMock.expect(repoClient.findResourceInstancesByUserName("root")).andAnswer(new MockResourceInstances());
		EasyMock.expect(repoClient.findVctsByUserName("root")).andAnswer(new MockListOfVcts());
		
		EasyMock.replay(repoClient);
		this.client = new LegacyTeagleClient("root", new URL("http://a"), repoClient);
	}

	@Test
	public void testGetResourceInstances() {
		final Collection<ResourceInstance> actual = this.client
				.getResourceInstances();
		System.out.println(actual);
	}

	@Test
	public void testGetVCTs() {
		final Collection<Vct> actual = this.client.getVCTs();
		Assert.assertNotNull(actual);
	}

	//@Test
	public void testFoo() throws IOException {
		this.client.bookVct(new File("src/test/resources/vcts/simplevct.xml"));
		OrchestrateReturn result = this.client.getResult();
		Assert.assertEquals(0, result.status);
	}
	
	private class MockResourceInstances implements IAnswer<List<ResourceInstance>> {
		 @Override
	        public List<ResourceInstance> answer() throws Throwable {
	            return new LinkedList<ResourceInstance>();
	        }
	};
	
	private class MockListOfVcts implements IAnswer<List<Vct>> {
		 @Override
	        public List<Vct> answer() throws Throwable {
				return new LinkedList<Vct>();
	        }
	};
	
}
