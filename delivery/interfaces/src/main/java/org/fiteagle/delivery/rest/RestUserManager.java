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
import org.fiteagle.core.userdatabase.UserDB.DuplicateUsernameException;
import org.fiteagle.core.userdatabase.UserDB.RecordNotFoundException;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/user")
public class RestUserManager implements RestUserManagement {
  private static Logger log = LoggerFactory.getLogger(RestUserManager.class);
  private static UserDBManager manager;
  static {
    try {
      manager = UserDBManager.getInstance();
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
  }
    
  @Override
  @GET
  @Path("{username}")
  @Produces(MediaType.APPLICATION_JSON)
  public User getUser(@PathParam("username") String username) {
    User user = null;
    try {
      user = manager.get(username);
    } catch (RecordNotFoundException e) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return user;
  }
  
  @Override
  @PUT
  @Path("{username}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addUser(@PathParam("username") String username, NewUser user) {
    user.setUsername(username);
    try {
      manager.add(createUser(user));
    } catch (DuplicateUsernameException e) {
      throw new WebApplicationException(Response.Status.CONFLICT);
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return Response.status(201).build();
  }   
  
  @Override
  @POST
  @Path("{username}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateUser(@PathParam("username") String username, NewUser user) {
    user.setUsername(username);
    try {
      manager.update(createUpdatedUser(user));     
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);  
    } catch (RecordNotFoundException e) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);      
    }
    return Response.status(200).build();
  }
  
  @Override
  @POST
  @Path("{username}/pubkey/")
  @Consumes("text/plain")
  public Response addPublicKey(@PathParam("username") String username, String pubkey) {
    if(pubkey == null || pubkey.length() == 0){
      throw new WebApplicationException(Response.status(422).build());
    }
    try {
      manager.addKey(username, pubkey);
    } catch (RecordNotFoundException e) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return Response.status(200).build();
  }
  
  @Override
  @DELETE
  @Path("{username}")
  public Response deleteUser(@PathParam("username") String username) {
    try {
      manager.delete(username);
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return Response.status(200).build();
  }
  
  @Override
  @POST
  @Path("{username}/certificate")
  @Consumes("text/plain")
  public String getUserCertAndPrivateKey(@PathParam("username") String username, String passphrase) {  
    if(passphrase == null || passphrase.length() == 0){
      throw new WebApplicationException(Response.status(422).build());
    }
    try {      
      return manager.createUserPrivateKeyAndCertAsString(username, passphrase);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new RuntimeException(e.getCause());
    }    
  }
  
  private User createUser(NewUser newUser) {
    checkAttributes(newUser);
    User user = null;
    try {
      user = manager.createUser(newUser.getUsername(), newUser.getFirstName(), newUser.getLastName(),
          newUser.getEmail(), newUser.getPassword(), newUser.getPublicKeys());
    } catch (NoSuchAlgorithmException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    } catch (IOException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return user;
  }
  
  private void checkAttributes(NewUser user){
    String firstName = user.getFirstName();
    String lastName = user.getLastName();
    String email = user.getEmail();
    String password = user.getPassword();
    if(firstName == null || lastName == null || email == null || password == null ||
        firstName.length() == 0 || lastName.length() == 0 || email.length() == 0 || 
        password.length() == 0 || !email.contains("@")){
      throw new WebApplicationException(Response.status(422).build());
    }
  }

  private User createUpdatedUser(NewUser newUser) {
    User oldUser = manager.get(newUser.getUsername());
    if(newUser.getFirstName() == null){
      newUser.setFirstName(oldUser.getFirstName());
    }
    if(newUser.getLastName() == null){
      newUser.setLastName(oldUser.getLastName());
    }
    if(newUser.getPublicKeys() == null){
      newUser.setPublicKeys(oldUser.getPublicKeys());
    }
    if(newUser.getEmail() == null){
      newUser.setEmail(oldUser.getEmail());
    }
    if(newUser.getPassword() == null){
      return new User(newUser.getUsername(), newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), oldUser.getPasswordHash(), oldUser.getPasswordSalt(), newUser.getPublicKeys());
    }
    return createUser(newUser);
  }  
}
