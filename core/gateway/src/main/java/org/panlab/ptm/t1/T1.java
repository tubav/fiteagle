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

public interface T1 extends java.rmi.Remote {
    public org.panlab.ptm.t1.types.ProvisioningResponse delete(java.lang.String vct_id, java.lang.String resource_id, java.lang.String config_data, java.lang.String callback) throws java.rmi.RemoteException;
    public org.panlab.ptm.t1.types.ProvisioningResponse create(java.lang.String vct_id, java.lang.String resource_id, java.lang.String config_data, java.lang.String callback) throws java.rmi.RemoteException;
    public org.panlab.ptm.t1.types.ProvisioningResponse query(java.lang.String vct_id, java.lang.String resource_id, java.lang.String config_data, java.lang.String callback) throws java.rmi.RemoteException;
    public org.panlab.ptm.t1.types.ProvisioningResponse update(java.lang.String vct_id, java.lang.String resource_id, java.lang.String config_data, java.lang.String callback) throws java.rmi.RemoteException;
}
