package org.fiteagle.core.userdatabase;

import org.fiteagle.core.userdatabase.UserPersistable.DatabaseException;

public class InMemoryUserDBTest extends UserPersistableTest {
  
  @Override
  public void setUpConnection() throws DatabaseException{
    database = new InMemoryUserDB();
  }
  
}
