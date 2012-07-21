package teagle.repository.tssg.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import teagle.repository.OrchestrateReturn;
import teagle.repository.OrchestrateReturn.Result.Mapping;
import teagle.repository.RepositoryException;
import teagle.repository.ResourceInstance;
import teagle.repository.Util;
import teagle.repository.VCT;
import teagle.repository.client.VctManager;
import teagle.repository.tssg.model.Booking;
import teagle.repository.tssg.model.Email;
import teagle.repository.tssg.model.Organisation;
import teagle.repository.tssg.model.Person;
import teagle.repository.tssg.model.PersonRole;
import teagle.repository.tssg.model.TSSGConfiglet;
import teagle.repository.tssg.model.TSSGResourceInstance;
import teagle.repository.tssg.model.TSSGVct;
import teagle.repository.tssg.model.VCTState;

public class TSSGVctManager extends TSSGResourceInstanceManager implements VctManager
{

	public TSSGVctManager(ClassLoader loader)
			throws IOException, RepositoryException
	{
		super(loader);
		client.addAliases("vct", TSSGVct.class, "person", Person.class, "personRole", PersonRole.class, "email", Email.class, "organisation", Organisation.class, 
					"vctState", VCTState.class, "booking", Booking.class);
		VCTState.init_static(client);
	}

	private TSSGVct[] getAll() throws RepositoryException
	{
		return client.listObjects(TSSGVct.class);
	}
	
	private Person getPerson(String name) throws RepositoryException
	{
		Person[] persons = client.listObjects(Person.class);
		for (Person p : persons)
			if (p.userName.equals(name))
				return p;
		throw new RepositoryException("No such user: " + name);
	}
	
	private TSSGVct findTSSGVct(String userName, String name) throws RepositoryException
	{
		TSSGVct[] vcts = getAll();
		for (TSSGVct v : vcts)
			if (v.commonName.equals(name) && v.getUser().userName.equals(userName))
				return v;
		return null;
	}
	
	private TSSGVct getTSSGVct(String userName, String name) throws RepositoryException
	{
		TSSGVct v = findTSSGVct(userName, name);
		if (v == null)
			throw new RepositoryException("No VCT found with userName: " + userName + " name: " + name);
		return v;
	}
	
	@Override
	public VCT getVct(String userName, String name) throws RepositoryException
	{
		return makeVCT(getTSSGVct(userName, name));
	}
	
	private VCT makeVCT(TSSGVct v) throws RepositoryException
	{
		HashMap<String, ResourceInstance> instances = new HashMap<String, ResourceInstance>();		
		
		for (TSSGResourceInstance ri : v.getResourceInstances())
		{
			ResourceInstance i = getInstance(ri.commonName);
			instances.put(i.getId(), i);
		}
		
		VCT vct = new VCT(v.getUser().userName, v.commonName, v.getState(), instances.values());
		return vct;
	}

	public String[][] listVcts(String userName) throws RepositoryException
	{
		TSSGVct[] vcts = getAll();
		ArrayList<String[]> result = new ArrayList<String[]>();
		
		for (int i = 0; i < vcts.length; ++i)
			if (vcts[i].getUser().userName.equals(userName))
				result.add(new String[] { userName, vcts[i].commonName });	
		
		return result.toArray(new String[0][]);
	}
	
	public void setVctState(VCT vct) throws RepositoryException
	{
		TSSGVct old = getTSSGVct(vct.user, vct.name);
		old.setState(vct.state);
		client.updateObject(old);
	}
	
