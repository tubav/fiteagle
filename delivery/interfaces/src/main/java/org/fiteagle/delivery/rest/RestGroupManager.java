package org.fiteagle.delivery.rest;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("group")
public class RestGroupManager implements RestGroupManagement {
  private Logger log = LoggerFactory.getLogger(getClass());
  private GroupManager groupManager;
  public RestGroupManager(){
    try {
      this.groupManager = GroupManager.getInstance();
    } catch (SQLException e) {
      log.error(e.getMessage(),e);
    }
  }
  
  @Override
  @GET
  @Path("{groupId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Group getGroup(String groupId) {
    return groupManager.getGroup(groupId);
  }

  @Override
  public void addGroup(Group group) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteGroup(String groupId) {
    // TODO Auto-generated method stub
    
  }
  
}
