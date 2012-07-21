package teagle.repository.tssg.model;

import teagle.repository.RepositoryException;
import teagle.repository.model.OrganizationRole;
import teagle.repository.tssg.client.TSSGClient;

public class OrganisationRole extends AbstractTSSGEntity implements OrganizationRole
{
	protected OrganisationRole(TSSGClient client) {
		super(client);
	}

	private PersonRole[] hasPersonRoles; 

	@Override
	public teagle.repository.model.PersonRole[] getPersonRoles() throws RepositoryException
	{
		return hasPersonRoles;
	}

}
