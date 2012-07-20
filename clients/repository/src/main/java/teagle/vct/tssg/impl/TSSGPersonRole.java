/**
 * 
 */
package teagle.vct.tssg.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import teagle.vct.model.PersonRole;

/**
 * @author sim
 * 
 */
@XmlRootElement(name = "personRole")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGPersonRole extends TSSGObject implements PersonRole,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -124818263548417447L;

	// protected static TSSGCache<TSSGPersonRole> cache = new
	// TSSGCache<TSSGPersonRole>("personRole", new TSSGPersonRole[]{});
	protected static Map<String, TSSGPersonRole> cache = new HashMap<String, TSSGPersonRole>();

	private String name = "";

	static {
		final TSSGPersonRole panlabCustomer = new TSSGPersonRole();
		panlabCustomer.id = "1";
		panlabCustomer.name = "Panlab Customer";

		TSSGPersonRole.cache.put(panlabCustomer.id, panlabCustomer);

		final TSSGPersonRole teagleAdministrator = new TSSGPersonRole();
		teagleAdministrator.id = "2";
		teagleAdministrator.name = "Teagle Administrator";

		TSSGPersonRole.cache.put(teagleAdministrator.id, teagleAdministrator);

		final TSSGPersonRole vctUser = new TSSGPersonRole();
		vctUser.id = "3";
		vctUser.name = "VCT User";

		TSSGPersonRole.cache.put(vctUser.id, vctUser);

		final TSSGPersonRole panlabPartner = new TSSGPersonRole();
		panlabPartner.id = "4";
		panlabPartner.name = "Panlab Partner";

		TSSGPersonRole.cache.put(panlabPartner.id, panlabPartner);
	}

	public TSSGPersonRole() {
	}

	protected TSSGPersonRole(final PersonRole role) {
		this.name = role.getName();
		this.flag = true;
	}

	public static TSSGPersonRole find(final String id) {
		return TSSGPersonRole.cache.get(id);
	}

	public static List<? extends PersonRole> list() {
		return new ArrayList<TSSGPersonRole>(TSSGPersonRole.cache.values());
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGPersonRole persist() {
		return this;
	}

	@Override
	public void delete() {
		// cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGPersonRole resolve() {
		return this.id != null ? TSSGPersonRole.cache.get(this.id) : this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGPersonRoleInstance getInstance() {
		return new TSSGPersonRoleInstance(this);
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@Override
	public void setName(final String name) {
		this.name = name;
		this.flag = true;
	}

	@XmlRootElement(name = "personRoleInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	static public class TSSGPersonRoleInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -4320643309065987627L;

		protected TSSGPersonRoleInstance() {
		}

		protected TSSGPersonRoleInstance(final TSSGPersonRole instance) {
		}
	}

	public static void main(final String[] args) {
		final TSSGPersonRole role = new TSSGPersonRole();
		role.name = "testRole";
		role.persist();
		try {
			Thread.sleep(5000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		role.delete();
	}
}
