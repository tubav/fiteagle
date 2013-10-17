package org.fiteagle.delivery.rest.fiteagle;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.groupmanagement.JPAGroupDB.CouldNotCreateGroup;
import org.fiteagle.core.userdatabase.JPAUserDB.UserNotFoundException;
import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.fiteagle.core.userdatabase.UserPublicKey;
import org.fiteagle.interactors.api.GroupManagerBoundary;
import org.fiteagle.interactors.api.UserManagerBoundary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GroupAuthenticationFilterTest {
  GroupAuthenticationFilter filter;
  HttpServletRequest req;
  HttpServletResponse resp ;
  FilterChain chain;
  GroupManagerBoundary groupManager;
  UserManagerBoundary userManager;
  @Before
  public void setUp() throws Exception {
     req = createMock(HttpServletRequest.class);
     filter = new GroupAuthenticationFilter();
     resp = createMock(HttpServletResponse.class);
     chain = createMock(FilterChain.class);
     groupManager = createMock(GroupManagerBoundary.class);
     expect(groupManager.getGroup("testGroup")).andReturn(new Group("testGroup", "test"));
     userManager = createMock(UserManagerBoundary.class);
     replay(userManager);
     replay(groupManager);
     filter.setGroupManager(groupManager);
     filter.setUserManager(userManager);
   
     
  }
  
  
  @Test
  public void testAuthenticateWithCookie() throws IOException, ServletException{
    Cookie cookie = new Cookie("fiteagle_group_cookie", "welt");
    filter.saveCookie("testGroup", cookie);
    expect(req.isSecure()).andReturn(true);
    expect(req.getCookies()).andReturn(new Cookie[]{cookie});
    expect(req.getRequestURI()).andReturn("/api/v1/group/testGroup");
    replay(req);
    chain.doFilter(req, resp);
    replay(chain);
    filter.doFilter(req, resp, chain);
    verify(chain);
  }
 
  
  @Test
  public void testAuthenticateWithUserNameAndPassword() throws IOException, ServletException{
    expect(req.isSecure()).andReturn(true);
    expect(req.getCookies()).andReturn(null);
    expect(req.getHeader("authorization")).andReturn("Basic dGVzdDp0ZXN0"); //user "test" password "test"
    expect(req.getRequestURI()).andReturn("/api/v1/group/testGroup");
    expectLastCall().times(2);
    replay(req);
    chain.doFilter(req, resp);
    replay(chain);
    filter.doFilter(req, resp, chain);
    verify(chain);
  }
 
  

}
