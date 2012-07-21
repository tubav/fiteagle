package teagle.repository.tssg.model;

import teagle.repository.RepositoryException;
import teagle.repository.tssg.client.TSSGClient;

public class ConnectionEndpoint extends AbstractTSSGEntity
{
	public int side, pos;
	public String identifier;
	private TSSGResourceInstance resourceInstance;
	
	public ConnectionEndpoint(TSSGClient client, TSSGResourceInstance i)
	{
		super(client);
		resourceInstance = i;
		identifier = i.commonName;
	}
	
	public TSSGResourceInstance getResourceInstance() throws RepositoryException
	{
		init();
		return resourceInstance;
	}
	
	public int getResourceInstanceId()
	{
		return resourceInstance.getId();
	}
	
	public void setResourceInstance(TSSGResourceInstance ri)
	{
		resourceInstance = ri;
	}
	
	protected void _init() throws RepositoryException
	{
		resourceInstance = client.getObject(TSSGResourceInstance.class, resourceInstance.getId());
	}
}
