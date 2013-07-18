package org.fiteagle.delivery.rest.fiteagle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.fiteagle.interactors.api.ResourceMonitoringBoundary;
import orgt.fiteagle.core.monitoring.StatusTable;

import com.google.inject.Inject;

@Path("/v1/status")
public class StatusPresenter {

	private ResourceMonitoringBoundary monitor;

	@Inject
	public StatusPresenter(final ResourceMonitoringBoundary monitor) {
		this.monitor = monitor;
	}

	@GET
	@Path("")
	@Produces("application/json")
	public List<TestbedStatus> getStatus() {
		
		//TODO: map status table 2 testbedstatus 
		
		ArrayList<TestbedStatus> status = new ArrayList<TestbedStatus>(); 
		
		Collection<StatusTable> monitoringData = monitor.getMonitoringData();
		
		for (Iterator iterator = monitoringData.iterator(); iterator.hasNext();) {
			StatusTable statusTable = (StatusTable) iterator.next();
			status.add(statusTable2Testbedstatus(statusTable));
		}
		
//		List<TestbedStatus> dummyList = new ArrayList<>();
//		TestbedStatus fuseco = new TestbedStatus();
//		fuseco.setId("fuseco");
//		fuseco.setStatus("up");
//		fuseco.setLastCheck(Calendar.getInstance().getTime());
//
//		TestbedStatus fiteagle = new TestbedStatus();
//		fiteagle.setId("fiteagle local");
//		fiteagle.setStatus("down");
//		fiteagle.setLastCheck(Calendar.getInstance().getTime());
//		
////		TestbedStatus fiteagle1 = new TestbedStatus();
////		fiteagle1.setId(testData);
////		fiteagle1.setStatus("down");
////		fiteagle1.setLastCheck(Calendar.getInstance().getTime());
//
//		dummyList.add(fuseco);
//		dummyList.add(fiteagle);
////		dummyList.add(fiteagle1);
		return status;

	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public TestbedStatus getTestBedStatusById(@PathParam("id") String dummy) {
		TestbedStatus status = new TestbedStatus();
		status.setId("dummy2");
		status.setStatus("up and away");
		status.setLastCheck(Calendar.getInstance().getTime());
		return status;
	}
	
	private TestbedStatus statusTable2Testbedstatus(StatusTable statusTable){
		TestbedStatus testbedStatus = new TestbedStatus();
		
		testbedStatus.setId(statusTable.getName());
		testbedStatus.setLastCheck(statusTable.getLastCheck());
		testbedStatus.setStatus(statusTable.getStatus());
		
		return testbedStatus;
		
	}

}
