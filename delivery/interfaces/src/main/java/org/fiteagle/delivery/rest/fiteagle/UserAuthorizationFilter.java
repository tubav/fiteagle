package org.fiteagle.delivery.rest.fiteagle;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.fiteagle.core.config.InterfaceConfiguration;
import org.fiteagle.interactors.api.PolicyEnforcementPointBoundary;
import org.fiteagle.interactors.authorization.PolicyEnforcementPoint;

public class UserAuthorizationFilter implements Filter {

  private static UserAuthorizationFilter instance = new UserAuthorizationFilter();
  
  private UserAuthorizationFilter(){};
  
  public static UserAuthorizationFilter getInstance(){
    return instance;
  }
  
  private PolicyEnforcementPointBoundary policyEnforcementPoint = PolicyEnforcementPoint.getInstance();
  
  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
      ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;
    
    String auth = request.getHeader("authorization");
    String[] credentials = decode(auth);
    String subjectUsername;
    if(credentials != null && credentials[0] != null){
      subjectUsername = addDomain(credentials[0]);
    }
    else{
      subjectUsername = "";
    }
    
    String resourceUsername = addDomain(getUsername(request));
    
    String action = request.getMethod(); 
//    try {
//      if(!policyEnforcementPoint.isRequestAuthorized(subjectUsername, resourceUsername, action, "user")){
//        response.sendError(Response.Status.FORBIDDEN.getStatusCode());
//        return; 
//      }
//    } catch (URISyntaxException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
    
    chain.doFilter(request, response);
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
  
  private String getUsername(HttpServletRequest request) {
    String path = request.getRequestURI();    
    String[] splitted = path.split("/");
    for (int i = 0; i < splitted.length - 1; i++) {
      if (splitted[i].equals("user")) {
        return splitted[i+1];
      }
    }
    return null;
  } 
  
  private String addDomain(String username) {

    InterfaceConfiguration configuration = null;
    if (!username.contains("@")) {
      configuration = InterfaceConfiguration.getInstance();
      username = username + "@" + configuration.getDomain();
    }
    return username;
  }
  
  
  @Override
  public void init(FilterConfig arg0) throws ServletException {
    
  }
  
  @Override
  public void destroy() {
    
  }


}
