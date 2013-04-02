package org.fiteagle.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fiteagle.adapter.common.Resource;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.ResourceDatabase;
import org.fiteagle.adapter.common.ResourceType;
import org.fiteagle.adapter.stopwatch.Stopwatch;


public class ResourceManager {

	private Map<Resource, ResourceAdapter> adapterMap = new HashMap<>();
	
	public ResourceManager(){
		Resource dummyResource = new Resource();
		dummyResource.setName("stopWatch");
		ResourceType dummyType = new ResourceType();
		dummyType.setName("StopWatchDummyType");
		dummyType.setDescription("some dummy description!");
		dummyResource.setType(dummyType);
		
		dummyResource.setIdentifier("stopwatch-id");
		ResourceDatabase.addResource(dummyResource);
		
		
		ResourceAdapter dummyResourceAdapter = new Stopwatch();
		adapterMap.put(dummyResource, dummyResourceAdapter);
	}
	
	public List<Resource> getResources(){
		return ResourceDatabase.getResources();
	}
	
	public String addResource(Resource resource){
		return ResourceDatabase.addResource(resource);
	}
	
	
}
