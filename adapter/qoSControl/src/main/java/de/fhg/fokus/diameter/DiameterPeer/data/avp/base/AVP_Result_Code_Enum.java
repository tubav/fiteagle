/*
 * $Id: AVP_Result_Code_Enum.java 1937 2010-07-15 12:30:50Z dvi $
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


import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;

public enum AVP_Result_Code_Enum {

	DIAMETER_MULTI_ROUND_AUTH			(1001),
	
	DIAMETER_SUCCESS					(2001),
	DIAMETER_LIMITED_SUCCESS			(2002),
	
	DIAMETER_COMMAND_UNSUPPORTED		(3001),
	DIAMETER_UNABLE_TO_DELIVER			(3002),
	DIAMETER_REALM_NOT_SERVED			(3003),
	DIAMETER_TOO_BUSY					(3004),
	DIAMETER_LOOP_DETECTED				(3005),
	DIAMETER_REDIRECT_INDICATION		(3006),
	DIAMETER_APPLICATION_UNSUPPORTED	(3007),
	DIAMETER_INVALID_HDR_BITS			(3008),
	DIAMETER_INVALID_AVP_BITS			(3009),
	DIAMETER_UNKNOWN_PEER				(3010),
	
	DIAMETER_AUTHENTICATION_REJECTED	(4001),
	DIAMETER_OUT_OF_SPACE				(4002),
	ELECTION_LOST						(4003),
	
	DIAMETER_AVP_UNSUPPORTED			(5001),
	DIAMETER_UNKNOWN_SESSION_ID			(5002),
	DIAMETER_AUTHORIZATION_REJECTED		(5003),
	DIAMETER_INVALID_AVP_VALUE			(5004),
	DIAMETER_MISSING_AVP				(5005),
	DIAMETER_RESOURCES_EXCEEDED			(5006),
	DIAMETER_CONTRADICTING_AVPS			(5007),
	DIAMETER_AVP_NOT_ALLOWED			(5008),
	DIAMETER_AVP_OCCURS_TOO_MANY_TIMES	(5009),
	DIAMETER_NO_COMMON_APPLICATION		(5010),
	DIAMETER_UNSUPPORTED_VERSION		(5011),
	DIAMETER_UNABLE_TO_COMPLY			(5012),
	DIAMETER_INVALID_BIT_IN_HEADER		(5013),
	DIAMETER_INVALID_AVP_LENGTH			(5014),
	DIAMETER_INVALID_MESSAGE_LENGTH		(5015),
	DIAMETER_INVALID_AVP_BIT_COMBO		(5016),	
	DIAMETER_NO_COMMON_SECURITY			(5017),
	
	;
	
	public final AVP_Result_Code avp;
	public final long value;
	public final long type;
	
	public static final int Type_Informational 		= 1000;
	public static final int Type_Success 			= 2000;
	public static final int Type_Protocol_Errors 	= 3000;
	public static final int Type_Transient_Failures	= 4000;
	public static final int Type_Permanent_Failure 	= 5000;
	
	
	
	private AVP_Result_Code_Enum(long data)
	{
		try {
			this.avp = new AVP_Result_Code(data);
		} catch (AVPDataTypeException e) {			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		this.value = data;
		this.type = data/1000*1000;
	}
	
}
