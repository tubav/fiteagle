package org.fiteagle.core.userdatabase;

import java.util.HashMap;
import java.util.List;

public class InMemoryUserDB implements UserPersistable {

  private HashMap<String, User> users;

	public InMemoryUserDB(){
		users = new HashMap<String, User>();
	}

	public int getNumberOfUsers(){
		return users.size();
	}

	@Override
	public void add(User u) throws DuplicateUsernameException, DuplicateEmailException, NotEnoughAttributesException, InValidAttributeException, DuplicatePublicKeyException {
		if(users.get(u.getUsername()) != null)
			throw new DuplicateUsernameException();
		checkForDuplicateEmail(u.getUsername(), u.getEmail());
		users.put(u.getUsername(), u);
	}

  private void checkForDuplicateEmail(String username, String email) {
    for(User user : users.values()){
		  if(user.getEmail().equals(email) && user.getUsername() != username){
		    throw new DuplicateEmailException();
		  }
		}
  }

	@Override
	public void delete(String username){
		users.remove(username);			
	}

	@Override
	public void delete(User u){
		delete(u.getUsername());
	}

	@Override
	public void update(String username, String firstName, String lastName, String email, String affiliation, String password, List<UserPublicKey> publicKeys) throws UserNotFoundException, DuplicateEmailException, NotEnoughAttributesException, InValidAttributeException, DuplicatePublicKeyException {
		if(users.get(username) == null)
			throw new UserNotFoundException();
		checkForDuplicateEmail(username, email);
	  User user = get(username);
	  user.update(firstName, lastName, email, affiliation, password, publicKeys);		 
	  users.put(username, user);    
	}

	@Override
	public User get(String username) throws UserNotFoundException {
		if(users.get(username) == null)
			throw new UserNotFoundException();
		return users.get(username);		
	}

	@Override
	public User get(User u) throws UserNotFoundException {
		return get(u.getUsername());		
	}

	@Override
	public void addKey(String username, UserPublicKey key) throws UserNotFoundException, InValidAttributeException, DuplicatePublicKeyException {
	  User u = get(username);		
		u.addPublicKey(key);
	}
	
	@Override
	public void deleteKey(String username, String description) throws UserNotFoundException, DatabaseException{	  
	  User u = get(username);    
    u.deletePublicKey(description);
	}	
	
	@Override
  public void renameKey(String username, String description, String newDescription) throws UserNotFoundException, DatabaseException, DuplicatePublicKeyException, InValidAttributeException, PublicKeyNotFoundException {
    User u = get(username);    
    u.renamePublicKey(description, newDescription);
  } 
}