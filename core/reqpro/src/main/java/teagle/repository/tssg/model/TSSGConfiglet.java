package teagle.repository.tssg.model;

import teagle.repository.RepositoryException;
import teagle.repository.tssg.client.TSSGClient;

public class TSSGConfiglet extends AbstractTSSGEntity
{
	public TSSGConfiglet(TSSGClient client, ConfigParamAtomic p)
	{
		super(client);
		configParamAtomic = p;
		commonName = p.commonName;
		description = p.description;
		setParamValue(p.defaultParamValue);
	}
	
	ConfigParamAtomic configParamAtomic;
	private String paramValue;
	@SuppressWarnings("unused")
	private String commonName;
	@SuppressWarnings("unused")
	private String description;
	
	@Override
	protected void _init() throws RepositoryException
	{
		if (configParamAtomic == null)
			throw new RepositoryException(getCommonName() + " is not bound to a ConfigParamAtomic");
//		Util.debug(client.toString());
//		Util.debug("" + configurationParametersAtomic[0].getId());
		configParamAtomic = client.getObject(ConfigParamAtomic.class, configParamAtomic.getId());
	}
	
	public ConfigParamAtomic getParameter() throws RepositoryException
	{
		init();
		return configParamAtomic;
	}

	public void setParamValue(String paramValue)
	{
		if (paramValue == null)
			paramValue = "";
		this.paramValue = paramValue;
	}

	public String getParamValue()
	{
		return paramValue;
	}

	public String getCommonName() throws RepositoryException
	{
		init();
		return getParameter().commonName;
		//return commonName;
	}
	
	public String getType() throws RepositoryException
	{
		return getParameter().configParamType;
	}
}
