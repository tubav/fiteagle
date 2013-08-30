/*
 * $Id: Answer.java 1970 2010-07-19 15:54:08Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer.data.message;


import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Experimental_Result;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Experimental_Result_Code;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Result_Code;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Id;


public class Answer extends Message {

	/** If the message is a Request*/
	public final boolean flagRequest=false;
	
	
	public Answer(int commandCode,long applicationId)
	{
		super(commandCode,false,applicationId);
	}
	
	public Answer(Message_Type type)
	{
		super(type);
		if (type.flagRequest!=false)
			throw new InstantiationError("Can not create an Answer from a request type parameter!");
	}

	public Answer(Request req)
	{		
		super(req.commandCode,false,req.flagProxiable,req.applicationId,req.hopByHopID,req.endToEndID);
		if (req.flagRequest!=true)
			throw new InstantiationError("Can not create an Answer from another Answer parameter!");
		AVP sid = req.findAVP(AVP_Type.Session_Id);
		if (sid!=null) this.addAVP(sid);
		AVP_Origin_Host oh = (AVP_Origin_Host)req.findAVP(AVP_Type.Origin_Host);
		if (oh!=null) this.addAVP(new AVP_Destination_Host(oh.get()));
		AVP_Origin_Realm or = (AVP_Origin_Realm)req.findAVP(AVP_Type.Origin_Realm);
		if (oh!=null) this.addAVP(new AVP_Destination_Realm(or.get()));
	}
	
	public void setResult_Code(long resultCode) throws AVPDataTypeException 
	{
		AVP_Result_Code x = (AVP_Result_Code) this.findAVP(AVP_Type.Result_Code);
		if (x==null) {
			x = new AVP_Result_Code(resultCode);
			this.addAVP(x);
		}else
			x.set(resultCode);
	}
	
	public long getResult_Code()
	{
		AVP_Result_Code result_code = (AVP_Result_Code)this.findAVP(AVP_Type.Result_Code);
		if (result_code==null) return 0;
		return result_code.get(); 
	}
	
	public void setExperimental_Result_Code(long vendorId,long resultCode) throws AVPDataTypeException 
	{		
		this.setAVP(new AVP_Experimental_Result(
						new AVP[]{
								new AVP_Vendor_Id(vendorId),
								new AVP_Experimental_Result_Code(resultCode),
						}));;
	}
	public long getExperimental_Result_Code()
	{
		AVP_Experimental_Result experimental_result = (AVP_Experimental_Result)this.findAVP(AVP_Type.Experimental_Result);
		if (experimental_result==null) return 0;
		AVP_Experimental_Result_Code experimental_result_code = (AVP_Experimental_Result_Code)experimental_result.findChildAVP(AVP_Type.Experimental_Result_Code);
		if (experimental_result_code==null) return 0;
		return experimental_result_code.get(); 
	}
	
	public long getExperimental_Result_Vendor_Id()
	{
		AVP_Experimental_Result experimental_result = (AVP_Experimental_Result)this.findAVP(AVP_Type.Experimental_Result);
		if (experimental_result==null) return 0;
		AVP_Vendor_Id vendor_id = (AVP_Vendor_Id)experimental_result.findChildAVP(AVP_Type.Vendor_Id);
		if (vendor_id==null) return 0;
		return vendor_id.get(); 
	}

}
