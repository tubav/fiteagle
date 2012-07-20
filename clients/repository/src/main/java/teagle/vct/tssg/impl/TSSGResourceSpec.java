/**
 * 
 */
package teagle.vct.tssg.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import teagle.vct.model.ConfigParam;
import teagle.vct.model.ConfigParamAtomic;
import teagle.vct.model.ConfigParamComposite;
import teagle.vct.model.ModelManager;
import teagle.vct.model.Organisation;
import teagle.vct.model.RepositoryException;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.ResourceSpec;

/**
 * @author sim
 * 
 */
@XmlRootElement(name = "resourceSpec")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGResourceSpec extends TSSGEntity implements ResourceSpec,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6193166882345603102L;

	protected static TSSGCache<TSSGResourceSpec> cache = new TSSGCache<TSSGResourceSpec>(
			"resourceSpec", new TSSGResourceSpec[] {});

	private TSSGConfigParam configurationParameters = new TSSGConfigParamComposite();

	private String url = null;
	private String reservations = null;// not yet used
	private String realDescription = null;

	public TSSGResourceSpec() {
	}

	protected TSSGResourceSpec(final ResourceSpec resourceSpec) {
		super(resourceSpec);
		this.setConfigurationParameters(resourceSpec
				.getConfigurationParameters());
		this.flag = true;
	}

	public static TSSGResourceSpec find(final String id) {
		return TSSGResourceSpec.cache.find(id);
	}

	public static List<? extends ResourceSpec> list() {
		return TSSGResourceSpec.cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceSpec persist() {
		this.configurationParameters = this.configurationParameters.resolve()
				.persist();
		return TSSGResourceSpec.cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		// configurationParameters.resolve().delete();//repo deletes them
		TSSGResourceSpec.cache.delete(this);
		// cost.resolve().delete();//repo does this
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceSpec resolve() {
		return this.id != null ? TSSGResourceSpec.cache.find(this.id) : this;
	}

	@Override
	public boolean isModified() {
		boolean modified = super.isModified();
		modified |= this.configurationParameters.resolve().isModified();
		return modified;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceSpecInstance getInstance() {
		return new TSSGResourceSpecInstance(this);
	}

	@Override
	public List<? extends ConfigParamAtomic> getConfigurationParameters() {
		final ConfigParamComposite param = TSSGConfigParamComposite
				.find(this.configurationParameters.getId());
		if (param != null)
			return param.getConfigurationParameters();

		final ConfigParamAtomic parama = TSSGConfigParamAtomic
				.find(this.configurationParameters.getId());

		final List<ConfigParamAtomic> result = new ArrayList<ConfigParamAtomic>();

		if (parama != null)
			result.add(parama);

		return result;
	}

	@Override
	public void setConfigurationParameters(
			final List<? extends ConfigParamAtomic> params) {
		final TSSGConfigParamComposite comp = new TSSGConfigParamComposite();
		comp.commonName = "specConfigParams";

		if (params != null)
			for (final ConfigParamAtomic p : params)
				comp.addConfigurationParameter(p);

		this.configurationParameters = comp;

		this.flag = true;
	}

	@Override
	public String getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProvider() {
		final List<? extends Organisation> organisationList = TSSGOrganisation
				.list();
		for (final Organisation o : organisationList)
			for (final ResourceSpec r : o.getResourceSpecs())
				if (this.commonName.equals(r.getCommonName()))
					return o.getName();
		return null;
	}

	@Override
	public String getType() {
		return this.commonName;
	}

	private void splitDescription() {
		this.url = "";
		this.reservations = "";

		final String[] vals = this.description.split("\\|");

		if (vals.length == 0) {
			this.realDescription = "";
			return;
		}

		this.realDescription = vals[vals.length - 1];
		if (vals.length > 1)
			this.url = vals[vals.length - 2];
		if (vals.length > 2)
			this.reservations = vals[0];
	}

	@Override
	public String getDescription() {
		this.splitDescription();
		return this.realDescription;
	}

	@Override
	public void setDescription(final String description) {
		this.splitDescription();
		super.setDescription(this.reservations + "|" + this.url + "|"
				+ description);
	}

	@Override
	public String getUrl() {
		this.splitDescription();
		return this.url;
	}

	@Override
	public void setUrl(final String url) {
		this.splitDescription();
		super.setDescription(this.reservations + "|" + url + "|"
				+ this.realDescription);
	}

	@Override
	public boolean isUsed() {
		final List<? extends ResourceInstance> resourceInstanceList = TSSGResourceInstance
				.list();
		for (final ResourceInstance ri : resourceInstanceList)
			if (ri.getResourceSpec().getCommonName().equals(this.commonName))
				return true;
		return false;
	}

	@XmlRootElement(name = "resourceSpecInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGResourceSpecInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 814157965321096960L;

		private String configurationParameters;

		protected TSSGResourceSpecInstance() {
		}

		protected TSSGResourceSpecInstance(final TSSGResourceSpec spec) {
			this.configurationParameters = spec.configurationParameters.getId();
		}

	}

	public static void main(final String[] args) {
		// TSSGResourceSpec spec = new TSSGResourceSpec();
		final TSSGResourceSpec spec = (TSSGResourceSpec) ModelManager
				.getInstance().getResourceSpec("newResourceSpec9");
		spec.commonName = "newResourceSpec9";
		// spec.description = "whatever lululu";

		spec.setDescription("description lululu");
		spec.setUrl("www.bla.org");

		final TSSGConfigParamComposite composite = new TSSGConfigParamComposite();
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

		spec.configurationParameters = composite;

		ModelManager.getInstance().persist(spec);

		// spec = spec.persist();
		// try {
		// Thread.sleep(20000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// spec.delete();
	}

	@Override
	public ConfigParam getConfigParam() {
		return this.configurationParameters;
	}

}
