package org.fiteagle.interactors.sfa.common;

public class GeniAvailableOption extends SFAOption {

	
	
	@Override
	public Boolean getValue() {
		return (Boolean) this.value;
	}
	
	public GeniAvailableOption(boolean value) {
		this.value = new Boolean(value);
		this.name = "geni_available";
		this.isOptional = true;
	}

}
