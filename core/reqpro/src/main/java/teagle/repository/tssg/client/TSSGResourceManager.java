package teagle.repository.tssg.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import teagle.repository.ConfigField;
import teagle.repository.DuplicateNameException;
import teagle.repository.Organization;
import teagle.repository.PTM;
import teagle.repository.ParameterValueException;
import teagle.repository.RepositoryException;
import teagle.repository.Resource;
import teagle.repository.client.PtmManager;
import teagle.repository.client.ResourceManager;
import teagle.repository.tssg.model.ConfigParamAtomic;
import teagle.repository.tssg.model.ConfigParamComposite;
import teagle.repository.tssg.model.Cost;
import teagle.repository.tssg.model.Email;
import teagle.repository.tssg.model.Organisation;
import teagle.repository.tssg.model.Person;
import teagle.repository.tssg.model.PersonRole;
import teagle.repository.tssg.model.TSSGConfiglet;
import teagle.repository.tssg.model.TSSGPtm;
import teagle.repository.tssg.model.TSSGResource;
import teagle.repository.tssg.model.TSSGResourceInstance;

public class TSSGResourceManager extends TSSGOrganizationManager implements ResourceManager, PtmManager
{

	public TSSGResourceManager(ClassLoader loader) throws IOException
	{
		super(loader);
		client.addAliases("ptm", TSSGPtm.class, "organisation", Organisation.class, "person", Person.class, "personRole", PersonRole.class, "email", Email.class, "configParamAtomic", ConfigParamAtomic.class, "configParamComposite", ConfigParamComposite.class,
				"configlet", TSSGConfiglet.class);
	}

	@Override
	public Resource[] getResources() throws RepositoryException
	{
		TSSGResource[] resources = client.listObjects(TSSGResource.class);
		//Resource[] result = new Resource[resources.length];
		ArrayList<Resource> result = new ArrayList<Resource>();
		for (int i = 0; i < resources.length;++i)
		{
			try
			{
				result.add(makeResource(resources[i]));
			}
			catch (RepositoryException e)
			{
				System.out.println("Error while fetching resource info: " + e);
				e.printStackTrace();
			}
		}
		return result.toArray(new Resource[0]);
	}
	
	public Resource[] getResourcesByOrganization(String name) throws RepositoryException
	{
		Organisation orga = getOrganisationByName(name);
		return makeResources(orga.getResources());
	}
	
	protected Resource[] makeResources(TSSGResource[] resources) throws RepositoryException
	{
		ArrayList<Resource> result = new ArrayList<Resource>();
		//TSSGResource[] resources = client.listObjects(TSSGResource.class);
		
		for (TSSGResource r : resources)
				result.add(makeResource(r));
		
		return result.toArray(new Resource[0]);		
	}

	protected Resource makeResource(TSSGResource r) throws RepositoryException
	{
		Organisation o = null;
		o =  getOrganisationByTSSGResource(r);
		String owner = o != null ? o.getName() : "<unknown>";
		ConfigParamAtomic[] params = r.getConfigParams();
		ConfigField[] fields = new ConfigField[params.length];
		for (int i = 0; i < params.length; ++i)
			fields[i] = makeConfigField(params[i]);
		String url = r.getUrl();
		String description = r.getDescription();
	
		return new Resource(r.getId(), owner, owner, r.commonName, r.commonName, r.getPrice(), description, url, fields);
	}
	
	protected ConfigField makeConfigField(ConfigParamAtomic param)
	{
		return new ConfigField(param.commonName, param.configParamType, param.defaultParamValue, param.description);
	}
	
	private Organisation getOrganisationByName(String name) throws RepositoryException
	{
		Organisation[] organisations = client.listObjects(Organisation.class);
		for (Organisation o : organisations)
			if (o.getName().equals(name)) 
				return o;
			else 
				System.out.println("'" + o.getName() + "' - " + name);
		throw new RepositoryException("No such organisation: " + name);
	}
	
	private TSSGResource findResourceByName(String name) throws RepositoryException
	{
		TSSGResource[] resources = client.listObjects(TSSGResource.class);
		for (TSSGResource r : resources)
			if (r.commonName.equals(name))
				return r;
		return null;
	}
	
	private TSSGResource getResourceByName(String name) throws RepositoryException
	{
		TSSGResource r = findResourceByName(name);
		if (r != null)
			return r;
		throw new RepositoryException("No resource by that name: " + name);
	}

