package org.fiteagle.interactors.groupmanagement;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fiteagle.core.groupmanagement.Group;

public interface RestGroupManagement {
  
  @GET
  @Path("{groupId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Group getGroup(String groupId);
  
  @PUT
  @Path("{groupId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addGroup(String groupId, Group group);
  
  @DELETE
  @Path("{groupId}")
  public Response deleteGroup(String groupId);
  
}
