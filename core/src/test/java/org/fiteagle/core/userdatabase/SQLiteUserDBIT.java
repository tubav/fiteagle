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
    if(USER1 != null){
      database.delete("test1");
      USER1 = null;
    }
    if(USER2 != null){
      database.delete("test2");
      USER2 = null;
    }
    if(USER3 != null){
      database.delete("test3");
      USER3 = null;
    }
    if(USER4 != null){
      database.delete("test4");
      USER4 = null;
    }
  }   
}