	@Override
	public Integer addResource(Resource resource, String resSource,
			String cfgSource) throws RepositoryException
	{
		Organisation owner = getOrganisationByName(resource.getProvider());
		ConfigField[] fields = resource.getConfigFields();
		ConfigParamAtomic[] params = new ConfigParamAtomic[fields.length];
		TSSGResource r = null;
		ConfigParamComposite comp = null;
		Cost price = null;
		//PTM ptm = getPTMByOrganisationId(owner.getId());
		//resource.setType(ptm.id + "." + resource.getType());
		
		if (findResourceByName(resource.getType()) != null)
			throw new DuplicateNameException("A resource with this name already exists: " + resource.getType());

		try
		{
			for (int i = 0; i < fields.length; ++i)
				params[i] = client.addObject(new ConfigParamAtomic(client, fields[i].getName(), fields[i].getType(), fields[i].getDefval(), fields[i].getDescription()));
		
			comp = client.addObject(new ConfigParamComposite(client, resource.getName(), params));
			price = client.addObject(new Cost(client));
			r = client.addObject(new TSSGResource(client, resource.getType(), resource.getUrl()+"|"+resource.getSynopsis(), comp, price));
			
			owner.addResource(r);
			client.updateObject(owner);
		}
		catch (RepositoryException e)
		{
			if (r != null)
				try	{
					client.deleteObject(r);
				} catch (RepositoryException ex) {}
	/*		if (price != null)
				try	{
					client.deleteObject(price);
				} catch (RepositoryException ex) {}
			if (comp != null)
				try	{
					client.deleteObject(comp);
				} catch (RepositoryException ex) {}
			for (int i = 0; i < params.length && params[i] != null; ++i)
				try	{
					client.deleteObject(params[i]);
				} catch (RepositoryException ex) {}*/
			throw new RepositoryException("Error while adding a resource " + e, e);
		}
		
		return r.getId();
	}

	@Override
	public Resource getResource(String name) throws RepositoryException
	{
		return makeResource(getResourceByName(name));
	}
	
	protected TSSGResource getTSSGResourceByType(String type) throws RepositoryException
	{
		TSSGResource[] resources = client.listObjects(TSSGResource.class);
		for (TSSGResource r : resources)
			if (r.commonName.equals(type))
				return r;
		throw new RepositoryException("No resource by that type: " + type);
	}

	@Override
	public void removeResource(String name) throws RepositoryException
	{
		client.deleteObject(getResourceByName(name));
	}
	
	public void removeResource(int id) throws RepositoryException
	{
		client.deleteObject("raSpecification", id);
	}

