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
  @Path("{UID}")
  @Produces(MediaType.APPLICATION_JSON)
  public abstract User getUser(@PathParam("UID") String UID);
  
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public abstract Response addUser(NewUser user);
    
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public abstract Response updateUser(NewUser user);
  
  @DELETE
  @Path("{UID}")
  public abstract Response deleteUser(@PathParam("UID") String UID);
  
  @GET
  @Path("{UID/certificate}")
  @Consumes("text/plain")
  public String getUserCertAndPrivateKey(@PathParam("UID") String uid, String passphrase);
 
  @POST
  @Path("{UID}/key/")
  @Consumes("text/plain")
  public abstract Response addPublicKey(@PathParam("UID") String UID, String key);  
  
}