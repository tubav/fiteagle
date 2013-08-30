package org.fiteagle.interactors.sfa.performoperationalaction;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ActionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoSomething(){
		Action action = new Action("doSomeThingVoid", new DoSomething());
		Assert.assertTrue((boolean) action.doAction());
	}
	
	@Test
	public void testDoReturnString(){
		Action action = new Action("doReturnString", new DoSomething());
		Assert.assertEquals("Hallo", action.doAction());
	}
	
	@Test
	public void testDoSomethingOneSimpleParameter(){
		Action action = new Action("doSomethingOneSimpleParameter(5)",new DoSomething());
		Assert.assertTrue((boolean) action.doAction());
	}
	
	@Test
	public void testDoSomethingOneObjectParameter(){
		Action action = new Action("doSomethingOneObjectParameter(o)", new DoSomething());
		Assert.assertTrue((boolean)action.doAction());
	}
	
	@Test
	public void testDoSomethingWithString(){
		Action action = new Action("doSomethingWithString(hallo)", new DoSomething());
		System.out.println(action.doAction());
	}

	
	class DoSomething{
		
		
		public boolean doSomeThingVoid(){
			return true;
		}
		
		public String doReturnString(){
			return "Hallo";
		}
		
		public boolean doSomethingOneSimpleParameter(int integer){
			return true;
		}
		
		public boolean doSomethingOneObjectParameter(Object o){
			return true;
		}
		
		public String doSomethingWithString(String string){
			return string;
		}
		
		public String doSomethingWithTwoStrings(String one, String two){
			return one + " "+two;
		}
		
	}
}
