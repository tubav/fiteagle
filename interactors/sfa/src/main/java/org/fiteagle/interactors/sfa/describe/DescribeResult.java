package org.fiteagle.interactors.sfa.describe;

import org.fiteagle.interactors.sfa.common.AMResult;

public class DescribeResult extends AMResult{
	
	
	private DescribeValue value;
	
	public DescribeValue getValue() {
		return this.value;
	}
	

	public void setValue(DescribeValue value) {
		this.value = value;
	}

}
