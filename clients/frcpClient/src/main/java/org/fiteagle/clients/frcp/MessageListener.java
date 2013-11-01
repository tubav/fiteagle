package org.fiteagle.clients.frcp;

import org.jivesoftware.smack.packet.Message;

public interface MessageListener {
	
	public void processMessage(Message m);

}
