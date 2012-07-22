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

package org.panlab.ptm.t1;

@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class T1ServiceLocator extends org.apache.axis.client.Service implements org.panlab.ptm.t1.T1Service {

    public T1ServiceLocator() {
    }


    public T1ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public T1ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for T1
    private java.lang.String T1_address = "http://127.0.0.1:9080/axis/services/T1";

    public java.lang.String getT1Address() {
        return T1_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String T1WSDDServiceName = "T1";

    public java.lang.String getT1WSDDServiceName() {
        return T1WSDDServiceName;
    }

    public void setT1WSDDServiceName(java.lang.String name) {
        T1WSDDServiceName = name;
    }

    public org.panlab.ptm.t1.T1 getT1() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(T1_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getT1(endpoint);
    }

    public org.panlab.ptm.t1.T1 getT1(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.panlab.ptm.t1.T1SoapBindingStub _stub = new org.panlab.ptm.t1.T1SoapBindingStub(portAddress, this);
            _stub.setPortName(getT1WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setT1EndpointAddress(java.lang.String address) {
        T1_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.panlab.ptm.t1.T1.class.isAssignableFrom(serviceEndpointInterface)) {
                org.panlab.ptm.t1.T1SoapBindingStub _stub = new org.panlab.ptm.t1.T1SoapBindingStub(new java.net.URL(T1_address), this);
                _stub.setPortName(getT1WSDDServiceName());
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
        if ("T1".equals(inputPortName)) {
            return getT1();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://t1.ptm.panlab.org", "T1Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://t1.ptm.panlab.org", "T1"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("T1".equals(portName)) {
            setT1EndpointAddress(address);
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
