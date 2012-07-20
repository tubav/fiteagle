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
@XmlRootElement(name="organisation")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGOrganisation extends TSSGObject implements Organisation, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1461644803668723016L;
	
	protected static TSSGCache<TSSGOrganisation> cache = new TSSGCache<TSSGOrganisation>("organisation", new TSSGOrganisation[]{});
	
	private String name = "";

	@XmlElement(name="resourceSpec")
	@XmlElementWrapper(name="hasResources")
	private List<TSSGResourceSpec> resourceSpecs = new ArrayList<TSSGResourceSpec>();

	@XmlElement(name="role")
	@XmlElementWrapper(name="hasOrgRoles")//added by sha
	private List<TSSGOrganisationRole> roles = new ArrayList<TSSGOrganisationRole>();
	
	@XmlElement(name="person")
	@XmlElementWrapper(name="people")//added by sha
	private List<TSSGPerson> persons = new ArrayList<TSSGPerson>();

	public TSSGOrganisation() {		
	}
	
	protected TSSGOrganisation(Organisation org) {
		name = org.getName();
		for (Person person : org.getPersons()) {
			addPerson(person);
		}
		for (OrganisationRole role : org.getRoles()) {
			addRole(role);
		}
		for (ResourceSpec spec : org.getResourceSpecs()) {
			addResourceSpec(spec);
		}
		flag = true;
	}
	
	public static TSSGOrganisation find(String id) {
		return cache.find(id);
	}

	public static List<? extends Organisation> list() {
		return cache.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGOrganisation persist() {
		for (ListIterator<TSSGPerson> it = persons.listIterator(); it.hasNext(); ) {
			it.set(it.next().resolve().persist());
		}
		for (ListIterator<TSSGOrganisationRole> it = roles.listIterator(); it.hasNext(); ) {
			it.set(it.next().resolve().persist());
		}
		for (ListIterator<TSSGResourceSpec> it = resourceSpecs.listIterator(); it.hasNext(); ) {
			it.set(it.next().resolve().persist());
		}
		return cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		for (ListIterator<TSSGPerson> it = persons.listIterator(); it.hasNext(); ) {
			it.next().resolve().delete();
		}
		for (ListIterator<TSSGOrganisationRole> it = roles.listIterator(); it.hasNext(); ) {
			it.next().resolve().delete();
		}
		for (ListIterator<TSSGResourceSpec> it = resourceSpecs.listIterator(); it.hasNext(); ) {
			it.next().resolve().delete();
		}
		cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGOrganisation resolve() {
		return id != null ? cache.find(id) : this;
	}
	
	@Override
	public boolean isModified() {
		boolean modified = super.isModified();
		for (TSSGOrganisationRole role : roles) {
			modified |= role.resolve().isModified();
		}
		for (TSSGPerson person : persons) {
			modified |= person.resolve().isModified();
		}
		for (TSSGResourceSpec spec : resourceSpecs) {
			modified |= spec.resolve().isModified();
		}
		return modified; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGOrganisationInstance getInstance() {
		return new TSSGOrganisationInstance(this);
	}	

	@Override
	public void addPerson(Person person) {
		TSSGPerson p = (person instanceof TSSGPerson) ? (TSSGPerson)person : new TSSGPerson(person);
		p.addOrganisation(this);		
		persons.add(p);
		flag = true;
	}

	@Override
	public void addResourceSpec(ResourceSpec resourceSpec) {
		TSSGResourceSpec spec = (resourceSpec instanceof TSSGResourceSpec) ? (TSSGResourceSpec)resourceSpec : new TSSGResourceSpec(resourceSpec);
		resourceSpecs.add(spec);
		flag = true;
	}

	@Override
	public void addRole(OrganisationRole role) {
		TSSGOrganisationRole r = (role instanceof TSSGOrganisationRole) ? (TSSGOrganisationRole)role : new TSSGOrganisationRole(role);
		roles.add(r);
		flag = true;
	}

	@Override
	public String getName() {
		if(name!=null){
			return name.trim();
		}
		else{
			return name;			
		}
	}

	@Override
	public List<? extends Person> getPersons() {
		List<Person> array = new ArrayList<Person>();
		for (TSSGPerson person : persons) {
			TSSGPerson p = (TSSGPerson)TSSGPerson.find(person.getId());
			array.add(p != null ? p : person);
		}
		return array;
	}

	@Override
	public List<? extends ResourceSpec> getResourceSpecs() {
		List<ResourceSpec> array = new ArrayList<ResourceSpec>();
		for (TSSGResourceSpec spec : resourceSpecs) {
			TSSGResourceSpec s = (TSSGResourceSpec)TSSGResourceSpec.find(spec.getId());
			array.add(s != null ? s : spec);
		}
		return array;
	}

	@Override
	public List<? extends OrganisationRole> getRoles() {
		List<OrganisationRole> array = new ArrayList<OrganisationRole>();
		for (TSSGOrganisationRole role : roles) {
			TSSGOrganisationRole r = (TSSGOrganisationRole)TSSGOrganisationRole.find(role.getId());
			array.add(r != null ? r : role);
		}
		return array;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		flag = true;
	}

	@Override
	public void removePerson(Person person) {
		persons.remove(person);
		flag = true;
	}

	@Override
	public void removeResourceSpec(ResourceSpec resourceSpec) {
		resourceSpecs.remove(resourceSpec);
		flag = true;
	}

	@Override
	public void removeRole(OrganisationRole role) {
		roles.remove(role);
		flag = true;
	}

	@XmlRootElement(name="organisationInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	static public class TSSGOrganisationInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3228694694573119635L;

		private String name;

		private List<PersonData> people = new ArrayList<PersonData>();
		
		private List<ResourceSpecData> hasResources = new ArrayList<ResourceSpecData>();

		@XmlElementWrapper(name="hasOrgRoles")
		@XmlElement(name="role")
		private List<String> roleIds = new ArrayList<String>();
		
		protected TSSGOrganisationInstance() {
		}
		
		protected TSSGOrganisationInstance(TSSGOrganisation instance) {
			name = instance.name;
			for (TSSGPerson person : instance.persons) {
				PersonData data = new PersonData();
				data.person.add(person.getId());
				people.add(data);
			}
			for (TSSGResourceSpec resourceSpec : instance.resourceSpecs) {
				ResourceSpecData data = new ResourceSpecData();
				data.resourceSpec.add(resourceSpec.getId());
				hasResources.add(data);
			}
			for (TSSGOrganisationRole role : instance.roles) {
				roleIds.add(role.getId());
			}
		}

		@XmlType(name="people")
		@XmlAccessorType(XmlAccessType.FIELD)
		static protected class PersonData {
			protected List<String> person = new ArrayList<String>();
		}

		@XmlType(name="hasResources")
		@XmlAccessorType(XmlAccessType.FIELD)
		static protected class ResourceSpecData {
			protected List<String> resourceSpec = new ArrayList<String>();
		}
	}

	public static void main(String[] args) throws RepositoryException {
		TSSGOrganisation org = new TSSGOrganisation();
		org.name = "testOrganisation5";

		TSSGResourceSpec spec1 = new TSSGResourceSpec();
		spec1.commonName = "newResourceSpec7";
		spec1.description = "whatever";

		
		List<TSSGConfigParamAtomic> params = new ArrayList<TSSGConfigParamAtomic>();
		TSSGConfigParamAtomic param = new TSSGConfigParamAtomic();
		param.commonName = "newTestAtomic1";
		param.description = "what ever description";
		param.setType("string");
		param.setDefaultValue("this is the default");
		params.add(param);
		
		spec1.setConfigurationParameters(params);
		
		TSSGResourceSpec spec2 = new TSSGResourceSpec();
		spec2.commonName = "newResourceSpec8";
		spec2.description = "whatever";
		

		List<TSSGConfigParamAtomic> params2 = new ArrayList<TSSGConfigParamAtomic>();
		TSSGConfigParamAtomic param2 = new TSSGConfigParamAtomic();
		param2.commonName = "newTestAtomic1";
		param2.description = "what ever description";
		param2.setType("string");
		param2.setDefaultValue("this is the default");
		params2.add(param2);
		
		spec2.setConfigurationParameters(params2);

		org.addResourceSpec(spec1);
		org.addResourceSpec(spec2);

		org = org.persist();
		
		TSSGPerson person1 = new TSSGPerson();
		person1.setUserName("orgTestUser1");
		person1.setFullName("Full Name");
		person1.setPassword("password");
		
		org.addPerson(person1);
		
		org = org.persist();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		org.delete();
	}
}
