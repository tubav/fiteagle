/*
 * $Id: OptionType.java 1979 2010-07-20 13:08:28Z dvi $
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

public enum OptionType {
	
	FRAG		("frag"),		/**<	Match if the packet is a fragment and this is not
                    					the first fragment of the datagram.  frag may not
                    					be used in conjunction with either tcpflags or
                    					TCP/UDP port specifications. */
	
	IPOPTIONS	("ipoptions"),	/**<	Match if the IP header contains the comma
                    					separated list of options specified in spec.  The
                    					supported IP options are:

                    					ssrr (strict source route), lsrr (loose source
					                    route), rr (record packet route) and ts
					                    (timestamp).  The absence of a particular option
					                    may be denoted with a '!'. */
	TCPOPTIONS	("tcpoptions"),	/**<	Match if the TCP header contains the comma
					                    separated list of options specified in spec.  The
					                    supported TCP options are:
					
					                    mss (maximum segment size), window (tcp window
					                    advertisement), sack (selective ack), ts (rfc1323
					                    timestamp) and cc (rfc1644 t/tcp connection
					                    count).  The absence of a particular option may
					                    be denoted with a '!'. */
	ESTABLISHED	("established"),/**<	TCP packets only.  Match packets that have the RST
                    					or ACK bits set. */
	SETUP		("setup"),		/**<	TCP packets only.  Match packets that have the SYN
                    					bit set but no ACK bit. */
	TCPFLAGS	("tcpflags"),	/**<	TCP packets only.  Match if the TCP header
					                    contains the comma separated list of flags
					                    specified in spec.  The supported TCP flags are:
					
					                    fin, syn, rst, psh, ack and urg.  The absence of a
					                    particular flag may be denoted with a '!'.  A rule
					                    that contains a tcpflags specification can never
					                    match a fragmented packet that has a non-zero
					                    offset.  See the frag option for details on
					                    matching fragmented packets. */
	ICMPTYPES	("icmptypes")	/**< 	ICMP packets only.  Match if the ICMP type is in
					                    the list types.  The list may be specified as any
					                    combination of ranges or individual types
					                    separated by commas.  Both the numeric values and
					                    the symbolic values listed below can be used.  The
					                    supported ICMP types are:
					
					                    echo reply (0), destination unreachable (3),
					                    source quench (4), redirect (5), echo request
					                    (8), router advertisement (9), router
					                    solicitation (10), time-to-live exceeded (11), IP
					                    header bad (12), timestamp request (13),
					                    timestamp reply (14), information request (15),
					                    information reply (16), address mask request (17)
					                    and address mask reply (18). */
	;
	
	public final String value;
	
	OptionType(String value){
		this.value = value;
	}
	
	
	public static OptionType getType(String x)
	{		
		for(OptionType s:OptionType.values())
			if (s.value.equals(x)) return s;
		return null;
	}
}
