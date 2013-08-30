/*
 * $Id: AVP_Rx_Experimental_Result_Enum.java 1979 2010-07-20 13:08:28Z dvi $
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

import de.fhg.fokus.diameter.DiameterPeer.data.Vendor_Id_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Experimental_Result;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Experimental_Result_Code;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Id;




public enum AVP_Rx_Experimental_Result_Enum {

	INVALID_SERVICE_INFORMATION			(5061),
	FILTER_RESTRICTIONS					(5062),	
	REQUESTED_SERVICE_NOT_AUTHORIZED	(5063),
	DUPLICATED_AF_SESSION				(5064),
	IP_CAN_SESSION_NOT_AVAILABLE		(5065),
	UNAUTHORIZED_NON_EMERGENCY_SESSION	(5066),
	
	;
	
	public final AVP_Experimental_Result avp;
	public final int value;
	
	
	private AVP_Rx_Experimental_Result_Enum(int data)
	{
		AVP_Experimental_Result rc=null;
		try {
			rc = new AVP_Experimental_Result(new AVP[]{
					new AVP_Vendor_Id(Vendor_Id_Enum.TGPP.value),
					new AVP_Experimental_Result_Code(data)
			});
		} catch (AVPDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		this.avp = rc;
		this.value = data;
	}


}
