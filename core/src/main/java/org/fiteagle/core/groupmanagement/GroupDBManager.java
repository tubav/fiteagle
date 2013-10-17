package org.fiteagle.core.groupmanagement;

import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.fiteagle.core.groupmanagement.JPAGroupDB.CouldNotCreateGroup;
import org.fiteagle.core.groupmanagement.JPAGroupDB.CouldNotDeleteGroup;
import org.fiteagle.core.groupmanagement.JPAGroupDB.CouldNotDeleteResource;
import org.fiteagle.core.groupmanagement.JPAGroupDB.CouldNotFindGroup;
import org.fiteagle.core.groupmanagement.JPAGroupDB.CouldNotUpdateGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupDBManager {

	private static GroupDBManager gm = null;
	private GroupPersistable groupDatabase;
	private static FiteaglePreferences preferences = new FiteaglePreferencesXML(
			GroupDBManager.class);
	private Logger log = LoggerFactory.getLogger(getClass());

	private static enum databaseType {
		InMemory, Persistent
	}

	private static final String DEFAULT_DATABASE_TYPE = databaseType.Persistent
			.name();

	private GroupDBManager() {

	}

	public void addGroup(Group group) throws CouldNotCreateGroup {

		groupDatabase.add(group);

	}

	public Group getGroup(String groupId) throws CouldNotFindGroup {

		return groupDatabase.get(groupId);

	}

	public void deleteGroup(String groupId) throws CouldNotDeleteGroup {

		groupDatabase.delete(groupId);

	}

	public static GroupDBManager getInstance() {
		if (gm == null) {

			gm = new GroupDBManager();
			gm.setGroupDatabase(gm.getDatabase());

		}
		return gm;
	}

	public void setGroupDatabase(GroupPersistable database) {
		this.groupDatabase = database;

	}

	public void updateGroup(Group g3) throws CouldNotUpdateGroup {

		groupDatabase.update(g3);
	}

	public void deleteResourceFromGroup(String resourceId)
			throws CouldNotDeleteResource {

		groupDatabase.deleteResourceFromGroup(resourceId);

	}

	private GroupPersistable getDatabase() {
		if (preferences.get("databaseType") == null) {
			preferences.put("databaseType", DEFAULT_DATABASE_TYPE);
		}
		if (preferences.get("databaseType").equals(
				databaseType.Persistent.name())) {
			groupDatabase = JPAGroupDB.getDerbyInstance();
		} else if (preferences.get("databaseType").equals(
				databaseType.InMemory.name())) {
			groupDatabase = JPAGroupDB.getInMemoryInstance();
		}

		return groupDatabase;
	}

}
