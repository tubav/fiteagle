package org.fiteagle.interactors.sfa.performoperationalaction;

import java.util.ArrayList;

import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.GeniSliversOperationalStatus;

public class PerformOperationalActionResult extends AMResult {
	
	private ArrayList<GeniSliversOperationalStatus> value = new ArrayList<GeniSliversOperationalStatus>();

	public ArrayList<GeniSliversOperationalStatus> getValue() {
		return value;
	}

	public void setValue(ArrayList<GeniSliversOperationalStatus> value) {
		this.value = value;
	}

}
