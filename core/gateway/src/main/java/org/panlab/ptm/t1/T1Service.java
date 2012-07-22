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

public interface T1Service extends javax.xml.rpc.Service {
    public java.lang.String getT1Address();

    public org.panlab.ptm.t1.T1 getT1() throws javax.xml.rpc.ServiceException;

    public org.panlab.ptm.t1.T1 getT1(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
