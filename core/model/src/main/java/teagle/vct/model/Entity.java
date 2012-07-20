/**
 * 
 */
package teagle.vct.model;

/**
 * @author sim
 * 
 */
public interface Entity {

	public boolean isPersisted();

	public boolean isModified();

	public String getCommonName();

	public void setCommonName(String commonName);

	public String getDescription();

	public void setDescription(String description);

	public Entity persist();

	public void delete() throws RepositoryException;

}
