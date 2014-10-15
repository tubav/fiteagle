package org.fiteagle.adapter.physicalNodeAdapter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.transport.TransportException;

import org.fiteagle.adapter.common.AdapterConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndroidSSHConnector extends SSHConnector {
	//TODO: correct/finish this class and enable it in PhysicalNodeAdapter.getJavaInstances()!
	Logger log = LoggerFactory.getLogger(this.getClass());

	public AndroidSSHConnector(String ip, String port, String username,
			String password, AdapterConfiguration adapterConfiguration) {
		super(ip, port, username, password, adapterConfiguration);
	}
	
	public void createAuthorizedKeysFile(String newUser) {
		try {
			Session session = getClient().startSession();
			try{
			
			//TODO: change this one here!!!
//			Command com3 = session.exec("echo "+getPassword()+"| sudo -S touch /home/"+newUser+"/.ssh/authorized_keys");
//			Command com3 = session.exec("echo "+getPassword()+"| sudo -S touch /sdcard/id_rsa.pub");
			Command com3 = null;
			try {
				 com3 = session.exec("touch /sdcard/id_rsa.pub");
//				com3 = session.exec("ls");
			} catch (Exception e) {
				log.error("create authorized keys file failed: "+e.getMessage());
			}

			System.out.println(IOUtils.readFully(com3.getInputStream()).toString());
			com3.join(5, TimeUnit.SECONDS);
			System.out.println("\n **exit status: "+com3.getExitStatus());
		
			}finally{
			session.close();
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addSSHKey(String sshKey, String newUser) {
		try {
			Session session = getClient().startSession();
			try{
			//TODO: change this to add the string into the  authorized keys
//				Command com4 = session.exec("echo "+getPassword()+"| sudo -S bash -c 'echo "+sshKey+" >> /sdcard/id_rsa.pub'" );
				Command com4=null;
				try {
					com4 = session.exec("echo "+sshKey+" >> /sdcard/id_rsa.pub");
				} catch (Exception e) {
					log.error("add SSH key failed: "+e.getMessage());
				}

				
				System.out.println(IOUtils.readFully(com4.getInputStream()).toString());
				com4.join(5, TimeUnit.SECONDS);
				System.out.println("\n **exit status: "+com4.getExitStatus());
			}finally{
			session.close();
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void removeAuthorizedKeyFile() {
		try {
			Session session = getClient().startSession();
			try{
			
			//TODO: change this one here!!!
//			Command com3 = session.exec("echo "+getPassword()+"| sudo -S touch /home/"+newUser+"/.ssh/authorized_keys");
//			Command com3 = session.exec("echo "+getPassword()+"| sudo -S rm /sdcard/id_rsa.pub");
				Command com3 =null;
			try {
				com3 = session.exec("rm /sdcard/id_rsa.pub");				
			} catch (Exception e) {
				log.error("remove authorized key file failed: "+e.getMessage());
			}
			
			System.out.println(IOUtils.readFully(com3.getInputStream()).toString());
			com3.join(5, TimeUnit.SECONDS);
			System.out.println("\n **exit status: "+com3.getExitStatus());
		
			}finally{
			session.close();
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
