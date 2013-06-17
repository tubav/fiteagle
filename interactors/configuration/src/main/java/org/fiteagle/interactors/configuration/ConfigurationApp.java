package org.fiteagle.interactors.configuration;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.fiteagle.interactors.api.ConfigurationManagerBoundary;

public class ConfigurationApp extends Application {

  @Override
  public Set<Class<?>> getClasses() {
      Set<Class<?>> classes = new HashSet<Class<?>>();
      classes.add(ConfigurationManager.class);
      classes.add(ConfigurationManagerBoundary.class);
      return classes;
  }
}

