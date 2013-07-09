package org.fiteagle.core.userdatabase;

import org.fiteagle.core.userdatabase.UserPersistable.DatabaseException;
import org.junit.After;

public class SQLiteUserDBIT extends UserPersistableTest { 
  
  @Override
  protected void setUpConnection() throws DatabaseException{
    database = SQLiteUserDB.getInstance();
  }
  
  @After
  public void cleanUp() {
    database.delete(USER1);
    database.delete(USER2);
    database.delete(USER3);
    database.delete(USER4);
  }   
}
