package de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf;

import java.util.Vector;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.basic.AVP_Grouped;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;

public class AVP_Service_Specific_Info extends AVP_Grouped {
	public AVP_Service_Specific_Info(byte[] data) throws AVPDecodeException 
	{
		super(AVP_Type.Service_Specific_Info, data);
	}
	
	public AVP_Service_Specific_Info(AVP[] data)
	{
		super(AVP_Type.Service_Specific_Info, data);
	}

	public AVP_Service_Specific_Info(Vector<AVP> data)
	{
		super(AVP_Type.Service_Specific_Info, data);
	}
}
