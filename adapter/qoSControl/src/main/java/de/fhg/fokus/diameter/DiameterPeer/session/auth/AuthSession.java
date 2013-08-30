/*
 * $Id: AuthSession.java 1971 2010-07-20 08:41:28Z dvi $
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
package de.fhg.fokus.diameter.DiameterPeer.session.auth;


import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Grace_Period;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Authorization_Lifetime;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Session_Timeout;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Specific_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message;
import de.fhg.fokus.diameter.DiameterPeer.session.Session;
import de.fhg.fokus.diameter.DiameterPeer.session.SessionManager;


public class AuthSession extends Session {


	public AuthSessionState state=AuthSessionState.Idle;		/**< current state */
	
	public long timeout=0;			/**< absolute time for session timeout  -1 means forever */
	public long lifetime=0;		/**< absolute time for auth lifetime -1 means forever */
	public long grace_period=0;	
	
	
	public AuthSession(SessionManager sessionManager) {
		super(sessionManager);
	}


	/**< grace_period in seconds 	*/ 
	
	
	public void log() {
		LOGGER.fine(" ----- Diameter Auth Session -----");
		super.log();
		LOGGER.fine("Timeout: "+this.timeout);
		LOGGER.fine("Lifetime: "+this.lifetime);
		LOGGER.fine("Grace Period: "+this.grace_period);		
		LOGGER.fine(" ---------------------------------");
	}


	
	public void updateSessionTimers(Message msg) 
	{		
		if (msg==null) return;
		synchronized(msg){
			AVP_Auth_Grace_Period grace_period = (AVP_Auth_Grace_Period) msg.findAVP(AVP_Type.Auth_Grace_Period);
			if (grace_period!=null)
				this.grace_period = grace_period.get();
			
			AVP_Authorization_Lifetime lifetime = (AVP_Authorization_Lifetime) msg.findAVP(AVP_Type.Authorization_Lifetime);
			if (lifetime!=null){
				this.lifetime = lifetime.get();
				if (this.lifetime==0) this.lifetime = System.currentTimeMillis()/1000;
				else if (this.lifetime==0xffffffffL) this.lifetime = -1;
				else this.lifetime = System.currentTimeMillis()/1000 + this.lifetime; 
				if (this.timeout!=-1 && this.timeout<this.lifetime) this.timeout = this.lifetime+this.grace_period;
			}
			
			AVP_Session_Timeout timeout = (AVP_Session_Timeout) msg.findAVP(AVP_Type.Session_Timeout);
			if (timeout!=null){	
				this.timeout = timeout.get();
				if (this.timeout==0) this.timeout = System.currentTimeMillis()/1000;
				else if (this.timeout==0xffffffff) this.timeout = -1;
				else this.timeout = System.currentTimeMillis()/1000 + this.timeout; 	
				if (this.lifetime==0) this.lifetime = this.timeout;
			}
		}
	}
	
	public void setSessionTimers(Message msg)
	{
		long x;
		if (msg==null) return;
		synchronized(msg){			
			AVP_Authorization_Lifetime lifetime = (AVP_Authorization_Lifetime) msg.findAVP(AVP_Type.Authorization_Lifetime);
			
			if (lifetime==null){				
				if (this.lifetime==-1){
					try {
						msg.addAVP(new AVP_Authorization_Lifetime(0xffffffffL));
					} catch (AVPDataTypeException e) {
						e.printStackTrace();
					}
				} else {
					x = this.lifetime - System.currentTimeMillis()/1000;
					if (x<0) x = 0;
					try {
						msg.addAVP(new AVP_Authorization_Lifetime(x));
					} catch (AVPDataTypeException e) {
						e.printStackTrace();
					}
				}
			}
			
			if (this.lifetime!=-1){
				AVP_Auth_Grace_Period grace_period = (AVP_Auth_Grace_Period) msg.findAVP(AVP_Type.Auth_Grace_Period);
				if (grace_period==null){
					try {
						msg.addAVP(new AVP_Auth_Grace_Period(this.grace_period));
					} catch (AVPDataTypeException e) {
						e.printStackTrace();
					}					
				}
			}
			
			AVP_Session_Timeout timeout = (AVP_Session_Timeout) msg.findAVP(AVP_Type.Session_Timeout);
			if (timeout==null){
				if (this.timeout==-1){
					try {
						msg.addAVP(new AVP_Session_Timeout(0xffffffffL));
					} catch (AVPDataTypeException e) {
						e.printStackTrace();
					}
				} else {
					x = this.timeout - System.currentTimeMillis()/1000;
					if (x<0) x = 0;
					try {
						msg.addAVP(new AVP_Session_Timeout(x));
					} catch (AVPDataTypeException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
	}


	public void updateApplication(Message msg) 
	{
		this.applicationId = msg.applicationId;
		if(msg.findAVPs(AVP_Type.Vendor_Specific_Application_Id).length >1){
		AVP_Vendor_Specific_Application_Id[] avps = (AVP_Vendor_Specific_Application_Id[])msg.findAVPs(AVP_Type.Vendor_Specific_Application_Id);
		AVP_Vendor_Id vendorId;
		AVP_Auth_Application_Id authAppId;
		for(AVP_Vendor_Specific_Application_Id avp: avps){
			vendorId = (AVP_Vendor_Id)avp.findChildAVP(AVP_Type.Vendor_Id);
			authAppId = (AVP_Auth_Application_Id)avp.findChildAVP(AVP_Type.Auth_Application_Id);
			if (vendorId!=null && authAppId!=null){
				this.applicationId = authAppId.get();
				this.vendorId = vendorId.get();
				return;
			}
		}	
		}else{
			AVP_Vendor_Specific_Application_Id vsaiAVP =  ( AVP_Vendor_Specific_Application_Id)msg.findAVP(AVP_Type.Vendor_Specific_Application_Id);
			AVP_Vendor_Id vendorId;
			AVP_Auth_Application_Id authAppId;
			vendorId = (AVP_Vendor_Id)vsaiAVP.findChildAVP(AVP_Type.Vendor_Id);
			authAppId = (AVP_Auth_Application_Id)vsaiAVP.findChildAVP(AVP_Type.Auth_Application_Id);
			if (vendorId!=null && authAppId!=null){
				this.applicationId = authAppId.get();
				this.vendorId = vendorId.get();
				return;
			}
		}
	}
	
	public void setApplication(Message msg) throws AVPDataTypeException
	{
		if (this.vendorId!=0){
			msg.addAVP( new AVP_Vendor_Specific_Application_Id(new AVP[]{
				new AVP_Vendor_Id(this.vendorId),
				new AVP_Auth_Application_Id(this.applicationId),
			}));
		}
		
		msg.addAVP(new AVP_Auth_Application_Id(this.applicationId));		
	}

}
