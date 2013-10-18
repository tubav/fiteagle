package org.fiteagle.interactors.authorization;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.fiteagle.interactors.api.PolicyEnforcementPointBoundary;

import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.Subject;

public class PolicyEnforcementPoint implements PolicyEnforcementPointBoundary {
  
  private static URI SUBJECT_ID;
  private static URI RESOURCE_ID;
  private static URI ACTION_ID;
  
  static{
    try {
      SUBJECT_ID = new URI("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
      RESOURCE_ID = new URI("urn:oasis:names:tc:xacml:1.0:resource:resource-id");
      ACTION_ID = new URI("urn:oasis:names:tc:xacml:1.0:action:action-id");
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  private static PolicyEnforcementPoint instance = new PolicyEnforcementPoint();
  
  public static PolicyEnforcementPoint getInstance(){
    return instance;
  }
  
  private PolicyEnforcementPoint(){};  
  
  private static PolicyDecisionPoint policyDecisionPoint = PolicyDecisionPoint.getInstance();
  
  @Override
  public boolean isRequestAuthorized(String username) throws URISyntaxException{
    RequestCtx request = createRequest(username);

    
    
    return policyDecisionPoint.evaluateRequest(request);
  }

  public RequestCtx createRequest(String username) throws URISyntaxException {
    RequestCtx request = 
        new RequestCtx(
            setSubject(username),
            setResource(),
            setAction(),
            setEnvironment());
    return request;
  }

  private Set<Subject> setSubject(String username) throws URISyntaxException {
    HashSet<Attribute> attributes = new HashSet<Attribute>();

    attributes.add(new Attribute(SUBJECT_ID, null, null, new StringAttribute(username)));

    attributes.add(new Attribute(new URI("group"), null, null, new StringAttribute("admin")));

    HashSet<Subject> subjects = new HashSet<Subject>();
    subjects.add(new Subject(attributes));

    return subjects;
  }

  private Set<Attribute> setResource() throws URISyntaxException {
    HashSet<Attribute> resource = new HashSet<Attribute>();

    resource.add(new Attribute(RESOURCE_ID, null, null, new StringAttribute("whatever")));

    return resource;
  }
  private Set<Attribute> setAction() throws URISyntaxException {
    HashSet<Attribute> action = new HashSet<Attribute>();

    action.add(new Attribute(ACTION_ID, null, null, new StringAttribute("GET")));

    return action;
  }
  
  private Set<Attribute> setEnvironment() throws URISyntaxException {
    HashSet<Attribute> environment = new HashSet<Attribute>();
    return environment;
  }
  
}
