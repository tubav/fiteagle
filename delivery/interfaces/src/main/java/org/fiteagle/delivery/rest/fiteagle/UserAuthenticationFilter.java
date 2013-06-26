package org.fiteagle.delivery.rest.fiteagle;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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

public class UserAuthenticationFilter extends TempAuthenticationFilter{

  private final static String COOKIE_NAME = "fiteagle_user_cookie";
  private HashMap<String, Cookie> cookies = new HashMap<>();
  
 
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
    
    if(!authenticateWithCookie(request)){
      if(!authenticateWithUsernamePassword(request, response)){
        return;
      }
      
      String username = getTarget(request); 
      if(username != null && getAuthCookieFromRequest(request) == null){
        response.addCookie(createNewCookie(username));
      }
    }
    
    chain.doFilter(req, resp);
    
    //TODO: when to remove cookie?
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
    String authToken = createRandomAuthToken();
    Cookie cookie = new Cookie(COOKIE_NAME, authToken);
    cookie.setSecure(true);
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





  
  
}
