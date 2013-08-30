/*
 * $Id: Communicator.java 1980 2010-07-20 14:37:22Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;


import de.fhg.fokus.diameter.DiameterPeer.DiameterPeer;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.Codec;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.MessageDecodeException;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message_Type;
import de.fhg.fokus.diameter.DiameterPeer.peer.Peer;
import de.fhg.fokus.diameter.DiameterPeer.peer.PeerEvent;
import de.fhg.fokus.diameter.DiameterPeer.peer.PeerStateMachine;

/**
 * This class defines the Diameter Connection Receiver.
 * <p>
 * A communicator maintains a connection with a peer. After its creation, it 
 * will be managed by the PeerManager.
 * 
 * @author Dragos Vingarzan vingarzan -at- fokus dot fraunhofer dot de
 *
 */
public class Communicator extends Thread {
	
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger(Communicator.class.toString());

	/** DiameterPeer API reference */
	public DiameterPeer diameterPeer;
		
	/** peer it is comunicating for */
	public Peer peer=null;
	
	/** indicator if still active */
	public boolean running; 
	
	/** Direction of socket opening */
	public int direction;
	public static final int Initiator 	= 0;
	public static final int Receiver 	= 1;
	
	/** socket connected to */
	public Socket socket;

	/** Diameter protocol header: 
		byte 0:   protocol version
		byte 1-3: length of message, including the header
	*/
	private static int HEADER_LENGTH = 4;

	/**
	   The size of the buffer that run() allocates for flushing the
	   InputStream of a message that is too big to fit in memory.
	 */
	private static final int TRASH_BUFFER_LENGTH = 524388; // 512KB

	/**
	 * Constructor giving the opened socket.
	 * 
	 * @param socket    Socket should be opened.
	 * @param dp		DiameterPeer, which contains several Peers  
	 * @param direction 1 for initiator, 0 for receiver
	 */
	public Communicator(Socket socket, DiameterPeer dp,int direction) {
		this.socket = socket;
		this.direction = direction;
		running = true;
		this.diameterPeer = dp;
		this.start();		
	}
	 
	/**
	 * Constructor giving the opened socket.
	 * 
	 * @param socket    Socket should be opened.
	 * @param p         Peer, for which the socket is opened.
	 * @param direction 1 for initiator, 0 for receiver.
	 */
	public Communicator(Socket socket, Peer p,int direction) {
		this.socket = socket;
		this.direction = direction;
		running = true;
		this.diameterPeer = p.diameterPeer;
		this.peer = p;
		this.start();		
	}
	
	
	
	/**
	 * Send a Diameter message.
	 * 
	 * @param msg The Diameter message which is sent.
	 * @return true if successful, false otherwise.
	 */
	public boolean sendMessage(Message msg)
	{
		if (this.peer!=null){
			// to optimize the call and avoid critical zone 
			PeerStateMachine.process(peer,PeerEvent.Send_Message,msg,this);
			//PeerStateMachine.Snd_Message(peer,msg);
		}
			
		return sendDirect(msg);
	}
	
	
	
