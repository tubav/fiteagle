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


	public String getHardware_types() {
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

}
