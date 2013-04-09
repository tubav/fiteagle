package org.fiteagle.core.userdatabase;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

public interface PersonDB {

	public void add(Person p) throws DuplicateUIDException;
	
	public void delete(String UID);
	public void delete(Person p);
	
	public void update(Person p) throws RecordNotFoundException;
	public void addKey(Person p, String key) throws RecordNotFoundException;
	
	public Person get(String UID) throws RecordNotFoundException;
	public Person get(Person p) throws RecordNotFoundException;
	
	public int getSize();

	
	
	public static class RecordNotFoundException extends RuntimeException {
	}
	
	public class DuplicateUIDException extends RuntimeException {
		
	}

}
