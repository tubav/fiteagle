package teagle.repository.tssg.client.test;

import java.io.IOException;

import teagle.repository.RepositoryException;
import teagle.repository.client.OrganizationManager;
import teagle.repository.tssg.client.TSSGOrganizationManager;
import teagle.repository.tssg.client.TSSGResourceManager;
import teagle.repository.tssg.client.TSSGVctManager;

public class ClientTest
{
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws RepositoryException 
	 */
	public static void main(String[] args) throws IOException, RepositoryException
	{
		OrganizationManager om = new TSSGOrganizationManager(null);
		
		TSSGVctManager vm = new TSSGVctManager(null);
		TSSGResourceManager rm = new TSSGResourceManager(null);
		//TSSGClient client = new TSSGClient(null, "configParamComposite", ConfigParamComposite.class);
		
	//	client.deleteObject("configParamComposite", 4);
		//System.out.println(om.getOrganization(1).id);
		//om.addOrganization(new Organization("qwertz123456"));
/*		for (int i = 0; i <= 200; ++i)
		{
			try
			{
				rm.removeResource(i);
				
			}
			catch (RepositoryException e)
			{
				
			}
			
		}*/
		//	om.removeOrganization(i);
		
		/*Organization[] orgs = om.listOrganizations();
		System.out.println(orgs.length);
		System.out.println("done");*/
		
//		vm.getVct("huhu", "blaw");
		
		//pm.addPtm(new PTM("id", "huhu", "testurl", "sfox", 1));
		
		//Resource[] rs = rm.getResourcesByOrganization("Fraunhofer FOKUS");
		//System.out.println(rs.length);
		//Resource tr = new Resource(null, "Fraunhofer FOKUS", "Fraunhofer FOKUS", "test5", "test5", 0, "test", new ConfigField[] { new ConfigField("test", "string", "test"), new ConfigField("bla", "string", "bla") });
		//Resource tr = new Resource(null, "Nokia", "Nokia", "test6", "test6", 0, "test", new ConfigField[] { new ConfigField("test", "string", "test") });
		//rm.addResource(tr, "", "");
		//rm.isUsed("test9");
//		Resource[] resources = rm.getResourcesByOrganization("Fraunhofer FOKUS");
//		System.out.println("Setting... " + resources[0].getName());
//		rm.setResource(resources[0]);
//		System.out.println("Getting...");
//		resources = rm.getResourcesByOrganization("Fraunhofer FOKUS");
	//	Resource resource = rm.getResource("testresource");
		//PTM ptm = rm.getPTMByResource(resource);
//		Organization org = pm.getOrganizationByResource(resource);
	//	System.out.println(org.name);
		//rm.getOrganizationByResource(r);
		
	//	Organization o = om.getOrganization(9);
	//	PTM[] ptms = pm.getPTMsByOrganization(o);
		//rm.getResource("test9");
		//Organization[] os = om.listOrganizations();
		//Organization[] o = om.getOrganizations("testuser");
//	Resource r = rm.getResource("test1");
//		Resource r1 = rm.getResource("VideoLan");
//		Organization o = pm.getOrganizationByResource(r1);
//		System.out.println(o.name);
		//Resource r2 = rm.getResource("PCSCF");
//		System.out.println(r1.getSynopsis());
//		PTM[] ptms = rm.getPTMsSupportingResource(r1);
	//	PTM ptm = rm.getPTMByResource(r1);
		//PTM ptm = pm.getPtm("fokus");
		//rm.addSupportedResource(ptm, "SCSCF");
		//rm.removeSupportedResource(ptm, "ICSCF");
		vm.getVct("root", "myvct");
		//fixVCTs();
		//vm.purgeConnections();
		System.out.println("done");
	}

}
