package org.fiteagle.delivery.xmlrpc.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.ISFA;
import org.fiteagle.interactors.sfa.SFAInteractor_v3;
import org.fiteagle.interactors.sfa.allocate.AllocateOptions;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Credentials;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniAvailableOption;
import org.fiteagle.interactors.sfa.common.GeniBestEffortOption;
import org.fiteagle.interactors.sfa.common.GeniEndTimeoption;
import org.fiteagle.interactors.sfa.common.GeniUser;
import org.fiteagle.interactors.sfa.common.GeniUserList;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.delete.DeleteOptions;
import org.fiteagle.interactors.sfa.describe.DescribeOptions;
import org.fiteagle.interactors.sfa.listresources.ListResourceOptions;
import org.fiteagle.interactors.sfa.performoperationalaction.PerformOperationalActionOptions;
import org.fiteagle.interactors.sfa.provision.ProvisionOptions;
import org.fiteagle.interactors.sfa.renew.RenewOptions;
import org.fiteagle.interactors.sfa.rspec.request.RSpecContents;
import org.fiteagle.interactors.sfa.status.StatusOptions;
import org.slf4j.Logger;

import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcStruct;

public class GeniAMHandler extends SFAHandler {

  ISFA interactor;

  private final Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

  
public GeniAMHandler() {
}

  @SuppressWarnings("rawtypes")
  @Override
  public Object invoke(String methodName, List parameters) throws Throwable {
    SFAInteractor_v3 interactor = new SFAInteractor_v3();
    setInteractor(interactor);
    
  Object response = null;

    try {
      Method knownMethod = getMethod(methodName, parameters);
      AMResult result =(AMResult) getMethodCallResult(knownMethod, parameters);
      response = createResponse(result);
    } catch (ParsingException e) {
      response = createErrorResponse(e);
    }
    return response;

  }

  private Object createErrorResponse(ParsingException e) throws IOException {
    AMResult errorResult = getErrorResult(e);
    Object errorResponse = introspect(errorResult);
    return errorResponse;
  }

  private AMResult getErrorResult(ParsingException e) {
    AMResult result = new AMResult();
    result.setOutput(e.getMessage());
    AMCode errorCode = getErrorCode(e);
    result.setCode(errorCode);
    return result;
    
  }

  private AMCode getErrorCode(ParsingException e) {
    AMCode code = new AMCode();
    code.setGeni_code(e.getErrorCode());
    return code;
  }

  private Object createResponse(AMResult result) {
    Object response = new HashMap<>();
    try {
      response = introspect(result);
    } catch (IOException ioException) {
      log.error(ioException.getMessage(),ioException);
    }
    return response;
  }
  
  
  @Override
  public Object invoke(String methodName, List arguments, X509Certificate certificate) throws Throwable {
    SFAInteractor_v3 interactor = new SFAInteractor_v3();
    interactor.setCertificate(certificate);
    setInteractor(interactor);
    
    Object response = null;

    try {
      Method knownMethod = getMethod(methodName, arguments);
      AMResult result =(AMResult) getMethodCallResult(knownMethod, arguments);
      response = createResponse(result);
    } catch (ParsingException e) {
      response = createErrorResponse(e);
    }
    return response;
  }



