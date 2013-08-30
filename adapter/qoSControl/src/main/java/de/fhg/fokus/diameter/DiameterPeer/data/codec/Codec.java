/*
 * $Id: Codec.java 1950 2010-07-16 13:07:50Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer.data.codec;

import java.util.Arrays;
import java.util.Vector;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message_Type;



/**
 * This class implements the Coding/Decoding of Diameter AVPs and Messages from/to network
 * data.
 * 
 * It is implemented as conforming to RFC 3588
 * 
 *  * The attributes can be accessed directly.
 * <ul>
 * <li> An AVP can be created most simply by parsing an array of bytes starting at at position
 * and decoding it from the network format using the AVP decode(buffer,start) method.
 * <li> To convert one AVP to the network format just call the byte[] encode(AVP) method
 * <li> To compute the network representation of an AVP just call the avp.group() method. This will 
 * refresh the data attribute, but will not reset the is_ungrouped flag as the childs Vector is 
 * still filled with data. You don't need to call this before an encode as it is automatically
 * called if the is_ungrouped flag is set.
 * </ul>

 * 
 * @author Dragos Vingarzan vingarzan -at- fokus dot fraunhofer dot de
 *
 */
public class Codec {

	
	
	/**
	 * Creates an AVP object by decoding data from a byte array as represented on network
	 * 
	 * @param from the byte array
	 * @param start from where to start decoding in the byte array
	 * @return the newly created AVP object
	 * @throws AVPDecodeException
	 */
	public static AVP decodeAVP(byte[] from,int start) throws AVPDecodeException
	{
		byte[] toData=null;
		int code,vendorId;
		boolean flagVendorSpecific,flagMandatory;
		//boolean flagProtected;
		int j,len;
		
		if (from.length-start<8) {
			int tmp = 0;
			//throw new AVPDecodeException("Data is shorter than AVP Header");
			return null;
		}
		
		code =	((int)from[start+0]&0xFF)<<24 |
					((int)from[start+1]&0xFF)<<16 |
					((int)from[start+2]&0xFF)<< 8 |
					((int)from[start+3]&0xFF);
		
		flagVendorSpecific = 	(from[start+4] & 0x80)!=0;
		flagMandatory = 		(from[start+4] & 0x40)!=0;
		//flagProtected = 		(from[start+4] & 0x20)!=0;
		len = 	((int)from[start+5]&0xFF)<<16 |
				((int)from[start+6]&0xFF)<< 8 |
				((int)from[start+7]&0xFF);

		if (flagVendorSpecific){
			vendorId = 	((int)from[start+ 8]&0xFF)<<24 |
							((int)from[start+ 9]&0xFF)<<16 |
							((int)from[start+10]&0xFF)<< 8 |
							((int)from[start+11]&0xFF);
			j = 12;
		} else {
			vendorId = 0;
			j = 8;
		}

		if (len>from.length-start) len = from.length-start;
		toData = new byte[len-j];
		System.arraycopy(from,start+j,toData,0,len-j);
		
		return AVP_Type.newInstance(code, flagMandatory, vendorId, toData);
	}

	/**
	 * Encodes an AVP object into its network representation as a byte array
	 * @param from the AVP object to encode
	 * @return the byte array in network ready format
	 */
	public static byte[] encodeAVP(AVP from)
	{
		byte[] fromData = from.getRaw();
		byte[] to = null;
		int total_len,send_len;
		int j;
		
		total_len=4+4+fromData.length;
		
		if (from.flagVendorSpecific) total_len+=4;
		send_len = total_len;
		if (total_len%4!=0) 
			total_len = (total_len/4+1)*4; 
		to = new byte[total_len];
		Arrays.fill(to,(byte)0);
		
		to[0] = (byte) ((from.code >> 24) & 0xFF);
		to[1] = (byte) ((from.code >> 16) & 0xFF);
		to[2] = (byte) ((from.code >>  8) & 0xFF);
		to[3] = (byte) ( from.code        & 0xFF);
		
		if (from.flagVendorSpecific) to[4] |= 0x80;
		if (from.flagMandatory) to[4] |= 0x40;
		if (from.flagProtected) to[4] |= 0x20;
		to[5] = (byte) ((send_len >> 16) & 0xFF);
		to[6] = (byte) ((send_len >>  8) & 0xFF);
		to[7] = (byte) ( send_len        & 0xFF);
		
		if (from.flagVendorSpecific){
			to[ 8] = (byte) ((from.vendorId >> 24) & 0xFF);
			to[ 9] = (byte) ((from.vendorId >> 16) & 0xFF);
			to[10] = (byte) ((from.vendorId >>  8) & 0xFF);
			to[11] = (byte) ( from.vendorId        & 0xFF);
			j = 12;
		}
		else j = 8;
		
		if (fromData.length!=0)
			System.arraycopy(fromData,0,to,j,fromData.length);
	
		return to;
	}
	
	/** 
	 * Calculates the needed space for an encoded AVP
	 * 
	 * @param avp
	 * @return The size of the AVP.
	 */
	public static int getEncodedAVPSize(AVP avp)
	{
		int len=0;
		byte[] avpData = avp.getRaw();
		
		len=4+4+avpData.length;
		
		if (avp.flagVendorSpecific) len+=4;		
		if (len%4!=0) 
			len = ((len/4)+1)*4;
		return len;
	}
	
