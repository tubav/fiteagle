package org.fiteagle.adapter.physicalNodeAdapter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.prefs.Preferences;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.transport.verification.HostKeyVerifier;

import org.fiteagle.adapter.common.AdapterConfiguration;
import org.fiteagle.adapter.common.AdapterUser;
import org.fiteagle.adapter.common.OpenstackResourceAdapter;
import org.fiteagle.adapter.common.PhysicalNodeAdapterInterface;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.SSHAccessable;
import org.junit.internal.matchers.IsCollectionContaining;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhysicalNodeAdapter extends ResourceAdapter implements
		PhysicalNodeAdapterInterface {

	private String nodeName = "";
	private String callerId = "";

	// private String hardwareType;
	private String ip = "";
	private String rootUserName= "";
	private String rootPassword = "";
	private String port = "";
//	private String sshKey = "";
//	private String username = "";
	private AdapterConfiguration configuration;

	private static boolean utilsConfigured = false;
	Logger log = LoggerFactory.getLogger(this.getClass());

	public PhysicalNodeAdapter() {
		super();
		if (!isUtilsConfigured()) {
			this.configureUtils();
		}
		this.setType("org.fiteagle.adapter.physicalNodeAdapter.PhysicalNodeAdapter");// TODO:
																						// check
																						// if
																						// this
																						// is
																						// needed!
	}

	private void configureUtils() {
		Preferences preferences = Preferences.userNodeForPackage(getClass());
		
		
		if (preferences.get("node_names", null) != null){
			
			String[] nodeNames = preferences.get("node_names", null).split(",");
			
			for (int i = 0; i < nodeNames.length; i++) {
				nodeNames[i] = nodeNames[i].trim();
			}
			Utils.NODE_NAMES = nodeNames;
			
			if (preferences.get("root_user_names", null) != null){
				String[] rootUserNames = preferences.get("root_user_names", null).split(",");
				
				for (int i = 0; i < rootUserNames.length; i++) {
					rootUserNames[i] = rootUserNames[i].trim();
				}
				Utils.ROOT_USER_NAMES = rootUserNames;
			}else {
				Utils.ROOT_USER_NAMES = new String[0];
			}
			
			
			if (preferences.get("nodes_passwords", null) != null){
				String[] nodesPasswords = preferences.get("nodes_passwords", null).split(",");
				
				for (int i = 0; i < nodesPasswords.length; i++) {
					nodesPasswords[i] = nodesPasswords[i].trim();
				}
				Utils.NODES_PASSWORDS = nodesPasswords;
			}else {
				Utils.NODES_PASSWORDS = new String[0];
			}
			
			if (preferences.get("nodes_ips", null) != null){
				String[] nodesIps = preferences.get("nodes_ips", null).split(",");
				
				for (int i = 0; i < nodesIps.length; i++) {
					nodesIps[i] = nodesIps[i].trim();
				}
				Utils.NODES_IPS = nodesIps;
			}else {
				Utils.NODES_IPS = new String[0];
			}
			
			if (preferences.get("nodes_ports", null) != null){
				String[] nodesPorts= preferences.get("nodes_ports", null).split(",");
				
				for (int i = 0; i < nodesPorts.length; i++) {
					nodesPorts[i] = nodesPorts[i].trim();
				}
				Utils.NODES_PORTS = nodesPorts;
			}else {
				Utils.NODES_PORTS = new String[0];
			}
			
			setUtilsConfigured(true);
		} else {
			preferences.put("node_names", "Please add node names (node_name property) as java preferences. You can allso add properties like \"root_user_names\" \"nodes_passwords\" \"nodes_ips\" \"nodes_ports\"  ( all seperated by comma)");
		}
		
	}

//	@Override
//	public PhysicalNodeAdapterInterface create(String callerId, String ip, String port, String username, String sshKey) {
//		log.warn("CREATE METHODE WITH 5 PARAMS CALLED!!");
//		this.setCallerId(callerId);
//		log.warn("CREATE METHIDE called physical adapter properties: \n callerId: "+callerId+"\n callerId: "+this.getCallerId()+"\n Ip: "+this.getIp()+"\n root password: "+this.getRootPassword()+"\n root username: "+this.getRootUserName()+"\n port: "+this.getPort()+"\n node name: "+this.getNodeName());
//		// set these over configure method!!!!!!
////		this.setUsername(username);
////		this.setSshKey(sshKey);
//		return this;
//	}

	private void setCallerId(String callerId2) {
		this.callerId = callerId2;
	}

	@Override
	public String getCallerId() {
		return this.callerId;
	}

	@Override
	public void start() {
//		log.warn("START METHOD WITHOUT ANY PARAM IS CALLED!");

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void configure(AdapterConfiguration configuration) {
//		log.warn("CONFIGURE METHOD WITH AdapterConfiguration configuration AS PARAM CALLED");
		
		
		if(configuration!=null){
			this.setConfiguration(configuration);
			SSHConnector connector = new SSHConnector(this.getIp(), this.getPort(), this.getRootUserName(), this.getRootPassword(), configuration);
			for (AdapterUser user : configuration.getUsers()) {
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
	public void release() {
		// ON THE REMOTE MACHINE FOR THIS USER// OR ALL OF THE HOME DIRECTORIES
		// UNDER HOME!
		SSHConnector connector = new SSHConnector(this.getIp(), this.getPort(), this.getRootUserName(), this.getRootPassword(), getConfiguration());
		for (AdapterUser user : getConfiguration().getUsers()) {
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
	public void checkStatus() {
		// Try SSH connection if the machine is available
		if(isAccessable(getIp(), Integer.parseInt(getPort())))
			this.getProperties().put("operational_status", "geni_ready");

	}

	@Override
	public boolean isLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLoaded(boolean loaded) {
		// TODO Auto-generated method stub
	}

	public static List<ResourceAdapter> getJavaInstances() {

		List<ResourceAdapter> adapterList = new ArrayList<ResourceAdapter>();
		
		if(new PhysicalNodeAdapter().isUtilsConfigured() && Utils.NODE_NAMES!=null && Utils.NODE_NAMES.length>0){
			
			
			for (int i = 0; i < Utils.NODE_NAMES.length; i++) {
				PhysicalNodeAdapter tmpPhysicalNodeAdapter = new PhysicalNodeAdapter();
				
				tmpPhysicalNodeAdapter.setNodeName(Utils.NODE_NAMES[i]);
				tmpPhysicalNodeAdapter.setId(Utils.NODE_NAMES[i]);
				
				if(Utils.ROOT_USER_NAMES != null && Utils.ROOT_USER_NAMES.length > i)
					tmpPhysicalNodeAdapter.setRootUserName(Utils.ROOT_USER_NAMES[i]);
				
				if(Utils.NODES_PASSWORDS != null && Utils.NODES_PASSWORDS.length > i)
					tmpPhysicalNodeAdapter.setRootPassword(Utils.NODES_PASSWORDS[i]);
				
				if(Utils.NODES_IPS != null && Utils.NODES_IPS.length > i)
					tmpPhysicalNodeAdapter.setIp(Utils.NODES_IPS[i]);
				
				if(Utils.NODES_PORTS != null && Utils.NODES_PORTS.length > i)
					tmpPhysicalNodeAdapter.setPort(Utils.NODES_PORTS[i]);
			
				
				adapterList.add(tmpPhysicalNodeAdapter);
				
			}
		}
		return adapterList;
		// return new ArrayList<ResourceAdapter>();
	}

	@Override
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	@Override
	public String getNodeName() {
		return nodeName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getSshKey() {
//		return sshKey;
//	}
//
//	public void setSshKey(String sshKey) {
//		this.sshKey = sshKey;
//	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public PhysicalNodeAdapterInterface create(String callerId) {
		log.warn("CREATE METHODE WITH 1 PARAM CALLED!!");
		this.setCallerId(callerId);
		this.setAvailable(false);
		log.warn("set caller id is: "+this.getCallerId());
		return this;
	}

	public static boolean isUtilsConfigured() {
		return utilsConfigured;
	}

	public static void setUtilsConfigured(boolean utilsConfigured) {
		PhysicalNodeAdapter.utilsConfigured = utilsConfigured;
	}

	public String getRootUserName() {
		return rootUserName;
	}

	public void setRootUserName(String rootUserName) {
		this.rootUserName = rootUserName;
	}

	public String getRootPassword() {
		return rootPassword;
	}

	public void setRootPassword(String rootPassword) {
		this.rootPassword = rootPassword;
	}

	public AdapterConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(AdapterConfiguration configuration) {
		this.configuration = configuration;
	}
	
	
	private boolean isAccessable(String ip, int port){
		SSHClient client = new SSHClient();
		InetAddress host;
		try {
			host = InetAddress.getByName(ip);
			
			client.addHostKeyVerifier(new HostKeyVerifier() {
				
				public boolean verify(String arg0, int arg1, PublicKey arg2) {
					return true;
				}
			});
			
			client.connect(host, port);
			boolean isConnected = client.isConnected();
			client.close();
			return isConnected;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
		
	}

}
