package org.fiteagle.core.userdatabase;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUserDB{
  
  private EntityManagerFactory factory;
  private final String PERSISTENCE_TYPE;
  
  private static final String PERSISTENCE_UNIT_NAME_DERBY = "Users_Derby";
  private static final String PERSISTENCE_UNIT_NAME_INMEMORY = "Users_InMemory";
  
  private static JPAUserDB derbyInstance;
  private static JPAUserDB inMemoryInstance;
  
  private JPAUserDB(String persistenceUnitName) {
    PERSISTENCE_TYPE = persistenceUnitName;
  }
  
  public static JPAUserDB getInMemoryInstance(){
    if(inMemoryInstance == null){
      inMemoryInstance = new JPAUserDB(PERSISTENCE_UNIT_NAME_INMEMORY);
    }
    return inMemoryInstance;
  }
  
  public static JPAUserDB getDerbyInstance(){
    if(derbyInstance == null){
      derbyInstance = new JPAUserDB(PERSISTENCE_UNIT_NAME_DERBY);
    }
    return derbyInstance;
  }
  
  private synchronized EntityManager getEntityManager() {
    if (factory == null){
      //TODO: Not so nice
      System.setProperty("derby.system.home", System.getProperty("user.home"));
      factory = Persistence.createEntityManagerFactory(PERSISTENCE_TYPE);
    }
    try{
      return factory.createEntityManager();
    } catch(Exception e){
      throw new DatabaseException(e.getMessage());
    }
  }
  
  public void add(User user){
    EntityManager em = getEntityManager();
    try{
      em.getTransaction().begin();
      em.persist(user);
      em.getTransaction().commit();
    } catch(Exception e){
      if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException && e.getMessage().contains("'EMAIL' defined on 'USERS'")){
        throw new DuplicateEmailException();
      }
      if(e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException && e.getMessage().contains("defined on 'USERS'")){
        throw new DuplicateUsernameException();
      }
      throw e;
    }finally{
      em.close();
    }
  }
  
  public User get(User user) throws UserNotFoundException{
    return get(user.getUsername());
  }
  
  public User get(String username) throws UserNotFoundException{
    EntityManager em = getEntityManager();
    try{
      User user = em.find(User.class, username);
      if(user == null){
        throw new UserNotFoundException();
      }
      return user;
    }finally{
      em.close();
    }
  }
  
  public void delete(User user){
    EntityManager em = getEntityManager();
    try{
      em.getTransaction().begin();
      em.remove(em.merge(user));
      em.getTransaction().commit();
    }finally{
      em.close();
    }
  }
  
  public void delete(String username){
    delete(get(username));
  }
 
  public void update(String username, String firstName, String lastName, String email, String affiliation, String password, List<UserPublicKey> publicKeys) {
    EntityManager em = getEntityManager();
    try{
      User user = em.find(User.class, username);
      if(user == null){
        throw new UserNotFoundException();
      }
      em.getTransaction().begin();
      user.updateAttributes(firstName, lastName, email, affiliation, password, publicKeys);
      em.getTransaction().commit();
    }catch(Exception e){
      if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException && e.getMessage().contains("'EMAIL' defined on 'USERS'")){
        throw new DuplicateEmailException();
      }
      throw e;
    }finally{
      em.close();
    }
  }

  public void addKey(String username, UserPublicKey publicKey){
    EntityManager em = getEntityManager();
    try{
      User user = em.find(User.class, username);
      if(user == null){
        throw new UserNotFoundException();
      }
      em.getTransaction().begin();
      user.addPublicKey(publicKey);
      em.getTransaction().commit();
    }catch(Exception e){
      if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException && e.getMessage().contains("defined on 'PUBLICKEYS'")){
        throw new DuplicatePublicKeyException();
      }
      throw e;
    }finally{
      em.close();
    }
  }
  
  public void deleteKey(String username, String description){
    EntityManager em = getEntityManager();
    try{
      User user = em.find(User.class, username);
      if(user == null){
        throw new UserNotFoundException();
      }
      em.getTransaction().begin();
      user.deletePublicKey(description);
      em.getTransaction().commit();
    }finally{
      em.close();
    }
  }
  
  public void renameKey(String username, String description, String newDescription){
    EntityManager em = getEntityManager();
    try{
      User user = em.find(User.class, username);
      if(user == null){
        throw new UserNotFoundException();
      }
      em.getTransaction().begin();
      user.renamePublicKey(description, newDescription);
      em.getTransaction().commit();
    }catch(Exception e){
      if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException && e.getMessage().contains("defined on 'PUBLICKEYS'")){
        throw new DuplicatePublicKeyException();
      }
      throw e;
    }finally{
      em.close();
    }
  }
  
  public static class UserNotFoundException extends RuntimeException {    
    private static final long serialVersionUID = 2315125279537534064L;
    
    public UserNotFoundException(){
      super("no user with this username could be found in the database");
    }
  }
  
  public static class DuplicateUsernameException extends RuntimeException {
    private static final long serialVersionUID = -7242105025265481986L;   
    
    public DuplicateUsernameException(){
      super("another user with the same username already exists in the database");
    }
  }
  
  public static class DuplicateEmailException extends RuntimeException {
    private static final long serialVersionUID = 5986984055945876422L;
    
    public DuplicateEmailException(){
      super("another user with the same email already exists in the database");
    }
  }
  
  public static class DuplicatePublicKeyException extends RuntimeException {
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
  
  public static class NotEnoughAttributesException extends RuntimeException {
    private static final long serialVersionUID = -8279867183643310351L;
    
    public NotEnoughAttributesException(){
      super();
    }
    
    public NotEnoughAttributesException(String message){
      super(message);
    }
  }
  
  public static class InValidAttributeException extends RuntimeException {
    private static final long serialVersionUID = -1299121776233955847L;
    
    public InValidAttributeException(){
      super();
    }
    
    public InValidAttributeException(String message){
      super(message);      
    }
  }
  
}
