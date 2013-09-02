//package org.fiteagle.adapter.qoSControl.tmp;
//
//import java.net.Inet4Address;
//import java.net.UnknownHostException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//
//import org.csapi.schema.parlayx.adq.v4_0.QoSFeatureProperties;
//import org.csapi.schema.parlayx.common.v4_0.NameValuePair;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Path("/QoSManager")
//public class QoSManagerImpl implements QoSManager {
//
//	String PCRF_HOST = "";
//	String PCRF_REALM = "open-ims.test";
//
//	//AFSessionManagerInterface afSessionManager;
//
//	Logger log = LoggerFactory.getLogger(this.getClass());
//
//	public QoSManagerImpl() {
//
//		//afSessionManager = new AFSessionManager();
//
//		// TODO make port and address configurable
//	}
//
//	@Override
//	@GET
//	@Path("authorize")
//	@Produces("text/xml")
//	public String authorize(@QueryParam("user") String user,
//			@QueryParam("pw") String pw) {
//		String authkey = null;
//
//		return authkey;
//	}
//
//	@Override
//	@GET
//	@Path("applyQoSRule")
//	@Produces("text/xml")
//	public String applyQoSRule(@QueryParam("authkey") String authkey,
//			@QueryParam("userName") String username,
//			@QueryParam("rule") String rule) {
//
//		String qosSession = null;
//		// try {
//		// sessionInfo = sessionService.getSessionInfo(authkey);
//		// } catch (SessionException e) {
//		// log.error(e.getMessage(), e);
//		// }
//		// if(sessionInfo.isValid()){
//		//
//		AFSession session = afSessionManager.createSession("Broker");
//		if (username != null) {
//			if (username.contains("@")) {
//				session.setSipUri(username);
//			} else {
//				session.setImsi(username);
//			}
//		}
//		MediaComponentDescription description = session
//				.createMediaComponentDescription();
//		description.setAFApplicationIdentifier(rule.getBytes());
//		if (afSessionManager.initiateSession(session)) {
//			qosSession = session.getId();
//		} else {
//			log.error("rx session initiation failed");
//		}
//
//		// }
//		return qosSession;
//
//	}
//
//	@Override
//	@POST
//	@Path("startSession")
//	@Produces("text/xml")
//	public String startSession(@QueryParam("authkey") String authkey,
//			@QueryParam("userName") String userName, QoSFeatureProperties props) {
//		// SessionInfo sessionInfo= null;
//		String qosSession = null;
//		// try {
//		// sessionInfo = sessionService.getSessionInfo(authkey);
//		// } catch (SessionException e) {
//		// log.error(e.getMessage(), e);
//		// }
//		// if(sessionInfo.isValid()){
//		// TODO modify interface to provide applicationName
//		AFSession session = afSessionManager.createSession("Broker");
//		if (userName != null) {
//			if (userName.contains("@")) {
//				session.setSipUri(userName);
//			} else {
//				session.setImsi(userName);
//			}
//		}
//		session = applyProperties(session, props);
//		if (afSessionManager.initiateSession(session)) {
//			qosSession = session.getId();
//		} else {
//			log.error("rx session initiation failed");
//		}
//
//		// }
//		return qosSession;
//	}
//
//	private AFSession applyProperties(AFSession session,
//			QoSFeatureProperties props) {
//		if (session.getMedias().size() == 0) {
//			MediaComponentDescription description = session
//					.createMediaComponentDescription();
//
//			for (NameValuePair pair : props.getOtherProperties()) {
//				if (pair.getName().equals("MediaType")) {
//					description.setMediaType(AVP_Media_Type_Enum.valueOf(pair
//							.getValue()));
//				}
//
//				if (pair.getName().equals("FlowStatus")) {
//					description.setFlowStatus(AVP_Flow_Status_Enum.valueOf(pair
//							.getValue()));
//				}
//				if (pair.getName().equals("ServiceIdentifier")) {
//					description.setAFApplicationIdentifier(pair.getValue()
//							.getBytes());
//				}
//				if (pair.getName().equals("Source_ip")) {
//					description.setSource(pair.getValue());
//
//				}
//				if (pair.getName().equals("Destination_ip")) {
//					description.setDestination(pair.getValue());
//				}
//				if (pair.getName().equals("Source_port")) {
//					description.setSource_port(pair.getValue());
//				}
//				if (pair.getName().equals("Destination_port")) {
//					description.setDestination_port(pair.getValue());
//				}
//				if (pair.getName().equals("Protocol")) {
//					description.setProtocol(pair.getValue());
//				}
//				if (pair.getName().equals("Framed_IP")) {
//					try {
//						session.setIPv4((Inet4Address) Inet4Address
//								.getByName(pair.getValue()));
//					} catch (UnknownHostException e) {
//						log.error(e.getMessage(), e);
//					}
//				}
//				if (pair.getName().equals("Direction")) {
//					description.setDirection(pair.getValue());
//				}
//
//			}
//
//			if (props.getDownStreamSpeedRate() != null) {
//				description.setMaxDownloadBandwidth(props
//						.getDownStreamSpeedRate());
//			}
//
//			if (props.getUpStreamSpeedRate() != null) {
//				description.setMaxUploadBandwidth(props.getUpStreamSpeedRate());
//			}
//		} else {
//
//		}
//		return session;
//	}
//
//	@Override
//	@POST
//	@Path("modifySession")
//	@Produces("text/xml")
//	public String modifySession(@QueryParam("authkey") String authkey,
//			@QueryParam("sessionKey") String sessionKey,
//			@QueryParam("mediaIdentifier") String mediaIdentifier,
//			QoSFeatureProperties props) {
//		// SessionInfo sessionInfo= null;
//		String qosSession = null;
//		// try {
//		// sessionInfo = sessionService.getSessionInfo(authkey);
//		//
//		//
//		// } catch (SessionException e) {
//		// log.error(e.getMessage(), e);
//		// }
//		// if(sessionInfo.isValid()){
//		AFSession afSession = afSessionManager.getSession(sessionKey);
//		afSession = modifyProperties(afSession, props, mediaIdentifier);
//
//		qosSession = afSessionManager.modifySession(afSession).getId();
//		// }
//		return qosSession;
//	}
//
//	private AFSession modifyProperties(AFSession afSession,
//			QoSFeatureProperties props, String mediaIdentifier) {
//		List<MediaComponentDescription> medias = afSession.getMedias();
//		for (MediaComponentDescription media : medias) {
//			if (media.getMediaIdentifier().equals(mediaIdentifier)) {
//				for (NameValuePair pair : props.getOtherProperties()) {
//					if (pair.getName().equals("MediaType")) {
//						media.setMediaType(AVP_Media_Type_Enum.valueOf(pair
//								.getValue()));
//					}
//
//					if (pair.getName().equals("FlowStatus")) {
//						media.setFlowStatus(AVP_Flow_Status_Enum.valueOf(pair
//								.getValue()));
//					}
//					if (pair.getName().equals("ServiceIdentifier")) {
//						media.setAFApplicationIdentifier(pair.getValue()
//								.getBytes());
//					}
//					if (pair.getName().equals("Source_ip")) {
//						media.setSource(pair.getValue());
//
//					}
//					if (pair.getName().equals("Destination_ip")) {
//						media.setDestination(pair.getValue());
//					}
//					if (pair.getName().equals("Source_port")) {
//						media.setSource_port(pair.getValue());
//					}
//					if (pair.getName().equals("Destination_port")) {
//						media.setDestination_port(pair.getValue());
//					}
//					if (pair.getName().equals("Protocol")) {
//						media.setProtocol(pair.getValue());
//					}
//					if (pair.getName().equals("Framed_IP")) {
//						try {
//							afSession.setIPv4((Inet4Address) Inet4Address
//									.getByName(pair.getValue()));
//						} catch (UnknownHostException e) {
//							log.error(e.getMessage(), e);
//						}
//					}
//					if (pair.getName().equals("Direction")) {
//						media.setDirection(pair.getValue());
//					}
//
//				}
//
//				if (props.getDownStreamSpeedRate() != null) {
//					media.setMaxDownloadBandwidth(props
//							.getDownStreamSpeedRate());
//				}
//
//				if (props.getUpStreamSpeedRate() != null) {
//					media.setMaxUploadBandwidth(props.getUpStreamSpeedRate());
//				}
//			}
//		}
//		return afSession;
//	}
//
//	@Override
//	@GET
//	@Path("endSession")
//	@Produces("text/xml")
//	public String endSession(@QueryParam("authkey") String authkey,
//			@QueryParam("sessionKey") String sessionKey) {
//		// SessionInfo sessionInfo= null;
//		String qosSession = "success";
//		// try {
//		// sessionInfo = sessionService.getSessionInfo(authkey);
//		// } catch (SessionException e) {
//		// log.error(e.getMessage(), e);
//		// }
//		// if(sessionInfo.isValid()){
//		afSessionManager.removeSession(sessionKey);
//		// }
//		return qosSession;
//	}
//
//	@Override
//	@GET
//	@Path("getQoSStatus")
//	@Produces("text/xml")
//	public GetQoSStatusResponse getQoSStatus(
//			@QueryParam("authkey") String authkey,
//			@QueryParam("userName") String userName) {
//		// SessionInfo sessionInfo= null;
//		GetQoSStatusResponse qosStatusResponse = new GetQoSStatusResponse();
//		Set<QoSStatus> qosStatusSet = new HashSet<QoSStatus>();
//		// try {
//		// sessionInfo = sessionService.getSessionInfo(authkey);
//		// } catch (SessionException e) {
//		// log.error(e.getMessage(), e);
//		// }
//		// if(sessionInfo.isValid()){
//		List<AFSession> userSessions = afSessionManager.getSessions(userName);
//		if (userSessions != null && userSessions.size() > 0) {
//			for (AFSession session : userSessions) {
//				// qosSession += session.toString();
//				QoSStatus qosStatus = new QoSStatus();
//
//				qosStatus.setUserId(session.getUserId());
//				qosStatus.setSessionId(session.getId());
//				List<MediaComponentDescription> mediaList = session.getMedias();
//
//				Set<MediaStatus> medias = createMediaStatusSet(mediaList);
//				qosStatus.setMedias(medias);
//				qosStatusSet.add(qosStatus);
//			}
//		} else {
//			// qosSession = "No Information available";
//		}
//		// }
//		qosStatusResponse.setQosStatusSet(qosStatusSet);
//		return qosStatusResponse;
//	}
//
//	private Set<MediaStatus> createMediaStatusSet(
//			List<MediaComponentDescription> mediaList) {
//		Set<MediaStatus> returnValue = new HashSet<MediaStatus>();
//
//		for (MediaComponentDescription mediaDescription : mediaList) {
//			MediaStatus mediaStatus = new MediaStatus();
//			mediaStatus.setMaxDownload(mediaDescription
//					.getMaxDownloadBandwidth());
//			mediaStatus.setMaxUpload(mediaDescription.getMaxUploadBandwidth());
//			mediaStatus.setMediaIdentifier(mediaDescription
//					.getMediaIdentifier());
//			returnValue.add(mediaStatus);
//		}
//		return returnValue;
//	}
//
//	@Override
//	@GET
//	@Path("subscribe")
//	@Produces("text/xml")
//	public String subscribe(@QueryParam("authkey") String authkey,
//			@QueryParam("userName") String userName,
//			@QueryParam("event") String event) {
//		// SessionInfo sessionInfo= null;
//		String qosSession = null;
//		// try {
//		// sessionInfo = sessionService.getSessionInfo(authkey);
//		// } catch (SessionException e) {
//		// log.error(e.getMessage(), e);
//		// }
//		// if(sessionInfo.isValid()){
//		AFSession session = afSessionManager.createSession("subscription");
//
//		if (userName != null) {
//			if (userName.contains("@")) {
//				session.setSipUri(userName);
//			} else {
//				session.setImsi(userName);
//			}
//		}
//		if (afSessionManager.initiateSubscriptionSession(session, event)) {
//			qosSession = session.getId();
//		}
//		// }
//		return qosSession;
//	}
//
//	@Override
//	@POST
//	@Path("createServiceProfile")
//	@Produces("text/xml")
//	public String createServiceProfile(@QueryParam("authkey") String authkey,
//			@QueryParam("serviceName") String serviceName,
//			QoSFeatureProperties profile) {
//		// SessionInfo sessionInfo= null;
//		String qosSession = null;
//		// try {
//		// sessionInfo = sessionService.getSessionInfo(authkey);
//		// } catch (SessionException e) {
//		// log.error(e.getMessage(), e);
//		// }
//		// if(sessionInfo.isValid()){
//		// Currently not possible to create a new Service Profile
//		// }
//		return qosSession;
//	}
//
//	@Override
//	@GET
//	@Path("subscribeToService")
//	@Produces("text/xml")
//	public String subscribeToService(@QueryParam("authkey") String authkey,
//			@QueryParam("serviceName") String serviceName) {
//		// SessionInfo sessionInfo= null;
//		String qosSession = null;
//		// try {
//		// sessionInfo = sessionService.getSessionInfo(authkey);
//		// } catch (SessionException e) {
//		// log.error(e.getMessage(), e);
//		// }
//		// if(sessionInfo.isValid()){
//		// Subscription to service is not possible
//		// }
//		return qosSession;
//	}
//
//}
