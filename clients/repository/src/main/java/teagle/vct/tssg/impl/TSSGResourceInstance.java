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

import teagle.vct.model.Configuration;
import teagle.vct.model.Geometry;
import teagle.vct.model.RepositoryException;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.ResourceInstanceState;
import teagle.vct.model.ResourceSpec;

@XmlRootElement(name = "resourceInstance")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGResourceInstance extends TSSGEntity implements
		ResourceInstance, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2855022714881990620L;

	protected static TSSGCache<TSSGResourceInstance> cache = new TSSGCache<TSSGResourceInstance>(
			"resourceInstance", new TSSGResourceInstance[] {});

	protected boolean shared;

	protected TSSGResourceSpec resourceSpec = new TSSGResourceSpec();

	@XmlElement(name = "configlet")
	@XmlElementWrapper(name = "configurationData")
	protected List<TSSGConfiguration> configurations = new ArrayList<TSSGConfiguration>();

	protected TSSGGeometry geometry = new TSSGGeometry();

	protected TSSGResourceInstanceState state = new TSSGResourceInstanceState();

	protected TSSGResourceInstance parentInstance = null;

	public TSSGResourceInstance() {
	}

	protected TSSGResourceInstance(final ResourceInstance resourceInstance) {
		super(resourceInstance);
		this.setGeometry(resourceInstance.getGeometry());
		this.setState(resourceInstance.getState());
		this.setResourceSpec(resourceInstance.getResourceSpec());
		this.setParentInstance(resourceInstance.getParentInstance());
		this.shared = resourceInstance.isShared();
		for (final Configuration configuration : resourceInstance
				.getConfigurations())
			this.addConfiguration(configuration);
		this.flag = true;
	}

	public static TSSGResourceInstance find(final String id) {
		return TSSGResourceInstance.cache.find(id);
	}

	public static List<? extends TSSGResourceInstance> list() {
		return TSSGResourceInstance.cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceInstance persist() {
		this.resourceSpec = this.resourceSpec.resolve().persist();
		this.geometry = this.geometry.resolve().persist();
		this.state = this.state.resolve().persist();
		for (final ListIterator<TSSGConfiguration> it = this.configurations
				.listIterator(); it.hasNext();)
			it.set(it.next().resolve().persist());
		return TSSGResourceInstance.cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		TSSGResourceInstance.cache.delete(this);
		// geometry.resolve().delete();
		/*
		 * for (ListIterator<TSSGConfiguration> it =
		 * configurations.listIterator(); it.hasNext(); ) {
		 * it.next().resolve().delete(); }
		 */
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceInstance resolve() {
		return this.id != null ? TSSGResourceInstance.cache.find(this.id)
				: this;
	}

	@Override
	public boolean isModified() {
		boolean modified = super.isModified();
		modified |= this.resourceSpec.resolve().isModified();
		modified |= this.geometry.resolve().isModified();
		modified |= this.state.resolve().isModified();
		for (final TSSGConfiguration configuration : this.configurations)
			modified |= configuration.resolve().isModified();
		return modified;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceInstanceInstance getInstance() {
		return new TSSGResourceInstanceInstance(this);
	}

	@Override
	public Geometry getGeometry() {
		if (this.geometry.getId() != null)
			this.geometry = TSSGGeometry.find(this.geometry.getId());
		return this.geometry;
	}

	@Override
	public ResourceSpec getResourceSpec() {
		if (this.resourceSpec.getId() != null)
			this.resourceSpec = TSSGResourceSpec
					.find(this.resourceSpec.getId());
		return this.resourceSpec;
	}

	@Override
	public void setResourceSpec(final ResourceSpec resourceSpec) {
		this.resourceSpec = (resourceSpec instanceof TSSGResourceSpec) ? (TSSGResourceSpec) resourceSpec
				: new TSSGResourceSpec(resourceSpec);
		this.flag = true;
	}

	@Override
	public ResourceInstance getParentInstance() {
		if (this.parentInstance.getId() != null)
			this.parentInstance = TSSGResourceInstance.find(this.parentInstance
					.getId());
		return this.parentInstance;
	}

	@Override
	public void setParentInstance(final ResourceInstance parentInstance) {
		this.parentInstance = (parentInstance instanceof TSSGResourceInstance) ? (TSSGResourceInstance) parentInstance
				: new TSSGResourceInstance(parentInstance);
		this.flag = true;
	}

	@Override
	public ResourceInstanceState.State getState() {
		if (this.state == null)
			for (final ResourceInstanceState s : TSSGResourceInstanceState
					.list())
				if (s.getCommonName().equals(
						ResourceInstanceState.State.NEW.toString())) {
					this.state = (TSSGResourceInstanceState) s;
					this.flag = true;
				}
		return this.state.getId() != null ? TSSGResourceInstanceState.find(
				this.state.getId()).get() : this.state.get();
	}

	@Override
	public boolean isShared() {
		return this.shared;
	}

	@Override
	public void isShared(final boolean shared) {
		this.shared = shared;
		this.flag = true;
	}

	@Override
	public void addConfiguration(final Configuration configuration) {
		final TSSGConfiguration conf = (configuration instanceof TSSGConfiguration) ? (TSSGConfiguration) configuration
				: new TSSGConfiguration(configuration);
		this.configurations.add(conf);
		this.flag = true;
	}

	@Override
	public void setGeometry(final Geometry geometry) {
		this.geometry = (geometry instanceof TSSGGeometry) ? (TSSGGeometry) geometry
				: new TSSGGeometry(geometry);
		this.flag = true;
	}

	@Override
	public void setState(final ResourceInstanceState.State state) {
		for (final ResourceInstanceState s : TSSGResourceInstanceState.list())
			if (s.getCommonName().equals(
					ResourceInstanceState.State.NEW.toString()))
				this.state = (TSSGResourceInstanceState) s;
		if (this.state == null) {
			this.state = new TSSGResourceInstanceState();
			this.state.set(state);
		}
		this.flag = true;
	}

	@Override
	public List<? extends Configuration> getConfigurations() {
		final List<Configuration> array = new ArrayList<Configuration>();
		for (final TSSGConfiguration config : this.configurations) {
			final TSSGConfiguration c = TSSGConfiguration.find(config.getId());
			array.add(c != null ? c : config);
		}
		return array;
	}

	@Override
	public void removeConfiguration(final Configuration configuration) {
		this.configurations.remove(configuration);
		this.flag = true;
	}

	@XmlRootElement(name = "resourceInstanceInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	static public class TSSGResourceInstanceInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6755173286162349440L;

		@XmlElementWrapper(name = "resourceSpec")
		@XmlElement(name = "resourceSpec")
		private final List<String> resourceSpecId = new ArrayList<String>();

		@XmlElementWrapper(name = "parentInstance")
		@XmlElement(name = "parentInstance")
		private final List<String> parentInstanceId = new ArrayList<String>();

		private final List<ConfigData> configurationData = new ArrayList<ConfigData>();

		@XmlElement(name = "state.id")
		private String stateId;

		protected TSSGResourceInstanceInstance() {
		}

		protected TSSGResourceInstanceInstance(
				final TSSGResourceInstance instance) {
			this.resourceSpecId.add(instance.resourceSpec.getId());
			if (instance.parentInstance != null)
				this.parentInstanceId.add(instance.parentInstance.getId());
			this.stateId = instance.state.getId();
			for (final TSSGConfiguration config : instance.configurations) {
				final ConfigData data = new ConfigData();
				data.configlet.add(config.getId());
				this.configurationData.add(data);
			}
		}

		@XmlType(name = "configurationData")
		@XmlAccessorType(XmlAccessType.FIELD)
		static protected class ConfigData {
			protected List<String> configlet = new ArrayList<String>();
		}
	}

	@Override
	public List<? extends Configuration> getReferences() {
		final List<Configuration> refs = new ArrayList<Configuration>();

		for (final Configuration c : this.getConfigurations())
			if (c.isReference())
				refs.add(c);
		return refs;
	}

}
