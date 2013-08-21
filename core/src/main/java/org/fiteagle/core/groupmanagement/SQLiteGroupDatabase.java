package org.fiteagle.core.groupmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.fiteagle.core.persistence.SQLiteDatabase;

public class SQLiteGroupDatabase extends SQLiteDatabase implements
		GroupPersistable {

	public SQLiteGroupDatabase() throws SQLException {

		createTable("CREATE TABLE IF NOT EXISTS Groups (groupId, ownerId, PRIMARY KEY(groupId))");
		createTable("CREATE TABLE IF NOT EXISTS Resources (id INTEGER PRIMARY KEY AUTOINCREMENT,resourceId,groupId)");
	
	}

	@Override
	public void addGroup(Group group) {
		PreparedStatement ps = null;

		try {
			Connection connection = getConnection();
			ps = connection.prepareStatement("INSERT INTO Groups VALUES(?,?)");

			ps.setString(1, group.getGroupId());
			ps.setString(2, group.getGroupOwnerId());

			ps.execute();
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new CouldNotCreateGroup();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		addResources(group);

	}

	private void addResources(Group group) {
		for (String resourceId : group.getResources()) {
			addResource(group.getGroupId(), resourceId);
		}
	}

	private void addResource(String groupId, String resourceId) {
		PreparedStatement ps = null;
		try {
			Connection connection = getConnection();
			ps = connection.prepareStatement("INSERT INTO Resources(id,resourceId,groupId) VALUES($next_id,?,?)");
			ps.setString(2, resourceId);
			ps.setString(3, groupId);
			ps.execute();
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("could not add resource");
		}finally {
			try {
				ps.close();
				
			} catch (SQLException e2) {
				throw new RuntimeException(e2);
			}
		}

	}

	@Override
	public Group getGroup(String groupId) {

		try {
			Connection connection = getConnection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT Groups.groupId, Groups.ownerId, Resources.resourceId FROM Groups LEFT OUTER JOIN Resources ON Groups.groupId=Resources.groupId WHERE Groups.groupId = ? ");
			ps.setString(1, groupId);
			ResultSet result = ps.executeQuery();
			Group g = null;
			if (result.next()) {
				g = evaluateResultSet(result);
				connection.commit();
				connection.close();
				return g;
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);

		}
		throw new GroupNotFound("Group not found");
	}

	private Group evaluateResultSet(ResultSet result) {
		try {
			String groupId = result.getString(1);
			String ownerId = result.getString(2);
			Group g =  new Group(groupId, ownerId);
			List<String> resourceIds = getResourceIdsFromResultset(result);
			g.setResources(resourceIds);
			return g;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

	private List<String> getResourceIdsFromResultset(ResultSet result) throws SQLException {
		List<String> resourceIds = new LinkedList<>();	
		if(result.getString(3)!=null)
				resourceIds.add(result.getString(3));
		while(result.next()){
			
			resourceIds.add(result.getString(3));
		}
		return resourceIds;
		
	}
	
	@Override
	public List<Group> getGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGroup(String groupId) {
		try {

			Connection connection = getConnection();
			PreparedStatement ps = connection
					.prepareStatement("DELETE FROM Groups WHERE Groups.groupId = ?");
			ps.setString(1, groupId);
			ps.execute();
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new CouldNotDeleteGroup();
		}
		deleteResources(groupId);
	}

	private void deleteResources(String groupId) {
		try {

			Connection connection = getConnection();
			PreparedStatement ps = connection
					.prepareStatement("DELETE FROM Resources WHERE Resources.groupId = ?");
			ps.setString(1, groupId);
			ps.execute();
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new CouldNotDeleteGroup();
		}
	}

	public class GroupNotFound extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public GroupNotFound(String message) {
			super(message);
		}

	}

	public class CouldNotCreateGroup extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}

	public class CouldNotDeleteGroup extends RuntimeException {
		private static final long serialVersionUID = 2L;
	}

	@Override
	public void updateGroup(Group g3) {
		Group existent = null;
		try{
			 existent = getGroup(g3.getGroupId());
		}catch(GroupNotFound e1){
			return;
		}
		try{
			deleteGroup(g3.getGroupId());
		}catch(CouldNotDeleteGroup e){
			return;
		}
		try{
			addGroup(g3);
		}catch(CouldNotCreateGroup e2){
			addGroup(existent);
		}
	}
}
