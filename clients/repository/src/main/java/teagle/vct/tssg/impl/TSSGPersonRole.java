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
@XmlRootElement(name="personRole")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGPersonRole extends TSSGObject implements PersonRole, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -124818263548417447L;
	
//	protected static TSSGCache<TSSGPersonRole> cache = new TSSGCache<TSSGPersonRole>("personRole", new TSSGPersonRole[]{});
	protected static Map<String, TSSGPersonRole> cache = new HashMap<String, TSSGPersonRole>();
	
	private String name = "";

	static {
		TSSGPersonRole panlabCustomer = new TSSGPersonRole();
		panlabCustomer.id = "1";
		panlabCustomer.name = "Panlab Customer";
		
		cache.put(panlabCustomer.id, panlabCustomer);
		
		TSSGPersonRole teagleAdministrator = new TSSGPersonRole();
		teagleAdministrator.id = "2";
		teagleAdministrator.name = "Teagle Administrator";
		
		cache.put(teagleAdministrator.id, teagleAdministrator);
		
		TSSGPersonRole vctUser = new TSSGPersonRole();
		vctUser.id = "3";
		vctUser.name = "VCT User";
		
		cache.put(vctUser.id, vctUser);

		TSSGPersonRole panlabPartner = new TSSGPersonRole();
		panlabPartner.id = "4";
		panlabPartner.name = "Panlab Partner";
		
		cache.put(panlabPartner.id, panlabPartner);
	}
	
	public TSSGPersonRole() {
	}

	protected TSSGPersonRole(PersonRole role) {
		name = role.getName();
		flag = true;
	}
	
	public static TSSGPersonRole find(String id) {
		return cache.get(id);
	}

	public static List<? extends PersonRole> list() {
		return new ArrayList<TSSGPersonRole>(cache.values());
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGPersonRole persist() {
		return this;
	}

	@Override
	public void delete() {
//		cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGPersonRole resolve() {
		return id != null ? cache.get(id) : this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGPersonRoleInstance getInstance() {
		return new TSSGPersonRoleInstance(this);
	}	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
		flag = true;
	}

	@XmlRootElement(name="personRoleInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	static public class TSSGPersonRoleInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -4320643309065987627L;
		
		private String name;
		
		protected TSSGPersonRoleInstance() {
		}
		
		protected TSSGPersonRoleInstance(TSSGPersonRole instance) {
			this.name = instance.name;
		}
	}

	public static void main(String[] args) {
		TSSGPersonRole role = new TSSGPersonRole();
		role.name = "testRole";
		role.persist();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		role.delete();
	}
}
