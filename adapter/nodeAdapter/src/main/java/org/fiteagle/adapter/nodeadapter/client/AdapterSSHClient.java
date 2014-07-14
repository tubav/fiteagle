package org.fiteagle.adapter.nodeadapter.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.PublicKey;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.transport.verification.HostKeyVerifier;

public class AdapterSSHClient {
	
	SSHClient client = new SSHClient();
	
public boolean isAccessable(String ip, int port){
	
		InetAddress host;
		try {
			host = InetAddress.getByName(ip);
			
			client.addHostKeyVerifier(new HostKeyVerifier() {
				
				public boolean verify(String arg0, int arg1, PublicKey arg2) {
					// TODO Auto-generated method stub
					return true;
				}
			});
			
			client.connect(host, port);
			return client.isConnected();
//			client.authPassword(username, password);
//			this.client = client;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

}

