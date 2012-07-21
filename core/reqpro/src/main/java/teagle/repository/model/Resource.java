package teagle.repository.model;

import teagle.repository.RepositoryException;

public interface Resource extends Entity
{
	public String getCommonName() throws RepositoryException;
}
