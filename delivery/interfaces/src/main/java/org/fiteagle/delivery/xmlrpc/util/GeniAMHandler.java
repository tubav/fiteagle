package org.fiteagle.delivery.xmlrpc.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.fiteagle.interactors.sfa.ISFA;
import org.fiteagle.interactors.sfa.SFAInteractor_v3;
import org.fiteagle.interactors.sfa.allocate.AllocateOptions;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Credentials;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniAvailableOption;
import org.fiteagle.interactors.sfa.common.GeniEndTimeoption;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.describe.DescribeOptions;
import org.fiteagle.interactors.sfa.listresources.ListResourceOptions;
import org.fiteagle.interactors.sfa.rspec.RSpecContents;
import org.slf4j.Logger;

import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcStruct;

public class GeniAMHandler extends SFAHandler {

  ISFA interactor;

  private final Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
  
  public GeniAMHandler(SFAInteractor_v3 sfaInteractor_v3) {
    setInteractor(sfaInteractor_v3);
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Object invoke(String methodName, List parameters) throws Throwable {
    
  Object response = null;

    try {
      Method knownMethod = getMethod(methodName);
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
    
    throw new ParsingException();

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
      jc = JAXBContext.newInstance(RSpecContents.class);
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
      String type = geni_rspec_version_struct.getString("type");
      String version = geni_rspec_version_struct.getString("version");
      Geni_RSpec_Version geni_RSpec_Version = new Geni_RSpec_Version();
      geni_RSpec_Version.setType(type);
      geni_RSpec_Version.setVersion(version);
      describeOptions.setGeni_rspec_version(geni_RSpec_Version);
    }
    
    return describeOptions;
  }

  private Object parseListCredentials(Object from, Object to) {
    try {
      XmlRpcArray listCredentialsArray = (XmlRpcArray) from;
      ListCredentials listCredentials = (ListCredentials) to;
      if (listCredentialsArray.size() > 0) {
        for (int i = 0; i < listCredentialsArray.size(); i++) {
          Object tmptest = listCredentialsArray.get(i);
          Class<? extends Object> tmttestClass = tmptest.getClass();
          XmlRpcStruct credentialsStruct = (XmlRpcStruct) listCredentialsArray
              .get(i);
          Credentials credentials = new Credentials();
          if (credentialsStruct.getString("geni_type") != null) {
            credentials.setGeni_type(credentialsStruct
                .getString("geni_type"));
          } else {
//            throw new CredentialsNotValid();
          }
          if (credentialsStruct.getString("geni_version") != null) {
            credentials.setGeni_version(credentialsStruct
                .getString("geni_version"));
          } else {
//            throw new CredentialsNotValid();
          }
          if (credentialsStruct.getString("geni_value") != null) {
            credentials.setGeni_value(credentialsStruct
                .getString("geni_value"));
          } else {
//            throw new CredentialsNotValid();
          }

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
    listResourceOptions.setGeni_available(new GeniAvailableOption(
        listResourceOptionsStruct.getBoolean("geni_available")));
    listResourceOptions.setGeni_compressed(new org.fiteagle.interactors.sfa.common.GeniCompressedOption(
        listResourceOptionsStruct.getBoolean("geni_compressed")));

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


  }}


