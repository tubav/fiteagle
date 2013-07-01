package org.fiteagle.interactors.monitoring;

import org.fiteagle.interactors.api.ResourceMonitoringBoundary;

public class MonitoringManager implements ResourceMonitoringBoundary {

	// TODO: save the information in first time in a list (static!) if the list
	// is empty then get information using xipi client!! => maybe extend pom to get xipi client

	@Override
	public String getMonitoringData() {
		return "";
	}
	
	String getXIPIMonitoringData(){
//		InfinityClient client = new Infini
		return "";
	}

}
