package org.fiteagle.adapter.common;

public interface SSHAccessable {

	public String getHardwareType();

	public void setHardwareType(String hardwareType);

	public String getIp();

	public void setIp(String ip);
	
	public String getPort();
	
	public void setPort(String port);

	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);

	public String getSshKey();

	public void setSshKey(String sshKey);

}
