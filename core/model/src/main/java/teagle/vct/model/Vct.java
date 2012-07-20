/**
 * 
 */
package teagle.vct.model;

import java.util.List;

/**
 * @author sim
 *
 */
public interface Vct extends Entity {

	public abstract boolean isShared();
	
	public abstract VctState.State getState();
	public abstract void setState(VctState.State state);
	
	/**
	 * getting the list of resource instances. This returns a copy of the list. Any changes are not
	 * reflected on the contained list, use add and remove for this purpose.
	 * 
	 * @return list of resource instances
	 */
	public abstract List<? extends ResourceInstance> getResourceInstances();
	public abstract void addResourceInstance(ResourceInstance instance);
	public abstract void removeResourceInstance(ResourceInstance instance);
	

	public abstract Person getPerson();
	public abstract void setPerson(Person user);
	
}
