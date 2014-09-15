package org.fiteagle.adapter.lte;

import java.util.LinkedList;
import java.util.List;

import org.fiteagle.adapter.common.AdapterConfiguration;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.SSHAccessable;

public class LteAdapter extends ResourceAdapter implements SSHAccessable{

	private String hardwareType = "lte-access";

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
//		ResourceAdapter dummy = new LteAdapter();
//		dummy.setId("LTE");
//		list.add(dummy);
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

}
