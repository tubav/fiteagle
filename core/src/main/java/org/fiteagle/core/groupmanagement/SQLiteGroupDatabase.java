package org.fiteagle.core.groupmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.fiteagle.core.persistence.SQLiteDatabase;


public class SQLiteGroupDatabase extends SQLiteDatabase implements GroupPersistable {
 
  
  public SQLiteGroupDatabase() throws SQLException {
   
    createTable("CREATE TABLE IF NOT EXISTS Groups (groupId, ownerId, PRIMARY KEY(groupId))");
   
    
  }
  
  @Override
  public void addGroup(Group group) {
    PreparedStatement ps =null;
    
    try {
      Connection connection = getConnection();
      ps = connection.prepareStatement("INSERT INTO Groups VALUES(?,?)");
   
      ps.setString(1, group.getGroupId());
      ps.setString(2, group.getGroupOwnerId());
  
      ps.execute();
      connection.commit();
      connection.close();
    } catch (SQLException e) {
        log.error(e.getMessage(),e);
    } finally {
      try {
        ps.close();
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
    }
    
  }
  
  @Override
  public Group getGroup(String groupId) {
     
    try{
   Connection connection =  getConnection();
    PreparedStatement ps = connection.prepareStatement("SELECT Groups.groupId, Groups.ownerId FROM Groups WHERE Groups.groupId = ? ");
    ps.setString(1, groupId);
    ResultSet result = ps.executeQuery();
    Group g = null;
    if(result.next()){
      g = evaluateResultSet(result);
      connection.commit();
      connection.close();
      return g;
    }
    }catch(SQLException e){
      log.error(e.getMessage(),e);
      
    }
    throw new GroupNotFound("Group not found");
  }
  
  
  private Group evaluateResultSet(ResultSet result) {
    try {
      String groupId = result.getString(1);
      String ownerId = result.getString(2);
      return new Group(groupId,ownerId);
    } catch (SQLException e) {
      log.error(e.getMessage(),e);
      throw new RuntimeException(e);
    }
    
  }

  @Override
  public List<Group> getGroups() {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public void deleteGroup(String groupId) {
    try{
      
      Connection connection = getConnection();
      PreparedStatement ps = connection.prepareStatement("DELETE FROM Groups WHERE Groups.groupId = ?");
      ps.setString(1, groupId);
      ps.execute();
      connection.commit();
      connection.close();
    } catch(SQLException e){
      log.error(e.getMessage(),e);
      throw new RuntimeException(e);
    }
  }
  
  
  public class GroupNotFound extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public GroupNotFound(String message){
      super(message);
    }
    
  }
}
