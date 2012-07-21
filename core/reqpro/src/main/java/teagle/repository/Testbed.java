package teagle.repository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A list of resources and the connections between them.
 */
public class Testbed 
{
	public Testbed(Collection<ResourceInstance> components) 
	{
		this.components = new ArrayList<ResourceInstance>(components);
	}
	
	
	public Collection<ResourceInstance>  components;
}
