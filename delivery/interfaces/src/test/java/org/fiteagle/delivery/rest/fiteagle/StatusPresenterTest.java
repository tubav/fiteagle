package org.fiteagle.delivery.rest.fiteagle;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.fiteagle.interactors.monitoring.MonitoringManager;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class StatusPresenterTest {
	
	StatusPresenter statusPresenter;
	
	@Before
	public void setup(){
	this.statusPresenter = new StatusPresenter(new MonitoringManager());
	}

	@Ignore
	@Test
	public void testGetStatus() {
		List<TestbedStatus> status = statusPresenter.getStatus();
		Assert.assertNotNull(status);
//		//this one is only while using the mock client. ignore this for other cases.
//		Assert.assertEquals("status unknown", status.get(0).getStatus());
	}
	
//	@Test
//	public void getTestBedStatusById(){
//		statusPresenter.getTestBedStatusById("")
//	}

}
