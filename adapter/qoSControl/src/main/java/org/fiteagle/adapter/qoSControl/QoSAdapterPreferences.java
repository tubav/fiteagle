package org.fiteagle.adapter.qoSControl;

import java.util.prefs.Preferences;

public class QoSAdapterPreferences {

	Preferences prefs;
	
	public QoSAdapterPreferences(){
		prefs = Preferences.userNodeForPackage(getClass());
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
