package de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.basic.AVP_Integer32;

public class AVP_Cause_Code extends AVP_Integer32 {

    public AVP_Cause_Code(int data) {
	super(AVP_Type.Cause_Code, data);
	// TODO Auto-generated constructor stub
    }

}
