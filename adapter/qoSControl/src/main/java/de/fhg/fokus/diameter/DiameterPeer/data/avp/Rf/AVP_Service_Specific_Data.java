package de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf;

import java.io.UnsupportedEncodingException;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.derived.AVP_UTF8String;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;

public class AVP_Service_Specific_Data extends AVP_UTF8String {
	public AVP_Service_Specific_Data(byte[] data) throws AVPDecodeException  
	{
		super(AVP_Type.Service_Specific_Data, data);
	}	
	
	public AVP_Service_Specific_Data(String data) throws UnsupportedEncodingException 
	{
		super(AVP_Type.Service_Specific_Data, data);
	}
}
