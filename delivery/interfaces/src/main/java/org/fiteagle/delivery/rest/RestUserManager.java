package org.fiteagle.delivery.rest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserDB.DatabaseException;
import org.fiteagle.core.userdatabase.UserDB.DuplicateUIDException;
import org.fiteagle.core.userdatabase.UserDB.RecordNotFoundException;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/userdb")
public class RestUserManager implements RestUserManagement {
  private Logger log = LoggerFactory.getLogger(getClass());
  private static UserDBManager manager;
  static {
    try {
      manager = UserDBManager.getInstance();
    } catch (DatabaseException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
  }
    
  @Override
  @GET
  @Path("{UID}")
  @Produces(MediaType.APPLICATION_JSON)
  public User getUser(@PathParam("UID") String UID) {
    User user = null;
    try {
      user = manager.get(UID);
    } catch (RecordNotFoundException e) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    } catch (DatabaseException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return user;
  }
  
  @Override
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addUser(NewUser user) {
    try {
      manager.add(createUser(user));
    } catch (DuplicateUIDException e) {
      throw new WebApplicationException(Response.Status.CONFLICT);
    } catch (DatabaseException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return Response.status(201).build();
  }
   
  
  @Override
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateUser(NewUser user) {
    try {
      manager.update(createUpdatedUser(user));
      return Response.status(200).build();
    } catch (DatabaseException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);  
    } catch (RecordNotFoundException e) {
      try {
        manager.add(createUser(user));
        return Response.status(201).build();
      } catch (DuplicateUIDException | DatabaseException e1) {
        throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
      }
    }      
  }
  
  @Override
  @POST
  @Path("{UID}/key/")
  @Consumes("text/plain")
  public Response addPublicKey(@PathParam("UID") String UID, String key) {
    try {
      manager.addKey(UID, key);
    } catch (RecordNotFoundException e) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    } catch (DatabaseException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return Response.status(200).build();
  }
  
  @Override
  @DELETE
  @Path("{UID}")
  public Response deleteUser(@PathParam("UID") String UID) {
    try {
      manager.delete(UID);
    } catch (DatabaseException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return Response.status(200).build();
  }
  
  @Override
  @POST
  @Path("{UID}/certificate")
  @Consumes("text/plain")
  public String getUserCertAndPrivateKey(@PathParam("UID") String UID, String passphrase) {  
    try {      
      
      return manager.createUserPrivateKeyAndCertAsString(UID, passphrase);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new RuntimeException(e.getCause());
    }    
  }
  
  
  private User createUser(NewUser newUser) {
    User user = null;	
	try {
	  user =  manager.createUser(newUser.getUID(), newUser.getFirstName(), newUser.getLastName(), newUser.getPassword(), newUser.getPublicKeys());
	} catch (NoSuchAlgorithmException e) {
	  // TODO: Not sure
	  throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	} catch (IOException e) {
	  // TODO: Not sure
	  throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	}    
	return user;
  }
  
  private User createUpdatedUser(NewUser newUser) {
    User oldUser = manager.get(newUser.getUID());
    if(newUser.getFirstName() == null){
      newUser.setFirstName(oldUser.getFirstName());
    }
    if(newUser.getLastName() == null){
      newUser.setLastName(oldUser.getLastName());
    }
    if(newUser.getPublicKeys() == null){
      newUser.setPublicKeys(oldUser.getPublicKeys());
    }
    if(newUser.getPassword() == null){
      return new User(newUser.getUID(), newUser.getFirstName(), newUser.getLastName(), oldUser.getPasswordHash(), oldUser.getPasswordSalt(), newUser.getPublicKeys());
    }
    return createUser(newUser);
  }

  @Override
  @POST
  @Path("{UID}/certificate")
  public String getUserCertificate(@PathParam("UID") String uid, String publicKeyEncoded) {
    try {
      return manager.createUserCertificate(uid, publicKeyEncoded);
    } catch (Exception e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
  }
  
}
