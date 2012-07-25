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
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import teagle.vct.model.ConfigParamAtomic;
import teagle.vct.model.ConfigParamComposite;
import teagle.vct.model.ConstraintViolation;
import teagle.vct.model.RepositoryException;
import teagle.vct.tssg.impl.TSSGOrganisation.TSSGOrganisationInstance.PersonData;

/**
 * @author sim
 *
 */
@XmlRootElement(name="configParamComposite")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGConfigParamComposite extends TSSGConfigParam implements ConfigParamComposite, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2690800135915649699L;

	protected static TSSGCache<TSSGConfigParamComposite> cache = new TSSGCache<TSSGConfigParamComposite>("configParamComposite", new TSSGConfigParamComposite[]{});

	@XmlElement(name="configParamAtomic")
	@XmlElementWrapper(name="configParams")
	private List<TSSGConfigParamAtomic> configParams = new ArrayList<TSSGConfigParamAtomic>();

	public TSSGConfigParamComposite() {
	}
	
	protected TSSGConfigParamComposite(ConfigParamComposite param) {
		super(param);
		for (ConfigParamAtomic parameter : param.getConfigurationParameters()) {
			addConfigurationParameter(parameter);
		}		
		flag = true;
	}
	
	public static TSSGConfigParamComposite find(String id) {
		return cache.find(id);
	}

	public static List<? extends ConfigParamComposite> list() {
		return cache.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGConfigParamComposite persist() {
		for (ListIterator<TSSGConfigParamAtomic> it = configParams.listIterator(); it.hasNext(); ) {
			it.set(it.next().resolve().persist());
		}
		return cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		cache.delete(this);
	}

	@Override
	public TSSGConfigParamComposite resolve() {
		return id != null ? cache.find(id) : this;
	}
	
	@Override
	public boolean isModified() {
		boolean modified = super.isModified();
		for (TSSGConfigParamAtomic atomic : configParams) {
			modified |= atomic.resolve().isModified();			
		}
		return modified; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGConfigParamCompositeInstance getInstance() {
		return new TSSGConfigParamCompositeInstance(this);
	}	

	/* (non-Javadoc)
	 * @see teagle.vct.model.ConfigParamComposite#addConfigurationParameter(teagle.vct.model.ConfigParamAtomic)
	 */
	@Override
	public void addConfigurationParameter(ConfigParamAtomic parameter) {
		TSSGConfigParamAtomic param = (parameter instanceof TSSGConfigParamAtomic) ? (TSSGConfigParamAtomic)parameter : new TSSGConfigParamAtomic(parameter);
		configParams.add(param);
		flag = true;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.ConfigParamComposite#getConfigurationParameters()
	 */
	@Override
	public List<? extends ConfigParamAtomic> getConfigurationParameters() {
		List<ConfigParamAtomic> array = new ArrayList<ConfigParamAtomic>();
		for (TSSGConfigParamAtomic atomic : configParams) {
			ConfigParamAtomic a = TSSGConfigParamAtomic.find(atomic.getId());
			array.add(a == null ? atomic : a);
		}
		return array;
	}

	@Override
	public void removeConfigurationParameter(ConfigParamAtomic parameter) {
		configParams.remove(parameter);
		flag = true;
	}

	@XmlRootElement(name="configParamCompositeInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGConfigParamCompositeInstance implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -7059345101561107816L;

		private String commonName;
		
		private String description;
		
		private List<ConfigParamAtomicData> configParams = new ArrayList<ConfigParamAtomicData>();
		
		protected TSSGConfigParamCompositeInstance() {
		}
		
		protected TSSGConfigParamCompositeInstance(TSSGConfigParamComposite composite) {
			commonName = composite.commonName;
			description = composite.description;
			for (TSSGConfigParamAtomic atomic : composite.configParams) {
				ConfigParamAtomicData data = new ConfigParamAtomicData();
				data.configParamAtomic.add(atomic.getId());
				configParams.add(data);
			}
		}
		
		@XmlType(name="configParams")
		@XmlAccessorType(XmlAccessType.FIELD)
		static protected class ConfigParamAtomicData {
			protected List<String> configParamAtomic = new ArrayList<String>();
		}
	}

	public static void main(String[] args) throws RepositoryException {
		TSSGConfigParamComposite composite = new TSSGConfigParamComposite();
		composite.commonName = "newTestComposite";
		composite.description = "what ever description";

		TSSGConfigParamAtomic atomic1 = new TSSGConfigParamAtomic();
		atomic1.commonName = "newTestAtomic1";
		atomic1.description = "what ever description";
		atomic1.setType("string");
		atomic1.setDefaultValue("this is the default");
		
		TSSGConfigParamAtomic atomic2 = new TSSGConfigParamAtomic();
		atomic2.commonName = "newTestAtomic2";
		atomic2.description = "what ever description";
		atomic2.setType("string");
		atomic2.setDefaultValue("this is the default");
		
		composite.addConfigurationParameter(atomic1);
		composite.addConfigurationParameter(atomic2);
		
		composite = composite.persist();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		composite.delete();
	}
}
