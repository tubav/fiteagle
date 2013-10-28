package org.fiteagle.interactors.sfa.common;

import java.util.Date;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.util.URN;

public class Sliver {

	private URN sliverURN;
	private Date expirationDate;
	private ResourceAdapter resource;
	private GENISliverAllocationState allocationState;
	private GENISliverOperationalState operationalState;
	private Group group;
	
	
	public Sliver(URN sliverURN, Group group) {
		this.sliverURN = sliverURN;
		this.allocationState = GENISliverAllocationState.geni_allocated;
		this.operationalState = GENISliverOperationalState.geni_pending_allocation;
		this.group = group;
	}
	public URN getSliverURN() {
		return sliverURN;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public ResourceAdapter getResource() {
		return resource;
	}
	public void setResource(ResourceAdapter resource) {
		this.resource = resource;
	}
	public GENISliverAllocationState getAllocationState() {
		return allocationState;
	}
	public void setAllocationState(GENISliverAllocationState allocationState) {
		this.allocationState = allocationState;
	}
	public GENISliverOperationalState getOperationalState() {
		return operationalState;
	}
	public void setOperationalState(GENISliverOperationalState operationalState) {
		this.operationalState = operationalState;
	}
	public String getId() {
		return sliverURN.toString();
	}
	public Group getGroup() {
		return group;
	}
	
	
	
	
	
}
