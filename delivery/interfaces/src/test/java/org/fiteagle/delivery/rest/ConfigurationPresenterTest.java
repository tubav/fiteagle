package org.fiteagle.delivery.rest;

import junit.framework.Assert;

import org.fiteagle.delivery.rest.fiteagle.ConfigurationPresenter;
import org.fiteagle.interactors.api.ConfigurationManagerBoundary;
import org.fiteagle.interactors.configuration.ConfigurationManager;
import org.junit.Test;

public class ConfigurationPresenterTest {
  
  @Test
  public void test() {
    ConfigurationManagerBoundary interactor = new ConfigurationManager();
    ConfigurationPresenter presenter = new ConfigurationPresenter(interactor);
    Assert.assertNotNull(presenter.getVersion());
  }  
}
