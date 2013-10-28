package org.fiteagle.core.util;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.config.InterfaceConfiguration;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.userdatabase.User;

public class URN {

	private String subject;
	private String domain;
	private String type;
	private static String prefix = "urn:publicid:IDN";

	public URN(String urnString) {
		parseURNString(urnString);
	}

	private void parseURNString(String urnString) {
		String[] splitted = urnString.split("\\+");
		if (isCorrectLength(splitted)) {
			if (isCorrectPrefix(splitted[0])) {
				this.domain = splitted[1];
				this.type = splitted[2];
				this.subject = splitted[3];
			} else {
				throw new URNParsingException();
			}

		} else {
			splitted = urnString.split("\\.");
			if (splitted.length == 2) {
				this.domain = splitted[0];
				this.type = "user";
				this.subject = splitted[1];
			} else {
				throw new URNParsingException();
			}
		}
	}

	private boolean isCorrectPrefix(String prefix) {
		return this.prefix.equals(prefix);
	}

	private boolean isCorrectLength(String[] splitted) {
		return splitted.length == 4;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDomain() {
		return domain;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return prefix + "+" + domain + "+" + type + "+" + subject;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof URN) {
			URN toCompare = (URN) o;
			return toCompare.toString().equals(this.toString());
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return this.toString().hashCode();
	}

	public class URNParsingException extends RuntimeException {

		private static final long serialVersionUID = 1L;

	}

	public String getSubjectAtDomain() {
		if (domain.contains(":")) {
			return subject + "@" + domain.replace(":", ".");
		}
		return subject + "@" + domain;
	}

	public static URN getURNFromGroup(Group g) {
		String[] split = g.getGroupId().split("@");
		String returnString = prefix + "+" + split[1] + "+slice+" + split[0];
		return new URN(returnString);
	}

	public static URN getURNFromUser(User u) {
		String[] split = u.getUsername().split("@");
		String returnString = prefix + "+" + split[1] + "+user+" + split[0];
		return new URN(returnString);
	}
	
	public static URN getURNFromResourceAdapter(ResourceAdapter ra){
		String returnString = prefix + "+" + InterfaceConfiguration.getInstance().getDomain() + "+sliver+" +ra.getId();
		return new URN(returnString);
	}
}
