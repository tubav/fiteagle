/*
 * $Id: DPR.java 1954 2010-07-16 14:28:56Z dvi $
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

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Disconnect_Cause;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Request;

/**
 * This class defines the Diameter Message Disconnect-Peer-Request
 * 
 * @author Dragos Vingarzan vingarzan -at- fokus dot fraunhofer dot de
 *
 */
public class DPR extends Request {

	
	public DPR()
	{
		super(Message_Type.Disconnect_Peer_Request);
						
		this.addAVP(new AVP_Origin_Host(""));
		
		this.addAVP(new AVP_Origin_Realm(""));
		
		this.addAVP(new AVP_Disconnect_Cause(0));				
	}
	
	public void setDisconnect_Cause(int disconnectCause)
	{
		AVP_Disconnect_Cause x = (AVP_Disconnect_Cause) this.findAVP(AVP_Type.Disconnect_Cause);
		if (x==null) {
			x = new AVP_Disconnect_Cause(disconnectCause);
			this.addAVP(x);
		}else
			x.set(disconnectCause);
	}
	
}
