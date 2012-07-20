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

import teagle.vct.model.ModelManager;
import teagle.vct.model.Organisation;
import teagle.vct.model.Ptm;
import teagle.vct.model.RepositoryException;
import teagle.vct.model.ResourceSpec;

/**
 * @author sim
 * 
 */
@XmlRootElement(name = "ptm")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGPtm extends TSSGEntity implements Ptm, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7576356842855129549L;

	protected static TSSGCache<TSSGPtm> cache = new TSSGCache<TSSGPtm>("ptm",
			new TSSGPtm[] {});

	@XmlElement(name = "provider")
	private TSSGOrganisation organisation = new TSSGOrganisation();

	@XmlElement(name = "resourceSpec")
	@XmlElementWrapper(name = "resourceSpecs")
	// added by sha
	private final List<TSSGResourceSpec> resourceSpecs = new ArrayList<TSSGResourceSpec>();

	private String url = "";
	private String legacyUrl;

	public TSSGPtm() {
	}

	public TSSGPtm(final Ptm ptm) {
		super(ptm);
		this.url = ptm.getUrl();
		this.legacyUrl = ptm.getLegacyUrl();
		this.setOrganisation(ptm.getOrganisation());
		for (final ResourceSpec spec : ptm.getResourceSpecs())
			this.addResourceSpec(spec);
		this.flag = true;
	}

	public static TSSGPtm find(final String id) {
		return TSSGPtm.cache.find(id);
	}

	public static List<? extends Ptm> list() {
		return TSSGPtm.cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGPtm persist() {
		this.organisation = this.organisation.resolve().persist();
		for (final ListIterator<TSSGResourceSpec> it = this.resourceSpecs
				.listIterator(); it.hasNext();)
			it.set(it.next().resolve().persist());
		return TSSGPtm.cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		TSSGPtm.cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGPtm resolve() {
		return this.id != null ? TSSGPtm.cache.find(this.id) : this;
	}

	@Override
	public boolean isModified() {
		boolean modified = super.isModified();
		modified |= this.organisation.resolve().isModified();
		for (final TSSGResourceSpec spec : this.resourceSpecs)
			modified |= spec.resolve().isModified();
		return modified;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * teagle.vct.model.PtmInfo#addResourceSpec(teagle.vct.model.ResourceSpec)
	 */
	@Override
	public void addResourceSpec(final ResourceSpec resourceSpec) {
		final TSSGResourceSpec spec = (resourceSpec instanceof TSSGResourceSpec) ? (TSSGResourceSpec) resourceSpec
				: new TSSGResourceSpec(resourceSpec);
		this.resourceSpecs.add(spec);
		this.flag = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.PtmInfo#getResourceSpecs()
	 */
	@Override
	public List<? extends ResourceSpec> getResourceSpecs() {
		final List<ResourceSpec> array = new ArrayList<ResourceSpec>();
		for (final TSSGResourceSpec spec : this.resourceSpecs) {
			final TSSGResourceSpec s = TSSGResourceSpec.find(spec.getId());
			array.add(s != null ? s : spec);
		}
		return array;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * teagle.vct.model.PtmInfo#removeResourceSpec(teagle.vct.model.ResourceSpec
	 * )
	 */
	@Override
	public void removeResourceSpec(final ResourceSpec resourceSpec) {
		this.resourceSpecs.remove(resourceSpec);
		this.flag = true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGPtmInstance getInstance() {
		return new TSSGPtmInstance(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Ptm#getOrganisation()
	 */
	@Override
	public Organisation getOrganisation() {
		if (this.organisation.getId() != null)
			this.organisation = TSSGOrganisation
					.find(this.organisation.getId());
		return this.organisation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Ptm#setOrganisation(teagle.vct.model.Organisation)
	 */
	@Override
	public void setOrganisation(final Organisation organisation) {
		this.organisation = (organisation instanceof TSSGOrganisation) ? (TSSGOrganisation) organisation
				: new TSSGOrganisation(organisation);
		this.flag = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Ptm#getUrl()
	 */
	@Override
	public String getUrl() {
		return this.url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Ptm#setUrl(java.lang.String)
	 */
	@Override
	public void setUrl(final String url) {
		this.url = url;
		this.flag = true;
	}

	@Override
	public String getLegacyUrl() {
		if ((this.legacyUrl == null) || (this.legacyUrl.length() == 0))
			return this.url;
		return this.legacyUrl;
	}

	@Override
	public void setLegacyUrl(final String url) {
		this.legacyUrl = url;
		this.flag = true;
	}

	@XmlRootElement(name = "ptmInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	static public class TSSGPtmInstance implements Serializable {
		@XmlType(name = "resourceSpecs")
		@XmlAccessorType(XmlAccessType.FIELD)
		static protected class ResourceSpecData {
			protected List<String> resourceSpec = new ArrayList<String>();
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 8449602261452294753L;

		private String provider;

		private final List<ResourceSpecData> resourceSpecs = new ArrayList<ResourceSpecData>();

		protected TSSGPtmInstance() {
		}

		protected TSSGPtmInstance(final TSSGPtm ptm) {
			this.provider = ptm.organisation.getId();
			for (final TSSGResourceSpec resourceSpec : ptm.resourceSpecs) {
				final ResourceSpecData data = new ResourceSpecData();
				data.resourceSpec.add(resourceSpec.getId());
				this.resourceSpecs.add(data);
			}

		}

		@XmlType(name = "configurationData")
		@XmlAccessorType(XmlAccessType.FIELD)
		static protected class ConfigData {
			protected List<String> configlet = new ArrayList<String>();
		}
	}

	public static void main(final String[] args) throws RepositoryException {
		TSSGPtm ptm = new TSSGPtm();
		ptm.commonName = "testPtm35";
		ptm.description = "descr";

		ptm.url = "http://example.com";
		ptm.organisation = (TSSGOrganisation) ModelManager.getInstance()
				.findOrganisationsByUserName("testuser").get(0);

		final TSSGResourceSpec spec1 = new TSSGResourceSpec();
		spec1.commonName = "newResourceSpec7";
		spec1.description = "whatever";

		final List<TSSGConfigParamAtomic> params = new ArrayList<TSSGConfigParamAtomic>();
		final TSSGConfigParamAtomic param = new TSSGConfigParamAtomic();
		param.commonName = "newTestAtomic1";
		param.description = "what ever description";
		param.setType("string");
		param.setDefaultValue("this is the default");
		params.add(param);

		spec1.setConfigurationParameters(params);

		final TSSGResourceSpec spec2 = new TSSGResourceSpec();
		spec2.commonName = "newResourceSpec8";
		spec2.description = "whatever";

		final List<TSSGConfigParamAtomic> params2 = new ArrayList<TSSGConfigParamAtomic>();
		final TSSGConfigParamAtomic param2 = new TSSGConfigParamAtomic();
		param2.commonName = "newTestAtomic1";
		param2.description = "what ever description";
		param2.setType("string");
		param2.setDefaultValue("this is the default");
		params2.add(param2);

		spec2.setConfigurationParameters(params2);

		ptm.addResourceSpec(spec1);
		ptm.addResourceSpec(spec2);

		ptm = ptm.persist();
		try {
			Thread.sleep(5000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		ptm.delete();
	}

	@Override
	public boolean supportsResourceSpec(final ResourceSpec resourceSpec) {
		for (final TSSGResourceSpec spec : this.resourceSpecs)
			if (spec.getId().equals(((TSSGResourceSpec) resourceSpec).getId()))
				return true;

		return false;
	}
}