	/**
	 * Creates an DiameterMessage object by decoding data from a byte array as represented on network
	 * 
	 * @param from the byte array
	 * @param start from where to start decoding in the byte array
	 * @return the newly created DiameterMessage object
	 * @throws MessageDecodeException
	 */	
	public static Message decodeMessage(byte[] from,int start) throws MessageDecodeException
	{
		Message to;
		int i,len;
		AVP avp;
		
		if (from.length-start<20) throw new MessageDecodeException("Data is shorter than Diameter Message Header");
		
		int version = ((int)from[start+0]&0xFF);
		if (version!=1) throw new MessageDecodeException("Unknown Diameter Message Version "+version);
		
		len = 	((int)from[start+ 1]&0xFF)<<16 |
				((int)from[start+ 2]&0xFF)<< 8 |
				((int)from[start+ 3]&0xFF);
		
		if (len>from.length-start) len=from.length-start;
		
		boolean flagRequest = 			(from[start+4] & 0x80)!=0;
		boolean flagProxiable = 		(from[start+4] & 0x40)!=0;
		boolean flagError = 			(from[start+4] & 0x20)!=0;
		boolean flagRetransmission = 	(from[start+4] & 0x10)!=0;

		int commandCode =	((int)from[start+ 5]&0xFF)<<16 |
							((int)from[start+ 6]&0xFF)<< 8 |
							((int)from[start+ 7]&0xFF);
		
		long applicationID =	((int)from[start+ 8]&0xFF)<<24 |
							((int)from[start+ 9]&0xFF)<<16 |
							((int)from[start+10]&0xFF)<< 8 |
							((int)from[start+11]&0xFF);

		long hopByHopID =	((int)from[start+12]&0xFF)<<24 |
							((int)from[start+13]&0xFF)<<16 |
							((int)from[start+14]&0xFF)<< 8 |
							((int)from[start+15]&0xFF);

		long endToEndID =	((int)from[start+16]&0xFF)<<24 |
							((int)from[start+17]&0xFF)<<16 |
							((int)from[start+18]&0xFF)<< 8 |
							((int)from[start+19]&0xFF);

		to = Message_Type.newInstance(commandCode, flagRequest, applicationID);
		if (to==null) return null;
		to.version = version;
		to.flagProxiable = flagProxiable;
		to.flagError = flagError;
		to.flagRetransmission = flagRetransmission;
		to.hopByHopID = hopByHopID;
		to.endToEndID = endToEndID;
		i = start+20;
		while (i<start+len){
			try {
				avp = Codec.decodeAVP(from,i);
				if (avp==null){
					break;
				}
			} catch (AVPDecodeException e) {
				e.printStackTrace();
				throw new MessageDecodeException(e.getMessage());
			}
			to.avps.add(avp);
			i+=Codec.getEncodedAVPSize(avp);
		}		
		return to;
	}
	
	/**
	 * Encodes an DiameterMessage object into its network represantation as a byte array
	 * @param from the Diameter object to encode
	 * @return the byte array in network ready format
	 */
	public static byte[] encodeMessage(Message from)
	{
		byte[] to = null;
		int total_len;
		int i,len=0;
		Vector<byte[]> temp;
		byte[] t;
		
		temp = new Vector<byte[]>();
		for(i=0;i<from.avps.size();i++){
			t = Codec.encodeAVP((AVP)from.avps.get(i));
			temp.add(t);
			len += t.length;
		}
		total_len=20+len;
		
		to = new byte[total_len];
		Arrays.fill(to,(byte)0);

		to[0] = (byte) ( from.version     & 0xFF);
		to[1] = (byte) ((total_len >> 16) & 0xFF);
		to[2] = (byte) ((total_len >>  8) & 0xFF);
		to[3] = (byte) ( total_len        & 0xFF);
		
		if (from.flagRequest) 			to[4] |= 0x80;
		if (from.flagProxiable) 		to[4] |= 0x40;
		if (from.flagError) 			to[4] |= 0x20;
		if (from.flagRetransmission)	to[4] |= 0x10;
		to[5] = (byte) ((from.commandCode >> 16) & 0xFF);
		to[6] = (byte) ((from.commandCode >>  8) & 0xFF);
		to[7] = (byte) ( from.commandCode        & 0xFF);
		
		to[ 8] = (byte) ((from.applicationId >> 24) & 0xFF);
		to[ 9] = (byte) ((from.applicationId >> 16) & 0xFF);
		to[10] = (byte) ((from.applicationId >>  8) & 0xFF);
		to[11] = (byte) ( from.applicationId        & 0xFF);		

		to[12] = (byte) ((from.hopByHopID >> 24) & 0xFF);
		to[13] = (byte) ((from.hopByHopID >> 16) & 0xFF);
		to[14] = (byte) ((from.hopByHopID >>  8) & 0xFF);
		to[15] = (byte) ( from.hopByHopID        & 0xFF);		

		to[16] = (byte) ((from.endToEndID >> 24) & 0xFF);
		to[17] = (byte) ((from.endToEndID >> 16) & 0xFF);
		to[18] = (byte) ((from.endToEndID >>  8) & 0xFF);
		to[19] = (byte) ( from.endToEndID        & 0xFF);		

		len = 20;
		for(i=0;i<temp.size();i++){
			t = temp.get(i);
			System.arraycopy(t,0,to,len,t.length);
			len += t.length;
		}		
	
		return to;
	}

}