	@Override
	public void setResource(Resource resource) throws RepositoryException
	{
		System.out.println("setResource called");
		
		if (isUsed(resource.getType()))
			throw new RepositoryException("Instances exist for " + resource.getType() + ". Cannot update.");
		
		TSSGResource old = client.getObject(TSSGResource.class, resource.getId()); //getResourceByName(resource.getType());
		
		ConfigField[] fields = resource.getConfigFields();
		ConfigParamAtomic[] params = new ConfigParamAtomic[fields.length];
		
		for (int i = 0; i < fields.length; ++i)
			params[i] = client.addObject(new ConfigParamAtomic(client, fields[i].getName(), fields[i].getType(), fields[i].getDefval(), fields[i].getDescription()));
	
		ConfigParamComposite comp = client.addObject(new ConfigParamComposite(client, resource.getName(), params));
		
		old.description = old.getReservations() + "|" + resource.getUrl()+"|"+resource.getSynopsis();
		
		old.setConfigurationParameters(comp);
		
		client.updateObject(old);
		
		int newprice = (int)resource.getPrice();
		if (newprice != old.getPrice())
		{
			Cost cost = old.getCost();
			cost.costAmount = newprice;
			client.updateObject(cost);
		}
		
		try {
			CachingTSSGClient.getInstance().clearCache();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isUsed(String name) throws RepositoryException
	{
		TSSGResource r = getResourceByName(name);
		for (TSSGResourceInstance ri : client.listObjects(TSSGResourceInstance.class))
			if (r.getId().equals(ri.getResourceId()))
			{
				System.out.println("Usedby: " + ri.commonName + " " + ri.getId());
				return true;
			}
		return false;
	}
	
	public PTM[] getPTMsSupportingResource(Resource resource) throws RepositoryException
	{
		TSSGResource r = getResourceByName(resource.getType());
		TSSGPtm[] ptms = listTSSGPtms();
		ArrayList<PTM> result = new ArrayList<PTM>();
		
		for (TSSGPtm ptm : ptms)
			if (ptm.supportsResource(r))
				result.add(makePTM(ptm));
		return result.toArray(new PTM[0]);
	}
	
	public Resource[] getResourcesByPTM(PTM ptm) throws RepositoryException
	{
		TSSGPtm tptm = getPtmByName(ptm.getId());
		return makeResources(tptm.getSupportedResources());
	}
	
	public Resource[] getResourcesNotSupportedByPTM(PTM ptm) throws RepositoryException
	{
		TSSGPtm tptm = getPtmByName(ptm.getId());
		//This should be a HashMap, but that implies overriding hashKey() or whatever java calls it. And I dont wanna touch too much
		ArrayList<TSSGResource> resources = new ArrayList<TSSGResource>();
		for (TSSGResource r : tptm.getSupportedResources())
				resources.add(r);
		ArrayList<Resource> result = new ArrayList<Resource>();
		for (TSSGResource r : client.listObjects(TSSGResource.class))
			if (!resources.contains(r))
				result.add(makeResource(r));
		return result.toArray(new Resource[0]);
	}
	
	
	public static class WizardError extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public WizardError(String msg) { super(msg); }
		public WizardError(String msg, Throwable cause) { super(msg, cause); }
	}

	/**
	 * Generates and returns a set of ConfigFields from HtppRequest.
	 * @throws RepositoryException 
	 */
	public static ConfigField[] generateConfigFields(HttpServletRequest req) throws WizardError, RepositoryException {
		String provider = req.getParameter("provider");
		String resource = req.getParameter("resource");

		if (provider==null || resource==null)
			throw new WizardError("missing request parameters");
		
		Map<Integer, Map<String, String>> fields = new HashMap<Integer, Map<String, String>>();
		int num_fields = 0;

		System.out.println("Parameters:");
		// look for all field names in the parameters
		for (Object entry: req.getParameterMap().keySet()) {
			String name = (String)entry;
			String value = req.getParameter(name);

			if (! name.startsWith("field"))
				continue;

			int underscore = name.indexOf("_");
			if (underscore < 0 || underscore < 6)
				throw new WizardError("unexpected field name: " + name);

			int id = Integer.valueOf(name.substring(5, underscore));
			Map<String, String> field = fields.containsKey(id) ? fields.get(id) : 
				new HashMap<String, String>();
			fields.put(id, field);

			String suffix = name.substring(underscore + 1);
			field.put(suffix, value);

			if (num_fields < id+1)
				num_fields = id+1;
		}
		
		if (num_fields != fields.size())
			throw new WizardError(String.format("num_fields=%d but #fields=%d", 
					num_fields, fields.size()));
		// validate name, fields

		String re_identifier = "[a-zA-Z_][a-zA-Z0-9_]*";

		//if (! provider.matches(re_identifier))
		//	throw new WizardError("Provider ('$provider') must look like a valid identifier");

		if (! resource.matches(re_identifier))
			throw new WizardError("Resource name ('$resource') must look like a valid identifier");

		Set<String> names = new HashSet<String>();

		for (Map<String, String> field: fields.values()) {
			String name   = field.get("name");
			String type   = field.get("type");
			String defval = field.get("default");
			//String description = field.get("description");

			if (names.contains(name)) 
				throw new WizardError("Duplicate field: " + name);

			names.add(name);

			if (type.equals("string")) {
				// this is weird. why does php escape the _POST values ?
				//$from = Array("\n",  "\r",  "\"",   "\t");
				//$to   = Array("\\n", "\\r", "\\\"", "\\t");
				//$field["default"] = str_replace($from, $to, $default);
			} else if (type.equals("int")) {
				try{
					if (! defval.equals("")) field.put("default", String.valueOf(Integer.valueOf(defval)));
				}
				catch(Exception e){
					throw new ParameterValueException("Wrong value for parameter type int");
				}
			} else if (type.equals("double")) {
				try{
					if (! defval.equals("")) field.put("default", String.valueOf(Double.valueOf(defval)));
				}
				catch(Exception e){
					throw new ParameterValueException("Wrong value for parameter type double");
				}
			} else if (type.equals("boolean")) {
				try{
					if (! defval.equals("")) field.put("default", defval.equals("true") ? "true" : "false");
				}
				catch(Exception e){
					throw new ParameterValueException("Wrong value for parameter type boolean");
				}
			} else if (type.equals("reference")) {
				try{
					if (! defval.equals("")) field.put("default", defval);
				}
				catch(Exception e){
					throw new ParameterValueException("Wrong value for parameter type reference");
				}
			} else if (type.equals("reference array")) {
				try{
					if (! defval.equals("")) field.put("default", defval);
				}
				catch(Exception e){
					throw new ParameterValueException("Wrong value for parameter type reference array");
				}
			} else {
				throw new WizardError("unknown type: " + type);
			}
		}
		
		// generate ConfigFields

		List<ConfigField> ret = new ArrayList<ConfigField>();
		for (int id=0; id<num_fields; id++) {
			Map<String, String> field = fields.get(id);
			if (field==null) throw new WizardError("null field #" + id);

			String name   = field.get("name");
			String type   = field.get("type");
			String defval = field.get("default");
			String description = field.get("description");

			ret.add(new ConfigField(name, type, defval, description));
		}

		return ret.toArray(new ConfigField[ret.size()]);
	}
	
	protected PTM makePTM(TSSGPtm ptm) throws RepositoryException
	{
		String ownerName = "<none>";
		int proivderId = 0;
		if (ptm.getProvider() != null && ptm.getProvider().getId() != null)
			proivderId = ptm.getProvider().getId();
		if (ptm.getOwner() != null)
			ownerName = ptm.getOwner().userName;
		return new PTM(ptm.getId(), ptm.commonName, ptm.commonName, ptm.url, ownerName, proivderId);
	}
	
	protected TSSGPtm[] listTSSGPtms() throws RepositoryException
	{
		return client.listObjects(TSSGPtm.class);
	}
	
	private TSSGPtm findPtmByName(String name) throws RepositoryException
	{
		for (TSSGPtm ptm : listTSSGPtms())
			if (ptm.commonName.equals(name))
				return ptm;
		return null;
	}
	
	protected TSSGPtm getPtmByName(String name) throws RepositoryException
	{
		TSSGPtm ptm = findPtmByName(name);
		if (ptm == null)
			throw new RepositoryException("No PTM by the name " + name + " found");
		return ptm;
	}
	
	public PTM[] getPTMsByOrganization(Organization organization) throws RepositoryException
	{
		return getPTMsByOrganisationId(organization.getId());
	}
		
	protected PTM[] getPTMsByOrganisationId(int id) throws RepositoryException
	{
		ArrayList<PTM> result = new ArrayList<PTM>();
		for (TSSGPtm p : listTSSGPtms())
			if (p.getProviderId() == id)
				result.add(makePTM(p));
		return result.toArray(new PTM[0]);
	}
	
	protected PTM getPTMByOrganisationId(int id) throws RepositoryException
	{
		PTM[] ptms = getPTMsByOrganisationId(id);
		if (ptms.length < 1)
			throw new RepositoryException("Organisation " + id + " is not associated with a ptm");
		return ptms[0];
	}
	
	public PTM[] getPTMsByResource(Resource resource) throws RepositoryException
	{
		List<TSSGPtm> ptms = new ArrayList<TSSGPtm>();
		
		TSSGResource r = getTSSGResourceByType(resource.getType());
		for (TSSGPtm p : listTSSGPtms())
			if (p.supportsResource(r))
				ptms.add(p);
		
		List<PTM> result = new ArrayList<PTM>();
		
		for (TSSGPtm ptm : ptms)
			result.add(makePTM(ptm));
		
		Organisation o = getOrganisationByResource(resource); 
		if (o != null)
			 for(PTM ptm : getPTMsByOrganisationId(o.getId()))
				result.add(ptm);	
		
		return result.toArray(new PTM[0]);
	}
	
	public PTM getPTMByResource(Resource resource) throws RepositoryException
	{
		PTM[] ptms = getPTMsByResource(resource);
		if (ptms.length < 1)
			throw new RepositoryException("Type " + resource.getType() + " is not associated with a ptm");
		return ptms[0];
	}

	@Override
	public PTM getPtm(String id) throws RepositoryException
	{
		return makePTM(getPtmByName(id));
	}

	@Override
	public PTM[] listPtms() throws RepositoryException
	{
		TSSGPtm[] ptms = listTSSGPtms();
		PTM[] result = new PTM[ptms.length];
		for (int i = 0; i < ptms.length; ++i)
			result[i] = makePTM(ptms[i]);
		return result;
	}

	@Override
	public void removePtm(String id) throws RepositoryException
	{
		client.deleteObject(getPtmByName(id));
	}

	@Override
	public void setPtm(PTM ptm) throws RepositoryException
	{
		TSSGPtm old = getPtmByName(ptm.id);
		old.url = ptm.url;
		old.description = ptm.id;
		client.updateObject(old);
	}

}
