/**
 * 
 */
package teagle.vct.model;

import java.util.List;

/**
 * @author sim
 * 
 */
public interface ResourceInstance extends Entity {

	public boolean isShared();

	public void isShared(boolean shared);

	public ResourceSpec getResourceSpec();

	public void setResourceSpec(ResourceSpec resourceSpec);

	public ResourceInstanceState.State getState();

	public void setState(ResourceInstanceState.State state);

	/**
	 * getting the list of configurations. This returns a copy of the list. Any
	 * changes are not reflected on the contained list, use add and remove for
	 * this purpose.
	 * 
	 * @return list of configurations
	 */
	public List<? extends Configuration> getConfigurations();

	public List<? extends Configuration> getReferences();

	public void addConfiguration(Configuration configuration);

	public void removeConfiguration(Configuration configuration);

	public Geometry getGeometry();

	public void setGeometry(Geometry geometry);

	public ResourceInstance getParentInstance();

	void setParentInstance(ResourceInstance parentInstance);
}
