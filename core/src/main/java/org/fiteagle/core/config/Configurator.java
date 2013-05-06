package org.fiteagle.core.config;

public class Configurator {
  
  
  private GitRepositoryProperties gitProperties;
  public Configurator() {
    gitProperties = new GitRepositoryProperties();
  }
  
  
  
  public GitRepositoryProperties getGitProperties() {
    return  gitProperties ;
  
  }
  
  
  public String getCommitVersion(){
      return gitProperties.getDescribe();
  }
}
