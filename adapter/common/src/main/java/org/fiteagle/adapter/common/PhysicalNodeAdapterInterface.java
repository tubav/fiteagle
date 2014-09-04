package org.fiteagle.adapter.common;

import java.util.List;

public interface PhysicalNodeAdapterInterface {
	
	String nodeName = "";
	
	String getId();
//	List<SSHAccessable> getPhysicalNodes();
//	void setMachines(List<SSHAccessable> physicalNodes);
	PhysicalNodeAdapterInterface create(String callerId);
	public void configure(AdapterConfiguration configuration);
	String getCallerId();
//	public PhysicalNodeAdapterInterface create(String callerId, String ip, String port, String username,String sshKey);
	
	public String getNodeName();
	public void setNodeName(String nodeName);
//	String getUsername();
	String getIp();
	String getPort();
	AdapterConfiguration getConfiguration();
	
	
	
	

}
