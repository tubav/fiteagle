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

@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class TeagleServiceLocator extends org.apache.axis.client.Service implements org.panlab.tgw.ptm.iface.TeagleService {

    public TeagleServiceLocator() {
    }


    public TeagleServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TeagleServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PtmIfaceTeagle
    private java.lang.String PtmIfaceTeagle_address = "http://localhost:8080/axis/services/ptm.iface.Teagle";

    public java.lang.String getPtmIfaceTeagleAddress() {
        return PtmIfaceTeagle_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PtmIfaceTeagleWSDDServiceName = "ptm.iface.Teagle";

    public java.lang.String getPtmIfaceTeagleWSDDServiceName() {
        return PtmIfaceTeagleWSDDServiceName;
    }

    public void setPtmIfaceTeagleWSDDServiceName(java.lang.String name) {
        PtmIfaceTeagleWSDDServiceName = name;
    }

    public org.panlab.tgw.ptm.iface.Teagle getPtmIfaceTeagle() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PtmIfaceTeagle_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPtmIfaceTeagle(endpoint);
    }

    public org.panlab.tgw.ptm.iface.Teagle getPtmIfaceTeagle(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.panlab.tgw.ptm.iface.PtmIfaceTeagleSoapBindingStub _stub = new org.panlab.tgw.ptm.iface.PtmIfaceTeagleSoapBindingStub(portAddress, this);
            _stub.setPortName(getPtmIfaceTeagleWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPtmIfaceTeagleEndpointAddress(java.lang.String address) {
        PtmIfaceTeagle_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.panlab.tgw.ptm.iface.Teagle.class.isAssignableFrom(serviceEndpointInterface)) {
                org.panlab.tgw.ptm.iface.PtmIfaceTeagleSoapBindingStub _stub = new org.panlab.tgw.ptm.iface.PtmIfaceTeagleSoapBindingStub(new java.net.URL(PtmIfaceTeagle_address), this);
                _stub.setPortName(getPtmIfaceTeagleWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ptm.iface.Teagle".equals(inputPortName)) {
            return getPtmIfaceTeagle();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:iface.ptm.teaglegw.panlab.org", "TeagleService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:iface.ptm.teaglegw.panlab.org", "org.panlab.teaglegw.ptm.iface.Teagle"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PtmIfaceTeagle".equals(portName)) {
            setPtmIfaceTeagleEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
