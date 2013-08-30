/*
 * $Id: RoutingEngine.java 1954 2010-07-16 14:28:56Z dvi $
 *
 * Copyright (C) 2004-2006 FhG Fokus
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

package de.fhg.fokus.diameter.DiameterPeer.routing;

import java.util.TreeMap;
import java.util.Vector;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Acct_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Specific_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message;
import de.fhg.fokus.diameter.DiameterPeer.peer.Application;
import de.fhg.fokus.diameter.DiameterPeer.peer.Peer;
import de.fhg.fokus.diameter.DiameterPeer.peer.PeerManager;
import de.fhg.fokus.diameter.DiameterPeer.peer.PeerState;

public class RoutingEngine {
	 
	Vector<RoutingEntry> defaultRoutes;
	TreeMap<String,RoutingRealm> realms;

	public RoutingEngine()
	{
		defaultRoutes = new Vector<RoutingEntry>();
		realms = new TreeMap<String, RoutingRealm>();
	}
	
	public void addDefaultRoute(String FQDN,int metric){		
		RoutingEntry re = new RoutingEntry(FQDN,metric);
		for (RoutingEntry i : defaultRoutes) 
			if (i.metric<=metric){
				defaultRoutes.add(defaultRoutes.indexOf(i), re);
				break;
			}
	}
	
	public void addRealmRoute(String realm, String FQDN, int metric)
	{
		RoutingRealm rr;
		rr = realms.get(realm);
		if (rr==null){
			rr = new RoutingRealm(realm);
			this.realms.put(realm, rr);
		}
		rr.addRoute(FQDN,metric);
	}

	public Peer getRoute(Message msg, PeerManager peerManager) {
		String destinationHost=null,destinationRealm=null;
		AVP_Destination_Host destination_host;
		AVP_Destination_Realm destination_realm;
		Peer p;
		Application application=null;
		
		destination_host = (AVP_Destination_Host) msg.findAVP(AVP_Type.Destination_Host);
		if (destination_host!=null) destinationHost = destination_host.get();
		destination_realm = (AVP_Destination_Realm) msg.findAVP(AVP_Type.Destination_Realm);		
		if (destination_realm!=null) destinationRealm = destination_realm.get();
		
		if (destinationHost!=null && destinationHost.length()>0){
			p = peerManager.getPeerByFQDN(destinationHost);
			if (p!=null && (p.state==PeerState.I_Open || p.state==PeerState.R_Open))
				return p;
		}		
		
		application = getApplication(msg);
		if (destinationRealm!=null && destinationRealm.length()>0){
			RoutingRealm rr = realms.get(destinationRealm);
			if (rr!=null){
				for (RoutingEntry i : rr.routes) {
					p = peerManager.getPeerByFQDN(i.FQDN);
					if (p!=null && 
						(p.state==PeerState.I_Open || p.state==PeerState.R_Open) &&
						(application==null || p.handlesApplication(application))
						)
						return p;					
				}
			}
		}
		for (RoutingEntry i : defaultRoutes) {
			p = peerManager.getPeerByFQDN(i.FQDN);
			if (p!=null && (p.state==PeerState.I_Open || p.state==PeerState.R_Open) &&
				(application==null || p.handlesApplication(application))
				)
				return p;					
		}
		return null;
	}

	private Application getApplication(Message msg) {
		AVP_Auth_Application_Id avp_auth;
		AVP_Acct_Application_Id avp_acct;
		AVP_Vendor_Id avp_vendor;
		AVP_Vendor_Specific_Application_Id avp_group;
		long vendorId=0,appId=0;
	
		appId = msg.applicationId;	
		
		avp_vendor = (AVP_Vendor_Id) msg.findAVP(AVP_Type.Vendor_Id);				
		if (avp_vendor!=null) vendorId = avp_vendor.get();
		else vendorId = 0;
		
		avp_auth = (AVP_Auth_Application_Id) msg.findAVP(AVP_Type.Auth_Application_Id);
		if (avp_auth!=null)
			return new Application(avp_auth.get(),vendorId,Application.Auth);
		
		avp_acct = (AVP_Acct_Application_Id) msg.findAVP(AVP_Type.Acct_Application_Id);
		if (avp_acct!=null)
			return new Application(avp_acct.get(),vendorId,Application.Acct);
				
		avp_group = (AVP_Vendor_Specific_Application_Id) msg.findAVP(AVP_Type.Vendor_Specific_Application_Id);
		if (avp_group!=null){				
			avp_vendor = (AVP_Vendor_Id) avp_group.findChildAVP(AVP_Type.Vendor_Id);
			if (avp_vendor!=null) vendorId = avp_vendor.get();
			else vendorId = 0;
		
			avp_auth = (AVP_Auth_Application_Id) avp_group.findChildAVP(AVP_Type.Auth_Application_Id);							
			if (vendorId!=0&&avp_auth!=null)											
				return new Application(avp_auth.get(),vendorId,Application.Auth);

			avp_acct = (AVP_Acct_Application_Id) avp_group.findChildAVP(AVP_Type.Acct_Application_Id);
			if (vendorId!=0&&avp_acct!=null)											
				return new Application(avp_acct.get(),vendorId,Application.Acct);
		}
		return new Application(appId,vendorId,Application.Unknown);
	}
	
	
	
	
}

class RoutingEntry {
	String FQDN;
	int metric;
	public RoutingEntry(String FQDN, int metric) {
		this.FQDN = FQDN;
		this.metric = metric;
	}
}

class RoutingRealm {
	String realm;
	Vector<RoutingEntry> routes;
	public RoutingRealm(String realm) {
		this.realm = realm;
		this.routes = new Vector<RoutingEntry>();
	}
	public void addRoute(String FQDN,int metric){		
		RoutingEntry re = new RoutingEntry(FQDN,metric);
		for (RoutingEntry i : routes) 
			if (i.metric<=metric){
				routes.add(routes.indexOf(i), re);
				break;
			}
	}
}