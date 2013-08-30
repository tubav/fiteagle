/*
 * $Id: AVP_Framed_IPv6_Prefix.java 1979 2010-07-20 13:08:28Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer.data.avp.NAS;

import java.net.Inet6Address;
import java.net.UnknownHostException;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;

public class AVP_Framed_IPv6_Prefix extends AVP {

	public static final int ASSIGNED	= 0xfffffffe;
	public static final int NEGOTIATED	= 0xffffffff;
	
	public AVP_Framed_IPv6_Prefix(byte[] data) throws AVPDecodeException
	{
		super(AVP_Type.Framed_IPv6_Prefix,data);
		if (data==null||data.length!=18) 
			throw new AVPDecodeException("AVP_Framed_IPv6_Prefix raw data can only have a length of 18!");
	}
	
	public AVP_Framed_IPv6_Prefix(Inet6Prefix data) throws AVPDecodeException {
		super(AVP_Type.Framed_IPv6_Prefix,new byte[0]);
		this.set(data);		
	}

	public synchronized void set(Inet6Prefix data)
	{		
		byte[] b = new byte[18];
		byte[] addr = data.addr.getAddress();
		b[0]= (byte)(data.reservedPrefixLength&0xFF);
		b[1]= (byte)(data.prefix&0xFF);
		System.arraycopy(addr, 0, b,2,addr.length);
		try {
			this.setRaw(b);
		} catch (AVPDecodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized Inet6Prefix get() throws UnknownHostException	
	{		
		byte[] data = this.getRaw();
		if (data.length!=18) return null;
		int reservedPrefixLength = (int)data[0];
		int prefix = (int)data[1];
		
		byte[] b = new byte[16];
		System.arraycopy(data, 2, b, 0, 16);
		Inet6Address addr = (Inet6Address) Inet6Address.getByAddress(b);
		return  new Inet6Prefix(addr, reservedPrefixLength, prefix);			
	}
	
	/**
	 * Human readable version of the AVP for logging.
	 */
	public synchronized String toString()
	{
		StringBuffer x = new StringBuffer(super.toString());
		try {
			x.append(" Framed_IP_Address=");x.append(this.get().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x.toString();
	}	

}
