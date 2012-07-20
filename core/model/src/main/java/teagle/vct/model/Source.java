/**
 * 
 */
package teagle.vct.model;

/**
 * @author sim
 * 
 */
public interface Source {

	public String getIdentifier();

	public void setIdentifier(String identifier);

	public ResourceInstance getResourceInstance();

	public void setResourceInstance(ResourceInstance resourceInstance);

	public int getSide();

	public void setSide(int side);

	public int getPos();

	public void setPos(int pos);

}
