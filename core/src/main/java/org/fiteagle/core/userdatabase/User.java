package org.fiteagle.core.userdatabase;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String UID;
	private String firstName;
	private String lastName;
	private String passwordHash;
	private String passwordSalt;
	private List<String> publicKeys;

	public User(String UID, String firstName, String lastName, String passwordHash, String passwordSalt, List<String> publicKeys){
		this.UID = UID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.publicKeys = publicKeys;
		this.passwordHash = passwordHash;
		this.passwordSalt =  passwordSalt;
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
		if (UID == null) {
			if (other.UID != null)
				return false;
		} else if (!UID.equals(other.UID))
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
    return "User [UID=" + UID + ", firstName=" + firstName + ", lastName="
        + lastName + ", publicKeys=" + publicKeys + "]";
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public String getPasswordSalt() {
    return passwordSalt;
  }
}
