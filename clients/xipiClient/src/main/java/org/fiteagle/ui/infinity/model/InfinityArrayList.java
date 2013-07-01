package org.fiteagle.ui.infinity.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfinityArrayList {

	private String javaClass;
	private java.util.List<InfinityValueID> list = new ArrayList<InfinityValueID>();
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getJavaClass() {
		return javaClass;
	}

	public void setJavaClass(String javaClass) {
		this.javaClass = javaClass;
	}

	public java.util.List<InfinityValueID> getList() {
		return list;
	}

	public void setList(java.util.List<InfinityValueID> list) {
		this.list = list;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperties(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
