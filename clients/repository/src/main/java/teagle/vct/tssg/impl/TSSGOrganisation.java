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
import javax.xml.bind.annotation.XmlType;

import teagle.vct.model.Organisation;
import teagle.vct.model.OrganisationRole;
import teagle.vct.model.Person;
import teagle.vct.model.RepositoryException;
import teagle.vct.model.ResourceSpec;

/**
 * @author sim
 * 
 */
@XmlRootElement(name = "organisation")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGOrganisation extends TSSGObject implements Organisation,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1461644803668723016L;

	protected static TSSGCache<TSSGOrganisation> cache = new TSSGCache<TSSGOrganisation>(
			"organisation", new TSSGOrganisation[] {});

	private String name = "";

	@XmlElement(name = "resourceSpec")
	@XmlElementWrapper(name = "hasResources")
	private final List<TSSGResourceSpec> resourceSpecs = new ArrayList<TSSGResourceSpec>();

	@XmlElement(name = "role")
	@XmlElementWrapper(name = "hasOrgRoles")
	// added by sha
	private final List<TSSGOrganisationRole> roles = new ArrayList<TSSGOrganisationRole>();

	@XmlElement(name = "person")
	@XmlElementWrapper(name = "people")
	// added by sha
	private final List<TSSGPerson> persons = new ArrayList<TSSGPerson>();

	public TSSGOrganisation() {
	}

	protected TSSGOrganisation(final Organisation org) {
		this.name = org.getName();
		for (final Person person : org.getPersons())
			this.addPerson(person);
		for (final OrganisationRole role : org.getRoles())
			this.addRole(role);
		for (final ResourceSpec spec : org.getResourceSpecs())
			this.addResourceSpec(spec);
		this.flag = true;
	}

	public static TSSGOrganisation find(final String id) {
		return TSSGOrganisation.cache.find(id);
	}

	public static List<? extends Organisation> list() {
		return TSSGOrganisation.cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGOrganisation persist() {
		for (final ListIterator<TSSGPerson> it = this.persons.listIterator(); it
				.hasNext();)
			it.set(it.next().resolve().persist());
		for (final ListIterator<TSSGOrganisationRole> it = this.roles
				.listIterator(); it.hasNext();)
			it.set(it.next().resolve().persist());
		for (final ListIterator<TSSGResourceSpec> it = this.resourceSpecs
				.listIterator(); it.hasNext();)
			it.set(it.next().resolve().persist());
		return TSSGOrganisation.cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		for (final ListIterator<TSSGPerson> it = this.persons.listIterator(); it
				.hasNext();)
			it.next().resolve().delete();
		for (final ListIterator<TSSGOrganisationRole> it = this.roles
				.listIterator(); it.hasNext();)
			it.next().resolve().delete();
		for (final ListIterator<TSSGResourceSpec> it = this.resourceSpecs
				.listIterator(); it.hasNext();)
			it.next().resolve().delete();
		TSSGOrganisation.cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGOrganisation resolve() {
		return this.id != null ? TSSGOrganisation.cache.find(this.id) : this;
	}

	@Override
	public boolean isModified() {
		boolean modified = super.isModified();
		for (final TSSGOrganisationRole role : this.roles)
			modified |= role.resolve().isModified();
		for (final TSSGPerson person : this.persons)
			modified |= person.resolve().isModified();
		for (final TSSGResourceSpec spec : this.resourceSpecs)
			modified |= spec.resolve().isModified();
		return modified;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGOrganisationInstance getInstance() {
		return new TSSGOrganisationInstance(this);
	}

	@Override
	public void addPerson(final Person person) {
		final TSSGPerson p = (person instanceof TSSGPerson) ? (TSSGPerson) person
				: new TSSGPerson(person);
		p.addOrganisation(this);
		this.persons.add(p);
		this.flag = true;
	}

	@Override
	public void addResourceSpec(final ResourceSpec resourceSpec) {
		final TSSGResourceSpec spec = (resourceSpec instanceof TSSGResourceSpec) ? (TSSGResourceSpec) resourceSpec
				: new TSSGResourceSpec(resourceSpec);
		this.resourceSpecs.add(spec);
		this.flag = true;
	}

	@Override
	public void addRole(final OrganisationRole role) {
		final TSSGOrganisationRole r = (role instanceof TSSGOrganisationRole) ? (TSSGOrganisationRole) role
				: new TSSGOrganisationRole(role);
		this.roles.add(r);
		this.flag = true;
	}

	@Override
	public String getName() {
		if (this.name != null)
			return this.name.trim();
		else
			return this.name;
	}

	@Override
	public List<? extends Person> getPersons() {
		final List<Person> array = new ArrayList<Person>();
		for (final TSSGPerson person : this.persons) {
			final TSSGPerson p = TSSGPerson.find(person.getId());
			array.add(p != null ? p : person);
		}
		return array;
	}

	@Override
	public List<? extends ResourceSpec> getResourceSpecs() {
		final List<ResourceSpec> array = new ArrayList<ResourceSpec>();
		for (final TSSGResourceSpec spec : this.resourceSpecs) {
			final TSSGResourceSpec s = TSSGResourceSpec.find(spec.getId());
			array.add(s != null ? s : spec);
		}
		return array;
	}

	@Override
	public List<? extends OrganisationRole> getRoles() {
		final List<OrganisationRole> array = new ArrayList<OrganisationRole>();
		for (final TSSGOrganisationRole role : this.roles) {
			final TSSGOrganisationRole r = TSSGOrganisationRole.find(role
					.getId());
			array.add(r != null ? r : role);
		}
		return array;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
		this.flag = true;
	}

	@Override
	public void removePerson(final Person person) {
		this.persons.remove(person);
		this.flag = true;
	}

	@Override
	public void removeResourceSpec(final ResourceSpec resourceSpec) {
		this.resourceSpecs.remove(resourceSpec);
		this.flag = true;
	}

	@Override
	public void removeRole(final OrganisationRole role) {
		this.roles.remove(role);
		this.flag = true;
	}

	@XmlRootElement(name = "organisationInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	static public class TSSGOrganisationInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3228694694573119635L;

		private final List<PersonData> people = new ArrayList<PersonData>();

		private final List<ResourceSpecData> hasResources = new ArrayList<ResourceSpecData>();

		@XmlElementWrapper(name = "hasOrgRoles")
		@XmlElement(name = "role")
		private final List<String> roleIds = new ArrayList<String>();

		protected TSSGOrganisationInstance() {
		}

		protected TSSGOrganisationInstance(final TSSGOrganisation instance) {
			for (final TSSGPerson person : instance.persons) {
				final PersonData data = new PersonData();
				data.person.add(person.getId());
				this.people.add(data);
			}
			for (final TSSGResourceSpec resourceSpec : instance.resourceSpecs) {
				final ResourceSpecData data = new ResourceSpecData();
				data.resourceSpec.add(resourceSpec.getId());
				this.hasResources.add(data);
			}
			for (final TSSGOrganisationRole role : instance.roles)
				this.roleIds.add(role.getId());
		}

		@XmlType(name = "people")
		@XmlAccessorType(XmlAccessType.FIELD)
		static protected class PersonData {
			protected List<String> person = new ArrayList<String>();
		}

		@XmlType(name = "hasResources")
		@XmlAccessorType(XmlAccessType.FIELD)
		static protected class ResourceSpecData {
			protected List<String> resourceSpec = new ArrayList<String>();
		}
	}

	public static void main(final String[] args) throws RepositoryException {
		TSSGOrganisation org = new TSSGOrganisation();
		org.name = "testOrganisation5";

		final TSSGResourceSpec spec1 = new TSSGResourceSpec();
		spec1.commonName = "newResourceSpec7";
		spec1.description = "whatever";

		final List<TSSGConfigParamAtomic> params = new ArrayList<TSSGConfigParamAtomic>();
		final TSSGConfigParamAtomic param = new TSSGConfigParamAtomic();
		param.commonName = "newTestAtomic1";
		param.description = "what ever description";
		param.setType("string");
		param.setDefaultValue("this is the default");
		params.add(param);

		spec1.setConfigurationParameters(params);

		final TSSGResourceSpec spec2 = new TSSGResourceSpec();
		spec2.commonName = "newResourceSpec8";
		spec2.description = "whatever";

		final List<TSSGConfigParamAtomic> params2 = new ArrayList<TSSGConfigParamAtomic>();
		final TSSGConfigParamAtomic param2 = new TSSGConfigParamAtomic();
		param2.commonName = "newTestAtomic1";
		param2.description = "what ever description";
		param2.setType("string");
		param2.setDefaultValue("this is the default");
		params2.add(param2);

		spec2.setConfigurationParameters(params2);

		org.addResourceSpec(spec1);
		org.addResourceSpec(spec2);

		org = org.persist();

		final TSSGPerson person1 = new TSSGPerson();
		person1.setUserName("orgTestUser1");
		person1.setFullName("Full Name");
		person1.setPassword("password");

		org.addPerson(person1);

		org = org.persist();
		try {
			Thread.sleep(5000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		org.delete();
	}
}
