/*
 * $Id: Session.java 1971 2010-07-20 08:41:28Z dvi $
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
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;


import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Session_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message;
import de.fhg.fokus.diameter.DiameterPeer.peer.PeerManager;


public class Session {
	
	
	protected static final Logger LOGGER = Logger.getLogger(PeerManager.class.toString());

	private Lock sessionLock=new ReentrantLock();
	private boolean active=true;

	public SessionManager sessionManager;

	public String sessionId=null;	/**< session-ID as string */
	
	public long applicationId=0; /**< specific application id associated with this session */
	public long vendorId=0;		/**< specific vendor id associated with this session's application Id */
	
	public SessionType type=SessionType.UnknownSession;
	
	public String destinationHost=null,destinationRealm=null;
	public String originHost = null, originRealm = null;
	private Vector<SessionListener> listeners = new Vector<SessionListener>();
		
	

	protected Session(SessionManager sessionManager) 
	{
		this.sessionManager = sessionManager;
		//this.tryLock();
	}

	public AVP_Session_Id getAVP()
	{
		try {
			return new AVP_Session_Id(sessionId);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return new AVP_Session_Id("Error");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean tryLock()
	{
		
		sessionLock.lock();
		if (!active) {
			sessionLock.unlock();
			return false;
		}
		else 
			return true;
	}
	
	public void unLock()
	{
		sessionLock.unlock();
	}

	public void delete() {
		this.active = false;		
	}

	public void addEventListener(SessionListener x)
	{
		listeners.add(x);
	}

	public void removeEventListener(SessionListener x)
	{
		listeners.remove(x);
	}
	
	public void fireEvent(SessionEvent event)
	{
		for(SessionListener x:listeners)
			x.handleEvent(event, this);		
	}

	public void log() {
		LOGGER.fine(" ----- Diameter Session -----");
		LOGGER.fine("SessionId: "+this.sessionId);
		LOGGER.fine("ApplicationId: "+this.applicationId);
		LOGGER.fine("Type: "+this.type.toString());		
		LOGGER.fine(" ----------------------------");
	}

	public void getOrigin(Message msg)
	{
		AVP_Origin_Host origin_host = (AVP_Origin_Host)msg.findAVP(AVP_Type.Origin_Host);
		if (origin_host!=null) this.originHost = origin_host.get();
		AVP_Origin_Realm origin_realm = (AVP_Origin_Realm)msg.findAVP(AVP_Type.Origin_Realm);
		if (origin_realm!=null) this.originRealm = origin_realm.get();		
	}
	
	public void setDestination(Message msg)
	{
		if (this.destinationHost!=null) msg.addAVP(new AVP_Destination_Host(this.destinationHost));		
		if (this.destinationRealm!=null) msg.addAVP(new AVP_Destination_Realm(this.destinationRealm));
	}

	
}
