package org.fiteagle.clients.frcp;

import java.util.LinkedList;
import java.util.List;

import org.apache.xmlbeans.XmlException;
import org.fiteagle.clients.frcp.FRCPXMPPClient.ItemEventCoordinator;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FRCPXMPPClientTest {

	private final String testHostname = "localhost";
	private final String testUser = "test";
	private final String testPassword = "test";
	private final String testTopic = "0fee9b27-4ff4-4acf-bb7a-850bf40a0da1";
	private final String testMessageConfigureMembership = "<configure xmlns='http://schema.mytestbed.net/omf/6.0/protocol' mid='a9cade02-8a57-4534-9fb2-df3aa871dbe4'><props><membership type='string'>xmpp://" + this.testTopic + "@" + this.testHostname + "</membership></props><ts>1383258297</ts><src>xmpp://alexandersmbp4-fritz-box-30936@fuseco.fokus.fraunhofer.de</src></configure>";
	private final String resteMessageCreateApp = "<create xmlns='http://schema.mytestbed.net/omf/6.0/protocol' mid='3c580c18-0302-4eb3-a743-60dbe0708eaa'><props xmlns:application='http://schema.mytestbed.net/omf/6.0/protocol/application'><application:binary_path type='string'>ip link list</application:binary_path><application:hrn type='string'>ip link list</application:hrn><application:membership type='string'>xmpp://4062196c-6327-4af7-a90d-906138ce07cc_application@fuseco.fokus.fraunhofer.de</application:membership><application:type type='string'>application</application:type></props><ts>1383344779</ts><src>xmpp://alexandersmbp4-fritz-box-41919@fuseco.fokus.fraunhofer.de</src><rtype>application</rtype></create>";
	private FRCPXMPPClient client;

	@Before
	public void setUp() {
		this.client = new FRCPXMPPClient(this.testUser, this.testHostname,
				this.testPassword);
	}

	@Test
	public void testParseConfigureMembership() throws XmlException {
		final String expected = this.testTopic;
		final String actual = this.client
				.getMembershipFromConfigureMessage(this.testMessageConfigureMembership);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGenerateInform() {
		final String input = this.client.generateInformMembershipMessage(
				this.testTopic).toString();
		Assert.assertTrue(input.contains(this.testTopic));
	}

	@Test
	public void testHandleConfigureMembershipViaPayload() throws XMPPException {
		final ItemEventCoordinator coordinator = this.client.new ItemEventCoordinator();

		final ItemPublishEvent<PayloadItem<SimplePayload>> items = this
				.createPayload(this.testMessageConfigureMembership);

		coordinator.handlePublishedItems(items);
	}

	@Test
	public void testHandleCreateAppViaPayload() throws XMPPException {
		final ItemEventCoordinator coordinator = this.client.new ItemEventCoordinator();

		final ItemPublishEvent<PayloadItem<SimplePayload>> items = this
				.createPayload(this.resteMessageCreateApp);

		coordinator.handlePublishedItems(items);
	}

	private ItemPublishEvent<PayloadItem<SimplePayload>> createPayload(
			final String payloadMessage) {
		final List<PayloadItem<SimplePayload>> eventItems = new LinkedList<PayloadItem<SimplePayload>>();

		final SimplePayload payload = new SimplePayload("", "", payloadMessage);
		eventItems.add(new PayloadItem<SimplePayload>("", payload));

		final ItemPublishEvent<PayloadItem<SimplePayload>> items = new ItemPublishEvent<PayloadItem<SimplePayload>>(
				"", eventItems);
		return items;
	}

}
