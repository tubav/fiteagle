package org.fiteagle.core.userdatabase;

public interface PersonDB {

	public abstract void add(Person p) throws DuplicateUID;
	
	public void delete(String UID);
	public void delete(Person p);
	
	public void update(Person p) throws RecordNotFound;
	public void addKey(Person p, String key) throws RecordNotFound;
	
	public Person get(String UID) throws RecordNotFound;
	public Person get(Person p) throws RecordNotFound;
	

	
	
	public static class RecordNotFound extends RuntimeException {
	}
	
	public class DuplicateUID extends RuntimeException {
		
	}
}
