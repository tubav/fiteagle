package org.fiteagle.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fiteagle.adapter.common.ResourceAdapter;

public class InMemoryResourceAdapterDatabase implements ResourceAdapterDatabase {

	private static Map<String, ResourceAdapter> adapterMap = new HashMap<>();

	@Override
	public void addResourceAdapter(ResourceAdapter resourceAdapter) {
		adapterMap.put(resourceAdapter.getId(),resourceAdapter);
	}

	@Override
	public ResourceAdapter getResourceAdapter(String resourceAdapterId) {
		return adapterMap.get(resourceAdapterId);
	}

	@Override
	public List<ResourceAdapter> getResourceAdapters() {
		return new ArrayList<>(adapterMap.values());
	}

	@Override
	public void deleteResourceAdapter(String resourceAdapterId) {
		adapterMap.remove(resourceAdapterId);

	}

}
