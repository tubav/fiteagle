
package org.fiteagle.core.config;

import java.util.prefs.Preferences;

public class FiteaglePreferencesXML extends FiteaglePreferences {

  private Preferences preferences;

  public FiteaglePreferencesXML(Class<?> prefix) {
    this.preferences = Preferences.userNodeForPackage(prefix);    
  }
  
  @Override
  public void put(String key, String value) {    
    preferences.put(key, value);    
  }
  
  @Override
  public String get(String key) {
    return preferences.get(key, null);
  }  
}
