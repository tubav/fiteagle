package teagle.repository.tssg.model;

import teagle.repository.RepositoryException;
import teagle.repository.tssg.client.TSSGClient;

public class TSSGPtm extends AbstractTSSGEntity
{
	public TSSGPtm(TSSGClient client, String name, String url, String description, Organisation organisation, Person owner) throws RepositoryException
	{
		super(client);
		this.commonName = name;
		this.url = url;
		this.provider = organisation;
		this.owner = owner;
		this.description = description;
		if (!organisation.isPersistent())
			throw new RepositoryException("organisation is not persistent");
	}
	
	public String commonName;
	public String url;
	public String description;
	private Person owner;
	private Organisation provider;
	private Object[] configurationData;
	private Object[] containsApplicationComponents;
	private TSSGResource[] resourceSpecs;
	private TSSGResource[] supportedResources;
	
	public Person getOwner() throws RepositoryException
	{
		init();
		return owner;
	}
	
	public Organisation getProvider() throws RepositoryException
	{
		init();
		return provider;
	}
	
	@Override
	protected void _init() throws RepositoryException
	{
/*		if (owner != null)
			owner = client.getObject(Person.class, owner.getId());
		if (provider != null)
			provider = client.getObject(Organisation.class, provider.getId());*/
		for (int i = 0; i < resourceSpecs.length; ++i)
			resourceSpecs[i] = client.getObject(TSSGResource.class, resourceSpecs[i].getId());
	}
	
	public TSSGResource[] getSupportedResources() throws RepositoryException
	{
		init();
		return resourceSpecs;
	}
	
	public boolean isProvidedBy(Organisation organisation)
	{
		return provider.equals(organisation);
	}
	
	public int getProviderId()
	{
		return provider.getId();
	}
	
	public boolean supportsResource(TSSGResource resource) throws RepositoryException
	{
		_init();
		for (TSSGResource r : resourceSpecs)
			if (r.equals(resource))
				return true;
		return false;
	}
	
}
