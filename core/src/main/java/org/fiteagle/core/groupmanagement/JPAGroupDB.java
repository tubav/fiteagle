package org.fiteagle.core.groupmanagement;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.derby.impl.io.CPStorageFactory;
import org.fiteagle.core.persistence.JPADatabase;

public class JPAGroupDB extends JPADatabase implements GroupPersistable{

	private static JPAGroupDB inMemoryInstance;
	private static JPAGroupDB derbyInstance;

	public JPAGroupDB(String persistenceUnitName) {
		PERSISTENCE_TYPE = persistenceUnitName;
	}

	public static JPAGroupDB getInMemoryInstance() {
		if (inMemoryInstance == null) {
			inMemoryInstance = new JPAGroupDB(PERSISTENCE_UNIT_NAME_INMEMORY);
		}
		return inMemoryInstance;
	}

	public static JPAGroupDB getDerbyInstance() {
		if (derbyInstance == null) {
			System.setProperty("derby.system.home", getDatabasePath());
			derbyInstance = new JPAGroupDB(PERSISTENCE_UNIT_NAME_DERBY);
		}
		return derbyInstance;
	}

	public void add(Group group){
		EntityManager em = getEntityManager();
		try{
		em.getTransaction().begin();
		em.persist(group);
		em.getTransaction().commit();
		}catch(Exception e){
			throw new CouldNotCreateGroup();
		}
	}

	public void delete(Group g) {
		EntityManager em = getEntityManager();
		g = em.merge(g);
		try {
			em.getTransaction().begin();
			em.remove(g);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new CouldNotDeleteGroup();
		}

	}

	public void update(Group g) {
		EntityManager em = getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(g);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new CouldNotUpdateGroup();
		}
	}

	public Group get(String groupId) {
		EntityManager em = getEntityManager();
		Group g;
		try {
			g = em.find(Group.class, groupId);
			if(g == null){
				throw new CouldNotFindGroup();
			}
		} catch (Exception e) {
			throw new CouldNotFindGroup();
		}
		return g;
	}

	public void delete(String groupId) {
		Group g = get(groupId);
		delete(g);
	}
	
	public void deleteResourceFromGroup(String resourceId) {
		//TODO IMPLEMENTATION
	}
	
	@Override
	public List<Group> getGroups() {
		// TODO Auto-generated method stub
		return null;
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
	
	public static class CouldNotFindGroup extends RuntimeException{
		private static final long serialVersionUID = 2L;
	}

	
	
}
