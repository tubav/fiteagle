package org.fiteagle.delivery.rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.fiteagle.core.aaa.KeyStoreManagement;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class RestUserManagerIT {
  
  private static FiteaglePreferencesXML keystorePrefs = new FiteaglePreferencesXML(KeyStoreManagement.class);
  private final static String KEYSTORE_DIRECTORY = keystorePrefs.get("keystore");
  private final static String KEYSTORE_PASSWORD = keystorePrefs.get("keystore_pass");
  
  private final static String USER_1_JSON = "{\"firstName\":\"mitja\",\"lastName\":\"nikolaus\",\"password\":\"mitja\",\"email\":\"mnikolaus@test.de\",\"publicKeys\":[\"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCLq3fDWATRF8tNlz79sE4Ug5z2r5CLDG353SFneBL5z9Mwoub2wnLey8iqVJxIAE4nJsjtN0fUXC548VedJVGDK0chwcQGVinADbsIAUwpxlc2FGo3sBoGOkGBlMxLc/+5LT1gMH+XD6LljxrekF4xG6ddHTgcNO26VtqQw/VeGw== RSA-1024\"]}";
  private final static String USER_1_UPDATE_JSON = "{\"lastName\":\"nicolaus\",\"password\":\"pass\"}";

  private String cookieValue;
  
  @BeforeClass
  public static void setUp() throws IllegalArgumentException, IOException{    
    RestAssured.keystore(KEYSTORE_DIRECTORY, KEYSTORE_PASSWORD);
    RestAssured.baseURI = "https://localhost";
    RestAssured.port = 8443;
    RestAssured.basePath = "/api/v1/user";    
  }
  
  @Before
  public void deleteUsers(){
    given().auth().preemptive().basic("mnikolaus", "mitja").and()
      .when().delete("mnikolaus");
    given().auth().preemptive().basic("mnikolaus", "pass").and()
      .when().delete("mnikolaus");
  }
    
  @Test
  public void testPutAndGet() {
    PutUser1();
    GetUser1();   
  }
  
  @Test
  public void testPost() {
    PutUser1();
    PostUser2();
    GetUser2();
  }
  
  @Test
  public void testDelete() {
    PutUser1();
    DeleteUser1();
  }
  
  @Test
  public void testCookieAuthentication() {
    PutUser1();
    
    Response response = given().auth().preemptive().basic("mnikolaus", "mitja").when().get("mnikolaus");
    cookieValue = response.getCookie(AuthenticationFilter.getCookieName());
    
    given().cookie(AuthenticationFilter.getCookieName(),cookieValue).and().expect().statusCode(200).when().delete("mnikolaus");
  }

  private void PutUser1() {
    given().contentType("application/json").body(USER_1_JSON)
      .expect().statusCode(201)
      .when().put("mnikolaus");
  }
  
  private void GetUser1() {
    given().auth().preemptive().basic("mnikolaus", "mitja").and()
      .expect().statusCode(200)
        .body("username", equalTo("mnikolaus"))
        .body("lastName", equalTo("nikolaus"))
      .when().get("mnikolaus");
  }
  
  private void PostUser2() {
    given().auth().preemptive().basic("mnikolaus", "mitja").and()
      .contentType("application/json").body(USER_1_UPDATE_JSON)     
      .expect().statusCode(200)
      .when().post("mnikolaus");
  }
  
  private void GetUser2() {
    given().auth().preemptive().basic("mnikolaus", "pass").and()
      .expect().statusCode(200)
        .body("username", equalTo("mnikolaus"))
        .body("lastName", equalTo("nicolaus"))
      .when().get("mnikolaus");
  }
  
  private void DeleteUser1() {
    given().auth().preemptive().basic("mnikolaus", "mitja").and()
      .expect().statusCode(200)
      .when().delete("mnikolaus");
  }
  
}
