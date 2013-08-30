/*
 * $Id: AVP_Time.java 3572 2011-05-23 07:51:55Z lal $
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

import java.util.Date;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;


public class AVP_Time extends AVP {

	public static final int EPOCH_UNIX_TO_EPOCH_NTP = (int) 2208988800L;
	
	public AVP_Time(AVP_Type type,byte[] data) throws AVPDecodeException
	{
		super(type,data);		
		if (data==null||data.length!=4) 
			throw new AVPDecodeException("AVP_Time raw data can only have a length of 4!");
	}
	
	public AVP_Time(int code, boolean mandatory, long vendorId,Date data) throws AVPDecodeException, AVPDataTypeException
	{		
		super(code,mandatory,vendorId);
		this.set(data);
	}
	
	public AVP_Time(AVP_Type type, Date data) throws AVPDataTypeException {
		super(type);
		this.set(data);
	}

	public synchronized void set(Date data) throws AVPDataTypeException
	{
		long x = data.getTime()/1000+EPOCH_UNIX_TO_EPOCH_NTP;
		if (x>0xFFFFFFFFL) throw new AVPDataTypeException("AVPTime overflow! ("+data.toString()+")");
		avpData = new byte[4];
		avpData[0] = (byte)((x & 0xFF000000L)>>24);
		avpData[1] = (byte)((x & 0x00FF0000L)>>16);
		avpData[2] = (byte)((x & 0x0000FF00L)>> 8);
		avpData[3] = (byte)((x & 0x000000FFL)    );		
	}
	
	public synchronized Date get()
	{
		long data=0;
		int b0 = (0xFF & ((int)avpData[0]));
		int b1 = (0xFF & ((int)avpData[1]));
		int b2 = (0xFF & ((int)avpData[2]));
		int b3 = (0xFF & ((int)avpData[3]));
        data = ((long) (b0<<24 |
        				b1<<16 |
        				b2<<8  |
        				b3)) 
        		&0xFFFFFFFFL;
        data = (data - EPOCH_UNIX_TO_EPOCH_NTP)*1000;
		return new Date(data);
	}
	
	/**
	 * Human readable version of the AVP for logging.
	 */
	public synchronized String toString()
	{
		StringBuffer x = new StringBuffer(super.toString());		
		x.append(" Time_Data=");x.append(this.get().toString());
		return x.toString();
	}

}
