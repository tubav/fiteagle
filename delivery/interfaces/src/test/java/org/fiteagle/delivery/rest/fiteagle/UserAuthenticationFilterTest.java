package org.fiteagle.delivery.rest.fiteagle;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymock.EasyMock;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.fiteagle.core.userdatabase.JPAUserDB.UserNotFoundException;
import org.fiteagle.interactors.api.UserManagerBoundary;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.core.util.Base64;

public class UserAuthenticationFilterTest {
  
  private static UserAuthenticationFilter filter;
  private static HttpServletRequest req;
  private static HttpServletResponse resp;
  private static FilterChain chain;
  
  private static UserManagerBoundary userManager;
  
  

  @Before
  public void createMocks() throws DatabaseException, NoSuchAlgorithmException, UserNotFoundException, IOException{
    req = createMock(HttpServletRequest.class);
    resp = createMock(HttpServletResponse.class);   
    chain = createMock(FilterChain.class);  
    userManager = createMock(UserManagerBoundary.class);
    userManager.delete(EasyMock.anyObject(String.class));
    expect(userManager.verifyCredentials(EasyMock.anyObject(String.class), EasyMock.anyObject(String.class))).andReturn(true);
    replay(userManager);
    filter = new UserAuthenticationFilter();
    filter.setUserManager(userManager);
  }
  
  @Test
  public void testAuthenticateWithUserNameAndPassword() throws IOException, ServletException{
    expect(req.isSecure()).andReturn(true);
    
    String auth = "Basic "+new String(Base64.encode("test:test".getBytes()));
    expect(req.getHeader("authorization")).andReturn(auth); 
    
    expect(req.getRequestURI()).andReturn("/api/v1/user/test"); 
    expectLastCall().times(3);
    
    expect(req.getMethod()).andReturn("GET");
    expectLastCall().times(3);
    
    expect(req.getParameter("setCookie")).andReturn(null);
    expect(req.getSession(false)).andReturn(null);
    expect(req.getSession(true)).andReturn(null);
    
    expect(req.getCookies()).andReturn(null);
    
    chain.doFilter(req, resp);
    
    replay(req, chain);
    
    filter.doFilter(req, resp, chain);
    verify(req, chain);
  }
  
  @Test
  public void testAuthenticateWithCookie() throws IOException, ServletException{
    Cookie cookie = new Cookie("fiteagle_user_cookie", "weargq32e1dwq");
    filter.saveCookie("test", cookie);  
    
    expect(req.getMethod()).andReturn("GET");
    expectLastCall().times(3);
    
    expect(req.isSecure()).andReturn(true);
    
    expect(req.getCookies()).andReturn(new Cookie[]{cookie});
    
    expect(req.getRequestURI()).andReturn("/api/v1/user/test");
    expectLastCall().times(2);
    
    expect(req.getParameter("setCookie")).andReturn(null);
    expect(req.getSession(false)).andReturn(null);
    expect(req.getSession(true)).andReturn(null);
    
    chain.doFilter(req, resp);
    
    replay(req, chain);
    
    filter.doFilter(req, resp, chain);
    verify(req, chain);
  }
  
  @AfterClass
  public static void cleanUp(){
    try{
      userManager.delete("test");
    } catch(UserNotFoundException e){}
  }
}
