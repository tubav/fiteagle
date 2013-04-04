package org.fiteagle.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.fiteagle.adapter.common.ResourceProperties;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.ResourceDatabase;
import org.fiteagle.adapter.common.ResourceType;
import org.fiteagle.adapter.stopwatch.StopWatchInstanceProperties;
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
