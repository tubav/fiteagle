
package org.fiteagle.delivery.rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.containsString;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.fiteagle.core.aaa.KeyStoreManagement;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.fiteagle.delivery.rest.fiteagle.UserAuthenticationFilter;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RestUserManagerIT {
  
  private static FiteaglePreferencesXML keystorePrefs = new FiteaglePreferencesXML(KeyStoreManagement.class);
  private final static String KEYSTORE_DIRECTORY = keystorePrefs.get("keystore");
  private final static String KEYSTORE_PASSWORD = keystorePrefs.get("keystore_pass");
  
  private final static String USER_1_JSON = "{\"firstName\":\"mitja\",\"lastName\":\"nikolaus\",\"password\":\"mitja\",\"email\":\"mnikolaus@test.de\",\"affiliation\":\"mitjasAffiliation\",\"publicKeys\":[{\"publicKeyString\":\"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCLq3fDWATRF8tNlz79sE4Ug5z2r5CLDG353SFneBL5z9Mwoub2wnLey8iqVJxIAE4nJsjtN0fUXC548VedJVGDK0chwcQGVinADbsIAUwpxlc2FGo3sBoGOkGBlMxLc/+5LT1gMH+XD6LljxrekF4xG6ddHTgcNO26VtqQw/VeGw== RSA-1024\",\"description\":\"my first key\"}]}";
  private final static String USER_1_UPDATE_JSON = "{\"lastName\":\"nicolaus\",\"email\":\"nicolaus@test.de\"}";
  private final static String USER_1_INCOMPLETE_JSON = "{\"firstName\":\"mitja\",\"lastName\":\"nikolaus\",\"password\":\"mitja\",\"email\":\"mnikolaus@testde\",\"affiliation\":\"mitjasAffiliation\"}";
  private final static String USER_1_PUBLIC_KEY = "{\"publicKeyString\":\"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCLq3fDWATRF8tNlz79sE4Ug5z2r5CLDG353SFneBL5z9Mwoub2wnLey8iqVJxIAE4nJsjtN0fUXC548VedJVGDK0chwcQGVinADbsIAUwpxlc2FGo3sBoGOkGBlMxLc/+5LT1gMH+XD6LljxrekF4xG6ddHTgcNO26VtqQw/VeGw== RSA-1024\",\"description\":\"my first key\"}";
  
  @BeforeClass
  public static void setUp() throws IllegalArgumentException, IOException{    
    RestAssured.keystore(KEYSTORE_DIRECTORY, KEYSTORE_PASSWORD);
    RestAssured.baseURI = "https://localhost";
    RestAssured.port = 8443;
    RestAssured.basePath = "/api/v1/user";    
    
    given().auth().preemptive().basic("mnikolaus", "mitja")
    .when().delete("mnikolaus");  
  }
  
  @Before
  public void addOneUser(){
    PutUser1();
  }
  
  @Test
  public void testGet() {
    GetUser1();   
  }
  
  @Test
  public void testPost() {
    UpdateUser1();
    GetUser1Updated();
    DeleteUser1();
  }
  
  @Test
  public void testDelete() {
    DeleteUser1();
    user1ShouldBeDeleted();
  }

  @Test
  public void testDeleteAndAddPublicKey() throws UnsupportedEncodingException {
    deletePubKey();
    user1ShouldHaveNoKeys();
    addPubKey();
  }
  
  @Test
  public void testCreateCertificate() throws UnsupportedEncodingException {
    String description = "my first key";
    String encodedDescription = URLEncoder.encode(description, "UTF-8");
    given().auth().preemptive().basic("mnikolaus", "mitja").and()
    .expect().statusCode(200)
      .body(containsString("-----BEGIN CERTIFICATE-----"))
      .when().get("mnikolaus/pubkey/"+encodedDescription+"/certificate");
  }
  
  @Test
  public void testPutUnprocessableUser(){
    given().contentType("application/json").body(USER_1_INCOMPLETE_JSON)
      .expect().statusCode(422)
      .body(containsString("email"))
      .when().put("mnikolaus");
  }

  @Test
  public void testAuthenticationFailure() {
    given().auth().preemptive().basic("mnikolaus", "wrongpassword").and()
      .expect().statusCode(401).when().get("mnikolaus");
  }
  
  @Test
  public void testSessionAuthentication() {
    Response response = given().auth().preemptive().basic("mnikolaus", "mitja").when().get("mnikolaus");
    String cookieValue = response.getSessionId();
    
    given().cookie("JSESSIONID",cookieValue).and()
      .expect().statusCode(200).when().delete("mnikolaus");
  }
  
  @Test
  public void testCookieAuthentication() {
    Response response = given().auth().preemptive().basic("mnikolaus", "mitja").when().get("mnikolaus?setCookie=true");
    String cookieValue = response.getCookie(UserAuthenticationFilter.COOKIE_NAME);
    
    given().cookie(UserAuthenticationFilter.COOKIE_NAME, cookieValue).and()
      .expect().statusCode(200).when().delete("mnikolaus");
  }
  
  @Test
  public void testLogout(){
    Response response = given().auth().preemptive().basic("mnikolaus", "mitja").when().get("mnikolaus?setCookie=true");
    String authCookieValue = response.getCookie(UserAuthenticationFilter.COOKIE_NAME);
    String sessionCookieValue = response.getCookie("JSESSIONID");

    deleteCookiesOfUser1(sessionCookieValue);
    
    user1CookieAuthenticationShouldFail(authCookieValue, sessionCookieValue);
  }

  private void PutUser1() {
    given().contentType("application/json").body(USER_1_JSON)   
      .when().put("mnikolaus");
  }
  
  private void GetUser1() {
    given().auth().preemptive().basic("mnikolaus", "mitja").and()
      .expect().statusCode(200)
        .body("username", containsString("mnikolaus"))
        .body("lastName", equalTo("nikolaus"))
      .when().get("mnikolaus");
  }
  
  private void UpdateUser1() {
    given().auth().preemptive().basic("mnikolaus", "mitja").and()
      .contentType("application/json").body(USER_1_UPDATE_JSON)     
      .expect().statusCode(200)
      .when().post("mnikolaus");
  }
  
  private void GetUser1Updated() {
    given().auth().preemptive().basic("mnikolaus", "mitja").and()
      .expect().statusCode(200)
        .body("username", containsString("mnikolaus"))
        .body("lastName", equalTo("nicolaus"))
        .body("email", equalTo("nicolaus@test.de"))
      .when().get("mnikolaus");
  }
  
  private void DeleteUser1() {
    given().auth().preemptive().basic("mnikolaus", "mitja").and()
      .expect().statusCode(200)
      .when().delete("mnikolaus");
  }  
  
  private void user1ShouldBeDeleted() {
    given().auth().preemptive().basic("mnikolaus", "mitja").and()
      .expect().statusCode(401).when().get("mnikolaus");
  }

  private void deletePubKey() throws UnsupportedEncodingException {
    String description = "my first key";
    String encodedDescription = URLEncoder.encode(description, "UTF-8");
    given().auth().preemptive().basic("mnikolaus", "mitja").and()
    .expect().statusCode(200)
    .when().delete("mnikolaus/pubkey/"+encodedDescription);
  }
  
  private void addPubKey() {
    given().auth().preemptive().basic("mnikolaus", "mitja").and()
      .contentType("application/json").body(USER_1_PUBLIC_KEY) 
      .expect().statusCode(200)
      .when().post("mnikolaus/pubkey");
  }
  
  private void user1ShouldHaveNoKeys() {
   given().auth().preemptive().basic("mnikolaus", "mitja").and()
    .expect().statusCode(200)
      .body("publicKeys", hasSize(0))      
    .when().get("mnikolaus");
  }
  
  private void user1CookieAuthenticationShouldFail(String authCookieValue, String sessionCookieValue) {
    given().cookie(UserAuthenticationFilter.COOKIE_NAME, authCookieValue).and()
      .expect().statusCode(401).when().get("mnikolaus");
    given().cookie("JSESSIONID", sessionCookieValue).and()
      .expect().statusCode(401).when().get("mnikolaus");
  }

  private void deleteCookiesOfUser1(String sessionCookieValue) {
    given().cookie("JSESSIONID", sessionCookieValue).and()
      .expect().statusCode(200).when().delete("mnikolaus/cookie");
  }
  
  @AfterClass
  public static void deleteUsers(){
    given().auth().preemptive().basic("mnikolaus", "mitja")
      .when().delete("mnikolaus");   
  }
  
}
