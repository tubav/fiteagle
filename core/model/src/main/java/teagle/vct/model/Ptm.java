/**
 * 
 */
package teagle.vct.model;

import java.util.List;

/**
 * @author sim
 * 
 */
public interface Ptm extends Entity {
	public Organisation getOrganisation();

	public void setOrganisation(Organisation organisation);

	public String getUrl();

	public String getLegacyUrl();

	public void setUrl(String url);

	public void setLegacyUrl(String url);

	public List<? extends ResourceSpec> getResourceSpecs();

	public void addResourceSpec(ResourceSpec resourceSpec);

	public void removeResourceSpec(ResourceSpec resourceSpec);

	public boolean supportsResourceSpec(ResourceSpec resourceSpec);

}
