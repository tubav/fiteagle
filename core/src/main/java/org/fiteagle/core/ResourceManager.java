package org.fiteagle.core;

import java.util.List;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.stopwatch.Stopwatch;


public class ResourceManager {

	private ResourceAdapterDatabase adapterDatabase ;
	
	public ResourceManager(){
		adapterDatabase = new InMemoryResourceAdapterDatabase();
		ResourceAdapter dummyResourceAdapter = new Stopwatch();
	
		adapterDatabase.addResourceAdapter(dummyResourceAdapter);
	}
	
	public List<ResourceAdapter> getResourceAdapters(){
		return adapterDatabase.getResourceAdapters();
	}
	
	public void addResourceAdapter(ResourceAdapter resourceAdapter){
		adapterDatabase.addResourceAdapter(resourceAdapter);
	}
	
	
}
