package teagle.repository.model;

import teagle.repository.RepositoryException;

public interface Person extends Entity
{
	public String getUserName() throws RepositoryException;
	public String getPassword() throws RepositoryException;
}
