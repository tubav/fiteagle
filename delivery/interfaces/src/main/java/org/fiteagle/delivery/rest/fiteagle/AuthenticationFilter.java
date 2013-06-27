package org.fiteagle.delivery.rest.fiteagle;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.fiteagle.core.userdatabase.UserDBManager;
import org.fiteagle.core.userdatabase.UserPersistable.DatabaseException;
import org.fiteagle.core.userdatabase.UserPersistable.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.core.util.Base64;

public abstract class AuthenticationFilter implements Filter {
  
  private UserDBManager manager;
  private Logger log = LoggerFactory.getLogger(getClass());
  
  protected HashMap<String, Cookie> cookies = new HashMap<>();
  
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    
  }
  
  @Override
  public void destroy() {
    
  }
  
  protected String[] decode(String auth) {
    if (auth == null || (!auth.startsWith("Basic ") && !auth.startsWith("basic "))) {
      return null;
    }
    auth = auth.replaceFirst("[B|b]asic ", "");
    byte[] decoded = DatatypeConverter.parseBase64Binary(auth);
    if (decoded == null || decoded.length == 0) {
      return null;
    }
    return new String(decoded).split(":", 2);
  }
  
  protected boolean authenticateWithUsernamePassword(HttpServletRequest request, HttpServletResponse response) {
    String auth = request.getHeader("authorization");
    String[] credentials = decode(auth);
    if (credentials == null || credentials.length != 2) {
      response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
      return false;
    }
    
    if (!isUserAuthorizedForTarget(credentials[0], getTarget(request))) {
      response.setStatus(Response.Status.FORBIDDEN.getStatusCode());
      return false;
    }
    
    manager = UserDBManager.getInstance();
    try {
      if (!manager.verifyCredentials(credentials[0], credentials[1])) {
        response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
        return false;
      }
    } catch (NoSuchAlgorithmException | IOException | DatabaseException e) {
      log.error(e.getMessage());
      response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
      return false;
    } catch (RecordNotFoundException e) {
      response.setStatus(Response.Status.NOT_FOUND.getStatusCode());
      return false;
    }
    return true;
  }
  
  protected String getTargetNameFromURI(String path, String targetIdentifier) {
    String[] splitted = path.split("/");
    for (int i = 0; i < splitted.length - 1; i++) {
      if (splitted[i].equals(targetIdentifier)) {
        return splitted[i+1];
      }
    }
    return null;
  }
  
  protected String createRandomAuthToken(String postfix) {
    String u = UUID.randomUUID().toString();
    u+=postfix;
    return new String(Base64.encode(u.getBytes()));
  }
  
  protected void saveCookie(String target, Cookie cookie) {
    cookies.put(target, cookie);    
  }
  
  abstract boolean isUserAuthorizedForTarget(String user, String target);
  
  abstract String getTarget(HttpServletRequest request);
}
