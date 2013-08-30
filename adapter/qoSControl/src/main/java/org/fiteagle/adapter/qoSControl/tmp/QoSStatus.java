package org.fiteagle.adapter.qoSControl.tmp;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QoSStatus {

	String sessionId;
	String userId;
	Set<MediaStatus> medias;
	
	public QoSStatus(){
		
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Set<MediaStatus> getMedias() {
		return medias;
	}
	public void setMedias(Set<MediaStatus> medias) {
		this.medias = medias;
	}
	
	
	
}
