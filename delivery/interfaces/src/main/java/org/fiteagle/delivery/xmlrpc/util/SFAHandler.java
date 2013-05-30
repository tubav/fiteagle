package org.fiteagle.delivery.xmlrpc.util;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fiteagle.interactors.sfa.ISFA;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.slf4j.Logger;

import redstone.xmlrpc.XmlRpcInvocationHandler;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class SFAHandler implements XmlRpcInvocationHandler {
  
  ISFA interactor;
  
  final Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
  void setInteractor(ISFA interactor){
    this.interactor = interactor;
  }
  @Override
  public abstract Object invoke(String method, List arguments) throws Throwable;
  public abstract Object invoke(String method, List arguments, X509Certificate certificate) throws Throwable;
  
  Method getMethod(String methodName) {
    Method knownMethod = null;
    Method[] methodsFromHandler = this.interactor.getClass().getMethods();
    
    for (int i = 0; i < methodsFromHandler.length; i++) {
      if (methodsFromHandler[i].getName().equals(methodName)) {
        // Critical assumption !!! Only one method which equals the
        // methodname exists!
        // failure prone
        //TODO: check with arguments!
        knownMethod = methodsFromHandler[i];
      }
    }
    if (knownMethod == null) {
      ParsingException e = new MethodNotFound(methodName);
      throw e;
    }
    return knownMethod;
  }
  
  List<Object> createEmptyMethodParameters(Class<?>[] parameterClasses) throws InstantiationException,
      IllegalAccessException {
    
    List<Object> returnList = new ArrayList<>();
    for (int i = 0; i < parameterClasses.length; i++) {
      Object o = parameterClasses[i].newInstance();
      returnList.add(o);
    }
    
    return returnList;
  }
  
  @SuppressWarnings("unchecked")
 Object introspect(Object result) throws IOException {
    if(result.getClass().equals(String.class)){
      return result;
    }
    final ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(Include.NON_NULL);
    final StringWriter writer = new StringWriter();
    
    mapper.writeValue(writer, result);
    final Map<String, Object> response = mapper.readValue(writer.toString(), Map.class);
    
    return response;
  }
  
  Object getMethodCallResult(Method knownMethod, @SuppressWarnings("rawtypes") List parameters)
      throws IllegalAccessException, InvocationTargetException, InstantiationException {
    Object result = null;
    
    Class<?>[] parameterClasses = knownMethod.getParameterTypes();
    if (parameterClasses.length == 0) {
      result = knownMethod.invoke(interactor, (Object[]) null);
    } else {
      
      List<Object> methodParameters = createEmptyMethodParameters(parameterClasses);
      
      for (int i = 0; i < parameterClasses.length; i++) {
    	  Object tmpParam = methodParameters.get(i);
    	  tmpParam = xmlStructToObject(parameters.get(i), methodParameters.get(i));
    	  methodParameters.set(i, tmpParam);
      }
      
      result = knownMethod.invoke(interactor, methodParameters.toArray());
      
    }
    return result;
  }
  
  Object createResponse(Object result) {
    Object response = new Object();
    try {
      response = introspect(result);
    } catch (IOException ioException) {
      log.error(ioException.getMessage(), ioException);
    }
    return response;
  }
  
  public abstract Object xmlStructToObject(Object object, Object object2);
  
  class ParsingException extends RuntimeException {
    private GENI_CodeEnum errorCode = GENI_CodeEnum.ERROR;
    private String errorMessage = "Error";
    private static final long serialVersionUID = 1L;
    
    public void setErrorCode(GENI_CodeEnum errorCode) {
      this.errorCode = errorCode;
    }
    
    public GENI_CodeEnum getErrorCode() {
      return errorCode;
    }
    
    public String getMessage() {
      return this.errorMessage;
    }
    
    public void setMessage(String message) {
      this.errorMessage = message;
    }
  }
  
  class MethodNotFound extends ParsingException {
    
    private static final long serialVersionUID = 2409993059634896770L;
    
    public MethodNotFound(String methodName) {
      setErrorCode(GENI_CodeEnum.RPCERROR);
      setMessage("Method " + methodName + " not found");
    }
    
  }
  
  class CredentialsNotValid extends ParsingException {
    
    private static final long serialVersionUID = 1L;
    
    public CredentialsNotValid() {
      setErrorCode(GENI_CodeEnum.BADARGS);
      setMessage("Credentials not Valid");
    }
    
  }
  
}
