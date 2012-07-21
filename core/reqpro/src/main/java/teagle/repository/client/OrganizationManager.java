package teagle.repository.client;

import teagle.repository.Organization;
import teagle.repository.RepositoryException;

public interface OrganizationManager
{
	Organization getOrganization(int id) throws RepositoryException;
	int addOrganization(Organization organization) throws RepositoryException;
	void removeOrganization(Organization organization) throws RepositoryException;
	void removeOrganization(int id) throws RepositoryException;
	Organization[] listOrganizations() throws RepositoryException;
	Organization[] getOrganizations(String userName) throws RepositoryException;

}
