package de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf;

import java.io.UnsupportedEncodingException;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.derived.AVP_UTF8String;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;

public class AVP_Application_Provided_Called_Party_Address extends AVP_UTF8String {
	public AVP_Application_Provided_Called_Party_Address(byte[] data) throws AVPDecodeException  
	{
		super(AVP_Type.Application_Provided_Called_Party_Address, data);
	}	
	
	public AVP_Application_Provided_Called_Party_Address(String data) throws UnsupportedEncodingException 
	{
		super(AVP_Type.Application_Provided_Called_Party_Address, data);
	}
}
