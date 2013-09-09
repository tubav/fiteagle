package org.fiteagle.adapter.qoSControl.tmp;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetQoSStatusResponse {

	Set<QoSStatus> qosStatusSet;
	
	public GetQoSStatusResponse() {
		
	}

	public Set<QoSStatus> getQosStatusSet() {
		return qosStatusSet;
	}

	public void setQosStatusSet(Set<QoSStatus> qosStatusSet) {
		this.qosStatusSet = qosStatusSet;
	}
	
	
}
