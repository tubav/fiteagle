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

@SuppressWarnings({ "rawtypes", "serial", "unused", "unchecked" })
public class PtmIfaceTeagleSoapBindingSkeleton implements org.panlab.tgw.ptm.iface.Teagle, org.apache.axis.wsdl.Skeleton {
    private org.panlab.tgw.ptm.iface.Teagle impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
	private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "resource_id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false),
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "event_type"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false),
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "data"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false),
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Certificate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false),
        };
        _oper = new org.apache.axis.description.OperationDesc("notify", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("urn:iface.ptm.tgw.panlab.org", "notify"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("notify") == null) {
            _myOperations.put("notify", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("notify")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "status_code"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false),
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request_id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false),
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "config_data"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false),
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Certificate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false),
        };
        _oper = new org.apache.axis.description.OperationDesc("callback", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("urn:iface.ptm.tgw.panlab.org", "callback"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("callback") == null) {
            _myOperations.put("callback", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("callback")).add(_oper);
    }

    public PtmIfaceTeagleSoapBindingSkeleton() {
        this.impl = new org.panlab.tgw.ptm.iface.PtmIfaceTeagleSoapBindingImpl();
    }

    public PtmIfaceTeagleSoapBindingSkeleton(org.panlab.tgw.ptm.iface.Teagle impl) {
        this.impl = impl;
    }
    public void notify(java.lang.String resource_id, java.lang.String event_type, java.lang.String data, java.lang.String Certificate) throws java.rmi.RemoteException
    {
        impl.notify(resource_id, event_type, data, Certificate);
    }

    public void callback(java.lang.String status_code, java.lang.String request_id, java.lang.String config_data, java.lang.String Certificate) throws java.rmi.RemoteException
    {
        impl.callback(status_code, request_id, config_data, Certificate);
    }

}
