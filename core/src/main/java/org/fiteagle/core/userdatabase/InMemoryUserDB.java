package org.fiteagle.core.userdatabase;

import java.util.HashMap;

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
		checkForDuplicateEmail(u);
	  u.checkAttributes();
		users.put(u.getUsername(), u);
	}

  private void checkForDuplicateEmail(User newUser) {
    for(User user : users.values()){
		  if(user.getEmail().equals(newUser.getEmail()) && user.getUsername() != newUser.getUsername()){
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
	public void update(User u) throws UserNotFoundException, DuplicateEmailException, NotEnoughAttributesException, InValidAttributeException, DuplicatePublicKeyException {
		if(users.get(u.getUsername()) == null)
			throw new UserNotFoundException();
		checkForDuplicateEmail(u);
	  User newUser = get(u.getUsername());
	  newUser.mergeWithUser(u);		 
	  users.put(u.getUsername(), newUser);    
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
}