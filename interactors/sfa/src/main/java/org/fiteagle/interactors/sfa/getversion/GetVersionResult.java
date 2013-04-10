package org.fiteagle.interactors.sfa.getversion;

import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.getversion.GetVersionValue;

public class GetVersionResult extends AMResult {
		

	private GetVersionValue value;

	public GetVersionValue getValue() {
		return this.value;
	}
	

	public void setValue(final GetVersionValue value) {
		this.value = value;
	}

	

//	@Override
//	public boolean checkValid() {
//		//TODO implement validation
//		return false;
//	}


	public int getGeni_api() {
		return this.value.getGeni_api();
	}

	
}
