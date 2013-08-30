/*
 * $Id: AVP_IPFilterRule.java 1979 2010-07-20 13:08:28Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer.data.avp.derived;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.derived.filterrule.IPFilterRule;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;



public class AVP_IPFilterRule extends AVP {

	private IPFilterRule ipFilterRule;
	private boolean dirtyAVPData=false;

	public AVP_IPFilterRule(AVP_Type type,byte[] data) throws AVPDecodeException
	{
		super(type,data);		
		this.ipFilterRule = new IPFilterRule(new String(data));
	}

	public AVP_IPFilterRule(int code, boolean mandatory, long vendorId,String data) throws AVPDecodeException
	{
		super(code,mandatory,vendorId);
		this.set(data);
	}

	public AVP_IPFilterRule(AVP_Type type, String data) throws AVPDecodeException {
		super(type);
		this.set(data);
	}

	public AVP_IPFilterRule(AVP_Type type, IPFilterRule data) throws AVPDecodeException {
		super(type,data.get().getBytes());		
	}

	public synchronized void setRaw(byte[] data) throws AVPDecodeException
	{
		if (data!=null)	this.avpData = data;
		else this.avpData = new byte[0];
		this.dirtyAVPData = false;
		this.ipFilterRule = new IPFilterRule(new String(data));
	}
	
	public synchronized void setRaw(byte[] data,int start,int len) throws AVPDecodeException
	{
		if (len<data.length - start)
			len = data.length - start;
		this.avpData = new byte[len];
		System.arraycopy(data,start,this.avpData,0,len);
		this.ipFilterRule = new IPFilterRule(new String(this.avpData));							
	}
	
	public synchronized byte[] getRaw() {
		if (dirtyAVPData) {
			if (ipFilterRule!=null)
				this.avpData = ipFilterRule.toString().getBytes();
			else
				this.avpData = new byte[0];
		}
		return this.avpData;
	}
	
	public synchronized void set(String data) throws AVPDecodeException
	{
		this.ipFilterRule = new IPFilterRule(data);
		this.dirtyAVPData = true; 
	}
	public synchronized void set(IPFilterRule data)
	{
		this.ipFilterRule = data;
		this.dirtyAVPData = true; 
	}
	
	public synchronized IPFilterRule get()
	{
		return this.ipFilterRule;
	}
	
	/**
	 * Human readable version of the AVP for logging.
	 */
	public synchronized String toString()
	{
		StringBuffer x = new StringBuffer(super.toString());		
		x.append(" IPFilterRule_Data=");x.append(this.ipFilterRule.toString());
		return x.toString();
	}

}
