package teagle.vct.model;

import java.util.List;

/**
 * @author sim
 * 
 */
public interface ConfigParamComposite extends ConfigParam {

	/**
	 * getting the list of atomic configuration parameters. This returns a copy
	 * of the list. Any changes are not reflected on the contained list, use add
	 * and remove for this purpose.
	 * 
	 * @return list of atomic configuration parameters
	 */
	public List<? extends ConfigParamAtomic> getConfigurationParameters();

	public void addConfigurationParameter(ConfigParamAtomic parameter);

	public void removeConfigurationParameter(ConfigParamAtomic parameter);

}
