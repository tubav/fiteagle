/**
 * 
 */
package teagle.vct.model;

import java.util.Collection;
import java.util.List;

/**
 * @author sim
 *
 */
public abstract class ModelManager {
	
	private static ModelManager instance;
	
	public static ModelManager getInstance() {
		if (instance == null) {
			try {
				instance = (ModelManager)Class.forName("teagle.vct.tssg.impl.TSSGModelFactory").newInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

//	public abstract void config(URL url, String username, String password);
	
	public abstract Vct createVct();
	
	public abstract Person createPerson();
	
	public abstract ResourceSpec createResource();

	public abstract ResourceInstance createResourceInstance(ResourceSpec resource);

	public abstract Configuration createConfiguration(ConfigParamAtomic param);
	
	public abstract ConfigParamAtomic createConfigParamAtomic();
	
	public abstract ConfigParamComposite createConfigParamComposite();

	public abstract Vct findVct(String userName, String commonName);
	
	public abstract List<Vct> findVctsByUserName(String userName);
	
	public abstract Person findPersonByUserName(String userName);

	public abstract Collection<ResourceInstance> findResourceInstancesByUserName(String userName);
	
	public abstract List<? extends Organisation> listOrganisations();
	
	public abstract List<? extends Ptm> listPtms();

	public abstract List<? extends ResourceSpec> listResourceSpecs();
	
	public abstract <T> T persist(T obj);

	public abstract <T extends Entity> void delete(T obj) throws RepositoryException;

	public abstract <T extends Entity> boolean isModified(T obj);
	
	//added by sha:
	public abstract Organisation getOrganisation(String organisationName);
	
	public abstract ResourceSpec getResourceSpec(String resourceName);
	
	public abstract List<Organisation> findOrganisationsByUserName(String userName);
	
	public abstract List<Ptm> listPtmsByOrganisation(String organisationName);
	
	public abstract Ptm getPtm(String ptmName);
	
	public abstract Ptm createPtm();
	
	public abstract List<ResourceSpec> getResourcesNotSupportedByPtm(Ptm ptm);
	
	public abstract List<? extends Person> listPersons();
	
	public abstract Organisation createOrganisation();
	
	public abstract Email createEmail();
	
	public abstract PersonRole getCustomerRole();
	
	public abstract PersonRole getPartnerRole();
	
	public abstract boolean vctExists(String vctName, String userName);
	
	public abstract Geometry createGeometry(int x, int y);

	public abstract void config(RepoClientConfig config);

	public abstract List<ResourceInstance> findUnusedResourceInstances();

	public abstract ResourceInstance findResourceInstanceByName(String name)
			throws RepositoryException;
	 

//	public abstract List<? extends Person> findPersonsByOrganisationName(String organisationName);
	
//	public abstract List<ResourceSpec> findResourceSpecsByOrganisation(String organisationName);
	
	//public abstract ResourceInstance getResourceInstance(String commonName);
}
