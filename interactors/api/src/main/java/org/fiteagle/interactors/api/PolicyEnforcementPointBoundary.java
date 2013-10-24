package org.fiteagle.interactors.api;

public interface PolicyEnforcementPointBoundary {
  
  public abstract boolean isRequestAuthorized(String subjectUsername, String resourceUsername, String action, String role, Boolean isAuthenticated);
  
}