package org.fiteagle.interactors.sfa.register;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class RegisterRequestProcessorTest {
  
  
  RegisterRequestProcessor proc = null;
  @Before
  public void setUp() throws Exception {
     proc = new RegisterRequestProcessor();
  }
  
  @Test
  public void registerTest() {
    HashMap<String,Object> result = proc.register(new HashMap<String,Object>());
  }
  
}
