package teagle.repository.model;

import teagle.repository.RepositoryException;

public interface PersonRole extends Entity
{
	public Resource[] getResources() throws RepositoryException;
}
