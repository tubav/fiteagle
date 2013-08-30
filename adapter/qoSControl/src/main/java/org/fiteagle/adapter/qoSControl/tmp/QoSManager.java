package org.fiteagle.adapter.qoSControl.tmp;

import org.csapi.schema.parlayx.adq.v4_0.QoSFeatureProperties;



/**
 * @author dne
 *
 */

public interface QoSManager {


	
	/**
	 * 
	 * @param user
	 * @param pw
	 * @return AuthKey for further interaction
	 */
	 
	String authorize (String user, String pw);
	
	/**
	 *   
	 * @param authkey
	 * @param username
	 * @param rule
	 * @return
	 */
	
	String applyQoSRule (String authkey, String username, String rule);
	/**
	 * 
	 * @param authkey
	 * @param userName
	 * @param source
	 * @param props
	 * @return
	 */
	  
	String startSession ( String authkey,String userName,QoSFeatureProperties props);
	/**
	 * 
	 * @param authkey
	 * @param sessionKey
	 * @param props
	 * @return
	 */
	
	String modifySession ( String authkey, String sessionKey, String mediaIdentifier,  QoSFeatureProperties props);
	/**
	 * 
	 * @param authkey
	 * @param sessionKey
	 * @return
	 */
	
	String endSession(  String authkey, String sessionKey);
	/**
	 * This method creates a default profile for a specific service or overwrites an existing
	 * @param authkey
	 * @param serviceName
	 * @param profile
	 * @return
	 */
	
	String createServiceProfile (String authkey, String serviceName, QoSFeatureProperties profile);
	/**
	 * This method initiates a subscription to a specific service, when a service is called the subscriber is notified with the corresponding session id
	 * @param authkey
	 * @param serviceName
	 * @return
	 */
	
	String subscribeToService (String authkey,   String serviceName); 
	/**
	 * 
	 * @param authkey
	 * @param userName
	 * @return
	 */
	
	GetQoSStatusResponse getQoSStatus (String authkey,String userName);
	/**
	 * 
	 * @param authkey
	 * @param userName
	 * @param event
	 * @return
	 */
	 
	String subscribe (String authkey,  String userName, String event);
	

}
