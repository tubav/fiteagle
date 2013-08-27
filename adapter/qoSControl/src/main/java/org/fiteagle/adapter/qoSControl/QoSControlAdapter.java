package org.fiteagle.adapter.qoSControl;

import java.util.LinkedList;
import java.util.List;

import org.fiteagle.adapter.common.AdapterConfiguration;
import org.fiteagle.adapter.common.ResourceAdapter;

public class QoSControlAdapter extends ResourceAdapter {

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configure(AdapterConfiguration configuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ResourceAdapter> getJavaInstances() {
		LinkedList<ResourceAdapter> adapers = new LinkedList<>();
		
		adapers.add(new QoSControlAdapter());
		return adapers;
	}

	@Override
	public boolean isLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLoaded(boolean loaded) {
		// TODO Auto-generated method stub
		
	}

}
