package org.fiteagle.delivery.rest.fiteagle;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import net.iharder.Base64;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.fiteagle.core.config.InterfaceConfiguration;
import org.fiteagle.core.userdatabase.JPAUserDB.UserNotFoundException;
import org.fiteagle.interactors.usermanagement.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserAuthenticationFilter extends AuthenticationFilter{

  private final static String COOKIE_NAME = "fiteagle_user_cookie";
  protected final static String SUBJECT_USERNAME_ATTRIBUTE = "subjectUsername";
  protected final static String RESOURCE_USERNAME_ATTRIBUTE = "resourceUsername";
  
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
      request.setAttribute(RESOURCE_USERNAME_ATTRIBUTE, addDomain(getTarget(request)));
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
    chain.doFilter(request, response);
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
    String subjectUsername = session.getAttribute("username").toString();
    String resourceUsername = addDomain(getTarget(request));
//    if(!isUserAuthorizedForTarget(subjectUsername, resourceUsername)) {
//      return false;
//    }    
    request.setAttribute(SUBJECT_USERNAME_ATTRIBUTE, subjectUsername);
    request.setAttribute(RESOURCE_USERNAME_ATTRIBUTE, resourceUsername);
    return true;
  }

  private boolean authenticateWithCookie(HttpServletRequest request){
    Cookie authCookieFromRequest = getAuthCookieFromRequest(request);
    if(authCookieFromRequest == null){
      return false;
    }
    
    String subjectUsername = getUsernameFromCookie(authCookieFromRequest);
    String resourceUsername = addDomain(getTarget(request));
    
    Cookie cookieFromStorage = (subjectUsername == null)? null : cookies.get(subjectUsername);
    
    if(cookieFromStorage == null || !authCookieFromRequest.getValue().equals(cookieFromStorage.getValue())){
      return false;
    }    
    
    request.setAttribute(SUBJECT_USERNAME_ATTRIBUTE, subjectUsername);
    request.setAttribute(RESOURCE_USERNAME_ATTRIBUTE, resourceUsername);
    return true;
  }
  
  @Override
  protected boolean authenticateWithUsernamePassword(HttpServletRequest request, HttpServletResponse response) throws IOException{
    String auth = request.getHeader("authorization");
    String[] credentials = decode(auth);
    if (credentials == null || credentials.length != 2) {
      response.sendError(Response.Status.UNAUTHORIZED.getStatusCode());
      return false;
    }
    String subjectUsername = addDomain(credentials[0]);
    String resourceUsername = addDomain(getTarget(request)); 
    
//    if (!isUserAuthorizedForTarget(credentials[0], getTarget(request))) {
//      response.sendError(Response.Status.FORBIDDEN.getStatusCode());
//      return false;
//    }
    
    UserManager manager = UserManager.getInstance();
    try {
      if (!manager.verifyCredentials(subjectUsername, credentials[1])) {
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
    
    request.setAttribute(SUBJECT_USERNAME_ATTRIBUTE, subjectUsername);
    request.setAttribute(RESOURCE_USERNAME_ATTRIBUTE, resourceUsername);
    return true;
    
  }
  
  private String getUsernameFromCookie(Cookie cookie){
    String s;
    try {
      s = new String(Base64.decode(cookie.getValue()));
    } catch (IOException e) {
      log.error(e.getMessage());
      return null;
    }
    String[] splitted = s.split("-username:");
    return splitted[1];
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
    String authToken = createRandomAuthToken("-username:"+addDomain(username));
    Cookie cookie = new Cookie(COOKIE_NAME, authToken);
    cookie.setSecure(true);
//  TODO:  cookie.setHttpOnly(true);
    cookie.setMaxAge(365 * 24 * 60 * 60);
    cookie.setPath("/");
    cookies.put(addDomain(username), cookie);
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
    cookies.remove(addDomain(username));
  }
  
  private String addDomain(String username) {
    InterfaceConfiguration configuration = null;
    if (!username.contains("@")) {
      configuration = InterfaceConfiguration.getInstance();
      username = username + "@" + configuration.getDomain();
    }
    return username;
  }
  
}
