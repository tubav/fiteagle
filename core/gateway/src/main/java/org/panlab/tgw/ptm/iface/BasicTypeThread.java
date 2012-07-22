package org.panlab.tgw.ptm.iface;

import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.panlab.tgw.restclient.RepoAdapter;
import org.panlab.tgw.util.XMLElement;
import org.panlab.tgw.util.XMLUtil;

import teagle.vct.model.ConfigParamAtomic;
import teagle.vct.model.ResourceSpec;

public class BasicTypeThread extends Thread
{

	protected static Log log = LogFactory.getLog(ModTypeThread.class);
	protected String m_type;
	protected String m_conf;
	protected final String m_ptm_id;

    public BasicTypeThread(String resource_type, String data, String ptm_id)
    {
        m_type = resource_type;
        m_conf = data;
        m_ptm_id = ptm_id;
    }

	protected ArrayList<ConfigParamAtomic> createAtomics(ResourceSpec rs)
	{
		Hashtable<String, ConfigParamAtomic> cpaHash = new Hashtable<String, ConfigParamAtomic>();
		for(ConfigParamAtomic tempCPA :  rs.getConfigurationParameters())
		{
		    log.info("    "+tempCPA.getCommonName());
		    log.info("    "+tempCPA.getType());
		    log.info("    "+tempCPA.getDescription());
		    cpaHash.put(tempCPA.getCommonName(), tempCPA);
		}
	
		log.info("<Creating Atomic(s) for type"+m_type+">");
		Object[] elems= XMLUtil.getElements(m_conf);
		ArrayList<ConfigParamAtomic> atomics = new ArrayList<ConfigParamAtomic>();
		for(int i=0; i< elems.length; i++)
		{
		    XMLElement elem = (XMLElement) elems[i];
		    ConfigParamAtomic newAtomic;
		    if(!cpaHash.containsKey(elem.m_name))
		        newAtomic = RepoAdapter.createAtomic(elem.m_name, elem.m_attributes.get("type").replace("\"",""), "","Atomic Parameter Created by Teagle Gateway");
		    else
		    {
		        newAtomic = cpaHash.get(elem.m_name);
		        newAtomic.setType(elem.m_attributes.get("type").replace("\"",""));
		    }
		    atomics.add(newAtomic);
		}
		log.info("</Creating Atomic(s) for type"+m_type+">");
		return atomics;
	}
}