package orgt.fiteagle.core.monitoring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatusTable {
	
	private String id;
	private String status=null;
	private Date lastCheck;
	private String organization;
	private String xipiId;
	private List<StatusTable>components;
//	private String name;
	
	
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
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
	public String getXipiId() {
		return xipiId;
	}
	public void setXipiId(String xipiId) {
		this.xipiId = xipiId;
	}
	
	
	public List<StatusTable> getComponents() {
		return this.components;
	}
	
	public void setComponents(List<StatusTable> components) {
		this.components = components;
	}
	
	public void addComponent(StatusTable component){
		if(this.components == null)
			components = new ArrayList<>();
			
		this.components.add(component);
	}

}
