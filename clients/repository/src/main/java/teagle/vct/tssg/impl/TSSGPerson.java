/**
 * 
 */
package teagle.vct.tssg.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import teagle.vct.model.Email;
import teagle.vct.model.ModelManager;
import teagle.vct.model.Organisation;
import teagle.vct.model.PersonRole;
import teagle.vct.model.Person;
import teagle.vct.model.RepositoryException;

/**
 * @author sim
 *
 */
@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGPerson extends TSSGObject implements Person, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6544806703604481502L;

	protected static TSSGCache<TSSGPerson> cache = new TSSGCache<TSSGPerson>("person", new TSSGPerson[]{});
	
	@XmlElement(name="personRole")
	@XmlElementWrapper(name="personRoles")
	private List<TSSGPersonRole> personRoles = new ArrayList<TSSGPersonRole>();
	
	private String userName = "";
	
	private String fullName = "";
	
	private String password = "";
	
	@XmlElement(name="email")
	@XmlElementWrapper(name="emails")
	private List<TSSGEmail> emails = new ArrayList<TSSGEmail>();
	
	@XmlElement(name="organisation")
	@XmlElementWrapper(name="organisations")
	private List<TSSGOrganisation> organisations = new ArrayList<TSSGOrganisation>();
	
	public TSSGPerson() {
	}

	protected TSSGPerson(Person person) {
		userName = person.getUserName();
		fullName = person.getFullName();
		for (Email email : person.getEmails()) {
			addEmail(email);
		}
		for (PersonRole role : person.getRoles()) {
			addRole(role);
		}
		for (Organisation org : person.getOrganisations()) {
			addOrganisation(org);
		}		
		flag = true;
	}
	
	public static TSSGPerson find(String id) {
		return cache.find(id);
	}

	public static List<? extends Person> list() {
		return cache.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGPerson persist() {
//		for (ListIterator<TSSGOrganisation> it = organisations.listIterator(); it.hasNext(); ) {
//			it.set(it.next().resolve().persist());
//		}
		for (ListIterator<TSSGPersonRole> it = personRoles.listIterator(); it.hasNext(); ) {
			it.set(it.next().resolve().persist());
		}
		for (ListIterator<TSSGEmail> it = emails.listIterator(); it.hasNext(); ) {
			it.set(it.next().resolve().persist());
		}
		TSSGPerson persisted = cache.persist(this);
		return persisted;
	}

	@Override
	public void delete() throws RepositoryException {
		for (ListIterator<TSSGPersonRole> it = personRoles.listIterator(); it.hasNext(); ) {
			it.next().resolve().delete();
		}
		for (ListIterator<TSSGEmail> it = emails.listIterator(); it.hasNext(); ) {
			it.next().resolve().delete();
		}
		cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGPerson resolve() {
		return id != null ? cache.find(id) : this;
	}
	
	@Override
	public boolean isModified() {
		boolean modified = super.isModified();
//		for (TSSGOrganisation org : organisations) {
//			modified |= org.resolve().isModified();
//		}
		for (TSSGPersonRole role : personRoles) {
			modified |= role.resolve().isModified();
		}
		for (TSSGEmail email : emails) {
			modified |= email.resolve().isModified();
		}
		return modified; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGPersonInstance getInstance() {
		return new TSSGPersonInstance(this);
	}	

	@Override
	public String getFullName() {
		return fullName;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setFullName(String fullName) {
		this.fullName = fullName;
		flag = true;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
		flag = true;
	}

	@Override
	public void addEmail(Email email) {
		System.out.println("addEmail called...person.getName = " + this.getUserName());
		TSSGEmail em = (email instanceof TSSGEmail) ? (TSSGEmail)email : new TSSGEmail(email);
		em.setPerson(this);
		emails.add(em);
		flag = true;
	}

	@Override
	public void addOrganisation(Organisation organisation) {
		TSSGOrganisation org = (organisation instanceof TSSGOrganisation) ? (TSSGOrganisation)organisation : new TSSGOrganisation(organisation);
		organisations.add(org);
		flag = true;
	}

	@Override
	public void addRole(PersonRole personRole) {
		TSSGPersonRole r = (personRole instanceof TSSGPersonRole) ? (TSSGPersonRole)personRole : new TSSGPersonRole(personRole);
		personRoles.add(r);
		flag = true;
	}

	@Override
	public List<? extends Email> getEmails() {
		List<Email> array = new ArrayList<Email>();
		for (TSSGEmail email : emails) {
			TSSGEmail e = (TSSGEmail)TSSGEmail.find(email.getId());
			array.add(e != null ? e : email);
		}
		return array;
	}

	@Override
	public List<? extends Organisation> getOrganisations() {
		List<Organisation> array = new ArrayList<Organisation>();
		for (TSSGOrganisation org : organisations) {
			TSSGOrganisation o = (TSSGOrganisation)TSSGOrganisation.find(org.getId());
			array.add(o != null ? o : org);
		}
		return array;
	}

	@Override
	public List<? extends PersonRole> getRoles() {
		List<PersonRole> array = new ArrayList<PersonRole>();
		for (TSSGPersonRole role : personRoles) {
			TSSGPersonRole r = (TSSGPersonRole)TSSGPersonRole.find(role.getId());
			array.add(r != null ? r : role);
		}
		return array;
	}

	@Override
	public void removeEmail(Email email) {
		emails.remove(email);
		flag = true;
	}

	@Override
	public void removeOrganisation(Organisation organisation) {
		organisations.remove(organisation);
		flag = true;
	}

	@Override
	public void removeRole(PersonRole role) {
		personRoles.remove(role);
		flag = true;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
		flag = true;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	@XmlRootElement(name="personInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGPersonInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4880071517781061939L;

		private String userName;
		
		private String fullName;
		
		private String password;
		
		@XmlElementWrapper(name="emails")
		@XmlElement(name="email")
		private List<String> emailIds = new ArrayList<String>();
		
		@XmlElementWrapper(name="personRoles")
		@XmlElement(name="personRole")
		private List<String> personRoleIds = new ArrayList<String>();
		
		@XmlElementWrapper(name="organisations")
		@XmlElement(name="organisation")
		private List<String> organisationIds = new ArrayList<String>();
		
		protected TSSGPersonInstance() {
		}
		
		protected TSSGPersonInstance(TSSGPerson instance) {
			this.userName = instance.userName;
			this.fullName = instance.fullName;
			this.password = instance.password;
			for (TSSGEmail email : instance.emails) {
				emailIds.add(email.getId());
			}
			for (TSSGPersonRole role : instance.personRoles) {
				personRoleIds.add(role.getId());
			}
			for (TSSGOrganisation org : instance.organisations) {
				organisationIds.add(org.getId());
			}
		}
	}

	public static void main(String[] args) {
//		TSSGPerson person = (TSSGPerson)ModelManager.getInstance().findPersonByUserName("testuser");

		TSSGPerson person = new TSSGPerson();
		person.fullName = "repouser";
		person.password = "ad0234829205b9033196ba818f7a872b";//testing for re-hashing a hash in the repos
		person.userName = "repouser10";

		person = person.persist();
		
		TSSGEmail email1 = new TSSGEmail();
		email1.setAddress("stefan.harder@fokus.fraunhofer.de");
		TSSGEmail email2 = new TSSGEmail();
		email2.setAddress("second@example.com");
		
		person.addEmail(email1);
		person.addEmail(email2);
	
		TSSGOrganisation o = (TSSGOrganisation)ModelManager.getInstance().getOrganisation("Fraunhofer FOKUS");
		o.addPerson(person);
		o.persist();//ModelManager.getInstance().persist(o);
		person.addOrganisation(o);
		
		person.addRole(TSSGPersonRole.find("4"));
		
		//md5("lala") = 2e3817293fc275dbee74bd71ce6eb056
//		person.setPassword("2e3817293fc275dbee74bd71ce6eb056");
//		person.setPassword("test");
		person = person.persist();
		
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		TSSGPerson person = find("233");
//		try {
//			person.delete();
//		} catch (RepositoryException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
