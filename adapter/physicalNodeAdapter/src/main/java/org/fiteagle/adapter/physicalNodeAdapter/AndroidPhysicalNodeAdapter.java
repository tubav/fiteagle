package org.fiteagle.adapter.physicalNodeAdapter;

import java.io.IOException;

import org.fiteagle.adapter.common.AdapterConfiguration;
import org.fiteagle.adapter.common.AdapterUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndroidPhysicalNodeAdapter extends PhysicalNodeAdapter{
	private String staticUsername = "test21";
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	public void configure(AdapterConfiguration configuration) {
		
		log.warn("ANDROID CONFIGURE METHOD WITHOUT ANY PARAM IS CALLED!");
		
//		log.warn("CONFIGURE METHOD WITH AdapterConfiguration configuration AS PARAM CALLED");
		
		
		if(configuration!=null){
			this.setConfiguration(configuration);
			
			
			AndroidSSHConnector androidConnector = new AndroidSSHConnector(this.getIp(), this.getPort(), this.getRootUserName(), this.getRootPassword(), configuration);
			for (AdapterUser user : configuration.getUsers()) {
				user.setUsername(staticUsername);//TODO: after android resource is capable adding different user names, delete this line!
				String newUser = user.getUsername();
				
				androidConnector.connect();
//				androidConnector.createUserAccount(newUser);
//				androidConnector.createUserSSHDirectory(newUser);
				androidConnector.createAuthorizedKeysFile(newUser);
//				androidConnector.changeOwnerOfUserHome(newUser);
				for(String userPublicKey: user.getSshPublicKeys()){
					androidConnector.addSSHKey(userPublicKey, newUser);
				}
			}
			androidConnector.disconnect();
			
//			SSHConnector connector = new SSHConnector(this.getIp(), this.getPort(), this.getRootUserName(), this.getRootPassword(), configuration);
//			for (AdapterUser user : configuration.getUsers()) {
//				String newUser = user.getUsername();
//				connector.connect();
//				connector.createUserAccount(newUser);
//				connector.createUserSSHDirectory(newUser);
//				connector.createAuthorizedKeysFile(newUser);
//				connector.changeOwnerOfUserHome(newUser);
//				for(String userPublicKey: user.getSshPublicKeys()){
//					connector.addSSHKey(userPublicKey, newUser);
//				}
//			}
//			connector.disconnect();
//			
		}
		
	}

	//TODO: this is needed for android node adapter with another connector class!
	@Override
	public void release() {
		
		log.warn("ANDROID RELEASE WITHOUT ANY PARAM IS CALLED!");
		
//		// ON THE REMOTE MACHINE FOR THIS USER// OR ALL OF THE HOME DIRECTORIES
//		// UNDER HOME!
		AndroidSSHConnector androidConnector = new AndroidSSHConnector(this.getIp(), this.getPort(), this.getRootUserName(), this.getRootPassword(), getConfiguration());
		for (AdapterUser user : getConfiguration().getUsers()) {
//			String newUser = user.getUsername();
			androidConnector.connect();
			androidConnector.removeAuthorizedKeyFile();
			
//			connector.lockAccount(newUser);
//			connector.killAllUserProcesses(newUser);
//			connector.deleteUser(newUser);
//			connector.deleteUserDirectory(newUser);
			
			androidConnector.disconnect();
		}

	}

}
