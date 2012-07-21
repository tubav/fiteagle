package teagle.repository.tssg.model;

import java.util.HashMap;

import teagle.repository.RepositoryException;
import teagle.repository.tssg.client.TSSGClient;

public class ResourceInstanceState extends AbstractTSSGEntity
{
	protected ResourceInstanceState(TSSGClient client) {
		super(client);
	}

	private static boolean initialized  = false;
	private static HashMap<Integer, ResourceInstanceState> map;
	
	public String commonName;
	public String description;

	
	public static void init_static(TSSGClient client) throws RepositoryException
	{
		if (initialized)
			return;
		
		map = new HashMap<Integer, ResourceInstanceState>();
		
		ResourceInstanceState[] states = client.listObjects(ResourceInstanceState.class);
		
		for (ResourceInstanceState s : states)
			map.put(s.getId(), s);
		
		initialized = true;
	}
	
	public static ResourceInstanceState getState(int id) throws RepositoryException
	{
		if (!initialized)
			throw new RepositoryException("Not initialized");
		
		ResourceInstanceState state = map.get(id);
		if (state == null)
			throw new RepositoryException("ResourceInstanceState not found: " + id);
		
		return state;
	}
	
	public static ResourceInstanceState getState(String name) throws RepositoryException
	{
		if (!initialized)
			throw new RepositoryException("Not initialized");
		
		name = name.toUpperCase();
		
		for (ResourceInstanceState s : map.values())
			if (s.commonName.equals(name))
				return s;
		
		throw new RepositoryException("ResourceInstanceState not found: " + name);
	}
}