  public Object xmlStructToObject(Object from, Object to) {
    
    if (to.getClass().isAssignableFrom(String.class)) {
      return from;

    }
    
    if (to.getClass().isAssignableFrom(ListResourceOptions.class)) {
      return parseListResourcesOptions(from, to);

    }
    if (to.getClass().isAssignableFrom(ListCredentials.class)) {
      return parseListCredentials(from, to);
      
    }
    
    if (to.getClass().isAssignableFrom(ArrayList.class)){
      return parseUrns(from);
      
    }
    
    if (to.getClass().isAssignableFrom(DescribeOptions.class)){
      return parseDescribeOptions(from);
      
    }
    
    if (to.getClass().isAssignableFrom(RSpecContents.class)){
      return parseRspecContents(from);
      
    }
    
    if (to.getClass().isAssignableFrom(AllocateOptions.class)){
      return parseAllocateOptions(from);
      
    }
    
    if (to.getClass().isAssignableFrom(ProvisionOptions.class)){
      return parseprovisionOptions(from);
      
    }
    
    if (to.getClass().isAssignableFrom(StatusOptions.class)){
      return parseStatusOptions(from);
      
    }
    
    if (to.getClass().isAssignableFrom(DeleteOptions.class)){
      return parseDeleteOptions(from);
      
    }
    
    if (to.getClass().isAssignableFrom(PerformOperationalActionOptions.class)){
        return parsePerformOperationalActionOptions(from);
        
      }
    
    //TODO: implement parsing options for renew method!?
    if (to.getClass().isAssignableFrom(RenewOptions.class)){
    	return new RenewOptions();
//        return parseRenewOptions(from);
        
      }
    
    throw new ParsingException();

  }

  private Object parsePerformOperationalActionOptions(Object from) {
	  XmlRpcStruct performOperationalActionStruct = (XmlRpcStruct) from;
	  PerformOperationalActionOptions performOperationalActionOptions = new PerformOperationalActionOptions();
	  if(performOperationalActionStruct.get("geni_best_effort")!=null){
		  GeniBestEffortOption geni_best_effort = new GeniBestEffortOption(performOperationalActionStruct.getBoolean("geni_best_effort"));
		  performOperationalActionOptions.setGeni_best_effort(geni_best_effort);
	  }
	  return performOperationalActionOptions;
}

private Object parseDeleteOptions(Object from) {
    XmlRpcStruct deleteOptionsStruct = (XmlRpcStruct) from;
    DeleteOptions deleteOptions = new DeleteOptions();
    if(deleteOptionsStruct.getString("geni_best_effort")!=null && deleteOptionsStruct.getString("geni_best_effort").compareTo("")!=0){//TODO: test this!
      GeniBestEffortOption geni_best_effort = new GeniBestEffortOption(deleteOptionsStruct.getBoolean("geni_best_effort"));
      deleteOptions.setGeni_best_effort(geni_best_effort);
    }
    return deleteOptions;
  }

  private Object parseStatusOptions(Object from) {
    return new StatusOptions();
  }

  private Object parseprovisionOptions(Object from) {
    XmlRpcStruct provisionOptionsStruct = (XmlRpcStruct) from;
    ProvisionOptions provisionOptions = new ProvisionOptions();
    if(provisionOptionsStruct.getString("geni_end_time")!=null && provisionOptionsStruct.getString("geni_end_time").compareTo("")!=0){
      GeniEndTimeoption geni_end_time = new GeniEndTimeoption(provisionOptionsStruct.getString("geni_end_time"));
      provisionOptions.setGeni_end_time(geni_end_time);
    }
    if(provisionOptionsStruct.getString("geni_best_effort")!=null && provisionOptionsStruct.getString("geni_best_effort").compareTo("")!=0){
      GeniBestEffortOption geni_best_effort = new GeniBestEffortOption(provisionOptionsStruct.getBoolean("geni_best_effort"));
      provisionOptions.setGeni_best_effort(geni_best_effort);
    }
    
    if(provisionOptionsStruct.getString("geni_end_time")!=null && provisionOptionsStruct.getString("geni_end_time").compareTo("")!=0){
      GeniEndTimeoption geni_end_time = new GeniEndTimeoption(provisionOptionsStruct.getString("geni_end_time"));
      provisionOptions.setGeni_end_time(geni_end_time);
    }
    
    XmlRpcStruct geni_rspec_version_struct = provisionOptionsStruct.getStruct("geni_rspec_version");
    if (geni_rspec_version_struct != null) {
      Geni_RSpec_Version geni_RSpec_Version = getGeniRspecVersionFromStruct(geni_rspec_version_struct);
      provisionOptions.setGeni_rspec_version(geni_RSpec_Version);
    }
    
    if(provisionOptionsStruct.getArray("geni_users")!=null){
      GeniUserList geni_users = getGeniUsers(provisionOptionsStruct.getArray("geni_users"));
      provisionOptions.setGeni_users(geni_users);
    }
    
    
    
    return provisionOptions;
  }

