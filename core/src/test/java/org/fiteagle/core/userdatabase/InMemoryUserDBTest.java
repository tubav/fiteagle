package org.fiteagle.core.userdatabase;

import org.fiteagle.core.userdatabase.UserPersistable.DatabaseException;

public class InMemoryUserDBTest extends UserPersistableTest {
  
  public void setUp() throws DatabaseException{
    database = InMemoryUserDB.getInstance();
  }
  
}
