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

import teagle.vct.model.ConfigParamAtomic;
import teagle.vct.model.ConfigParamComposite;
import teagle.vct.model.RepositoryException;

/**
 * @author sim
 * 
 */
@XmlRootElement(name = "configParamComposite")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGConfigParamComposite extends TSSGConfigParam implements
		ConfigParamComposite, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2690800135915649699L;

	protected static TSSGCache<TSSGConfigParamComposite> cache = new TSSGCache<TSSGConfigParamComposite>(
			"configParamComposite", new TSSGConfigParamComposite[] {});

	@XmlElement(name = "configParamAtomic")
	@XmlElementWrapper(name = "configParams")
	private final List<TSSGConfigParamAtomic> configParams = new ArrayList<TSSGConfigParamAtomic>();

	public TSSGConfigParamComposite() {
	}

	protected TSSGConfigParamComposite(final ConfigParamComposite param) {
		super(param);
		for (final ConfigParamAtomic parameter : param
				.getConfigurationParameters())
			this.addConfigurationParameter(parameter);
		this.flag = true;
	}

	public static TSSGConfigParamComposite find(final String id) {
		return TSSGConfigParamComposite.cache.find(id);
	}

	public static List<? extends ConfigParamComposite> list() {
		return TSSGConfigParamComposite.cache.list();
	}

	@Override
	public TSSGConfigParamComposite persist() {
		for (final ListIterator<TSSGConfigParamAtomic> it = this.configParams
				.listIterator(); it.hasNext();)
			it.set(it.next().resolve().persist());
		return TSSGConfigParamComposite.cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		TSSGConfigParamComposite.cache.delete(this);
	}

	@Override
	public TSSGConfigParamComposite resolve() {
		return this.id != null ? TSSGConfigParamComposite.cache.find(this.id)
				: this;
	}

	@Override
	public boolean isModified() {
		boolean modified = super.isModified();
		for (final TSSGConfigParamAtomic atomic : this.configParams)
			modified |= atomic.resolve().isModified();
		return modified;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGConfigParamCompositeInstance getInstance() {
		return new TSSGConfigParamCompositeInstance(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * teagle.vct.model.ConfigParamComposite#addConfigurationParameter(teagle
	 * .vct.model.ConfigParamAtomic)
	 */
	@Override
	public void addConfigurationParameter(final ConfigParamAtomic parameter) {
		final TSSGConfigParamAtomic param = (parameter instanceof TSSGConfigParamAtomic) ? (TSSGConfigParamAtomic) parameter
				: new TSSGConfigParamAtomic(parameter);
		this.configParams.add(param);
		this.flag = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.ConfigParamComposite#getConfigurationParameters()
	 */
	@Override
	public List<? extends ConfigParamAtomic> getConfigurationParameters() {
		final List<ConfigParamAtomic> array = new ArrayList<ConfigParamAtomic>();
		for (final TSSGConfigParamAtomic atomic : this.configParams) {
			final ConfigParamAtomic a = TSSGConfigParamAtomic.find(atomic
					.getId());
			array.add(a == null ? atomic : a);
		}
		return array;
	}

	@Override
	public void removeConfigurationParameter(final ConfigParamAtomic parameter) {
		this.configParams.remove(parameter);
		this.flag = true;
	}

	@XmlRootElement(name = "configParamCompositeInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGConfigParamCompositeInstance implements
			Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7059345101561107816L;

		private final List<ConfigParamAtomicData> configParams = new ArrayList<ConfigParamAtomicData>();

		protected TSSGConfigParamCompositeInstance() {
		}

		protected TSSGConfigParamCompositeInstance(
				final TSSGConfigParamComposite composite) {
			for (final TSSGConfigParamAtomic atomic : composite.configParams) {
				final ConfigParamAtomicData data = new ConfigParamAtomicData();
				data.configParamAtomic.add(atomic.getId());
				this.configParams.add(data);
			}
		}

		@XmlType(name = "configParams")
		@XmlAccessorType(XmlAccessType.FIELD)
		static protected class ConfigParamAtomicData {
			protected List<String> configParamAtomic = new ArrayList<String>();
		}
	}

	public static void main(final String[] args) throws RepositoryException {
		TSSGConfigParamComposite composite = new TSSGConfigParamComposite();
		composite.commonName = "newTestComposite";
		composite.description = "what ever description";

		final TSSGConfigParamAtomic atomic1 = new TSSGConfigParamAtomic();
		atomic1.commonName = "newTestAtomic1";
		atomic1.description = "what ever description";
		atomic1.setType("string");
		atomic1.setDefaultValue("this is the default");

		final TSSGConfigParamAtomic atomic2 = new TSSGConfigParamAtomic();
		atomic2.commonName = "newTestAtomic2";
		atomic2.description = "what ever description";
		atomic2.setType("string");
		atomic2.setDefaultValue("this is the default");

		composite.addConfigurationParameter(atomic1);
		composite.addConfigurationParameter(atomic2);

		composite = composite.persist();
		try {
			Thread.sleep(5000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		composite.delete();
	}
}
