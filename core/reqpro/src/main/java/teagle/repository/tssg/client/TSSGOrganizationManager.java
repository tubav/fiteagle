package teagle.repository.tssg.client;

import java.io.IOException;
import java.util.ArrayList;

import teagle.repository.Organization;
import teagle.repository.RepositoryException;
import teagle.repository.Resource;
import teagle.repository.client.OrganizationManager;
import teagle.repository.tssg.model.Cost;
import teagle.repository.tssg.model.Email;
import teagle.repository.tssg.model.Organisation;
import teagle.repository.tssg.model.Person;
import teagle.repository.tssg.model.PersonRole;
import teagle.repository.tssg.model.TSSGResource;
import teagle.repository.tssg.model.TSSGResourceInstance;

public class TSSGOrganizationManager extends AbstractTSSGManager implements OrganizationManager
{
	private Organisation[] organisations = null;

	public TSSGOrganizationManager(ClassLoader loader) throws IOException
	{
		super(loader);
		client.addAliases("resourceInstance", TSSGResourceInstance.class, "resourceSpec", TSSGResource.class, "cost", Cost.class, "email", Email.class, "organisation", Organisation.class, "person", Person.class, "personRole", PersonRole.class);
	}
	
	protected Organization makeOrganization(Organisation o)  throws RepositoryException
	{
		return new Organization(o.getId(), o.getName(), "<deprecated attribute>");
	}

	@Override
	public Organization getOrganization(int id) throws RepositoryException
	{
		Organisation o = (Organisation)client.getObject("organisation", id);
		return makeOrganization(o);
	}

	@Override
	public int addOrganization(Organization organization)
			throws RepositoryException
	{
		if (organization.id >= 0)
			throw new RepositoryException("That object is already in the repo: " + organization);
		
		organization.id = client.addObject("organisation", new Organisation(client, organization.name)).getId();
		
		return organization.id;
	}

	protected Organisation[] listOrganisations() throws RepositoryException
	{
		if (organisations == null)
			organisations = client.listObjects(Organisation.class);
		return organisations;
	}
	
	@Override
	public Organization[] listOrganizations() throws RepositoryException
	{
		Organisation[] orgs = listOrganisations();
		Organization[] result = new Organization[orgs.length];
		
		for (int  i = 0; i < orgs.length; ++i)
			result[i] = new Organization(orgs[i].getId(), orgs[i].getName(), "<deprecated attribute>");
		
		return result;
	}

	@Override
	public void removeOrganization(Organization organization)
			throws RepositoryException
	{
		removeOrganization(organization.id);
		
	}

	@Override
	public void removeOrganization(int id) throws RepositoryException
	{
		if (id < 0)
			throw new RepositoryException("Illegal id: " + id);
		
		client.deleteObject("organisation", id);
	}

	public Organization[] getOrganizations(String userName)
			throws RepositoryException
	{
		Organisation[] allOrgs = client.listObjects(Organisation.class);
		ArrayList<Organization> result = new ArrayList<Organization>();
		
		for (Organisation o : allOrgs)
			for (Person p : o.getPeople())
				if (p.userName.equals(userName))
				{
					result.add(makeOrganization(o));
					break;
				}
		
		return result.toArray(new Organization[0]);
	}
	
	public Person[] getPersons() throws RepositoryException
	{
		Organisation[] allOrgs = client.listObjects(Organisation.class);
		ArrayList<Person> result = new ArrayList<Person>();
		
		for (Organisation o : allOrgs)
			for (Person p : o.getPeople())
			{
					result.add(p);
			}
		
		return result.toArray(new Person[0]);
	}
	
	public Person[] getPersonsByOrganization(String organisationName) throws RepositoryException
	{
		Organisation[] allOrgs = client.listObjects(Organisation.class);
		ArrayList<Person> result = new ArrayList<Person>();
		
		for (Organisation o : allOrgs)
		{
			if(o.getName().equals(organisationName))
			{
				for (Person p : o.getPeople())
				{
						result.add(p);
				}
				break;
			}
		}
		return result.toArray(new Person[0]);
	}
	
	
	protected Organisation getOrganisationByTSSGResource(TSSGResource r) throws RepositoryException
	{
		Organisation[] organisations = listOrganisations();
		for (Organisation o : organisations)
			if (o.ownsResource(r))
				return o;
		return null;
	}
	
	protected Organisation getOrganisationByResource(Resource r) throws RepositoryException
	{
		return getOrganisationByTSSGResource(client.getObject(TSSGResource.class, r.getId()));
	}
	
	public Organization getOrganizationByResource(Resource r) throws RepositoryException
	{
		return makeOrganization(getOrganisationByResource(r));
	}
}
