package teagle.repository.model;

import teagle.repository.RepositoryException;

public interface OrganizationRole extends Entity
{
	public PersonRole[] getPersonRoles() throws RepositoryException;
}
