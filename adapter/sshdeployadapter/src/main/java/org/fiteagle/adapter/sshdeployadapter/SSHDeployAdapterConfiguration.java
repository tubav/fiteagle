package org.fiteagle.adapter.sshdeployadapter;

import java.util.prefs.Preferences;


public class SSHDeployAdapterConfiguration {
	
	private static SSHDeployAdapterConfiguration sshDeployAdapterConfig;
	private Preferences preferences;
	
	public SSHDeployAdapterConfiguration() {
		this.preferences = Preferences.userNodeForPackage(getClass());
		
	}
	
	
	public static SSHDeployAdapterConfiguration getInstance(){
		if(sshDeployAdapterConfig==null)
			sshDeployAdapterConfig = new SSHDeployAdapterConfiguration();
		return sshDeployAdapterConfig;
	}
	
	private void addPreference(String key, String value){
		preferences.put(key, value);
	}
	
	private String getPreference(String key){
		return preferences.get(key, null);
	}



	public void setIps(String ips) {
		addPreference("ips",ips);
	}

	public void setUsernames(String usernames) {
		addPreference("usernames",usernames);
	}

	public void setPasswords(String passwords) {
		addPreference("passwords", passwords);
	}


	public void setSsh_keys(String sshKeys) {
		addPreference("ssh_keys", sshKeys);
	}



	public void setCountries(String countries) {
		addPreference("countries",countries);
	}



	public void setLatitudes(String latitudes) {
		addPreference("latitudes", latitudes);
	}



	public void setLongitues(String longitudes) {
		addPreference("longitudes",longitudes);
	}
	
	public void setHardwareTypes(String hardwareTypes) {
		addPreference("hardware_types",hardwareTypes);
	}
	
	public String getHardwareTypes() {
		return getPreference("hardware_types");
	}
	
	
	public String getIps() {
		return getPreference("ips");
	}

	public String getUsernames() {
		return getPreference("usernames");
	}

	public String getPasswords() {
		return getPreference("passwords");
	}


	public String getSsh_keys() {
		return getPreference("ssh_keys");
	}



	public String getCountries() {
		return getPreference("countries");
	}



	public String getLatitudes() {
		return getPreference("latitudes");
	}



	public String getLongitues() {
		return getPreference("longitudes");
	}
	
	
	public void removeHardwareTypes() {
		preferences.remove("hardware_types");
	}
	
	public void removeIps() {
		preferences.remove("ips");
	}

	public void removeUsernames() {
		preferences.remove("usernames");
	}

	public void removePasswords() {
		preferences.remove("passwords");
	}


	public void removeSsh_keys() {
		preferences.remove("ssh_keys");
	}



	public void removeCountries() {
		preferences.remove("countries");
	}



	public void removeLatitudes() {
		preferences.remove("latitudes");
	}



	public void removeLongitues() {
		preferences.remove("longitudes");
	}
	
	

}