  private GeniUserList getGeniUsers(XmlRpcArray array) {
    
    GeniUserList geniUSers= new GeniUserList();
    
    if (array.size() > 0) {
      for (int i = 0; i < array.size(); i++) {
        Object tmptest = array.get(i);
        Class<? extends Object> tmttestClass = tmptest.getClass();
        XmlRpcStruct userStruct = (XmlRpcStruct) array
            .get(i);
        GeniUser geniUser = new GeniUser();
        if (userStruct.getString("urn") != null) {
          geniUser.setUrn(new URN(userStruct.getString("urn")));
        }
        
        if (userStruct.getArray("keys") != null) {
          
          
          XmlRpcArray keysInStruct = userStruct.getArray("keys");
          ArrayList<String> keys = new ArrayList<String>();
          
          for (int j = 0; j < keysInStruct.size(); j++) {
            String key = keysInStruct
                .getString(j);
            
            keys.add(key);

          }
          geniUser.setKeys(keys);
        }
        
        
        
        geniUSers.addUser(geniUser);

      }
    }
    
    return geniUSers;
  }

  private Object parseAllocateOptions(Object from) {
    XmlRpcStruct allocateOptionsStruct = (XmlRpcStruct) from;
    AllocateOptions allocateOptions = new AllocateOptions();
    if(allocateOptionsStruct.getString("geni_end_time")!=null && allocateOptionsStruct.getString("geni_end_time").compareTo("")!=0){
      GeniEndTimeoption geni_end_time = new GeniEndTimeoption(allocateOptionsStruct.getString("geni_end_time"));
      allocateOptions.setGeni_end_time(geni_end_time);
    }
    return allocateOptions;
  }

  private Object parseRspecContents(Object from) {
    String fromStr = (String)from;
    InputStream fromIs = new ByteArrayInputStream(fromStr.getBytes());
    RSpecContents rSpec = new RSpecContents();
    JAXBContext jc;
    try {
//      jc = JAXBContext.newInstance( "org.fiteagle.interactors.sfa.rspec" );
      jc = JAXBContext.newInstance("org.fiteagle.interactors.sfa.rspec.request:org.fiteagle.interactors.sfa.rspec.ext:org.fiteagle.interactors.sfa.rspec.ext.openstack");
      Unmarshaller u = jc.createUnmarshaller();
      JAXBElement obj = (JAXBElement) u.unmarshal(fromIs);
      rSpec=(RSpecContents)obj.getValue();
    } catch (JAXBException e) {
      throw new ParsingException();
    }
    return rSpec;
  }

  private Object parseUrns(Object from) {
    XmlRpcArray urnsArray = (XmlRpcArray) from;
    ArrayList<String> urns = new ArrayList<String>();
    
    for (int i = 0; i < urnsArray.size(); i++) {
      urns.add(urnsArray.getString(i));
    }
    return urns;
  }

  private Object parseDescribeOptions(Object from) {
    XmlRpcStruct describeOptionsStruct = (XmlRpcStruct) from;
    DescribeOptions describeOptions = new DescribeOptions();
    describeOptions.setGeni_compressed(new org.fiteagle.interactors.sfa.common.GeniCompressedOption(describeOptionsStruct.getBoolean("geni_compressed")));
    
    XmlRpcStruct geni_rspec_version_struct = describeOptionsStruct.getStruct("geni_rspec_version");
    
    if (geni_rspec_version_struct != null) {
      Geni_RSpec_Version geni_RSpec_Version = getGeniRspecVersionFromStruct(geni_rspec_version_struct);
      describeOptions.setGeni_rspec_version(geni_RSpec_Version);
    }
    
    return describeOptions;
  }
  
  
  private Geni_RSpec_Version getGeniRspecVersionFromStruct(XmlRpcStruct geni_rspec_version_struct){
    String type = geni_rspec_version_struct.getString("type");
    String version = geni_rspec_version_struct.getString("version");
    Geni_RSpec_Version geni_RSpec_Version = new Geni_RSpec_Version();
    geni_RSpec_Version.setType(type);
    geni_RSpec_Version.setVersion(version);
    
    return geni_RSpec_Version;
  }

