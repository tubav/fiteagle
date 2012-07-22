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

package org.panlab.tgw.ptm.iface;
import java.util.List;

import org.panlab.tgw.restclient.RepoAdapter;

import teagle.vct.model.ConfigParamAtomic;
import teagle.vct.model.ResourceSpec;
/**
 *
 * @author kkoutso
 */
class TypeThread  extends BasicTypeThread
{
    public TypeThread(String resource_type, String data, String ptm_id)
    {
    	super(resource_type, data, ptm_id);
    }

    public void run()
    {
        try
        {
            //sleep(Integer.parseInt(System.getProperty("singleton.thread")));
            ResourceSpec rs = RepoAdapter.getResourceSpec(m_type, m_ptm_id);
            if(rs!=null)
            {
                log.info("The following Spec is already in the Repository for ptm: "+m_ptm_id);
                log.info(rs.getCommonName());
                log.info(rs.getDescription());
                //log.info(RepoAdapter.createResourceInstance(m_id,rs.getId(),"9"));
            }
            else
            {
                log.info(m_type + " spec not found");
                List<? extends ConfigParamAtomic> atomics = createAtomics(rs);

                log.info("<Creating Resource Spec for type"+m_type+">");
                rs = RepoAdapter.createResourceSpec(m_type, atomics);
                log.info("</Creating Resource Spec for type"+m_type+">");

                log.info("<Updating PTM Info with new type>");
                RepoAdapter.updatePtmInfo(m_ptm_id, rs);
                log.info("</Updating PTM Info with new type"+m_type+">");
            }
            



        }
        catch(Exception error)
        {
            log.error(error.getMessage());
            error.printStackTrace();
        }
    }

}
