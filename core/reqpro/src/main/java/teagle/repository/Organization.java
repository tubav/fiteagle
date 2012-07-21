package teagle.repository;

/**
 * Class to model organization data within the XML repositories.
 * @author alexandru.mihaiuc@fokus.fraunhofer.de
 *
 */
public class Organization {
	public Organization(String name) 
	{
		id = -1;
		this.name = name;
		userName = "<deprecated attribute>";
	}
	
	public Organization(int id, String name, String userName)
	{
		this.id = id;
		this.name = name;
		this.userName = userName;
	}
	
	public int getId()
	{
		return id;
	}
	
	
	/**
	 * Unique, will simulate auto increment, will be ignored when adding new organization.
	 */
	public int id;
	/**
	 * 	Assigned name for the organization.
	 */
	public String name;
	/**
	 * 	The creator of the organization (user names are unique).
	 */
	public String userName;
}
