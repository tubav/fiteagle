package org.fiteagle.clients.frcp;

import java.util.LinkedList;
import java.util.List;

import org.apache.xmlbeans.XmlException;
import org.fiteagle.clients.frcp.FRCPXMPPClient;
import org.fiteagle.clients.frcp.FRCPXMPPClient.ItemEventCoordinator;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FRCPXMPPClientTest {

	private String testHostname = "localhost";
	private String testUser = "test";
	private String testPassword = "test";
	private String testTopic = "0fee9b27-4ff4-4acf-bb7a-850bf40a0da1";
	String configureMembership = "<configure xmlns='http://schema.mytestbed.net/omf/6.0/protocol' mid='a9cade02-8a57-4534-9fb2-df3aa871dbe4'><props><membership type='string'>xmpp://" + testTopic + "@" + testHostname + "</membership></props><ts>1383258297</ts><src>xmpp://alexandersmbp4-fritz-box-30936@fuseco.fokus.fraunhofer.de</src></configure>";
	String createApp = "<create xmlns='http://schema.mytestbed.net/omf/6.0/protocol' mid='3c580c18-0302-4eb3-a743-60dbe0708eaa'><props xmlns:application='http://schema.mytestbed.net/omf/6.0/protocol/application'><application:binary_path type='string'>ip link list</application:binary_path><application:hrn type='string'>ip link list</application:hrn><application:membership type='string'>xmpp://4062196c-6327-4af7-a90d-906138ce07cc_application@fuseco.fokus.fraunhofer.de</application:membership><application:type type='string'>application</application:type></props><ts>1383344779</ts><src>xmpp://alexandersmbp4-fritz-box-41919@fuseco.fokus.fraunhofer.de</src><rtype>application</rtype></create>";
	private FRCPXMPPClient client;

	@Before
	public void setUp() {
		this.client = new FRCPXMPPClient(testUser, testHostname, testPassword);
	}

	@Test
	public void testParseConfigureMembership() throws XmlException {
		String expected = this.testTopic;
		String actual = this.client.getMembershipFromConfigureMessage(configureMembership);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGenerateInform() {
		String input = this.client.generateInformMembershipMessage(testTopic).toString();
		Assert.assertTrue(input.contains(testTopic));
	}

	@Test
	public void testHandleConfigureMembershipViaPayload() throws XMPPException {
		ItemEventCoordinator coordinator = this.client.new ItemEventCoordinator();

		ItemPublishEvent<PayloadItem<SimplePayload>> items = createPayload(this.configureMembership);
		
		coordinator.handlePublishedItems(items);
	}

	@Test
	public void testHandleCreateAppViaPayload() throws XMPPException {
		ItemEventCoordinator coordinator = this.client.new ItemEventCoordinator();

		ItemPublishEvent<PayloadItem<SimplePayload>> items = createPayload(this.createApp);
		
		coordinator.handlePublishedItems(items);
	}

	private ItemPublishEvent<PayloadItem<SimplePayload>> createPayload(
			String payloadMessage) {
		List<PayloadItem<SimplePayload>> eventItems = new LinkedList<PayloadItem<SimplePayload>>();

		SimplePayload payload = new SimplePayload("", "", payloadMessage);
		eventItems.add(new PayloadItem<SimplePayload>("", payload));
		
		ItemPublishEvent<PayloadItem<SimplePayload>> items = new ItemPublishEvent<PayloadItem<SimplePayload>>("", eventItems);
		return items;
	}

}
