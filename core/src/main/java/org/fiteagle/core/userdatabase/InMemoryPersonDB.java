package org.fiteagle.core.userdatabase;

import java.util.HashMap;

public class InMemoryPersonDB implements PersonDB {

	
	private HashMap<String, Person> persons;
	
	public InMemoryPersonDB(){
		persons = new HashMap<String, Person>();
	}
		
	public int getSize(){
		return persons.size();
	}
	
	@Override
	public void add(Person p) throws DuplicateUID {
		if(persons.get(p.getUID()) != null){
			throw new DuplicateUID();
		}
		else{
			persons.put(p.getUID(), p);
		}
	}	

	@Override
	public void delete(String UID){
		persons.remove(UID);			
	}

	@Override
	public void delete(Person p){
		delete(p.getUID());
	}

	@Override
	public void update(Person p) throws RecordNotFound {
		if(persons.get(p.getUID()) == null)
			throw new RecordNotFound();
		else
			persons.put(p.getUID(), p);		
	}

	@Override
	public Person get(String UID) throws RecordNotFound {
		if(persons.get(UID) == null)
			throw new RecordNotFound();
		return persons.get(UID);		
	}

	@Override
	public Person get(Person p) throws RecordNotFound {
		return get(p.getUID());		
	}

	@Override
	public void addKey(Person p, String key) throws RecordNotFound {
		if((p=persons.get(p.getUID())) == null)
			throw new RecordNotFound();
		p.addPublicKey(key);
		update(p);
	}

}
