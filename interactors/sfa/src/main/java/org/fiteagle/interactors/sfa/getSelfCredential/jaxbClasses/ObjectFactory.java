//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.03 at 03:55:56 PM CEST 
//


package org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PropertyName_QNAME = new QName("", "property_name");
    private final static QName _PropertyPenalty_QNAME = new QName("", "property_penalty");
    private final static QName _Link_QNAME = new QName("", "link");
    private final static QName _RedeemBefore_QNAME = new QName("", "redeem_before");
    private final static QName _PacketLoss_QNAME = new QName("", "packet_loss");
    private final static QName _Parent_QNAME = new QName("", "parent");
    private final static QName _InterfaceName_QNAME = new QName("", "interface_name");
    private final static QName _Type_QNAME = new QName("", "type");
    private final static QName _Bandwidth_QNAME = new QName("", "bandwidth");
    private final static QName _Node_QNAME = new QName("", "node");
    private final static QName _PropertyValue_QNAME = new QName("", "property_value");
    private final static QName _Expires_QNAME = new QName("", "expires");
    private final static QName _Latency_QNAME = new QName("", "latency");
    private final static QName _Rspec_QNAME = new QName("", "rspec");
    private final static QName _OwnerGid_QNAME = new QName("", "owner_gid");
    private final static QName _Name_QNAME = new QName("", "name");
    private final static QName _DestinationInterface_QNAME = new QName("", "destination_interface");
    private final static QName _Uuid_QNAME = new QName("", "uuid");
    private final static QName _NodeName_QNAME = new QName("", "node_name");
    private final static QName _Serial_QNAME = new QName("", "serial");
    private final static QName _TargetGid_QNAME = new QName("", "target_gid");
    private final static QName _SourceInterface_QNAME = new QName("", "source_interface");
    private final static QName _TicketCanDelegate_QNAME = new QName("", "can_delegate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LinkContents }
     * 
     */
    public LinkContents createLinkContents() {
        return new LinkContents();
    }

    /**
     * Create an instance of {@link Location }
     * 
     */
    public Location createLocation() {
        return new Location();
    }

    /**
     * Create an instance of {@link Privileges }
     * 
     */
    public Privileges createPrivileges() {
        return new Privileges();
    }

    /**
     * Create an instance of {@link Privilege }
     * 
     */
    public Privilege createPrivilege() {
        return new Privilege();
    }

    /**
     * Create an instance of {@link Extensions }
     * 
     */
    public Extensions createExtensions() {
        return new Extensions();
    }

    /**
     * Create an instance of {@link Ticket }
     * 
     */
    public Ticket createTicket() {
        return new Ticket();
    }

    /**
     * Create an instance of {@link RSpecContents }
     * 
     */
    public RSpecContents createRSpecContents() {
        return new RSpecContents();
    }

    /**
     * Create an instance of {@link Capability }
     * 
     */
    public Capability createCapability() {
        return new Capability();
    }

    /**
     * Create an instance of {@link Credentials }
     * 
     */
    public Credentials createCredentials() {
        return new Credentials();
    }

    /**
     * Create an instance of {@link Signatures }
     * 
     */
    public Signatures createSignatures() {
        return new Signatures();
    }

    /**
     * Create an instance of {@link Signature }
     * 
     */
    public Signature createSignature() {
        return new Signature();
    }

    /**
     * Create an instance of {@link Property }
     * 
     */
    public Property createProperty() {
        return new Property();
    }

    /**
     * Create an instance of {@link Violatable }
     * 
     */
    public Violatable createViolatable() {
        return new Violatable();
    }

    /**
     * Create an instance of {@link Fd }
     * 
     */
    public Fd createFd() {
        return new Fd();
    }

    /**
     * Create an instance of {@link NodeType }
     * 
     */
    public NodeType createNodeType() {
        return new NodeType();
    }

    /**
     * Create an instance of {@link NodeTypeContents }
     * 
     */
    public NodeTypeContents createNodeTypeContents() {
        return new NodeTypeContents();
    }

    /**
     * Create an instance of {@link Field }
     * 
     */
    public Field createField() {
        return new Field();
    }

    /**
     * Create an instance of {@link Interface }
     * 
     */
    public Interface createInterface() {
        return new Interface();
    }

    /**
     * Create an instance of {@link Credential }
     * 
     */
    public Credential createCredential() {
        return new Credential();
    }

    /**
     * Create an instance of {@link Capabilities }
     * 
     */
    public Capabilities createCapabilities() {
        return new Capabilities();
    }

    /**
     * Create an instance of {@link SignedCredential }
     * 
     */
    public SignedCredential createSignedCredential() {
        return new SignedCredential();
    }

    /**
     * Create an instance of {@link NodeContents }
     * 
     */
    public NodeContents createNodeContents() {
        return new NodeContents();
    }

    /**
     * Create an instance of {@link InterfaceSpec }
     * 
     */
    public InterfaceSpec createInterfaceSpec() {
        return new InterfaceSpec();
    }

    /**
     * Create an instance of {@link InterfaceRef }
     * 
     */
    public InterfaceRef createInterfaceRef() {
        return new InterfaceRef();
    }

    /**
     * Create an instance of {@link LinkContents.LinkType }
     * 
     */
    public LinkContents.LinkType createLinkContentsLinkType() {
        return new LinkContents.LinkType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "property_name")
    public JAXBElement<String> createPropertyName(String value) {
        return new JAXBElement<String>(_PropertyName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "property_penalty")
    public JAXBElement<Float> createPropertyPenalty(Float value) {
        return new JAXBElement<Float>(_PropertyPenalty_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkContents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "link")
    public JAXBElement<LinkContents> createLink(LinkContents value) {
        return new JAXBElement<LinkContents>(_Link_QNAME, LinkContents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "redeem_before")
    public JAXBElement<XMLGregorianCalendar> createRedeemBefore(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_RedeemBefore_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "packet_loss")
    public JAXBElement<Float> createPacketLoss(Float value) {
        return new JAXBElement<Float>(_PacketLoss_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Credentials }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "parent")
    public JAXBElement<Credentials> createParent(Credentials value) {
        return new JAXBElement<Credentials>(_Parent_QNAME, Credentials.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "interface_name")
    public JAXBElement<String> createInterfaceName(String value) {
        return new JAXBElement<String>(_InterfaceName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "type")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createType(String value) {
        return new JAXBElement<String>(_Type_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "bandwidth")
    public JAXBElement<Float> createBandwidth(Float value) {
        return new JAXBElement<Float>(_Bandwidth_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NodeContents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "node")
    public JAXBElement<NodeContents> createNode(NodeContents value) {
        return new JAXBElement<NodeContents>(_Node_QNAME, NodeContents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "property_value")
    public JAXBElement<String> createPropertyValue(String value) {
        return new JAXBElement<String>(_PropertyValue_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "expires")
    public JAXBElement<XMLGregorianCalendar> createExpires(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Expires_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "latency")
    public JAXBElement<Float> createLatency(Float value) {
        return new JAXBElement<Float>(_Latency_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RSpecContents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "rspec")
    public JAXBElement<RSpecContents> createRspec(RSpecContents value) {
        return new JAXBElement<RSpecContents>(_Rspec_QNAME, RSpecContents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "owner_gid")
    public JAXBElement<String> createOwnerGid(String value) {
        return new JAXBElement<String>(_OwnerGid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InterfaceSpec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "destination_interface")
    public JAXBElement<InterfaceSpec> createDestinationInterface(InterfaceSpec value) {
        return new JAXBElement<InterfaceSpec>(_DestinationInterface_QNAME, InterfaceSpec.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "uuid")
    public JAXBElement<String> createUuid(String value) {
        return new JAXBElement<String>(_Uuid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "node_name")
    public JAXBElement<String> createNodeName(String value) {
        return new JAXBElement<String>(_NodeName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "serial")
    public JAXBElement<String> createSerial(String value) {
        return new JAXBElement<String>(_Serial_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "target_gid")
    public JAXBElement<String> createTargetGid(String value) {
        return new JAXBElement<String>(_TargetGid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InterfaceSpec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "source_interface")
    public JAXBElement<InterfaceSpec> createSourceInterface(InterfaceSpec value) {
        return new JAXBElement<InterfaceSpec>(_SourceInterface_QNAME, InterfaceSpec.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "can_delegate", scope = Ticket.class)
    public JAXBElement<Boolean> createTicketCanDelegate(Boolean value) {
        return new JAXBElement<Boolean>(_TicketCanDelegate_QNAME, Boolean.class, Ticket.class, value);
    }

}
