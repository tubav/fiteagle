package org.fiteagle.delivery.rest.fiteagle;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
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

import org.fiteagle.core.userdatabase.PublicKey;
import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicatePublicKeyException;
import org.fiteagle.core.userdatabase.UserPersistable.InValidAttributeException;
import org.fiteagle.core.userdatabase.UserPersistable.DatabaseException;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicateUsernameException;
import org.fiteagle.core.userdatabase.UserPersistable.NotEnoughAttributesException;
import org.fiteagle.core.userdatabase.UserPersistable.RecordNotFoundException;
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
  
  @PUT
  @Path("{username}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addUser(@PathParam("username") String username, NewUser user) {
    user.setUsername(username);
    try {
      manager.add(createUser(user));
    } catch (DuplicateUsernameException e) {
      throw new WebApplicationException(Response.Status.CONFLICT);
    } catch (DuplicatePublicKeyException e){
      throw new FiteagleWebApplicationException(409, e.getMessage());
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    } catch (NotEnoughAttributesException | InValidAttributeException e) {
      throw new FiteagleWebApplicationException(422, e.getMessage());
    }
    return Response.status(201).build();
  }

  @POST
  @Path("{username}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateUser(@PathParam("username") String username, NewUser user) {
    user.setUsername(username);
    try {
      manager.update(createUser(user));    
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);  
    } catch (RecordNotFoundException e) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);  
    } catch (DuplicatePublicKeyException e){
      throw new FiteagleWebApplicationException(409, e.getMessage());
    } catch (NotEnoughAttributesException | InValidAttributeException e) {
      throw new FiteagleWebApplicationException(422, e.getMessage());
    }
    return Response.status(200).build();
  }

  private User createUser(NewUser newUser){
    User user = null;    
    List<PublicKey> publicKeys = createPublicKeys(newUser.getPublicKeys());
    try {
      user = new User(newUser.getUsername(), newUser.getFirstName(), newUser.getLastName(),
          newUser.getEmail(), newUser.getAffiliation(), newUser.getPassword(), publicKeys);
    } catch (NoSuchAlgorithmException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return user;
  }

  private ArrayList<PublicKey> createPublicKeys(List<NewPublicKey> keys) {
    if(keys == null){
      return null;
    }
    ArrayList<PublicKey> publicKeys = new ArrayList<>();
    for(NewPublicKey key : keys){
      publicKeys.add(new PublicKey(key.getPublicKey(), key.getDescription()));
    }
    return publicKeys;
  }
  @POST
  @Path("{username}/pubkey/")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addPublicKey(@PathParam("username") String username, NewPublicKey pubkey) {    
    try {
      manager.addKey(username, new PublicKey(pubkey.getPublicKey(), pubkey.getDescription()));
    } catch (InValidAttributeException e){
      throw new FiteagleWebApplicationException(422, e.getMessage());
    } catch (RecordNotFoundException e) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    } catch (DuplicatePublicKeyException e){
      throw new FiteagleWebApplicationException(409, e.getMessage());
    } catch (DatabaseException e) {
      log.error(e.getMessage());
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    return Response.status(200).build();
  }
  
  @DELETE
  @Path("{username}/pubkey/{description}")
  public Response deletePublicKey(@PathParam("username") String username, @PathParam("description") String description) {    
    try {
      String decodedDescription = URLDecoder.decode(description, "UTF-8");
      manager.deleteKey(username, decodedDescription);
    } catch (InValidAttributeException e){
      throw new FiteagleWebApplicationException(422, e.getMessage());
    } catch (RecordNotFoundException e) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    } catch (UnsupportedEncodingException | DatabaseException e) {
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
  @Consumes("text/plain")
  public String getUserCertAndPrivateKey(@PathParam("username") String username, String passphrase) {  
    if(passphrase == null || passphrase.length() == 0){
      throw new FiteagleWebApplicationException(422, "no passphrase given or passphrase too short");
    }
    try {      
      return manager.createUserPrivateKeyAndCertAsString(username, passphrase);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new RuntimeException(e.getCause());
    }    
  }
  
  @POST
  @Path("{username}/certificate")
  public String getUserCertificate(@PathParam("username") String uid, String publicKeyEncoded) {
    try {
      return manager.createUserCertificate(uid, publicKeyEncoded);
    } catch (Exception e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
  } 
  
  @DELETE
  @Path("{username}/cookie")
  public Response deleteCookie(@PathParam("username") String username){
    UserAuthenticationFilter.getInstance().deleteCookie(username);
    return Response.status(200).build();
  }
  
  
  public class FiteagleWebApplicationException extends WebApplicationException {  
    private static final long serialVersionUID = 5823637635206011675L;
    public FiteagleWebApplicationException(int status, String message){
      super(new ResponseBuilderImpl().status(status).entity(message).build()); 
    }   
  }   
  
}
