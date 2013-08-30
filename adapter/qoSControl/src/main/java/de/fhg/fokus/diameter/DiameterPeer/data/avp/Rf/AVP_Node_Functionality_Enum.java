package de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf;

public enum AVP_Node_Functionality_Enum {

    S_CSCF(0), P_CSCF(1), I_CSCF(2), MRFC(3), MGCF(4), BBCF(5), AS(6), IBCF(7), S_GW(8), P_GW(9), GSGW(10);

    public final AVP_Node_Functionality avp;
    public final int value;

    private AVP_Node_Functionality_Enum(int data) {
	this.avp = new AVP_Node_Functionality(data);
	this.value = data;
    }

}
