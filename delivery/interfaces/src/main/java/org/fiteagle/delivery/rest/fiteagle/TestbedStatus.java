package org.fiteagle.delivery.rest.fiteagle;

import java.util.Date;

public class TestbedStatus {
  
  
  private String id;
  private String status;
  private Date lastCheck;
  
  public TestbedStatus(){
    
  }

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
  
  
}
