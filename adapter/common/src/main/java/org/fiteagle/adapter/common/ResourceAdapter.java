package org.fiteagle.adapter.common;

import java.rmi.server.ObjID;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
	private boolean exclusive = false;
	private boolean available = true;
//	private String locationCountry= "Germany";
//  private String locationLatitude= "52.516719";
//  private String longitude = "13.323507";
	
	
//	public abstract List<ResourceProperties> getAllResources();
	public HashMap<String, Object> getProperties() {
	  if (properties !=null){
	    return properties;
	  }else {
	    properties = new HashMap<String, Object>();//TODO: get these from preferences!
	    properties.put("country", "TestCountry");
	    properties.put("latitude", "52.516719");
	    properties.put("longitude","13.323507");
	    
	    return properties;
    }
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
//	  this.setId(objId.toString());
	  this.setId(UUID.randomUUID().toString());
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
  public boolean isExclusive() {
    return exclusive;
  }
  public void setExclusive(boolean exclusive) {
    this.exclusive = exclusive;
  }
	
  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;  
  }
	
	
	
	
}
