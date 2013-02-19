package org.fiteagle.adapter.stopwatch;

public class Stopwatch {

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
}
