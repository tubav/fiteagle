package org.fiteagle.core.userdatabase;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.fiteagle.core.aaa.KeyManagement;
import org.fiteagle.core.aaa.KeyManagement.CouldNotParse;
import org.fiteagle.core.userdatabase.UserPersistable.NotEnoughAttributesException;

public class UserPublicKey {
  
  @JsonIgnore
  private PublicKey publicKey;  
  private String description;
  private String publicKeyString; 
  private Date created;  
  
  private KeyManagement keyManager = KeyManagement.getInstance();
  
  public UserPublicKey(PublicKey publicKey, String description) throws NotEnoughAttributesException, IOException {
    this.publicKey = publicKey;
    this.publicKeyString = keyManager.encodePublicKey(publicKey);
    this.created = new Date();
    
    this.description = description;  
    checkDescription();    
    
    this.publicKeyString = keyManager.encodePublicKey(publicKey);
  } 
  
  public UserPublicKey(String publicKeyString, String description, Date created) throws NotEnoughAttributesException, InvalidKeySpecException, NoSuchAlgorithmException, IOException {
    this.publicKeyString = publicKeyString;
    checkPublicKeyString();
    this.publicKey = keyManager.decodePublicKey(publicKeyString);  
    
    this.description = description;  
    checkDescription();     
    
    this.created = created;    
    if(created == null){
      this.created = new Date();
    }       
  }
  
  public UserPublicKey(String publicKeyString, String description) throws NotEnoughAttributesException, InvalidKeySpecException, NoSuchAlgorithmException, IOException, CouldNotParse{    
    this.publicKeyString = publicKeyString;
    checkPublicKeyString();
    this.publicKey = keyManager.decodePublicKey(publicKeyString);    
    this.created = new Date();    
    
    this.description = description;      
    checkDescription();    
  }

  private void checkDescription() throws NotEnoughAttributesException {
    if(description == null || description.length() == 0){
      throw new NotEnoughAttributesException("no description for public key given");
    }
  }   
  
  private void checkPublicKeyString() throws NotEnoughAttributesException {
    if(publicKeyString == null || publicKeyString.length() == 0){
      throw new NotEnoughAttributesException("no publicKeyString given");
    }
  }   
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserPublicKey other = (UserPublicKey) obj;
    if (publicKey == null) {
      if (other.publicKey != null)
        return false;
    } else if (!publicKey.equals(other.publicKey))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "PublicKey [publicKey=" + publicKey + ", description=" + description + ", created=" + created + "]";
  }

  public PublicKey getPublicKey() {
    return publicKey;
  }  

  public void setPublicKey(PublicKey publicKey) {
    this.publicKey = publicKey;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public String getPublicKeyString() {
    return publicKeyString;
  }

  public void setPublicKeyString(String publicKeyString) {
    this.publicKeyString = publicKeyString;
  }  
}
