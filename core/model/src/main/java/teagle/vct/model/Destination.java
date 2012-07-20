/**
 * 
 */
package teagle.vct.model;

/**
 * @author sim
 * 
 */
public interface Destination {

	public String getIdentifier();

	public void setIdentifier(String identifier);

	public ResourceInstance getResourceIstance();

	public void setResourceInstance(ResourceInstance resourceInstance);

	public int getSide();

	public void setSide(int side);

	public int getPos();

	public void setPos(int pos);

}
