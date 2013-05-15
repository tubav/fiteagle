package org.fiteagle.delivery.rest;

import java.sql.SQLException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserDB.DuplicateUIDException;
import org.fiteagle.core.userdatabase.UserDB.RecordNotFoundException;
import org.fiteagle.core.userdatabase.UserDBManager;

@Path("/userdb")
public class RestUserManager implements RestUserManagement{
    
  private static UserDBManager manager;
  static{
    try {
      manager = new UserDBManager();
    } catch (SQLException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);           
    }
  }
  
  @Override
  @GET
  @Path("{UID}")
  @Produces(MediaType.APPLICATION_JSON)
  public User getUser(@PathParam("UID") String UID){
    User user = null;
    try {
      user = manager.get(UID);
    } catch (RecordNotFoundException e) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    } catch (SQLException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return user;
  }
  
  @Override
  @PUT
  @Path("{UID}")
  public Response addUser(@PathParam("UID") String UID, 
                          @QueryParam("firstName") String firstName, 
                          @QueryParam("lastName") String lastName, 
                          @QueryParam("key") String key){   
    try {
      manager.add(new User(UID, firstName, lastName, key));
    } catch (DuplicateUIDException e) {
      throw new WebApplicationException(Response.Status.CONFLICT);    
    } catch (SQLException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR); 
    }
    return Response.status(201).build();
  }
  
  @Override
  @POST
  @Path("{UID}")
  public Response updateUser(@PathParam("UID") String UID, 
                             @QueryParam("firstName") String firstName, 
                             @QueryParam("lastName") String lastName, 
                             @QueryParam("key") String key){   
    try {
      manager.update(new User(UID, firstName, lastName, key));
    } catch (RecordNotFoundException e) {
      try {
        manager.add(new User(UID, firstName, lastName, key));
      } catch (DuplicateUIDException | SQLException e1) {
        throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
      }
      return Response.status(201).build();
    } catch (SQLException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return Response.status(200).build();
  }
  
  @Override
  @PUT
  @Path("{UID}/key/{key}")
  public Response addPublicKey(@PathParam("UID") String UID, @PathParam("key") String key){
    try {
      manager.addKey(UID, key);
    } catch (RecordNotFoundException e) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    } catch (SQLException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }    
    return Response.status(200).build();
  }
  
  @Override
  @DELETE
  @Path("{UID}")
  public Response deleteUser(@PathParam("UID") String UID){
    try {
      manager.delete(UID);
    } catch (SQLException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return Response.status(200).build();
  }
}

