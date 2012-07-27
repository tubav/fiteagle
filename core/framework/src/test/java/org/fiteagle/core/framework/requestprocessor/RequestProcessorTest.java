package org.fiteagle.core.framework.requestprocessor;

import javax.xml.bind.JAXBException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.teagle.clients.api.LegacyTeagleClient;

import teagle.vct.util.OrchestrateReturn;

public class RequestProcessorTest {
	private RequestProcessor requestProcessor;
	private String startRequest;

	@Before
	public void setup() {
		final String reqCommand = "startVct";
		final String vctName = "testvct";
		final String username = "testuser";
		this.requestProcessor = new RequestProcessor();
		this.startRequest = LegacyTeagleClient.genRequest(username,
				vctName, reqCommand);
	}
	
	@Test
	public void testSuccessfulStartVct() throws JAXBException {
		this.requestProcessor.handle(startRequest);
		final OrchestrateReturn result = this.requestProcessor.getResult();

		Assert.assertEquals(RequestProcessor.STATUS_SUCCESS, result.status);
		Assert.assertFalse(result.log.isEmpty());
		Assert.assertFalse(result.message.isEmpty());
		Assert.assertFalse(null == result.logbook);
		Assert.assertFalse(result.logbook.component.isEmpty());
		Assert.assertFalse(result.logbook.name.isEmpty());
		Assert.assertFalse(null == result.logbook.entries);
		Assert.assertFalse(0 == result.logbook.entries.length);
	}
}
