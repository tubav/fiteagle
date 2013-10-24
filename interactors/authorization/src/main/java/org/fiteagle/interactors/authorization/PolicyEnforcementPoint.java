package org.fiteagle.interactors.authorization;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.fiteagle.interactors.api.PolicyEnforcementPointBoundary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.xacml.Indenter;
import com.sun.xacml.attr.BooleanAttribute;
import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.Subject;

public class PolicyEnforcementPoint implements PolicyEnforcementPointBoundary {
  
  private static Logger log = LoggerFactory.getLogger(PolicyEnforcementPoint.class);
  
  private static URI SUBJECT_ID;
  private static URI RESOURCE_ID;
  private static URI ACTION_ID;
  
  static{
    try {
      SUBJECT_ID = new URI("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
      RESOURCE_ID = new URI("urn:oasis:names:tc:xacml:1.0:resource:resource-id");
      ACTION_ID = new URI("urn:oasis:names:tc:xacml:1.0:action:action-id");
    } catch (URISyntaxException e) {
      log.error(e.getMessage());
    }
  }
  
  private static PolicyEnforcementPoint instance = new PolicyEnforcementPoint();
  
  public static PolicyEnforcementPoint getInstance(){
    return instance;
  }
  
  private PolicyEnforcementPoint(){};  
  
  private static PolicyDecisionPoint policyDecisionPoint = PolicyDecisionPoint.getInstance();
  
  @Override
  public boolean isRequestAuthorized(String subjectUsername, String resourceUsername, String action, String role, Boolean isAuthenticated) {
    RequestCtx request = createRequest(subjectUsername, resourceUsername, action , role, isAuthenticated);

    if(log.isDebugEnabled()){
      request.encode(System.out, new Indenter());
    }
    
    return policyDecisionPoint.evaluateRequest(request);
  }

  private RequestCtx createRequest(String subjectUsername, String resourceUsername, String action, String role, Boolean isAuthenticated) {
    RequestCtx request = null;
    try {
      request = new RequestCtx(
          setSubject(subjectUsername, role),
          setResource(resourceUsername),
          setAction(action),
          setEnvironment(isAuthenticated));
    } catch (URISyntaxException e) {
      log.error(e.getMessage());
    }
    return request;
  }

  private Set<Subject> setSubject(String subjectUsername, String role) throws URISyntaxException {
    HashSet<Attribute> attributeSet = new HashSet<Attribute>();

    attributeSet.add(new Attribute(SUBJECT_ID, null, null, new StringAttribute(subjectUsername)));

    attributeSet.add(new Attribute(new URI("role"), null, null, new StringAttribute(role)));

    HashSet<Subject> subjects = new HashSet<Subject>();
    subjects.add(new Subject(attributeSet));

    return subjects;
  }

  private Set<Attribute> setResource(String resourceUsername) throws URISyntaxException {
    HashSet<Attribute> resourceSet = new HashSet<Attribute>();

    resourceSet.add(new Attribute(RESOURCE_ID, null, null, new StringAttribute(resourceUsername)));

    return resourceSet;
  }
  private Set<Attribute> setAction(String action) throws URISyntaxException {
    HashSet<Attribute> actionSet = new HashSet<Attribute>();

    actionSet.add(new Attribute(ACTION_ID, null, null, new StringAttribute(action)));

    return actionSet;
  }
  
  private Set<Attribute> setEnvironment(Boolean isAuthenticated) throws URISyntaxException {
    HashSet<Attribute> environmentSet = new HashSet<Attribute>();

    environmentSet.add(new Attribute(new URI("isAuthenticated"), null, null, BooleanAttribute.getInstance(isAuthenticated)));
    
    return environmentSet;
  }
  
}
