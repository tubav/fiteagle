package org.fiteagle.core.userdatabase;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class User {
	
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	@JsonIgnore
	private String passwordHash;
	@JsonIgnore
	private String passwordSalt;
	private List<String> publicKeys;

	public User(String username, String firstName, String lastName, String email, String passwordHash, String passwordSalt, List<String> publicKeys){
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.passwordHash = passwordHash;
		this.passwordSalt =  passwordSalt;
		if(publicKeys == null){
		  this.publicKeys = new ArrayList<>();
		}
		else{
		  this.publicKeys = publicKeys;
		}		
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

  public List<String> getPublicKeys() {
		return publicKeys;
	}

	public void setPublicKeys(List<String> publicKeys) {
		this.publicKeys = publicKeys;
	}
	
	public void addPublicKey(String publicKey){
		if(!this.publicKeys.contains(publicKey)){
			this.publicKeys.add(publicKey);
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (publicKeys == null) {
			if (other.publicKeys != null)
				return false;
		} else if (!publicKeys.equals(other.publicKeys))
			return false;
		return true;
	}
	
	@Override
  public String toString() {
    return "User [username=" + username + ", firstName=" + firstName + ", lastName="
        + lastName + ", publicKeys=" + publicKeys + "]";
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public String getPasswordSalt() {
    return passwordSalt;
  }
}
