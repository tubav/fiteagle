/**
 * 
 */
package teagle.vct.model;

import java.util.List;

/**
 * @author sim
 * 
 */
public interface ResourceSpec extends Entity {
	public List<? extends ConfigParamAtomic> getConfigurationParameters();

	public void setConfigurationParameters(
			List<? extends ConfigParamAtomic> params);

	public ConfigParam getConfigParam();

	public String getType();

	public String getProvider();

	public String getOwner();

	public String getUrl();

	public void setUrl(String url);

	public boolean isUsed();

}
