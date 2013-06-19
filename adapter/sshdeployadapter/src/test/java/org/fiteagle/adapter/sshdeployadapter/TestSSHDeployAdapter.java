package org.fiteagle.adapter.sshdeployadapter;

import java.util.prefs.Preferences;

import org.junit.Before;
import org.junit.Test;

public class TestSSHDeployAdapter {
	
	
	
	private SSHDeployAdapterConfiguration sshDeployAdapterConfiguration;
	private String countries="testcountry1, testcountry2";

	@Before
	public void setUp() {
		sshDeployAdapterConfiguration = new SSHDeployAdapterConfiguration();
	}
	
	@Test
	public void testAddPreference(){
		sshDeployAdapterConfiguration.setCountries(countries);
		
		Preferences preferences = Preferences.userNodeForPackage(getClass());
		System.out.println(preferences.get("countries", null));
		
//		sshDeployAdapterConfiguration.removeCountries();
		
	}
  
}