  private Object parseListCredentials(Object from, Object to) {
    try {
      XmlRpcArray listCredentialsArray = (XmlRpcArray) from;
      ListCredentials listCredentials = (ListCredentials) to;
      if (listCredentialsArray.size() > 0) {
        for (int i = 0; i < listCredentialsArray.size(); i++) {
          Object tmptest = listCredentialsArray.get(i);
          String geni_type ="geni_sfa";
          String geni_version ="3";
          String geni_value = "";
          if(tmptest instanceof String){
            geni_value = (String) tmptest;
          }else{
          XmlRpcStruct credentialsStruct = (XmlRpcStruct) listCredentialsArray
              .get(i);
           geni_type = credentialsStruct.getString("geni_type");
           geni_version = credentialsStruct.getString("geni_version");
           geni_value =credentialsStruct.getString("geni_value");
          }
          Credentials credentials = new Credentials();
          credentials.setGeni_type(geni_type);
          credentials.setGeni_version(geni_version);
          credentials.setGeni_value(geni_value);
          
          listCredentials.addCredentials(credentials);
          
        }
      }
      
      return listCredentials;
    } catch (ClassCastException e) {
      throw new CredentialsNotValid();
    }
  }

  private Object parseListResourcesOptions(Object from, Object to) {
    XmlRpcStruct listResourceOptionsStruct = (XmlRpcStruct) from;
    ListResourceOptions listResourceOptions = (ListResourceOptions) to;
    if(listResourceOptionsStruct.get("geni_available")!=null)
    listResourceOptions.setGeni_available(new GeniAvailableOption(
        listResourceOptionsStruct.getBoolean("geni_available")));
    if(listResourceOptionsStruct.get("geni_compressed")!=null){
      listResourceOptions.setGeni_compressed(new org.fiteagle.interactors.sfa.common.GeniCompressedOption(
          listResourceOptionsStruct.getBoolean("geni_compressed")));
    }else {
      listResourceOptions.setGeni_compressed(new org.fiteagle.interactors.sfa.common.GeniCompressedOption(false));
    }

    XmlRpcStruct geni_rspec_version_struct = listResourceOptionsStruct
        .getStruct("geni_rspec_version");
    if (geni_rspec_version_struct != null) {
      String type = geni_rspec_version_struct.getString("type");
      String version = geni_rspec_version_struct.getString("version");
      Geni_RSpec_Version geni_RSpec_Version = new Geni_RSpec_Version();
      geni_RSpec_Version.setType(type);
      geni_RSpec_Version.setVersion(version);

      listResourceOptions.setGeni_rspec_version(geni_RSpec_Version);

    } else {
      // TODO error handling throw exception => set corresponding
      // error code of response
    }
    
    return listResourceOptions;
  }


  private class ParsingException extends RuntimeException {
    private GENI_CodeEnum errorCode = GENI_CodeEnum.ERROR;
    private String errorMessage = "Error";
    private static final long serialVersionUID = 1L;
    public void setErrorCode(GENI_CodeEnum errorCode) {
      this.errorCode = errorCode;
    }
    
    public GENI_CodeEnum getErrorCode() {
      return errorCode;
    }
    
    public String getMessage(){
      return this.errorMessage;
    }
    
    public void setMessage(String message){
      this.errorMessage = message;
    }
  }

  private class CredentialsNotValid extends ParsingException {

    private static final long serialVersionUID = 1L;
    public CredentialsNotValid() {
      setErrorCode(GENI_CodeEnum.BADARGS);
      setMessage("Credentials not Valid");
    }


  }

}


