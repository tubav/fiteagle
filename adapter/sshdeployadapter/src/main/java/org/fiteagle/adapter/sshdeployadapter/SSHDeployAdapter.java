package org.fiteagle.adapter.sshdeployadapter;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.SSHAccessable;

public class SSHDeployAdapter extends ResourceAdapter implements SSHAccessable{
  
  private String hardwareType;
  
  public SSHDeployAdapter(){
    super();
    this.setType("org.fiteagle.adapter.sshdeployadapter.SSHDeployAdapter");
    this.create();
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
  
}
