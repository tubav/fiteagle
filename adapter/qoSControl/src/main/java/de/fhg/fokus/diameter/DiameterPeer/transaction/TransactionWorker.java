/*
 * $Id: TransactionWorker.java 3461 2011-03-29 14:09:46Z lal $
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

package de.fhg.fokus.diameter.DiameterPeer.transaction;

import java.util.Vector;
import java.util.concurrent.Semaphore;

import de.fhg.fokus.diameter.DiameterPeer.DiameterPeer;
import de.fhg.fokus.diameter.DiameterPeer.MessageListener;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Answer;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Request;

/**
 * A TansactionWorker maintains a set of Diameter transactions for 
 * a Diameter Client.
 * 
 * @author Dragos Vingarzan vingarzan -at- fokus dot fraunhofer dot de
 */
public class TransactionWorker extends Thread implements MessageListener{
	
	
	private DiameterPeer dp;
	
	private Vector<Transaction> transactions = new Vector<Transaction>();
	
	private long timeout = 30000;
	private long checkInterval = 1000;
	private boolean running = true;
	
	/**
	 * Construct a TransactionWorker.
	 * 
	 * @param dp			DiameterPeer creating the worker. 
	 * @param timeout		Transaction timeout.
	 * @param checkInterval	
	 */
	public TransactionWorker(DiameterPeer dp, long timeout,long checkInterval)
	{
		this.dp = dp;
		dp.addEventListener(this);
		this.timeout = timeout *1000;
		this.checkInterval = checkInterval*1000;
		this.start();
	}

	private synchronized Transaction addTransaction(Message req,TransactionListener tl,boolean blocking)
	{
		Transaction dt = getTransaction(req);
		if (dt!=null) return dt;
		dt = new Transaction(req,tl,blocking);
		transactions.add(dt);
		return dt;
	}

	private synchronized Transaction takeTransaction(Message ans)
	{
		int i;
		Transaction dt;
		for(i=0;i<transactions.size();i++){
			dt = transactions.get(i);
			if (dt.request.endToEndID == ans.endToEndID &&
				dt.request.hopByHopID == ans.hopByHopID){
					transactions.remove(i);
					return dt;
			}
		}
		return null;
	}
	
	private synchronized Transaction getTransaction(Message msg)
	{
		int i;
		Transaction dt;
		for(i=0;i<transactions.size();i++){
			dt = transactions.get(i);
			if (dt.request.endToEndID == msg.endToEndID &&
				dt.request.hopByHopID == msg.hopByHopID)
					return dt;
		}
		return null;
	}
	
	private synchronized void timeoutExpired(long now)
	{
		int i;
		Transaction dt;
		for(i=0;i<transactions.size();i++){
			dt = transactions.get(i);
			if (dt.expires<=now){
				if (dt.tl!=null) dt.tl.timeout(dt.request);
//				synchronized(dt) {
					dt.release();
//					dt.notifyAll();
//			}
				transactions.remove(i);
				i--;
			}
		}
		
	}
	
	/**
	 * Sends a Diameter request.  
	 * 
	 * @param peerFQDN	Destination host of peer.
	 * @param req		Diameter request to be sent.
	 * @param tl		TransactionListener that handles the corresponding 
	 * 					Diameter answer within a transaction.
	 * @return			true if Diameter request sent successfully.
	 */
	public boolean sendRequestTransactional(String peerFQDN,Message req,TransactionListener tl)
	{
		boolean sent=false;
		Transaction dt;
		if (!req.flagRequest) {
			System.err.println("DiameterTransaction:sendMessageTransactional() is only for Requests!");
			return false;
		}
		dt = addTransaction(req,tl,false);
		sent = dp.sendMessage(peerFQDN,req);
		if (!sent){
			takeTransaction(req);
			return false;
		}
		dt.expires = req.networkTime + this.timeout;

		return sent;
	}

	/**
	 * Sends a Diameter request.  
	 * 
	 * @param req		Diameter request to be sent.
	 * @param tl		TransactionListener that handles the corresponding 
	 * 					Diameter answer within a transaction.
	 * @return			true if Diameter request sent successfully.
	 */
	public boolean sendRequestTransactional(Message req,TransactionListener tl)
	{
		boolean sent=false;
		Transaction dt;
		if (!req.flagRequest) {
			System.err.println("DiameterTransaction:sendMessageTransactional() is only for Requests!");
			return false;
		}
		dt = addTransaction(req,tl,false);
		sent = dp.sendMessage(req);
		if (!sent){
			takeTransaction(req);
			return false;
		}
		dt.expires = req.networkTime + this.timeout;

		return sent;
	}
	
	
	/**
	 * Sends a Diameter request and blocks the thread until a Diameter answer 
	 * returned or timeout.
	 * 
	 * @param peerFQDN	Destination host of peer.
	 * @param req		Diameter request to be sent.	
	 * @return Diameter answer returned within the transaction; null if timeout.
	 */
	public Message sendRequestBlocking(String peerFQDN,Message req)
	{
		boolean sent;
		Transaction dt;
		if (!req.flagRequest) {
			System.err.println("DiameterTransaction:sendMessageBlocking() is only for Requests!");
			return null;
		}
		dt = addTransaction(req,null,true);
		sent = dp.sendMessage(peerFQDN,req);
		if (!sent){
			takeTransaction(req);
			return null;
		}
		dt.expires = req.networkTime + this.timeout;
		try {
//			synchronized(dt) {
			dt.acquire();	
//			dt.wait();
//			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return dt.answer;
	}	

	/**
	 * Sends a Diameter request and blocks the thread until a Diameter answer 
	 * returned or timeout.
	 * 
	 * @param req		Diameter request to be sent.	
	 * @return Diameter answer returned within the transaction; null if timeout.
	 */
	public Message sendRequestBlocking(Message req)
	{
		boolean sent;
		Transaction dt;
		if (!req.flagRequest) {
			System.err.println("DiameterTransaction:sendMessageBlocking() is only for Requests!");
			return null;
		}
		dt = addTransaction(req,null,true);
		sent = dp.sendMessage(req);
		if (!sent){
			takeTransaction(req);
			return null;
		}
		dt.expires = req.networkTime + this.timeout;
		try {
	//		synchronized(dt) {
			dt.acquire();	
		//	dt.wait();
		//s	}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return dt.answer;
	}	
	
	/**
	 * Handles the received Diameter answers. They will be handled by 
	 * corresponding TransactionListeners. 
	 * 
	 * @param FQDN 	FQDN of the origin host. 
	 * @param msg 	Received Diameter message.
	 */
	public void recvAnswer(String FQDN, Answer msg) 
	{
		if (msg.flagRequest) return;
		Transaction dt;	
		dt = takeTransaction(msg);
		if (dt==null) return;
		dt.answer = msg;
//		synchronized(dt) {
			dt.release();
//			dt.notifyAll();
//		}
		if (dt.tl!=null) dt.tl.receiveAnswer(FQDN,dt.request,msg);
	}

	public void recvRequest(String FQDN, Request req) {
		
	}
	
	/**
	 * Trigger transaction timeout event.
	 */
	public void run()
	{
		while(running){
			timeoutExpired(System.currentTimeMillis());
			try {
				Thread.sleep(checkInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Shutdown the TransactionLister.
	 *
	 */
	public void shutdown()
	{
		this.running = false;
	}

}
