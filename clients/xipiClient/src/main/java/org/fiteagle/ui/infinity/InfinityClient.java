package org.fiteagle.ui.infinity;

import java.util.ArrayList;

import org.fiteagle.ui.infinity.model.InfinityInfrastructure;
import org.fiteagle.ui.infinity.model.InfinityValueID;

public abstract class InfinityClient {
	
	protected InfinityParser parser;

	protected enum Methods {
	    GET_INFRA_BY_ID ("getInfrastructuresById"),
	    GET_COMPONENT_BY_ID  ("getComponentById");
	    private String value;

		private Methods(String value) { this.value = value;}
		public String getValue() { return this.value; }
	}
	
	public InfinityClient() {
		this.parser = new InfinityParser();
	}

	abstract InfinityInfrastructure getInfrastructuresById(Number i);
	abstract ArrayList<InfinityValueID> searchInfrastructures();

}
