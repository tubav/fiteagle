package de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf;

import java.util.Vector;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.basic.AVP_Grouped;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;

public class AVP_IMS_Information extends AVP_Grouped{
	public AVP_IMS_Information(byte[] data) throws AVPDecodeException 
	{
		super(AVP_Type.IMS_Information,data);
	}
	
	public AVP_IMS_Information(AVP[] data)
	{
		super(AVP_Type.IMS_Information,data);
	}

	public AVP_IMS_Information(Vector<AVP> data)
	{
		super(AVP_Type.IMS_Information,data);
	}
}
