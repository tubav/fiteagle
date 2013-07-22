package org.fiteagle.interactors.monitoring;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.fiteagle.interactors.api.ResourceMonitoringBoundary;
import org.fiteagle.interactors.monitoring.server.OMLServer;
import org.fiteagle.ui.infinity.InfinityClientMock;
import org.fiteagle.ui.infinity.model.InfinityInfrastructure;
import org.fiteagle.ui.infinity.model.InfinityValueID;
import orgt.fiteagle.core.monitoring.StatusTable;

public class MonitoringManager implements ResourceMonitoringBoundary {

	// TODO: save the information in first time in a list (static!) if the list
	// is empty then get information using xipi client!! => maybe extend pom to get xipi client

	private static HashMap<String, StatusTable> monitoringData = new HashMap<String, StatusTable>();
	
	private static boolean serverStarted = false;
	private static OMLServer omlServer = new OMLServer();
	
	public MonitoringManager() {
		if(!serverStarted){
			new Thread(omlServer).start();
			serverStarted=true;
		}
	}
	
	public void terminateOMLServer(){
		omlServer.terminate();
	}
	
	@Override
	public Collection<StatusTable> getMonitoringData() {
		if(monitoringData.isEmpty()){
			List<StatusTable> data = getXIPIMonitoringData();
			//TODO: parse this data and store it in monitoringData hashmap
			addMonitoringData(data);
		}
		//TODO: give monitoring data as html parts?? delivery should not know the interactor side!
//		ArrayList<StatusTable> result = (ArrayList<StatusTable>) monitoringData.values();
		return monitoringData.values();
	}
	
	private void addMonitoringData(List<StatusTable> data) {
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			StatusTable statusTable = (StatusTable) iterator.next();
//			Date lastCheck=Calendar.getInstance().getTime();
//			statusTable.setLastCheck(lastCheck);
			monitoringData.put(statusTable.getId(), statusTable);
		}
	}

	List<StatusTable> getXIPIMonitoringData(){
		//TODO: return value must be internal monitoring data structure. InfinityValueID must be mapped to this.
		InfinityClientMock client = new InfinityClientMock();
		ArrayList<InfinityValueID> infrastructures = client.searchInfrastructures();
		
		ArrayList<StatusTable> result = new ArrayList<StatusTable>();
		
		
		if (infrastructures == null) return null;
		
		for (Iterator iterator = infrastructures.iterator(); iterator.hasNext();) {
			InfinityValueID infinityValueID = (InfinityValueID) iterator.next();
			
			StatusTable statusTable = new StatusTable();
			
			String id = infinityValueID.getId();
			statusTable.setXipiId(id);
			
//			Integer intId = new Integer(id);
//			InfinityInfrastructure infrastructure = client.getInfrastructuresById(intId);
			
//			String name = infrastructure.getName_();
//			statusTable.setName(name);
//			
//			Number idInteger = infrastructure.getId();
//			String idString = new Integer(idInteger.intValue()).toString();
//			statusTable.setId(idString);
//			
//			String status = infrastructure.getStatus();
//			if(status.trim().compareTo("")==0)
//				status = "status unknown";
//			statusTable.setStatus(status);
//			
//			String organization = infrastructure.getOrganization();
//			statusTable.setOrganization(organization);
			
			statusTable.setId(infinityValueID.getValue());
			statusTable.setLastCheck(null);
			
			result.add(statusTable);
		}
		//TODO: while parsing, get the ids and call the getbyid, get name and status, if status empty => unknown status.
//		make enum for status staff.
		return result;
	}
	
	public void pushMonitoringData(StatusTable statusTable){
		//TODO: over an interface available for individual testbeds??
		monitoringData.put(statusTable.getId(), statusTable);
	}

}
