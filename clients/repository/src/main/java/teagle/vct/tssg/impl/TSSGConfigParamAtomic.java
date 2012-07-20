/**
 * 
 */
package teagle.vct.tssg.impl;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import teagle.vct.model.ConfigParamAtomic;
import teagle.vct.model.RepositoryException;

/**
 * @author sim
 * 
 */
@XmlRootElement(name = "configParamAtomic")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGConfigParamAtomic extends TSSGConfigParam implements
		ConfigParamAtomic, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1171347002488523106L;

	protected static TSSGCache<TSSGConfigParamAtomic> cache = new TSSGCache<TSSGConfigParamAtomic>(
			"configParamAtomic", new TSSGConfigParamAtomic[] {});

	// @XmlElement(name="defaultParamValue")
	private String defaultParamValue = "";

	// @XmlElement(name="configParamType")
	private String configParamType = "";

	public TSSGConfigParamAtomic() {
	}

	protected TSSGConfigParamAtomic(final ConfigParamAtomic param) {
		super(param);
		this.defaultParamValue = param.getDefaultValue();
		this.configParamType = param.getType();
		this.flag = true;
	}

	public static TSSGConfigParamAtomic find(final String id) {
		return TSSGConfigParamAtomic.cache.find(id);
	}

	public static List<? extends ConfigParamAtomic> list() {
		return TSSGConfigParamAtomic.cache.list();
	}

	@Override
	public TSSGConfigParamAtomic persist() {
		return TSSGConfigParamAtomic.cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		TSSGConfigParamAtomic.cache.delete(this);
	}

	@Override
	public TSSGConfigParamAtomic resolve() {
		return this.id != null ? TSSGConfigParamAtomic.cache.find(this.id)
				: this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGConfigParamAtomicInstance getInstance() {
		return new TSSGConfigParamAtomicInstance(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.ConfigParamAtomic#getDefaultValue()
	 */
	@Override
	public String getDefaultValue() {
		return this.defaultParamValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.ConfigParamAtomic#getType()
	 */
	@Override
	public String getType() {
		return this.configParamType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.ConfigParamAtomic#setDefaultValue(java.lang.String)
	 */
	@Override
	public void setDefaultValue(final String defaultValue) {
		this.defaultParamValue = defaultValue;
		this.flag = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.ConfigParamAtomic#setType(java.lang.String)
	 */
	@Override
	public void setType(final String type) {
		this.configParamType = type;
		this.flag = true;
	}

	@XmlRootElement(name = "configParamAtomicInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGConfigParamAtomicInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5607457572404350156L;

		protected TSSGConfigParamAtomicInstance() {
		}

		protected TSSGConfigParamAtomicInstance(
				final TSSGConfigParamAtomic config) {
		}

	}

	public static void main(final String[] args) throws RepositoryException {
		TSSGConfigParamAtomic atomic = new TSSGConfigParamAtomic();
		atomic.commonName = "newTestAtomic";
		atomic.description = "what ever description";
		atomic.configParamType = "string";
		atomic.defaultParamValue = "this is the default";

		atomic = atomic.persist();
		try {
			Thread.sleep(5000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		atomic.delete();
	}

	@Override
	public boolean isReference() {
		return this.getType().toLowerCase().startsWith("reference");
	}
}
