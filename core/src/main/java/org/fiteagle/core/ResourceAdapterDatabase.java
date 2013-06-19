package org.fiteagle.core;

import java.util.List;

import org.fiteagle.adapter.common.ResourceAdapter;

public interface ResourceAdapterDatabase {

	public  void addResourceAdapter(ResourceAdapter resource);
	
	public  ResourceAdapter getResourceAdapter(String resourceAdapterId);
	
	public  List<ResourceAdapter> getResourceAdapters();
	public void deleteResourceAdapter(String resourceAdapterId);

	public void addResourceAdapters(List<ResourceAdapter> resourceAdapters); 
	
}
