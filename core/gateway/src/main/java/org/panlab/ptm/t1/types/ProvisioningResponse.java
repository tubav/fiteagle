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

package org.panlab.ptm.t1.types;

@SuppressWarnings({ "serial", "unused" })
public class ProvisioningResponse  implements java.io.Serializable {
    private java.lang.String status_code;

    private java.lang.String request_id;

    private java.lang.String config_data;

    public ProvisioningResponse() {
    }

    public ProvisioningResponse(
           java.lang.String status_code,
           java.lang.String request_id,
           java.lang.String config_data) {
           this.status_code = status_code;
           this.request_id = request_id;
           this.config_data = config_data;
    }


    /**
     * Gets the status_code value for this ProvisioningResponse.
     * 
     * @return status_code
     */
    public java.lang.String getStatus_code() {
        return status_code;
    }


    /**
     * Sets the status_code value for this ProvisioningResponse.
     * 
     * @param status_code
     */
    public void setStatus_code(java.lang.String status_code) {
        this.status_code = status_code;
    }


    /**
     * Gets the request_id value for this ProvisioningResponse.
     * 
     * @return request_id
     */
    public java.lang.String getRequest_id() {
        return request_id;
    }


    /**
     * Sets the request_id value for this ProvisioningResponse.
     * 
     * @param request_id
     */
    public void setRequest_id(java.lang.String request_id) {
        this.request_id = request_id;
    }


    /**
     * Gets the config_data value for this ProvisioningResponse.
     * 
     * @return config_data
     */
    public java.lang.String getConfig_data() {
        return config_data;
    }


    /**
     * Sets the config_data value for this ProvisioningResponse.
     * 
     * @param config_data
     */
    public void setConfig_data(java.lang.String config_data) {
        this.config_data = config_data;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProvisioningResponse)) return false;
        ProvisioningResponse other = (ProvisioningResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.status_code==null && other.getStatus_code()==null) || 
             (this.status_code!=null &&
              this.status_code.equals(other.getStatus_code()))) &&
            ((this.request_id==null && other.getRequest_id()==null) || 
             (this.request_id!=null &&
              this.request_id.equals(other.getRequest_id()))) &&
            ((this.config_data==null && other.getConfig_data()==null) || 
             (this.config_data!=null &&
              this.config_data.equals(other.getConfig_data())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getStatus_code() != null) {
            _hashCode += getStatus_code().hashCode();
        }
        if (getRequest_id() != null) {
            _hashCode += getRequest_id().hashCode();
        }
        if (getConfig_data() != null) {
            _hashCode += getConfig_data().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
