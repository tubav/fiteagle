/*
 * $Id: AVP_Vendor_Id_Enum.java 1932 2010-07-15 06:53:24Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer.data.avp.base;

import de.fhg.fokus.diameter.DiameterPeer.data.Vendor_Id_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;

public enum AVP_Vendor_Id_Enum {
	
	None		(Vendor_Id_Enum.None),
	CableLabs	(Vendor_Id_Enum.CableLabs),
	TGPP		(Vendor_Id_Enum.TGPP),
	ETSI		(Vendor_Id_Enum.ETSI),
	;
	
	
	public final AVP_Vendor_Id avp;
	public final long value;
	public final String text; 
	
	
	private AVP_Vendor_Id_Enum(long data,String text)
	{
		try {
			this.avp = new AVP_Vendor_Id(data);
		} catch (AVPDataTypeException e) {			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		this.value = data;
		this.text = text;
	}

	private AVP_Vendor_Id_Enum(Vendor_Id_Enum vendor_id)
	{
		try {
			this.avp = new AVP_Vendor_Id(vendor_id.value);
		} catch (AVPDataTypeException e) {			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		this.value = vendor_id.value;
		this.text = vendor_id.text;
	}
	
	public String toString()
	{
		return this.text;
	}
		
}
