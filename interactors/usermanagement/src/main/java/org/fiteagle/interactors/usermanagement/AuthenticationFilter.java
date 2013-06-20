

package org.fiteagle.interactors.usermanagement;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.DatatypeConverter;

import org.fiteagle.core.userdatabase.UserPersistable.DatabaseException;
import org.fiteagle.core.userdatabase.UserPersistable.RecordNotFoundException;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.core.util.Base64;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

public class AuthenticationFilter implements ContainerRequestFilter, ContainerResponseFilter{
  
  private final static String COOKIE_NAME = "fiteagle_user_cookie";
  private HashMap<String, Cookie> cookies = new HashMap<>();
  
  private UserDBManager manager = null;
  
  private Logger log = LoggerFactory.getLogger(getClass());
  
  @Override
  public ContainerRequest filter(ContainerRequest req) {
    if(!req.getSecurityContext().isSecure()){
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
    
    String method = req.getMethod();    
    if(method.equals("PUT")){
      return req;
    }
    
    if(!authenticateWithCookie(req)){
      authenticateWithUsernameAndPassword(req);
    }
    
    return req;
  }
  
  private String[] decode(String auth){ 
    if(auth == null || (!auth.startsWith("Basic ") && !auth.startsWith("basic "))){
      throw new WebApplicationException(Response.Status.UNAUTHORIZED);
    }
    auth = auth.replaceFirst("[B|b]asic ", "");
    byte[] decoded = DatatypeConverter.parseBase64Binary(auth);
    if(decoded == null || decoded.length == 0){
      throw new WebApplicationException(Response.Status.UNAUTHORIZED);
    }
    return new String(decoded).split(":", 2);
  }
  
  private void authenticateWithUsernameAndPassword(ContainerRequest req){
    String auth = req.getHeaderValue("authorization");    
    String[] credentials = decode(auth);    
    if(credentials == null || credentials.length != 2){
      throw new WebApplicationException(Response.Status.UNAUTHORIZED);
    }

    if(!getUsernameFromRequest(req).equals(credentials[0])){
        throw new WebApplicationException(Response.Status.FORBIDDEN); 
    }
    
    manager = UserDBManager.getInstance();
    try {
      if(!manager.verifyCredentials(credentials[0], credentials[1])){
        throw new WebApplicationException(Response.Status.UNAUTHORIZED);
      }
    } catch (NoSuchAlgorithmException | IOException | DatabaseException e) {
        log.error(e.getMessage());
        throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    } catch (RecordNotFoundException e){
      throw new WebApplicationException(Response.Status.NOT_FOUND);       
    }
  }

  private boolean authenticateWithCookie(ContainerRequest req){
    String username = getUsernameFromRequest(req); 
    Cookie cookie = req.getCookies().get(COOKIE_NAME);
    Cookie cookieFromStorage = cookies.get(username);
    if(cookie != null && cookieFromStorage != null && cookie.getValue().equals(cookieFromStorage.getValue())){
      return true;
    }
    return false;
  }

  private String getUsernameFromRequest(ContainerRequest req) {
    //@mitja: ugly code - exception if path changes
    return (String) req.getPathSegments().get(0).getPath();
  }
  
  @Override
  public ContainerResponse filter(ContainerRequest req, ContainerResponse resp) {
    if(req.getMethod().equals("PUT")){
      return resp;
    }
        
    String username = getUsernameFromRequest(req); 
    if(username != null && req.getCookies().get(COOKIE_NAME) == null){
      ResponseBuilder rb = Response.fromResponse(resp.getResponse());
      rb.cookie(createNewCookie(username));
      resp.setResponse(rb.build());
    }

    //TODO: when to remove cookie?
    return resp;
  }
  
  private NewCookie createNewCookie(String username){
    String authToken = createRandomAuthToken();
    NewCookie cookie = new NewCookie(COOKIE_NAME, authToken, null, null , null, 1800, true);
    cookies.put(username, cookie.toCookie());
    return cookie;
  }

  private String createRandomAuthToken() {
    String u = UUID.randomUUID().toString();    
    return new String(Base64.encode(u.getBytes()));
  }
  
  public static String getCookieName(){
    return COOKIE_NAME;
  }
}
