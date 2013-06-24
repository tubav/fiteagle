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

public class AuthenticationFilter implements Filter{

  private final static String COOKIE_NAME = "fiteagle_user_cookie";
  private HashMap<String, Cookie> cookies = new HashMap<>();
  
  private UserDBManager manager = null;
  
  private Logger log = LoggerFactory.getLogger(getClass());
  
  
  @Override
  public void destroy() {   
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
    
    if(!authenticateWithCookie(request)){
      if(!authenticateWithUsernameAndPassword(request, response)){
        return;
      }
      
      String username = getUsernameFromRequest(request); 
      if(username != null && getAuthCookieFromRequest(request) == null){
        response.addCookie(createNewCookie(username));
      }
    }
    
    chain.doFilter(req, resp);
    
    //TODO: when to remove cookie?
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {    
  }
  
  private String[] decode(String auth){ 
    if(auth == null || (!auth.startsWith("Basic ") && !auth.startsWith("basic "))){
      return null;
    }
    auth = auth.replaceFirst("[B|b]asic ", "");
    byte[] decoded = DatatypeConverter.parseBase64Binary(auth);
    if(decoded == null || decoded.length == 0){
      return null;
    }
    return new String(decoded).split(":", 2);
  }
  
  private boolean authenticateWithUsernameAndPassword(HttpServletRequest request, HttpServletResponse response){
    String auth = request.getHeader("authorization");  

    String[] credentials = decode(auth);    
    if(credentials == null || credentials.length != 2){      
      response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());      
      return false;
    }

    if(!getUsernameFromRequest(request).equals(credentials[0])){
      response.setStatus(Response.Status.FORBIDDEN.getStatusCode());      
      return false;
    }

    manager = UserDBManager.getInstance();
    try {
      if(!manager.verifyCredentials(credentials[0], credentials[1])){
        response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());      
        return false;
      }
    } catch (NoSuchAlgorithmException | IOException | DatabaseException e) {
        log.error(e.getMessage());
        response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());      
        return false;
    } catch (RecordNotFoundException e){
      response.setStatus(Response.Status.NOT_FOUND.getStatusCode());      
      return false;       
    }
    return true;
  }

  private boolean authenticateWithCookie(HttpServletRequest request){
    String username = getUsernameFromRequest(request);
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

  private String getUsernameFromRequest(HttpServletRequest req) {
    String path = req.getPathTranslated();
    String[] splitted = path.split("/");
    for(int i = 0; i < splitted.length -1; i++){
      if(splitted[i].equals("user")){
        return splitted[i+1];
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

  private String createRandomAuthToken() {
    String u = UUID.randomUUID().toString();    
    return new String(Base64.encode(u.getBytes()));
  }
  
  public static String getCookieName(){
    return COOKIE_NAME;
  }
  
}
