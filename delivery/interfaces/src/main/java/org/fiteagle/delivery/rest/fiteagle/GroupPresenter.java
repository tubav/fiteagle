package org.fiteagle.delivery.rest.fiteagle;

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
import org.fiteagle.core.groupmanagement.SQLiteGroupDatabase.CouldNotCreateGroup;
import org.fiteagle.core.groupmanagement.SQLiteGroupDatabase.CouldNotDeleteGroup;
import org.fiteagle.interactors.api.GroupManagerBoundary;

import com.google.inject.Inject;
@Path("/v1/group")
public class GroupPresenter {
  GroupManagerBoundary groupManagerBoundary;
  
  @Inject
  public GroupPresenter(final GroupManagerBoundary groupManagerBoundary){
    this.groupManagerBoundary = groupManagerBoundary ;
  }

  
  @GET
  @Path("{groupId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Group getGroup(@PathParam("groupId") String groupId) {
   
    return groupManagerBoundary.getGroup(groupId);
  }


  @PUT
  @Path("{groupId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addGroup(@PathParam("groupId") String groupId,Group g) {
    try{
      groupManagerBoundary.addGroup(g.getGroupId(), g);
    }catch(CouldNotCreateGroup e){
      return Response.status(500).build();
    }
    return Response.status(201).build();
    
  }


  @DELETE
  @Path("{groupId}")
  public Response deleteGroup(@PathParam("groupId")String groupId) {
    try{
      groupManagerBoundary.deleteGroup(groupId);
    }catch(CouldNotDeleteGroup e){
      return Response.status(500).build();
    }
    return Response.status(200).build();
    
  }
  
  
}
