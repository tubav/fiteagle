package org.fiteagle.delivery.rest.fiteagle;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.fiteagle.core.userdatabase.User.Role;
import org.fiteagle.interactors.api.PolicyEnforcementPointBoundary;
import org.fiteagle.interactors.authorization.PolicyEnforcementPoint;
import org.fiteagle.interactors.usermanagement.UserManager;

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
    
    String subjectUsername = (String) request.getAttribute(UserAuthenticationFilter.SUBJECT_USERNAME_ATTRIBUTE);
    String resourceUsername = (String) request.getAttribute(UserAuthenticationFilter.RESOURCE_USERNAME_ATTRIBUTE);
    String action = (String) request.getAttribute(UserAuthenticationFilter.ACTION_ATTRIBUTE);
    Role role = Role.USER;
    if(subjectUsername != null){
      role = UserManager.getInstance().get(subjectUsername).getRole();
    }
    Boolean isAuthenticated = (Boolean) request.getAttribute(UserAuthenticationFilter.IS_AUTHENTICATED_ATTRIBUTE);
    Boolean requiresAdminRights = requiresAdminRights(request);
    
    if(!policyEnforcementPoint.isRequestAuthorized(subjectUsername, resourceUsername, action, role.name(), isAuthenticated, requiresAdminRights)){
      if(isAuthenticated){
        response.sendError(Response.Status.FORBIDDEN.getStatusCode());
      }
      else{
        response.sendError(Response.Status.UNAUTHORIZED.getStatusCode());
      }
      return; 
    }
    
    chain.doFilter(request, response);
  }

  private Boolean requiresAdminRights(HttpServletRequest request) {
    if(request.getRequestURI().endsWith("/role/ADMIN")){
      return true;
    }
    return false;
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
    
  }
  
  @Override
  public void destroy() {
    
  }


}
