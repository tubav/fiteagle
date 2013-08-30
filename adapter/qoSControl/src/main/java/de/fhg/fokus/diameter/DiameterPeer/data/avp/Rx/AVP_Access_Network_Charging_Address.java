/*
 * $Id: AVP_Access_Network_Charging_Address.java 1979 2010-07-20 13:08:28Z dvi $
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
package de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx;

import java.net.InetAddress;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.derived.AVP_Address;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;

public class AVP_Access_Network_Charging_Address extends AVP_Address {
	
	public AVP_Access_Network_Charging_Address(byte[] data) throws AVPDecodeException 
	{
		super(AVP_Type.Host_IP_Address, data);
	}	
	
	public AVP_Access_Network_Charging_Address(InetAddress data) 
	{
		super(AVP_Type.Host_IP_Address, data);
	}	
	
}
