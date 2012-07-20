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

@XmlRootElement(name="resourceInstance")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGResourceInstance extends TSSGEntity implements ResourceInstance, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2855022714881990620L;

	protected static TSSGCache<TSSGResourceInstance> cache = new TSSGCache<TSSGResourceInstance>("resourceInstance", new TSSGResourceInstance[]{});
	
	protected boolean shared;

	protected TSSGResourceSpec resourceSpec = new TSSGResourceSpec();
	
	@XmlElement(name="configlet")
	@XmlElementWrapper(name="configurationData")
	protected List<TSSGConfiguration> configurations = new ArrayList<TSSGConfiguration>();

	protected TSSGGeometry geometry = new TSSGGeometry();
	
	protected TSSGResourceInstanceState state = new TSSGResourceInstanceState();
	
	protected TSSGResourceInstance parentInstance = null;
	
	public TSSGResourceInstance() {
	}

	protected TSSGResourceInstance(ResourceInstance resourceInstance) {
		super(resourceInstance);
		setGeometry(resourceInstance.getGeometry());
		setState(resourceInstance.getState());
		setResourceSpec(resourceInstance.getResourceSpec());
		setParentInstance(resourceInstance.getParentInstance());
		shared = resourceInstance.isShared();
		for (Configuration configuration : resourceInstance.getConfigurations()) {
			addConfiguration(configuration);
		}
		flag = true;
	}
	
	public static TSSGResourceInstance find(String id) {
		return cache.find(id);
	}

	public static List<? extends TSSGResourceInstance> list() {
		return cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceInstance persist() {
		resourceSpec = resourceSpec.resolve().persist();
		geometry = geometry.resolve().persist();
		state = state.resolve().persist();
		for (ListIterator<TSSGConfiguration> it = configurations.listIterator(); it.hasNext(); ) {
			it.set(it.next().resolve().persist());
		}
		return cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		cache.delete(this);
		//geometry.resolve().delete();
		/*for (ListIterator<TSSGConfiguration> it = configurations.listIterator(); it.hasNext(); ) {
			it.next().resolve().delete();
		}*/
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceInstance resolve() {
		return id != null ? cache.find(id) : this;
	}
	
	@Override
	public boolean isModified() {
		boolean modified = super.isModified();
		modified |= resourceSpec.resolve().isModified();
		modified |= geometry.resolve().isModified();
		modified |= state.resolve().isModified();
		for (TSSGConfiguration configuration : configurations) {
			modified |= configuration.resolve().isModified();
		}
		return modified;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceInstanceInstance getInstance() {
		return new TSSGResourceInstanceInstance(this);
	}	

	@Override
	public Geometry getGeometry() {
		if (geometry.getId() != null) {
			geometry = TSSGGeometry.find(geometry.getId());
		}
		return geometry;
	}

	@Override
	public ResourceSpec getResourceSpec() {
		if (resourceSpec.getId() != null) {
			resourceSpec = TSSGResourceSpec.find(resourceSpec.getId());
		}
		return resourceSpec;
	}

	@Override
	public void setResourceSpec(ResourceSpec resourceSpec) {
		this.resourceSpec = (resourceSpec instanceof TSSGResourceSpec) ? (TSSGResourceSpec)resourceSpec : new TSSGResourceSpec(resourceSpec);			
		flag = true;
	}
	
	@Override
	public ResourceInstance getParentInstance() {
		if (parentInstance.getId() != null) {
			parentInstance = TSSGResourceInstance.find(parentInstance.getId());
		}
		return parentInstance;
	}

	@Override
	public void setParentInstance(ResourceInstance parentInstance) {
		this.parentInstance = (parentInstance instanceof TSSGResourceInstance) ? (TSSGResourceInstance)parentInstance : new TSSGResourceInstance(parentInstance);			
		flag = true;
	}
	
	@Override
	public ResourceInstanceState.State getState() {
		if (state == null) {
			for (ResourceInstanceState s : TSSGResourceInstanceState.list()) {
				if (s.getCommonName().equals(ResourceInstanceState.State.NEW.toString())) {
					state = (TSSGResourceInstanceState)s;
					flag = true;
				}
			}
		}
		return state.getId() != null ? TSSGResourceInstanceState.find(state.getId()).get() : state.get();
	}

	@Override
	public boolean isShared() {
		return shared;
	}
	
	@Override
	public void isShared(boolean shared)
	{
		this.shared = shared;
		flag = true;
	}

	@Override
	public void addConfiguration(Configuration configuration) {
		TSSGConfiguration conf = (configuration instanceof TSSGConfiguration) ? (TSSGConfiguration)configuration : new TSSGConfiguration(configuration);
		configurations.add(conf);
		flag = true;
	}

	@Override
	public void setGeometry(Geometry geometry) {
		this.geometry = (geometry instanceof TSSGGeometry) ? (TSSGGeometry)geometry : new TSSGGeometry(geometry);
		flag = true;
	}

	@Override
	public void setState(ResourceInstanceState.State state) {
		for (ResourceInstanceState s : TSSGResourceInstanceState.list()) {
			if (s.getCommonName().equals(ResourceInstanceState.State.NEW.toString())) {
				this.state = (TSSGResourceInstanceState)s;
			}				
		}
		if (this.state == null) {
			this.state = new TSSGResourceInstanceState();
			this.state.set(state);
		}
		flag = true;
	}

	@Override
	public List<? extends Configuration> getConfigurations() {
		List<Configuration> array = new ArrayList<Configuration>();
		for (TSSGConfiguration config : configurations) {
			TSSGConfiguration c = (TSSGConfiguration)TSSGConfiguration.find(config.getId());
			array.add(c != null ? c : config);
		}
		return array;
	}

	@Override
	public void removeConfiguration(Configuration configuration) {
		configurations.remove(configuration);
		flag = true;
	}

	@XmlRootElement(name="resourceInstanceInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	static public class TSSGResourceInstanceInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6755173286162349440L;

		private String commonName;

		private String description;

		@XmlElementWrapper(name="resourceSpec")
		@XmlElement(name="resourceSpec")
		private List<String> resourceSpecId = new ArrayList<String>();
		
		@XmlElementWrapper(name="parentInstance")
		@XmlElement(name="parentInstance")
		private List<String> parentInstanceId = new ArrayList<String>();
		
		private boolean shared;
		
		private List<ConfigData> configurationData = new ArrayList<ConfigData>();
		
		@XmlElement(name="state.id")
		private String stateId;
		

		
		protected TSSGResourceInstanceInstance() {
		}
		
		protected TSSGResourceInstanceInstance(TSSGResourceInstance instance) {
			commonName = instance.commonName;
			description = instance.description;
			shared = instance.shared;
			resourceSpecId.add(instance.resourceSpec.getId());
			if (instance.parentInstance != null)
				parentInstanceId.add(instance.parentInstance.getId());
			stateId = instance.state.getId();
			for (TSSGConfiguration config : instance.configurations) {
				ConfigData data = new ConfigData();
				data.configlet.add(config.getId());
				configurationData.add(data);
			}
		}
		
		@XmlType(name="configurationData")
		@XmlAccessorType(XmlAccessType.FIELD)
		static protected class ConfigData {
			protected List<String> configlet = new ArrayList<String>();
		}
	}

	@Override
	public List<? extends Configuration> getReferences() {
		List<Configuration> refs = new ArrayList<Configuration>();
		
		for (Configuration c : getConfigurations()) {
			if (c.isReference()) {
				refs.add(c);				
			}			
		}
		return refs;
	}

}
