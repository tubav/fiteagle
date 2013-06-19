package org.fiteagle.interactors.configuration;

import org.fiteagle.core.config.Configurator;
import org.fiteagle.interactors.api.ConfigurationManagerBoundary;

public class ConfigurationManager implements ConfigurationManagerBoundary {
  public String getVersion() {
    return new Configurator().getCommitVersion();
  }

}
