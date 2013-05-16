package org.fiteagle.core.userdatabase;

import java.sql.SQLException;

public interface UserDB {

	public void add(User u) throws DuplicateUIDException, SQLException;
	
	public void delete(String UID) throws SQLException;
	public void delete(User u) throws SQLException;
	
	public void update(User u) throws RecordNotFoundException, SQLException;
	public void addKey(String UID, String key) throws RecordNotFoundException, SQLException;
	
	public User get(String UID) throws RecordNotFoundException, SQLException;
	public User get(User u) throws RecordNotFoundException, SQLException;
	
	public int getNumberOfUsers() throws SQLException;
	
	public void deleteAllEntries() throws SQLException;

		
	public static class RecordNotFoundException extends RuntimeException {		
		private static final long serialVersionUID = 2315125279537534064L;
	}
	
	public class DuplicateUIDException extends RuntimeException {
		private static final long serialVersionUID = -7242105025265481986L;		
	}
}
