package org.fiteagle.delivery.rest.fiteagle;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.userdatabase.JPAUserDB.UserNotFoundException;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.fiteagle.interactors.usermanagement.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupAuthenticationFilter extends AuthenticationFilter {
  Logger log = LoggerFactory.getLogger(getClass());
  UserDBManager userManager;
  GroupDBManager groupManager;
  private final static String COOKIE_NAME = "fiteagle_group_cookie";
  
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }
  
  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
      ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;
    if (!request.isSecure()) {
      response.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
      return;
    }
    if (authenticateWithCookie(request)) {
      chain.doFilter(request, response);
    } else {
      if (authenticateWithUsernamePassword(request, response)) {
        
        Cookie cookie = createAndStoreCookie(request);
        response.addCookie(cookie);
        chain.doFilter(request, response);
      } else {
        response.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
        return;
      }
    }
  }
  
  private boolean authenticateWithUsernamePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
    
    UserManager manager = UserManager.getInstance();
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
  
  private Cookie createAndStoreCookie(HttpServletRequest request) {
    String target = getTarget(request);
    Cookie cookie = new Cookie(COOKIE_NAME, createRandomAuthToken("-group:"+target));
    cookie.setSecure(true);
    cookie.setMaxAge(120);
    saveCookie(target, cookie);
    return cookie;
    
  }
  
  private boolean authenticateWithCookie(HttpServletRequest request) {
    Cookie cookie = getCookie(request);
    if (cookie != null) {
      
      if (cookies.get(getTarget(request)) != null) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
  
  private Cookie getCookie(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0)
      for (Cookie c : cookies) {
        if (c.getName().equals(COOKIE_NAME))
          return c;
      }
    
    return null;
  }
  
  private boolean isUserAuthorizedForTarget(String user, String target) {
    groupManager = GroupDBManager.getInstance();
    Group targetGroup = groupManager.getGroup(target);
    String groupOwner = targetGroup.getGroupOwnerId();
    return groupOwner.equals(user);
    
  }
  
  @Override
  protected String getTarget(HttpServletRequest request) {
    String path = request.getRequestURI();
    String returnValue = getTargetNameFromURI(path, "group");
    return returnValue;
    
  }
  
}
