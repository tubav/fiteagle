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

import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author kkoutso
 */
public class XMLElement 
{
    public String m_name;
    public Hashtable<String,String> m_attributes = new Hashtable<String,String>();
    public String m_attribute;
    public String m_attributeValue;
    public String m_value;
    public XMLElement(String name, String attribute, String attributeValue, String value)
    {
        m_name = name;
        m_attribute = attribute;
        m_attributeValue = attributeValue;
        if(attribute!=null)
            m_attributes.put(attribute, attributeValue);
        m_value = value;
    }
    public void addAttribute(String attribute, String attributeValue)
    {
        if(attribute!=null)
            m_attributes.put(attribute, attributeValue);
    }
    public String toString()
    {
        String temp = "";
        if(m_name!=null)
        {
            temp+="<"+m_name;
            Enumeration<String> keys =  m_attributes.keys();
            while(keys.hasMoreElements())
            {
                String key = keys.nextElement();
                temp+=" "+key;
                temp+="="+m_attributes.get(key);
            }
            if(m_value==null)
                temp+="/";
            temp+=">";
                
            if(m_value!=null)
            {
                temp+=m_value;
                temp+="</"+m_name+">";
            }
            
        }
        return temp;   
        
    }

}
