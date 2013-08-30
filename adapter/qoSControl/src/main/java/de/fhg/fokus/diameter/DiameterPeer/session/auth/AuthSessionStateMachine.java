/*
 * $Id: AuthSessionStateMachine.java 1971 2010-07-20 08:41:28Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer.session.auth;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;


import de.fhg.fokus.diameter.DiameterPeer.DiameterPeer;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Session_State;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Session_State_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Result_Code_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Termination_Cause;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Termination_Cause_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Answer;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Request;
import de.fhg.fokus.diameter.DiameterPeer.session.SessionEvent;


public class AuthSessionStateMachine {

	private static final Logger LOGGER = Logger.getLogger(AuthSessionStateMachine.class.toString());

	public static void process(AuthSession session,
			AuthSessionEventType eventType,Message msg,DiameterPeer diameterPeer) {
		switch (session.type){
			case AuthorizationClientStateful:
				processClientStateful(session,eventType,msg,diameterPeer);
				break;
			case AuthorizationClientStateless:
				processClientStateless(session,eventType,msg);
				break;
			case AuthorizationServerStateful:
				processServerStateful(session,eventType,msg);
				break;
			case AuthorizationServerStateless:
				processServerStateless(session,eventType,msg);
				break;				
		}		
	}
	
	private static void processClientStateless(AuthSession session,
			AuthSessionEventType eventType,Message msg) 
	{
		long resultCode=0;
		LOGGER.warning("Auth State Machine: "+session.state+" Event:"+eventType+" Msg:"+(msg==null?"null":msg.toString()));
	
		switch(session.state){
			case Idle:
				switch(eventType){
					case SendRequest:
						session.state = AuthSessionState.Pending;
						break;
					default:
						LOGGER.severe(" Receive invalid event: "+eventType+" while in state: "+session.state);
				}
				break;
			case Pending:
				if (msg.flagRequest==false){
					resultCode = ((Answer)msg).getResult_Code();
					if (resultCode>=2000 &&resultCode<3000)
						eventType = AuthSessionEventType.ReceiveAnswerSuccess;
					else
						eventType = AuthSessionEventType.ReceiveAnswerUnsuccess;
				}
				switch(eventType){
					case ReceiveAnswerSuccess:
						session.state = AuthSessionState.Open;						
						break;
					case ReceiveAnswerUnsuccess:
						Cleanup(session);
						session.state = AuthSessionState.Idle;
						break;
					default:
						LOGGER.severe(" Receive invalid event: "+eventType+" while in state: "+session.state);
				}
				break;
			case Open:
				switch(eventType){
					case SessionTimeout:
						session.state = AuthSessionState.Idle;
						break;
					case ServiceTerminated:
						session.state = AuthSessionState.Idle;
					default:
						LOGGER.severe(" Receive invalid event: "+eventType+" while in state: "+session.state);
				}
				break;
			default:
				LOGGER.severe(" Invalid session state: "+session.state);
		}		
	}

	private static void processServerStateless(AuthSession session,
			AuthSessionEventType eventType,Message msg) 	
	{
		LOGGER.warning("Auth State Machine: "+session.state+" Event:"+eventType+" Msg:"+(msg==null?"null":msg.toString()));
		
		/* empty - no state change, anyway */
		
	}

	private static void processClientStateful(AuthSession session,
			AuthSessionEventType eventType,Message msg,DiameterPeer diameterPeer) 
	{
		LOGGER.warning("Auth State Machine: "+session.state+" Event:"+eventType+" Msg:"+(msg==null?"null":msg.toString()));
		
		if (session==null){
			switch(eventType){
				case ReceiveASR:
					Send_ASA(diameterPeer, msg);
					break;
				default:
					LOGGER.severe(" Receive invalid event: "+eventType+" with no session!");
			}
			return;
		}
		
		session.fireEvent(new SessionEvent(eventType,msg));
		
		switch(session.state){
			case Idle:
				switch(eventType){
					case SendRequest:
						session.updateApplication(msg);
						session.state = AuthSessionState.Pending;
						session.updateSessionTimers(msg);
						session.setSessionTimers(msg);
						break;
					default:
						LOGGER.severe(" Receive invalid event: "+eventType+" while in state: "+session.state);
				}
				break;
				
			case Pending:
				if (msg.flagRequest==false){
					long resultCode = ((Answer)msg).getResult_Code();
					AVP_Auth_Session_State sessionState = msg.getAuth_Session_State();
					//if sessionState avp not present, assume yes as default
					if (resultCode>=2000 &&resultCode<3000 && (sessionState==null || sessionState.get()==AVP_Auth_Session_State_Enum.STATE_MAINTAINED.value))
						eventType = AuthSessionEventType.ReceiveAnswerSuccess;
					else
						eventType = AuthSessionEventType.ReceiveAnswerUnsuccess;
				}
				switch(eventType){
					case ReceiveAnswerSuccess:
						session.state = AuthSessionState.Open;
						session.updateSessionTimers(msg);
						break;
					case ReceiveAnswerUnsuccess:						
						session.state = AuthSessionState.Discon;
						Send_STR(session);
						break;
					case SessionTimeout:
					case ServiceTerminated:
					case SessionGraceTimeout:
						Cleanup(session);
						session=null;
					default:
						LOGGER.severe(" Receive invalid event: "+eventType+" while in state: "+session.state);
				}
				break;
				
			case Open:
				if (msg.flagRequest==false){
					long resultCode = ((Answer)msg).getResult_Code();
					AVP_Auth_Session_State sessionState = msg.getAuth_Session_State();
					//if sessionState avp not present, assume yes as default
					if (resultCode>=2000 &&resultCode<3000 &&( 
							sessionState==null || sessionState.get()==AVP_Auth_Session_State_Enum.STATE_MAINTAINED.value))
						eventType = AuthSessionEventType.ReceiveAnswerSuccess;
					else
						eventType = AuthSessionEventType.ReceiveAnswerUnsuccess;
				}
				
				switch(eventType){
					case SendRequest:
						if (msg.commandCode == Message_Type.Session_Termination_Request.commandCode){
							session.state = AuthSessionState.Discon;
						}else{
							session.state = AuthSessionState.Open;
							session.setSessionTimers(msg);
						}
						break;
					case ReceiveAnswerSuccess:
						session.state = AuthSessionState.Open;
						session.updateSessionTimers(msg);
						break;
					case ReceiveAnswerUnsuccess:
						session.state = AuthSessionState.Discon;
						break;
					case SessionTimeout:
					case ServiceTerminated:
					case SessionGraceTimeout:
						session.state = AuthSessionState.Discon;
						Send_STR(session);
						break;
					case SendASASuccess:
						session.state = AuthSessionState.Discon;
						Send_STR(session);
						break;
					case SendASAUnsuccess:
						session.state = AuthSessionState.Open;
						session.updateSessionTimers(msg);
						break;						
					case ReceiveASR:
						session.state = AuthSessionState.Discon;
						Send_ASA(session, msg);
						Send_STR(session);
						break;
					default:
						LOGGER.severe(" Receive invalid event: "+eventType+" while in state: "+session.state);
				}
				break;
				
			case Discon:
				switch(eventType){
					case ReceiveASR:
						session.state = AuthSessionState.Discon;
						Send_ASA(session,msg);
						break;
					case SessionTimeout:
					case SessionGraceTimeout:
					case ReceiveSTA:
						session.state = AuthSessionState.Idle;
						Cleanup(session);
						session=null;
						break;
					default:
						LOGGER.severe(" Receive invalid event: "+eventType+" while in state: "+session.state);						
				}
				break;
			
			default:
				LOGGER.severe(" Invalid session state: "+session.state);
		}
		
		if (session!=null)
			session.fireEvent(new SessionEvent(AuthSessionEventType.SessionModified));		
	}

	private static void processServerStateful(AuthSession session,
			AuthSessionEventType eventType,Message msg) 
	{
		LOGGER.warning("Auth State Machine: "+session.state+" Event:"+eventType+" Msg:"+(msg==null?"null":msg.toString()));
		
		session.fireEvent(new SessionEvent(eventType,msg));

		switch(session.state){
			case Idle:
				switch(eventType){
					case ReceiveSTR:
						break;
					case ReceiveRequest:
						session.state = AuthSessionState.Open;
						session.fireEvent(new SessionEvent(AuthSessionEventType.SessionModified));
						session = null;
						break;
					case SendSTA:
						session.state = AuthSessionState.Idle;
						Cleanup(session);
						session=null;
						break;
						
					case SessionTimeout:
					case SessionGraceTimeout:
						Cleanup(session);
						session=null;
						break;
					default:
						LOGGER.severe(" Receive invalid event: "+eventType+" while in state: "+session.state);	
				}
				break;
				
			case Open:
				if (msg!=null && eventType==AuthSessionEventType.SendAnswer && msg.flagRequest==false){
					long resultCode = ((Answer)msg).getResult_Code();					
					if (resultCode>=2000 &&resultCode<3000 )
						eventType = AuthSessionEventType.SendAnswerSuccess;
					else
						eventType = AuthSessionEventType.SendAnswerUnsuccess;
				}
				switch(eventType){
					case ReceiveSTR:
						break;
					case SendAnswerSuccess:
						session.state = AuthSessionState.Open;
						session.updateApplication(msg);
						session.setSessionTimers(msg);
						break;
					case SendAnswerUnsuccess:
						session.state = AuthSessionState.Idle;
						Cleanup(session);
						session=null;
						break;
					case SendASR:
						session.state = AuthSessionState.Discon;
						break;
					case SessionTimeout:
					case SessionGraceTimeout:
					case SendSTA:
						session.state = AuthSessionState.Idle;
						Cleanup(session);
						session=null;
						break;
					default:
						LOGGER.severe(" Receive invalid event: "+eventType+" while in state: "+session.state);	
				}
				break;
				
			case Discon:
				switch(eventType){
					case ReceiveSTR:
						break;
					case ReceiveASA:
					case ReceiveASASuccess:
						session.state = AuthSessionState.Idle;
						//Cleanup(session);session=null;
						break;
					case ReceiveASAUnsuccess:
						Send_ASR(session);
						session.state=AuthSessionState.Discon;
						break;
					case SendSTA:
						session.state = AuthSessionState.Idle;
						Cleanup(session);
						session=null;
						break;
					default:
						LOGGER.severe(" Receive invalid event: "+eventType+" while in state: "+session.state);	
				}
				break;
			
			default:
				LOGGER.severe(" Invalid session state: "+session.state);
		}
		if (session!=null)
			session.fireEvent(new SessionEvent(AuthSessionEventType.SessionModified));
	}

	private static void Send_ASR(AuthSession session)
	{
		Request ASR = new Request(Message_Type.Abort_Session_Request.commandCode, session.applicationId);
		try {
			ASR.setSession_Id(session.sessionId);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		session.setDestination(ASR);
		
		try {
			session.setApplication(ASR);
		} catch (AVPDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.sessionManager.diameterPeer.sendMessage(ASR);
	}
	
	protected static void Send_ASA(AuthSession session,Message ASR)
	{
		if (ASR==null || !(ASR instanceof Request)){
			LOGGER.severe("ASR parameter of Send_ASA must be the ASR request!");
			return;
		}
		Answer ASA = new Answer((Request)ASR);
		try {
			ASA.setResult_Code(AVP_Result_Code_Enum.DIAMETER_SUCCESS.value);
		} catch (AVPDataTypeException e) {
			e.printStackTrace();
		}
		session.sessionManager.diameterPeer.sessionManager.diameterPeer.sendMessage(ASA);
	}

	protected static void Send_ASA(DiameterPeer diameterPeer,Message ASR)
	{
		if (ASR==null || !(ASR instanceof Request)){
			LOGGER.severe("ASR parameter of Send_ASA must be the ASR request!");
			return;
		}
		Answer ASA = new Answer((Request)ASR);
		try {
			ASA.setResult_Code(AVP_Result_Code_Enum.DIAMETER_SUCCESS.value);
		} catch (AVPDataTypeException e) {
			e.printStackTrace();
		}
		diameterPeer.sendMessage(ASA);
	}
	
	protected static void Send_STR(AuthSession session) 
	{
		Request STR = new Request(Message_Type.Session_Termination_Request);
		try {
			STR.setSession_Id(session.sessionId);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		session.setDestination(STR);
		
		try {
			session.setApplication(STR);
		} catch (AVPDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		STR.addAVP(new AVP_Termination_Cause(AVP_Termination_Cause_Enum.DIAMETER_ADMINISTRATIVE.value));
		
		session.sessionManager.diameterPeer.sendMessage(STR);
	}
	
	protected static void Cleanup(AuthSession session)
	{
		session.fireEvent(new SessionEvent(AuthSessionEventType.ServiceTerminated));
		session.sessionManager.dropSession(session);
	}
}
