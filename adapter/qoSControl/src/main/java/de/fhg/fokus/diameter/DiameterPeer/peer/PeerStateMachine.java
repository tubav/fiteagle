/*
 * $Id: PeerStateMachine.java 3461 2011-03-29 14:09:46Z lal $
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

package de.fhg.fokus.diameter.DiameterPeer.peer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


import de.fhg.fokus.diameter.DiameterPeer.DiameterTask;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Acct_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Disconnect_Cause_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Result_Code_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Session_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Specific_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.basic.AVP_Grouped;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Answer;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.message.base.CEA;
import de.fhg.fokus.diameter.DiameterPeer.data.message.base.CER;
import de.fhg.fokus.diameter.DiameterPeer.data.message.base.DPA;
import de.fhg.fokus.diameter.DiameterPeer.data.message.base.DPR;
import de.fhg.fokus.diameter.DiameterPeer.data.message.base.DWA;
import de.fhg.fokus.diameter.DiameterPeer.data.message.base.DWR;
import de.fhg.fokus.diameter.DiameterPeer.session.Session;
import de.fhg.fokus.diameter.DiameterPeer.session.auth.AuthSession;
import de.fhg.fokus.diameter.DiameterPeer.session.auth.AuthSessionEventType;
import de.fhg.fokus.diameter.DiameterPeer.session.auth.AuthSessionStateMachine;
import de.fhg.fokus.diameter.DiameterPeer.transport.Communicator;

/**
 * This class defines the Diameter Peer State Machine. 
 * 
 * @author Dragos Vingarzan vingarzan -at- fokus dot fraunhofer dot de
 *
 */
public class PeerStateMachine {
	
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger(PeerStateMachine.class.toString());

	
	/**
	 * Process a transition in the state machine.
	 * 
	 * @param p     The peer for which the event happened.
	 * @param event The event happened.
	 * @return  1 one success, 0 on error. Also the peer states are updated.
	 */
	public static int process(Peer p, PeerEvent event)
	{
		return process(p,event,null,null);
	}
	
	
	
