package de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf;

import de.fhg.fokus.diameter.DiameterPeer.data.Application_Id_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVP_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.derived.AVP_Enumerated;

public class AVP_Node_Functionality extends AVP_Enumerated {

    public AVP_Node_Functionality(int data) {
	super(AVP_Type.Node_Functionality, data);
	// TODO Auto-generated constructor stub
    }

    public String toString() {
	String x = super.toString();
	long value = this.get();
	for (Application_Id_Enum e : Application_Id_Enum.values())
	    if (e.value == value)
		return x + " " + e.toString();
	return x;
    }

}
