package org.fiteagle.interactors.sfa.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.util.URN;

public class SliverManagement {

	private Map<URN, Sliver> sliverDB;
	private static SliverManagement sliverManager = null;
	private ResourceAdapterManager resourceManager = null;
	public static SliverManagement getInstance(){
		if(sliverManager == null){
			sliverManager = new SliverManagement();
		}
		return sliverManager;
	}
	
	private SliverManagement(){
		this.sliverDB = new HashMap<>();
	}
	

	public Sliver getSliver(URN sliverURN) {
		
		Sliver returnSliver = sliverDB.get(sliverURN);
		if(returnSliver == null){
			throw new SliverNotFound();
		}
		return returnSliver;
	}

	public void addSliver(Sliver sliver1) {
		if(sliverDB==null){
			sliverDB = new HashMap<>();
		}
		sliverDB.put(sliver1.getSliverURN(), sliver1);
		
	}

	public boolean isSliverValid(Sliver sliver) {
		boolean valid = true;
		if(!isURNSet(sliver) && !isURNValid(sliver))
			valid= false;
		if(!isAllocationStateSet(sliver))
			valid = false;
		if(!isOperationalStateSet(sliver))
			valid = false;
		if(!isSliverResourceExistent())
			valid = false;
		return valid;
	}

	private boolean isSliverResourceExistent() {
		return false;
	}

	private boolean isOperationalStateSet(Sliver sliver) {
		return sliver.getOperationalState() != null;
	}

	private boolean isAllocationStateSet(Sliver sliver) {
		return sliver.getAllocationState() != null;
	}

	private boolean isURNValid(Sliver sliver) {
		return sliver.getSliverURN().getType().equalsIgnoreCase("sliver");
	}

	private boolean isURNSet(Sliver sliver) {
		return sliver.getSliverURN() != null;
	}

	public ResourceAdapterManager getResourceManager() {
		return resourceManager;
	}

	public void setResourceManager(ResourceAdapterManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public List<Sliver> getSlivers(List<String> resourceIds) {
		List<Sliver> slivers = new LinkedList<>();
		for(String resourceId: resourceIds){
			slivers.add(getSliver(new URN(resourceId)));
		}
		return slivers;
	}
	
	public class SliverNotFound extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = -4379207521713588274L;
		
	}

}
