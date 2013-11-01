package org.fiteagle.delivery.rest.fiteagle;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
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

import org.eclipse.persistence.exceptions.DatabaseException;
import org.fiteagle.core.aaa.KeyManagement.CouldNotParse;
import org.fiteagle.core.userdatabase.JPAUserDB.DuplicateEmailException;
import org.fiteagle.core.userdatabase.JPAUserDB.DuplicatePublicKeyException;
import org.fiteagle.core.userdatabase.JPAUserDB.DuplicateUsernameException;
import org.fiteagle.core.userdatabase.JPAUserDB.UserNotFoundException;
import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.User.PublicKeyNotFoundException;
import org.fiteagle.core.userdatabase.User.Role;
import org.fiteagle.core.userdatabase.UserPublicKey;
import org.fiteagle.interactors.api.UserManagerBoundary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

@Path("v1/user")
public class UserPresenter{
  
  private static Logger log = LoggerFactory.getLogger(UserPresenter.class);
  
  private final UserManagerBoundary manager;
  
  @Inject
  public UserPresenter(final UserManagerBoundary manager){
    this.manager = manager;
  }
  
  @GET
  @Path("{username}")
  @Produces(MediaType.APPLICATION_JSON)
  public User getUser(@PathParam("username") String username, @QueryParam("setCookie") boolean setCookie) {
	try {
      return manager.get(username);
    } catch (UserNotFoundException e) {
      throw new FiteagleWebApplicationException(404, e.getMessage());
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }   
  }
  
  @PUT
  @Path("{username}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addUser(@PathParam("username") String username, NewUser user) {
    user.setUsername(username);
    try {
      manager.add(createUser(user));
    } catch (DuplicateUsernameException e) {
      throw new FiteagleWebApplicationException(409, e.getMessage());
    } catch (DuplicateEmailException e) {
      throw new FiteagleWebApplicationException(409, e.getMessage());
    } catch (DuplicatePublicKeyException e){
      throw new FiteagleWebApplicationException(409, e.getMessage());
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    } catch (User.NotEnoughAttributesException | User.InValidAttributeException e) {
      throw new FiteagleWebApplicationException(422, e.getMessage());
    }
    return Response.status(201).build();
  }

  @POST
  @Path("{username}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateUser(@PathParam("username") String username, NewUser user) {
    try {
      List<UserPublicKey> publicKeys = createPublicKeys(user.getPublicKeys());  
      manager.update(username, user.getFirstName(), user.getLastName(), user.getEmail(), user.getAffiliation(), user.getPassword(), publicKeys);
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);  
    } catch (UserNotFoundException e) {
      throw new FiteagleWebApplicationException(404, e.getMessage());
    } catch (DuplicateEmailException e) {
      throw new FiteagleWebApplicationException(409, e.getMessage());
    } catch (DuplicatePublicKeyException e){
      throw new FiteagleWebApplicationException(409, e.getMessage());
    } catch (User.NotEnoughAttributesException | User.InValidAttributeException e) {
      throw new FiteagleWebApplicationException(422, e.getMessage());
    }
    return Response.status(200).build();
  }

  @POST
  @Path("{username}/role/{role}")
  public Response setRole(@PathParam("username") String username, @PathParam("role") Role role) {
    try {
      manager.setRole(username, role);
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);  
    } catch (UserNotFoundException e) {
      throw new FiteagleWebApplicationException(404, e.getMessage());
    }
    return Response.status(200).build();
  }
  
  @POST
  @Path("{username}/pubkey/")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addPublicKey(@PathParam("username") String username, NewPublicKey pubkey) {    
	try {
      manager.addKey(username, new UserPublicKey(pubkey.getPublicKeyString(), pubkey.getDescription()));
    } catch (CouldNotParse | User.InValidAttributeException | User.NotEnoughAttributesException e){
      throw new FiteagleWebApplicationException(422, e.getMessage());
    } catch (UserNotFoundException e) {
      throw new FiteagleWebApplicationException(404, e.getMessage());
    } catch (DuplicatePublicKeyException e){
      throw new FiteagleWebApplicationException(409, e.getMessage());
    } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);    
    }
    return Response.status(200).build();
  }
  
  @DELETE
  @Path("{username}/pubkey/{description}")
  public Response deletePublicKey(@PathParam("username") String username, @PathParam("description") String description) {
    try {
      manager.deleteKey(username, decode(description));
    } catch (User.InValidAttributeException e){
      throw new FiteagleWebApplicationException(422, e.getMessage());
    } catch (UserNotFoundException e) {
      throw new FiteagleWebApplicationException(404, e.getMessage());
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return Response.status(200).build();
  }
  
  @POST
  @Path("{username}/pubkey/{description}/description")
  @Consumes(MediaType.TEXT_PLAIN)
  public Response renamePublicKey(@PathParam("username") String username, @PathParam("description") String description, String newDescription) {    
    try {
      manager.renameKey(username, decode(description), newDescription);
    } catch (User.InValidAttributeException e){
      throw new FiteagleWebApplicationException(422, e.getMessage());
    } catch (DuplicatePublicKeyException e){
      throw new FiteagleWebApplicationException(409, e.getMessage());
    } catch (UserNotFoundException | PublicKeyNotFoundException e) {
      throw new FiteagleWebApplicationException(404, e.getMessage());
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return Response.status(200).build();
  }
  
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
  
  @POST
  @Path("{username}/certificate")
  @Consumes(MediaType.TEXT_PLAIN)
  public String createUserCertAndPrivateKey(@PathParam("username") String username, String passphrase) {  
    try {      
      return manager.createUserKeyPairAndCertificate(username, decode(passphrase));
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }    
  }
  
  @GET
  @Path("{username}/pubkey/{description}/certificate")
  @Produces(MediaType.TEXT_PLAIN)
  public String getUserCertificateForPublicKey(@PathParam("username") String username, @PathParam("description") String description) {
    try {
      return manager.createUserCertificateForPublicKey(username, decode(description));
    } catch (PublicKeyNotFoundException e){
      throw new FiteagleWebApplicationException(404, e.getMessage());
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }    
  } 
  
  @DELETE
  @Path("{username}/cookie")
  public Response deleteCookie(@PathParam("username") String username){
    UserAuthenticationFilter.getInstance().deleteCookie(username);
    return Response.status(200).build();
  }
  
  private String decode(String string){    
    try {
      return URLDecoder.decode(string, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
  }

  private User createUser(NewUser newUser){
    List<UserPublicKey> publicKeys = createPublicKeys(newUser.getPublicKeys());    
    return new User(newUser.getUsername(), newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getAffiliation(), newUser.getPassword(), publicKeys);     
  }
  
  private ArrayList<UserPublicKey> createPublicKeys(List<NewPublicKey> keys) {
    if(keys == null){
      return null;
    }
    ArrayList<UserPublicKey> publicKeys = new ArrayList<>();
    for(NewPublicKey key : keys){
      try {
        publicKeys.add(new UserPublicKey(key.getPublicKeyString(), key.getDescription()));
      } catch (CouldNotParse e) {
        throw new FiteagleWebApplicationException(422, e.getMessage());
      } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
        log.error(e.getMessage());
        throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
      }
    }
    return publicKeys;
  }
  
  public static class FiteagleWebApplicationException extends WebApplicationException {  
    private static final long serialVersionUID = 5823637635206011675L;
    public FiteagleWebApplicationException(int status, String message){
      super(new ResponseBuilderImpl().status(status).entity(message).build()); 
    }   
  }   
  
}
