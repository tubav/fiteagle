/**
 * 
 */
package teagle.vct.tssg.impl;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import teagle.vct.model.ConfigParamAtomic;
import teagle.vct.model.RepositoryException;

/**
 * @author sim
 *
 */
@XmlRootElement(name="configParamAtomic")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGConfigParamAtomic extends TSSGConfigParam implements ConfigParamAtomic, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1171347002488523106L;

	protected static TSSGCache<TSSGConfigParamAtomic> cache = new TSSGCache<TSSGConfigParamAtomic>("configParamAtomic", new TSSGConfigParamAtomic[]{});
	
	//@XmlElement(name="defaultParamValue")
	private String defaultParamValue = "";

	//@XmlElement(name="configParamType")
	private String configParamType = "";
	
	public TSSGConfigParamAtomic() {
	}

	protected TSSGConfigParamAtomic(ConfigParamAtomic param) {
		super(param);
		defaultParamValue = param.getDefaultValue();
		configParamType = param.getType();
		flag = true;
	}
	
	public static TSSGConfigParamAtomic find(String id) {
		return cache.find(id);
	}

	public static List<? extends ConfigParamAtomic> list() {
		return cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGConfigParamAtomic persist() {
		return cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		cache.delete(this);
	}

	@Override
	public TSSGConfigParamAtomic resolve() {
		return id != null ? cache.find(id) : this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGConfigParamAtomicInstance getInstance() {
		return new TSSGConfigParamAtomicInstance(this);
	}	

	/* (non-Javadoc)
	 * @see teagle.vct.model.ConfigParamAtomic#getDefaultValue()
	 */
	@Override
	public String getDefaultValue() {
		return defaultParamValue;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.ConfigParamAtomic#getType()
	 */
	@Override
	public String getType() {
		return configParamType;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.ConfigParamAtomic#setDefaultValue(java.lang.String)
	 */
	@Override
	public void setDefaultValue(String defaultValue) {
		defaultParamValue = defaultValue;
		flag = true;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.ConfigParamAtomic#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		configParamType = type;
		flag = true;
	}

	@XmlRootElement(name="configParamAtomicInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGConfigParamAtomicInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5607457572404350156L;
		
		private String commonName;

		private String description;

		private String defaultParamValue;
		private String configParamType;
		
		protected TSSGConfigParamAtomicInstance() {
		}
		
		protected TSSGConfigParamAtomicInstance(TSSGConfigParamAtomic config) {
			commonName = config.commonName;
			description = config.description;
			defaultParamValue = config.defaultParamValue;
			configParamType = config.configParamType;
		}
		
	}

	public static void main(String[] args) throws RepositoryException {
		TSSGConfigParamAtomic atomic = new TSSGConfigParamAtomic();
		atomic.commonName = "newTestAtomic";
		atomic.description = "what ever description";
		atomic.configParamType = "string";
		atomic.defaultParamValue = "this is the default";
		
		atomic = atomic.persist();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		atomic.delete();
	}

	@Override
	public boolean isReference() {
		return getType().toLowerCase().startsWith("reference");
	}
}
