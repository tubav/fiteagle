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
import teagle.vct.model.Person;
import teagle.vct.model.PersonRole;
import teagle.vct.model.RepositoryException;

/**
 * @author sim
 * 
 */
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGPerson extends TSSGObject implements Person, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6544806703604481502L;

	protected static TSSGCache<TSSGPerson> cache = new TSSGCache<TSSGPerson>(
			"person", new TSSGPerson[] {});

	@XmlElement(name = "personRole")
	@XmlElementWrapper(name = "personRoles")
	private final List<TSSGPersonRole> personRoles = new ArrayList<TSSGPersonRole>();

	private String userName = "";

	private String fullName = "";

	private String password = "";

	@XmlElement(name = "email")
	@XmlElementWrapper(name = "emails")
	private final List<TSSGEmail> emails = new ArrayList<TSSGEmail>();

	@XmlElement(name = "organisation")
	@XmlElementWrapper(name = "organisations")
	private final List<TSSGOrganisation> organisations = new ArrayList<TSSGOrganisation>();

	public TSSGPerson() {
	}

	protected TSSGPerson(final Person person) {
		this.userName = person.getUserName();
		this.fullName = person.getFullName();
		for (final Email email : person.getEmails())
			this.addEmail(email);
		for (final PersonRole role : person.getRoles())
			this.addRole(role);
		for (final Organisation org : person.getOrganisations())
			this.addOrganisation(org);
		this.flag = true;
	}

	public static TSSGPerson find(final String id) {
		return TSSGPerson.cache.find(id);
	}

	public static List<? extends Person> list() {
		return TSSGPerson.cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGPerson persist() {
		// for (ListIterator<TSSGOrganisation> it =
		// organisations.listIterator(); it.hasNext(); ) {
		// it.set(it.next().resolve().persist());
		// }
		for (final ListIterator<TSSGPersonRole> it = this.personRoles
				.listIterator(); it.hasNext();)
			it.set(it.next().resolve().persist());
		for (final ListIterator<TSSGEmail> it = this.emails.listIterator(); it
				.hasNext();)
			it.set(it.next().resolve().persist());
		final TSSGPerson persisted = TSSGPerson.cache.persist(this);
		return persisted;
	}

	@Override
	public void delete() throws RepositoryException {
		for (final ListIterator<TSSGPersonRole> it = this.personRoles
				.listIterator(); it.hasNext();)
			it.next().resolve().delete();
		for (final ListIterator<TSSGEmail> it = this.emails.listIterator(); it
				.hasNext();)
			it.next().resolve().delete();
		TSSGPerson.cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGPerson resolve() {
		return this.id != null ? TSSGPerson.cache.find(this.id) : this;
	}

	@Override
	public boolean isModified() {
		boolean modified = super.isModified();
		// for (TSSGOrganisation org : organisations) {
		// modified |= org.resolve().isModified();
		// }
		for (final TSSGPersonRole role : this.personRoles)
			modified |= role.resolve().isModified();
		for (final TSSGEmail email : this.emails)
			modified |= email.resolve().isModified();
		return modified;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGPersonInstance getInstance() {
		return new TSSGPersonInstance(this);
	}

	@Override
	public String getFullName() {
		return this.fullName;
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public void setFullName(final String fullName) {
		this.fullName = fullName;
		this.flag = true;
	}

	@Override
	public void setUserName(final String userName) {
		this.userName = userName;
		this.flag = true;
	}

	@Override
	public void addEmail(final Email email) {
		System.out.println("addEmail called...person.getName = "
				+ this.getUserName());
		final TSSGEmail em = (email instanceof TSSGEmail) ? (TSSGEmail) email
				: new TSSGEmail(email);
		em.setPerson(this);
		this.emails.add(em);
		this.flag = true;
	}

	@Override
	public void addOrganisation(final Organisation organisation) {
		final TSSGOrganisation org = (organisation instanceof TSSGOrganisation) ? (TSSGOrganisation) organisation
				: new TSSGOrganisation(organisation);
		this.organisations.add(org);
		this.flag = true;
	}

	@Override
	public void addRole(final PersonRole personRole) {
		final TSSGPersonRole r = (personRole instanceof TSSGPersonRole) ? (TSSGPersonRole) personRole
				: new TSSGPersonRole(personRole);
		this.personRoles.add(r);
		this.flag = true;
	}

	@Override
	public List<? extends Email> getEmails() {
		final List<Email> array = new ArrayList<Email>();
		for (final TSSGEmail email : this.emails) {
			final TSSGEmail e = TSSGEmail.find(email.getId());
			array.add(e != null ? e : email);
		}
		return array;
	}

	@Override
	public List<? extends Organisation> getOrganisations() {
		final List<Organisation> array = new ArrayList<Organisation>();
		for (final TSSGOrganisation org : this.organisations) {
			final TSSGOrganisation o = TSSGOrganisation.find(org.getId());
			array.add(o != null ? o : org);
		}
		return array;
	}

	@Override
	public List<? extends PersonRole> getRoles() {
		final List<PersonRole> array = new ArrayList<PersonRole>();
		for (final TSSGPersonRole role : this.personRoles) {
			final TSSGPersonRole r = TSSGPersonRole.find(role.getId());
			array.add(r != null ? r : role);
		}
		return array;
	}

	@Override
	public void removeEmail(final Email email) {
		this.emails.remove(email);
		this.flag = true;
	}

	@Override
	public void removeOrganisation(final Organisation organisation) {
		this.organisations.remove(organisation);
		this.flag = true;
	}

	@Override
	public void removeRole(final PersonRole role) {
		this.personRoles.remove(role);
		this.flag = true;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	@Override
	public void setPassword(final String password) {
		this.password = password;
		this.flag = true;
	}

	/**
	 * @return the password
	 */
	@Override
	public String getPassword() {
		return this.password;
	}

	@XmlRootElement(name = "personInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGPersonInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4880071517781061939L;

		@XmlElementWrapper(name = "emails")
		@XmlElement(name = "email")
		private final List<String> emailIds = new ArrayList<String>();

		@XmlElementWrapper(name = "personRoles")
		@XmlElement(name = "personRole")
		private final List<String> personRoleIds = new ArrayList<String>();

		@XmlElementWrapper(name = "organisations")
		@XmlElement(name = "organisation")
		private final List<String> organisationIds = new ArrayList<String>();

		protected TSSGPersonInstance() {
		}

		protected TSSGPersonInstance(final TSSGPerson instance) {
			for (final TSSGEmail email : instance.emails)
				this.emailIds.add(email.getId());
			for (final TSSGPersonRole role : instance.personRoles)
				this.personRoleIds.add(role.getId());
			for (final TSSGOrganisation org : instance.organisations)
				this.organisationIds.add(org.getId());
		}
	}

	public static void main(final String[] args) {
		// TSSGPerson person =
		// (TSSGPerson)ModelManager.getInstance().findPersonByUserName("testuser");

		TSSGPerson person = new TSSGPerson();
		person.fullName = "repouser";
		person.password = "ad0234829205b9033196ba818f7a872b";// testing for
																// re-hashing a
																// hash in the
																// repos
		person.userName = "repouser10";

		person = person.persist();

		final TSSGEmail email1 = new TSSGEmail();
		email1.setAddress("stefan.harder@fokus.fraunhofer.de");
		final TSSGEmail email2 = new TSSGEmail();
		email2.setAddress("second@example.com");

		person.addEmail(email1);
		person.addEmail(email2);

		final TSSGOrganisation o = (TSSGOrganisation) ModelManager
				.getInstance().getOrganisation("Fraunhofer FOKUS");
		o.addPerson(person);
		o.persist();// ModelManager.getInstance().persist(o);
		person.addOrganisation(o);

		person.addRole(TSSGPersonRole.find("4"));

		// md5("lala") = 2e3817293fc275dbee74bd71ce6eb056
		// person.setPassword("2e3817293fc275dbee74bd71ce6eb056");
		// person.setPassword("test");
		person = person.persist();

		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// TSSGPerson person = find("233");
		// try {
		// person.delete();
		// } catch (RepositoryException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}
