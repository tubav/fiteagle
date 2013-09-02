/*
 * $Id: OptionICMPTypesType.java 1979 2010-07-20 13:08:28Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer.data.avp.derived.filterrule;

import java.util.StringTokenizer;


public enum OptionICMPTypesType {
	
	ECHO_REPLY					("echo reply",0),
	DESTINATION_UNREACHABLE		("destination unreachable",3),
	SOURCE_QUENCH				("source quench",4),
	REDIRECT					("redirect",5),
	ECHO_REQUEST				("echo request",8),
	ROUTER_ADVERTISEMENT		("router advertisement",9),
	ROUTER_SOLICITATION			("router solicitation",10),
	TIME_TO_LIVE_EXCEEDED		("time-to-live exceeded",11),
	IP_HEADER_BAD				("IP header bad",12),
	TIMESTAMP_REQUEST			("timestamp request",13),
	INFORMATION_REQUEST			("information request",15),
	INFORMATION_REPLY			("information reply",16),
	ADDRESS_MASK_REQUEST		("address mask request",17),
	ADDRESS_MASK_REPLY			("address mask reply",18),
	;
	
	public final String value;
	public final int intValue;
	
	OptionICMPTypesType(String value,int intValue){
		this.value = value;
		this.intValue = intValue;
	}
	
	public static OptionICMPTypesType getType(int x)
	{
		for(OptionICMPTypesType s:OptionICMPTypesType.values())
			if (s.value.equals(x)) return s;
		return null;		
	}
	
	public static String reduce(String x)
	{
		StringBuffer stb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(x," \t");
		boolean first = true;
		while(st.hasMoreTokens()){
			if (!first) stb.append(' ');
			first = false;
			stb.append(st.nextToken());
		}
		return stb.toString();
	}
	
	public static OptionICMPTypesType getType(String x)
	{		
		String y = OptionICMPTypesType.reduce(x);
		for(OptionICMPTypesType s:OptionICMPTypesType.values())
			if (s.value.equals(y)) return s;
		return null;
	}
	
	public String toString()
	{
		return value;
	}
}