package org.fiteagle.core.util;

import junit.framework.Assert;

import org.fiteagle.core.util.URN;
import org.junit.Before;
import org.junit.Test;

public class URNTest {
  
  private URN urn;
  private String urnString;
private String urnWithSubDomain;
private URN urnWSubDom;

  @Before
  public void setUp() throws Exception {
    urnString = "urn:publicid:IDN+av.tu-berlin.de+user+test";
    urn = new URN(urnString);
    urnWithSubDomain = "urn:publicid:IDN+av:tu-berlin:de+user+test";
	 urnWSubDom = new URN(urnWithSubDomain);
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
  
  @Test
  public void testURNWithSubDomain(){
	
	 Assert.assertEquals(urnWSubDom.toString(),"urn:publicid:IDN+av:tu-berlin:de+user+test" );
  }
  
  @Test
  public void testGetUserAtDomain(){
	  String expected = "test@av.tu-berlin.de";
	  Assert.assertEquals(expected, urn.getSubjectAtDomain());
	  Assert.assertEquals(expected, urnWSubDom.getSubjectAtDomain());
  }
 
  
  
  
}
