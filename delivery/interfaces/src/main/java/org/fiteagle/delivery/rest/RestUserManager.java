package org.fiteagle.delivery.rest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
      manager =UserDBManager.getInstance();
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
  @Path("{UID}")
  public Response addUser(@PathParam("UID") String UID, @QueryParam("firstName") String firstName,
      @QueryParam("lastName") String lastName, @QueryParam("password") String password, @QueryParam("key") String key) {
    ArrayList<String> keys = new ArrayList<String>();
    keys.add(key);
    try {
      manager.add(manager.createUser(UID, firstName, lastName, password, keys));
    } catch (DuplicateUIDException e) {
      throw new WebApplicationException(Response.Status.CONFLICT);
    } catch (DatabaseException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    } catch (NoSuchAlgorithmException e) {
      // TODO: Not sure
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    } catch (IOException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return Response.status(201).build();
  }
  
  @Override
  @POST
  @Path("{UID}")
  public Response updateUser(@PathParam("UID") String UID, @QueryParam("firstName") String firstName,
      @QueryParam("lastName") String lastName, @QueryParam("password") String password, @QueryParam("key") String key) {
    ArrayList<String> keys = new ArrayList<String>();
    keys.add(key);
    try {
      manager.update(manager.createUser(UID, firstName, lastName, password, keys));
    } catch (RecordNotFoundException e) {
      try {
        manager.add(manager.createUser(UID, firstName, lastName, password, keys));
        ;
      } catch (DuplicateUIDException | DatabaseException e1) {
        throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
      } catch (NoSuchAlgorithmException e1) {
        throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
      } catch (IOException e1) {
        throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        
      }
      return Response.status(201).build();
    } catch (DatabaseException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    } catch (DuplicateUIDException e) {
      throw new WebApplicationException(Response.Status.CONFLICT);
    } catch (NoSuchAlgorithmException e) {
      // TODO: Not sure
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    } catch (IOException e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return Response.status(200).build();
  }
  
  @Override
  @PUT
  @Path("{UID}/key/{key}")
  public Response addPublicKey(@PathParam("UID") String UID, @PathParam("key") String key) {
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
  @GET
  @Path("{UID}/certificate")
  public String getUserCertAndPrivateKey(@PathParam("UID") String uid, @QueryParam("password") String password) {
    
    try {
      
      String privateKey = manager.createUserPrivateKey(uid, password);
      String certificate = manager.createUserCertificate(uid);
      return privateKey + "\n" + certificate;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new RuntimeException(e.getCause());
    }
    
  }
}
