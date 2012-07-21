package teagle.repository;

/**
 * Model for PTMs inside the repository.
 * @author alexandru.mihaiuc@fokus.fraunhofer.de
 *
 */
public class PTM {
	public PTM(){}
	
/*	public PTM(String name, String url, String userName, int organizationId)
	{
		repoid = 0;
		id = "";
		this.name = name;
		this.url = url;
		this.organizationId = organizationId;
		this.userName = userName;
	}*/
	
	public PTM(String id, String name, String url, String userName, int organizationId)
	{
		repoid = 0;
		this.id = id;
		this.name = name;
		this.url = url;
		this.organizationId = organizationId;
		this.userName = userName;
	}
	
	public PTM(int repoid, String id, String name, String url, String userName, int organizationId)
	{
		this.repoid = repoid;
		this.id = id;
		this.name = name;
		this.url = url;
		this.organizationId = organizationId;
		this.userName = userName;
	}
	
	public String getId()
	{
		return id;
	}
	
	public int repoid;
	
	/**
	 * The unique assigned id (internal name) in a form like "fokus-public-ptm1". 
	 */
	public String id;
	/**
	 * Assigned name for the PTM.
	 */
	public String name;
	/**
	 * The id of the creator (user names are unique). 
	 */
	public String userName;
	/**
	 * Link to the organization owning the PTM. (Users are not enough, as one user can be a member of multiple organizations).
	 * See also class {@link Organization}.
	 */
	public int organizationId;
	/**
	 * Address to access the URL.
	 * TODO: and?
	 */
	public String url;
}
