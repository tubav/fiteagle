package teagle.repository.tssg.model;

import teagle.repository.tssg.client.TSSGClient;

public class Person extends AbstractTSSGEntity
{
	public Person(TSSGClient client, String userName, String fullName,  String password, Organisation orga)
	{
		super(client);
		organisations = new Organisation[] { orga };
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;
	}

	public String fullName;
	public String userName;
	public String password;
	private PersonRole[] personRoles;
	private Email[] emails;
	private Organisation[] organisations;
		
}
