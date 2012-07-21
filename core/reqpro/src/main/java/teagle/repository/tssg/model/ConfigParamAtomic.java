package teagle.repository.tssg.model;

import teagle.repository.tssg.client.TSSGClient;

public class ConfigParamAtomic extends ConfigParam
{
	public String commonName;
	public String description;
	public String defaultParamValue;
	public String configParamType;
	
	public ConfigParamAtomic(TSSGClient client, String name, String type, String defaultValue, String description)
	{
		super(client);
		commonName = name;
		if (description == null || description.length() == 0)
			description = "no description";
		this.description = description;
		configParamType = type;
		defaultParamValue = defaultValue;
	}

	@Override
	public ConfigParamAtomic[] getConfigParams()
	{
		return new ConfigParamAtomic[] { this };
	}

}
