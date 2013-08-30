/*
 * $Id: QoSFilterRule.java 1979 2010-07-20 13:08:28Z dvi $
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

/**
 * IPFilterRule filters MUST follow the format:
 * 
 * 		action dir proto from src to dst [options]
 * 
 * 
 *
 */
public class QoSFilterRule {
	
	public QoSFilterAction action=QoSFilterAction.TAG;
	public Direction direction=Direction.IN;
	public String proto="ip";

	public String srcAddr="any";
	public int srcAddrMask=-1;
	public Vector<PortRange> srcPorts = new Vector<PortRange>();
	
	public String dstAddr="any";
	public int dstAddrMask=-1;
	public Vector<PortRange> dstPorts = new Vector<PortRange>();
	
	public Vector<Option> options = new Vector<Option>();
	
	
	
	public QoSFilterRule(String qosFilterRule) throws AVPDecodeException
	{
		this.set(qosFilterRule);
	}

	public void set(String rule) throws AVPDecodeException 
	{
		this.action = QoSFilterAction.TAG;
		this.direction = Direction.IN;
		this.proto = "ip";
		this.srcAddr = "any";
		this.srcAddrMask = -1;
		this.srcPorts.clear();
		this.dstAddr = "any";
		this.dstAddrMask = -1;
		this.dstPorts.clear();
		this.options.clear();
		
		StringTokenizer st = new StringTokenizer(rule," ");
		String token;
		if (st.hasMoreTokens()){
			token = st.nextToken();
			this.action = QoSFilterAction.getType(token);
			if (this.action==null) 
				throw new AVPDecodeException("Error parsing IPFilterRule: invalid action "+token);
		}else
			throw new AVPDecodeException("Error parsing IPFilterRule: no tokens left while looking for action");
		
		if (st.hasMoreTokens()){
			token = st.nextToken();
			this.direction = Direction.getType(token);
			if (this.direction==null) 
				throw new AVPDecodeException("Error parsing IPFilterRule: invalid direction "+token);
		}else
			throw new AVPDecodeException("Error parsing IPFilterRule: no tokens left while looking for dir");
		
		if (st.hasMoreTokens()){
			token = st.nextToken();
			this.proto = token;			
		}else
			throw new AVPDecodeException("Error parsing IPFilterRule: no tokens left while looking for protocol");
		
		if (st.hasMoreTokens()){
			token = st.nextToken();
			if (!token.equals("from"))		
				throw new AVPDecodeException("Error parsing IPFilterRule: looking for token from and found "+token);
		}else
			throw new AVPDecodeException("Error parsing IPFilterRule: no tokens left while looking for token from");
		
		if (st.hasMoreTokens()){
			token = st.nextToken();
			int pos = token.indexOf("/");
			if (pos<0) srcAddr = token;
			else{
				srcAddr = token.substring(0,pos);
				srcAddrMask = Integer.valueOf(token.substring(pos+1));
			}
		}else
			throw new AVPDecodeException("Error parsing IPFilterRule: no tokens left while looking for src");
		
		if (st.hasMoreTokens()){
			token = st.nextToken();
			if (!token.equals("to")){
				StringTokenizer st2 = new StringTokenizer(token,",");
				String token2;
				int pos;
				while(st2.hasMoreTokens()){
					token2 = st2.nextToken();
					pos = token2.indexOf("-");
					if (pos<0) srcPorts.add(new PortRange(Integer.valueOf(token2)));
					else srcPorts.add(new PortRange(Integer.valueOf(token2.substring(0,pos)),Integer.valueOf(token2.substring(pos+1))));					
				}
				if (st.hasMoreTokens()){
					token = st.nextToken();
					if(!token.equals("to"))
						throw new AVPDecodeException("Error parsing IPFilterRule: looking for token to and found "+token);
				}else
					throw new AVPDecodeException("Error parsing IPFilterRule: no tokens left while looking for token to");
			}
		}else
			throw new AVPDecodeException("Error parsing IPFilterRule: no tokens left while looking for token to");
		
		if (st.hasMoreTokens()){
			token = st.nextToken();
			int pos = token.indexOf("/");
			if (pos<0) dstAddr = token;
			else{
				dstAddr = token.substring(0,pos);
				dstAddrMask = Integer.valueOf(token.substring(pos+1));
			}
		}else
			throw new AVPDecodeException("Error parsing IPFilterRule: no tokens left while looking for src");

		if (st.hasMoreTokens()){
			token = st.nextToken();

			StringTokenizer st2 = new StringTokenizer(token,",");
			String token2;
			int pos;
			while(st2.hasMoreTokens()){
				token2 = st2.nextToken();
				pos = token2.indexOf("-");
				if (pos<0) dstPorts.add(new PortRange(Integer.valueOf(token2)));
				else dstPorts.add(new PortRange(Integer.valueOf(token2.substring(0,pos)),Integer.valueOf(token2.substring(pos+1))));					
			}			
		}else
			throw new AVPDecodeException("Error parsing IPFilterRule: no tokens left while looking for dst");
		
		OptionType o;
		while(st.hasMoreTokens()){
			token = st.nextToken();
			o = OptionType.getType(token);
			if (o==null)
				throw new AVPDecodeException("Error parsing IPFilterRule: no such option "+token);				
			switch (o){
				case FRAG:
					this.options.add(new OptionFrag());
					break;
				case IPOPTIONS:
					if (!st.hasMoreTokens())
						throw new AVPDecodeException("Error parsing IPFilterRule: no tokens left while looking for ipoptions spec");
					this.options.add(new OptionIPOptions(st.nextToken()));
					break;
				case TCPOPTIONS:
					if (!st.hasMoreTokens())
						throw new AVPDecodeException("Error parsing IPFilterRule: no tokens left while looking for tcpoptions spec");
					this.options.add(new OptionTCPOptions(st.nextToken()));
					break;
				case ESTABLISHED:
					this.options.add(new OptionEstablished());
					break;
				case SETUP:
					this.options.add(new OptionSetup());
					break;
				case TCPFLAGS:
					if (!st.hasMoreTokens())
						throw new AVPDecodeException("Error parsing IPFilterRule: no tokens left while looking for tcpflags spec");
					this.options.add(new OptionTCPFlags(st.nextToken()));
					break;
				case ICMPTYPES:
					if (!st.hasMoreTokens())
						throw new AVPDecodeException("Error parsing IPFilterRule: no tokens left while looking for icmptypes types");
					this.options.add(new OptionICMPTypes(st.nextToken()));
					break;
				default:
					throw new AVPDecodeException("Error parsing IPFilterRule: Unimplemented OptionType "+o.toString());					
			}
		}
				
	}
	
	public String get()
	{
		boolean first;
		StringBuffer stb = new StringBuffer();
		stb.append(this.action.toString());
		stb.append(' ');
		stb.append(this.direction.toString());
		stb.append(' ');
		stb.append(this.proto);
		stb.append(" from ");
		stb.append(srcAddr);
		if (srcAddrMask>=0){
			stb.append('/');
			stb.append(srcAddrMask);
		}
		first = true;
		for(PortRange x:srcPorts){
			if (first) stb.append(' ');
			else stb.append(',');
			first = false;
			stb.append(x.toString());
		}
		stb.append(" to ");
		stb.append(dstAddr);
		if (dstAddrMask>=0){
			stb.append('/');
			stb.append(dstAddrMask);
		}
		first = true;
		for(PortRange x:dstPorts){
			if (first) stb.append(' ');
			else stb.append(',');
			first = false;
			stb.append(x.toString());
		}
		
		for(Option x:options){
			stb.append(' ');
			stb.append(x.toString());
		}
		return stb.toString();
	}
	
	public String toString()
	{
		return this.get();
	}
}

