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

/**
 *
 * @author kkoutso
 * 
 */
/*
// The Java class will be hosted at the URI path "/dummy"
@Path("/{ptmid}/{resourceid}")
public class PTMResourceNoError 
{
    static int classIndex=0;
    int j=0;

    public PTMResourceNoError()
    {
        j = classIndex++;
    }

    public static String createError(String errorInfo, String ra_id, String type)
    {
        return XMLUtil.xmlHeader
           +"<"
           +type+" status=\"FAIL\" code=\"410\"><identifier type=\"string\">"
           +ra_id+"</identifier><info>"
           +errorInfo+"</info></"
           +type+">";
    }
    public static String createOK(String ra_id, String type)
    {
        return XMLUtil.xmlHeader
           +"<"
           +type+" status=\"SUCCESS\">"+
           (ra_id!=null?"<identifier type=\"string\">"+ra_id+"</identifier>":"")+
           "</"+type+">";
    }
    private String createOK(String ra_id, String type, String request_id)
    {
        return XMLUtil.xmlHeader
           +"<"
           +type+" status=\"SUCCESS\"  requestId=\""+request_id+"\">"+
           (ra_id!=null?"<identifier type=\"string\">"+ra_id+"</identifier>":"")+
           "</"+type+">";
    }
    // The Java method will process HTTP GET requests
    @GET    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    @Produces("text/xml")
    //@Consumes("text/xml")
    public String getResource(
            @PathParam("ptmid") String ptmid,
            @PathParam("resourceid") String resourceid
            )
    {
        System.out.println("GET ("+j+") /"+ptmid+"/"+resourceid);//+content);
        String type = resourceid.substring(resourceid.lastIndexOf(".")+1,resourceid.lastIndexOf("-"));
        try
        {
            T1ServiceLocator l = new T1ServiceLocator();
            T1SoapBindingStub stub = (T1SoapBindingStub)(l.getT1(RepoAdapter.getPtmUrl(ptmid)));
            ProvisioningResponse res = stub.query("default_vct",resourceid, "<get></get>",null);
            System.out.println(res.getConfig_data());
            return res.getConfig_data();

        }
        catch (Exception error)
        {
            error.printStackTrace();
            return createError
               (
                error.getMessage(),
                resourceid,
                type
               );
        }
    }


}    
*/