	/**
	 * Process a transition in the state machine.
	 * 
	 * @param p		The peer for which the event happend.
	 * @param event The event happened.
	 * @param msg   Received message.
	 * @return 1 one success, 0 one error. Also the peer states are upated.
	 */
	public static int process(Peer p, PeerEvent event, Message msg)
	{
		return process(p,event,msg,null);
	}
	
	
	/**
	 * Process a transition in the state machine.
	 * 
	 * @param p     The peer for which the event happend.
	 * @param event The event happened.
	 * @param msg   Received message.
	 * @param comm  Communicator used to send a DiameterMessage
	 * @return 1 one success, 0 one error. Also the peer states are upated.
	 */
	public static int process(Peer p, PeerEvent event, Message msg,Communicator comm)
	{
		PeerEvent next_event;
		long result_code;
		boolean msg_received=false;
		LOGGER.warning("Peer Old State: "+p.state+" FQDN:"+p.FQDN+" Event:"+event);

	synchronized(p){
		switch (p.state){
			case Closed:
				switch (event){
					case Start:
						p.state = PeerState.Wait_Conn_Ack;
						next_event = I_Snd_Conn_Req(p);
						PeerStateMachine.process(p,next_event,null,p.I_comm);
						break;
					case R_Conn_CER:
						R_Accept(p,comm);
						result_code = Process_CER(p,msg);
						Snd_CEA(p,msg,result_code,p.R_comm);
						if (result_code>=2000 && result_code<3000)
							p.state = PeerState.R_Open;
						else {
							R_Disc(p);
							p.state = PeerState.Closed;
						}
						break;
					case Stop:
						/* just ignore this state */
						p.state = PeerState.Closed;
						break;
					default:
						LOGGER.severe("StateMachine: Invalid event "+event+" for state "+p.state);
						return 0;
				}
				break;
			case Wait_Conn_Ack:
				switch(event){
					case I_Rcv_Conn_Ack:
						I_Snd_CER(p);
						p.state = PeerState.Wait_I_CEA;
						break;	
					case I_Rcv_Conn_NAck:
						Cleanup(p,comm);
						p.state = PeerState.Closed;
						break;
					case R_Conn_CER:
						p.R_CER = null;
						R_Accept(p,comm);
						result_code = Process_CER(p,msg);
						if (result_code>=2000 && result_code<3000) {
							p.state = PeerState.Wait_Conn_Ack_Elect;							
							p.R_CER = msg;
						} else {
							p.state = PeerState.Closed;
							R_Disc(p);
							I_Disc(p);							
						}
						break;
					case Timeout:
						Error(p,p.I_comm);
						p.state = PeerState.Closed;
					default:
						LOGGER.severe("StateMachine: Invalid event "+event+" for state "+p.state);
						return 0;
				}
				break;
			case Wait_I_CEA:
				switch(event){
					case I_Rcv_CEA:
						result_code = Process_CEA(p,(CEA)msg);
						if (result_code>=2000 && result_code<3000)
							p.state = PeerState.I_Open; 
						else {
							Cleanup(p,p.I_comm);
							p.state = PeerState.Closed;
						}
						break;
					case R_Conn_CER:
						p.R_CER = null;
						R_Accept(p,comm);
						result_code = Process_CER(p,msg);
						if (result_code>=2000 && result_code<3000){
							p.state = PeerState.Wait_Returns;
							if (Elect(p,msg)) {
								LOGGER.warning("Wait_I_CEA: Win Elect");
								PeerStateMachine.process(p,PeerEvent.Win_Election,msg,comm);
							}else{
								LOGGER.warning("Wait_I_CEA: Lose Elect");
								p.R_CER = msg;
								PeerStateMachine.process(p,PeerEvent.I_Peer_Disc,null,p.I_comm);
							}
						} else {
							Snd_CEA(p,msg,result_code,p.R_comm);
							R_Disc(p);
							I_Disc(p);
							p.state = PeerState.Closed;
						}
						break;
					case I_Peer_Disc:
						I_Disc(p);
						p.state = PeerState.Closed;
						break;
					case I_Rcv_Non_CEA:
						Error(p,p.I_comm);
						p.state = PeerState.Closed;
						break;
					case Timeout:
						Error(p,p.I_comm);
						p.state = PeerState.Closed;
						break;
					default:
						LOGGER.severe("StateMachine: Invalid event "+event+" for state "+p.state);
						return 0;
				}
				break;

			case Wait_Conn_Ack_Elect:
				switch(event){
					case I_Rcv_Conn_Ack:
						I_Snd_CER(p);
						if (p.R_CER!=null) {
							p.state = PeerState.Wait_Returns;
							if (Elect(p,p.R_CER)){
								LOGGER.warning("Wait_Conn_Ack_Elect: Win Elect");
								PeerStateMachine.process(p,PeerEvent.Win_Election,p.R_CER,comm);
								p.R_CER = null;
							}else{
								LOGGER.warning("Wait_Conn_Ack_Elect: Lose Elect");
								p.R_CER = null;								
							}															
						}else{
							LOGGER.warning("Wait_Conn_Ack_Elect: I_Rcv_Conn_Ack, but there's no R_CER!");
							p.state = PeerState.Wait_I_CEA;							
						}
						break;
					case I_Rcv_Conn_NAck:
						Cleanup(p, p.I_comm);
						if (p.R_CER!=null){
							result_code = Process_CER(p,p.R_CER);
							Snd_CEA(p,p.R_CER,result_code,p.R_comm);
							p.R_CER = null;
							if (result_code>=2000 && result_code<3000)
								p.state = PeerState.R_Open;
							else {
								R_Disc(p);
								p.state = PeerState.Closed;
							}	
						}else{
							LOGGER.warning("Wait_Conn_Ack_Elect: I_Rcv_Conn_NAck, but there's no R_CER!");
						}
						break;
					case R_Peer_Disc:
						R_Disc(p);
						p.state = PeerState.Wait_Conn_Ack;
						break;
					case R_Conn_CER:
						R_Reject(p, comm);
						p.state = PeerState.Wait_Conn_Ack_Elect;
						break;
					case Timeout:
						if (p.I_comm!=null) Error(p,p.I_comm);
						if (p.R_comm!=null) Error(p,p.R_comm);
						p.state = PeerState.Closed;
						break;
					default:
						LOGGER.severe("StateMachine: Invalid event "+event+" for state "+p.state);
						return 0;
				}
				break;
			
			case Wait_Returns:
				switch(event){
					case Win_Election:
						/* this is the Win Election -> I is dropped, R is kept */
						I_Disc(p);
						result_code = Process_CER(p,msg);
						Snd_CEA(p,msg,result_code,p.R_comm);
						if (result_code>=2000 && result_code<3000){
							p.state = PeerState.R_Open;
						}else{
							R_Disc(p);
							p.state = PeerState.Closed;
						}
						break;
					case I_Peer_Disc:
						I_Disc(p);
						if (p.R_CER!=null){ 
							result_code = Process_CER(p,msg);
							Snd_CEA(p,p.R_CER,result_code,p.R_comm);
							p.R_CER = null;
							if (result_code>=2000 && result_code<3000){
								p.state = PeerState.R_Open;
							}else{
								R_Disc(p);
								p.state = PeerState.Closed;
							}
						}else{
							LOGGER.warning("Wait_Returns: I_Peer_Disc, but there's no R_CER!");
						}
						break;
					case I_Rcv_CEA:
						/* this is the Lose Election -> I is kept, R is dropped */
						R_Disc(p);
						result_code = Process_CEA(p,(CEA)msg);
						if (result_code>=2000 && result_code<3000)
							p.state = PeerState.I_Open; 
						else {
							Cleanup(p,p.I_comm);
							p.state = PeerState.Closed;
						}
						break;
					case R_Peer_Disc:
						R_Disc(p);
						p.state = PeerState.Wait_I_CEA;
						break;
					case R_Conn_CER:
						R_Reject(p,comm);
						p.state = PeerState.Wait_Returns;
						break;
					case Timeout:
						if (p.I_comm!=null) Error(p,p.I_comm);
						if (p.R_comm!=null) Error(p,p.R_comm);
						p.state = PeerState.Closed;
					default:
						LOGGER.severe("StateMachine: Invalid event "+event+" for state "+p.state);
						return 0;
				}
				break;
			case R_Open:
				switch (event){
					case Send_Message:
						Snd_Message(p,msg);
						p.state = PeerState.R_Open;
						break;
					case R_Rcv_Message:
						// delayed processing until out of the critical zone
						//Rcv_Process(p,msg);
						msg_received = true;
						p.state = PeerState.R_Open;
						break;
					case R_Rcv_DWR:
						result_code = Process_DWR(p,msg);
						Snd_DWA(p,msg,result_code,p.R_comm);
						p.state = PeerState.R_Open;
						break;
					case R_Rcv_DWA:
						Process_DWA(p,msg);
						p.state = PeerState.R_Open;
						break;
					case R_Conn_CER:
						R_Reject(p,comm);
						p.state = PeerState.R_Open;
						break;
					case Stop:
						Snd_DPR(p);
						p.state = PeerState.Closing;
						break;
					case R_Rcv_DPR:
						Snd_DPA(p,msg,AVP_Result_Code_Enum.DIAMETER_SUCCESS.value,p.R_comm);
						R_Disc(p);
						p.state = PeerState.Closed;
						break;
					case R_Peer_Disc:
						R_Disc(p);
						p.state = PeerState.Closed;
						break;
					case R_Rcv_CER:
						result_code = Process_CER(p,msg);
						Snd_CEA(p,msg,result_code,p.R_comm);
						if (result_code>=2000 && result_code<3000)
							p.state = PeerState.R_Open;
						else {
							/*R_Disc(p);p.state = Closed;*/
							p.state = PeerState.R_Open; /* Or maybe I should disconnect it?*/
						}
						break;
					case R_Rcv_CEA:
						result_code = Process_CEA(p,(CEA)msg);
						if (result_code>=2000 && result_code<3000)
							p.state = PeerState.R_Open;
						else {
							/*R_Disc(p);p.state = Closed;*/
							p.state = PeerState.R_Open; /* Or maybe I should disconnect it?*/
						}
						break;
					default:
						LOGGER.severe("StateMachine: Invalid event "+event+" for state "+p.state);
						return 0;
				}
				break;
			case I_Open:
				switch (event){
					case Send_Message:
						Snd_Message(p,msg);
						p.state = PeerState.I_Open;
						break;
					case I_Rcv_Message:
						// delayed processing until out of the critical zone
						//Rcv_Process(p,msg);
						msg_received = true;
						p.state = PeerState.I_Open;
						break;
					case I_Rcv_DWR:
						result_code = Process_DWR(p,msg);
						Snd_DWA(p,msg,result_code,p.I_comm);						
						p.state = PeerState.I_Open;
						break;
					case I_Rcv_DWA:
						Process_DWA(p,msg);
						p.state = PeerState.I_Open;
						break;
					case R_Conn_CER:
						R_Reject(p,comm);
						p.state = PeerState.I_Open;
						break;
					case Stop:
						Snd_DPR(p);
						p.state = PeerState.Closing;
						break;
					case I_Rcv_DPR:
						Snd_DPA(p,msg,2001,p.I_comm);
						I_Disc(p);
						p.state = PeerState.Closed;
						break;
					case I_Peer_Disc:
						I_Disc(p);
						p.state = PeerState.Closed;
						break;
					case I_Rcv_CER:
						result_code = Process_CER(p,msg);
						Snd_CEA(p,msg,result_code,p.I_comm);
						if (result_code>=2000 && result_code<3000)
							p.state = PeerState.I_Open;
						else {
							/*I_Disc(p);p.state = Closed;*/
							p.state = PeerState.I_Open; /* Or maybe I should disconnect it?*/
						}
						break;
					case I_Rcv_CEA:
						result_code = Process_CEA(p,(CEA)msg);
						if (result_code>=2000 && result_code<3000)
							p.state = PeerState.I_Open;
						else {
							/*I_Disc(p);p.state = Closed;*/
							p.state = PeerState.I_Open; /* Or maybe I should disconnect it?*/
						}
						break;
					default:
						LOGGER.severe("StateMachine: Invalid event "+event+" for state "+p.state);
						return 0;
				}
				break;				
			case Closing:
				switch(event){
					case I_Rcv_DPA:
						I_Disc(p);
						p.state = PeerState.Closed;
						break;
					case R_Rcv_DPA:
						R_Disc(p);
						p.state = PeerState.Closed;
						break;
					case Timeout:
						if (p.I_comm!=null) Error(p,p.I_comm);
						if (p.R_comm!=null) Error(p,p.R_comm);
						p.state = PeerState.Closed;
						break;
					case I_Peer_Disc:
						I_Disc(p);
						p.state = PeerState.Closed;
						break;
					case R_Peer_Disc:
						R_Disc(p);
						p.state = PeerState.Closed;
						break;
					default:
						LOGGER.severe("StateMachine: Invalid event "+event+" for state "+p.state);
						return 0;
				}
				break;
			default:
				LOGGER.severe("StateMachine: Invalid state "+p.state);
				return 0;
				
		}
		LOGGER.warning("Peer New State: "+p.state+" FQDN:"+p.FQDN);
	}
		if (msg_received){
			// delayed processing until out of the critical zone
			Rcv_Process(p,msg);
		}
		
		return 1;
	}
	
