package org.fiteagle.adapter.dummyNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.fiteagle.adapter.common.AdapterConfiguration;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.SSHAccessable;

public class DummyNodeAdapter extends ResourceAdapter implements SSHAccessable{

	private String hardwareType = "raw-pc";
	
	private final static String[] ids = {"WiFi_connectivity", "lte-connectivity", "2G_connectivity", "3G_connectivity", "epc-as-a-service", "OpenEPC_client_Laptop", "OpenEPC_client_Samsung_S4", "Seamless_Handover_as_a_Service_through_MME", "Attenuator", "QoS_based_controllable_Core_Network", "OpenSDNCore", "OpenIMSCore"};

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configure(AdapterConfiguration configuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLoaded(boolean loaded) {
		// TODO Auto-generated method stub
		
	}
	
	public static List<ResourceAdapter> getJavaInstances(){
//		List<ResourceAdapter> list = new LinkedList<>();
//		
//		for (int i = 0; i < ids.length; i++) {
//			DummyNodeAdapter dummy = new DummyNodeAdapter();
//			dummy.setType("org.fiteagle.adapter.dummyNode.DummyNodeAdapter");
//			dummy.setId(ids[i]);
//			if(ids[i].compareTo("lte-connectivity")==0){
//				HashMap<String, Object> tmpProps = new HashMap<String, Object>();
//				tmpProps.put("sliverTypeName", "connectivity");
//				dummy.setProperties(tmpProps);
//				dummy.setHardwareType("lte-cell");
//			}
//			if(ids[i].compareTo("epc-as-a-service")==0){
//				HashMap<String, Object> tmpProps = new HashMap<String, Object>();
//				tmpProps.put("sliverTypeName", "epc-service");
//				dummy.setProperties(tmpProps);
//				dummy.setHardwareType("epc-installation");
//			}
//			
//			list.add(dummy);
//		}
//		return list;
		return new ArrayList<ResourceAdapter>();
		
	}

	@Override
	public String getHardwareType() {
		// TODO Auto-generated method stub
		return this.hardwareType ;
	}

	@Override
	public void setHardwareType(String hardwareType) {
		// TODO Auto-generated method stub
		this.hardwareType = hardwareType;
	}

	@Override
	public String getIp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIp(String ip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPort(String port) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSshKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSshKey(String sshKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkStatus() {
		// TODO Auto-generated method stub
		
	}

}
