package org.fiteagle.interactors.monitoring;

import java.net.URISyntaxException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import orgt.fiteagle.core.monitoring.StatusTable;

public class MonitoringManagerTest {

	private MonitoringManager monitoringManager;

	@Before
	public void setup() throws URISyntaxException {
		this.monitoringManager = new MonitoringManager();
	}
	
	@Test
	public void testGetXIPIMonitoringData() throws URISyntaxException {
//		List<StatusTable> result = monitoringManager.getXIPIMonitoringData();
//		Assert.assertNotNull(result);
		
	}

	@Test
	public void getMonitoringDataById(){
//		monitoringManager.getXIPIMonitoringData();
//		StatusTable statusTable = new StatusTable();
//		statusTable.setId("FOKUS FUSECO Playground");
//		statusTable.setLastCheck(new Date());
//		statusTable.setStatus("up");
//		StatusTable component= new StatusTable();
//		component.setId("componentId");
//		component.setStatus("up");
//		component.setLastCheck(new Date());
//		statusTable.addComponent(component);
//		
//		monitoringManager.pushMonitoringData(statusTable);
//		StatusTable data = monitoringManager.getMonitoringDataById("FOKUS FUSECO Playground");
//		Assert.assertNull(data);
//		Assert.assertEquals("FOKUS FUSECO Playground", data.getId());
//		Assert.assertEquals("up", data.getStatus());
	}
	
}
