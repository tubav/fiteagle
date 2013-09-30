package org.fiteagle.adapter.openstackvmadapter.client.model;

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
 * this class is a copy of com.woorea.openstack.nova.modelImage class from woorea client, which has small extensions.
 */
import java.io.Serializable;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

import com.woorea.openstack.nova.model.Link;
@JsonIgnoreProperties(ignoreUnknown = true) // changed
@JsonRootName("image")
public class Image implements Serializable {

	private String id;

	private String status;

	private String name;

	private Integer progress;

	private Integer minRam;

	private Integer minDisk;

	private Calendar created;

	private Calendar updated;

	@JsonProperty("OS-EXT-IMG-SIZE:size")
	private Long size;

	private Map<String, String> metadata;

	private List<Link> links;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the progress
	 */
	public Integer getProgress() {
		return progress;
	}

	/**
	 * @param progress
	 *            the progress to set
	 */
	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	/**
	 * @return the minRam
	 */
	public Integer getMinRam() {
		return minRam;
	}

	/**
	 * @param minRam
	 *            the minRam to set
	 */
	public void setMinRam(Integer minRam) {
		this.minRam = minRam;
	}

	/**
	 * @return the minDisk
	 */
	public Integer getMinDisk() {
		return minDisk;
	}

	/**
	 * @param minDisk
	 *            the minDisk to set
	 */
	public void setMinDisk(Integer minDisk) {
		this.minDisk = minDisk;
	}

	/**
	 * @return the created
	 */
	public Calendar getCreated() {
		return created;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Calendar created) {
		this.created = created;
	}

	/**
	 * @return the updated
	 */
	public Calendar getUpdated() {
		return updated;
	}

	/**
	 * @param updated
	 *            the updated to set
	 */
	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}

	/**
	 * @return the metadata
	 */
	public Map<String, String> getMetadata() {
		return metadata;
	}

	/**
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * @param metadata
	 *            the metadata to set
	 */
	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	/**
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * @param links
	 *            the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Image [id=" + id + ", status=" + status + ", name=" + name
				+ ", progress=" + progress + ", minRam=" + minRam
				+ ", minDisk=" + minDisk + ", created="
				+ (created != null ? created.getTime() : null) + ", updated="
				+ (updated != null ? updated.getTime() : null) + ", size="
				+ size + ", metadata=" + metadata + ", links=" + links + "]";
	}

}
