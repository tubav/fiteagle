package org.fiteagle.clients.frcp;

import org.jivesoftware.smack.XMPPException;

public class FRCPXMPPServer implements Runnable {
	
	private FRCPXMPPClient client;

	public FRCPXMPPServer(final String username, final String hostname,
			final String password) throws XMPPException {
		this.client = new FRCPXMPPClient(username, hostname, password);
		this.client.connect();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (final InterruptedException e) {
				this.client.disconnect();
			}
		}
	}
}
