package org.fiteagle.adapter.common;

import java.util.List;

public interface NodeAdapterInterface {
	
	public List<OpenstackResourceAdapter> getImages();
	public void setImages(List<OpenstackResourceAdapter> images);
	
	String getId();
	

}
