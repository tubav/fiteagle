package de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf;

import java.io.UnsupportedEncodingException;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.derived.AVP_UTF8String;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;

public class AVP_Application_Server extends AVP_UTF8String {
	public AVP_Application_Server(byte[] data) throws AVPDecodeException  
	{
		super(AVP_Type.Application_Server, data);
	}	
	
	public AVP_Application_Server(String data) throws UnsupportedEncodingException 
	{
		super(AVP_Type.Application_Server, data);
	}	
}
