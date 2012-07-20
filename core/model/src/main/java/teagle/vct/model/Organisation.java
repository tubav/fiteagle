/**
 * 
 */
package teagle.vct.model;

import java.util.List;

/**
 * @author sim
 *
 */
public interface Organisation {

	public String getName();
	public void setName(String name);
	
	/**
	 * getting the list of organisation roles. This returns a copy of the list. Any changes are not
	 * reflected on the contained list, use add and remove for this purpose.
	 * 
	 * @return list of organisation roles
	 */
	public List<? extends OrganisationRole> getRoles();
	public void addRole(OrganisationRole role);
	public void removeRole(OrganisationRole role);
	
	/**
	 * getting the list of resource specifications. This returns a copy of the list. Any changes are not
	 * reflected on the contained list, use add and remove for this purpose.
	 * 
	 * @return list of resource specifications
	 */
	public List<? extends ResourceSpec> getResourceSpecs();
	public void addResourceSpec(ResourceSpec resourceSpec);
	public void removeResourceSpec(ResourceSpec resourceSpec);
	
	/**
	 * getting the list of persons. This returns a copy of the list. Any changes are not
	 * reflected on the contained list, use add and remove for this purpose.
	 * 
	 * @return list of persons
	 */
	public List<? extends Person> getPersons();
	public void addPerson(Person person);
	public void removePerson(Person person);
	
}
