package org.fiteagle.adapter.common;

import java.util.ArrayList;
import java.util.List;



public interface NodeAdapterInterface {
	
	public List<OpenstackResourceAdapter> getImages();
	public void setImages(List<OpenstackResourceAdapter> images);
	static final String nodeName = "fOpenStack";
	
	String getId();
	List<OpenstackResourceAdapter> getVms();
	void setVms(List<OpenstackResourceAdapter> vms);
	NodeAdapterInterface create(String id, List<OpenstackResourceAdapter> vms, String callerId);
	public void configure(AdapterConfiguration configuration);
	String getCallerId();
	

}
