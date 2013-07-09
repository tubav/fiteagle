package org.fiteagle.interactors.sfa.util;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class URNTest {
  
  private URN urn;
  private String urnString;

  @Before
  public void setUp() throws Exception {
    urnString = "urn:publicid:IDN+av.tu-berlin.de+user+test";
    urn = new URN(urnString);
  }
 
  @Test
  public void testSetAndGetSubjectName(){
    
    String subjectName = urn.getSubject();
    Assert.assertNotNull(subjectName);
  }
  
  @Test
  public void testSetAndGetDomain(){
    
    String domain = urn.getDomain();
    Assert.assertNotNull(domain);
  }
  
  @Test
  public void testSetAndGetType(){
    
    String type = urn.getType();
    Assert.assertNotNull(type);
  }
 
  @Test
  public void testToString(){
    Assert.assertEquals(urnString, urn.toString());
  }
  
  
  
}
