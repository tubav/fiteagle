package org.fiteagle.clients.frcp;

import org.jivesoftware.smack.XMPPException;

public class FRCPXMPPCLI {

	public static void main(final String[] args) throws XMPPException {
			
		String username = "test";
		String password = "test";
		String hostname = "fuseco.fokus.fraunhofer.de";
		final FRCPXMPPServer server = new FRCPXMPPServer(username,
				hostname, password);
		System.out.println("Listening (" + username + "@" + hostname + ")...");
		server.run();
	}
}
