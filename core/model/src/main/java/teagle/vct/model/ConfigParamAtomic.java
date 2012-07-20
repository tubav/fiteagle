/**
 * 
 */
package teagle.vct.model;

/**
 * @author sim
 * 
 */
public interface ConfigParamAtomic extends ConfigParam {

	public String getDefaultValue();

	public void setDefaultValue(String defaultValue);

	public String getType();

	public void setType(String type);

	public boolean isReference();
}
