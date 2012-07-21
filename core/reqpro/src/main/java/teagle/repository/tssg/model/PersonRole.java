package teagle.repository.tssg.model;

import teagle.repository.RepositoryException;
import teagle.repository.tssg.client.TSSGClient;

public class PersonRole extends AbstractTSSGEntity implements teagle.repository.model.PersonRole
{
	
	protected PersonRole(TSSGClient client) {
		super(client);
	}

	@Override
	public teagle.repository.model.Resource[] getResources() throws RepositoryException
	{
		return null;
	}

}
