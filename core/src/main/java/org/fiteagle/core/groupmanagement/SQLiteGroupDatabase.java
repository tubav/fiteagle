package org.fiteagle.core.groupmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.fiteagle.core.groupmanagement.GroupDBManager.GroupNotFound;
import org.fiteagle.core.persistence.SQLiteDatabase;

public class SQLiteGroupDatabase extends SQLiteDatabase implements
		GroupPersistable {

	public SQLiteGroupDatabase() throws SQLException {
		Connection connection = getConnection();
		try {
			createTable(connection,
					"CREATE TABLE IF NOT EXISTS Groups (groupId, ownerId, PRIMARY KEY(groupId))");
			createTable(
					connection,
					"CREATE TABLE IF NOT EXISTS Resources (id INTEGER PRIMARY KEY AUTOINCREMENT,resourceId,groupId)");
		} finally {
			connection.close();
		}
	}

	@Override
	public void addGroup(Group group) throws SQLException {
		PreparedStatement ps = null;
		Connection connection = getConnection();
		try {

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
			connection.close();
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
			try {
				addResource(group.getGroupId(), resourceId);
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
				throw new RuntimeException();
			}
		}
	}

	private void addResource(String groupId, String resourceId)
			throws SQLException {
		PreparedStatement ps = null;
		Connection connection = getConnection();
		try {

			ps = connection
					.prepareStatement("INSERT INTO Resources(id,resourceId,groupId) VALUES($next_id,?,?)");
			ps.setString(2, resourceId);
			ps.setString(3, groupId);
			ps.execute();
			connection.commit();
			
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("could not add resource");
		} finally {
			connection.close();
			ps.close();

		}
	}

	@Override
	public Group getGroup(String groupId) throws SQLException {
		Group g = null;
		Connection connection = getConnection();
		try {
			
			PreparedStatement ps = connection
					.prepareStatement("SELECT Groups.groupId, Groups.ownerId, Resources.resourceId FROM Groups LEFT OUTER JOIN Resources ON Groups.groupId=Resources.groupId WHERE Groups.groupId = ? ");
			ps.setString(1, groupId);
			ResultSet result = ps.executeQuery();
			
			if (result.next()) {
				g = evaluateResultSet(result);
				connection.commit();
				
			}else{
				throw new GroupNotFound(groupId);
			}
		}catch(SQLException e){
			throw new GroupNotFound("Group not found");
		}finally{
			connection.close();
		}
	
			return g;
	}

	private Group evaluateResultSet(ResultSet result) {
		try {
			String groupId = result.getString(1);
			String ownerId = result.getString(2);
			Group g = new Group(groupId, ownerId);
			List<String> resourceIds = getResourceIdsFromResultset(result);
			g.setResources(resourceIds);
			return g;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

	private List<String> getResourceIdsFromResultset(ResultSet result)
			throws SQLException {
		List<String> resourceIds = new LinkedList<>();
		if (result.getString(3) != null)
			resourceIds.add(result.getString(3));
		while (result.next()) {

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
	public void deleteGroup(String groupId) throws SQLException {
		Connection connection = getConnection();
		try {

			
			PreparedStatement ps = connection
					.prepareStatement("DELETE FROM Groups WHERE Groups.groupId = ?");
			ps.setString(1, groupId);
			ps.execute();
			connection.commit();
			
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new CouldNotDeleteGroup();
		}finally{
			connection.close();
		}
		deleteAllResourcesFromSingleGroup(groupId);
	}

	private void deleteAllResourcesFromSingleGroup(String groupId) throws SQLException {
		Connection connection = getConnection();
		try {

			
			PreparedStatement ps = connection
					.prepareStatement("DELETE FROM Resources WHERE Resources.groupId = ?");
			ps.setString(1, groupId);
			ps.execute();
			connection.commit();
			
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new CouldNotDeleteGroup();
		}finally{
			connection.close();
		}
	}

	
	@Override
	public void updateGroup(Group g3) throws SQLException {
		Group existent = null;
		try {
			existent = getGroup(g3.getGroupId());
		} catch (GroupNotFound e1) {
			return;
		}
		try {
			deleteGroup(g3.getGroupId());
		} catch (CouldNotDeleteGroup e) {
			return;
		}
		try {
			addGroup(g3);
		} catch (CouldNotCreateGroup e2) {
			addGroup(existent);
		}
	}

	@Override
	public void deleteResourceFromGroup(String resourceId) throws SQLException {

		Connection connection = getConnection();
		try {

			PreparedStatement ps = connection
					.prepareStatement("DELETE FROM Resources WHERE Resources.resourceId = ?");
			ps.setString(1, resourceId);
			ps.execute();
			connection.commit();
		
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new CouldNotDeleteGroup();
		}finally{
			connection.close();
		}

	}
	
	public static class CouldNotCreateGroup extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}

	public static class CouldNotDeleteGroup extends RuntimeException {
		private static final long serialVersionUID = 2L;
	}
	public static class CouldNotUpdateGroup extends RuntimeException{

		private static final long serialVersionUID = 1L;
		
	}
	public static class CouldNotDeleteResource extends RuntimeException{

		private static final long serialVersionUID = 1L;
		
	}
}
