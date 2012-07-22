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

package org.panlab.tgw.resources;
import java.io.FileInputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author kkoutso
 * 
 */
// The Java class will be hosted at the URI path "/dummy"
@Path("/{log}")
public class TeagleGWLog 
{
    static int classIndex=0;
    int j=0;

    public TeagleGWLog()
    {
        j = classIndex++;
    }

    // The Java method will process HTTP GET requests
    @GET    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    @Produces("text/plain")
    //@Consumes("text/xml")
    public String getResource(@PathParam("log") String log)
    {
        try
        {
            String ret = "TEAGLE GATEWAY LOG ";
            FileInputStream fis = new FileInputStream("tgw.log");
            while(fis.available()>0)
            {
                byte[] bArray = new byte[fis.available()];
                fis.read(bArray);
                ret += new String(bArray);

            }
            fis.close();
            return "\n"+ret;
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }


    }


}    

