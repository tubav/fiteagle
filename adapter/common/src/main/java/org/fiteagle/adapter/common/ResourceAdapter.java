package org.fiteagle.adapter.common;

import java.rmi.server.ObjID;
import java.util.HashMap;
import java.util.List;

public abstract class ResourceAdapter {

	public abstract void start();
	public abstract void stop();
	public abstract void create();
	public abstract void configure();
	public abstract void release();
//	public abstract String getStatus();
	private HashMap<String, Object> properties;
//	protected ResourceDatabase resourceDatabase;
	private String type;//class of the implementing adapter
	private String id;
	private String groupId;
	private String status;
//	public abstract List<ResourceProperties> getAllResources();
	public HashMap<String, Object> getProperties() {
		return (properties != null ? properties : (properties = new HashMap<String, Object>()));
	}
	public void setProperties(HashMap<String, Object> properties) {
		this.properties = properties;
	}
	
//	public void addProperty(HashMap<String, Object> property) {
//    this.properties = properties;
//  }
	
//	public ResourceDatabase getResourceDatabase() {
//		return resourceDatabase;
//	}
	
	public ResourceAdapter() {
	  ObjID objId = new ObjID();
	  this.setId(objId.toString());
	  this.setStatus("geni_allocated");
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
  public String getGroupId() {
    return groupId;
  }
  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
	
	
	
	
	
}
