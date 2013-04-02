package org.fiteagle.adapter.common;

public interface ResourceAdapter {

	public void start();
	public void stop();
	public void create();
	public void configure();
	public void release();
	public String getStatus();
	
	
}
