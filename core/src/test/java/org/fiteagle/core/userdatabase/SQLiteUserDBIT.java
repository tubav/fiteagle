package org.fiteagle.core.userdatabase;

import org.fiteagle.core.userdatabase.UserPersistable.DatabaseException;
import org.junit.After;
import org.junit.BeforeClass;

public class SQLiteUserDBIT extends UserPersistableTest {
  
  @BeforeClass
  public static void deleteUsers(){
    database = SQLiteUserDB.getInstance();
    database.delete(USER1);
    database.delete(USER2);
    database.delete(USER3);
    database.delete(USER4);
  }
  
  @Override
  protected void setUp() throws DatabaseException{
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
