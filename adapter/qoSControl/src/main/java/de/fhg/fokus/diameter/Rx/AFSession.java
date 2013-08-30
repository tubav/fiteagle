package de.fhg.fokus.diameter.Rx;

import de.fhg.fokus.diameter.DiameterPeer.DiameterPeer;
import de.fhg.fokus.diameter.DiameterPeer.data.Application_Id_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.Vendor_Id_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.CC.AVP_Subscription_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.CC.AVP_Subscription_Id_Data;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.CC.AVP_Subscription_Id_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.CC.AVP_Subscription_Id_Type_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Gq.AVP_Reservation_Priority;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.NAS.AVP_Framed_IP_Address;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.NAS.AVP_Framed_IPv6_Prefix;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.NAS.Inet6Prefix;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_AF_Application_Identifier;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Flows;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Rx_Experimental_Result_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Authorization_Lifetime;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Experimental_Result;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Experimental_Result_Code;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Specific_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.derived.filterrule.IPFilterRule;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Request;
import de.fhg.fokus.diameter.DiameterPeer.session.Session;
import de.fhg.fokus.diameter.DiameterPeer.session.SessionEvent;
import de.fhg.fokus.diameter.DiameterPeer.session.SessionListener;
import de.fhg.fokus.diameter.DiameterPeer.session.SessionManager;
import de.fhg.fokus.diameter.DiameterPeer.session.auth.AuthSession;
import de.fhg.fokus.diameter.DiameterPeer.session.auth.AuthSessionState;
import de.fhg.fokus.diameter.DiameterPeer.transaction.TransactionListener;


import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AFSession

{
  private final Logger log = LoggerFactory.getLogger(getClass());
  private DiameterPeer diameterPeer;
  private AuthSession authSession;
  private String userId;
  private AVP_Subscription_Id subscriptionIdAvp;
private AVP_Framed_IP_Address ipv4AddressAvp;
  private AVP_Framed_IPv6_Prefix ipv6PrefixAvp;
  private AVP_AF_Application_Identifier afApplicationIdentifier;
  public AVP_AF_Application_Identifier getAfApplicationIdentifier() {
	return afApplicationIdentifier;
}
  public AVP_Subscription_Id getSubscriptionIdAvp() {
	return subscriptionIdAvp;
}

public void setSubscriptionIdAvp(AVP_Subscription_Id subscriptionIdAvp) {
	this.subscriptionIdAvp = subscriptionIdAvp;
}

public AVP_Framed_IP_Address getIpv4AddressAvp() {
	return ipv4AddressAvp;
}
public void setIpv4AddressAvp(AVP_Framed_IP_Address ipv4AddressAvp) {
	this.ipv4AddressAvp = ipv4AddressAvp;
}
public AVP_Framed_IPv6_Prefix getIpv6PrefixAvp() {
	return ipv6PrefixAvp;
}
public void setIpv6PrefixAvp(AVP_Framed_IPv6_Prefix ipv6PrefixAvp) {
	this.ipv6PrefixAvp = ipv6PrefixAvp;
}
public void setAfApplicationIdentifier(
		AVP_AF_Application_Identifier afApplicationIdentifier) {
	this.afApplicationIdentifier = afApplicationIdentifier;
}

private List<MediaComponentDescription> medias = new ArrayList();

  protected AFSession(DiameterPeer peer, String FQDN, String realm) {
    this.diameterPeer = peer;
    this.authSession = this.diameterPeer.sessionManager.createAuthClientSession(true);
 

    this.authSession.destinationHost = FQDN;
    this.authSession.destinationRealm = realm;

    this.afApplicationIdentifier = new AVP_AF_Application_Identifier("defaf".getBytes());
    this.afApplicationIdentifier.flagMandatory = true;
    this.afApplicationIdentifier.flagVendorSpecific = true;
    this.afApplicationIdentifier.vendorId = Vendor_Id_Enum.TGPP.value;
  }

  public AuthSession getAuthSession() {
	return authSession;
}

  public String getId() {
    return this.authSession.sessionId;
  }

  public void setImsi(String imsi) {
   if (imsi == null)
      this.subscriptionIdAvp = null;
    else
      setSubscriptionId(AVP_Subscription_Id_Type_Enum.END_USER_IMSI, imsi);
   
   	this.userId = imsi;
  }

  public void setSipUri(String uri)
  {
    if (uri == null)
      this.subscriptionIdAvp = null;
    else
      setSubscriptionId(AVP_Subscription_Id_Type_Enum.END_USER_SIP_URI, uri);
    
    this.userId = uri;
  }

  private void setSubscriptionId(AVP_Subscription_Id_Type_Enum type, String data)
  {
    Vector avps = new Vector();
    avps.add(new AVP_Subscription_Id_Type(type.value));
    try {
      avps.add(new AVP_Subscription_Id_Data(data));
      this.subscriptionIdAvp = new AVP_Subscription_Id(avps);
    } catch (UnsupportedEncodingException e) {
      this.log.error("setting subscription Id", e);
    }
  }

  public void setIPv4(Inet4Address address) {
    try {
      this.ipv4AddressAvp = new AVP_Framed_IP_Address(AVP_Type.Framed_IP_Address, address);
    } catch (AVPDecodeException e) {
      this.log.error("setting ipv4 address", e);
    }
  }

  public void setIPv6(Inet6Prefix address) {
    try {
      this.ipv6PrefixAvp = new AVP_Framed_IPv6_Prefix(address);
    } catch (AVPDecodeException e) {
      this.log.error("setting ipv6 prefix", e);
    }
  }
  
  

  

  public MediaComponentDescription createMediaComponentDescription() {
    MediaComponentDescription media = new MediaComponentDescription(this.medias.size() + 1);
   // media.setAFApplicationIdentifier(this.afApplicationIdentifier.get());

    this.medias.add(media);
    return media;
  }

  public void setAFApplicationIdentifier(String applicationIdentifier) {
    this.afApplicationIdentifier.set(applicationIdentifier.getBytes());
  }
public List<MediaComponentDescription> getMedias() {
	return medias;
}

public String getUserId(){

	return this.userId;
}




	
}