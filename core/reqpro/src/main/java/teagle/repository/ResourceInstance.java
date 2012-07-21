package teagle.repository;

import java.util.ArrayList;

/**
 * Class to model the representation of specific instances of resources within the repository.
 * For example, given a resource type for an Apache HTTP server with 3 available servers for booking,
 * each of them would be an instance of this class.
 * @author alexandru.mihaiuc@fokus.fraunhofer.de
 *
 */
public class ResourceInstance {
	public ResourceInstance(String id, String state, Resource resource, Configlet[] config, ResourceInstance parentInstance)
	{
		this.id = id;
		this.state = state;
		this.resource = resource;
		this.parentInstance = parentInstance;
		permissions = "<deprecated attribute>";
		setUser("<deprecated attribute>");
		if (config == null)
		{
			ConfigField[] fields = resource.getConfigFields();
			config = new Configlet[fields.length];
			for (int i = 0; i < fields.length; ++i)
				config[i] = new Configlet(fields[i]);
		}
		this.config = config;
	}
	
	private ResourceInstance parentInstance;
	
	private Configlet[] config;
	
	/**
	 * Current owner of the instance.
	 */
	private String user;
	/**
	 * TODO: unique id for this resource instance.
	 */
	private String id;
	
	public String getId()
	{
		return id;
	}
	
	public String getTypename()
	{
		return resource.getType();
	}
	
	public Resource getType()
	{
		return resource;
	}

/**
	 * TODO: what's this?
	 */
	private String state;
	/**
	 * TODO: what's this?
	 */
	public String permissions;
	
	private Resource resource;
	
	public void setId(String newId) {
		id = newId;
		
	}
	
	public void setState(String newState)
	{
		state = newState;
	}

	public String getState() {
		return state;
	}

	public boolean isProvisioned()
	{
		return state.equals("provisioned");
	}
	
	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}
	
	public Object getAttribute(String name) throws IllegalArgumentException
	{
		for (Configlet f : config)
			if (f.getName().equals(name))
				return f.getValue();
		throw new IllegalArgumentException("No such attribute; " + name);
	}

	public String getAttributeString(String name) throws IllegalArgumentException
	{
		for (Configlet f : config)
			if (f.getName().equals(name))
				return f.getValueString();
		throw new IllegalArgumentException("No such attribute; " + name);
	}
	
	public void setAttribute(String name, String value) throws IllegalArgumentException
	{
		for (Configlet f : config)
			if (f.getName().equals(name))
			{
				f.setValueString(value);
				return;
			}
		throw new IllegalArgumentException("No such attribute; " + name);
	}
	
	public Configlet[] getConfiguration()
	{
		return config;
	}
	
	public Configlet[] getReferences()
	{
		ArrayList<Configlet> l = new ArrayList<Configlet>();
		
		for (Configlet c : getConfiguration())
			if (c.isReference())
				l.add(c);
		
		return l.toArray(new Configlet[0]);
	}

	public String getSimpleType() {
		return resource.getName();
	}
	
	public boolean equals(Object o)
	{
		return o == this || (o != null && (o instanceof ResourceInstance) && ((ResourceInstance)o).getId().equals(id));
	}
	
	public ResourceInstance getParentInstance()
	{
		return parentInstance;
	}
}
