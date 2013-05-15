package org.fiteagle.core.userdatabase;

import java.util.HashMap;

public class InMemoryUserDB implements UserDB {

	private HashMap<String, User> users;

	public InMemoryUserDB(){
		users = new HashMap<String, User>();
	}

	public int getNumberOfUsers(){
		return users.size();
	}

	@Override
	public void add(User u) throws DuplicateUIDException {
		if(users.get(u.getUID()) != null){
			throw new DuplicateUIDException();
		}
		else{
			users.put(u.getUID(), u);
		}
	}	

	@Override
	public void delete(String UID){
		users.remove(UID);			
	}

	@Override
	public void delete(User u){
		delete(u.getUID());
	}

	@Override
	public void update(User u) throws RecordNotFoundException {
		if(users.get(u.getUID()) == null)
			throw new RecordNotFoundException();
		else
			users.put(u.getUID(), u);		
	}

	@Override
	public User get(String UID) throws RecordNotFoundException {
		if(users.get(UID) == null)
			throw new RecordNotFoundException();
		return users.get(UID);		
	}

	@Override
	public User get(User u) throws RecordNotFoundException {
		return get(u.getUID());		
	}

	@Override
	public void addKey(String UID, String key) throws RecordNotFoundException {
	  User u;
		if((u=users.get(UID)) == null)
			throw new RecordNotFoundException();
		u.addPublicKey(key);
		update(u);
	}
	
	@Override
	public void deleteAllEntries(){
		users = new HashMap<String, User>();
	}
}