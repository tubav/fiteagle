package org.fiteagle.adapter.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceDatabase {

	private static Map<String, Resource> resourceDatabase = new HashMap<>();
	
	public static String addResource(Resource resource){
	
		//TODO dummy id generation
		resourceDatabase.put(resource.getIdentifier(), resource);
		return resource.getIdentifier();
	}
	
	public static Resource getResource(String resourceId){
		return resourceDatabase.get(resourceId);
	}
	
	public static List<Resource> getResources(){
		return new ArrayList<>(resourceDatabase.values());
	}
	public static void deleteResource(String resourceId){
		resourceDatabase.remove(resourceId);
	}
	
	
	
	
}
