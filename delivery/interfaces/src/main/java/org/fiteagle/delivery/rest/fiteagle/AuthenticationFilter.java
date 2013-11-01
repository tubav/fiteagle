package org.fiteagle.delivery.rest.fiteagle;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import net.iharder.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AuthenticationFilter implements Filter {
  
  @SuppressWarnings("unused")
  private Logger log = LoggerFactory.getLogger(getClass());
  
  protected HashMap<String, Cookie> cookies = new HashMap<>();
  
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    
  }
  
  @Override
  public void destroy() {
    
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
  
  protected String getTargetNameFromURI(String path, String targetIdentifier) {
    String[] splitted = path.split("/");
    for (int i = 0; i < splitted.length - 1; i++) {
      if (splitted[i].equals(targetIdentifier)) {
        return splitted[i+1];
      }
    }
    return null;
  }
  
  protected String createRandomAuthToken(String postfix) {
    String u = UUID.randomUUID().toString();
    u+=postfix;
    return new String(Base64.encodeBytes(u.getBytes()));
  }
  
  protected void saveCookie(String target, Cookie cookie) {
    cookies.put(target, cookie);    
  }
  
  protected abstract String getTarget(HttpServletRequest request);
}
