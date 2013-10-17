package org.fiteagle.core.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.fiteagle.core.userdatabase.JPAUserDB;

public abstract class JPADatabase {

	protected static String PERSISTENCE_TYPE = null;
	private EntityManagerFactory factory;
	private static FiteaglePreferences preferences = new FiteaglePreferencesXML(
			JPAUserDB.class);
	private static final String DEFAULT_DATABASE_PATH = System
			.getProperty("user.home") + "/.fiteagle/db/";
	protected static final String PERSISTENCE_UNIT_NAME_DERBY = "Derby";
	protected static final String PERSISTENCE_UNIT_NAME_INMEMORY = "InMemory";

	protected synchronized EntityManager getEntityManager() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_TYPE);
		}
		return factory.createEntityManager();
	}

	protected static String getDatabasePath() {
		if (preferences.get("databasePath") == null) {
			preferences.put("databasePath", DEFAULT_DATABASE_PATH);
		}
		return preferences.get("databasePath");
	}
}
