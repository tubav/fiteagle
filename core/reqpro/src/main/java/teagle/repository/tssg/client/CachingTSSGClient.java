package teagle.repository.tssg.client;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;

import teagle.repository.RepositoryException;
import teagle.repository.tssg.model.AbstractTSSGEntity;

public class CachingTSSGClient extends TSSGClient
{

	protected class CacheEntry
	{
		private boolean haveList = false;
		private HashMap<Integer, AbstractTSSGEntity> objectCache = new HashMap<Integer, AbstractTSSGEntity>();
		private long age;
		
		public AbstractTSSGEntity getObject(Integer id)
		{
			if (!checkAge())
				return null;
			//System.out.println("Got from cache: " + id);
			return objectCache.get(id);
		}
		
		@SuppressWarnings("rawtypes")
		public Collection getList()
		{
			if (haveList && checkAge())
				return objectCache.values();
			return null;
		}
		
		public AbstractTSSGEntity addObject(AbstractTSSGEntity o)
		{
//			if (objectCache.containsKey(o.getId()))
//				invalidate();
			
//			return setObject(o);
			invalidate();
			return o;
		}
			
		public AbstractTSSGEntity  setObject(AbstractTSSGEntity o)
		{
			objectCache.put(o.getId(), o);
			return o;
		}
		
		//TODO: Make sure those are really AbstractTSSGEntities 
		public Collection setList(Collection entities)
		{
			invalidate();
			age = System.currentTimeMillis() / 1000;
			
			for (Object e : entities)
				setObject((AbstractTSSGEntity)e);
			
			haveList = true;
			
			return entities;
		}
		
		public boolean hasList()
		{
			return haveList;
		}
		
		public void invalidate()
		{
			haveList = false;
			objectCache.clear();
		}
		
		private boolean checkAge()
		{
			long now = System.currentTimeMillis() / 1000;
			if (now <= age + lifetime)
				return true;
			
			invalidate();
			return false;
		}
	}
	
	private HashMap<String, CacheEntry> cache;
	private int lifetime;
	private static CachingTSSGClient instance = null;
	private static URL __repoUrl = null;
	
	public static void config(URL repoUrl)
	{
		__repoUrl = repoUrl;
	}
	
	public static synchronized CachingTSSGClient getInstance() throws IOException
	{
		if (instance == null)
		{
			if (__repoUrl == null)
				throw new IOException("Not properly configured. repo url is not set. call config() first!");
			instance = new CachingTSSGClient(null, __repoUrl, 3600);
		}
		return instance;
	}
	
	public CachingTSSGClient(ClassLoader loader, URL repoUrl,  int lifetime, Object... aliases)
			throws IOException
	{
		super(loader, repoUrl, aliases);
		cache = new HashMap<String, CacheEntry>();
		this.lifetime = lifetime;
	}

	@Override
	public AbstractTSSGEntity getObject(String type, int id) throws RepositoryException
	{
		CacheEntry entry = getEntry(type);
		if (!entry.hasList())
			doListObjects(type);
		assert(entry.hasList());
		
		AbstractTSSGEntity entity = entry.getObject(id); 
		
		if (entity == null)
		{
			entity = super.getObject(type, id);
			if (entry.hasList())
				entry.invalidate();
			entry.setObject(entity);
		}
		
		return entity;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection listObjects(String type) throws RepositoryException
	{
		CacheEntry entry = getEntry(type);	
		Collection objects = entry.getList();
		
		if (objects == null)
			return doListObjects(type);
		
		return objects;
	}
	
	@SuppressWarnings("rawtypes")
	private Collection doListObjects(String type) throws RepositoryException
	{
		return getEntry(type).setList(super.listObjects(type));
	}
	
	@Override
	public AbstractTSSGEntity addObject(String type, AbstractTSSGEntity data) throws RepositoryException
	{
		data = super.addObject(type, data);
		
		CacheEntry entry = getEntry(type);
		entry.addObject(data);
		
		return data;
	}
	
	@Override 
	public void updateObject(String type, AbstractTSSGEntity data) throws RepositoryException
	{
		super.updateObject(type, data);
		
		CacheEntry entry = getEntry(type);
		entry.addObject(data);
	}
	
	@Override
	public void deleteObject(String type, int id) throws RepositoryException 
	{
		getEntry(type).invalidate();
		super.deleteObject(type, id);
	}
	
	protected CacheEntry getEntry(String type)
	{
		CacheEntry e = cache.get(type);
		if (e == null)
		{
			e = new CacheEntry();
			cache.put(type, e);
		}
		return e;
	}
	
	public void clearCache()
	{
		cache.clear();
	}
}
