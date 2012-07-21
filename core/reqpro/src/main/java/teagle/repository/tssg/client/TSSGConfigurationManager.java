package teagle.repository.tssg.client;

import java.io.IOException;

import teagle.repository.RepositoryException;
import teagle.repository.tssg.model.Organisation;
import teagle.repository.tssg.model.Person;

public class TSSGConfigurationManager extends AbstractTSSGManager 
{

	public TSSGConfigurationManager(ClassLoader loader)
			throws IOException
	{
		super(loader);
		client.addAliases("person", Person.class, "organisation", Organisation.class);
	}

	public void newConfig(String userName, String fullName, String organization, String password) throws RepositoryException
	{
		Organisation orga = null;
		
		for (Organisation o : client.listObjects(Organisation.class))
			if (o.getName().equals(organization))
			{
				orga = o;
				break;
			}
		
		if (orga == null)
			throw new RepositoryException("No such organisation: " + organization);
		
		client.addObject(new Person(client, userName, fullName, password, orga));
	}

}
