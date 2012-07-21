package teagle.repository.tssg.model;

import java.util.Arrays;

import teagle.repository.RepositoryException;
import teagle.repository.tssg.client.TSSGClient;

public class ConfigParamComposite extends ConfigParam
{
	public String commonName;
	public String description;
	private ConfigParamAtomic[] configParams;
	
	public ConfigParamComposite(TSSGClient client, String name, ConfigParamAtomic[] params)
	{
		super(client);
		this.commonName = name;
		description = name;
		configParams = params;
	}

	@Override
	public ConfigParamAtomic[] getConfigParams() throws RepositoryException
	{
		init();
		return configParams;
	}
	
	@Override
	protected void _init() throws RepositoryException
	{
		super._init();
		
		for (int i = 0; i < configParams.length; ++i)
			configParams[i] = client.getObject(ConfigParamAtomic.class, configParams[i].getId());
		Arrays.sort(configParams, new AbstractTSSGEntity.TSSGEntityComparator());
	}
}
