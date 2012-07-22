/**
 *  Copyright 2010, Konstantinos Koutsopoulos (k.koutsopoulos@yahoo.gr), Nikos Mouratidis (nmouratid@teemail.gr)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


package org.panlab.tgw.restclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import teagle.vct.model.ConfigParamAtomic;
import teagle.vct.model.ConfigParamComposite;
import teagle.vct.model.Configuration;
import teagle.vct.model.Entity;
import teagle.vct.model.EntityNotFound;
import teagle.vct.model.ModelManager;
import teagle.vct.model.Ptm;
import teagle.vct.model.RepoClientConfig;
import teagle.vct.model.RepositoryException;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.ResourceInstanceState;
import teagle.vct.model.ResourceSpec;
import teagle.vct.tssg.impl.TSSGConfigParamAtomic;
import teagle.vct.tssg.impl.TSSGConfigParamComposite;
import teagle.vct.tssg.impl.TSSGConfiguration;
import teagle.vct.tssg.impl.TSSGEntity;
import teagle.vct.tssg.impl.TSSGModelFactory;
import teagle.vct.tssg.impl.TSSGPtm;
import teagle.vct.tssg.impl.TSSGResourceInstance;
import teagle.vct.tssg.impl.TSSGResourceSpec;

/**
 *
 * @author kkoutso
 */
public class RepoAdapter {
    private static Log log = LogFactory.getLog(RepoAdapter.class);
    private static final String repourl = "http://localhost:8080/CoreRepository/rest";
    private static ModelManager repo;
    
    static 
    {
    	repo = TSSGModelFactory.getInstance();
    	try
		{
			RepoClientConfig config = new RepoClientConfig(new URL(repourl), "root", "r00t", true, true);
			repo.config(config);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
    }

  private static String persistIt(Entity o)
  {
	  return ((TSSGEntity)o).persist().getId();
  }

  public static String createResourceInstance(String commonname, ResourceSpec spec, String state, String[] cfgId)
  {
	  ResourceInstance ri = repo.createResourceInstance(spec);
	  
	  ri.setCommonName(commonname);
      ri.isShared(true);
      ri.setDescription("singleton "+commonname);

	  if (state.toLowerCase().startsWith("u"))
		  ri.setState(ResourceInstanceState.State.UNPROVISIONED);
	  else if (state.toLowerCase().startsWith("p"))
		  ri.setState(ResourceInstanceState.State.PROVISIONED);
	  else
		  throw new IllegalAccessError("Hardcoded ids?! SRSLY!");
	

        for(String id : cfgId)
        	ri.addConfiguration(TSSGConfiguration.find(id));
        
        return persistIt(ri); 
    }
  public static String createConfiglet( String value, String paramID)
    {
      ConfigParamAtomic cfa = getAtomic(paramID);

	  Configuration cl = repo.createConfiguration(cfa);

        cl.setCommonName(cfa.getCommonName());
        cl.setDescription(cfa.getDescription());
        cl.setValue(value);
        return ((TSSGConfiguration)cl.persist()).getId();

    }
  public static String updateResourceInstance(String commonName, String state)
    {
      ResourceInstance ri = getResourceInstance(commonName);
      if(ri == null)
          return null;

	  if (state.toLowerCase().startsWith("u"))
		  ri.setState(ResourceInstanceState.State.UNPROVISIONED);
	  else if (state.toLowerCase().startsWith("p"))
		  ri.setState(ResourceInstanceState.State.PROVISIONED);
	  else
		  throw new IllegalAccessError("Hardcoded ids?! SRSLY!");

	 return ((TSSGResourceInstance)ri.persist()).getId();
    }
 
    public static ResourceInstance getResourceInstance(String name)
    {
    	try
		{
			return repo.findResourceInstanceByName(name);
		}
    	catch (EntityNotFound e)
    	{
    		return null;
    	}
		catch (RepositoryException e)
		{
			log.error("Error getting ResourceInstane: " + name, e);
			return null;
		}
    }
  
    public static TSSGResourceSpec getResourceSpec(String type)
    {
    	return (TSSGResourceSpec)repo.getResourceSpec(type);
    }


    public static ConfigParamAtomic createAtomic(String commonName, String paramType, String defaultValue, String description)
    {
    	ConfigParamAtomic c = repo.createConfigParamAtomic();
    	
    	c.setCommonName(commonName);
    	c.setType(paramType);
    	c.setDescription(description);
    	c.setDefaultValue(defaultValue);

    	return (ConfigParamAtomic)c.persist();
    }
   public static String updateAtomic(int id, String commonName, String paramType, String defaultValue, String description)
    {
	   ConfigParamAtomic c = getAtomic(Integer.toString(id));

        c.setCommonName(commonName);
        c.setType(paramType);
        c.setDefaultValue(defaultValue);
        c.setDescription(description);

        return persistIt(c);
    }
    public static String createComposite(String commonName, String[] atomic, String description)
    {
    	ConfigParamComposite composite = repo.createConfigParamComposite();

    	composite.setCommonName(commonName);
    	composite.setDescription(description);
    	
    	for(String id : atomic)
    		composite.addConfigurationParameter(getAtomic(id));
    	
    	return persistIt(composite);

    }
    public static void updateComposite(ResourceSpec rs, String commonName, List<? extends ConfigParamAtomic> atomics, String description)
    {
    	rs.setConfigurationParameters(atomics);
    	rs.persist();
    }
    public static ConfigParamComposite getComposite(int id)
    {
    	return TSSGConfigParamComposite.find(Integer.toString(id));
    }

    public static ResourceSpec getResourceSpec(String type, String ptm_id)
    {
        log.info("getResourceSpec: " + type+"@"+ptm_id);
        Ptm ptm = getPtm(ptm_id);
        ResourceSpec rs = getResourceSpec(type);
        return ptm.supportsResourceSpec(rs) ? rs : null;
    }
    
    public static ConfigParamAtomic getAtomic(String id)
    {
    	return TSSGConfigParamAtomic.find(id);
    }

    public static Ptm getPtm( String ptm_id)
    {
    	return repo.getPtm(ptm_id);
    }

    public static URL getPtmUrl(String ptm_id)
    {
    	Ptm ptm = getPtm(ptm_id);
    	try
		{
			return new URL(ptm.getLegacyUrl());
		}
		catch (MalformedURLException e)
		{
			log.error("Illegal URL on PTM: " + ptm.getCommonName() + " - " + ptm.getUrl());
			return null;
		}
    }

    public static void updatePtmInfo(String id, ResourceSpec rs)
    {
    	Ptm ptm = repo.getPtm(id);        
        ptm.addResourceSpec(rs);
        ptm.persist();
    }
 
    public static ResourceSpec createResourceSpec(String type, List<? extends ConfigParamAtomic> atomics)
    {
    	ResourceSpec rs = repo.createResource();
        rs.setCommonName(type);
        rs.setDescription("Resource Specification created by Teagle GW");
        rs.setConfigurationParameters(atomics);
        return (ResourceSpec)rs.persist();
    }

    public static List<? extends Ptm> getPtms()
    {
    	return TSSGPtm.list();
    }
}
