package org.fiteagle.core.config;

import java.io.IOException;
import java.util.Properties;

public class GitRepositoryProperties {
  
  private String branch;
  private String describe;
  private String commitId;
  private String buildUserName;
  private String buildUserEmail;
  private String buildTime;
  private String commitMessageShort;
  private String commitMessageFull;
  private String commitUserName;
  private String commitUserEmail;
  private String commitTime;
  
  public GitRepositoryProperties(){
    Properties properties = new Properties();
    try {
      properties.load(this.getClass().getResourceAsStream("/org/fiteagle/core/config/git.properties"));
      this.branch = properties.get("git.branch").toString();
      this.describe = properties.get("git.commit.id.describe").toString();
      this.commitId = properties.get("git.commit.id").toString();
      this.buildUserName = properties.get("git.build.user.name").toString();
      this.buildUserEmail = properties.get("git.build.user.email").toString();
      this.buildTime = properties.get("git.build.time").toString();
      this.commitUserName = properties.get("git.commit.user.name").toString();
      this.commitUserEmail = properties.get("git.commit.user.email").toString();
      this.commitMessageShort = properties.get("git.commit.message.short").toString();
      this.commitMessageFull = properties.get("git.commit.message.full").toString();
      this.commitTime = properties.get("git.commit.time").toString();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    
    
    
  }

  public String getBranch() {
    return branch;
  }

  public String getDescribe() {
    return describe;
  }

  public String getCommitId() {
    return commitId;
  }

  public String getBuildUserName() {
    return buildUserName;
  }

  public String getBuildUserEmail() {
    return buildUserEmail;
  }

  public String getBuildTime() {
    return buildTime;
  }

  public String getCommitMessageShort() {
    return commitMessageShort;
  }

  public String getCommitMessageFull() {
    return commitMessageFull;
  }

  public String getCommitUserName() {
    return commitUserName;
  }

  public String getCommitUserEmail() {
    return commitUserEmail;
  }

  public String getCommitTime() {
    return commitTime;
  }
}
