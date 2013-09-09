package org.fiteagle.adapter.qoSControl;

import javax.ws.rs.core.MediaType;

import org.csapi.schema.parlayx.adq.v4_0.QoSFeatureProperties;
import org.fiteagle.adapter.qoSControl.tmp.GetQoSStatusResponse;
import org.fiteagle.adapter.qoSControl.tmp.QoSManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;



public class QoSClient implements QoSManager {

	private String url;
	private Client client;

	public QoSClient(String url){
		this.url = url;
		client = Client.create();
	}
	public String authorize(String user, String pw) {
		String path = "authorize";
		WebResource webResource = client.resource(url + "/" + path);
		
		webResource = webResource.queryParam("user", user).queryParam("pw", pw);
		String rsp = webResource.accept(MediaType.TEXT_XML).get(String.class);
		return rsp;
	}

	public String applyQoSRule(String authkey, String username, String rule) {
		String path = "applyQoSRule";
		WebResource webResource = client.resource(url + "/" + path);
		webResource = webResource.queryParam("authkey", authkey).queryParam("userName", username).queryParam("rule", rule);
		String rsp = webResource.accept(MediaType.TEXT_XML).get(String.class);
		return rsp;	
	}
	
	public String startSession(String authkey, String userName, QoSFeatureProperties props) {
		String path = "startSession";
		WebResource webResource = client.resource(url + "/" + path);
		webResource = webResource.queryParam("authkey", authkey).queryParam("userName", userName);
		String rsp = webResource.accept(MediaType.TEXT_XML).post(String.class,props);
		return rsp;
	}

	public String modifySession(String authkey, String sessionKey, String mediaIdentifier, QoSFeatureProperties props) {
		String path = "modifySession";
		WebResource webResource = client.resource(url + "/" + path);
		webResource = webResource.queryParam("authkey", authkey).queryParam("sessionKey", sessionKey).queryParam("mediaIdentifier", mediaIdentifier);
		String rsp = webResource.accept(MediaType.TEXT_XML).post(String.class,props);
		return rsp;
	}

	public String endSession(String authkey, String sessionKey) {
		String path = "endSession";
		WebResource webResource = client.resource(url + "/" + path);
		webResource = webResource.queryParam("authkey", authkey).queryParam("sessionKey", sessionKey);
		String rsp = webResource.accept(MediaType.TEXT_XML).get(String.class);
		return rsp;	
		}

	public String createServiceProfile(String authkey, String serviceName,
			QoSFeatureProperties profile) {
		// TODO Auto-generated method stub
		return null;
	}

	public String subscribeToService(String authkey, String serviceName) {
		// TODO Auto-generated method stub
		return null;
	}

	public GetQoSStatusResponse getQoSStatus(String authkey, String userName) {
		String path = "getQoSStatus";
		WebResource webResource = client.resource(url + "/" + path);
		webResource = webResource.queryParam("authkey", authkey).queryParam("userName", userName);
		GetQoSStatusResponse rsp = webResource.accept(MediaType.TEXT_XML).get(GetQoSStatusResponse.class);
		return rsp;
	}

	public String subscribe(String authkey, String userName, String event) {
		String path = "subscribe";
		WebResource webResource = client.resource(url + "/" + path);
		webResource = webResource.queryParam("authkey", authkey).queryParam("userName", userName).queryParam("event", event);
		String rsp = webResource.accept(MediaType.TEXT_XML).get(String.class);
		return rsp;	
	}

	
	
	public String listAvailableNetworks(String authkey, String userId) {
		String path = "listAvailableNetworks";
		WebResource webResource = client.resource(url+"/" + path);
		webResource = webResource.queryParam("authkey", authkey).queryParam("userId", userId);
		String rsp = webResource.accept(MediaType.TEXT_XML).get(String.class);
		return rsp;
	}

	
	public String selectAccessNetwork(String authkey, String userId,
			String networkIdentifier) {
		String path = "selectAccessNetwork";
		WebResource webResource = client.resource(url+"/" + path);
		webResource = webResource.queryParam("authkey", authkey).queryParam("userId", userId).queryParam("networkIdentifier", networkIdentifier);
		String rsp = webResource.accept(MediaType.TEXT_XML).get(String.class);
		return rsp;
	}

