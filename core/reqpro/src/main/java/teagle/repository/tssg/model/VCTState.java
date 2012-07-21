package teagle.repository.tssg.model;

import java.util.HashMap;

import teagle.repository.RepositoryException;
import teagle.repository.tssg.client.TSSGClient;

public class VCTState extends AbstractTSSGEntity
{
	protected VCTState(TSSGClient client) {
		super(client);
	}

	private static boolean initialized  = false;
	private static HashMap<Integer, VCTState> map;
	
	public String commonName;
	public String description;
	
	
	public static void init_static(TSSGClient client) throws RepositoryException
	{
		if (initialized)
			return;
		
		map = new HashMap<Integer, VCTState>();
		
		VCTState[] states = client.listObjects(VCTState.class);
		
		for (VCTState s : states)
			map.put(s.getId(), s);
		
		initialized = true;
	}
	
	public static VCTState getState(int id) throws RepositoryException
	{
		if (!initialized)
			throw new RepositoryException("Not initialized");
		
		VCTState state = map.get(id);
		if (state == null)
			throw new RepositoryException("VCTState not found: " + id);
		
		return state;
	}
	
	public static VCTState getState(String name) throws RepositoryException
	{
		if (!initialized)
			throw new RepositoryException("Not initialized");
		
		name = name.toUpperCase();
		
		for (VCTState s : map.values())
			if (s.commonName.equals(name))
				return s;
		
		throw new RepositoryException("ResourceInstanceState not found: " + name);
	}
}
