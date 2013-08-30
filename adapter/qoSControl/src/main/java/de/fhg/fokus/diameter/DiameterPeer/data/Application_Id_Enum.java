/*
 * $Id: Application_Id_Enum.java 1945 2010-07-15 14:52:42Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer.data;


public enum Application_Id_Enum {
	
	Base		(		       0,		"Diameter Base"			),
	Cx			(       16777216,		"Cx (I/S-CSCF<->HSS"	),			
	Dx			(       16777216,		"Dx (I/S-CSCF<->HSS"	),
	Sh			(       16777217,		"Sh (AS<->HSS"			),
	Ph			(       16777217,		"Sh (AS<->HSS"			),
	Gq			(		16777222,		"Gq (PCRF<->PDF)"		),
	Rf			(		16777223,		"Rf (X<->OFCS)"			),
	e2			(		16777231,		"e2 (CLF<->AF)"			),
	Rx			(		16777236,		"Rx (PCRF<->PCEF)"		),
	Gx			(		16777238,		"Gx (PCRF<->BBERF)"		),
	S6a			(		16777251,		"S6a (MME<->HSS)"		),
	S6d			(		16777251,		"S6d"					),
	Gxx			(		16777266,		"Gxx (PCRF<->BBERF)"	),
	
	Relay		(    0xffffffffL,		"Diameter Relay"		),
	;
	
	
	public final long value;
	public final String text;
	
	
	private Application_Id_Enum(long data,String text)
	{
		this.value = data;
		this.text = text;
	}
	
	public String toString()
	{
		return this.text;
	}
}
