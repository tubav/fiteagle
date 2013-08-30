/*
 * $Id: AVP_DiameterURI.java 1950 2010-07-16 13:07:50Z dvi $
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
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;



public class AVP_DiameterURI extends AVP{

	public AVP_DiameterURI(AVP_Type type,byte[] data) throws AVPDecodeException
	{
		super(type,data);		
	}

	public AVP_DiameterURI(int code, boolean mandatory, long vendorId,String data)
	{
		super(code,mandatory,vendorId);
		this.set(data);
	}
	
	public AVP_DiameterURI(AVP_Type type, String data) {
		super(type);
		this.set(data);
	}

	public synchronized void set(String data)
	{
		try {
			setRaw(data.getBytes());
		} catch (AVPDecodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public synchronized String get()
	{
		return new String(this.getRaw());
	}
	/**
	 * Human readable version of the AVP for logging.
	 */
	public synchronized String toString()
	{
		StringBuffer x = new StringBuffer(super.toString());		
		x.append(" DiameterURI_Data=");x.append(this.get());
		return x.toString();
	}

}
