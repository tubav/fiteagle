package teagle.repository.client;

import teagle.repository.RepositoryException;

public interface ConfigurationManager
{

	void newConfig(String userName) throws RepositoryException;

}
