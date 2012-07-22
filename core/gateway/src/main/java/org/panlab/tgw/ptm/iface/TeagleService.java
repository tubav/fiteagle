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

public interface TeagleService extends javax.xml.rpc.Service {
    public java.lang.String getPtmIfaceTeagleAddress();

    public org.panlab.tgw.ptm.iface.Teagle getPtmIfaceTeagle() throws javax.xml.rpc.ServiceException;

    public org.panlab.tgw.ptm.iface.Teagle getPtmIfaceTeagle(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
