/*
 * $Id: AVP_Session_Server_Failover_Enum.java 1937 2010-07-15 12:30:50Z dvi $
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




public enum AVP_Session_Server_Failover_Enum {

	REFUSE_SERVICE						(0),
	TRY_AGAIN							(1),
	ALLOW_SERVICE						(2),	
	TRY_AGAIN_ALLOW_SERVICE				(3),
	
	
	;
	
	public final AVP_Session_Server_Failover avp;
	public final int value;
	
	
	private AVP_Session_Server_Failover_Enum(int data)
	{
		this.avp = new AVP_Session_Server_Failover(data);
		this.value = data;
	}


}
