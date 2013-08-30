/*
 * $Id: OptionTCPFlags.java 1979 2010-07-20 13:08:28Z dvi $
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
import java.util.Vector;

import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;




public class OptionTCPFlags extends Option {

	public Vector<OptionTCPFlagsSpec> mustHave = new Vector<OptionTCPFlagsSpec>();
	public Vector<OptionTCPFlagsSpec> mustNotHave = new Vector<OptionTCPFlagsSpec>();
	
	public OptionTCPFlags(String spec) throws AVPDecodeException
	{
		this.type = OptionType.TCPFLAGS;
		this.set(spec);
	}
	
	public void set(String spec) throws AVPDecodeException
	{
		StringTokenizer st = new StringTokenizer(spec, ", \t");
		String token;
		OptionTCPFlagsSpec x;
		boolean not = false;
		this.mustHave.clear();
		this.mustNotHave.clear();
		while (st.hasMoreTokens()){
			token = st.nextToken();
			if (token.equals("!")) not = true;
			else {
				x = OptionTCPFlagsSpec.getType(token);
				if (x==null) throw new AVPDecodeException("Unknown option ipoptions: "+token);
				if (not) mustNotHave.add(x);
				else mustHave.add(x);
				not = false;
			}			
		}
	}
	
	public String get()
	{
		StringBuffer stb = new StringBuffer();
		boolean first = true;
		if (mustHave.size()+mustNotHave.size()==0) return "";
		stb.append(' ');
		stb.append(this.type.toString());
		stb.append(' ');
		for(OptionTCPFlagsSpec x:mustHave){
			if (!first) stb.append(',');
			first = false;
			stb.append(x.toString());
		}
		for(OptionTCPFlagsSpec x:mustNotHave){
			if (!first) stb.append(",");
			first = false;
			stb.append('!');
			stb.append(x.toString());
		}
		return stb.toString();		
	}
	
	public String toString()
	{
		return this.get();
	}
}
