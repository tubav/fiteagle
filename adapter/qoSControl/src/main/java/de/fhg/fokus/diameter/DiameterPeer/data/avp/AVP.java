/*
 * $Id: AVP.java 3459 2011-03-28 17:09:58Z lal $
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

package de.fhg.fokus.diameter.DiameterPeer.data.avp;

import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;



/**
 * \package de.fhg.fokus.diameter.DiameterPeer.data
 * Provides methods to create and manipulate of AVPs and Diameter messages.
 */

/**
 * This class defines the basic AVP data structure.
 * 
 * The attributes can be accessed directly.
 * <ul>
 * <li> If you know that one AVP is a grouped AVP then you can parse its content and fill 
 * the children Vector by calling the avp.ungroup() method. This will also set the is_ungrouped flag.
 * <li> To create a new AVP just call one of the constructors. Don't forget to set the data with 
 * one of the setData functions or add the child avps if grouped.
 * <li> If you have child avps the data that you set with setData will be discarded on encoding,
 * as the grouped avps have priority.
 * </ul>
 * 
 * @author Dragos Vingarzan vingarzan -at- fokus dot fraunhofer dot de
 *
 */
public class AVP {
	
	public AVP_Type type=null;

	/** The AVP code */
	public int code=0;
	
	/** The AVP Vendor Specific flag. If not set, vendor_id will not be used */
	public boolean flagVendorSpecific=false;
	
	/** The AVP Mandatory Flag */
	public boolean flagMandatory=true;
	
	/** The AVP Protected Flag indicating the need for end-to-end security */
	public boolean flagProtected=false;
	
	/** The Vendor Identifier. Should only be used when the vendor specific flag is set */
	public long vendorId=0;
		
	/** The binary data actually contained inside */
	protected byte[] avpData={};
		
	
	/**
	 * Creates a new AVP.
	 * 
	 * @param code				AVP code.
	 * @param Vendor_Specific   true, if this AVP is vendor specific.
	 * @param mandatory			true, if mandatory tag should be set.	
	 * @param Protected			true, if protected tag should be send.
	 * @param vendorId			Vendor-Id.
	 * @throws AVPDecodeException 
	 */
	protected AVP(int code,boolean mandatory,boolean Protected,long vendorId)
	{
		this.code = code;
		this.flagMandatory = mandatory;
		this.flagProtected = Protected;
		this.vendorId = vendorId;
		if (this.vendorId!=0) this.flagVendorSpecific = true;
		this.type = AVP_Type.getType(this);
	}
	
	
	/**
	 * Creates a new AVP.
	 * 
	 * @param code				AVP code.
	 * @param mandatory			true, if mandatory tag should be set.	
	 * @param vendorId			Vendor-Id
	 * @throws AVPDecodeException 
	 */
	protected AVP(int code,boolean mandatory,long vendorId)
	{
		this.code = code;
		this.flagMandatory = mandatory;
		this.vendorId = vendorId;
		if (this.vendorId!=0) this.flagVendorSpecific = true;
		this.type = AVP_Type.getType(this);
	}

	public AVP(int code,boolean mandatory,boolean Protected,long vendorId,byte[] data) throws AVPDecodeException
	{
		this.code = code;
		this.flagMandatory = mandatory;
		this.flagProtected = Protected;
		this.vendorId = vendorId;
		if (this.vendorId!=0) this.flagVendorSpecific = true;
		this.setRaw(data);
		this.type = AVP_Type.getType(this);
	}
	
	
	public AVP(int code,boolean mandatory,long vendorId,byte[] data) throws AVPDecodeException
	{
		this.code = code;
		this.flagMandatory = mandatory;
		this.vendorId = vendorId;
		if (this.vendorId!=0) this.flagVendorSpecific = true;
		this.setRaw(data);
		this.type = AVP_Type.getType(this);
	}

	public AVP(AVP_Type type,byte[] data) throws AVPDecodeException {
		this.code = type.code;
		this.flagMandatory = type.flagMandatory;
		this.vendorId = type.vendorId;
		if (this.vendorId!=0) this.flagVendorSpecific = true;
		this.type = type;
		this.setRaw(data);
	}
	
	protected AVP(AVP_Type type) {
		this.code = type.code;
		this.flagMandatory = type.flagMandatory;
		this.vendorId = type.vendorId;
		if (this.vendorId!=0) this.flagVendorSpecific = true;
		this.type = type;		
	}

	/**
	 * Sets the data to an array of bytes.
	 * 
	 * @param Data 	Date field of an AVP represented in byte[].
	 * @throws AVPDecodeException 
	 */
	public synchronized void setRaw(byte[] data) throws AVPDecodeException
	{
		if (data!=null)	this.avpData = data;
		else this.avpData = new byte[0];
	}

	/**
	 * Sets the data to an array of bytes starting from start for len bytes.
	 * 
	 * @param data	Date field of an AVP represented in byte[].
	 * @param start Start position. 
	 * @param len	Length of the array.
	 * @throws AVPDecodeException 
	 */
	public synchronized void setRaw(byte[] data,int start,int len) throws AVPDecodeException
	{
		if (data==null) this.avpData=new byte[0];
		if (len<data.length - start)
			len = data.length - start;
		this.avpData = new byte[len];
		System.arraycopy(data,start,this.avpData,0,len);		
	}
	
	/**
	 * Get byte[] value.
	 * 
	 * @return The data field of an AVP converted to byte[]. 
	 */
	public synchronized byte[] getRaw() {
		return avpData;
	}
	
	
	protected static char[] hexa={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

	/**
	 * Human readable version of the AVP for logging.
	 */
	public synchronized String toString()
	{
		int i;
		StringBuffer x = new StringBuffer();		
		x.append("AVP");
		if (this.type!=null) {
			x.append("(");x.append(type.toString());x.append(")");
		}
		x.append(": Code=");x.append(code);
		if (flagVendorSpecific) x.append(" V");
		if (flagMandatory) x.append(" M");
		if (flagProtected) x.append(" P");
		x.append(" Len=");x.append(avpData.length);
		if (flagVendorSpecific){
			x.append(" V_ID=");x.append(vendorId);
		}
		x.append(" Data=0x");
		for(i=0;i<avpData.length;i++){
			x.append(hexa[(avpData[i]>>4)&0x0F]);
			x.append(hexa[avpData[i]&0x0F]);
		}
//		x.append(" Char_Data=\"");
//		for(i=0;i<avpData.length;i++)
//			if (avpData[i]>=32&&avpData[i]<=126)
//				x.append((char)avpData[i]);
//			else 
//				x.append(".");
//		x.append("\"");
		return x.toString();
	}

	
	
	
}
