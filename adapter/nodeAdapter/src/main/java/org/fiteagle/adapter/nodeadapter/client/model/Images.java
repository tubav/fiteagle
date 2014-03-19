package org.fiteagle.adapter.nodeadapter.client.model;

/*
 * #%L
 * FITeagle
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2012 - 2013 Architektur der Vermittlungsknoten, TU-Berlin
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

/**
 * this class is a copy of com.woorea.openstack.nova.model.Images class from woorea client, which has small extensions.
 */

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)//changed
public class Images implements Iterable<Image>, Serializable {

	@JsonProperty("images")
	private List<Image> list;

	/**
	 * @return the list
	 */
	public List<Image> getList() {
		return list;
	}
	
	@Override
	public Iterator<Image> iterator() {
		return list.iterator();
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Images [list=" + list + "]";
	}

}

