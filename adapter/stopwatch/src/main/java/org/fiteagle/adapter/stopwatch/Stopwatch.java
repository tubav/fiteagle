package org.fiteagle.adapter.stopwatch;

import org.fiteagle.adapter.common.ResourceAdapter;


public class Stopwatch implements ResourceAdapter {

	
	private transient boolean runningState = false;

	public boolean isRunning() {
		return runningState;
	}

	@Override
	public void stop() {
		this.runningState = false;
	}

	@Override
	public void start() {
		this.runningState=true;
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configure() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getStatus() {
		return "Is running: "+runningState;
	}
	
	
}
