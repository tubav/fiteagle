/*
 * $Id: Message.java 2219 2010-08-17 15:26:33Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer.data.message;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.Vector;
import java.util.logging.Logger;


import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Session_State;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Host_IP_Address;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Product_Name;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Session_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;
import de.fhg.fokus.diameter.DiameterPeer.session.Session;


/**
 * This class defines the basic Diameter Message structure
 * 
 * @author Dragos Vingarzan vingarzan -at- fokus dot fraunhofer dot de
 *
 */
public class Message {
	
	/** The logger */
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Message.class.toString());

	public Message_Type type = null;
	
	/** The version of the message */
	public int version=1;
	
	/** Command Code */
	public int commandCode;
	
	/** If the message is a Request*/
	public boolean flagRequest=true;
	
	/** If the message is proxiable */
	public boolean flagProxiable=true;
	
	/** If the message contains a protocol error */
	public boolean flagError=false;
	
	/** Potentially retransmission */
	public boolean flagRetransmission=false;
	
	/** Application ID */
	public long applicationId=0;
	
	/** Hop-by-Hop identifier */
	public long hopByHopID=0;

	/** End-to-End identifier */
	public long endToEndID=0;
	
	/** Contained AVPs */
	public Vector<AVP> avps;
	
	
	/** statistical time - close to media */
	public long networkTime=0;

	
	/**
	 * Dumb constructor.
	 */
	public Message()
	{
		avps = new Vector<AVP>();
	}
	
	/**
	 * Constructor that initializes everything.
	 * @param Command_Code
	 * @param Request
	 * @param Proxiable
	 * @param Application_id
	 * @param HopByHop_id
	 * @param EndToEnd_id
	 */
	public Message(int Command_Code,boolean Request,boolean Proxiable,
			long Application_id,long HopByHop_id, long EndToEnd_id)
	{
		this.commandCode = Command_Code;
		this.flagRequest = Request;
		this.flagProxiable = Proxiable;
		this.applicationId = Application_id;
		this.hopByHopID = HopByHop_id;
		this.endToEndID = EndToEnd_id;
		avps = new Vector<AVP>();
		this.type = Message_Type.getType(this);
	}
	
	/**
	 * Constructor that initializes just the needed attributes.
	 * @param Command_Code
	 * @param Request
	 * @param Application_id
	 */
	public Message(int Command_Code,boolean Request,long Application_id)
	{
		this.commandCode = Command_Code;
		this.flagRequest = Request;
		this.flagProxiable = false;
		this.applicationId = Application_id;
		avps = new Vector<AVP>();
		this.type = Message_Type.getType(this);
	}
	
	public Message(Message_Type type)
	{
		this.commandCode = type.commandCode;
		this.flagRequest = type.flagRequest;
		this.flagProxiable = false;
		this.applicationId = type.applicationId;
		avps = new Vector<AVP>();
		this.type = type;
	}	
	
	
	
	/**
	 * Adds one AVP to the message.
	 * @param child
	 */
	public synchronized void addAVP(AVP child)
	{
		avps.add(child);
	}

	/**
	 * Adds one AVP to the message.
	 * @param child
	 */
	public synchronized void setAVP(AVP child)
	{
		this.deleteAVPs(child.code,child.vendorId);
		avps.add(child);
	}
	
	/**
	 * Returns the count of the AVPs.
	 * 
	 * @return The number of all available AVPs.
	 */
	public synchronized int getAVPCount()
	{
		return avps.size();
	}
	
	/**
	 * Returns the AVP. 
	 * @param index
	 * @return the found AVP or null if out of bounds
	 */
	public synchronized AVP getAVP(int index)
	{
		if (index<avps.size())
			return (AVP)avps.get(index);
		else return null;
	}
	
	/**
	 * Deletes the given AVP from the list of AVPs.
	 * @param avp
	 */
	public synchronized void deleteAVP(AVP avp)
	{
		avps.remove(avp);
	}

	public synchronized void deleteAVPs(int code,long vendorId)
	{
		for(AVP avp: avps)
			if (avp.code==code&&avp.vendorId==vendorId)
				avps.remove(avp);		
	}
	
	
	/**
	 * Searches for an AVP inside the Vector of AVPs.
	 * @param Code
	 * @param Mandatory
	 * @param Vendor_id
	 * @return the found AVP, null if not found
	 */
	public synchronized AVP findAVP(int Code,boolean Mandatory,long Vendor_id)
	{
		AVP avp;
		for(int i=0;i<avps.size();i++){
			avp = (AVP) avps.get(i);
			if (avp.code == Code &&
				avp.flagMandatory == Mandatory &&
				avp.vendorId == Vendor_id) 
					return avp;
		}
		return null;
	}

	/**
	 * Searches for an AVP inside the Vector of AVPs.
	 * @param Code
	 * @param Mandatory
	 * @param Vendor_id
	 * @return the found AVP, null if not found
	 */
	public synchronized AVP findAVP(int Code,long Vendor_id)
	{
		AVP avp;
		for(int i=0;i<avps.size();i++){
			avp = (AVP) avps.get(i);
			if (avp.code == Code &&				
				avp.vendorId == Vendor_id) 
					return avp;
		}
		return null;
	}
	public synchronized AVP findAVP(AVP_Type type)
	{
		return this.findAVP(type.code,type.vendorId);
	}	


	/**
	 * Searches for all AVPs with the same code inside the Vector of AVPs.
	 * @param Code
	 * @return the found AVP, null if not found
	 */
	public synchronized AVP[] findAVPs(int Code,long Vendor_id)
	{
		AVP[] avpset;
		int j = 0, count = 0;
		AVP avp;
		
		for(int i=0;i<avps.size();i++){
			avp = (AVP) avps.get(i);
			if (avp.code == Code)
				count++;
		}
		
		if (count == 0) return null;
		avpset = new AVP[count];
		for(int i=0;i<avps.size();i++){
			avp = (AVP) avps.get(i);
			if (avp.code == Code&&avp.vendorId == Vendor_id) {
				avpset[j++] = avp;
				if (j == count) break;
			}
		}
		
		return avpset;
	}
	
	public synchronized AVP[] findAVPs(AVP_Type type)
	{
		return findAVPs(type.code,type.vendorId);
	}
	
	
	/**
	 * Searches for the Session-Id AVP inside a message
	 * @return the found Session-Id AVP, null if not found
	 */
	public synchronized AVP_Session_Id getSessionId() {
		AVP x = this.findAVP(AVP_Type.Session_Id);
		if (x==null) return null;
		if (x instanceof AVP_Session_Id) return (AVP_Session_Id)x;
		
		AVP_Session_Id y=null;
		try {
			y = new AVP_Session_Id(x.getRaw());
		} catch (AVPDecodeException e) {
			e.printStackTrace();				
		}			
		return y;		
	}

	public synchronized AVP_Auth_Session_State getAuth_Session_State()
	{
		AVP x = this.findAVP(AVP_Type.Auth_Session_State);
		if (x==null) return null;
		if (x instanceof AVP_Auth_Session_State) return (AVP_Auth_Session_State)x;
		
		AVP_Auth_Session_State y=null;
		try {
			y = new AVP_Auth_Session_State(x.getRaw());
		} catch (AVPDecodeException e) {
			e.printStackTrace();				
		}			
		return y;		
	}	
	
	public void setOrigin_Host(String FQDN) {
		AVP_Origin_Host x = (AVP_Origin_Host) this.findAVP(AVP_Type.Origin_Host);
		if (x==null) {
			x = new AVP_Origin_Host(FQDN);
			this.addAVP(x);
		}
		x.set(FQDN);
		
	}

	public void setOrigin_Realm(String realm) {
		AVP_Origin_Realm x = (AVP_Origin_Realm) this.findAVP(AVP_Type.Origin_Realm);
		if (x==null) {
			x = new AVP_Origin_Realm(realm);
			this.addAVP(x);
		}else
			x.set(realm);
	}
	
	public void setDestination_Host(String FQDN) {
		AVP_Destination_Host x = (AVP_Destination_Host) this.findAVP(AVP_Type.Destination_Host);
		if (x==null) {
			x = new AVP_Destination_Host(FQDN);
			this.addAVP(x);
		}
		x.set(FQDN);
		
	}

	public void setHost_IP_Address(InetAddress addr) {
		AVP_Host_IP_Address x = (AVP_Host_IP_Address) this.findAVP(AVP_Type.Host_IP_Address);
		if (x==null) {
			x = new AVP_Host_IP_Address(addr);
			this.addAVP(x);
		}else
			x.set(addr);
	}

	public void setVendor_Id(long vendorId) throws AVPDataTypeException 
	{
		AVP_Vendor_Id x = (AVP_Vendor_Id) this.findAVP(AVP_Type.Vendor_Id);
		if (x==null) {
			x = new AVP_Vendor_Id(vendorId);
			this.addAVP(x);
		}else
			x.set(vendorId);		
	}

	public void setProduct_Name(String productName) throws UnsupportedEncodingException {
		AVP_Product_Name x = (AVP_Product_Name) this.findAVP(AVP_Type.Product_Name);
		if (x==null) {
			x = new AVP_Product_Name(productName);
			this.addAVP(x);
		}else
			x.set(productName);
	}

	public void setDestination_Realm(String realm) {
		AVP_Destination_Realm x = (AVP_Destination_Realm) this.findAVP(AVP_Type.Destination_Realm);
		if (x==null) {
			x = new AVP_Destination_Realm(realm);
			this.addAVP(x);
		}else
			x.set(realm);
	}	
	
	public void setSession_Id(String sid) throws UnsupportedEncodingException {
		AVP_Session_Id x = (AVP_Session_Id) this.findAVP(AVP_Type.Session_Id);
		if (x==null) {
			x = new AVP_Session_Id(sid);
			this.addAVP(x);
		}else
			x.set(sid);
	}	
	
	public void setSession(Session session) throws UnsupportedEncodingException
	{
		this.setSession_Id(session.sessionId);
	}

	/**
	 * Human readable version of the AVP for logging
	 */
	public synchronized String toString()
	{
		StringBuffer x = new StringBuffer();
		x.append("Diameter Message");
		if (this.type!=null) {
			x.append("(");x.append(type.toString());x.append(")");
		}
		x.append(": commandCode=");x.append(commandCode);
		if (flagRequest) x.append(" R");
		if (flagProxiable) x.append(" P");
		if (flagError) x.append(" E");
		if (flagRetransmission) x.append(" T");
		
		x.append(" AppID=");x.append(applicationId);
		x.append(" HbHID=");x.append(hopByHopID);
		x.append(" E2EID=");x.append(endToEndID);
		
		for(int i=0;i<avps.size();i++){
			x.append("\n  '--");
			x.append(avps.get(i).toString());
		}		
		return x.toString();
	}
	
	
	
}
