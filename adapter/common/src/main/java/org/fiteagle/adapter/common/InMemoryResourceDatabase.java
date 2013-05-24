//package org.fiteagle.adapter.common;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class InMemoryResourceDatabase implements ResourceDatabase {
//
//	private  Map<String, ResourceProperties> resourceDatabase = new HashMap<>();
//	@Override
//	public void addResource(ResourceProperties resource) {
//
//		
//		resourceDatabase.put(resource.getIdentifier(), resource);
//	}
//
//	@Override
//	public ResourceProperties getResource(String resourceId) {
//		return resourceDatabase.get(resourceId);
//	}
//
//	@Override
//	public List<ResourceProperties> getResources() {
//		return new ArrayList<>(resourceDatabase.values());
//	}
//
//	@Override
//	public void deleteResource(String resourceId) {
//		resourceDatabase.remove(resourceId);
//
//	}
//
//}
