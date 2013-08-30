/*
 * $Id: SessionManager.java 2219 2010-08-17 15:26:33Z dvi $
 *
 * Copyright (C) 2004-2010 FhG Fokus
 *
 * This file is part of Open IMS Core - an open source IMS CSCFs & HSS
 * implementation
 *
 * Open IMS Core is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * For a license to use the Open IMS Core software under conditions
 * other than those described here, or to purchase support for this
 * software, please contact Fraunhofer FOKUS by e-mail at the following
 * addresses:
 *     info@open-ims.org
 *
 * Open IMS Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package de.fhg.fokus.diameter.DiameterPeer.session;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;
import java.util.logging.Logger;


import de.fhg.fokus.diameter.DiameterPeer.DiameterPeer;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Session_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message;
import de.fhg.fokus.diameter.DiameterPeer.peer.PeerManager;
import de.fhg.fokus.diameter.DiameterPeer.session.acct.AcctSession;
import de.fhg.fokus.diameter.DiameterPeer.session.auth.AuthSessionEventType;
import de.fhg.fokus.diameter.DiameterPeer.session.auth.AuthSessionStateMachine;
import de.fhg.fokus.diameter.DiameterPeer.session.auth.AuthSession;

public class SessionManager extends Thread {
	
	private static final Logger LOGGER = Logger.getLogger(PeerManager.class.toString());

	public DiameterPeer diameterPeer;

	/** Default Authorization Session Timeout */
	public int DefaultAuthSessionTimeout=60;

	/** Maximum Authorization Session Timeout */
	public int MaxAuthSessionTimeout=300;
		
	private long sessionId1 = 0;
	private long sessionId2 = 0;

	private TreeMap<String,Session> sessions = new TreeMap<String,Session>();
	boolean running = false;
	public SessionManager(DiameterPeer dp)
	{
		this.diameterPeer = dp;		
		this.sessionId1 = ((int)(65536*Math.random()))<<16;
		this.sessionId1 |= System.currentTimeMillis() & 0xFFFF;
		this.sessionId2 = 0;
		running = true;
	}

	private synchronized String makeSessionId()
	{
		StringBuffer stb = new StringBuffer();
		stb.append(this.diameterPeer.FQDN);
		stb.append(';');
		stb.append(this.sessionId1);
		stb.append(';');
		stb.append(this.sessionId2);		
		this.sessionId2++;		
		return stb.toString();
	}
	
	
	
	
	
	public Session createSession()
	{
		Session x = new Session(this);
		x.sessionId = makeSessionId();
		this.sessions.put(x.sessionId, x);
		return x;
	}
	
	
	public Session makeSession(int applicationId,String sessionId,SessionType type)
	{
		Session x = new Session(this);
		x.sessionId = sessionId;
		x.applicationId = applicationId;
		x.type = type;
		this.sessions.put(x.sessionId, x);
		return x;
	}

	public Session getSession(String sessionId)
	{
		Session x = this.sessions.get(sessionId);
		if (x==null) return null;
		
		if (x.tryLock()) return x;		
		else return null;
	}
	
	public Session getSession(Message message) throws UnsupportedEncodingException
	{
		AVP_Session_Id session_id = message.getSessionId();
		if (session_id==null) return null;
		return this.getSession(session_id.get());
	}
	
	private void deleteSession(Session session)
	{
		if (session==null) return;
		this.sessions.remove(session.sessionId);
		session.delete();		
	}
	
	public void terminateSession(Session session)
	{
		if (session instanceof AuthSession)
			AuthSessionStateMachine.process((AuthSession) session,AuthSessionEventType.ServiceTerminated,null,this.diameterPeer);		
		this.deleteSession(session);
	}	
	
	public void dropSession(Session session)
	{
		if (session instanceof AuthSession)
			session.fireEvent(new SessionEvent(AuthSessionEventType.SessionDrop));
		this.deleteSession(session);
	}
	
	
	public AuthSession makeAuthSession(int applicationId,String sessionId,SessionType type)
	{
		AuthSession x = new AuthSession(this);
		x.sessionId = sessionId;
		x.applicationId = applicationId;
		x.type = type;
		x.timeout = System.currentTimeMillis()/1000+this.DefaultAuthSessionTimeout;
		x.lifetime = 0;
		x.grace_period = 0;
		this.sessions.put(x.sessionId, x);
		return x;
	}
	
	public AuthSession createAuthClientSession(boolean isStateful)
	{
		AuthSession x = new AuthSession(this);
		x.sessionId = makeSessionId();
		if (isStateful) x.type = SessionType.AuthorizationClientStateful;
		else x.type = SessionType.AuthorizationClientStateless;
		x.timeout = System.currentTimeMillis()/1000+this.DefaultAuthSessionTimeout;
		x.lifetime = 0;
		x.grace_period = 0;
		this.sessions.put(x.sessionId, x);
		return x;
	}
	
	public AuthSession createAuthServerSession(Message msg,boolean isStateful,SessionListener eventListener)
	{
		String sessionId = null;
		if (msg==null) sessionId = this.makeSessionId();
		else{
			AVP_Session_Id avp = msg.getSessionId();
			try {
				if (avp==null||avp.get().length()==0) sessionId = makeSessionId();
				else sessionId = new String(avp.get());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}		
		AuthSession x = new AuthSession(this);
		x.sessionId = sessionId;
		if (isStateful) x.type = SessionType.AuthorizationServerStateful;
		else x.type = SessionType.AuthorizationServerStateless;
		x.timeout = System.currentTimeMillis()/1000+this.DefaultAuthSessionTimeout;
		x.lifetime = 0;
		x.grace_period = 0;
		x.addEventListener(eventListener);
		x.tryLock();
		this.sessions.put(x.sessionId, x);
		try {
			x.fireEvent(new SessionEvent(AuthSessionEventType.SessionCreated));
			x.updateSessionTimers(msg);			
			AuthSessionStateMachine.process(x, AuthSessionEventType.ReceiveRequest,msg,this.diameterPeer);
		}finally{
			x.unLock();
		}
		return x;
	}
	
	public AuthSession getAuthSession(String sessionId)
	{
		Session x = this.getSession(sessionId);
		if (x==null) return null;		
		if (x instanceof AuthSession) 
			return (AuthSession) x;
		else {
			x.unLock();
			return null;
		}
	}

	
	public AcctSession createAcctClientSession()
	{
		AcctSession x = new AcctSession(this);
		x.sessionId = makeSessionId();
		x.type = SessionType.AccountingClient;		
		this.sessions.put(x.sessionId, x);
		return x;
	}
	
	public AcctSession createAcctServerSession(boolean isStateful)
	{
		AcctSession x = new AcctSession(this);
		x.sessionId = makeSessionId();
		if (isStateful) x.type = SessionType.AccountingServerStateful;
		else x.type = SessionType.AccountingServerStateless;
		return x;
	}
	
	public AcctSession getAcctSession(String sessionId)
	{
		Session x = this.getSession(sessionId);
		if (x==null) return null;
		if (x instanceof AcctSession) 
			return (AcctSession) x;
		else {
			x.unLock();
			return null;
		}
	}

	public void log()
	{
		LOGGER.warning("----- Diameter Sessions -----");
		for(Session x:sessions.values())
			x.log();
		LOGGER.warning("-----------------------------");
	}
	public void shutdown(){
		running = false;
	}
	/**
	 * Diameter Session Manager Timer
	 */
	@Override
	public void run() 
	{
		int cnt=0;		
		AuthSession auth;
		//DiameterAcctSession acct;
		while(running){
			
			synchronized(sessions){
				for(Session x:sessions.values()){
					x.tryLock();					
					try{
						switch(x.type){
							case AuthorizationClientStateful:
							case AuthorizationServerStateful:
								auth = (AuthSession)x;
								if (auth.timeout>=0 && auth.timeout<=System.currentTimeMillis()/1000){
									//Session Timeout
									LOGGER.fine("Session Timeout "+auth.sessionId);
									AuthSessionStateMachine.process(auth,AuthSessionEventType.SessionTimeout,null,this.diameterPeer);									
								}
								else if (auth.lifetime>0 && auth.lifetime+auth.grace_period<=System.currentTimeMillis()/1000){
									//lifetime+grace Timeout
									LOGGER.fine("Lifetime+grace Timeout "+auth.sessionId);
									AuthSessionStateMachine.process(auth,AuthSessionEventType.SessionGraceTimeout,null,this.diameterPeer);
								}
								else if (auth.lifetime>0 && auth.lifetime<=System.currentTimeMillis()/1000){
									//lifetime Timeout
									LOGGER.fine("Lifetime Timeout "+auth.sessionId);
									AuthSessionStateMachine.process(auth,AuthSessionEventType.SessionLifetimeTimeout,null,this.diameterPeer);
								}
								break;
								
							default:
								break;
						}
					} finally{
						x.unLock();
					}
				}
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//if (cnt%5==0) 
				this.log();
			cnt++;
		}
	}

	
}
