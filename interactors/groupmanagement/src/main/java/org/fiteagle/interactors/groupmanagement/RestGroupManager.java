package org.fiteagle.interactors.groupmanagement;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/group")
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
  public Group getGroup(@PathParam("groupId") String groupId) {
    return groupManager.getGroup(groupId);
  }

  @Override
  @PUT
  @Path("{groupId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addGroup(@PathParam("groupId") String groupId,Group group) {
     groupManager.addGroup(group);
    return Response.status(Response.Status.CREATED).build();
    
  }

  @Override
  @DELETE
  @Path("{groupId}")
  public Response deleteGroup(@PathParam("groupId")String groupId) {
    groupManager.deleteGroup(groupId);
    return Response.status(Response.Status.OK).build();
    
  }
  
}
