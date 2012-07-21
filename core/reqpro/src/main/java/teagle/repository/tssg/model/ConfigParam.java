package teagle.repository.tssg.model;

import teagle.repository.RepositoryException;
import teagle.repository.tssg.client.TSSGClient;

public abstract class ConfigParam extends AbstractTSSGEntity
{

	protected ConfigParam(TSSGClient client) {
		super(client);
	}

	abstract ConfigParamAtomic[] getConfigParams() throws RepositoryException;
}
