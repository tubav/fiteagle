package org.fiteagle.adapter.stopwatch;

import org.fiteagle.interfaces.frcp.IFRCP;

public class Stopwatch implements IFRCP {

	private transient boolean runningState = false;

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
	public boolean inform() {
		return true;}

	@Override
	public void configure() {
		// nothing yet
	}

	@Override
	public void request() {
		// nothing yet
	}

	@Override
	public void create() {
		// nothing yet
	}

	@Override
	public void release() {
		// nothing yet
	}
}
