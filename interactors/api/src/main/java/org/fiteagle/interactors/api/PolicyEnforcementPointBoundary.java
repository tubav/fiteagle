package org.fiteagle.interactors.api;

import java.net.URISyntaxException;

public interface PolicyEnforcementPointBoundary {
  
  public abstract boolean isRequestAuthorized(String subjectUsername, String resourceUsername, String action, String role) throws URISyntaxException;
  
}