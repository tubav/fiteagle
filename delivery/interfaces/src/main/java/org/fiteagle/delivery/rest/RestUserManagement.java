package org.fiteagle.delivery.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fiteagle.core.userdatabase.User;

public interface RestUserManagement {
  
  @GET
  @Path("{username}")
  @Produces(MediaType.APPLICATION_JSON)
  public abstract User getUser(@PathParam("username") String username);
  
  @PUT
  @Path("{username}")
  @Consumes(MediaType.APPLICATION_JSON)
  public abstract Response addUser(@PathParam("username") String username, NewUser user);
    
  @POST
  @Path("{username}")
  @Consumes(MediaType.APPLICATION_JSON)
  public abstract Response updateUser(@PathParam("username") String username, NewUser user);
  
  @DELETE
  @Path("{username}")
  public abstract Response deleteUser(@PathParam("username") String username);
  
  @POST
  @Path("{username/certificate}")
  @Consumes("text/plain")
  public String getUserCertAndPrivateKey(@PathParam("username") String username, String passphrase);
 
  @POST
  @Path("{username}/pubkey/")
  @Consumes("text/plain")
  public abstract Response addPublicKey(@PathParam("username") String username, String pubkey);  
    
}