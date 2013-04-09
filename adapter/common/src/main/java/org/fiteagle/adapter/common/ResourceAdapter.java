package org.fiteagle.adapter.common;

import java.util.HashMap;
import java.util.List;

public abstract class ResourceAdapter {

	public abstract void start();
	public abstract void stop();
	public abstract void create();
	public abstract void configure();
	public abstract void release();
	public abstract String getStatus();
	private HashMap<String, Object> properties;
	protected ResourceDatabase resourceDatabase;
	private String type;
	private String id;
	public abstract List<ResourceProperties> getAllResources();
	public HashMap<String, Object> getProperties() {
		return (properties != null ? properties : (properties = new HashMap<String, Object>()));
	}
	public void setProperties(HashMap<String, Object> properties) {
		this.properties = properties;
	}
	public ResourceDatabase getResourceDatabase() {
		return resourceDatabase;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
}
