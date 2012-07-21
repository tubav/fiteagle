package teagle.repository;

import java.util.Collection;

/**
 * With this class, a whole VCT, with composing resources can be described.
 * @author alexandru.mihaiuc@fokus.fraunhofer.de
 *
 */
public class VCT {
	public VCT(String user, String name, String state, Testbed testbed)
	{
		this.user = user;
		this.name = name;
		this.permissions = "<deprecated attribute>";
		this.testbed = testbed;
		if (state.equals("unbooked"))
			state = "UNPROVISIONED";
		this.state = state;
	}
	
	public VCT(String user, String name, String state, Collection<ResourceInstance> components)
	{
		this(user, name, state, new Testbed(components));
	}
	
	public ResourceInstance[] getResourceInstances()
	{
		return testbed.components.toArray(new ResourceInstance[0]);
	}
	
	/**
	 * User name for the owner of the VCT. (unique)
	 */
	public String user;
	/**
	 * Assigned name of the VCT.
	 */
	public String name;
	/**
	 * Contains all the components and all the connections between them; the contents of the VCT.
	 */
	public Testbed testbed;
	/**
	 * Availability information.
	 */
	//public List<Availability> availability;
	/**
	 * TODO: what's this?
	 */
	public String state;
	/**
	 * TODO: what's this?
	 */
	public String permissions;
	/**
     * the booking status returned from the orchestration engine
     */
	//public OrchestrateReturn orchestrateReturn;
	
	public String getState()
	{
		return state;
	}
		
/*	public void setUnbooked()
	{
		state = "UNPROVISIONED";
	}
	
	public void setBooked()
	{
		state = "PROVISIONED";
	}*/
}
