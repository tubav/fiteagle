package org.fiteagle.core.userdatabase;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

public class PublicKey {
  
  private String publicKey;  
  private String description;
  @JsonIgnore
  private Date created;
  
  private static int id = 0; //TODO: Better default description
  
  public PublicKey(){};
  
  public PublicKey(String publicKey) {
    this.publicKey = publicKey;
    this.description = new Date().toString()+" id:"+id;
    this.created = new Date();
    id++;
  }
  
  public PublicKey(String publicKey, String description) {
    this.publicKey = publicKey;
    this.created = new Date();
    
    if(description == null || description.length() == 0){
      this.description = new Date().toString()+" id:"+id;
    }
    else{
      this.description = description;
    }    
    id++;
  }
  
  public PublicKey(String publicKey, String description, Date created) {
    this.publicKey = publicKey;
    
    if(description == null || description.length() == 0){
      this.description = new Date().toString()+" id:"+id;
    }
    else{
      this.description = description;
    }    
    
    if(created == null){
      this.created = new Date();
    }
    else{
      this.created = created;
    }
    id++;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PublicKey other = (PublicKey) obj;
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

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }
  
}
