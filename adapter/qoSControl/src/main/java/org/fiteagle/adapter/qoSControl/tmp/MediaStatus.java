package org.fiteagle.adapter.qoSControl.tmp;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MediaStatus {
	String maxUpload;
	String maxDownload;
	String mediaIdentifier;
	public MediaStatus(){
		
	}
	public String getMaxUpload() {
		return maxUpload;
	}
	public void setMaxUpload(String maxUpload) {
		this.maxUpload = maxUpload;
	}
	public String getMaxDownload() {
		return maxDownload;
	}
	public void setMaxDownload(String maxDownload) {
		this.maxDownload = maxDownload;
	}
	public String getMediaIdentifier() {
		return mediaIdentifier;
	}
	public void setMediaIdentifier(String mediaIdentifier) {
		this.mediaIdentifier = mediaIdentifier;
	}
	
	
}
