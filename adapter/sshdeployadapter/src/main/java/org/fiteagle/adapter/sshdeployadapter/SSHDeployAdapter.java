package org.fiteagle.adapter.sshdeployadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fiteagle.adapter.common.AdapterConfiguration;
import org.fiteagle.adapter.common.AdapterUser;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.SSHAccessable;

public class SSHDeployAdapter extends ResourceAdapter implements SSHAccessable {
	private static boolean loaded = false;
	private String hardwareType;
	private String ip = "";
	private String username = "";
	private String password = "";
	private String sshKey = "";

	private static SSHDeployAdapterConfiguration sshDeployAdapterConfig = SSHDeployAdapterConfiguration
			.getInstance();
	private String port;
	private AdapterConfiguration adapterConfiguration;

	public SSHDeployAdapter() {

		// TODO: set staff over properties call other deploy adapter
		super();
		// this.setType("org.fiteagle.adapter.sshdeployadapter.SSHDeployAdapter");
		// this.create();
		// SSHDeployAdapter(sshDeployAdapterConfig.get, String username, String
		// password, String sshKey);
		// this.setHardwareType(hardwareType); //TODO get hardware type from
		// configuration
	}

	public SSHDeployAdapter(String ip, String port, String username,
			String password, String sshKey) {
		super();
		this.setType("org.fiteagle.adapter.sshdeployadapter.SSHDeployAdapter");
		this.setIp(ip);
		this.setPort(port);
		this.setUsername(username);
		this.setPassword(password);
		this.setSshKey(sshKey);
	}

	@Override
	public void start() {

		if (adapterConfiguration != null) {
			SSHConnector connector = new SSHConnector(ip, port, username,
					password, adapterConfiguration);
			for (AdapterUser user : adapterConfiguration.getUsers()) {
				String newUser = user.getUsername();
				
				connector.connect();
				connector.createUserAccount(newUser);

				connector.createUserSSHDirectory(newUser);
				connector.createAuthorizedKeysFile(newUser);
				connector.changeOwnerOfUserHome(newUser);
				for(String userPublicKey: user.getSshPublicKeys()){
					connector.addSSHKey(userPublicKey, newUser);
				}
			}
			connector.disconnect();
		}

	}

