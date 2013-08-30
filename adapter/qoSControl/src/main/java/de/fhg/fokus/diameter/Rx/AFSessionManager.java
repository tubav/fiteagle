package de.fhg.fokus.diameter.Rx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.fhg.fokus.diameter.DiameterPeer.DiameterPeer;
import de.fhg.fokus.diameter.DiameterPeer.data.Application_Id_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.Vendor_Id_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Flow_Usage;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Flow_Usage_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Media_Component_Description;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Media_Component_Number;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Media_Sub_Component;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Service_Info_Status;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Service_Info_Status_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Specific_Action;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Specific_Action_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Authorization_Lifetime;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Experimental_Result;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Experimental_Result_Code;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Specific_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Request;
import de.fhg.fokus.diameter.DiameterPeer.session.Session;
import de.fhg.fokus.diameter.DiameterPeer.session.SessionEvent;
import de.fhg.fokus.diameter.DiameterPeer.session.auth.AuthSessionState;

public class AFSessionManager implements AFSessionManagerInterface {
	private DiameterPeer diameterPeer;
	private String destinationFQDN;
	private String destinationRealm;

	/** Configuration DOM */
	Document config;
	private HashMap<String, AFSession> sessionContainer;
	private static final Logger log = LoggerFactory
			.getLogger(AFSessionManager.class.getName());

	public AFSessionManager() {
		this("config/rxconfig.xml");

	}

	private AFSessionManager(String configFile) {
		sessionContainer = new HashMap<String, AFSession>();

		if (!readConfigFile(configFile)) {
			log.error("Error reading config file");
			return;
		}
		NodeList nl = config.getElementsByTagName("Peer");
		Node pcrf = nl.item(nl.getLength() - 1);

		this.destinationFQDN = pcrf.getAttributes().getNamedItem("FQDN")
				.getNodeValue();
		this.destinationRealm = pcrf.getAttributes().getNamedItem("Realm")
				.getNodeValue();
		this.diameterPeer = new DiameterPeer(configFile);
		this.diameterPeer.enableTransactions(10L, 1L);
	}

