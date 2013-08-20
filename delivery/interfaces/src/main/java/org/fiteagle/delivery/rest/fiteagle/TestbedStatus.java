package org.fiteagle.delivery.rest.fiteagle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestbedStatus {

	private String id;
	private String status;
	private Date lastCheck;
	private List<TestbedStatus> components;

	public TestbedStatus() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastCheck() {
		return lastCheck;
	}

	public void setLastCheck(Date lastCheck) {
		this.lastCheck = lastCheck;
	}

	public List<TestbedStatus> getComponents() {
		return this.components;
	}
	
	public void setComponents(List<TestbedStatus> components) {
		this.components = components;
	}
	
	public void addComponent(TestbedStatus component){
		if(this.components == null)
			components = new ArrayList<TestbedStatus>();
			
		this.components.add(component);
	}
}
