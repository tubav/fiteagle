package org.fiteagle.core.userdatabase;

public interface UserDB {

	public void add(User u) throws DuplicateUIDException, DatabaseException;
	
	public void delete(String UID) throws DatabaseException;
	public void delete(User u) throws DatabaseException;
	
	public void update(User u) throws RecordNotFoundException, DatabaseException;
	public void addKey(String UID, String key) throws RecordNotFoundException, DatabaseException;
	
	public User get(String UID) throws RecordNotFoundException, DatabaseException;
	public User get(User u) throws RecordNotFoundException, DatabaseException;
	
	public int getNumberOfUsers() throws DatabaseException;
	
	public void deleteAllEntries() throws DatabaseException;

		
	public static class RecordNotFoundException extends RuntimeException {		
		private static final long serialVersionUID = 2315125279537534064L;
	}
	
	public class DuplicateUIDException extends RuntimeException {
		private static final long serialVersionUID = -7242105025265481986L;		
	}
	
	public class DatabaseException extends RuntimeException {
    private static final long serialVersionUID = -8002909402748409082L;    
  }
}
