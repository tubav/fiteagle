package teagle.repository.tssg.model;

import java.util.Comparator;

import teagle.repository.RepositoryException;
import teagle.repository.tssg.client.TSSGClient;

public class AbstractTSSGEntity
{
	static class TSSGEntityComparator implements Comparator<AbstractTSSGEntity>
	{
	
		@Override
		public int compare(AbstractTSSGEntity o1, AbstractTSSGEntity o2) {
			return o1.getId().compareTo(o2.getId());
		}
		
	}

	private Integer id;
	private boolean isRoot = false;
	private boolean initialized = false;
	protected TSSGClient client;
	
	protected AbstractTSSGEntity(TSSGClient client)
	{
		this(client, true);
	}
	
/*	public AbstractTSSGEntity()
	{}
	*/
/*	protected AbstractTSSGEntity(boolean x)
	{
		initialized = x;
	}*/
	
	public AbstractTSSGEntity(TSSGClient client, boolean b) {
		this.client = client;
		initialized = b;
	}

	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id) throws RepositoryException
	{
		if (id != null && this.id != null && !this.id.equals(id))
			throw new RepositoryException("Id already set");
		this.id = id;
			
	}
	
	public boolean isPersistent()
	{
		return id != null;
	}
	
	public void setClient(TSSGClient client)
	{
		this.client = client;
	}
	
	public final void init() 
		throws RepositoryException
	{
		if (!initialized)
		{
			_init();
			initialized = true;
		}
	}
	
	public boolean equals(Object o)
	{
		return ((o instanceof AbstractTSSGEntity) && ((AbstractTSSGEntity)o).getId() != null && ((AbstractTSSGEntity)o).getId().equals(id)) || super.equals(o);
	}
	
	public boolean isRoot()
	{
		return this.isRoot;
	}
	
	public void setRoot(boolean b)
	{
		this.isRoot = b;
	}
	
	protected void _init() throws RepositoryException {}
}
