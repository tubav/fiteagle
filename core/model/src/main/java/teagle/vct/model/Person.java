/**
 * 
 */
package teagle.vct.model;

import java.util.List;

/**
 * @author sim
 *
 */
public interface Person {

	public String getUserName();
	public void setUserName(String userName);
	
	public String getFullName();
	public void setFullName(String fullName);
		
	public String getPassword();
	public void setPassword(String password);
		
	/**
	 * getting the list of organisations. This returns a copy of the list. Any changes are not
	 * reflected on the contained list, use add and remove for this purpose.
	 * 
	 * @return list of organisations
	 */
	public List<? extends Organisation> getOrganisations();
	public void addOrganisation(Organisation organisation);
	public void removeOrganisation(Organisation organisation);
	
	/**
	 * getting the list of person's roles. This returns a copy of the list. Any changes are not
	 * reflected on the contained list, use add and remove for this purpose.
	 * 
	 * @return list of person's roles
	 */
	public List<? extends PersonRole> getRoles();
	public void addRole(PersonRole role);
	public void removeRole(PersonRole role);
	
	/**
	 * getting the list of email addresses. This returns a copy of the list. Any changes are not
	 * reflected on the contained list, use add and remove for this purpose.
	 * 
	 * @return list of email addresses
	 */
	public List<? extends Email> getEmails();
	public void addEmail(Email email);
	public void removeEmail(Email email);
	
}
