package teagle.repository.tssg.model;

import java.util.Arrays;

import teagle.repository.RepositoryException;
import teagle.repository.Util;
import teagle.repository.tssg.client.TSSGClient;

public class TSSGResourceInstance extends AbstractTSSGEntity
{
	public String commonName;
	public String description;
	private ResourceInstanceState state;
	private TSSGResource resourceSpec;
	private TSSGResourceInstance parentInstance;
	//private ArrayList<TSSGConfiglet> configurationData;
	private TSSGConfiglet[] configurationData;
	private boolean shared;
	
	public TSSGResourceInstance(TSSGClient client, String commonName, String state, TSSGResource type) throws RepositoryException
	{
		super(client, true);
		
		this.commonName = commonName;
		description = commonName;
		this.state = ResourceInstanceState.getState(state);
		this.resourceSpec = type; 
		
		createConfigurationData(type);
	}

	private void createConfigurationData(TSSGResource type)
			throws RepositoryException {
		ConfigParamAtomic[] params = type.getConfigParams();
		configurationData = new TSSGConfiglet[params.length];
		for (int i = 0; i < params.length; ++i)
			configurationData[i] = new TSSGConfiglet(client, params[i]);
	}
	

	
	public TSSGConfiglet[] getConfigurationData() throws RepositoryException
	{
		init();
		//return configurationData.toArray(new TSSGConfiglet[0]);
		return configurationData;
	}
	
	protected void _init() throws RepositoryException
	{
		if (resourceSpec == null || getResourceId() == null)
			throw new RepositoryException("ResourceInstance " + commonName + " (" + getId() + ") is not tied to a ResourceSpec");
		
		if (state == null || state.getId() == null)
			state = ResourceInstanceState.getState("NEW");
		else
			state = ResourceInstanceState.getState(state.getId());
		
		if (parentInstance != null && parentInstance.getId() != null)
			parentInstance = client.getObject(TSSGResourceInstance.class, parentInstance.getId());
		else
			parentInstance = null;
		
		resourceSpec = client.getObject(TSSGResource.class, getResourceId());
		
		if ((configurationData == null || configurationData.length == 0) && description.startsWith("singleton"))
		{
			createConfigurationData(resourceSpec);
			if (configurationData.length != 0)
			{
				Util.debug("Creating missing configuration data for singleton: " + commonName + "(" + getId() + ")");
				for (int i = 0; i < configurationData.length; ++i)
					configurationData[i] = client.addObject(configurationData[i]);
				client.updateObject(this);
			}
		}
		else
			for (int i = 0; i < configurationData.length; ++i)
				configurationData[i] = client.getObject(TSSGConfiglet.class, configurationData[i].getId());
		Arrays.sort(configurationData, new AbstractTSSGEntity.TSSGEntityComparator());
	}
	
	public TSSGResourceInstance getParentInstance()
	{
		return parentInstance;
	}
	
	public void setState(String state) throws RepositoryException
	{
		this.state = ResourceInstanceState.getState(state);
	}
	
	public String getState() throws RepositoryException
	{
		init();
		return state.commonName.toLowerCase();
	}

	public Integer getResourceId() throws RepositoryException
	{
		if (resourceSpec == null)
			throw new RepositoryException("Instance " + commonName + " (" + getId() + ") is not associated with a type");
		return resourceSpec.getId();
	}
	
	public TSSGResource getResource() throws RepositoryException
	{
		init();
		return resourceSpec;
	}
	
	public void setAttribute(String name, String value) throws RepositoryException
	{
		init();
		
		for (TSSGConfiglet c : configurationData)
			if (c.getCommonName().equals(name))
			{
				c.setParamValue(value);
				return;
			}
		
		throw new RepositoryException("No such parameter: " + name);
	}
	
	public boolean isProvisioned() throws RepositoryException
	{
		return getState().equals("provisioned");
	}
}
