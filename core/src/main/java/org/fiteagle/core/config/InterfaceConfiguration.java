package org.fiteagle.core.config;

public class InterfaceConfiguration {
  
  private FiteaglePreferences preferences;
  
  private static InterfaceConfiguration interfaceConfig;
  private Configurator configurator ;
  private String DEFAULT_AM_URN = "urn:publicid:IDN+fiteagle+authority+am";
  private String DEFAULT_HOSTHAME = "fiteagle.org";
  private String DEFAULT_AM_URL = "https://fiteagle-fuseco.fokus.fraunhofer.de/api/sfa/am/v3";
  private InterfaceConfiguration(){
    
   preferences = new FiteaglePreferencesXML(getClass());
    if(preferences.get("am_urn")== null)
      preferences.put("am_urn", DEFAULT_AM_URN);
    
    if(preferences.get("hosthame") == null)
      preferences.put("hostname", DEFAULT_HOSTHAME);
    
    if(preferences.get("am_url") == null)
      preferences.put("am_url", DEFAULT_AM_URL);
    
   configurator = new Configurator();
  };
  
  
  public static InterfaceConfiguration getInstance(){
    if(interfaceConfig == null)
        interfaceConfig = new InterfaceConfiguration();
    
    return interfaceConfig;
  }
  
  
  public String getCommitVersion(){
    return configurator.getCommitVersion();
  }
  
  
  public String getAM_URN(){
    return preferences.get("am_urn");
  }
  
  public String getHostname(){
    return preferences.get("hostname");
  }
  
  public String getAM_URL(){
    return preferences.get("am_url");
  }
  
}
