package org.fiteagle.adapter.attenuatoradapter;

import java.util.ArrayList;
import java.util.List;

import org.fiteagle.adapter.common.AdapterConfiguration;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.SSHAccessable;

public class AttenuatorAdapter extends ResourceAdapter{
	
	private static boolean loaded = false;
	
	public AttenuatorAdapter() {
		super();
		this.setType("org.fiteagle.adapter.sshdeployadapter.AttenuatorAdapter");
	}

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
	public void release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ResourceAdapter> getJavaInstances() {
		//TODO: set the adapter specific staff to set the correct properties
		
		List<ResourceAdapter> resourceAdapters = new ArrayList<ResourceAdapter>();
		
		AttenuatorAdapter testAttentuatorAdapter = new AttenuatorAdapter();
		testAttentuatorAdapter.setExclusive(true);
		resourceAdapters.add(testAttentuatorAdapter);
		
		return resourceAdapters;
	}

	@Override
	public boolean isLoaded() {
		return this.loaded;
	}

	@Override
	public void setLoaded(boolean loaded) {
		this.loaded=loaded;
	}

	@Override
	public void configure(AdapterConfiguration configuration) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
