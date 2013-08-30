package de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf;

import java.io.UnsupportedEncodingException;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.derived.AVP_UTF8String;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;

public class AVP_IMS_Charging_Identifier extends AVP_UTF8String{
	public AVP_IMS_Charging_Identifier(byte[] data) throws AVPDecodeException  
	{
		super(AVP_Type.IMS_Charging_Identifier, data);
	}	
	
	public AVP_IMS_Charging_Identifier(String data) throws UnsupportedEncodingException 
	{
		super(AVP_Type.IMS_Charging_Identifier, data);
	}
}
