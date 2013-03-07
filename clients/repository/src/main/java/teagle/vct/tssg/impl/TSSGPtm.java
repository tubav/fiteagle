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
@XmlRootElement(name="ptm")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGPtm extends TSSGEntity implements Ptm, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7576356842855129549L;

	protected static TSSGCache<TSSGPtm> cache = new TSSGCache<TSSGPtm>("ptm", new TSSGPtm[]{});

	@XmlElement(name="provider")
	private TSSGOrganisation organisation = new TSSGOrganisation();

	@XmlElement(name="resourceSpec")
	@XmlElementWrapper(name="resourceSpecs")//added by sha
	private List<TSSGResourceSpec> resourceSpecs = new ArrayList<TSSGResourceSpec>();
	
	private String url = "";
	private String legacyUrl;


	public TSSGPtm() {
	}

	public TSSGPtm(Ptm ptm) {
		super(ptm);
		url = ptm.getUrl();
		legacyUrl = ptm.getLegacyUrl();
		setOrganisation(ptm.getOrganisation());
		for (ResourceSpec spec : ptm.getResourceSpecs())
			addResourceSpec(spec);
		flag = true;
	}
	
	public static TSSGPtm find(String id) {
		return cache.find(id);
	}

	public static List<? extends Ptm> list() {
		return cache.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGPtm persist() {
		organisation = organisation.resolve().persist();
		for (ListIterator<TSSGResourceSpec> it = resourceSpecs.listIterator(); it.hasNext(); ) {
			it.set(it.next().resolve().persist());
		}
		return cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGPtm resolve() {
		return id != null ? cache.find(id) : this;
	}
	
	@Override
	public boolean isModified() {
		boolean modified = super.isModified();
		modified |= organisation.resolve().isModified();
		for (TSSGResourceSpec spec : resourceSpecs)
			modified |= spec.resolve().isModified();
		return modified; 
	}
	
	/* (non-Javadoc)
	 * @see teagle.vct.model.PtmInfo#addResourceSpec(teagle.vct.model.ResourceSpec)
	 */
	@Override
	public void addResourceSpec(ResourceSpec resourceSpec) {
		TSSGResourceSpec spec = (resourceSpec instanceof TSSGResourceSpec) ? (TSSGResourceSpec)resourceSpec : new TSSGResourceSpec(resourceSpec);
		resourceSpecs.add(spec);
		flag = true;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.PtmInfo#getResourceSpecs()
	 */
	@Override
	public List<? extends ResourceSpec> getResourceSpecs() {
		List<ResourceSpec> array = new ArrayList<ResourceSpec>();
		for (TSSGResourceSpec spec : resourceSpecs) {
			TSSGResourceSpec s = (TSSGResourceSpec)TSSGResourceSpec.find(spec.getId());
			array.add(s != null ? s : spec);
		}
		return array;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.PtmInfo#removeResourceSpec(teagle.vct.model.ResourceSpec)
	 */
	@Override
	public void removeResourceSpec(ResourceSpec resourceSpec) {
		resourceSpecs.remove(resourceSpec);
		flag = true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGPtmInstance getInstance() {
		return new TSSGPtmInstance(this);
	}	

	/* (non-Javadoc)
	 * @see teagle.vct.model.Ptm#getOrganisation()
	 */
	@Override
	public Organisation getOrganisation() {
		if (organisation.getId() != null) {
			organisation = TSSGOrganisation.find(organisation.getId());
		}
		return organisation;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.Ptm#setOrganisation(teagle.vct.model.Organisation)
	 */
	@Override
	public void setOrganisation(Organisation organisation) {
		this.organisation = (organisation instanceof TSSGOrganisation) ? (TSSGOrganisation)organisation : new TSSGOrganisation(organisation);
		flag = true;
	}
	
	/* (non-Javadoc)
	 * @see teagle.vct.model.Ptm#getUrl()
	 */
	@Override
	public String getUrl() {
		return url;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.Ptm#setUrl(java.lang.String)
	 */
	@Override
	public void setUrl(String url) {
		this.url = url;
		flag = true;
	}

	@Override
	public String getLegacyUrl() {
		if (legacyUrl == null || legacyUrl.length() == 0)
			return url;
		return legacyUrl;
	}

	@Override
	public void setLegacyUrl(String url) {
		this.legacyUrl = url;
		flag = true;
	}
	
	@XmlRootElement(name="ptmInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	static public class TSSGPtmInstance implements Serializable {
		@XmlType(name="resourceSpecs")
		@XmlAccessorType(XmlAccessType.FIELD)
		static protected class ResourceSpecData
		{
			protected List<String> resourceSpec = new ArrayList<String>();
		} 

		/**
		 * 
		 */
		private static final long serialVersionUID = 8449602261452294753L;

		private String commonName;

		private String description;

		private String provider;
				
		private String url;
		
		private String legacyUrl;
				
		private List<ResourceSpecData> resourceSpecs = new ArrayList<ResourceSpecData>();

				
		protected TSSGPtmInstance() {
		}
		
		protected TSSGPtmInstance(TSSGPtm ptm) {
			this.commonName = ptm.commonName;
			this.description = ptm.description;
			this.url = ptm.url;
			this.legacyUrl = ptm.legacyUrl;
			provider = ptm.organisation.getId();
			for (TSSGResourceSpec resourceSpec : ptm.resourceSpecs) {
				ResourceSpecData data = new ResourceSpecData();
				data.resourceSpec.add(resourceSpec.getId());
				resourceSpecs.add(data);
			}

		}

		@XmlType(name="configurationData")
		@XmlAccessorType(XmlAccessType.FIELD)
		static protected class ConfigData {
			protected List<String> configlet = new ArrayList<String>();
		}
	}

	public static void main(String[] args) throws RepositoryException {
		TSSGPtm ptm = new TSSGPtm();
		ptm.commonName = "testPtm35";
		ptm.description = "descr";

		ptm.url = "http://example.com";
		ptm.organisation = (TSSGOrganisation)ModelManager.getInstance().findOrganisationsByUserName("testuser").get(0);

		TSSGResourceSpec spec1 = new TSSGResourceSpec();
		spec1.commonName = "newResourceSpec7";
		spec1.description = "whatever";

		
		List<TSSGConfigParamAtomic> params = new ArrayList<TSSGConfigParamAtomic>();
		TSSGConfigParamAtomic param = new TSSGConfigParamAtomic();
		param.commonName = "newTestAtomic1";
		param.description = "what ever description";
		param.setType("string");
		param.setDefaultValue("this is the default");
		params.add(param);
		
		spec1.setConfigurationParameters(params);
		
		TSSGResourceSpec spec2 = new TSSGResourceSpec();
		spec2.commonName = "newResourceSpec8";
		spec2.description = "whatever";

		
		List<TSSGConfigParamAtomic> params2 = new ArrayList<TSSGConfigParamAtomic>();
		TSSGConfigParamAtomic param2 = new TSSGConfigParamAtomic();
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
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ptm.delete();
	}

	@Override
	public boolean supportsResourceSpec(ResourceSpec resourceSpec)
	{
		for (TSSGResourceSpec spec : resourceSpecs)
			if (spec.getId().equals(((TSSGResourceSpec)resourceSpec).getId()))
				return true;
		
		return false;
	}
}
