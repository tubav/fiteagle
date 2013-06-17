package org.fiteagle.interactors.configuration;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ConfigurationManagerTest {

  private ConfigurationManager manager;
  
  @Before
  public void setUp() throws Exception {
    this.manager = new ConfigurationManager();
  }
  
  @Test
  public void testGetVersion() {
        Assert.assertNotNull(manager.getVersion());
  }
  
}
