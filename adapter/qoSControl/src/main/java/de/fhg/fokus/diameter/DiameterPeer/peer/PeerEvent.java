/*
 * $Id: PeerEvent.java 1954 2010-07-16 14:28:56Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer.peer;

public enum PeerEvent {
	
	 Start,
	 Stop,	 
	 Timeout,
	 Win_Election,
	 R_Conn_CER,
	 
	 I_Rcv_Conn_Ack,
	 I_Rcv_Conn_NAck,
	 
	 I_Rcv_CER,
	 I_Rcv_CEA,
	 R_Rcv_CER,
	 R_Rcv_CEA,
	 I_Rcv_Non_CEA,
	 
	 I_Rcv_DPR,
	 I_Rcv_DPA,
	 R_Rcv_DPR,
	 R_Rcv_DPA,
	 
	 I_Rcv_DWR,
	 I_Rcv_DWA,
	 R_Rcv_DWR,
	 R_Rcv_DWA,
	 
	 Send_Message,
	 I_Rcv_Message,
	 R_Rcv_Message,
	 
	 I_Peer_Disc,
	 R_Peer_Disc,
	 ;
}
