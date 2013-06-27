package org.fiteagle.delivery.rest.fiteagle;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.userdatabase.UserDBManager;
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
  
  @Override
  boolean isUserAuthorizedForTarget(String user, String target) {
    groupManager = GroupDBManager.getInstance();
    Group targetGroup = groupManager.getGroup(target);
    String groupOwner = targetGroup.getGroupOwnerId();
    return groupOwner.equals(user);
    
  }
  
  @Override
  String getTarget(HttpServletRequest request) {
    String path = request.getRequestURI();
    String returnValue = getTargetNameFromURI(path, "group");
    return returnValue;
    
  }
  
}