	private boolean readConfigFile(String cfgFile) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// factory.setValidating(true);
		// factory.setNamespaceAware(true);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			config = builder.parse(cfgFile);
		} catch (SAXException sxe) {
			// Error generated during parsing)
			Exception x = sxe;
			if (sxe.getException() != null)
				x = sxe.getException();
			x.printStackTrace();
			return false;
		} catch (ParserConfigurationException pce) {
			// Parser with specified options can't be built
			pce.printStackTrace();
			return false;
		} catch (IOException ioe) {
			// I/O error
			ioe.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fhg.fokus.diameter.Rx.AFSessionManagerInterface#getSession(java.lang
	 * .String)
	 */

	public AFSession getSession(String id) {
		return sessionContainer.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fhg.fokus.diameter.Rx.AFSessionManagerInterface#removeSession(java
	 * .lang.String)
	 */

	public void removeSession(String id) {
		AFSession session = sessionContainer.get(id);
		if (session != null
				&& session.getAuthSession().state != AuthSessionState.Open) {
			log.warn("terminate not possible. session either not initiated, disconnecting or already disconnected");
			return;
		}
		try {
			Message str = buildSTR(session);
			sendRequest(str);

		} catch (UnsupportedEncodingException e) {
			log.error("sending STR", e);
		}

	}

	public AFSession modifySession(AFSession session) {
		if (session != null
				&& session.getAuthSession().state != AuthSessionState.Open) {
			log.warn("terminate not possible. session either not initiated, disconnecting or already disconnected");
			return session;
		}
		try {
			Message aar = buildAAR(session);
			sendRequest(aar);

		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		return session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fhg.fokus.diameter.Rx.AFSessionManagerInterface#createSession()
	 */

	public AFSession createSession() {
		return createSession("defaf");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fhg.fokus.diameter.Rx.AFSessionManagerInterface#createSession(java
	 * .lang.String)
	 */

	public AFSession createSession(String applicationIdentifier) {
		AFSession session = new AFSession(this.diameterPeer,
				this.destinationFQDN, this.destinationRealm);
		session.setAFApplicationIdentifier(applicationIdentifier);
		sessionContainer.put(session.getId(), session);
		session.getAuthSession().addEventListener(this);
		return session;
	}

	private static String readConfigFile(InputStream is) {
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String str;
			while ((str = in.readLine()) != null) {
				sb.append(str);
			}
			in.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
		return sb.toString();
	}

	public void receiveAnswer(String FQDN, Message request, Message answer) {
		log.info("received answer: " + answer.toString());
	}

	public void timeout(Message request) {

		log.error(request.getSessionId() + " timed out!");

	}

	public void handleEvent(SessionEvent event, Session session) {
		try {
			switch (((de.fhg.fokus.diameter.DiameterPeer.session.auth.AuthSessionEventType) event.type)
					.ordinal()) {
			case 7:
				break;
			case 17:
				receivedRAR(event.msg, session);
				break;
			case 5:
				receivedASR(event.msg, session);
				break;
			case 18:
			case 19:
			case 20:
			default:
				log.info("session event: {" + event.type + "}");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("handle session event", e);
		}
	}

	private void receivedRAR(Message rar, Session session)
			throws UnsupportedEncodingException {
		Message raa = buildRAA(rar, session);
		log.info("Received RAR: " + rar.toString());
		sendAnswer(raa);
	}

	private Message buildRAA(Message rar, Session session)
			throws UnsupportedEncodingException {
		Message raa = this.diameterPeer.newAnswer((Request) rar);
		raa.setSession(session);
		return raa;

	}

	private void receivedASR(Message asr, Session session)
			throws UnsupportedEncodingException {
		Message asa = buildASA(asr, session);
		sendAnswer(asa);
		sessionContainer.remove(asa.getSessionId().toString());
	}

	private Message buildASA(Message asr, Session session)
			throws UnsupportedEncodingException {
		Message asa = this.diameterPeer.newAnswer((Request) asr);
		asa.setSession(session);

		return asa;
	}

	private Message buildSTR(AFSession session)
			throws UnsupportedEncodingException {
		Message str = this.diameterPeer.newRequest(
				Message_Type.Rx_Session_Termination_Request.commandCode,
				(int) Application_Id_Enum.Rx.value);
		str.setSession(session.getAuthSession());
		AVP_Destination_Host avp_destinationHost = new AVP_Destination_Host(
				session.getAuthSession().destinationHost);
		AVP_Destination_Realm avp_destionationRealm = new AVP_Destination_Realm(
				session.getAuthSession().destinationRealm);

		str.addAVP(avp_destionationRealm);
		str.addAVP(avp_destinationHost);
		str.networkTime = 2000L;
		return str;

	}

	private void receivedSTA(Message sta) {
		sessionContainer.remove(sta.getSessionId().toString());
	}

	private Message buildAAR(AFSession session)
			throws UnsupportedEncodingException {
		Message aar = this.diameterPeer.newRequest(
				Message_Type.Rx_Authorize_Authenticate_Request.commandCode,
				(int) Application_Id_Enum.Rx.value);

		aar.setSession(session.getAuthSession());
		try {
			AVP[] vsaiAvps = new AVP[2];
			vsaiAvps[0] = new AVP_Auth_Application_Id(
					Application_Id_Enum.Rx.value);
			vsaiAvps[1] = new AVP_Vendor_Id(Vendor_Id_Enum.TGPP.value);
			AVP_Vendor_Specific_Application_Id vsaiAvp = new AVP_Vendor_Specific_Application_Id(
					vsaiAvps);
			aar.addAVP(vsaiAvp);

			AVP_Destination_Host avp_destinationHost = new AVP_Destination_Host(
					session.getAuthSession().destinationHost);
			AVP_Destination_Realm avp_destionationRealm = new AVP_Destination_Realm(
					session.getAuthSession().destinationRealm);

			aar.addAVP(avp_destionationRealm);
			aar.addAVP(avp_destinationHost);

			if (session.getSubscriptionIdAvp() != null) {
				aar.addAVP(session.getSubscriptionIdAvp());
			}

			if (session.getIpv4AddressAvp() != null)
				aar.addAVP(session.getIpv4AddressAvp());
			else if (session.getIpv6PrefixAvp() != null) {
				aar.addAVP(session.getIpv6PrefixAvp());
			}

			if (session.getMedias() != null && session.getMedias().size() > 0) {
				for (MediaComponentDescription media : session.getMedias()) {
					AVP_Media_Component_Description media_AVP = media.toAvp();
					aar.addAVP(media_AVP);
				}

			}
			AVP_Authorization_Lifetime lifetimeAvp = new AVP_Authorization_Lifetime(
					3600L);
			lifetimeAvp.flagMandatory = true;
			aar.addAVP(lifetimeAvp);
			AVP_Service_Info_Status avp_final = new AVP_Service_Info_Status(
					AVP_Service_Info_Status_Enum.FINAL_SERVICE_INFORMATION.value);
			aar.addAVP(avp_final);
		} catch (AVPDataTypeException e) {
			log.error("preparing AAR", e);
		}
		aar.networkTime = 2000L;
		return aar;

	}

	private void receivedAAA(Message aaa) {
		if (aaa != null) {
			log.info("answer received: {" + aaa.toString() + "}");
			AVP_Experimental_Result resultAvp = (AVP_Experimental_Result) aaa
					.findAVP(AVP_Type.Experimental_Result);
			if (resultAvp != null) {
				AVP_Experimental_Result_Code code = (AVP_Experimental_Result_Code) resultAvp
						.findChildAVP(AVP_Type.Experimental_Result_Code);
				if (code != null) {
					// AVP_Rx_Experimental_Result_Enum en =
					// AVP_Rx_Experimental_Result_Enum.;
					// if (en != null) {
					// switch (AVP_Rx_Experimental_Result_Enum.valueOf((code) {
					// case 1:
					// break;
					// case 2:
					// break;
					// case 3:
					// break;
					// case 4:
					// break;
					// case 5:
					// break;
					// case 6:
					// default:
					// break;
					// }
					// }
				}
			}
		} else {
			this.log.warn("no answer, timeout");
		}
	}

	public boolean initiateSession(AFSession session) {
		if (session.getAuthSession().state != AuthSessionState.Idle) {
			this.log.warn("initiate not possible. session either already open, disconnecting or already disconnected");
			return false;
		}
		try {
			Message aar = buildAAR(session);
			sendRequest(aar);

		} catch (UnsupportedEncodingException e) {
			this.log.error("sending AAR", e);
			return false;
		}
		return true;
	}

	public boolean initiateSubscriptionSession(AFSession session, String event) {
		Message aar = null;
		try {
			aar = buildAAR(session);
		} catch (UnsupportedEncodingException e) {
			log.error("unsupported Encoding while building AAR");
		}
		if (aar != null) {
			AVP_Specific_Action avp_spec = new AVP_Specific_Action(
					AVP_Specific_Action_Enum.valueOf(event).value);
			aar.addAVP(avp_spec);
			AVP_Flow_Usage avp_flowUsage = new AVP_Flow_Usage(
					AVP_Flow_Usage_Enum.AF_SIGNALLING.value);
			Vector<AVP> mediaSubVector = new Vector<AVP>();
			mediaSubVector.add(avp_flowUsage);

			AVP_Media_Sub_Component avp_mediaSub = new AVP_Media_Sub_Component(
					mediaSubVector);
			Vector<AVP> mediaVector = new Vector<AVP>();
			AVP_Media_Component_Number avp_mediaComponentNumber = null;
			try {
				avp_mediaComponentNumber = new AVP_Media_Component_Number(0);
			} catch (AVPDataTypeException e) {
				log.error("AVPDataTypeException", e);
			}
			mediaVector.add(avp_mediaSub);
			mediaVector.add(avp_mediaComponentNumber);
			AVP_Media_Component_Description avp_media = new AVP_Media_Component_Description(
					mediaVector);
			aar.addAVP(avp_media);
			sendRequest(aar);
			return true;
		} else
			return false;
	}

	private void sendRequest(Message request) {
		this.diameterPeer.sendRequestTransactional(request, this);
	}

	private void sendAnswer(Message answer) {
		this.diameterPeer.sendMessage(answer);
	}

	public void stop() {
		this.diameterPeer.shutdown();

	}

	public List<AFSession> getSessions(String userName) {
		List<AFSession> userSessions = new LinkedList<AFSession>();
		for (AFSession session : sessionContainer.values()) {
			if (session.getUserId().equals(userName)) {
				userSessions.add(session);
			}
		}
		return userSessions;
	}
}