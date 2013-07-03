package org.fiteagle.delivery.rest.fiteagle;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewPublicKey {
  
  private String publicKey;  
  private String description;  
  
  public NewPublicKey(){};   

  public String getPublicKey() {
    return publicKey;
  }
  
  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}

