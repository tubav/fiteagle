package org.fiteagle.delivery.rest;


public class NewUser {
  
  private String UID;
  private String firstName;
  private String lastName;
  private String password;
  
  public NewUser(){};
  
  public NewUser(String uID, String firstName, String lastName, String password) {
    this.UID = uID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
  }
  
  public String getUID() {
    return UID;
  }
  public void setUID(String uID) {
    UID = uID;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  } 
 
}
