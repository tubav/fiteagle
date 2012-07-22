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

package org.panlab.tgw;

import java.util.Vector;

import org.panlab.ptm.t1.T1ServiceLocator;
import org.panlab.ptm.t1.T1SoapBindingStub;
import org.panlab.ptm.t1.types.ProvisioningResponse;
import org.panlab.tgw.restclient.RepoAdapter;

/**
 *
 * @author kkoutso
 */
public class Notification
{

    public String m_reqid;
    public String m_component;
    public String m_event;
    public String m_vctid;
    public String m_status;
    public String m_reason;
    public String m_details;
    public String m_uuid;
    public String m_ptm;
    private String m_type;
    private Vector<String> referencedRAIDs = new Vector<String>();

    public Notification(String requestid, String component, String event,
       String vctid, String status, String reason, String details, String ptm_id,
       String type, Vector<String> refs)
    {
        m_reqid = requestid;
        m_component = component;
        m_event = event;
        m_vctid = vctid;
        m_status = status;
        m_reason = reason;
        m_details = details;
        m_ptm = ptm_id;
        m_type = type;
        referencedRAIDs = refs;
    }

    public void processReferences()
    {
        try
        {
            int referencedSize = referencedRAIDs.size();
            if (referencedSize > 0)
            {
                T1ServiceLocator l = new T1ServiceLocator();
                T1SoapBindingStub stub = (T1SoapBindingStub) (l.getT1(RepoAdapter.getPtmUrl(m_ptm)));
                ProvisioningResponse ref;
                ref = stub.query(m_vctid, m_uuid, "<reference></reference>", null);
                String conf = "<" + m_type + ">" + ref.getConfig_data() + "</" + m_type + ">";
                System.out.println("The following data have to be sent to all referenced resources by " + m_uuid + " :" + conf);
                for (int k = 0; k < referencedSize; k++)
                {
                    String temp = referencedRAIDs.remove(0);
                    if (temp != null)
                    {
                        System.out.println("Updating " + temp);
                        System.out.println(conf);
                        String ptm = temp.substring(0, temp.indexOf("."));
                        stub = (T1SoapBindingStub) (l.getT1(RepoAdapter.getPtmUrl(m_ptm)));
                        ProvisioningResponse update;
                        update = stub.update(m_vctid, temp, conf, null);
                        System.out.println("Updated " + temp + " :" + update.getStatus_code());
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public String toString()
    {
        return "<notification requestId=\"" + m_reqid + "\">" +
           "<uuid>" + m_uuid + "</uuid>" +
           "<component>" + m_component + "</component>" +
           "<event>" + m_event + "</event>" +
           "<vctId>" + m_vctid + "</vctId>" +
           "<status>" + m_status + "</status>" +
           "<reason>" + m_reason + "</reason>" +
           "<details>" + m_details + "</details>" +
           "</notification>";
    }
}

