package org.fiteagle.delivery.rest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.fiteagle.core.userdatabase.UserDB.DatabaseException;
import org.fiteagle.core.userdatabase.UserDB.RecordNotFoundException;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class AuthenticationFilter implements ContainerRequestFilter{

  @Context
  private HttpServletRequest request;
  
  private UserDBManager manager = null;
  private Logger log = LoggerFactory.getLogger(getClass());
  
	@Override
	public ContainerRequest filter(ContainerRequest req) {
	  if(!req.getSecurityContext().isSecure()){
	    throw new WebApplicationException(Response.Status.BAD_REQUEST);
	  }
	  
		String method = req.getMethod();    
    if(method.equals("PUT")){
      return req;
    }
		String auth = req.getHeaderValue("authorization");		
		String[] credentials = decode(auth);		
		if(credentials == null || credentials.length != 2){
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
		
//		String loggedUser = (String) this.request.getSession().getAttribute("LOGGED_USER");		
//		if(loggedUser != null && loggedUser.equals(credentials[0])){
//		  //TODO: safe?
//		  return req;
//		}
		
		manager = UserDBManager.getInstance();
		try {
      if(!manager.verifyCredentials(credentials[0], credentials[1])){
      	throw new WebApplicationException(Response.Status.UNAUTHORIZED);
      }
    } catch (NoSuchAlgorithmException | IOException | DatabaseException e) {
        log.error(e.getMessage());
        throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    } catch (RecordNotFoundException e){
      throw new WebApplicationException(Response.Status.UNAUTHORIZED);       
    }

		if(!req.getPathSegments().get(1).getPath().equals(credentials[0])){
		    throw new WebApplicationException(Response.Status.UNAUTHORIZED); 
		}
		
		//TODO: send cookie back?
		//System.out.println(request.getSession().getId());
		//this.request.getSession().setAttribute("LOGGED_USER", credentials[0]);

		return req;
	}
	
	private String[] decode(String auth){	
		if(auth == null || (!auth.startsWith("Basic ") && !auth.startsWith("basic "))){
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
		auth = auth.replaceFirst("[B|b]asic ", "");
		byte[] decoded = DatatypeConverter.parseBase64Binary(auth);
		if(decoded == null || decoded.length == 0){
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
		return new String(decoded).split(":", 2);
	}
}