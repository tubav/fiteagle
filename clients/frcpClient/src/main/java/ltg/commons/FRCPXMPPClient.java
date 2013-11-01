package ltg.commons;

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

	public static void main(final String[] args) throws XMPPException {
		final FRCPXMPPClient client = new FRCPXMPPClient("test",
				"fuseco.fokus.fraunhofer.de", "test");
		client.connect();
		while (true) {
			try {
				System.out.println(".");
				Thread.sleep(5000);
			} catch (final InterruptedException e) {
				client.disconnect();
			}
		}
	}

	public FRCPXMPPClient(final String username, final String hostname,
			final String password) {
		this.username = username;
		this.password = password;
		this.hostname = hostname;
		this.id = username + "@" + hostname;
	}

	private void connect() throws XMPPException {
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

	public String getMembershipFromConfigureMessage(final String xml) throws XmlException {
		String actual = "";
	
		final ConfigureDocument doc = ConfigureDocument.Factory.parse(xml);
		for (final net.mytestbed.schema.omf.x60.protocol.PropsDocument.Props prop : doc
				.getConfigure().getPropsArray()) {
			final NodeList childs = prop.getDomNode().getChildNodes();
			for (int i = 0; i < childs.getLength(); i++) {
				final org.w3c.dom.Node child = childs.item(i);
				final String nodeName = child.getNodeName();
				if ("membership".equals(nodeName) && child.hasChildNodes()) {
					actual = childs.item(i).getChildNodes().item(0)
							.getNodeValue();
				}
			}
		}
		return actual.replace(protocol, "").replace("@" + hostname, "");
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

	private void addMembershipToProperty(final Props prop,
			final String topic) {
		final QName membership = new QName(prop.getDomNode().getNamespaceURI(),
				"membership");
		final XmlCursor cursor = prop.newCursor();
		cursor.toFirstContentToken();
		cursor.beginElement(membership);
		cursor.insertAttributeWithValue("type", "string");
		cursor.insertChars(topic);
	}

	private void handleConfigureMessage(final String message) throws XmlException, XMPPException {
		final String membershipTopic = this.getMembershipFromConfigureMessage(message);
		System.out.println("Subscribing to: " + membershipTopic);
		this.pubsub.getNode(membershipTopic).subscribe(this.id);
		this.pubsub.getNode(membershipTopic).addItemEventListener(
				new ItemEventCoordinator());
	
		final InformDocument informMessage = this.generateInformMembershipMessage(membershipTopic);
		System.out.println("Sending INFORM message...");
	
		final LeafNode node = this.pubsub.getNode(this.username);
		final SimplePayload payload = new SimplePayload("", "",
				informMessage.toString());
		node.send(new PayloadItem<SimplePayload>("", payload));
	}

	private void handleCreateMessage(final String message) throws XmlException {
		final CreateDocument createDocument = CreateDocument.Factory
				.parse(message);
		final Create createMessage = createDocument.getCreate();
		final Props[] props = createMessage.getPropsArray();
		final NodeList children = props[0].getDomNode().getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			final String type = children.item(i).getNodeName();
			if (type.equals("binary_path")) {
				final String command = children.item(i).getChildNodes().item(0)
						.getNodeValue();
				System.out.println(command);
			}
		}
	}

	private class ItemEventCoordinator implements
			ItemEventListener<PayloadItem<SimplePayload>> {
		@Override
		public void handlePublishedItems(
				final ItemPublishEvent<PayloadItem<SimplePayload>> items) {
			final String message = items.getItems().get(0).getPayload().toXML();
			System.out.println("Incoming...");

			try {
				final XmlObject xmlObject = XmlObject.Factory.parse(message);
				System.out.println("============");
				System.out.println(xmlObject);
				System.out.println("============");
				final String type = xmlObject.getDomNode().getFirstChild()
						.getNodeName();

				if (type.equals("configure")) {
					FRCPXMPPClient.this.handleConfigureMessage(message);
				} else if (type.equals("create")) {
					FRCPXMPPClient.this.handleCreateMessage(message);
				} else {
					System.out.println("Unkown type.");
				}
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}
}
