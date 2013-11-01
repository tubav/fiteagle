package org.fiteagle.clients.frcp;

import org.apache.xmlbeans.XmlException;
import org.fiteagle.clients.frcp.FRCPXMPPClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FRCPXMPPClientTest {

	private String testHostname = "localhost";
	private String testUser = "test";
	private String testPassword = "test";
	private String testTopic = "0fee9b27-4ff4-4acf-bb7a-850bf40a0da1";
	private FRCPXMPPClient client;

	@Before
	public void setUp() {
		this.client = new FRCPXMPPClient(testUser, testHostname, testPassword);
	}

	@Test
	public void testParseConfigureMembership() throws XmlException {
		String input = "<configure xmlns='http://schema.mytestbed.net/omf/6.0/protocol' mid='a9cade02-8a57-4534-9fb2-df3aa871dbe4'><props><membership type='string'>xmpp://" + testTopic + "@" + testHostname + "</membership></props><ts>1383258297</ts><src>xmpp://alexandersmbp4-fritz-box-30936@fuseco.fokus.fraunhofer.de</src></configure>";
		String expected = this.testTopic;
		String actual = this.client.getMembershipFromConfigureMessage(input);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGenerateInform() {
		String input = this.client.generateInformMembershipMessage(testTopic).toString();
		Assert.assertTrue(input.contains(testTopic));
	}
}
