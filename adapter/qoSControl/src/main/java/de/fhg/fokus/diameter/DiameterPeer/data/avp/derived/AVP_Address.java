/*
 * $Id: AVP_Address.java 1950 2010-07-16 13:07:50Z dvi $
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

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;


public class AVP_Address extends AVP {

	public AVP_Address(AVP_Type type,byte[] data) throws AVPDecodeException
	{
		super(type,data);	
		if (data==null||(data.length!=6&&data.length!=18)) 
			throw new AVPDecodeException("AVP_Address raw data can only have a length of 2+4 or 2+16!");		
	}

	public AVP_Address(int code, boolean mandatory, long vendorId,InetAddress data)
	{		
		super(code,mandatory,vendorId);
		this.set(data);
	}
	
	public AVP_Address(AVP_Type type, InetAddress data) {
		super(type.code,type.flagMandatory,type.vendorId);
		this.set(data);
	}

	public synchronized void set(InetAddress data)
	{
		byte[] b,ip;
		
		ip = data.getAddress();
		b = new byte[2+ip.length];
		if (data instanceof Inet4Address){
			b[0]=0;
			b[1]=1;			
		}
		if (data instanceof Inet6Address){
			b[0]=0;
			b[1]=2;			
		}
		System.arraycopy(ip, 0, b, 2, ip.length);
		
		try {
			setRaw(b);
		} catch (AVPDecodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized InetAddress get() throws UnknownHostException	
	{
		byte[] data = this.getRaw();
		if (data.length<6) return null;
		byte[] data2 = new byte[data.length-2];
		System.arraycopy(data, 2, data2, 0, data.length-2);
		return InetAddress.getByAddress(data2);		
	}
	
	/**
	 * Human readable version of the AVP for logging.
	 */
	public synchronized String toString()
	{
		StringBuffer x = new StringBuffer(super.toString());
		try {
			x.append(" Address_Data=");x.append(this.get().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x.toString();
	}	
}
