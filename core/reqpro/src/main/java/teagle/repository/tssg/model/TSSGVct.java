package teagle.repository.tssg.model;

import teagle.repository.RepositoryException;
import teagle.repository.tssg.client.TSSGClient;

public class TSSGVct extends AbstractTSSGEntity
{
	public boolean shared;
	public String commonName;
	private VCTState state;
	public String description;
	//@SuppressWarnings("unused")
	private TSSGResourceInstance[] providesResources;
	private Person user;
	@SuppressWarnings("unused")
	private Booking[] hasBookings;
	
	public TSSGVct(TSSGClient client, String name, String state, Person user, TSSGResourceInstance[] instances) throws RepositoryException
	{
		super(client);
		this.commonName = name;
		this.state = VCTState.getState(state);
		this.description = name;
		this.user = user;
		this.providesResources = instances;
	}
	
	public Person getUser() throws RepositoryException
	{
		init();
		return user;
	}
	
	public String getState() throws RepositoryException
	{
		init();
		return state.commonName.toLowerCase();
	}
	
	public void setState(String state) throws RepositoryException
	{
		this.state = VCTState.getState(state);
	}
	
	public TSSGResourceInstance[] getResourceInstances() throws RepositoryException
	{
		init();
		return providesResources;
/*		HashMap<Integer, TSSGResourceInstance> instances = new HashMap<Integer, TSSGResourceInstance>();
		
		for (TSSGConnection c : getConnections())
		{
			Src src = c.getSrc();
			Dst dst = c.getDst();
			if (!instances.containsKey(src.getResourceInstanceId()))
				instances.put(src.getResourceInstanceId(), src.getResourceInstance());
			if (!instances.containsKey(dst.getResourceInstanceId()))
				instances.put(dst.getResourceInstanceId(), dst.getResourceInstance());
		}
		return instances.values().toArray(new TSSGResourceInstance[0]);*/
	}
	
	public void setResourceInstances(TSSGResourceInstance[] instances)
	{
		providesResources = instances;
	}
	
	protected void _init() throws RepositoryException
	{
		if (state == null)
			state = VCTState.getState("NEW");
		else
			state = VCTState.getState(state.getId());
		user = client.getObject(Person.class, user.getId());
		//hasConnections = client.listObjects(TSSGConnection.class);
		
		if (providesResources != null)
			for (int i = 0; i < providesResources.length; ++i)
				providesResources[i] = client.getObject(TSSGResourceInstance.class, providesResources[i].getId());
		else
			providesResources = new TSSGResourceInstance[0];
	}
	
	public void replaceInstance(TSSGResourceInstance oldInstance, TSSGResourceInstance newInstance) throws RepositoryException
	{
		TSSGResourceInstance[] instances = getResourceInstances();
		for (int i = 0; i < instances.length; ++i)
			if (instances[i].equals(oldInstance))
			{
				instances[i] = newInstance;
				return;
			}
			
		throw new RepositoryException("No instance found for replacement: " + oldInstance.commonName + "(" + oldInstance.getId() + ")");
	}
}
