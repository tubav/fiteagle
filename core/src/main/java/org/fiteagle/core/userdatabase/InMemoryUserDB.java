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
	public void add(User p) throws DuplicateUIDException {
		if(users.get(p.getUID()) != null){
			throw new DuplicateUIDException();
		}
		else{
			users.put(p.getUID(), p);
		}
	}	

	@Override
	public void delete(String UID){
		users.remove(UID);			
	}

	@Override
	public void delete(User p){
		delete(p.getUID());
	}

	@Override
	public void update(User p) throws RecordNotFoundException {
		if(users.get(p.getUID()) == null)
			throw new RecordNotFoundException();
		else
			users.put(p.getUID(), p);		
	}

	@Override
	public User get(String UID) throws RecordNotFoundException {
		if(users.get(UID) == null)
			throw new RecordNotFoundException();
		return users.get(UID);		
	}

	@Override
	public User get(User p) throws RecordNotFoundException {
		return get(p.getUID());		
	}

	@Override
	public void addKey(User p, String key) throws RecordNotFoundException {
		if((p=users.get(p.getUID())) == null)
			throw new RecordNotFoundException();
		p.addPublicKey(key);
		update(p);
	}
	
	@Override
	public void deleteAllEntries(){
		users = new HashMap<String, User>();
	}
}