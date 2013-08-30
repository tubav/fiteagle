/*
 * $Id: AVP_Grouped.java 1950 2010-07-16 13:07:50Z dvi $
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

import java.util.Vector;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.Codec;

public class AVP_Grouped extends AVP{

	private Vector<AVP> children=null;
	
	private boolean dirtyAVPData=false;
	
	public AVP_Grouped(int code, boolean mandatory, long vendorId,byte[] data) throws AVPDecodeException
	{
		super(code,mandatory,vendorId);
		this.setRaw(data);
	}	
	
	public AVP_Grouped(int code, boolean mandatory, long vendorId,Vector<AVP> data)
	{
		super(code,mandatory,vendorId);
		this.set(data);
	}


	public AVP_Grouped(int code, boolean mandatory, long vendorId,AVP[] data)
	{
		super(code,mandatory,vendorId);
		this.set(data);
	}

	public AVP_Grouped(AVP_Type type,byte[] data) throws AVPDecodeException
	{
		super(type);
		this.setRaw(data);
	}	

	public AVP_Grouped(AVP_Type type,Vector<AVP> data)
	{
		super(type);
		this.set(data);
	}

	public AVP_Grouped(AVP_Type type,AVP[] data)
	{
		super(type);
		this.set(data);
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
		this.dirtyAVPData = false;
		this.ungroup();
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
		if (len<data.length - start)
			len = data.length - start;
		this.avpData = new byte[len];
		System.arraycopy(data,start,this.avpData,0,len);			
		this.ungroup();
	}
	
	/**
	 * Get byte[] value.
	 * 
	 * @return The data field of an AVP converted to byte[]. 
	 */
	public synchronized byte[] getRaw() {
		if (dirtyAVPData) this.group();
		return avpData;
	}
	
	
	public synchronized void set(Vector<AVP> data)
	{
		this.children = new Vector<AVP>(data);
		this.dirtyAVPData = true;
	}

	private void set(AVP[] data) 
	{
		this.children = new Vector<AVP>();
		if (data!=null) 
			for(AVP x:data)
				this.children.add(x);
		this.dirtyAVPData = true;
	}
	
	public synchronized Vector<AVP> get()
	{
		return new Vector<AVP>(children);		
	}
	
	/**
	 * Adds one child avp to the group
	 * 
	 * @param child Child AVP added to this AVPGrouped.
	 */
	public synchronized void addChildAVP(AVP child)
	{		
		if (children == null) children = new Vector<AVP>();
		children.add(child);
		this.dirtyAVPData = true;
	}
	
	/**
	 * Adds one AVP to the message.
	 * @param child
	 */
	public synchronized void setChildAVP(AVP child)
	{
		this.deleteAVPs(child.code,child.vendorId);
		children.add(child);
	}
	
	public synchronized void deleteAVPs(int code,long vendorId)
	{
		for(AVP avp: children)
			if (avp.code==code&&avp.vendorId==vendorId)
				children.remove(avp);		
	}
	/**
	 * Returns the count of the child AVPs if the AVP is a grouped one, or 0 if not.
	 * 
	 * @return the count of child avps, 0 if not.
	 */
	public synchronized int getChildrenCount()
	{
		if (children==null) return 0;		
		return children.size();
	}
	
	/**
	 * Returns the child AVP at index if the AVP is a grouped one.
	 * 
	 * @param index	The position of the child AVP.
	 * @return the found AVP or null if out of bounds.
	 */
	public synchronized AVP getChildAVP(int index)
	{
		if (children==null) return null;
		if (index<children.size())
			return (AVP)children.get(index);
		else return null;
	}
	
	/**
	 * Deletes the given AVP from the list of child avps.
	 * 
	 * @param avp The Child AVP which should be deleted. 
	 */
	public synchronized void deleteChildAVP(AVP avp)
	{
		if (children==null) return;
		children.remove(avp);
		this.dirtyAVPData = true;
	}
	
	
	/**
	 * Searches for an AVP inside the Vector of child avps if the AVP is a grouped one.
	 * 
	 * @param code		AVP code.
	 * @param Mandatory	true, if the AVP is set with Mandatory tag.
	 * @param vendorId Vendor-Id of the AVP.
	 * @return the found AVP, null if not found.
	 */
	public synchronized AVP findChildAVP(int code,long vendorId)
	{
		if (children==null) return null;
		for(AVP avp:children)
			if (avp.code == code &&	avp.vendorId == vendorId) 
					return avp;
		return null;
	}

	public synchronized AVP findChildAVP(AVP_Type type)
	{
		return findChildAVP(type.code,type.vendorId);
	}
	
	/**
	 * Searches for all AVPs with the same code inside the Vector of child avps 
	 * if the AVP is a grouped one.
	 * 
	 * @param code		AVP code.
	 * @return the found AVPs
	 */
	public synchronized Vector<AVP> findChildrenAVPs(int code,long vendorId)
	{		
		Vector<AVP> avps = new Vector<AVP>();
		if (children==null) return avps;		
		for(AVP avp:children)
			if (avp.code == code&&avp.vendorId == vendorId)
					avps.add(avp);
		return avps;
	}
	
	public synchronized Vector<AVP> findChildrenAVPs(AVP_Type type)
	{		
		return findChildrenAVPs(type.code,type.vendorId);
	}
	
	/**
	 *	Un-groups the AVP data into children Vector<AVP>
	 *
	 *	@throws AVPDecodeException if un-grouping fails.
	 */
	public synchronized void ungroup() throws AVPDecodeException
	{
		this.dirtyAVPData = false;
		int i = 0;
		AVP x;
		children = new Vector<AVP>();
		while (i<avpData.length){
			x = Codec.decodeAVP(avpData,i);
			children.add(x);
			i+=Codec.getEncodedAVPSize(x);
		}
		
	}
	
	/**
	 * Groups the child AVPs into the data field.
	 *
	 */
	public synchronized void group() 
	{
		Vector<byte[]> temp;
		int i,len=0;
		byte[] t;
		if (children==null||children.size()==0) {
			avpData = new byte[0];
		}
		temp = new Vector<byte[]>();
		for(AVP avp: children){
			t = Codec.encodeAVP(avp);
			temp.add(t);
			len += t.length;
		}
		avpData = new byte[len];
		len = 0;
		for(i=0;i<temp.size();i++){
			t = (byte[]) temp.get(i);
			System.arraycopy(t,0,avpData,len,t.length);
			len += t.length;
		}
		this.dirtyAVPData = false;
	}
	
	/**
	 * Human readable version of the AVP for logging.
	 */
	public synchronized String toString()
	{
		if (this.dirtyAVPData) this.group();
		StringBuffer x = new StringBuffer(super.toString());
		for(AVP avp:children) {
			x.append("\n '--");x.append(avp.toString());
		}
		return x.toString();
	}
}
