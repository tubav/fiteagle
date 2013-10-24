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

import net.iharder.Base64;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.fiteagle.core.userdatabase.JPAUserDB.UserNotFoundException;
import org.fiteagle.interactors.api.UserManagerBoundary;
import org.fiteagle.interactors.usermanagement.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AuthenticationFilter implements Filter {
  
  private UserManagerBoundary manager;
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
  
  protected boolean authenticateWithUsernamePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String auth = request.getHeader("authorization");
    String[] credentials = decode(auth);
    if (credentials == null || credentials.length != 2) {
      response.sendError(Response.Status.UNAUTHORIZED.getStatusCode());
      return false;
    }
    
    if (!isUserAuthorizedForTarget(credentials[0], getTarget(request))) {
      response.sendError(Response.Status.FORBIDDEN.getStatusCode());
      return false;
    }
    
    manager = UserManager.getInstance();
    try {
      if (!manager.verifyCredentials(credentials[0], credentials[1])) {
        response.sendError(Response.Status.UNAUTHORIZED.getStatusCode());
        return false;
      }
    } catch (NoSuchAlgorithmException | IOException | DatabaseException e) {
      log.error(e.getMessage());
      response.sendError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
      return false;
    } catch (UserNotFoundException e) {
      response.sendError(Response.Status.NOT_FOUND.getStatusCode(), "User Not Found");
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
    return new String(Base64.encodeBytes(u.getBytes()));
  }
  
  protected void saveCookie(String target, Cookie cookie) {
    cookies.put(target, cookie);    
  }
  
  abstract boolean isUserAuthorizedForTarget(String user, String target);
  
  abstract String getTarget(HttpServletRequest request);
}