	public void setVct(String userName, String name, VCT vct)
			throws RepositoryException
	{
		userName = vct.user;
		name = vct.name; //TODO: is this always initialized?
		ResourceInstance[] instances = vct.getResourceInstances();
		HashMap<String, TSSGResourceInstance> instanceMap = new HashMap<String, TSSGResourceInstance>();		
		
		for (int i = 0; i < instances.length; ++i)
		{
			TSSGResourceInstance ri = doSetInstance(instances[i]);
			instanceMap.put(ri.commonName, ri);
			/*Src src = new Src (client, ri);
			Dst dst = new Dst (client, ri);
			TSSGConnection connection = new TSSGConnection(client, ri.commonName, "references", src, dst);
			connections.add(connection);
			client.addObject(src);
			client.addObject(dst);
			client.addObject(connection);*/
		}
		
		TSSGVct old = findTSSGVct(userName, name);
		
		if (old != null)
		{	
			ArrayList<TSSGResourceInstance> del = new ArrayList<TSSGResourceInstance>();
			for (TSSGResourceInstance i : old.getResourceInstances())
				if (!instanceMap.containsKey(i.commonName))
					del.add(i);
			
			old.setState(vct.getState());
			
			for (TSSGResourceInstance i : del)
				try {
					considerDeletion(i);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
		}
		else
		{
			Person owner = getPerson(userName);
			old = new TSSGVct(client, name, vct.getState(), owner, instanceMap.values().toArray(new TSSGResourceInstance[0]));
			client.addObject(old);
		}
	}

	public boolean testAndSetVct(String userName, String name, VCT currentVct,
			VCT originalVct) throws RepositoryException
	{
		setVct(userName, name, currentVct);
		// TODO Auto-generated method stub
		return true;
	}

	public OrchestrateReturn pollBooking(String userName, String name)
			throws RepositoryException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String[][] getVctsContainingResource(String userName, String id)
			throws RepositoryException
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void renameIds(String userName, String vctName, Mapping[] mappings) throws RepositoryException
	{
		TSSGVct vct = getTSSGVct(userName, vctName);
		ArrayList<TSSGResourceInstance> del = new ArrayList<TSSGResourceInstance>();
		
		for (Mapping m : mappings)
		{
			if (m.designid.equals(m.runtimeid))
				continue;
			
			Util.warn("Renaming: " + m.designid + " --> " + m.runtimeid);
					
			Util.warn("Testing " + vct.getResourceInstances().length + " ris");
			for (TSSGResourceInstance ri : vct.getResourceInstances())
			{
				Util.warn("Testing config of ri: " + ri.commonName + " - " + ri.getId());
				for (TSSGConfiglet cl : ri.getConfigurationData())
				{
					Util.warn("Testing cl: " + cl.getId() + " - " + cl.getCommonName() + " - " + cl.getType() + " - " + cl.getParamValue());
					if (cl.getType().equals("reference") && cl.getParamValue().equals(m.designid))
					{
						Util.warn("Updating it");
						cl.setParamValue(m.runtimeid);
						client.updateObject(cl);
						
					}
					else if (cl.getType().equals("reference-array"))
					{
						String[] ids = cl.getParamValue().split("/");
						boolean update = false;
						for (int i = 0; i < ids.length; ++i)
						{
							try
							{
								String ref = URLDecoder.decode(ids[i], "utf-8");
								if (ref.equals(m.designid))
								{
									ids[i] = URLEncoder.encode(m.runtimeid, "utf-8");
									update = true;
								}
							}
							catch (UnsupportedEncodingException e)
							{
								throw new RepositoryException(e);
							}
						}
						
						if (update)
						{
							String val = "";
							for (int i = 0; i < ids.length; ++i)
							{
								val += ids[i];
								if (i < ids.length - 1)
									val += "/";
							}
							cl.setParamValue(val);
							client.updateObject(cl);
						}
					}
					else
						Util.warn("No update for cl");
				}
			}
			
			TSSGResourceInstance oldInstance = getInstanceById(m.designid);
			TSSGResourceInstance newInstance = findInstanceById(m.runtimeid);
			
			if (newInstance == null)
			{
				Util.warn("No new instance. Setting commonName of " + oldInstance.getId());
				oldInstance.commonName = m.runtimeid;
				client.updateObject(oldInstance);
			}
			else
			{
				Util.warn("Found instance: " + newInstance.getId());
				del.add(oldInstance);
				vct.replaceInstance(oldInstance, newInstance);
			}
		}
		
		if (del.size() != 0)
		{
			client.updateObject(vct);
			for (TSSGResourceInstance oldInstance : del)
			{
				Util.debug("Considering deletion of old instance: " + oldInstance.commonName + " " + oldInstance.getId());
				considerDeletion(oldInstance);
			}
		}
	}
	
	protected void considerDeletion(TSSGResourceInstance i) throws RepositoryException
	{
		try {
			if (i.isProvisioned())
				for (TSSGVct v : client.listObjects(TSSGVct.class))
					for (TSSGResourceInstance ri : v.getResourceInstances())
						if (i.equals(ri))
							return;
			
			Util.debug("Deleting unused: " + i.getId());
			client.deleteObject(i);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
		
	
}
