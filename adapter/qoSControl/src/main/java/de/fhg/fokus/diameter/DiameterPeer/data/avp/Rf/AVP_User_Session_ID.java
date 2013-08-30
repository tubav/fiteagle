package de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf;

import java.io.UnsupportedEncodingException;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.derived.AVP_UTF8String;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;

public class AVP_User_Session_ID extends AVP_UTF8String {
	public AVP_User_Session_ID(byte[] data) throws AVPDecodeException  
	{
		super(AVP_Type.User_Session_ID, data);
	}	
	
	public AVP_User_Session_ID(String data) throws UnsupportedEncodingException 
	{
		super(AVP_Type.User_Session_ID, data);
	}	
}
