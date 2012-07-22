/**
 * 
 */
package teagle.vct.tssg.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import teagle.vct.model.ConfigParamAtomic;
import teagle.vct.model.ConfigParamComposite;
import teagle.vct.model.Configuration;
import teagle.vct.model.Email;
import teagle.vct.model.Entity;
import teagle.vct.model.EntityNotFound;
import teagle.vct.model.Geometry;
import teagle.vct.model.ModelManager;
import teagle.vct.model.Organisation;
import teagle.vct.model.Person;
import teagle.vct.model.PersonRole;
import teagle.vct.model.Ptm;
import teagle.vct.model.RepoClientConfig;
import teagle.vct.model.RepositoryException;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.ResourceInstanceState;
import teagle.vct.model.ResourceInstanceState.State;
import teagle.vct.model.ResourceSpec;
import teagle.vct.model.Vct;

/**
 * @author sim
 * 
 */
public final class TSSGModelFactory extends ModelManager {

	private boolean autoClearCache = false;
	private long autoClearCacheInterval = 1800000;

	private boolean prefetching = true;

	public void clearCache(final boolean prefetching) {
		TSSGConfigParamAtomic.cache.clear();
		TSSGConfigParamComposite.cache.clear();
		TSSGConfiguration.cache.clear();
		TSSGCost.cache.clear();
		TSSGEmail.cache.clear();
		TSSGGeometry.cache.clear();
		TSSGOrganisation.cache.clear();
		TSSGOrganisationRole.cache.clear();
		TSSGPerson.cache.clear();
		// TSSGPersonRole.cache.clear();
		TSSGConfigParamComposite.cache.clear();
		TSSGPtm.cache.clear();
		TSSGResourceInstance.cache.clear();
		TSSGResourceInstanceState.cache.clear();
		TSSGResourceSpec.cache.clear();
		TSSGVct.cache.clear();
		TSSGVctState.cache.clear();

		if (prefetching)
			this.prefetchData();
	}

