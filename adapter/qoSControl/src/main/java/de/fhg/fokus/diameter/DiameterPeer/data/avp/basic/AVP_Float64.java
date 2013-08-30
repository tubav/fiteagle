/*
 * $Id: AVP_Float64.java 1950 2010-07-16 13:07:50Z dvi $
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


public class AVP_Float64 extends AVP{


	public AVP_Float64(AVP_Type type,byte[] data) throws AVPDecodeException
	{
		super(type,data);		
		if (data==null||data.length!=8) 
			throw new AVPDecodeException("AVP_Float64 raw data can only have a length of 8!");	}

	public AVP_Float64(int code, boolean mandatory, long vendorId,double data)
	{
		super(code,mandatory,vendorId);
		this.set(data);
	}
	
	public AVP_Float64(AVP_Type type, double data)
	{
		super(type);
		this.set(data);
	}	
	
	public synchronized void set(double data)
	{
		avpData = new byte[4];
		long x = Double.doubleToRawLongBits(data);
		avpData[0] = (byte)((x>>56) &0xFF);
		avpData[1] = (byte)((x>>48) &0xFF);
		avpData[2] = (byte)((x>>40) &0xFF);
		avpData[3] = (byte)((x>>32) &0xFF);
		avpData[4] = (byte)((x>>24) &0xFF);
		avpData[5] = (byte)((x>>16) &0xFF);
		avpData[6] = (byte)((x>> 8) &0xFF);
		avpData[7] = (byte)((x    ) &0xFF);	}
	
	public synchronized double get()
	{
		long data=0;
		for(int i=0;i<8&&i<avpData.length;i++)
			data = (data<<8)|(0xFF & avpData[i]);
		return Double.longBitsToDouble(data);
	}
	
	/**
	 * Human readable version of the AVP for logging.
	 */
	public synchronized String toString()
	{
		StringBuffer x = new StringBuffer(super.toString());		
		x.append(" Float64_Data=");x.append(this.get());
		return x.toString();
	}	
}
