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


package org.panlab.tgw.util;

import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author kkoutso
 */
public class XMLUtil 
{
    public static final String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    public static String getXMLElement(String document, String element)
    {
        try
        {
            int index = document.indexOf("<"+element);
            String response  = document.substring( document.indexOf(">",index)+1);
            response = response.substring(0, response.indexOf("</"+element+">"));  
            response = response.trim();
            return response;
        }
        catch(Exception error)
        {
            error.printStackTrace();
            return "";
        }
    }
    public static XMLElement getTopElement(String document)
    {
        try
        {
            String response=null;
            String element=null;
            String attribute=null;
            String attributeValue=null;
            String value=null;
            if(document.indexOf("?>")!=-1)
                response = document.substring(document.indexOf("?>")+2);
            else
                response = document;
            response = response.substring(response.indexOf('<')+1, response.indexOf('>'));
            if(response.contains(" "))
            {
                StringTokenizer temptok = new StringTokenizer(response," ");
                //System.out.println(temp);
                String name = temptok.nextToken();
                String attr = temptok.nextToken();
                String attrValue=null;
                XMLElement tempElement = new XMLElement(name,null,null,null);
                if(attr.contains("="))
                {
                    while(attr.contains("="))
                    {
                        attrValue = attr.substring(attr.indexOf("=")+1);
                        attr = attr.substring(0,attr.indexOf("="));
                        if(
                            (attrValue.indexOf(',')!=-1) &&
                            (attrValue.indexOf('\"')!=-1) &&
                           (
                            (attrValue.indexOf('\"'))==(attrValue.lastIndexOf('\"'))
                           )
                          )
                        {
                            while((attrValue.indexOf('\"'))==(attrValue.lastIndexOf('\"')))
                               attrValue+=" "+temptok.nextToken();
                        }
                        tempElement.addAttribute(attr, attrValue);
                        System.out.println(attr+":"+attrValue);
                        if(temptok.hasMoreTokens())
                            attr = temptok.nextToken();
                        else
                            attr="";
                    }
                }
                value = getXMLElement(document, name);
                value = value.trim();
                tempElement.m_value=value;
                return tempElement;
            }
            else
            {
                element = response;
                element = element.trim();
                value = getXMLElement(document, element);
                value = value.trim();
                return new XMLElement(element, attribute, attributeValue, value);
            }
        }
        catch(Exception error)
        {
            error.printStackTrace();
        }
        return null;
           
    }
    public static Object[] getElements(String document)
    {
        XMLElement top = getTopElement(document);
        if(top!=null)
        //System.out.println(top.m_name);
        //System.out.println(top.m_value);
        //System.out.println(top.toString());
        
        document = top.m_value;
        
        document = document.replace("</", " ");
        document = document.replace("/>", " ");
        document = document.replace(">", " ");
        //System.out.println(document);
        StringTokenizer strtok = new StringTokenizer(document,"<");
        Vector elements = new Vector();
        while(strtok.hasMoreTokens())
        {
            String temp =  strtok.nextToken();
            temp = temp.trim();
            if(temp.contains(" "))
            {
                StringTokenizer temptok = new StringTokenizer(temp," ");
                //System.out.println(temp);
                String name = temptok.nextToken();
                String attr = temptok.nextToken();
                String attrValue=null;
                String value;
                XMLElement tempElement = new XMLElement(name,null,null,null);
                if(attr.contains("="))
                {
                    while(attr.contains("="))
                    {
                        attrValue = attr.substring(attr.indexOf("=")+1);
                        attr = attr.substring(0,attr.indexOf("="));
                        if(
                            (attrValue.indexOf(',')!=-1) &&
                            (attrValue.indexOf('\"')!=-1) &&
                           (
                            (attrValue.indexOf('\"'))==(attrValue.lastIndexOf('\"'))
                           )
                          )
                        {
                            while((attrValue.indexOf('\"'))==(attrValue.lastIndexOf('\"')))
                               attrValue+=" "+temptok.nextToken();
                        }
                        tempElement.addAttribute(attr, attrValue);
                        if(temptok.hasMoreTokens())
                            attr = temptok.nextToken();
                        else
                            attr="";
                    }
                    value = attr;
                    tempElement.m_value=value;
                }
                else
                {
                    value = attr;
                    tempElement.m_value=value;
                    attr = null;
                }
                
                elements.add(tempElement);
                
            }
            else
                elements.add(new XMLElement(temp,null,null,null));
        }
          
           
//        int index = 0;
//        do
//        {
//            index = document.indexOf('<');
//            if(index!=-1 && document.indexOf('>', index));
//            
//        }
//        while(index!=document.length());
        return elements.toArray();
        
    }

}