	/**
	 * Send a Diameter message.
	 * 
	 * @param msg Diameter request which is sent.
	 * @return true if successful, false otherwise.
	 */
	public synchronized boolean sendDirect(Message msg)
	{
		if (!socket.isConnected()) {
			LOGGER.warning("Communicator: Tried to send message to unconnected socket.");
			return false;
		}
		byte[] buffer;
		int sent=0;
		//LOGGER.fine("Communicator:sendDirect():"+msg.toString());
		try {
			OutputStream out=socket.getOutputStream();
			buffer = Codec.encodeMessage(msg);
			out.write(buffer,sent,buffer.length-sent);
			msg.networkTime = System.currentTimeMillis();
		} catch (Exception e){
			LOGGER.fine("Communicator: Error on message send\n");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		InputStream in;
		byte[] buffer;
		long bytesRead = 0L;

		// Every message begins with a header.  We know how big that
		// is.  We don't need to allocate the header every time.
		byte[] header = new byte[HEADER_LENGTH];

		// When a message is legitimate but too big to fit into the
		// JVM, we'll attempt to read all of it in chunks to remove if
		// from the InputStream.  We put those chunks in trashBuffer.
		byte[] trashBuffer = new byte[TRASH_BUFFER_LENGTH];
		Message msg;
		
		int cnt = 0,len = 0,x = 0;
		
		try {
			socket.setTcpNoDelay(true);
			in = socket.getInputStream();
		} catch (IOException e) {
			LOGGER.warning("Communicator: Error getting InputStream from socket");
			e.printStackTrace();
			return;
		}

		try {
			// TALMAGE: New plan: After reading the four-byte header,
			// allocate a buffer for the whole message.  Read into
			// the buffer in chunks, blocking if necessary.
			//
			// What I really want to do is to use a StringBuilder to
			// accumulate bytes.  Alas, there is no byte holding
			// version of StringBuilder.
			//
			// I also considered using Java NIO.
			while(running){
				/* first we read the version */ 
				cnt=0;
				while(cnt<1){
					x=in.read(header,cnt,1);
					// It's not that the read failed.  There isn't
					// any more data in the input stream.
					if (x<0) throw(new Exception("Read failed"));
					cnt+=x;
				}
				if (header[0]!=1){
					LOGGER.warning("Communicator: Expecting diameter version 1. Received version "+header[0]);
					continue;
				}
				/* then we read the length of the message */
				while(cnt<4){
					x = in.read(header,cnt,4-cnt);
					if (x<0) throw(new Exception("Read failed.  # bytes before failure: "));
					cnt+=x;
					bytesRead += x;
				}
				len = ((int)header[1]&0xFF)<<16 |
					((int)header[2]&0xFF)<< 8 |
					((int)header[3]&0xFF);

				LOGGER.fine("Message length " + len);
				try {
					// The biggest message permitted by the protocol
					// is 2^24 (= 16777216) bytes.  This could throw
					// OutOfMemoryError.
					buffer = new byte[len];

					// This could throw three different RuntimeExceptions.
					// The conditions that cause them will never occur
					// here, so there is no need to catch them.
					System.arraycopy(header, 0, buffer, 0, header.length);

					/* and then we read all the rest of the message */
					while(cnt<len){
						x = in.read(buffer,cnt,len-cnt);
						// It's not that the read failed.  There isn't
						// any more data in the input stream.
						if (x<0) throw(new Exception("Premature end of input stream"));
						cnt+=x;
						bytesRead += x;
					}
					//LOGGER.fine("received "+cnt+" bytes");
					/* now we can decode the message */
					try {
						msg = Codec.decodeMessage(buffer,0);
					} catch (MessageDecodeException e3) {
						LOGGER.warning("Communicator: Error decoding diameter message");
						e3.printStackTrace();
						continue;
					}
					//LOGGER.fine("Communicator:run(): "+msg.toString());
					msg.networkTime = System.currentTimeMillis();
					if (this.peer!=null)
						this.peer.refreshTimer();
					processMessage(msg);
					msg = null;
				} catch (OutOfMemoryError e1) {
					// Attempt to read all of the bytes in the message and
					// throw them away.  Is it an error to continue?
					//
					// TALMAGE: I want to wrap the arg of
					// LOGGER.warning() in one of my Strings objects to
					// prevent concatenation unless the logging level
					// is Level.WARN or higher.  However,
					// OutOfMemoryError shouldn't occur very often and
					// I don't have permission to distribute the
					// Strings class.
					LOGGER.warning("Out of memory allocating buffer for message of length " + len);
					LOGGER.fine(e1.getMessage());
					try {
						while (cnt < len) {
							x = in.read(trashBuffer, 0, trashBuffer.length);
							cnt += x;
							bytesRead += x;
						}
					} catch (IOException ex) {						
						LOGGER.warning("Read " + bytesRead + 
									" before Exception."+ex.getMessage());
						ex.printStackTrace();
						disconnect(ex);
						running=false;
					}
				}
			}
		} catch (Exception e1) {
			LOGGER.warning("Read " + bytesRead + " before Exception."+e1.getMessage());
			e1.printStackTrace();
			disconnect(e1);
		}

		try {
			socket.close();
		} catch (IOException e2) {
			LOGGER.warning("Communicator: Error closing socket.");
			e2.printStackTrace();
		}
	}
	
	private void processMessage(Message msg)
	{
		PeerEvent event;
		
		LOGGER.fine("Communicator: Received message \n"+
				msg.toString()+"\n---");
		
		/* pre-processing for special states */
		if (this.peer!=null){
			switch (this.peer.state){
				case Wait_I_CEA:
					if (msg.type!=Message_Type.Capabilities_Exchange_Answer){
						PeerStateMachine.process(this.peer,PeerEvent.I_Rcv_Non_CEA,msg,this);
						return;
					}
					break;		
				case R_Open:
					if (msg.type==null){
						/* faster processing -> no state machine for regular messages */
						//StateMachine.process(this.peer,PeerEvent.R_Rcv_Message,msg,this);
						PeerStateMachine.Rcv_Process(this.peer,msg);
						return;
					}
					switch (msg.type){
						case Capabilities_Exchange_Request:
							PeerStateMachine.process(this.peer,PeerEvent.R_Rcv_CER,msg,this);
							return;
						case Capabilities_Exchange_Answer:
							PeerStateMachine.process(this.peer,PeerEvent.R_Rcv_CEA,msg,this);
							return;
						case Device_Watchdog_Request:							
							PeerStateMachine.process(this.peer,PeerEvent.R_Rcv_DWR,msg,this);
							return;
						case Device_Watchdog_Answer:
							PeerStateMachine.process(this.peer,PeerEvent.R_Rcv_DWA,msg,this);
							return;
						case Disconnect_Peer_Request:							
							PeerStateMachine.process(this.peer,PeerEvent.R_Rcv_DPR,msg,this);
							return;
						case Disconnect_Peer_Answer:
							PeerStateMachine.process(this.peer,PeerEvent.R_Rcv_DPA,msg,this);
							return;
						default:
							/* faster processing -> no state machine for regular messages */
							//StateMachine.process(this.peer,PeerEvent.R_Rcv_Message,msg,this);
							PeerStateMachine.Rcv_Process(this.peer,msg);
							return;
					}
				case I_Open:
					if (msg.type==null){
						/* faster processing -> no state machine for regular messages */
						//StateMachine.process(this.peer,PeerEvent.I_Rcv_Message,msg,this);
						PeerStateMachine.Rcv_Process(this.peer,msg);
						return;
					}
					switch (msg.type){
						case Capabilities_Exchange_Request:						
							PeerStateMachine.process(this.peer,PeerEvent.I_Rcv_CER,msg,this);
							return;
						case Capabilities_Exchange_Answer:
							PeerStateMachine.process(this.peer,PeerEvent.I_Rcv_CEA,msg,this);
							return;
						case Device_Watchdog_Request:						
							PeerStateMachine.process(this.peer,PeerEvent.I_Rcv_DWR,msg,this);
							return;
						case Device_Watchdog_Answer:
							PeerStateMachine.process(this.peer,PeerEvent.I_Rcv_DWA,msg,this);
							return;
						case Disconnect_Peer_Request:							
							PeerStateMachine.process(this.peer,PeerEvent.I_Rcv_DPR,msg,this);
							return;
						case Disconnect_Peer_Answer:
							PeerStateMachine.process(this.peer,PeerEvent.I_Rcv_DPA,msg,this);
							return;
						default:
							/* faster processing -> no state machine for regular messages */
							//StateMachine.process(this.peer,PeerEvent.I_Rcv_Message,msg,this);
							PeerStateMachine.Rcv_Process(this.peer,msg);
							return;
					}
			}
		}
		
		/* main processing */
		if (msg.type==null){
			/*
			if (this.direction == Initiator) event = PeerEvent.I_Rcv_Message;
			else event = PeerEvent.R_Rcv_Message;
			StateMachine.process(peer,event,msg,this);
			*/
			/* faster processing -> no state machine for regular messages */
			PeerStateMachine.Rcv_Process(this.peer,msg);
		} 
		else
			switch (msg.type){
				case Capabilities_Exchange_Request:	
					/* CER  - Special processing to find the peer */
					/* find peer */
					AVP_Origin_Host fqdn;
					AVP_Origin_Realm realm;
					Peer p;
					fqdn = (AVP_Origin_Host) msg.findAVP(AVP_Type.Origin_Host);
					if (fqdn==null) {
						LOGGER.warning("Communicator: CER Received without Origin-Host");
						return;
					}
					realm = (AVP_Origin_Realm) msg.findAVP(AVP_Type.Origin_Realm);
					if (realm==null) {
						LOGGER.warning("Communicator: CER Received without Origin-Realm");
						return;
					}
					p = diameterPeer.peerManager.getPeerByFQDN(fqdn.get());
					if (p==null) {
						p = diameterPeer.peerManager.addDynamicPeer(fqdn.get(),realm.get());
					}
					if (p==null){
						//Give up
						LOGGER.warning("Communicator: Not Allowed to create new Peer");
						return;
					}
					this.peer = p;
					/* call state machine */	
					PeerStateMachine.process(p,PeerEvent.R_Conn_CER,msg,this);
					break;
					
				case Capabilities_Exchange_Answer:
					/* CEA */
					if (this.peer==null) {
						LOGGER.warning("Receiver: received CEA for an unknown peer");
						LOGGER.warning(msg.toString());
					}else{
						if (this.direction == Initiator) event = PeerEvent.I_Rcv_CEA;
						else event = PeerEvent.R_Rcv_CEA;
						PeerStateMachine.process(this.peer,event,msg,this);
					}
					break;
					
				case Device_Watchdog_Request:						
					if (this.direction == Initiator) event = PeerEvent.I_Rcv_DWR;
					else event = PeerEvent.R_Rcv_DWR;
					PeerStateMachine.process(peer,event,msg,this);
					break;
				
				case Device_Watchdog_Answer:						
					if (this.direction == Initiator) event = PeerEvent.I_Rcv_DWA;
					else event = PeerEvent.R_Rcv_DWA;
					PeerStateMachine.process(peer,event,msg,this);
					break;
				
				case Disconnect_Peer_Request:							
					if (this.direction == Initiator) event = PeerEvent.I_Rcv_DPR;
					else event = PeerEvent.R_Rcv_DPR;
					PeerStateMachine.process(peer,event,msg,this);
					break;
				case Disconnect_Peer_Answer:
					if (this.direction == Initiator) event = PeerEvent.I_Rcv_DPA;
					else event = PeerEvent.R_Rcv_DPA;
					PeerStateMachine.process(peer,event,msg,this);
					break;
				
				default:
					/*
					if (this.direction == Initiator) event = PeerEvent.I_Rcv_Message;
					else event = PeerEvent.R_Rcv_Message;
					StateMachine.process(peer,event,msg,this);
					*/
					/* faster processing -> no state machine for regular messages */
					PeerStateMachine.Rcv_Process(this.peer,msg);
			}
	}
	
	/**
	   On some kind of Exception, forcefully disconnect from the Peer
	   that caused it.
	 */
    private void disconnect(Exception ex) {
		if (running){
			if (this.peer!=null){
				if (this.peer.I_comm==this) 
					PeerStateMachine.process(this.peer,PeerEvent.I_Peer_Disc);
				if (this.peer.R_comm==this) 
					PeerStateMachine.process(this.peer,PeerEvent.R_Peer_Disc);
			}
			LOGGER.fine("Communicator: Error reading from InputStream. Closing socket.");
			if (null != ex) {
				LOGGER.fine(ex.getMessage());
			}
		}/* else it was a shutdown request, it's normal */
	}


	/**
	 * Shutdown the socket.
	 */
	public void shutdown()
	{
		this.running = false;
		try {
			this.socket.close();
		} catch (IOException e) {
			LOGGER.warning("Communicator: Shutdown - error closing socket");
			e.printStackTrace();
		}

	}
}
/**

\package de.fhg.fokus.diameter.DiameterPeer.transport
Contains acceptors and communicators for creating and maintaining connections 
between Diameter peers.

<p>
A DiameterPeer uses an Acceptor to listen to requests from other DiameterPeers. 
If a request is coming in from an undetected DiameterPeer, a Communicator is 
created to maintain this connection. This Communicator will also be associated 
with a Peer in the PeerManager. 
*/