	// TODO: proper config class
	@Override
	public void config(final RepoClientConfig config) {
		TSSGClient.config(config.getUrl(), config.getUsername(),
				config.getPassword());

		this.autoClearCache = config.getDoAutoclear();
		this.autoClearCacheInterval = config.getAutoClearInterval();

		this.prefetching = config.getDoPrefetching();

		if (this.autoClearCache) {
			final Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					TSSGModelFactory.this
							.clearCache(TSSGModelFactory.this.prefetching);
				}
			}, this.autoClearCacheInterval, this.autoClearCacheInterval);
		}

		if (this.prefetching) {
			this.prefetchData();
		}
	}

	private void prefetchData() {

		final Thread task10 = new Thread(new Runnable() {
			@Override
			public void run() {
				TSSGConfiguration.list();
			}
		});
		task10.start();

		final Thread task2 = new Thread(new Runnable() {
			@Override
			public void run() {
				TSSGPerson.list();
			}
		});
		task2.start();

		final Thread task5 = new Thread(new Runnable() {
			@Override
			public void run() {
				TSSGResourceInstance.list();
			}
		});
		task5.start();

		final Thread task4 = new Thread(new Runnable() {
			@Override
			public void run() {
				TSSGResourceSpec.list();
			}
		});
		task4.start();

		final Thread task1 = new Thread(new Runnable() {
			@Override
			public void run() {
				TSSGPtm.list();
			}
		});
		task1.start();

		final Thread task3 = new Thread(new Runnable() {
			@Override
			public void run() {
				TSSGVct.list();
			}
		});
		task3.start();

		final Thread task0 = new Thread(new Runnable() {
			@Override
			public void run() {
				TSSGOrganisation.list();
			}
		});
		task0.start();

		final Thread task6 = new Thread(new Runnable() {
			@Override
			public void run() {
				TSSGConfigParamComposite.list();
			}
		});
		task6.start();

		final Thread task7 = new Thread(new Runnable() {
			@Override
			public void run() {
				TSSGConfigParamAtomic.list();
			}
		});
		task7.start();

		final Thread task8 = new Thread(new Runnable() {
			@Override
			public void run() {
				TSSGGeometry.list();
			}
		});
		task8.start();

	}

	@Override
	public Vct findVct(final String userName, final String commonName) {
		for (final Vct vct : TSSGVct.list())
			if (vct.getPerson().getUserName().equals(userName)
					&& vct.getCommonName().equals(commonName))
				return vct;
		return null;
	}

	@Override
	public List<Vct> findVctsByUserName(final String userName) {
		final List<Vct> vcts = new ArrayList<Vct>();

		for (final Vct vct : TSSGVct.list())
			if (vct.getPerson().getUserName().equals(userName))
				vcts.add(vct);
		return vcts;
	}

	@Override
	public Person findPersonByUserName(final String userName) {
		for (final Person person : TSSGPerson.list())
			if (person.getUserName().equals(userName))
				return person;
		return null;
	}

	@Override
	public Collection<ResourceInstance> findResourceInstancesByUserName(
			final String userName) {
		final Set<ResourceInstance> instances = new HashSet<ResourceInstance>();

		for (final Vct vct : this.findVctsByUserName(userName))
			instances.addAll(vct.getResourceInstances());

		instances.addAll(this.findUnusedResourceInstances());

		return instances;
	}

	@Override
	public List<ResourceInstance> findUnusedResourceInstances() {
		final Set<String> usedInstances = new HashSet<String>();
		final List<ResourceInstance> unused = new ArrayList<ResourceInstance>();

		for (final TSSGVct vct : TSSGVct.list())
			for (final TSSGResourceInstance ri : vct.getResourceInstances())
				if (ri.getState() == State.PROVISIONED)
					usedInstances.add(ri.getId());

		for (final TSSGResourceInstance ri : TSSGResourceInstance.list())
			if ((ri.getState() == State.PROVISIONED)
					&& !usedInstances.contains(ri.getId()))
				unused.add(ri);

		return unused;
	}

	@Override
	public ResourceSpec createResource() {
		final TSSGResourceSpec spec = new TSSGResourceSpec();
		spec.flag = true;
		return spec;
	}

	@Override
	public ConfigParamAtomic createConfigParamAtomic() {
		final TSSGConfigParamAtomic atomic = new TSSGConfigParamAtomic();
		atomic.flag = true;
		return atomic;
	}

	@Override
	public ResourceInstance createResourceInstance(
			final ResourceSpec resourceSpec) {
		final TSSGResourceInstance instance = new TSSGResourceInstance();
		instance.setResourceSpec(resourceSpec);
		final List<? extends ConfigParamAtomic> configs = resourceSpec
				.getConfigurationParameters();
		for (final ConfigParamAtomic param : configs)
			instance.addConfiguration(this.createConfiguration(param));

		instance.setState(ResourceInstanceState.State.NEW);
		instance.flag = true;
		return instance;
	}

	@Override
	public Vct createVct() {
		return new TSSGVct();
	}

	@Override
	public List<? extends ResourceSpec> listResourceSpecs() {
		return TSSGResourceSpec.list();
	}

	@Override
	public List<? extends Organisation> listOrganisations() {
		return TSSGOrganisation.list();
	}

	@Override
	public List<? extends Ptm> listPtms() {
		return TSSGPtm.list();
	}

	@Override
	public ConfigParamComposite createConfigParamComposite() {
		final TSSGConfigParamComposite composite = new TSSGConfigParamComposite();
		composite.flag = true;
		return composite;
	}

	@Override
	public Person createPerson() {
		final TSSGPerson person = new TSSGPerson();
		person.flag = true;
		return person;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T persist(T obj) {
		if (obj instanceof TSSGObject)
			obj = (T) ((TSSGObject) obj).persist();
		return obj;
	}

	@Override
	public <T extends Entity> void delete(final T obj)
			throws RepositoryException {
		if (obj instanceof TSSGObject)
			((TSSGObject) obj).delete();
	}

	@Override
	public <T extends Entity> boolean isModified(final T obj) {
		return obj instanceof TSSGObject ? ((TSSGObject) obj).isModified()
				: true;
	}

	@Override
	public Configuration createConfiguration(final ConfigParamAtomic param) {
		final TSSGConfiguration config = new TSSGConfiguration();
		config.setCommonName(param.getCommonName());
		config.setDescription(param.getDescription());
		config.setValue(param.getDefaultValue());
		config.setConfigParamatomic(param);
		config.flag = true;
		return config;
	}

	@Override
	public Organisation getOrganisation(final String organisationName) {
		for (final Organisation o : TSSGOrganisation.list())
			if (o.getName().trim().equals(organisationName))
				return o;
		return null;
	}

	@Override
	public ResourceSpec getResourceSpec(final String resourceName) {
		for (final ResourceSpec r : TSSGResourceSpec.list())
			if (r.getCommonName().equals(resourceName))
				return r;
		return null;
	}

	@Override
	public List<Organisation> findOrganisationsByUserName(final String userName) {
		final List<Organisation> organisations = new ArrayList<Organisation>();
		new ArrayList<Person>();
		for (final Organisation o : TSSGOrganisation.list())
			for (final Person p : o.getPersons())
				if (p.getUserName().equals(userName))
					organisations.add(o);
		return organisations;
	}

	@Override
	public ResourceInstance findResourceInstanceByName(final String name)
			throws RepositoryException {
		for (final ResourceInstance ri : TSSGResourceInstance.list())
			if (ri.getCommonName().equals(name))
				return ri;

		throw new EntityNotFound(name);
	}

	@Override
	public List<Ptm> listPtmsByOrganisation(final String organisationName) {
		final List<Ptm> ptms = new ArrayList<Ptm>();
		for (final Ptm ptm : TSSGPtm.list())
			if (ptm.getOrganisation().getName().equals(organisationName))
				ptms.add(ptm);
		return ptms;
	}

	@Override
	public Ptm getPtm(final String ptmName) {
		for (final Ptm ptm : TSSGPtm.list())
			if (ptm.getCommonName().equals(ptmName))
				return ptm;
		return null;
	}

	@Override
	public Ptm createPtm() {
		final TSSGPtm ptm = new TSSGPtm();
		ptm.flag = true;
		return ptm;
	}

	@Override
	public List<ResourceSpec> getResourcesNotSupportedByPtm(final Ptm ptm) {
		final List<? extends ResourceSpec> supportedResources = ptm
				.getResourceSpecs();
		final List<ResourceSpec> result = new ArrayList<ResourceSpec>();
		for (final ResourceSpec r : TSSGResourceSpec.list())
			if (!supportedResources.contains(r))
				result.add(r);
		return result;
	}

	@Override
	public List<? extends Person> listPersons() {
		return TSSGPerson.list();
	}

	@Override
	public Organisation createOrganisation() {
		final TSSGOrganisation organisation = new TSSGOrganisation();
		organisation.flag = true;
		return organisation;
	}

	@Override
	public Email createEmail() {
		final TSSGEmail email = new TSSGEmail();
		email.flag = true;
		return email;
	}

	@Override
	public PersonRole getPartnerRole() {
		return TSSGPersonRole.find("4");
	}

	@Override
	public PersonRole getCustomerRole() {
		return TSSGPersonRole.find("1");
	}

	@Override
	public boolean vctExists(final String vctName, final String userName) {
		for (final Vct vct : TSSGVct.list())
			if (vct.getCommonName().equals(vctName)
					&& vct.getPerson().getUserName().equals(userName))
				return true;

		return false;
	}

	@Override
	public Geometry createGeometry(final int x, final int y) {
		return new TSSGGeometry(x, y);
	}

	@SuppressWarnings("unchecked")
	public <T extends TSSGObject> T getObject(final Class<T> cls,
			final String id) throws RepositoryException {
		T t = null;
		try {
			t = (T) cls.getMethod("find", String.class).invoke(null, id);
		} catch (final Exception e) {
			throw new RepositoryException(e);
		}

		if (t == null)
			throw new RepositoryException("Object of type "
					+ cls.getSimpleName() + " with id " + id + "not found.");

		return t;
	}

	// @Override
	// public List<? extends Person> findPersonsByOrganisationName(String
	// organisationName) {
	// List<? extends Person> persons = new ArrayList<Person>();
	// for(Organisation o : TSSGOrganisation.list()){
	// if(o.getName().equals(organisationName)){
	// persons = o.getPersons();
	// }
	// }
	// return persons;
	// }

	// @Override
	// public List<ResourceSpec> findResourceSpecsByOrganisation(String
	// organisationName) {
	// List<ResourceSpec> resources = new ArrayList<ResourceSpec>();
	// for(ResourceSpec r : TSSGResourceSpec.list()){
	// if(r.getProvider()!= null &&
	// r.getProvider().trim().equals(organisationName)){
	// resources.add(r);
	// }
	// }
	// return resources;
	// }

	// @Override
	// public ResourceInstance getResourceInstance(String commonName) {
	// for (ResourceInstance ri : TSSGResourceInstance.list()) {
	// if (ri.getCommonName().equals(commonName)) {
	// return ri;
	// }
	// }
	// return null;
	// }

}
