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
import teagle.vct.model.Configuration;
import teagle.vct.model.RepositoryException;

/**
 * @author sim
 * 
 */
@XmlRootElement(name = "configlet")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGConfiguration extends TSSGEntity implements Configuration,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -580918860208630756L;

	protected static TSSGCache<TSSGConfiguration> cache = new TSSGCache<TSSGConfiguration>(
			"configlet", new TSSGConfiguration[] {});

	@XmlElement(name = "paramValue")
	protected String value = "";

	// @XmlElementWrapper(name="configurationParametersAtomic")
	// @XmlElement(name="configParamAtomic")
	private TSSGConfigParamAtomic configParamAtomic = new TSSGConfigParamAtomic();

	public TSSGConfiguration() {
	}

	protected TSSGConfiguration(final Configuration configuration) {
		super(configuration);
		this.value = configuration.getValue();
		this.configParamAtomic = (TSSGConfigParamAtomic) configuration
				.getConfigParamAtomic();
		this.flag = true;
	}

	public static TSSGConfiguration find(final String id) {
		return TSSGConfiguration.cache.find(id);
	}

	public static List<? extends Configuration> list() {
		return TSSGConfiguration.cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGConfiguration persist() {
		if (this.configParamAtomic != null)
			this.configParamAtomic.persist();
		return TSSGConfiguration.cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		TSSGConfiguration.cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGConfiguration resolve() {
		return this.id != null ? TSSGConfiguration.cache.find(this.id) : this;
	}

	@Override
	public boolean isModified() {
		return super.isModified()
				|| ((this.configParamAtomic != null) && this.configParamAtomic
						.isModified());
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGConfigurationInstance getInstance() {
		return new TSSGConfigurationInstance(this);
	}

	@Override
	public ConfigParamAtomic getConfigParamAtomic() {
		if (this.configParamAtomic != null) {
			final ConfigParamAtomic a = TSSGConfigParamAtomic
					.find(this.configParamAtomic.getId());
			if (a != null)
				return a;
		}

		return this.configParamAtomic;
	}

	@Override
	public void setValue(final String value) {
		this.value = value;
		this.flag = true;
	}

	@XmlRootElement(name = "configletInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	@SuppressWarnings("unused")
	public static class TSSGConfigurationInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2472241162493208979L;

		private String commonName;

		private String description;

		@XmlElement(name = "paramValue")
		private String value;

		// @XmlElementWrapper(name="configurationParametersAtomic")
		@XmlElement(name = "configParamAtomic.id")
		private String configParamAtomic;

		protected TSSGConfigurationInstance() {
		}

		protected TSSGConfigurationInstance(
				final TSSGConfiguration configuration) {
			this.commonName = configuration.commonName;
			this.description = configuration.description;
			this.value = configuration.value;
			if (configuration.configParamAtomic != null)
				this.configParamAtomic = configuration.configParamAtomic
						.getId();
		}
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public boolean isReferenceArray() {
		return this.getConfigParamAtomic().getType().equals("reference-array");
	}

	@Override
	public boolean isReference() {
		return this.getConfigParamAtomic().getType().startsWith("reference");
	}

	@Override
	public void setConfigParamatomic(final ConfigParamAtomic param) {
		this.configParamAtomic = (TSSGConfigParamAtomic) param;
		this.flag = true;
	}
}
