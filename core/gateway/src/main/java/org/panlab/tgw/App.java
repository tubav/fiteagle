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

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;
//import org.apache.axis.transport.http.JettyAxisServer;


public class App
{
    //public static Hashtable<String, URL> ptm_indexes = new Hashtable<String, URL>();
    public static Hashtable<String, String> ra_status = new Hashtable<String, String>();
    public static Hashtable<String, String> ptm_certs = new Hashtable<String, String>();
    public static Hashtable<String, String> ptm_certs_ind = new Hashtable<String, String>();
    public static Hashtable<String, Integer> ptm_status = new Hashtable<String, Integer>();
    public static Hashtable<String, Notification> async_reqs = new Hashtable<String, Notification>();
    private static ConfParser m_confParser;

    static Integer OK = 0;
    static Integer SSL_PROBLEM = 9;
    static Integer PTM_OFFLINE = 10;
    static Integer RAL_OFFLINE = 11;
    static Integer RAL_MANAGER_OFFLINE = 12;
    static boolean DEBUG = true;

    private static Log log = LogFactory.getLog(App.class);
    public static boolean CIRCULAR_REFERENCE = false;

    public static Integer getStatus(String ptmid)
    {
        if(ptm_status.containsKey(ptmid))
            return ptm_status.get(ptmid);
        else
            return new Integer(-1);
    }
    /*
    public static Integer getRAStatus(String RAid)
    {
        if(ra_status.containsKey(RAid))
            return new Integer(1);
        else
            return new Integer(0);
    }
    */
    
    public static String statusText(Integer status)
    {
        if(status==null)
            return "Status not initialised yet";

        if(status==-1)
            return "PTM not registered";
        return m_confParser.getReason(status);
    }
    public static void main(String[] args)
    {
        try
        {
            InputStream is = App.class.getResourceAsStream("/deploy.wsdd");
            int wsddLength = is.available();
            m_confParser = new ConfParser();
            m_confParser.start();


            byte[] wsdd = new byte[wsddLength];
            is.read(wsdd);
            is.close();
            System.out.println("***************************");
            //System.exit(0);

            FileOutputStream fos = new FileOutputStream("wsdd");
            fos.write(wsdd);
            fos.close();

            //JettyAxisServer.main(new String[0]);
            log.info("Jetty Started");
            //AdminClient.main(new String[]{"-lhttps://localhost:8070/axis/servlet/AxisServlet","wsdd"});

            final String baseUri_0 = "http://0.0.0.0:9001/teaglegw/";
            final Map<String, String> initParams = new HashMap<String, String>();

            initParams.put("com.sun.jersey.config.property.packages",
               "org.panlab.tgw.resources");

            log.info("Starting grizzly...");
            SelectorThread threadSelector_0 = GrizzlyWebContainerFactory.create(baseUri_0, initParams);

            byte[] command = new byte[10];
            do
            {
                System.in.read(command,0,5);
                System.out.println("#"+new String(command,0,4)+"#");
                if(((new String(command,0,4)).equalsIgnoreCase("deon")))
                   DEBUG=true;
                if(((new String(command,0,4)).equalsIgnoreCase("doff")))
                   DEBUG=false;
                if(((new String(command,0,4)).equalsIgnoreCase("ref0")))
                   CIRCULAR_REFERENCE=false;
                if(((new String(command,0,4)).equalsIgnoreCase("ref1")))
                   CIRCULAR_REFERENCE=true;

            }
            while(!((new String(command,0,4)).equalsIgnoreCase("exit")));

                threadSelector_0.stopEndpoint();
            System.exit(0);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
