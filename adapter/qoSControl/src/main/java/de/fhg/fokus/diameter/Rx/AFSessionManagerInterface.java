package de.fhg.fokus.diameter.Rx;

import java.util.List;

import de.fhg.fokus.diameter.DiameterPeer.session.SessionListener;
import de.fhg.fokus.diameter.DiameterPeer.transaction.TransactionListener;

public interface AFSessionManagerInterface extends TransactionListener, SessionListener {

	public  AFSession getSession(String id);

	public  void removeSession(String id);

	public  AFSession createSession();

	public  AFSession createSession(String applicationIdentifier);
	
	public boolean initiateSession(AFSession session);
	
	public AFSession modifySession(AFSession session);

	public  boolean initiateSubscriptionSession(AFSession session, String event);
	
	public void stop();

	public List<AFSession> getSessions(String userName);

}