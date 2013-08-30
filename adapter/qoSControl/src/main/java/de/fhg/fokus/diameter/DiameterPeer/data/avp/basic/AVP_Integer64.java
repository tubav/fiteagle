/*
 * $Id: AVP_Integer64.java 1950 2010-07-16 13:07:50Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer.data.avp.basic;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;


public class AVP_Integer64 extends AVP{

	public AVP_Integer64(AVP_Type type,byte[] data) throws AVPDecodeException
	{
		super(type,data);		
		if (data==null||data.length!=8) 
			throw new AVPDecodeException("AVP_Integer64 raw data can only have a length of 8!");
	}

	public AVP_Integer64(int code, boolean mandatory, long vendorId,int data)
	{
		super(code,mandatory,vendorId);
		this.set(data);
	}
	
	public AVP_Integer64(AVP_Type type,int data)
	{
		super(type);
		this.set(data);
	}
	
	public synchronized void set(long data)
	{
		avpData = new byte[8];
		avpData[0] = (byte)((data>>56) &0xFF);
		avpData[1] = (byte)((data>>48) &0xFF);
		avpData[2] = (byte)((data>>40) &0xFF);
		avpData[3] = (byte)((data>>32) &0xFF);
		avpData[4] = (byte)((data>>24) &0xFF);
		avpData[5] = (byte)((data>>16) &0xFF);
		avpData[6] = (byte)((data>> 8) &0xFF);
		avpData[7] = (byte)((data    ) &0xFF);
	}
	
	public synchronized long get()
	{
		long data=0;
		for(int i=0;i<8&&i<avpData.length;i++)
			data = (data<<8)|(0xFF & avpData[i]);
		return data;
	}
	
	/**
	 * Human readable version of the AVP for logging.
	 */
	public synchronized String toString()
	{
		StringBuffer x = new StringBuffer(super.toString());
		x.append(" Integer64_Data=");x.append(this.get());
		return x.toString();
	}		
}
