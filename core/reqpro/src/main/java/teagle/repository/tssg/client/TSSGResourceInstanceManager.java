package teagle.repository.tssg.client;

import java.io.IOException;
import java.util.ArrayList;

import teagle.repository.Configlet;
import teagle.repository.RepositoryException;
import teagle.repository.ResourceInstance;
import teagle.repository.client.ResourceInstanceManager;
import teagle.repository.tssg.model.ResourceInstanceState;
import teagle.repository.tssg.model.TSSGConfiglet;
import teagle.repository.tssg.model.TSSGResource;
import teagle.repository.tssg.model.TSSGResourceInstance;

public class TSSGResourceInstanceManager extends TSSGResourceManager implements ResourceInstanceManager
{

	public TSSGResourceInstanceManager(ClassLoader loader)
			throws IOException, RepositoryException
	{
		super(loader);
		client.addAliases("resourceInstanceState", ResourceInstanceState.class);
		ResourceInstanceState.init_static(client);
	}
	
	private TSSGResourceInstance[] listTSSGInstances() throws RepositoryException
	{
		return client.listObjects(TSSGResourceInstance.class); 
	}
	
	protected TSSGResourceInstance findInstanceById(String id) throws RepositoryException
	{
		for (TSSGResourceInstance r : listTSSGInstances())
			if (id.equals(r.commonName))
				return r;
		return null;
	}
	
	protected TSSGResourceInstance getInstanceById(String id) throws RepositoryException
	{
		TSSGResourceInstance r = findInstanceById(id);
		if (r == null)
			throw new RepositoryException("No such resource instance: " + id);
		return r;
	}
	
	private ResourceInstance makeInstance(TSSGResourceInstance tri) throws RepositoryException
	{
		TSSGConfiglet[] fields = tri.getConfigurationData();
		Configlet[] lets = new Configlet[fields.length];
		for (int i = 0; i < fields.length; ++i)
			lets[i] = new Configlet(makeConfigField(fields[i].getParameter()), fields[i].getParamValue());
		ResourceInstance parentInstance = tri.getParentInstance() == null ? null : makeInstance(tri.getParentInstance());
		return new ResourceInstance(tri.commonName, tri.getState(), makeResource(tri.getResource()), lets, parentInstance);
	}

	@Override
	public ResourceInstance getInstance(String id) throws RepositoryException
	{
		return makeInstance(getInstanceById(id));
	}

	@Override
	public boolean instanceExists(String id) throws RepositoryException
	{
		return findInstanceById(id) != null;
	}

	@Override
	public ResourceInstance[] listInstances(String userName)
			throws RepositoryException
	{
		TSSGResourceInstance[] instances = listTSSGInstances();
		ArrayList<ResourceInstance> result = new ArrayList<ResourceInstance>();
		for (int i = 0; i < instances.length; ++i)
			try
			{
				result.add(makeInstance(instances[i]));	
			}
			catch (RepositoryException e)
			{
				e.printStackTrace();
			}
		return result.toArray(new ResourceInstance[0]);
	}

	@Override
	public void removeInstance(String id) throws RepositoryException
	{
		client.deleteObject(getInstanceById(id));
	}

	protected TSSGResourceInstance doSetInstance(ResourceInstance instance)
			throws RepositoryException
	{
		TSSGResourceInstance old = findInstanceById(instance.getId());
		if (old != null)
		{
			old.setState(instance.getState());
			
			for (Configlet c : instance.getConfiguration())
				 old.setAttribute(c.getName(), c.getValueString());				 
			 
			/*
			 old.getGeometry().x = instance.getGeometry().x;
			 old.getGeometry().y = instance.getGeometry().y;
			 old.getGeometry().w = instance.getGeometry().w;
			 old.getGeometry().h = instance.getGeometry().h;
			 
			 for (TSSGConfiglet c : old.getConfigurationData())
				 client.updateObject(c);
			 client.updateObject(old.getGeometry());
			 client.updateObject(old);
			 */
		}
		else
		{
			TSSGResource type = getTSSGResourceByType(instance.getSimpleType());
			old = new TSSGResourceInstance(client, instance.getId(), instance.getState(), type);
			
			for (Configlet c : instance.getConfiguration())
				 old.setAttribute(c.getName(), c.getValueString());	
			
			 for (TSSGConfiglet c : old.getConfigurationData())
				 client.addObject(c);
			 client.addObject(old);
		}
		
		return old;
	}
	
	@Override
	public void setInstance(ResourceInstance instance)
		throws RepositoryException
	{
		doSetInstance(instance);
	}

}
