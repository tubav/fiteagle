/**
 * 
 */
package teagle.vct.model;

import java.util.List;

/**
 * @author sim
 *
 */
public interface Configuration extends Entity {

	public String getValue();
	public void setValue(String value);
	
	/**
	 * getting the list of atomic configuration parameters. This returns a copy of the list. Any changes are not
	 * reflected on the contained list, use add and remove for this purpose.
	 * 
	 * @return list of atomic configuration parameters
	 */
	public ConfigParamAtomic getConfigParamAtomic();
	public void setConfigParamatomic(ConfigParamAtomic param);

	public boolean isReferenceArray();
	public boolean isReference();
}
