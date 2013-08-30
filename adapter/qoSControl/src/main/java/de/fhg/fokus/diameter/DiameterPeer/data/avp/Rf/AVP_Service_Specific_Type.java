package de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf;

import java.io.UnsupportedEncodingException;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.basic.AVP_Unsigned32;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;

public class AVP_Service_Specific_Type extends AVP_Unsigned32 {
	
	public AVP_Service_Specific_Type(byte[] data) throws UnsupportedEncodingException, AVPDecodeException 
	{
		super(AVP_Type.Service_Specific_Type, data);
	}
	
	public AVP_Service_Specific_Type(long data) throws AVPDataTypeException 
	{
		super(AVP_Type.Service_Specific_Type, data);
	}
}
