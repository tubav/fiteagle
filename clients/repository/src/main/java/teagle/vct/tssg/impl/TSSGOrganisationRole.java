/**
 * 
 */
package teagle.vct.tssg.impl;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import teagle.vct.model.OrganisationRole;
import teagle.vct.model.RepositoryException;

/**
 * @author sim
 * 
 */
@XmlRootElement(name = "organisationRole")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGOrganisationRole extends TSSGObject implements
		OrganisationRole, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8096826571716167073L;

	protected static TSSGCache<TSSGOrganisationRole> cache = new TSSGCache<TSSGOrganisationRole>(
			"organisationRole", new TSSGOrganisationRole[] {});

	public TSSGOrganisationRole() {
	}

	protected TSSGOrganisationRole(final OrganisationRole role) {
	}

	public static TSSGOrganisationRole find(final String id) {
		return TSSGOrganisationRole.cache.find(id);
	}

	public static List<? extends OrganisationRole> list() {
		return TSSGOrganisationRole.cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGOrganisationRole persist() {
		return TSSGOrganisationRole.cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		TSSGOrganisationRole.cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGOrganisationRole resolve() {
		return this.id != null ? TSSGOrganisationRole.cache.find(this.id)
				: this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGOrganisationRole getInstance() {
		// TODO change to instance object
		return this;
	}

}
