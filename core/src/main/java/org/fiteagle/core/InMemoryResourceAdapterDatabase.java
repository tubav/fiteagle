package org.fiteagle.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.ResourceAdapterManager.ResourceNotFound;

public class InMemoryResourceAdapterDatabase implements ResourceAdapterDatabase {

	private  Map<String, ResourceAdapter> adapterMap = new HashMap<>();

	@Override
	public void addResourceAdapter(ResourceAdapter resourceAdapter) {
		adapterMap.put(resourceAdapter.getId(),resourceAdapter);
	}

	@Override
	public ResourceAdapter getResourceAdapter(String resourceAdapterId){
		if(adapterMap.containsKey(resourceAdapterId)){
			return adapterMap.get(resourceAdapterId);
		}
		else{
			throw new ResourceNotFound();
		}
	}

	@Override
	public List<ResourceAdapter> getResourceAdapters() {
		return new ArrayList<>(adapterMap.values());
	}

	@Override
	public void deleteResourceAdapter(String resourceAdapterId) {
		if(adapterMap.containsKey(resourceAdapterId)){
			adapterMap.remove(resourceAdapterId);
		}else{
			throw new ResourceNotFound();
		}
	}

	@Override
	public void addResourceAdapters(List<ResourceAdapter> resourceAdapters) {
		for (Iterator iterator = resourceAdapters.iterator(); iterator
				.hasNext();) {
			addResourceAdapter((ResourceAdapter) iterator.next());
		}
	}

}
