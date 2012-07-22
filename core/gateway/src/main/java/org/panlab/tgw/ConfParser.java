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

import java.io.IOException;
import java.net.URL;

import javax.xml.rpc.ServiceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.panlab.ptm.t1.T1ServiceLocator;
import org.panlab.ptm.t1.T1SoapBindingStub;
import org.panlab.ptm.t1.types.ProvisioningResponse;
import org.panlab.tgw.restclient.RepoAdapter;

import teagle.vct.model.Ptm;

/**
 *
 * @author kkoutso
 */
class ConfParser extends Thread
{
    private static Log log = LogFactory.getLog(ConfParser.class);

    public ConfParser()
    {
    }

    public void run()
    {
        while(true)
        {
            try
            {
            	for (Ptm ptm : RepoAdapter.getPtms())
                {
                    //log.info(element.m_name + " "+ attributeValue.replace("\"","") +" "+element.m_value);
                   // App.ptm_indexes.put(element.m_name, new URL(element.m_value));
                    //App.ptm_certs.put(element.m_name, attributeValue.replace("\"",""));
                   // App.ptm_certs_ind.put(attributeValue.replace("\"",""), element.m_name);
                    //log.info(element.m_name+"@"+attributeValue.replace("\"",""));
                    T1ServiceLocator l = new T1ServiceLocator();
                    T1SoapBindingStub stub;
                    String ptmUrl = ptm.getLegacyUrl();
                    stub = (T1SoapBindingStub) (l.getT1(new URL(ptmUrl)));
                    ProvisioningResponse ref;
                    String current_ptm = ptm.getCommonName();
                    log.info("Testing PTM status: " + current_ptm + " (" + ptmUrl + ")");
                    try
                    {
                        ref = stub.query("status_check", ptm.getCommonName()+".top-0", "<status></status>", null);
                        //log.info(ref.getConfig_data());
                        if(ref.getConfig_data().contains("top-0"))
                        {
                            App.ptm_status.put(ptm.getCommonName(), App.OK);
                            log.info("PTM " + current_ptm + " OK.");
                        }
                        else
                        	log.warn("Illegal answer for status_check from " + current_ptm + " (" + ptmUrl + "): " + ref.getConfig_data());
                    }
                    catch(org.apache.axis.AxisFault ex)
                    {
                       log.warn("======================"+current_ptm+"======================");
                       log.warn("Status check failure for PTM " + current_ptm + " (" + ptmUrl + ")");

                        if(ex.getFaultReason().startsWith("java.net.ConnectException:"))
                            App.ptm_status.put(current_ptm, App.PTM_OFFLINE);
                        else
                        if(ex.getFaultReason().equalsIgnoreCase("java.net.NoRouteToHostException: No route to host"))
                            App.ptm_status.put(current_ptm, App.PTM_OFFLINE);
                        else
                        if(ex.getFaultReason().contains("java.net.ConnectException: Connection refused"))
                            App.ptm_status.put(current_ptm, App.RAL_OFFLINE);
                        else
                        if(ex.getFaultReason().contains("ralmanager"))
                            App.ptm_status.put(current_ptm, App.RAL_MANAGER_OFFLINE);
                        else
                        if(ex.getFaultReason().contains("SSLHandshakeException"))
                            App.ptm_status.put(current_ptm, App.SSL_PROBLEM);
                        else
                        {
                        	log.warn("Unknown error, stacktrace follows", ex);
                            App.ptm_status.put(current_ptm, App.PTM_OFFLINE);
                            //log.info("Reason: "+ex.getFaultReason());
                            //log.info("String: "+ex.getFaultString());
                            
                        }
                        log.warn(ex);
                        log.warn("======================"+current_ptm+"======================");
                    }
                    //log.info(current_ptm+" "+ getReason(App.ptm_status.get(current_ptm)));

                }
         
                sleep(10000);
            }

            catch (ServiceException ex)
            {
                ex.printStackTrace();
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
            catch(IOException ex)
            {

                log.info("Configuration file error"+ ex.getClass().getName());
                ex.printStackTrace();
            }
        }
    }

    public static String getReason(Integer get)
    {
        if(get==null)
            return "invalid";
        switch(get.intValue())
        {
            case 0: return "OK";
            case 9: return "SSL Problem";
            case 10: return "PTM - Offline";
            case 11: return "RAL - Offline";
            case 12: return "RAL Manager Offline";
            default: return "---";
        }
    }

}
