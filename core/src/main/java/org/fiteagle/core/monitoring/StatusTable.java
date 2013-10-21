package org.fiteagle.core.monitoring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class StatusTable {

	public final static String UNDEFINED = "undefined";
	public final static String UP_AND_LAST_CHECKED_OLD = "upAndLastCheckedOld";
	public final static String UP = "up";
	public final static String PARTIALLY = "partially";
	public final static String DOWN = "down";

	private String id;
	private String status = null;
	private Date lastCheck;
	private String organization;
	private String xipiId;
	private HashMap<String, StatusTable> components;

	// private List<StatusTable>components;
	// private String name;

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

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	// public String getName() {
	// return name;
	// }
	// public void setName(String name) {
	// this.name = name;
	// }
	public String getXipiId() {
		return xipiId;
	}

	public void setXipiId(String xipiId) {
		this.xipiId = xipiId;
	}

	public Collection<StatusTable> getComponents() {
		if (this.components == null)
			components = new HashMap<String, StatusTable>();
		return this.components.values();
	}

	public void setComponents(HashMap<String, StatusTable> components) {
		this.components = components;
	}

	public void addComponent(StatusTable component) {
		if (this.components == null)
			components = new HashMap<String, StatusTable>();

		this.components.put(component.getId(), component);
	}

}
