package org.fiteagle.interactors.sfa.common;


public class GeniCompressedOption extends SFAOption {

	public GeniCompressedOption(boolean value) {
		this.name = "geni_compressed";
		this.value = new Boolean(value);
		this.isOptional = true;
	}

	@Override
	public Boolean getValue() {
		return (Boolean) this.value;
	}
}
