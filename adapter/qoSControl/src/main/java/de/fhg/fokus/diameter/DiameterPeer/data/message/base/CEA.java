/*
 * $Id: CEA.java 1954 2010-07-16 14:28:56Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer.data.message.base;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Host_IP_Address;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Product_Name;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Result_Code;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Result_Code_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Answer;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message_Type;

/**
 * This class defines the Diameter Message Capabilities-Exchange-Answer
 * 
 * @author Dragos Vingarzan vingarzan -at- fokus dot fraunhofer dot de
 *
 */
public class CEA extends Answer {
	
	public CEA()
	{
		super(Message_Type.Capabilities_Exchange_Answer);
			
		try {
			this.addAVP(new AVP_Result_Code(AVP_Result_Code_Enum.DIAMETER_SUCCESS.value));
		} catch (AVPDataTypeException e1) {
			e1.printStackTrace();
		}
		
		this.addAVP(new AVP_Origin_Host(""));
		
		this.addAVP(new AVP_Origin_Realm(""));
		
		try {
			this.addAVP(new AVP_Host_IP_Address(InetAddress.getLocalHost()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.addAVP(new AVP_Vendor_Id(0));
		} catch (AVPDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.addAVP(new AVP_Product_Name(""));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		/* CER allows no proxie */
		super.flagProxiable = false;
		
	}
	
	public CEA(long result_code) throws AVPDataTypeException
	{
		super(Message_Type.Capabilities_Exchange_Answer);
		
		this.addAVP(new AVP_Result_Code(result_code));
		
		this.addAVP(new AVP_Origin_Host(""));
		
		this.addAVP(new AVP_Origin_Realm(""));
		
		try {
			this.addAVP(new AVP_Host_IP_Address(InetAddress.getLocalHost()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.addAVP(new AVP_Vendor_Id(0));
		} catch (AVPDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.addAVP(new AVP_Product_Name(""));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		/* CER allows no proxie */
		super.flagProxiable = false;
	}
	
}