	private static PeerEvent I_Snd_Conn_Req(Peer p)
	{
		Socket s;
		if (p.I_comm!=null) p.I_comm.shutdown();
		p.I_comm = null;
		try {
			s = new Socket(p.FQDN,p.port);
		} catch (UnknownHostException e1) {
			LOGGER.severe("StateMachine: Peer "+p.FQDN+" can not be resolved.");
			return PeerEvent.I_Rcv_Conn_NAck;
		} catch (IOException e1) {
			LOGGER.severe("StateMachine: Peer "+p.FQDN+":"+p.port+" not responding to connection attempt ");
			return PeerEvent.I_Rcv_Conn_NAck;
		}
		Communicator r = new Communicator(s,p,Communicator.Initiator);
		p.I_comm = r;
		return PeerEvent.I_Rcv_Conn_Ack;
	}

	private static void R_Accept(Peer p,Communicator comm)
	{
		p.R_comm = comm;
		p.refreshTimer();
	}

	private static void R_Reject(Peer p,Communicator comm)
	{
		comm.shutdown();
	}
	
	private static void I_Snd_CER(Peer p)
	{
		CER cer = new CER();
		
		cer.hopByHopID = p.diameterPeer.getNextHopByHopId();
		cer.endToEndID = p.diameterPeer.getNextEndToEndId();
		cer.setOrigin_Host(p.diameterPeer.FQDN);
		cer.setOrigin_Realm(p.diameterPeer.Realm);
		cer.setHost_IP_Address(p.I_comm.socket.getLocalAddress());
		try {
			cer.setVendor_Id(p.diameterPeer.Vendor_Id);
		} catch (AVPDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cer.setProduct_Name(p.diameterPeer.Product_Name);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Snd_CE_add_applications(cer,p);
		
		//LOGGER.fine(cer.toString());
		p.I_comm.sendDirect(cer);
	}
	
	
	private static void Cleanup(Peer p,Communicator comm)
	{
		if (comm==null) return;
		comm.shutdown();
		if (p.I_comm == comm) p.I_comm = null;
		if (p.R_comm == comm) p.R_comm = null;
	}

	private static void Error(Peer p, Communicator comm)
	{
		Cleanup(p,comm);
	}

	private static boolean Elect(Peer p,Message cer)
	{
		/* returns if we win the election */
		AVP avp;
		byte[] remote,local;
		int x,i;
		local = p.diameterPeer.FQDN.getBytes();
		avp = cer.findAVP(AVP_Type.Origin_Host);
		if (avp==null) {
			return true;
		}else{
			remote = avp.getRaw();
			for(i=0;i<remote.length&&i<local.length;i++){
				x = ((int) local[i]&0xFF)-((int) remote[i]&0xFF);
				if (x>0) return true;
				if (x<0) return false;
			}
			if (local.length>remote.length) return true;
			return false;
		}
	}

	private static long Process_CER(Peer p,Message cer)
	{
		int common_app=0;
		Iterator<AVP> i = cer.avps.iterator();
		Iterator<Application> i2;
		Application app;
		AVP avp;
		AVP_Grouped avp_group;
		AVP_Vendor_Id avp_vendor;
		AVP avp2;
		p.AuthApp.clear();
		p.AcctApp.clear();
		long app_id;
		
		while(i.hasNext()&& common_app==0){
			avp = (AVP) i.next();
			switch (avp.type){
				case Auth_Application_Id:
					app_id = ((AVP_Auth_Application_Id)avp).get();
					p.AuthApp.add(new Application(app_id,0,Application.Auth));					
					i2 = p.diameterPeer.AuthApp.iterator();
					while(i2.hasNext()){
						app = i2.next();
						if (app_id==Application.Relay ||
							(app.id==app_id && app.vendor==0)){
							common_app++;
							break;
						}
					}
					break;
				case Acct_Application_Id:
					app_id = ((AVP_Acct_Application_Id)avp).get();
					p.AcctApp.add(new Application(app_id,0,Application.Acct));
					i2 = p.diameterPeer.AcctApp.iterator();
					while(i2.hasNext()){
						app = i2.next();
						if (app_id==Application.Relay ||
							(app.id==app_id && app.vendor==0)){
							common_app++;
							break;
						}
					}
					break;
				case Vendor_Specific_Application_Id:
					avp_group = (AVP_Grouped) avp;
					avp_vendor = (AVP_Vendor_Id)avp_group.findChildAVP(AVP_Type.Vendor_Id);
					if (avp_vendor==null) break;
					avp2 = avp_group.findChildAVP(AVP_Type.Auth_Application_Id);
					if (avp2!=null) {
						app_id = ((AVP_Auth_Application_Id)avp2).get();
						p.AuthApp.add(new Application(app_id,avp_vendor.get(),Application.Auth));
						i2 = p.diameterPeer.AuthApp.iterator();
						while(i2.hasNext()){
							app = i2.next();
							if (app_id==Application.Relay ||
								(app.id==app_id && app.vendor==avp_vendor.get())){
								common_app++;
								break;
							}
						}
					}
					avp2 = avp_group.findChildAVP(AVP_Type.Acct_Application_Id);
					if (avp2!=null) {
						app_id = ((AVP_Acct_Application_Id)avp2).get();
						p.AcctApp.add(new Application(app_id,avp_vendor.get(),Application.Acct));
						i2 = p.diameterPeer.AcctApp.iterator();
						while(i2.hasNext()){
							app = (Application) i2.next();
							if (app_id==Application.Relay ||
								(app.id==app_id && app.vendor==avp_vendor.get())){
								common_app++;
								break;
							}
						}
					}
					break;
					
			}
		}
		
		if (common_app!=0)
			return AVP_Result_Code_Enum.DIAMETER_SUCCESS.value;
		else return AVP_Result_Code_Enum.DIAMETER_NO_COMMON_APPLICATION.value;
	}

	private static long Process_CEA(Peer p,CEA cea)
	{
		long result_code = cea.getResult_Code();		
		if (result_code==0) return AVP_Result_Code_Enum.DIAMETER_UNABLE_TO_COMPLY.value;
		else return result_code;
	}
	
	private static void Snd_CEA(Peer p,Message cer,long resultCode,Communicator comm)
	{
		CEA cea=null;
		try {
			cea = new CEA(resultCode);
		} catch (AVPDataTypeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		cea.hopByHopID = cer.hopByHopID;
		cea.endToEndID = cer.endToEndID;
		cea.setOrigin_Host(p.diameterPeer.FQDN);
		cea.setOrigin_Realm(p.diameterPeer.Realm);
		cea.setHost_IP_Address(p.R_comm.socket.getLocalAddress());
		try {
			cea.setVendor_Id(p.diameterPeer.Vendor_Id);
		} catch (AVPDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cea.setProduct_Name(p.diameterPeer.Product_Name);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Snd_CE_add_applications(cea,p);
		
		//LOGGER.fine(cea.toString());
		comm.sendDirect(cea);
	}
	
	private static void I_Disc(Peer p)
	{
		if (p.I_comm!=null)
		p.I_comm.shutdown();
		p.I_comm = null;
	}
	private static void R_Disc(Peer p)
	{
		if (p.R_comm!=null)
			p.R_comm.shutdown();
		p.R_comm = null;
	}
	
	private static void Snd_CE_add_applications(Message msg,Peer p)
	{
		AVP avp;
		for(Application app: p.diameterPeer.AuthApp){
			if (app.vendor==0){
				try {
					avp = new AVP_Auth_Application_Id(app.id);
					msg.addAVP(avp);
				} catch (AVPDataTypeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}else{
				try {
					avp = new AVP_Vendor_Specific_Application_Id(new AVP[] {
							new AVP_Auth_Application_Id(app.id),
							new AVP_Vendor_Id(app.vendor)
							});
					msg.addAVP(avp);
				} catch (AVPDataTypeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for(Application app: p.diameterPeer.AcctApp){
			if (app.vendor==0){
				try {
					avp = new AVP_Acct_Application_Id(app.id);
					msg.addAVP(avp);
				} catch (AVPDataTypeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					avp = new AVP_Vendor_Specific_Application_Id(new AVP[] {
							new AVP_Acct_Application_Id(app.id),
							new AVP_Vendor_Id(app.vendor)
							});
					msg.addAVP(avp);
				} catch (AVPDataTypeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private static long Process_DWR(Peer p,Message dwr)
	{
		return AVP_Result_Code_Enum.DIAMETER_SUCCESS.value;
	}

	private static void Process_DWA(Peer p,Message dwa)
	{
		p.waitingDWA = false;
	}

	/**
	 * Send Device-Watchdog-Request.
	 * 
	 * @param p Peer sending the request.
	 * @return true if sending successful, false otherwise. 
	 */
	public static boolean Snd_DWR(Peer p)
	{
		DWR dwr;
		boolean r=false;
		
		dwr = new DWR();
		dwr.hopByHopID = p.diameterPeer.getNextHopByHopId();
		dwr.endToEndID = p.diameterPeer.getNextEndToEndId();
		dwr.setOrigin_Host(p.diameterPeer.FQDN);
		dwr.setOrigin_Realm(p.diameterPeer.Realm);
		
		if (p.state==PeerState.I_Open)
			r = p.I_comm.sendDirect(dwr);
		if (p.state==PeerState.R_Open)
			r = p.R_comm.sendDirect(dwr);
		return r;
	}

	private static void Snd_DWA(Peer p,Message dwr,long resultCode,Communicator comm)
	{
		DWA dwa;
	
		try {
			dwa = new DWA(resultCode);
			dwa.hopByHopID = dwr.hopByHopID;
			dwa.endToEndID = dwr.endToEndID;
			dwa.setOrigin_Host(p.diameterPeer.FQDN);
			dwa.setOrigin_Realm(p.diameterPeer.Realm);
			
			LOGGER.fine(dwa.toString());
			comm.sendDirect(dwa);
		} catch (AVPDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Send Disconnect-Peer-Request.
	 * 
	 * @param p Peer sending the message.
	 */
	public static void Snd_DPR(Peer p)
	{
		DPR dpr;
		
		dpr = new DPR();
		dpr.hopByHopID = p.diameterPeer.getNextHopByHopId();
		dpr.endToEndID = p.diameterPeer.getNextEndToEndId();
		dpr.setOrigin_Host(p.diameterPeer.FQDN);
		dpr.setOrigin_Realm(p.diameterPeer.Realm);
		dpr.setDisconnect_Cause(AVP_Disconnect_Cause_Enum.BUSY.value);
		
		LOGGER.fine(dpr.toString());
		if (p.state==PeerState.I_Open)
			p.I_comm.sendDirect(dpr);
		if (p.state==PeerState.R_Open)
			p.R_comm.sendDirect(dpr);
	}

	private static void Snd_DPA(Peer p,Message dpr,long result_code,Communicator comm)
	{
		DPA dpa;
	
		try {
			dpa = new DPA(result_code);
			dpa.hopByHopID = dpr.hopByHopID;
			dpa.endToEndID = dpr.endToEndID;
			dpa.setOrigin_Host(p.diameterPeer.FQDN);
			dpa.setOrigin_Realm(p.diameterPeer.Realm);
			
			LOGGER.fine(dpa.toString());
			comm.sendDirect(dpa);
		} catch (AVPDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Send a Diameter message.
	 * 
	 * @param p   Peer sending the Diameter message.
	 * @param msg Diameter message being sent.
	 */
	public static void Snd_Message(Peer p, Message msg)
	{
		p.refreshTimer();
		
		Session session=null;
		AuthSession authSession=null;
		AVP_Session_Id sessionId = msg.getSessionId();
		if (sessionId!=null)
			try {
				session = p.diameterPeer.sessionManager.getSession(sessionId.get());
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if (session!=null){
			switch(session.type){
				case AuthorizationClientStateful:
					if (!(session instanceof AuthSession)){
						LOGGER.severe("Session of type "+session.type+" is not and instance of AuthSession!");
						break;
					}
					authSession = (AuthSession) session;				
					if (msg.flagRequest){
						AuthSessionStateMachine.process(authSession, AuthSessionEventType.SendRequest, msg, p.diameterPeer);
					}else{
						if (msg.commandCode == Message_Type.Abort_Session_Answer.commandCode){
							Answer ans = (Answer)msg;
							long resultCode = ans.getResult_Code();
							if (resultCode>=2000&&resultCode<3000){
								AuthSessionStateMachine.process(authSession,AuthSessionEventType.SendASASuccess,msg,p.diameterPeer);							
							}else{
								AuthSessionStateMachine.process(authSession,AuthSessionEventType.SendASAUnsuccess,msg,p.diameterPeer);
							}						
						}else{
							AuthSessionStateMachine.process(authSession, AuthSessionEventType.SendAnswer, msg, p.diameterPeer);
						}
					}
					session.unLock();
					session=null;
					break;
					
				case AuthorizationServerStateful:
					if (!(session instanceof AuthSession)){
						LOGGER.severe("Session of type "+session.type+" is not and instance of AuthSession!");
						break;
					}
					authSession = (AuthSession) session;
					if (msg.flagRequest){
						if (msg.commandCode==Message_Type.Abort_Session_Request.commandCode)
							AuthSessionStateMachine.process(authSession, AuthSessionEventType.SendASR, msg, p.diameterPeer);
						else
							AuthSessionStateMachine.process(authSession, AuthSessionEventType.SendRequest, msg, p.diameterPeer);
					}else{
						if (msg.commandCode==Message_Type.Session_Termination_Answer.commandCode)
							AuthSessionStateMachine.process(authSession, AuthSessionEventType.SendSTA, msg, p.diameterPeer);
						else
							AuthSessionStateMachine.process(authSession, AuthSessionEventType.SendAnswer, msg, p.diameterPeer); 						
					}
					session.unLock();
					session=null;
					
					
					break;
				default:
					LOGGER.severe("Session of type "+session.type+" is not handled!");
				    session.unLock();
					session=null;
			}
		}
	}

	
	/**
	 * Process the received Diameter message.
	 * 
	 * @param p		Peer receiving the Diameter message.
	 * @param msg   The received Diameter message.
	 */
	public static void Rcv_Process(Peer p, Message msg)
	{
		boolean done=false;
		Session session=null;
		AuthSession authSession=null;
		AVP_Session_Id sessionId = msg.getSessionId();
		if (sessionId!=null)
			try {
				session = p.diameterPeer.sessionManager.getSession(sessionId.get());
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		if (session!=null){
			switch(session.type){
				case AuthorizationClientStateful:
					if (!(session instanceof AuthSession)){
						LOGGER.severe("Session of type "+session.type+" is not and instance of AuthSession!");
						break;
					}
					authSession = (AuthSession) session;
					if (msg.flagRequest){
						if (msg.commandCode==Message_Type.Abort_Session_Request.commandCode)
							AuthSessionStateMachine.process(authSession, AuthSessionEventType.ReceiveASR, msg, p.diameterPeer);
						else
							AuthSessionStateMachine.process(authSession, AuthSessionEventType.ReceiveRequest, msg, p.diameterPeer);
					}else{
						if (msg.commandCode==Message_Type.Session_Termination_Answer.commandCode)
							AuthSessionStateMachine.process(authSession, AuthSessionEventType.ReceiveSTA, msg, p.diameterPeer);
						else
							AuthSessionStateMachine.process(authSession, AuthSessionEventType.ReceiveAnswer, msg, p.diameterPeer); 						
					}
					session.unLock();
					session=null;
					break;
					
				case AuthorizationServerStateful:
					if (!(session instanceof AuthSession)){
						LOGGER.severe("Session of type "+session.type+" is not and instance of AuthSession!");
						break;
					}
					authSession = (AuthSession) session;
					if (msg.flagRequest){
						if (msg.commandCode==Message_Type.Session_Termination_Request.commandCode)
							AuthSessionStateMachine.process(authSession, AuthSessionEventType.ReceiveSTR, msg, p.diameterPeer);
						else
							AuthSessionStateMachine.process(authSession, AuthSessionEventType.ReceiveRequest, msg, p.diameterPeer);
					}else{
						if (msg.commandCode==Message_Type.Abort_Session_Answer.commandCode)
							AuthSessionStateMachine.process(authSession, AuthSessionEventType.ReceiveASA, msg, p.diameterPeer);
						else
							AuthSessionStateMachine.process(authSession, AuthSessionEventType.ReceiveAnswer, msg, p.diameterPeer); 						
					}
					session.unLock();
					session=null;					
					break;
					
				default:
					LOGGER.severe("Session of type "+session.type+" is not handled!");
					session.unLock();
					session=null;		
			}
		}else{
			if (sessionId!=null&&msg.commandCode == Message_Type.Abort_Session_Request.commandCode)
				AuthSessionStateMachine.process(null, AuthSessionEventType.ReceiveASR, msg, p.diameterPeer);
		}
			
		if (p.diameterPeer.eventListeners.size()!=0){
			while(!done)
			{
				try {
					done = p.diameterPeer.queueTasks.offer(new DiameterTask(p,msg),1000,TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
//				if (!done) LOGGER.fine("ful "+p.diameterPeer.queueTasks.size());
//				else LOGGER.fine("put "+p.diameterPeer.queueTasks.size());
//				//LOGGER.fine("StateMachine: Processing queue is full. Overload...");
			}
		}
		//LOGGER.fine("StateMachine: Received message "+msg.toString()+"---");		
	}
}