	@Override
	public void stop() {
	
		SSHConnector connector = new SSHConnector(ip, port, username, password, adapterConfiguration);
		for (AdapterUser user : adapterConfiguration.getUsers()) {
			String newUser = user.getUsername();
			connector.connect();
			connector.lockAccount(newUser);
			connector.killAllUserProcesses(newUser);
			connector.deleteUser(newUser);
			connector.deleteUserDirectory(newUser);
			connector.disconnect();
			
	}

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getHardwareType() {
		return hardwareType;
	}

	@Override
	public void setHardwareType(String hardwareType) {
		this.hardwareType = hardwareType;
	}

	@Override
	public String getIp() {
		return ip;
	}

	@Override
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String getPort() {
		return this.port;
	}

	@Override
	public void setPort(String port) {
		this.port = port;

	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getSshKey() {
		return sshKey;
	}

	@Override
	public void setSshKey(String sshKey) {
		this.sshKey = sshKey;
	}

	
	public static List<ResourceAdapter> getJavaInstances() {
		List<ResourceAdapter> resourceAdapters = new ArrayList<ResourceAdapter>();

		String[] ips = null;
		String[] ports = null;
		String[] usernames = null;
		String[] passwords = null;
		String[] sshKeys = null;
		String[] countries = null;
		String[] latitudes = null;
		String[] longitudes = null;
		String[] hardwareTypes = null;

		if (sshDeployAdapterConfig.getPasswords() == null
				&& sshDeployAdapterConfig.getSsh_keys() == null
				|| sshDeployAdapterConfig.getIps() == null
				|| sshDeployAdapterConfig.getUsernames() == null)
			return resourceAdapters;

		if (sshDeployAdapterConfig.getIps() != null) {
			ips = sshDeployAdapterConfig.getIps().split(",");
		}

		if (sshDeployAdapterConfig.getUsernames() != null) {
			usernames = sshDeployAdapterConfig.getUsernames().split(",");
		}

		if (sshDeployAdapterConfig.getPasswords() != null) {
			passwords = sshDeployAdapterConfig.getPasswords().split(",");
		}

		if (sshDeployAdapterConfig.getSsh_keys() != null) {
			sshKeys = sshDeployAdapterConfig.getSsh_keys().split(",");
		}

		if (sshDeployAdapterConfig.getCountries() != null) {
			countries = sshDeployAdapterConfig.getCountries().split(",");
		}

		if (sshDeployAdapterConfig.getLatitudes() != null) {
			latitudes = sshDeployAdapterConfig.getLatitudes().split(",");
		}

		if (sshDeployAdapterConfig.getLongitues() != null) {
			longitudes = sshDeployAdapterConfig.getLongitues().split(",");
		}

		if (sshDeployAdapterConfig.getHardwareTypes() != null) {
			hardwareTypes = sshDeployAdapterConfig.getHardwareTypes()
					.split(",");
		}
		if (sshDeployAdapterConfig.getPorts() != null) {
			ports = sshDeployAdapterConfig.getPorts().split(",");
		}

		if (!(ips.length == usernames.length && usernames.length == passwords.length)
				&& (!(ips.length == usernames.length && usernames.length == sshKeys.length)))
			return resourceAdapters;

		for (int i = 0; i < usernames.length; i++) {
			SSHDeployAdapter sshDeployAdapter = new SSHDeployAdapter(
					ips[i].trim(), ports[i].trim(), usernames[i].trim(),
					passwords[i].trim(), sshKeys[i].trim());

			if (hardwareTypes != null && i < hardwareTypes.length)
				sshDeployAdapter.setHardwareType(hardwareTypes[i].trim());
			if (countries != null && i < countries.length)
				sshDeployAdapter.addProperty("country", countries[i].trim());
			if (latitudes != null && i < latitudes.length)
				sshDeployAdapter.addProperty("latitude", latitudes[i].trim());
			if (longitudes != null && i < longitudes.length)
				sshDeployAdapter.addProperty("longitude", longitudes[i].trim());

			sshDeployAdapter.setExclusive(true);
			resourceAdapters.add(sshDeployAdapter);
		}

		return resourceAdapters;
	}

	public void setPreferences(String ips, String usernames, String passwords,
			String hardwareTypesPreference, String sshKeys, String countries,
			String latitudes, String longitudes) {

		if (ips != null)
			sshDeployAdapterConfig.setIps(ips);

		if (usernames != null)
			sshDeployAdapterConfig.setUsernames(usernames);

		if (passwords != null)
			sshDeployAdapterConfig.setPasswords(passwords);

		if (sshKeys != null)
			sshDeployAdapterConfig.setSsh_keys(sshKeys);

		if (countries != null)
			sshDeployAdapterConfig.setCountries(countries);

		if (latitudes != null)
			sshDeployAdapterConfig.setLatitudes(latitudes);

		if (longitudes != null)
			sshDeployAdapterConfig.setLongitues(longitudes);

		if (hardwareTypesPreference != null)
			sshDeployAdapterConfig.setHardwareTypes(hardwareTypesPreference);
	}

	public void removeAllPreferences() {

		sshDeployAdapterConfig.removeIps();

		sshDeployAdapterConfig.removeUsernames();

		sshDeployAdapterConfig.removePasswords();

		sshDeployAdapterConfig.removeSsh_keys();

		sshDeployAdapterConfig.removeCountries();

		sshDeployAdapterConfig.removeLatitudes();

		sshDeployAdapterConfig.removeLongitues();

		sshDeployAdapterConfig.removeHardwareTypes();
	}

	@Override
	public boolean isLoaded() {
		return this.loaded;
	}

	@Override
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	@Override
	public void configure(AdapterConfiguration configuration) {
		this.adapterConfiguration = configuration;

	}

	@Override
	public void checkStatus() {
		// TODO Auto-generated method stub
		
	}

}
