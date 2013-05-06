package org.fiteagle.interactors.sfa.common;

public abstract class SFAOptionsService {

	public abstract boolean areOptionsValid();
	
	public boolean isGeniCompressedOptionSupported(){
		return true;
	}
}
