/*
 * $Id: DiameterPeerTest.java 1970 2010-07-19 15:54:08Z dvi $
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

package de.fhg.fokus.diameter.DiameterPeer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import de.fhg.fokus.diameter.DiameterPeer.data.Application_Id_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.Vendor_Id_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Session_State_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Error_Message;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_User_Name;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Specific_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.basic.AVP_OctetString;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.derived.AVP_UTF8String;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Answer;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Message;
import de.fhg.fokus.diameter.DiameterPeer.data.message.Request;
import de.fhg.fokus.diameter.DiameterPeer.session.Session;
import de.fhg.fokus.diameter.DiameterPeer.transaction.TransactionListener;



/**
 * This class tests the DiameterPeer for correct behaviour
 * 
 * @author Dragos Vingarzan vingarzan -at- fokus dot fraunhofer dot de
 *
 */
public class DiameterPeerTest {
	
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger(DiameterPeerTest.class.toString());

	public static Message UAR(DiameterPeer dp)
	{
		Message uar = dp.newRequest(300,16777216);
		
		try {
		
			/*session-id*/
			Session session = dp.sessionManager.createSession();		
			uar.addAVP(session.getAVP());
			/*destination-host*/
			uar.addAVP(new AVP_Destination_Host("localhost"));
			/*destination-realm*/
			uar.addAVP(new AVP_Destination_Realm("open-ims.test"));
			/*vendor-specific app id */
			uar.addAVP(new AVP_Vendor_Specific_Application_Id(new AVP[] {
					new AVP_Vendor_Id(Vendor_Id_Enum.TGPP.value),
					new AVP_Auth_Application_Id(Application_Id_Enum.Cx.value)
			}));
			/*auth-session-state*/
			uar.addAVP(AVP_Auth_Session_State_Enum.NO_STATE_MAINTAINED.avp);
			/*user-name*/		
			uar.addAVP(new AVP_User_Name("alice@open-ims.test"));
			/*public-id*/		
			uar.addAVP(new AVP_UTF8String(601,true,10415,"sip:alice@open-ims.test"));
			/*visited-network-id*/
			uar.addAVP(new AVP_OctetString(600,true,10415,"open-ims.test".getBytes()));
		} catch (AVPDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uar;
	}
	
	
	public static String readConfigFile(String filename)
	{
		StringBuffer sb = new StringBuffer();
	    try {
	        BufferedReader in = new BufferedReader(new FileReader(filename));
	        String str;
	        while ((str = in.readLine()) != null) {
	            sb.append(str);
	        }
	        in.close();
	    } catch (Exception e1) {
	    	e1.printStackTrace();
			return "";
	    }
	    return sb.toString();
	}
	
	public static void test(String[] args) throws InterruptedException {
		
		if (args.length != 2) {
			LOGGER.severe("Provide two XML config files as input");
			System.exit(0);
		} else {
			LOGGER.info("DiameterPeer Starting...");
			
			
			// Create a Diameter server.
			String filename2 = args[1];
			LOGGER.fine("Opening Config file: "+filename2);
			String xml = readConfigFile(filename2);
			DiameterPeer dp2 = new DiameterPeer(xml);
			TestEventListener eventlistener = new TestEventListener();
			eventlistener.diameterPeer = dp2;
			dp2.addEventListener(eventlistener);

			setAllLoggers(Level.ALL);

			Thread.sleep(2000);

			// Create a Diameter client.
			String filename = args[0];
			LOGGER.fine("Opening Config file: "+filename);
			xml = readConfigFile(filename);			
			DiameterPeer dp1 = new DiameterPeer(xml);
			dp1.enableTransactions(10,1);

			
			Thread.sleep(2000);
			Message uar = UAR(dp1);
			
			Message uaa = dp1.sendRequestBlocking("localhost",uar);
			if (uaa==null) LOGGER.fine("SendBlocking timed-out");
			else LOGGER.fine("SendBlocking answer: "+uaa.toString());
	
			Thread.sleep(600*1000);
		
			dp1.shutdown();
			Thread.sleep(10000);
			LOGGER.fine("... DiameterPeer Done");
		}
	}	
	
	

	public static void setAllLoggers(Level level)
	{
		Handler[] handlers = Logger.getLogger( "" ).getHandlers();
		 for ( int index = 0; index < handlers.length; index++ ){
			 handlers[index].setLevel( level );
			 handlers[index].setFormatter(new TestFormater());
		}
		LOGGER.setLevel(level);				
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		setAllLoggers(Level.ALL);
		
		
		test(args);
	}
}

class TestEventListener implements MessageListener{

	DiameterPeer diameterPeer;
	
	public void recvRequest(String FQDN, Request req) {
		int i;
		Answer response;
		response = diameterPeer.newAnswer(req);		
		AVP a=null;
		try {
			a = new AVP_Error_Message("HelloWorld!");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(i=0;i<10;i++){
			response.addAVP(a);
		}
		diameterPeer.sendMessage(FQDN,response);
	}

	public void recvAnswer(String FQDN, Answer ans) {
		
		
	}
}

class TestTransactionListener implements TransactionListener{

	/** The logger */
	private final Logger LOGGER = Logger.getLogger(DiameterPeerTest.class.toString());
	
	DiameterPeer diameterPeer;

	public void receiveAnswer(String FQDN, Message request, Message answer) {
		LOGGER.fine("Transaction received an answer: "+answer.toString());		
	}

	public void timeout(Message request) {
		LOGGER.fine("Transaction received an timeout");
	}

}

class TestFormater extends SimpleFormatter
{
    Date dat = new Date();
    private final static String format = "{0,date} {0,time}";
    private MessageFormat formatter;

    private Object args[] = new Object[1];

    // Line separator string.  This is the value of the line.separator
    // property at the moment that the SimpleFormatter was created.
    private String lineSeparator = "\n";

    public synchronized String format(LogRecord record) {
    	StringBuffer sb = new StringBuffer();
    	// Minimize memory allocations here.
    	dat.setTime(record.getMillis());
    	args[0] = dat;
    	StringBuffer text = new StringBuffer();
    	if (formatter == null) {
    	    formatter = new MessageFormat(format);
    	}
    	formatter.format(args, text, null);
    	sb.append(text);
    	sb.append(" ");
    	if (record.getSourceClassName() != null) {	
    	    sb.append(record.getSourceClassName());
    	} else {
    	    sb.append(record.getLoggerName());
    	}
    	if (record.getSourceMethodName() != null) {	
    	    sb.append(" ");
    	    sb.append(record.getSourceMethodName());
    	}
    	//sb.append(lineSeparator);
    	sb.append("\t");
    	String message = formatMessage(record);
    	sb.append(record.getLevel().getLocalizedName());
    	sb.append(": ");
    	sb.append(message);
    	sb.append(lineSeparator);
    	if (record.getThrown() != null) {
    	    try {
    	        StringWriter sw = new StringWriter();
    	        PrintWriter pw = new PrintWriter(sw);
    	        record.getThrown().printStackTrace(pw);
    	        pw.close();
    		sb.append(sw.toString());
    	    } catch (Exception ex) {
    	    }
    	}
    	return sb.toString();
        }

}