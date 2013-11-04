package org.fiteagle.clients.frcp;

import javax.xml.namespace.QName;

import net.mytestbed.schema.omf.x60.protocol.ConfigureDocument;
import net.mytestbed.schema.omf.x60.protocol.CreateDocument;
import net.mytestbed.schema.omf.x60.protocol.CreateDocument.Create;
import net.mytestbed.schema.omf.x60.protocol.InformDocument;
import net.mytestbed.schema.omf.x60.protocol.InformDocument.Inform;
import net.mytestbed.schema.omf.x60.protocol.ItypeDocument.Itype;
import net.mytestbed.schema.omf.x60.protocol.PropsDocument.Props;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.Node;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.w3c.dom.NodeList;

public class FRCPXMPPClient {

	private final String username;
	private final String id;
	private final String hostname;
	private final String password;
	private final String protocol = "xmpp://";
	private PubSubManager pubsub;
	private Node pubsubNode;
	private XMPPConnection connection;

	public FRCPXMPPClient(final String username, final String hostname,
			final String password) {
		this.username = username;
		this.password = password;
		this.hostname = hostname;
		this.id = username + "@" + hostname;
	}

	public void connect() throws XMPPException {
		this.connection = new XMPPConnection(this.hostname);
		this.connection.connect();
		this.connection.login(this.username, this.password);

		this.pubsub = new PubSubManager(this.connection);
		this.pubsubNode = this.pubsub.getNode(this.username);
		this.pubsubNode.addItemEventListener(new ItemEventCoordinator());
	}

	public void disconnect() {
		this.connection.disconnect();
	}

	public String getMembershipFromConfigureMessage(final String xml)
			throws XmlException {
		String actual = "";

		final ConfigureDocument doc = ConfigureDocument.Factory.parse(xml);
		for (final Props prop : doc.getConfigure().getPropsArray()) {
			final NodeList childs = prop.getDomNode().getChildNodes();
			for (int i = 0; i < childs.getLength(); i++) {
				final org.w3c.dom.Node child = childs.item(i);
				actual = getTextFromNode(child, "membership");
			}
		}
		return actual.replace(protocol, "").replace("@" + hostname, "");
	}

	private String getTextFromNode(final org.w3c.dom.Node child,
			final String expectedNodeName) {
		String text = "";
		final String nodeName = child.getLocalName();
		if (nodeName.equals(expectedNodeName) && child.hasChildNodes()) {
			text = child.getChildNodes().item(0).getNodeValue();
		}
		return text;
	}

	public InformDocument generateInformMembershipMessage(final String topic) {
		final InformDocument doc = InformDocument.Factory.newInstance();
		final Inform inform = Inform.Factory.newInstance();
		inform.addItype(Itype.STATUS);
		inform.addSrc(this.protocol + this.id);
		inform.addTs("123312");
		final Props prop = inform.addNewProps();
		this.addMembershipToProperty(prop, topic);
		doc.setInform(inform);
		return doc;
	}

	private void addMembershipToProperty(final Props prop, final String topic) {
		final QName membership = new QName(prop.getDomNode().getNamespaceURI(),
				"membership");
		final XmlCursor cursor = prop.newCursor();
		cursor.toFirstContentToken();
		cursor.beginElement(membership);
		cursor.insertAttributeWithValue("type", "string");
		cursor.insertChars(topic);
	}

	private void handleCreateApplicationMessage(final String message)
			throws XmlException {
		final CreateDocument createDocument = CreateDocument.Factory
				.parse(message);
		final Create createMessage = createDocument.getCreate();
		final Props prop = createMessage.getPropsArray(0);
		final NodeList children = prop.getDomNode().getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			String command = getTextFromNode(children.item(i), "binary_path");
			if (!command.isEmpty())
				System.out.println("Command: " + command);
		}
	}

	private void handleConfigureMembershipMessage(final String message)
			throws XmlException, XMPPException {
		final String topic = this.getMembershipFromConfigureMessage(message);
		subscribeToMembership(topic);
		informAboutMembership(topic);
	}

	private void informAboutMembership(final String topic) throws XMPPException {
		System.out.println("Sending INFORM message...");
		final InformDocument informMessage = this
				.generateInformMembershipMessage(topic);
		if (null != this.pubsub) {
			final LeafNode receiver = this.pubsub.getNode(this.username);
			final SimplePayload payload = new SimplePayload("", "",
					informMessage.toString());
			receiver.send(new PayloadItem<SimplePayload>("", payload));
		}
	}

	private void subscribeToMembership(final String topic) throws XMPPException {
		System.out.println("Subscribing to: " + topic);
		if (null != this.pubsub) {
			this.pubsub.getNode(topic).subscribe(this.id);
			this.pubsub.getNode(topic).addItemEventListener(
					new ItemEventCoordinator());
		}
	}

	public class ItemEventCoordinator implements
			ItemEventListener<PayloadItem<SimplePayload>> {
		@Override
		public void handlePublishedItems(
				final ItemPublishEvent<PayloadItem<SimplePayload>> items) {
			final String message = items.getItems().get(0).getPayload().toXML();

			try {
				final String type = getMessageType(message);
				if (type.equals("configure")) {
					FRCPXMPPClient.this
							.handleConfigureMembershipMessage(message);
				} else if (type.equals("create")) {
					FRCPXMPPClient.this.handleCreateApplicationMessage(message);
				} else {
					System.out.println("Unkown type.");
				}
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

		private String getMessageType(final String message) throws XmlException {
			final XmlObject xmlObject = XmlObject.Factory.parse(message);
			System.out.println("============");
			System.out.println("Incoming:");
			System.out.println(xmlObject);
			System.out.println("============");
			final String type = xmlObject.getDomNode().getFirstChild()
					.getNodeName();
			return type;
		}
	}
}
