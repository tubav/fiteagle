package org.fiteagle.core.userdatabase;

public interface UserPersistable {

	public void add(User u) throws DuplicateUsernameException, DatabaseException, NotEnoughAttributesException, InValidAttributeException, DuplicatePublicKeyException, DuplicateEmailException;
	
	public void delete(String username) throws DatabaseException;
	public void delete(User u) throws DatabaseException;
	
	public void update(User u) throws UserNotFoundException, DatabaseException, NotEnoughAttributesException, InValidAttributeException, DuplicatePublicKeyException, DuplicateEmailException; 
	public void addKey(String username, UserPublicKey key) throws UserNotFoundException, DatabaseException, InValidAttributeException, DuplicatePublicKeyException;
	public void deleteKey(String username, String description) throws UserNotFoundException, DatabaseException;
	public void renameKey(String username, String description, String newDescription) throws UserNotFoundException, DatabaseException, DuplicatePublicKeyException, InValidAttributeException, PublicKeyNotFoundException;
	
	public User get(String username) throws UserNotFoundException, DatabaseException;
	public User get(User u) throws UserNotFoundException, DatabaseException;
	
	public int getNumberOfUsers() throws DatabaseException;	
		
	public static class UserNotFoundException extends RuntimeException {		
		private static final long serialVersionUID = 2315125279537534064L;
		
		public UserNotFoundException(){
		  super("no user with this username could be found in the database");
		}
	}
	
	public class DuplicateUsernameException extends RuntimeException {
		private static final long serialVersionUID = -7242105025265481986L;		
		
		public DuplicateUsernameException(){
		  super("another user with the same username already exists in the database");
		}
	}
	
	public class DuplicateEmailException extends RuntimeException {
    private static final long serialVersionUID = 5986984055945876422L;
    
    public DuplicateEmailException(){
      super("another user with the same email already exists in the database");
    }
	}
	
	public class DuplicatePublicKeyException extends RuntimeException {
    private static final long serialVersionUID = -8863826365649086008L;	
    
    public DuplicatePublicKeyException(){
      super("either this public key already exists or another public key with the same description already exists for this user");
    }
	}
	
  public class DatabaseException extends RuntimeException {
    private static final long serialVersionUID = -8002909402748409082L;
    
    public DatabaseException(String message){
      super(message);
    }
  }
  
  public class NotEnoughAttributesException extends RuntimeException {
    private static final long serialVersionUID = -8279867183643310351L;
    
    public NotEnoughAttributesException(){
      super();
    }
    
    public NotEnoughAttributesException(String message){
      super(message);
    }
  }
  
  public class InValidAttributeException extends RuntimeException {
    private static final long serialVersionUID = -1299121776233955847L;
    
    public InValidAttributeException(){
      super();
    }
    
    public InValidAttributeException(String message){
      super(message);      
    }
  }
  
  public class PublicKeyNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 4906415519200726744L;  
    
    public PublicKeyNotFoundException(){
      super("no public key with this description could be found in the database");
    }
  }
}
