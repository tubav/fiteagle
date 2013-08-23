package org.fiteagle.adapter.sshdeployadapter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.PublicKey;
import java.util.concurrent.TimeUnit;

import org.fiteagle.adapter.common.AdapterConfiguration;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.transport.verification.HostKeyVerifier;

public class SSHConnector {
	  private String ip="";
	  private String username="";
	  private String password="";
	  private String port;
	
	
	private SSHClient client;
	AdapterConfiguration adapterConfiguration ;
	public SSHConnector(String ip,String port, String username, String password,AdapterConfiguration adapterConfiguration) {
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
		this.adapterConfiguration = adapterConfiguration;
	}

	public void connect(){
		
		client = new SSHClient();
		InetAddress host;
		try {
			host = InetAddress.getByName(ip);
			client.addHostKeyVerifier(new HostKeyVerifier() {
				
				public boolean verify(String arg0, int arg1, PublicKey arg2) {
					// TODO Auto-generated method stub
					return true;
				}
			});
			client.connect(host);
			client.authPassword(username, password);
			this.client = client;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void createUserAccount(String newUser) {
		try {
			Session session = client.startSession();
			try{
			Command cmd = session.exec("echo "+password+"| sudo -S adduser -gecos \""+newUser+" "+ newUser+", test@test.test\" -disabled-password "+newUser+" ");
			cmd.join(5, TimeUnit.SECONDS);
			System.out.println(IOUtils.readFully(cmd.getInputStream()).toString());

			System.out.println("\n **exit status: "+cmd.getExitStatus());
			}finally{
			session.close();
			}
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void disconnect() {
		try {
			this.client.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void createUserSSHDirectory(String newUser) {
		try {
			Session session = client.startSession();
			try{
			Command cmd = session.exec("echo "+password+"| sudo -S mkdir /home/"+newUser+"/.ssh");
			cmd.join(5, TimeUnit.SECONDS);
			System.out.println(IOUtils.readFully(cmd.getInputStream()).toString());
			System.out.println("\n **exit status: "+cmd.getExitStatus());
			
			}finally{
			session.close();
			}
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void createAuthorizedKeysFile(String newUser) {
		try {
			Session session = client.startSession();
			try{
			
			
			Command com3 = session.exec("echo "+password+"| sudo -S touch /home/"+newUser+"/.ssh/authorized_keys");
			System.out.println(IOUtils.readFully(com3.getInputStream()).toString());
			com3.join(5, TimeUnit.SECONDS);
			System.out.println("\n **exit status: "+com3.getExitStatus());
		
			}finally{
			session.close();
			}
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void changeOwnerOfUserHome(String newUser) {
		try {
			Session session = client.startSession();
			try{
			
				
				Command com2 = session.exec("echo "+password+"| sudo -S chown -R "+newUser+":"+newUser+" /home/"+newUser+"/.ssh");
				System.out.println(IOUtils.readFully(com2.getInputStream()).toString());
				com2.join(5, TimeUnit.SECONDS);
				System.out.println("\n **exit status: "+com2.getExitStatus());
			
			
			}finally{
			session.close();
			}
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void addSSHKey(String sshKey, String newUser) {
		try {
			Session session = client.startSession();
			try{
			
				Command com4 = session.exec("echo "+password+"| sudo -S bash -c 'echo "+sshKey+" >> /home/"+newUser+"/.ssh/authorized_keys'" );
				System.out.println(IOUtils.readFully(com4.getInputStream()).toString());
				com4.join(5, TimeUnit.SECONDS);
				System.out.println("\n **exit status: "+com4.getExitStatus());
				
				
			
			}finally{
			session.close();
			}
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
