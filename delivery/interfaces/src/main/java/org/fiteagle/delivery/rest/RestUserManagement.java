package org.fiteagle.delivery.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fiteagle.core.userdatabase.User;

public interface RestUserManagement {
  
  @GET
  @Path("{UID}")
  @Produces(MediaType.APPLICATION_JSON)
  public abstract User getUser(@PathParam("UID") String UID);
  
  @PUT
  @Path("{UID}")
  public abstract Response addUser(@PathParam("UID") String UID, @QueryParam("firstName") String firstName,
                                    @QueryParam("lastName") String lastName, @QueryParam("password") String password, @QueryParam("key") String key);
  
  @PUT
  @Path("{UID}/key/{key}")
  public abstract Response addPublicKey(@PathParam("UID") String UID, @PathParam("key") String key);  
  
  @POST
  @Path("{UID}")
  public abstract Response updateUser(@PathParam("UID") String UID, @QueryParam("firstName") String firstName,
                                      @QueryParam("lastName") String lastName, @QueryParam("password") String password, @QueryParam("key") String key);
  
  @DELETE
  @Path("{UID}")
  public abstract Response deleteUser(@PathParam("UID") String UID);

  
  @GET
  @Path("{UID/certificate}")
  public String getUserCertAndPrivateKey(@PathParam("UID") String uid, @QueryParam("password") String password);
 
}