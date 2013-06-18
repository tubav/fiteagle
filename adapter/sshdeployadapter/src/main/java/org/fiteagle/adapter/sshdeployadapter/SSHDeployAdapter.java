package org.fiteagle.adapter.sshdeployadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.SSHAccessable;

public class SSHDeployAdapter extends ResourceAdapter implements SSHAccessable{
  private static boolean loaded = false;
  private String hardwareType;
  private String ip="";
  private String username="";
  private String password="";
  private String sshKey="";
  
  private SSHDeployAdapterConfiguration sshDeployAdapterConfig=SSHDeployAdapterConfiguration.getInstance();
  
  public SSHDeployAdapter(){
	  
//	  TODO: set staff over properties call other deploy adapter
    super();
//    this.setType("org.fiteagle.adapter.sshdeployadapter.SSHDeployAdapter");
//    this.create();
//	  SSHDeployAdapter(sshDeployAdapterConfig.get, String username, String password, String sshKey);
//	  this.setHardwareType(hardwareType); //TODO get hardware type from configuration
  }
  
  public SSHDeployAdapter(String ip, String username, String password, String sshKey){
	    super();
	    this.setType("org.fiteagle.adapter.sshdeployadapter.SSHDeployAdapter");
	    this.setIp(ip);
	    this.setUsername(username);
	    this.setPassword(password);
	    this.setSshKey(sshKey);
//	    this.create();
	  }

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
  public void configure() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void release() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public String getHardwareType() {
    return hardwareType;
  }

  @Override
  public void setHardwareType(String hardwareType) {
    this.hardwareType = hardwareType;
  }
@Override 
public String getIp() {
	return ip;
}
@Override
public void setIp(String ip) {
	this.ip = ip;
}
@Override
public String getUsername() {
	return username;
}
@Override
public void setUsername(String username) {
	this.username = username;
}
@Override
public String getPassword() {
	return password;
}
@Override
public void setPassword(String password) {
	this.password = password;
}
@Override
public String getSshKey() {
	return sshKey;
}
@Override
public void setSshKey(String sshKey) {
	this.sshKey = sshKey;
}

@Override
public List<ResourceAdapter> getJavaInstances() {
	List<ResourceAdapter> resourceAdapters = new ArrayList<ResourceAdapter>();
	
	String[] ips = sshDeployAdapterConfig.getIps().split(",");
	String[] usernames = sshDeployAdapterConfig.getUsernames().split(",");
	String[] passwords = sshDeployAdapterConfig.getPasswords().split(",");
	String[] sshKeys = sshDeployAdapterConfig.getSsh_keys().split(",");
	
	String[] countries = sshDeployAdapterConfig.getCountries().split(",");
	String[] latitudes = sshDeployAdapterConfig.getLatitudes().split(",");
	String[] longitudes = sshDeployAdapterConfig.getLongitues().split(",");
	
	String[] hardwareTypes = sshDeployAdapterConfig.getHardware_types().split(",");
	
	for (int i = 0; i < usernames.length; i++) {
		SSHDeployAdapter sshDeployAdapter = new SSHDeployAdapter(ips[i], usernames[i], passwords[i], sshKeys[i]);
		sshDeployAdapter.setHardwareType(hardwareTypes[i]);
		
		sshDeployAdapter.addProperty("country", countries[i]);
		sshDeployAdapter.addProperty("latitude", latitudes[i]);
		sshDeployAdapter.addProperty("longitude", longitudes[i]);
		sshDeployAdapter.setExclusive(true);
		resourceAdapters.add(sshDeployAdapter);
	}
	
	return resourceAdapters;
}

@Override
public boolean isLoaded() {
	return this.loaded;
}

@Override
public void setLoaded(boolean loaded) {
	this.loaded=loaded;
}

}
