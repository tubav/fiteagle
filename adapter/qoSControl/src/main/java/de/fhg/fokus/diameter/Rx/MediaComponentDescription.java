package de.fhg.fokus.diameter.Rx;

import de.fhg.fokus.diameter.DiameterPeer.data.avp.AVPDataTypeException;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Gq.AVP_Reservation_Priority;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Gq.AVP_Reservation_Priority_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_AF_Application_Identifier;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Flow_Description;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Flow_Number;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Flow_Status_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Flows;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Max_Requested_Bandwidth_DL;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Max_Requested_Bandwidth_UL;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Media_Component_Description;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Media_Component_Number;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Media_Sub_Component;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Media_Type_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.derived.filterrule.Direction;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.derived.filterrule.IPFilterAction;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.derived.filterrule.IPFilterRule;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MediaComponentDescription
{
  private AVP_Media_Component_Number ordinalNumber;
  private AVP_AF_Application_Identifier afApplicationIdentifier;
  private AVP_Media_Type_Enum mediaType = AVP_Media_Type_Enum.APPLICATION;
  private AVP_Flow_Status_Enum flowStatus = AVP_Flow_Status_Enum.ENABLED;
  private AVP_Max_Requested_Bandwidth_DL maxDownloadBandwidth;
  private AVP_Max_Requested_Bandwidth_UL maxUploadBandwidth;
  private AVP_Reservation_Priority avp_reservationPriority;
  private String source;
  private String destination;
  private String source_port ="";
  private String destination_port="";
  private String protocol= "ip";
  private String direction="both";
  private Logger log = LoggerFactory.getLogger(this.getClass());
  
  
  public MediaComponentDescription(long ordinalNumber)
  {
    try
    {
      this.ordinalNumber = new AVP_Media_Component_Number(ordinalNumber);
      this.avp_reservationPriority = new AVP_Reservation_Priority(AVP_Reservation_Priority_Enum.PRIORITY_ONE.value);
    } catch (AVPDataTypeException e) {
      try {
        this.ordinalNumber = new AVP_Media_Component_Number(0L);
      }
      catch (AVPDataTypeException localAVPDataTypeException1) {
      }
    }
  }

  public String getMaxDownloadBandwidth() {
    return String.valueOf(this.maxDownloadBandwidth.get());
  }

  public void setMaxDownloadBandwidth(String maxDownloadBandwidth) {
    try {
      this.maxDownloadBandwidth = new AVP_Max_Requested_Bandwidth_DL(Long.parseLong(maxDownloadBandwidth));
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (AVPDataTypeException e) {
      e.printStackTrace();
    }
  }

  public String getMaxUploadBandwidth() {
    return String.valueOf(this.maxUploadBandwidth.get());
  }

  public void setMaxUploadBandwidth(String maxUploadBandwidth) {
    try {
      this.maxUploadBandwidth = new AVP_Max_Requested_Bandwidth_UL(Long.parseLong(maxUploadBandwidth));
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (AVPDataTypeException e) {
      e.printStackTrace();
    }
  }

  protected AVP_Media_Component_Description toAvp() {
    Vector avps = new Vector();

    avps.add(this.ordinalNumber);

    avps.add(avp_reservationPriority);
    if (this.afApplicationIdentifier != null) {
      avps.add(this.afApplicationIdentifier);
    }

    avps.add(this.mediaType.avp);
    avps.add(this.flowStatus.avp);

    if (this.maxDownloadBandwidth != null) {
	      avps.add(this.maxDownloadBandwidth);
	    }

	    if (this.maxUploadBandwidth != null) {
	      avps.add(this.maxUploadBandwidth);
	    }
    
    AVP_Media_Component_Description description =  new AVP_Media_Component_Description(avps);
    if(this.source != null && this.destination != null && this.destination_port != null){
	    Vector subAvps = new Vector();
	    AVP_Flow_Description flowDescriptionIn=null;
	    AVP_Flow_Description flowDescriptionOut=null;
		try {
			if(direction.equals("both")){
			 if(source_port != null && !source_port.equals("")){
				flowDescriptionIn  = new AVP_Flow_Description(IPFilterAction.PERMIT.value+" "+Direction.IN.value +" "+ protocol+" from " + source + " " + source_port +  " to "+  destination + " " + destination_port);
				flowDescriptionOut  = new AVP_Flow_Description(IPFilterAction.PERMIT.value +" "+ Direction.OUT.value +" "+ protocol+" from " + destination + " " + destination_port +  " to "+  source + " " + source_port );
			 }else{
				  flowDescriptionIn = new AVP_Flow_Description(IPFilterAction.PERMIT.value+" "+Direction.IN.value +" "+ protocol+" from " + source + " to "+  destination + " " + destination_port);
				  flowDescriptionOut = new AVP_Flow_Description(IPFilterAction.PERMIT.value +" "+ Direction.OUT.value +" "+ protocol+" from " + destination + " " + destination_port +  " to "+  source );
			 }
			}if(direction.equals("in")){
				 if(source_port != null && !source_port.equals("")){
						flowDescriptionIn  = new AVP_Flow_Description(IPFilterAction.PERMIT.value+" "+Direction.IN.value +" "+ protocol+" from " + source + " " + source_port +  " to "+  destination + " " + destination_port);
					 }else{
						  flowDescriptionIn = new AVP_Flow_Description(IPFilterAction.PERMIT.value+" "+Direction.IN.value +" "+ protocol+" from " + source + " to "+  destination + " " + destination_port);
					 }
			}
			if(direction.equals("out")){
			 if(source_port != null && !source_port.equals("")){
					flowDescriptionOut  = new AVP_Flow_Description(IPFilterAction.PERMIT.value+" "+Direction.OUT.value +" "+ protocol+" from " + source + " " + source_port +  " to "+  destination + " " + destination_port);
				 }else{
					  flowDescriptionOut = new AVP_Flow_Description(IPFilterAction.PERMIT.value+" "+Direction.OUT.value +" "+ protocol+" from " + source + " to "+  destination + " " + destination_port);
				 }
		}
			 AVP_Flow_Number flowNumber = new AVP_Flow_Number(ordinalNumber.getRaw());
			 subAvps.add(flowNumber);
			if(flowDescriptionIn!= null)
				subAvps.add(flowDescriptionIn);
			
			if(flowDescriptionOut != null)
				subAvps.add(flowDescriptionOut);
			 if (this.maxDownloadBandwidth != null) {
			      subAvps.add(this.maxDownloadBandwidth);
			    }
	
			    if (this.maxUploadBandwidth != null) {
			      subAvps.add(this.maxUploadBandwidth);
			    }
			 AVP_Media_Sub_Component mediaSubcomponent = new AVP_Media_Sub_Component(subAvps);
			 description.addChildAVP(mediaSubcomponent);
		} catch (AVPDecodeException e) {
			log.error(e.getMessage(),e);
			
		}
    }
    return description;
  }

  public void setMediaType(AVP_Media_Type_Enum mediaType) {
    this.mediaType = mediaType;
  }

  public AVP_Media_Type_Enum getMediaType() {
    return this.mediaType;
  }

  public void setFlowStatus(AVP_Flow_Status_Enum status) {
    this.flowStatus = status;
  }

  public AVP_Flow_Status_Enum getFlowStatus() {
    return this.flowStatus;
  }

  protected void update(AVP_Media_Component_Description avp)
  {
  }

  public void setAFApplicationIdentifier(byte[] applicationIdentifier) {
    this.afApplicationIdentifier = new AVP_AF_Application_Identifier(applicationIdentifier);
  }

public String getSource() {
	return source;
}

public void setSource(String source) {
	this.source = source;
}

public String getDestination() {
	return destination;
}

public void setDestination(String destination) {
	this.destination = destination;
}

public String getSource_port() {
	return source_port;
}

public void setSource_port(String source_port) {
	this.source_port = source_port;
}

public String getDestination_port() {
	return destination_port;
}

public void setDestination_port(String destination_port) {
	this.destination_port = destination_port;
}

public String getProtocol() {
	return protocol;
}

public void setProtocol(String protocol) {
	this.protocol = protocol;
}


public String getMediaIdentifier(){
	String mediaId = null;
	mediaId = String.valueOf(ordinalNumber.get());
	return mediaId;
}

public String getDirection() {
	return direction;
}

public void setDirection(String direction) {
	this.direction = direction;
}



}