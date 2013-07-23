package org.fiteagle.interactors.monitoring;

import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import org.fiteagle.ui.infinity.InfinityClient;
import org.fiteagle.ui.infinity.InfinityClientMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import orgt.fiteagle.core.monitoring.StatusTable;

import com.sun.jersey.server.impl.managedbeans.ManagedBeanComponentProviderFactoryInitilizer;

public class MonitoringManagerTest {

	private MonitoringManager monitoringManager;

	@Before
	public void setup() throws URISyntaxException {
		//URI uri = new URI("http://www.xipi.eu");
		//this.client = new InfinityClientWeb(uri);
		this.monitoringManager = new MonitoringManager();
	}
	
	@Test
	public void testGetXIPIMonitoringData() {
		List<StatusTable> result = monitoringManager.getXIPIMonitoringData();
		Assert.assertNotNull(result);
		
	}

	@Test
	public void getMonitoringDataById(){
//		StatusTable statusTable = new StatusTable();
//		statusTable.setId("testId");
//		statusTable.setLastCheck(new Date());
//		statusTable.setStatus("up");
//		statusTable.setXipiId("xipiId");
//		StatusTable component= new StatusTable();
//		component.setId("componentId");
//		component.setStatus("up");
//		component.setLastCheck(new Date());
//		statusTable.addComponent(component);
//		
//		monitoringManager.pushMonitoringData(statusTable);
//		StatusTable data = monitoringManager.getMonitoringDataById("testId");
//		Assert.assertNull(data);//
//		Assert.assertEquals("testId", data.getId());
	}
	
}
