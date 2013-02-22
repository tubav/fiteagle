package org.fiteagle.adapter.stopwatch;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestStopwatch {

	private Stopwatch stopwatch;

	@Before
	public void setup() {
		stopwatch = new Stopwatch();
	}


	@Test
	public void testRunStopIsRunning() throws Exception {
		Assert.assertFalse(stopwatch.isRunning());
		stopwatch.run();
		Assert.assertTrue(stopwatch.isRunning());
		stopwatch.stop();
		Assert.assertFalse(stopwatch.isRunning());
	}

	
	//http://mytestbed.net/projects/omf/wiki/ArchitecturalFoundation2ProtocolInteractions
	@Test
	public void testFRCPInterface() throws Exception {
		stopwatch.inform();
		stopwatch.configure();
		stopwatch.request();
		stopwatch.create();
		stopwatch.release();
	}
}
