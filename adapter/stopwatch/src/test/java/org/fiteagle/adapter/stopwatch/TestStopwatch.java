package org.fiteagle.adapter.stopwatch;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestStopwatch {

	private transient Stopwatch stopwatch;

	@Before
	public void setUp() {
		stopwatch = new Stopwatch();
	}


	@Test
	public void testRunStopIsRunning() {
		Assert.assertFalse(stopwatch.isRunning());
		stopwatch.start();
		Assert.assertTrue(stopwatch.isRunning());
		stopwatch.stop();
		Assert.assertFalse(stopwatch.isRunning());
	}

	
	//http://mytestbed.net/projects/omf/wiki/ArchitecturalFoundation2ProtocolInteractions
//	@Test
//	public void testFRCPInterface() {
//		//stopwatch.inform();
//		Assert.assertTrue(stopwatch.inform());
//		stopwatch.configure();
//		stopwatch.request();
//		stopwatch.create();
//		stopwatch.release();
//	}
}
