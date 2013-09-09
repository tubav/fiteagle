package org.fiteagle.delivery.rest.fiteagle;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import org.fiteagle.core.config.InterfaceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAuthenticationFilter extends AuthenticationFilter{

  private final static String COOKIE_NAME = "fiteagle_user_cookie";
  
  @SuppressWarnings("unused")
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
      response.sendError(Response.Status.BAD_REQUEST.getStatusCode());      
      return;   
    }
    
    String method = request.getMethod(); 
    if(method.equals("PUT")){
      chain.doFilter(req, resp);
      return;
    }
    
    String username = getTarget(request);
    if(username == null){
      response.sendError(Response.Status.UNAUTHORIZED.getStatusCode());      
      return;
    }
    
    if(!authenticateWithSession(request) && !authenticateWithCookie(request) && !authenticateWithUsernamePassword(request, response)){
      return;
    }
    
    addCookieOnLogin(request, response);
    createSession(request);    
    chain.doFilter(req, resp);
    deleteSessionAndCookieOnLogout(request, response);
  }

  private void createSession(HttpServletRequest request) {
    HttpSession session = request.getSession(true);
    if(session != null){
      String username =  getTarget(request);
      username = addDomain(username);
      session.setAttribute("username",username);
    }
  }

  private String addDomain(String username) {

		InterfaceConfiguration configuration = null;
		if (!username.contains("@")) {
			configuration = InterfaceConfiguration.getInstance();
			username = username + "@" + configuration.getDomain();
		}
		return username;
	}
  private void addCookieOnLogin(HttpServletRequest request, HttpServletResponse response) {    
    boolean setCookie = Boolean.parseBoolean(request.getParameter("setCookie"));
    if(request.getMethod().equals("GET") && setCookie == true && getAuthCookieFromRequest(request) == null){      
      response.addCookie(createNewCookie(getTarget(request)));      
    }
  }

  private void deleteSessionAndCookieOnLogout(HttpServletRequest request, HttpServletResponse response) {
    if(request.getMethod().equals("DELETE") && request.getRequestURI().endsWith("/cookie")){
      request.getSession().invalidate();      
      addNullCookies(request, response);
    }
  }

  private void addNullCookies(HttpServletRequest request, HttpServletResponse response) {
    Cookie authCookie = getAuthCookieFromRequest(request);
    if(authCookie != null){
      authCookie.setMaxAge(0);
      authCookie.setValue(null);
      authCookie.setPath("/");
      response.addCookie(authCookie);
    }    
    
    Cookie sessionCookie = getSessionCookieFromRequest(request);
    if(sessionCookie != null){
      sessionCookie.setMaxAge(0);
      sessionCookie.setValue(null);
      sessionCookie.setPath("/");
      response.addCookie(sessionCookie);
    }
  }
  
  private boolean authenticateWithSession(HttpServletRequest request){
    HttpSession session = request.getSession(false);    
    if(session == null){
      return false;
    }
    if(!isUserAuthorizedForTarget(session.getAttribute("username").toString(), addDomain(getTarget(request)))) {
      return false;
    }    
    return true;
  }

  private boolean authenticateWithCookie(HttpServletRequest request){
    String username = getTarget(request);
    Cookie authCookieFromRequest = getAuthCookieFromRequest(request);
    Cookie cookieFromStorage = (username == null)? null : cookies.get(username);
    
    if(authCookieFromRequest == null || cookieFromStorage == null){
      return false;
    }    
    if(!authCookieFromRequest.getValue().equals(cookieFromStorage.getValue())){
      return false;
    }    
    return true;
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
  
  private Cookie getSessionCookieFromRequest(HttpServletRequest request) {
    Cookie[] cookiesFromRequest = request.getCookies();
    if(cookiesFromRequest != null){
      for(Cookie cookie : cookiesFromRequest){
        if(cookie.getName().equals("JSESSIONID")){
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
//  TODO:  cookie.setHttpOnly(true);
    cookie.setMaxAge(365 * 24 * 60 * 60);
    cookie.setPath("/");
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
