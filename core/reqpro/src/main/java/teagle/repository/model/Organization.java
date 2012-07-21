package teagle.repository.model;

import teagle.repository.RepositoryException;

public interface Organization extends Entity
{
	public String getName() throws RepositoryException;
	public OrganizationRole[] getOrganizationRoles() throws RepositoryException;
	public Person[] getPeople() throws RepositoryException;
	public Resource[] getResources() throws RepositoryException;
}
