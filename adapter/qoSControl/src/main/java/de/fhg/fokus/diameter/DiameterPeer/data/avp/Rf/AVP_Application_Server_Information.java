package de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf;

import java.io.UnsupportedEncodingException;
import java.util.Vector;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.basic.AVP_Grouped;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;

public class AVP_Application_Server_Information extends AVP_Grouped {
	public AVP_Application_Server_Information(byte[] data) throws AVPDecodeException  
	{
		super(AVP_Type.Application_Server_Information, data);
	}	
	
	public AVP_Application_Server_Information(AVP[] data) throws UnsupportedEncodingException 
	{
		super(AVP_Type.Application_Server_Information, data);
	}	

	public AVP_Application_Server_Information(Vector<AVP> data)
	{
		super(AVP_Type.Application_Server_Information, data);
	}
}
