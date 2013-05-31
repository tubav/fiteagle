package org.fiteagle.delivery.rest;

import java.util.List;


public class NewUser {
  
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private List<String> publicKeys;
  
  public NewUser(){};
  
  public NewUser(String username, String firstName, String lastName, String email, String password, List<String> publicKeys) {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.publicKeys = publicKeys;
  }  
  
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  public List<String> getPublicKeys() {
	return publicKeys;
  }

  public void setPublicKeys(List<String> publicKeys) {
	this.publicKeys = publicKeys;
  }
 
}
