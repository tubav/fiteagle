package org.fiteagle.core.userdatabase;

import org.fiteagle.core.userdatabase.UserPersistable.DatabaseException;

public class SQLiteUserDBIT extends UserPersistableTest {
  
  protected void setUp() throws DatabaseException{
    database = SQLiteUserDB.getInstance();
  }
  
}
