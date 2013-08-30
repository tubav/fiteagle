/*
 * $Id: AVP_RAT_Type_Enum.java 1979 2010-07-20 13:08:28Z dvi $
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
package de.fhg.fokus.diameter.DiameterPeer.data.avp.Gx;




public enum AVP_RAT_Type_Enum {

	/* 0-999 used for generic radio access technologies that can apply to different IP-CAN types and are not IP-CAN specific */
	
	WLAN						(0),
	
	/* 1000-1999 are used for 3GPP specific radio access technology types */
	
	UTRAN						(1000),
	GERAN						(1001),
	GAN							(1002),
	HSPA_EVOLUTION				(1003),
	EUTRAN						(1004),
	
	/* 2000-2999 are used for 3GPP2 specific radio access technology types */
	
	CDMA2000_1x					(2000),
	HRPD						(2001),
	UMB							(2002),

	;
	
	public final AVP_RAT_Type avp;
	public final int value;
	
	
	private AVP_RAT_Type_Enum(int data)
	{
		this.avp = new AVP_RAT_Type(data);
		this.value = data;
	}


}
