package teagle.repository.client;

import teagle.repository.RepositoryException;
import teagle.repository.ResourceInstance;

public interface ResourceInstanceManager
{

	ResourceInstance[] listInstances(String userName)
			throws RepositoryException;

	boolean instanceExists(String id) throws RepositoryException;

	ResourceInstance getInstance(String id) throws RepositoryException;

	void setInstance(ResourceInstance instance) throws RepositoryException;

	void removeInstance(String id) throws RepositoryException;
}