	public String getClientNetworkInformation(String userId){
		String path = "getClientNetworkInformation";
		WebResource webResource = client.resource(url+"/" + path);
		webResource = webResource.queryParam("userId", userId);
		String rsp = webResource.accept(MediaType.TEXT_XML).get(String.class);
		return rsp;
	}
//	public static void main(String[] args){
//		
//		Util util = new Util();
//		QoSRXTest rxTest = new QoSRXTest(util.createUrlFor("QoSManager"));
//		QoSRXTest connectivityTest = new QoSRXTest(util.createUrlFor("ConnectivityManagement"));
//		//String auth = rxTest.authorize("admin", "admin");
//		String auth = "";
//		String sessionId ="";
//		
//		String userId = "alice@openepc.test";
//		
//	
//		System.out.println("Type start:");
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//		String input = "";
//		while(true){
//		try {
//			input = bf.readLine();
//			
//			int in = Integer.parseInt(input);
//		switch(in){
//		case 0:
//			
//			/**
//			 * SUBSCRIBE TO Alice's EVENTS
//			 */
//			System.out.println("Subscribe");
//			sessionId = rxTest.subscribe(auth, "001011234567890", AVP_Specific_Action_Enum.INDICATION_OF_RELEASE_OF_BEARER.name());
//			break;
//		case 1:
////			/**
////			 * APPLY SIMPLE RULE FOR Alice
////			 */
//			System.out.println("Apply");
//		 sessionId = rxTest.applyQoSRule(auth, userId, "www.youtube.com");
//		break;
//		
//		case 2:
//			System.out.println("initate Session");
////			/**
////			 * SESSION INITIATION
////			 */
//			QoSFeatureProperties props = new QoSFeatureProperties();
//			List<NameValuePair> otherProps = props.getOtherProperties();
////			
////			NameValuePair nvp = new NameValuePair();
////			nvp.setName("ServiceIdentifier");
////			nvp.setValue("www.youtube.com");
////			otherProps.add(nvp);
//			props.setDownStreamSpeedRate("300000");
//			props.setUpStreamSpeedRate("300000");
//			TimeMetric duration = new TimeMetric();
//			duration.setUnits(100000);
//			props.setDuration(duration);
//			NameValuePair source_ip = new NameValuePair();
//			NameValuePair source_port = new NameValuePair();
//			NameValuePair destination_ip = new NameValuePair();
//			NameValuePair destination_port = new NameValuePair();
//			NameValuePair mediatype = new NameValuePair();
//			NameValuePair protocol = new NameValuePair();
//			NameValuePair framedIp = new NameValuePair();
//			NameValuePair direction = new NameValuePair();
//			
//			direction.setName("Direction");
//			direction.setValue("out");
//			framedIp.setName("Framed_IP");
//			framedIp.setValue("192.168.3.33");
//			mediatype.setName("MediaType");
//			mediatype.setValue(AVP_Media_Type_Enum.DATA.toString());
//			source_ip.setName("Source_ip");
//			source_ip.setValue("192.168.1.30"); //ip mdf
////			source_port.setName("Source_port");
////			source_port.setValue("22");
//			destination_ip.setName("Destination_ip");
//			destination_ip.setValue("192.168.3.33"); //ip alice
//			destination_port.setName("Destination_port");
//			destination_port.setValue("1234");
//			protocol.setName("Protocol");
//			protocol.setValue("udp");
//			otherProps.add(source_ip);
//	//		otherProps.add(source_port);
//			otherProps.add(destination_ip);
//			otherProps.add(destination_port);
//			otherProps.add(protocol);
//			otherProps.add(framedIp);
//			otherProps.add(direction);
//			sessionId = rxTest.startSession(auth, "001011234567890", props );
//			break;
//			
//		case 3:
////			/**
////			 * DELETE SESSIONS
////			 */
//			System.out.println("Delete");
//			String result2 = rxTest.endSession(auth, sessionId);
//			break;
////			
//		case 4:
//			//Modify session
//			System.out.println("Modify Existing Session");
//			QoSFeatureProperties newProps = new QoSFeatureProperties();
//			newProps.setDownStreamSpeedRate("6000");
//			newProps.setUpStreamSpeedRate("6000");
//			TimeMetric newDuration = new TimeMetric();
//			newDuration.setUnits(10000);
//			newProps.setDuration(newDuration);
//			String mediaIdentifier = "1";
//			String availableNetworks =  rxTest.modifySession(auth, sessionId,mediaIdentifier ,newProps);
//			System.out.println(availableNetworks);
//			break;
//		case 5:
//			//Get QoS Status
//			System.out.println("QoS Status");
//			GetQoSStatusResponse sessionDescription = rxTest.getQoSStatus(auth,userId);
//			System.out.println(sessionDescription);	
//			break;
//		case 6:
//				//Switch to LTE
//				System.out.println("LTE");
//				connectivityTest.selectAccessNetwork(auth,"001011234567890" , "LTE");
//				
//				break;
//				
//		case 7: 
//				//Switch to WIFI
//				System.out.println("WiFi");
//				connectivityTest.selectAccessNetwork(auth, "001011234567890", "WIFI");
//				break;
//		case 8:
//				//getClientNetworkInformation
//				System.out.println("Get Client Network Information");
//				System.out.println(connectivityTest.getClientNetworkInformation("001011234567890"));
//				break;
//		case 9:
//				System.out.println("List Available Networks");
//				System.out.println(connectivityTest.listAvailableNetworks(auth, "001011234567890"));
//		}
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		}
//	}
//
//	
	
}
