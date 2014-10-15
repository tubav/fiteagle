package org.fiteagle.adapter.physicalNodeAdapter;

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
		this.setIp(ip);
		this.setPort(port);
		this.setUsername(username);
		this.setPassword(password);
		this.adapterConfiguration = adapterConfiguration;
	}

	public void connect(){
		
		setClient(new SSHClient());
		InetAddress host;
		try {
			host = InetAddress.getByName(getIp());
			getClient().addHostKeyVerifier(new HostKeyVerifier() {
				
				public boolean verify(String arg0, int arg1, PublicKey arg2) {
					// TODO Auto-generated method stub
					return true;
				}
			});
			
			try {
				getClient().connect(host, Integer.parseInt(getPort()));
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
			
			getClient().authPassword(getUsername(), getPassword());
			this.setClient(client);
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
			Session session = getClient().startSession();
			try{
			Command cmd = session.exec("echo "+getPassword()+"| sudo -S adduser -gecos \""+newUser+" "+ newUser+", test@test.test\" -disabled-password "+newUser+" ");
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
			this.getClient().disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void createUserSSHDirectory(String newUser) {
		try {
			Session session = getClient().startSession();
			try{
			Command cmd = session.exec("echo "+getPassword()+"| sudo -S mkdir /home/"+newUser+"/.ssh");
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
			Session session = getClient().startSession();
			try{
			
			
			Command com3 = session.exec("echo "+getPassword()+"| sudo -S touch /home/"+newUser+"/.ssh/authorized_keys");
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
			Session session = getClient().startSession();
			try{
			
				
				Command com2 = session.exec("echo "+getPassword()+"| sudo -S chown -R "+newUser+":"+newUser+" /home/"+newUser+"/.ssh");
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
			Session session = getClient().startSession();
			try{
			
				Command com4 = session.exec("echo "+getPassword()+"| sudo -S bash -c 'echo "+sshKey+" >> /home/"+newUser+"/.ssh/authorized_keys'" );
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

	public void deleteUser(String newUser) {
		try {
			Session session = getClient().startSession();
			try{
			
				Command command = session.exec("echo "+getPassword()+"| sudo -S userdel -r "+newUser+"" );
				System.out.println(IOUtils.readFully(command.getInputStream()).toString());
				command.join(5, TimeUnit.SECONDS);
				System.out.println("\n **exit status: "+command.getExitStatus());
				
				
			
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

	public void deleteUserDirectory(String newUser) {
		try {
			Session session = getClient().startSession();
			try{
			
				Command command = session.exec("echo "+getPassword()+"| sudo -S rm -R /home/"+newUser+"" );
				System.out.println(IOUtils.readFully(command.getInputStream()).toString());
				command.join(5, TimeUnit.SECONDS);
				System.out.println("\n **exit status: "+command.getExitStatus());
				
				
			
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

	public void lockAccount(String newUser) {
		try {
			Session session = getClient().startSession();
			try{
			
				Command command = session.exec("echo "+getPassword()+"| sudo -S passwd -l "+newUser+"" );
				System.out.println(IOUtils.readFully(command.getInputStream()).toString());
				command.join(5, TimeUnit.SECONDS);
				System.out.println("\n **exit status: "+command.getExitStatus());
				
				
			
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

	public void killAllUserProcesses(String newUser) {
		try {
			Session session = getClient().startSession();
			try{
			
				Command command = session.exec("echo "+getPassword()+"| sudo -S killall -KILL -u "+newUser+"" );
				System.out.println(IOUtils.readFully(command.getInputStream()).toString());
				command.join(5, TimeUnit.SECONDS);
				System.out.println("\n **exit status: "+command.getExitStatus());
				
				
			
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

	public SSHClient getClient() {
		return client;
	}

	public void setClient(SSHClient client) {
		this.client = client;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	
}
