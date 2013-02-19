package org.fiteagle.adapter.stopwatch;

import junit.framework.Assert;

import org.fiteagle.adapter.stopwatch.Stopwatch;
import org.junit.Test;

public class TestStopwatch {

	@Test
	public void testRunStopIsRunning() throws Exception {
		final Stopwatch stopwatch = new Stopwatch();
		Assert.assertFalse(stopwatch.isRunning());
		stopwatch.run();
		Assert.assertTrue(stopwatch.isRunning());
		stopwatch.stop();
		Assert.assertFalse(stopwatch.isRunning());
	}
}
