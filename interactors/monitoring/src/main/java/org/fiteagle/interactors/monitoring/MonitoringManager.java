package org.fiteagle.interactors.monitoring;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.fiteagle.interactors.api.ResourceMonitoringBoundary;
import org.fiteagle.interactors.monitoring.server.OMLServer;
import org.fiteagle.ui.infinity.InfinityClient;
import org.fiteagle.ui.infinity.InfinityClientWeb;
import org.fiteagle.ui.infinity.model.InfinityInfrastructure;
import org.fiteagle.ui.infinity.model.InfinityValueID;
import orgt.fiteagle.core.monitoring.StatusTable;

public class MonitoringManager implements ResourceMonitoringBoundary {
	
	private static HashMap<String, StatusTable> monitoringData = new HashMap<String, StatusTable>();
	
	private static boolean serverStarted = false;
	private static OMLServer omlServer = new OMLServer();
	private String xipiUriString="http://www.xipi.eu";
//	InfinityClientMock client = new InfinityClientMock();
	InfinityClient client;
	
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
			List<StatusTable> data;
			try {
				data = getXIPIMonitoringData();
			} catch (URISyntaxException e) {
				throw new RuntimeException(e.getMessage());
			}
			addMonitoringData(data);
		}
		return monitoringData.values();
	}
	
	private void addMonitoringData(List<StatusTable> data) {
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			StatusTable statusTable = (StatusTable) iterator.next();
			monitoringData.put(statusTable.getId(), statusTable);
		}
	}

	List<StatusTable> getXIPIMonitoringData() throws URISyntaxException{
		
		client = new InfinityClientWeb(new URI(xipiUriString));
//		client = new InfinityClientMock();
		
		
		ArrayList<InfinityValueID> infrastructures = client.searchInfrastructures();
		
		ArrayList<StatusTable> result = new ArrayList<StatusTable>();
		
		
		if (infrastructures == null) return null;
		
		for (Iterator iterator = infrastructures.iterator(); iterator.hasNext();) {
			InfinityValueID infinityValueID = (InfinityValueID) iterator.next();
			
			StatusTable statusTable = new StatusTable();
			
			String id = infinityValueID.getId();
			statusTable.setXipiId(id);
			statusTable.setId(infinityValueID.getValue());
			
			InfinityInfrastructure infinityInfrastructure = getInfrastuctureByID(new Integer(id));
			
			if(infinityInfrastructure.getStatus() != null && infinityInfrastructure.getStatus()!=""){
				//TODO: how does status look like in json(if it is not empty) from XIPI? There is no example
				//testbed with state information from XIPI response. Get this information and map this to 
				//status table states.
			}else
				statusTable.setStatus(StatusTable.UNDEFINED);
			
			result.add(statusTable);
		}
		
		return result;
	}
	
	private InfinityInfrastructure getInfrastuctureByID(Number id) {
		return client.getInfrastructuresById(id);
	}

	public void pushMonitoringData(StatusTable statusTable){
		if(monitoringData.get(statusTable.getId())!=null)
		monitoringData.put(statusTable.getId(), statusTable);
	}
	
	@Override
	public StatusTable getMonitoringDataById(String id) {
		return monitoringData.get(id);
	}

}
