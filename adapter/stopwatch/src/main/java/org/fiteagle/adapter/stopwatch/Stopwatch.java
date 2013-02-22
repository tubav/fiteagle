package org.fiteagle.adapter.stopwatch;

import org.fiteagle.interfaces.frcp.IFRCP;

public class Stopwatch implements IFRCP {

	private boolean runningState = false;

	public boolean isRunning() {
		return runningState;
	}

	public void run() {
		this.runningState=true;
	}

	public void stop() {
		this.runningState = false;
	}
	
	@Override
	public void inform() {}

	@Override
	public void configure() {}

	@Override
	public void request() {}

	@Override
	public void create() {}

	@Override
	public void release() {}
}
