package org.fiteagle.delivery.rest.fiteagle;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAuthenticationFilter extends AuthenticationFilter{

  private final static String COOKIE_NAME = "fiteagle_user_cookie";
  
  private Logger log = LoggerFactory.getLogger(getClass());
  
  private static UserAuthenticationFilter filter = null;
  
  public static UserAuthenticationFilter getInstance(){
    if(filter == null){
      filter = new UserAuthenticationFilter();
    }
    return filter;
  }
 
  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
      ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;
    if(!request.isSecure()){
      response.setStatus(Response.Status.BAD_REQUEST.getStatusCode());      
      return;   
    }
    
    String method = request.getMethod(); 
    if(method.equals("PUT")){
      chain.doFilter(req, resp);
      return;
    }
    
    if(!(request.getSession(false) == null)){
      log.info("authenticated with session!");
      log.info("Created: "+new Date(request.getSession().getCreationTime()).toString());
      log.info("Last Accessed: "+new Date(request.getSession().getLastAccessedTime()).toString());
      chain.doFilter(req, resp);
    }
    
    String username = getTarget(request);
    if(username == null){
      response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());      
      return;
    }
    
    if(!authenticateWithCookie(request)){
      if(!authenticateWithUsernamePassword(request, response)){
        return;
      }
      
      if(getAuthCookieFromRequest(request) == null){
        response.addCookie(createNewCookie(username));
      }
    }
    
    request.getSession(true);
    chain.doFilter(req, resp);
    deleteSessionOnLogout(request);
  }

  private void deleteSessionOnLogout(HttpServletRequest request) {
    if(request.getMethod().equals("DELETE") && request.getRequestURI().endsWith("/cookie")){
      request.getSession().invalidate();
    }    
  }

  private boolean authenticateWithCookie(HttpServletRequest request){
    String username = getTarget(request);
    Cookie authCookieFromRequest = getAuthCookieFromRequest(request);
    Cookie cookieFromStorage = (username == null)? null : cookies.get(username);
    if(authCookieFromRequest != null && cookieFromStorage != null && authCookieFromRequest.getValue().equals(cookieFromStorage.getValue())){
      return true;
    }
    return false;
  }

  private Cookie getAuthCookieFromRequest(HttpServletRequest request) {
    Cookie[] cookiesFromRequest = request.getCookies();
    if(cookiesFromRequest != null){
      for(Cookie cookie : cookiesFromRequest){
        if(cookie.getName().equals(COOKIE_NAME)){
          return cookie;
        }
      }
    }
    return null;
  }
  
  private Cookie createNewCookie(String username){
    String authToken = createRandomAuthToken("-username:"+username);
    Cookie cookie = new Cookie(COOKIE_NAME, authToken);
    cookie.setSecure(true);
//  TODO  cookie.setHttpOnly(true);
    cookies.put(username, cookie);
    return cookie;
  } 
  
  public static String getCookieName(){
    return COOKIE_NAME;
  }

  @Override
  String getTarget(HttpServletRequest request) {
    String path = request.getRequestURI();
    return getTargetNameFromURI(path, "user");
  } 

  @Override
  boolean isUserAuthorizedForTarget(String user, String target) {
    return user.equals(target);
  }

  protected void deleteCookie(String username){
    cookies.remove(username);
  }
  
}
