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

import java.rmi.RemoteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.panlab.tgw.App;
import org.panlab.tgw.Notification;
import org.panlab.tgw.util.XMLUtil;

public class PtmIfaceTeagleSoapBindingImpl implements org.panlab.tgw.ptm.iface.Teagle{

    private static Log log = LogFactory.getLog(PtmIfaceTeagleSoapBindingImpl.class);

    public void notify(String resource_id, String event_type, String data, String Certificate) throws RemoteException
    {


        log.info(resource_id+" "+event_type+" "+data+" "+Certificate);
        if(event_type.equalsIgnoreCase(TYPE_ACTIVATION+""))
        {
            String ptm_id = App.ptm_certs_ind.get(Certificate);
            log.info(ptm_id);
            //(new ActivationThread(resource_id, data, ptm_id)).start();

        }
        else
        if(event_type.equalsIgnoreCase(TYPE_CONFIGURATION+""))
        {
            String ptm_id = App.ptm_certs_ind.get(Certificate);
            log.info(ptm_id);
            (new ActivationThread(resource_id, data, ptm_id)).start();

        }
        else
        if(event_type.equalsIgnoreCase(NEW_TYPE_EVENT+""))
        {
            String ptm_id = App.ptm_certs_ind.get(Certificate);
            log.info(ptm_id);
            (new TypeThread(resource_id, data, ptm_id)).start();

        }
        else
        if(event_type.equalsIgnoreCase(MOD_TYPE_EVENT+""))
        {
            String ptm_id = App.ptm_certs_ind.get(Certificate);
            log.info(ptm_id);
            (new ModTypeThread(resource_id, data, ptm_id)).start();

        }

        if(event_type.equalsIgnoreCase(ERROR_EVENT+""))
        {
            App.ra_status.put(resource_id, "ERROR");
        }
        if(event_type.equalsIgnoreCase(INFO_EVENT+""))
        {
            App.ra_status.remove(resource_id);
        }

    }

    public void callback(String status_code, String request_id, String config_data, String Certificate) throws RemoteException
    {
        Notification notification = App.async_reqs.get(request_id);
        if(notification!=null)
        {
            String cert = App.ptm_certs.get(notification.m_ptm);
            if(cert!=null)
            {
                if(cert.equalsIgnoreCase(Certificate))
                {
                    String identifier = XMLUtil.getXMLElement(status_code, "identifier");
                    notification.m_uuid = identifier;
                    System.out.println(notification.toString());
                    notification.processReferences();
                    App.async_reqs.remove(request_id);
                }
                else
                    System.out.println("Callback from PTM with incorrect certificate");
            }
            else
                System.out.println("Callback from unregistered PTM");
        }
        else
            System.out.println("NO SUCH REQ_ID");
    }
    public static final int RA_CONNECTIVITY = 10000;
    public static final int RA_REGISTRATION = 1000;
    public static final int TYPE_ACTIVATION = 1001;
    public static final int TYPE_CONFIGURATION = 1002;
    public static final int TYPE_AVAILABILITY = 1003;
    public static final int TYPE_REGISTRATION_SELF = 1004;
    public static final int TYPE_REGISTRATION_NONSELF = 1005;
    public static final int RAID_ASSIGNMENT = 1006;
    public static final int TYPE_REMOVAL = 1007;
    public static final int RAID_REMOVAL = 1008;

    public static final int INFO_EVENT = 999;
    public static final int ERROR_EVENT = 998;
    public static final int NEW_TYPE_EVENT = 997;
    public static final int MOD_TYPE_EVENT = 996;
    public static final int DEL_TYPE_EVENT = 995;
    public static final int DEL_RAID_EVENT = 994;
}
