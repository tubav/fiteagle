package org.fiteagle.core.userdatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.iharder.Base64;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.fiteagle.core.userdatabase.UserPersistable.InValidAttributeException;
import org.fiteagle.core.userdatabase.UserPersistable.NotEnoughAttributesException;

public class User {
	
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String affiliation;
	@JsonIgnore
	private Date created;
	@JsonIgnore
	private Date last_modified;
	@JsonIgnore
	private String passwordHash;
	@JsonIgnore
	private String passwordSalt;
	private List<String> publicKeys;

	private final static int MINIMUM_PASSWORD_LENGTH = 3;
	private final static int MINIMUM_USERNAME_LENGTH = 3;
	private final static int MINIMUM_FIRST_AND_LASTNAME_LENGTH = 3;
  private final static int MINIMUM_AFFILITAION_LENGTH = 2;

	
	public User(String username, String firstName, String lastName, String email, String affiliation, String passwordHash, String passwordSalt, Date created, Date lastModified, List<String> publicKeys){
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.affiliation = affiliation;
		this.passwordHash = passwordHash;
		this.passwordSalt =  passwordSalt;
		this.created = created;
		this.last_modified = lastModified;
		if(publicKeys == null){
		  this.publicKeys = new ArrayList<>();
		}
		else{
		  this.publicKeys = publicKeys;
		}		
	}
	
	public User(String username, String firstName, String lastName, String email, String affiliation, String password) throws NoSuchAlgorithmException{ 
	  this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.affiliation = affiliation;
    this.created = Calendar.getInstance().getTime();
    this.last_modified = created;
    byte[] salt = generatePasswordSalt();
    this.passwordSalt = Base64.encodeBytes(salt);        
    this.passwordHash = generatePasswordHash(salt, password);
    
    this.publicKeys = new ArrayList<String>();
	}
	
	public User(String username, String firstName, String lastName, String email, String affiliation, String password, List<String> publicKeys) throws NoSuchAlgorithmException{ 
	  this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.affiliation = affiliation;
    this.created = Calendar.getInstance().getTime();
    this.last_modified = created;
    byte[] salt = generatePasswordSalt();
    this.passwordSalt = Base64.encodeBytes(salt);        
    this.passwordHash = generatePasswordHash(salt, password);
    
    if(publicKeys == null){
      this.publicKeys = new ArrayList<>();
    }
    else{
      this.publicKeys = publicKeys;
    }   
	}
	
	public void checkAttributes() throws NotEnoughAttributesException, InValidAttributeException{  
	  if(username == null){
	    throw new UserPersistable.NotEnoughAttributesException("no username given");
	  }
	  if(firstName == null){
	    throw new UserPersistable.NotEnoughAttributesException("no firstName given");
	  }
	  if(lastName == null){
      throw new UserPersistable.NotEnoughAttributesException("no lastName given");
    }
	  if(email == null){
      throw new UserPersistable.NotEnoughAttributesException("no email given");
    }
	  if(affiliation == null){
      throw new UserPersistable.NotEnoughAttributesException("no affiliation given");
    }	  
	  if(passwordHash == null){
      throw new UserPersistable.NotEnoughAttributesException("no password given or password too short");
    }   
	  
	  if(username.length() < MINIMUM_USERNAME_LENGTH){
	    throw new UserPersistable.InValidAttributeException("username too short");
	  }
	  if(firstName.length() < MINIMUM_FIRST_AND_LASTNAME_LENGTH){
      throw new UserPersistable.InValidAttributeException("firstName too short");
    }
	  if(lastName.length() < MINIMUM_FIRST_AND_LASTNAME_LENGTH){
      throw new UserPersistable.InValidAttributeException("lastName too short");
    }
	  if(!email.contains("@") || !email.contains(".")){
      throw new UserPersistable.InValidAttributeException("an email needs to contain \"@\" and \".\"");
    }
	  if(affiliation.length() < MINIMUM_AFFILITAION_LENGTH){
      throw new UserPersistable.InValidAttributeException("affiliation too short");
    }	 
  }
	
	private byte[] generatePasswordSalt(){
	  SecureRandom random = new SecureRandom();
    return random.generateSeed(20);
	}
	
	private String generatePasswordHash(byte[] salt, String password) throws NoSuchAlgorithmException{
	  if(password == null || password.length() < MINIMUM_PASSWORD_LENGTH){
	    return null;
	  }
    
	  byte[] passwordBytes = createHash(salt, password);
    return Base64.encodeBytes(passwordBytes);
	}
	
  private byte[] createHash(byte[] salt, String password) throws NoSuchAlgorithmException {    
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    digest.reset();
    digest.update(salt);
    return digest.digest(password.getBytes());
  }
	
  public void mergeWithUser(User newUser){
    if(newUser.getFirstName() != null){
     this.firstName = newUser.getFirstName();
    }
    if(newUser.getLastName() != null){
      this.lastName = newUser.getLastName();
    }
    if(newUser.getPublicKeys() != null && newUser.getPublicKeys().size() != 0){
      this.publicKeys = newUser.getPublicKeys();
    }
    if(newUser.getEmail() != null){
      this.email = newUser.getEmail();
    }
    if(newUser.getAffiliation() != null){
      this.affiliation = newUser.getAffiliation();
    }
    if(newUser.getPasswordHash() != null){
      this.passwordSalt = newUser.getPasswordSalt();
      this.passwordHash = newUser.getPasswordHash();
    }
    this.setLast_modified(Calendar.getInstance().getTime());
    this.checkAttributes();      
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

  public String getAffiliation() {
    return affiliation;
  }

  public void setAffiliation(String affiliation) {
    this.affiliation = affiliation;
  }  

  public String getPasswordHash() {
    return passwordHash;
  }

  public String getPasswordSalt() {
    return passwordSalt;
  }
    
  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public void setPasswordSalt(String passwordSalt) {
    this.passwordSalt = passwordSalt;
  }

  public Date getLast_modified() {
    return last_modified;
  }

  public void setLast_modified(Date last_modified) {
    this.last_modified = last_modified;
  }

  public Date getCreated() {
    return created;
  }

  public List<String> getPublicKeys() {
		return publicKeys;
	}

	public void setPublicKeys(List<String> publicKeys) {
		this.publicKeys = publicKeys;
	}
	
	public void addPublicKey(String publicKey){
		if(this.publicKeys.contains(publicKey)){
			throw new UserPersistable.DuplicatePublicKeyException();
		}
		this.publicKeys.add(publicKey);
	}
	
	public void deletePublicKey(String publicKey){    
    this.publicKeys.remove(publicKey);
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
    if (affiliation == null) {
      if (other.affiliation != null)
        return false;
    } else if (!affiliation.equals(other.affiliation))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
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
    if (passwordHash == null) {
      if (other.passwordHash != null)
        return false;
    } else if (!passwordHash.equals(other.passwordHash))
      return false;
    if (passwordSalt == null) {
      if (other.passwordSalt != null)
        return false;
    } else if (!passwordSalt.equals(other.passwordSalt))
      return false;
    if (publicKeys == null) {
      if (other.publicKeys != null)
        return false;
    } else if (!publicKeys.equals(other.publicKeys))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }
	
	@Override
  public String toString() {
    return "User [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
        + ", affiliation=" + affiliation + ", created=" + created + ", last_modified=" + last_modified
        + ", publicKeys=" + publicKeys + "]";
  }
  
}
