/*
 * $Id: Message_Type.java 1979 2010-07-20 13:08:28Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer.data.message;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import de.fhg.fokus.diameter.DiameterPeer.data.Application_Id_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.message.base.CEA;
import de.fhg.fokus.diameter.DiameterPeer.data.message.base.CER;
import de.fhg.fokus.diameter.DiameterPeer.data.message.base.DPA;
import de.fhg.fokus.diameter.DiameterPeer.data.message.base.DPR;
import de.fhg.fokus.diameter.DiameterPeer.data.message.base.DWA;
import de.fhg.fokus.diameter.DiameterPeer.data.message.base.DWR;


public enum Message_Type {
	
	Abort_Session_Request				(	274,	true,	Application_Id_Enum.Base.value,		"ASR",	Request.class	),
	Abort_Session_Answer				(	274,	false,	Application_Id_Enum.Base.value,		"ASA",  Answer.class 	),

	Accounting_Request_Request			(	271,	true,	Application_Id_Enum.Base.value,		"ACR",	Request.class	),
	Accounting_Request_Answer			(	271,	false,	Application_Id_Enum.Base.value,		"ACA",  Answer.class 	),

	Capabilities_Exchange_Request		(	257,	true,	Application_Id_Enum.Base.value,		"CER",	CER.class		),
	Capabilities_Exchange_Answer		(	257,	false,	Application_Id_Enum.Base.value,		"CEA",  CEA.class 		),

	Device_Watchdog_Request				(	280,	true,	Application_Id_Enum.Base.value,		"DWR",	DWR.class		),
	Device_Watchdog_Answer				(	280,	false,	Application_Id_Enum.Base.value,		"DWA",  DWA.class 		),

	Disconnect_Peer_Request				(	282,	true,	Application_Id_Enum.Base.value,		"DPR",	DPR.class		),
	Disconnect_Peer_Answer				(	282,	false,	Application_Id_Enum.Base.value,		"DPA",  DPA.class	 	),

	Re_Auth_Request						(	258,	true,	Application_Id_Enum.Base.value,		"RAR",	Request.class	),
	Re_Auth_Answer						(	258,	false,	Application_Id_Enum.Base.value,		"RAA",	Answer.class	),

	Session_Termination_Request			(	258,	true,	Application_Id_Enum.Base.value,		"STR",	Request.class	),
	Session_Termination_Answer			(	258,	false,	Application_Id_Enum.Base.value,		"STA",  Answer.class 	),

	
	
	/* 3GPP TS 29.214 Policy and Charging Control over Rx reference point */
	
	Rx_Authorize_Authenticate_Request	(   265,	true,	Application_Id_Enum.Rx.value,		"Rx-AAR",Request.class	),
	Rx_Authorize_Authenticate_Answer	(   265,	false,	Application_Id_Enum.Rx.value,		"Rx-AAA",Answer.class	),
	
	Rx_Re_Auth_Request					(   258,	true,	Application_Id_Enum.Rx.value,		"Rx-RAR",Request.class	),
	Rx_Re_Auth_Answer					(   258,	false,	Application_Id_Enum.Rx.value,		"Rx-RAA",Answer.class	),

	Rx_Session_Termination_Request		(   275,	true,	Application_Id_Enum.Rx.value,		"Rx-STR",Request.class	),
	Rx_Session_Termination_Answer		(   275,	false,	Application_Id_Enum.Rx.value,		"Rx-STA",Answer.class	),
	
	Rx_Abort_Session_Request			(   274,	true,	Application_Id_Enum.Rx.value,		"Rx-ASR",Request.class	),
	Rx_Abort_Session_Answer				(   274,	false,	Application_Id_Enum.Rx.value,		"Rx-ASA",Answer.class	),

	;
	
	
	/** Command Code */
	public final int commandCode;
	
	/** If the message is a Request*/
	public final boolean flagRequest;
	
	/** Application ID */
	public final long applicationId;	
	
	/* Abbreviated symbol */
	public final String text;
	
	
	
	public final Class<?> messageClass;
	
	private final Object[] initArgsEmpty;
	private final Object[] initArgsFull;
	
		
	private Message_Type(int commandCode,boolean flagRequest,long applicationId,String text, Class<?> messageClass)
	{
		this.commandCode = commandCode;
		this.flagRequest = flagRequest;
		this.applicationId = applicationId;
		this.text = text;
		this.messageClass = messageClass;
		this.initArgsEmpty = new Object[] {};		
		this.initArgsFull = new Object[] {commandCode,applicationId};
	}	
	
	
	
	private static final Class<?>[] msgParameterTypesEmpty = {};
	private static final Class<?>[] msgParameterTypesFull = {int.class,long.class};
	 
	public Message newInstance()
	{
		Message x = null;
		if (this.messageClass==null) return x;
		Constructor<?> c;
		try {
			c = this.messageClass.getConstructor(msgParameterTypesEmpty);
			x = (Message) c.newInstance(this.initArgsEmpty);
		
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

		if(x== null){
		try {
			c = this.messageClass.getConstructor(msgParameterTypesFull);
			x = (Message) c.newInstance(this.initArgsFull);
		
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		}			
		return x;
	}
	
	public static Message newInstance(int commandCode,boolean flagRequest,long applicationId)
	{
		Message x=null;
		for(Message_Type type:Message_Type.values())
			if (type.commandCode==commandCode &&
				type.flagRequest==flagRequest &&
				type.applicationId==applicationId &&
				type.messageClass!=null){
				x = type.newInstance();
				break;
			}
		if (x==null){
			if (flagRequest) x = new Request(commandCode,applicationId);
			else x = new Answer(commandCode,applicationId);
		}
		return x;
	}
	
	
	public static Message_Type getType(Message msg)
	{		
		for(Message_Type x:Message_Type.values())
			if (x.commandCode == msg.commandCode && 
					x.flagRequest == msg.flagRequest && 
					x.applicationId == msg.applicationId)
				return x;
		return null;
	}	
	
	public String toString()
	{
		return this.text;
	}
}
