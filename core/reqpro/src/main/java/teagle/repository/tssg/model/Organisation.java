package teagle.repository.tssg.model;

import teagle.repository.RepositoryException;
import teagle.repository.tssg.client.TSSGClient;

public class Organisation extends AbstractTSSGEntity
{
	private String name;
	private OrganisationRole[] hasOrgRoles;
	private Person[] people;
	private TSSGResource[] hasResources;
	
	public Organisation(TSSGClient client, String name)
	{
		super(client);
		this.name = name;
		hasOrgRoles = new OrganisationRole[0];
		people = new Person[0];
		hasResources = new TSSGResource[0];
	}
	
	public OrganisationRole[] getOrganizationRoles() throws RepositoryException
	{
		return hasOrgRoles;
	}

	public String getName() throws RepositoryException
	{
		return name.trim();
	}

	public Person[] getPeople() throws RepositoryException
	{
		init();
		return people;
	}

	public TSSGResource[] getResources() throws RepositoryException
	{
		init();
		return hasResources;
	}
	
	public boolean ownsResource(TSSGResource resource) throws RepositoryException
	{
		TSSGResource[] resources = getResources();
		if (resources  != null)
			for (TSSGResource r : resources)
				if (r.equals(resource))
					return true;
		return false;
	}
	
	public void addResource(TSSGResource r) throws RepositoryException
	{
		TSSGResource[] old = getResources();
		TSSGResource[] nr = new TSSGResource[old.length + 1];
		for (int i = 0; i < old.length; ++i)
			if (old[i].equals(r))
				throw new RepositoryException("Organisation " + getName() + " already owns resource " + r.getId());
			else
				nr[i] = old[i];
		
		nr[old.length] = r;
		hasResources = nr;
	}
	
	@Override
	protected void _init() throws RepositoryException
	{
		if (people == null)
			people = new Person[0];
		else
			for (int i = 0; i < people.length; ++i)
				people[i] = client.getObject(Person.class, people[i].getId());
		if (hasResources == null)
			hasResources = new TSSGResource[0];
		else
			for (int i = 0; i < hasResources.length; ++i)
				hasResources[i] = client.getObject(TSSGResource.class, hasResources[i].getId());
	}
}
