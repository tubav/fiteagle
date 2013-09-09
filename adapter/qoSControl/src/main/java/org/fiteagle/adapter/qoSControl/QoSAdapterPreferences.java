package org.fiteagle.adapter.qoSControl;

import java.util.prefs.Preferences;

public class QoSAdapterPreferences {

	private static final String DEFAULT_IP = "localhost";

	private static final String DEFAULT_PORT = "6066";

	private static final String DEFAULT_CLIENT_IP = "192.168.3.100";
	
	Preferences prefs;
	
	public QoSAdapterPreferences(){
		prefs = Preferences.userNodeForPackage(getClass());
		if(getIP()== null){
			setIP(DEFAULT_IP);
		}
		if(getPort() == null){
			setPort(DEFAULT_PORT);
		}
		if(getClientIP() == null){
			setClientIP(DEFAULT_CLIENT_IP);
		}
	}
	
	private void setClientIP(String defaultClientIp) {
		prefs.put("clientIP", defaultClientIp);
		
	}

	private void setPort(String defaultPort) {
		prefs.put("port", defaultPort);
	}

	private void setIP(String defaultIp) {
		prefs.put("ip", defaultIp);
		
	}

	private void addPreference(String key, String value){
		prefs.put(key, value);
	}
	
	
	private String getPreference(String key){
		return prefs.get(key, null);
	}
	
	
	public String getIP(){
		return getPreference("ip");
	}
	
	public String getPort(){
		return getPreference("port");
	}

	public String getClientIP() {
		return getPreference("clientIP");
	}
	
}
