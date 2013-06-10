package org.fiteagle.interactors.sfa.rspec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.SSHAccessable;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;

public class SFAv3RspecTranslator {
  
  private static final String COMPONENT_MANAGER_ID = "urn:publicid:IDN+fiteagle.fuseco.fokus.fraunhofer.de+authority+root";
  private final Geni_RSpec_Version geni_rspec_version;
  private final String adRspecNamespace = "http://www.geni.net/resources/rspec/3";
  private final String adRspecSchema = "http://www.geni.net/resources/rspec/3/ad.xsd";
  private final ArrayList<String> adRspecExtensions = new ArrayList<String>();
  
  private final String requestRspecNamespace = "http://www.geni.net/resources/rspec/3";
  private final String requestRspecSchema = "http://www.geni.net/resources/rspec/3/request.xsd";
  private final ArrayList<String> requestRspecExtensions = new ArrayList<String>();
  
  private final String RSPEC_EXTENSION = "http://www.fiteagle.org/rspec/ext/1";
  
  public SFAv3RspecTranslator() {
    geni_rspec_version = new Geni_RSpec_Version();
    geni_rspec_version.setType("GENI");
    geni_rspec_version.setVersion("3");
    addAdRspecExtension(this.RSPEC_EXTENSION);
    addRequestRspecExtension(RSPEC_EXTENSION);
  }
  
  public String getAdRspecNamespace() {
    return adRspecNamespace;
  }
  
  public String getAdRspecSchema() {
    return adRspecSchema;
  }
  
  public String[] getAdRspecExtensions() {
    return adRspecExtensions.toArray(new String[adRspecExtensions.size()]);
  }
  
  public String getRequestRspecNamespace() {
    return requestRspecNamespace;
  }
  
  public String getRequestRspecSchema() {
    return requestRspecSchema;
  }
  
  public String[] getRequestRspecExtensions() {
    return requestRspecExtensions.toArray(new String[requestRspecExtensions.size()]);
  }
  
  public String getType() {
    return this.geni_rspec_version.getType();
  }
  
  public String getVersion() {
    return this.geni_rspec_version.getVersion();
  }
  
  private void addRequestRspecExtension(String extension) {
    requestRspecExtensions.add(extension);
  }
  
  private void addAdRspecExtension(String extension) {
    adRspecExtensions.add(extension);
  }
  
  public Object translateToFITeagleResource(ResourceAdapter resourceAdapter) {
    
    Resource fiteagleSFAResource = new Resource();
    
    fiteagleSFAResource.getMethod().addAll(this.getResourceAdapterRspecMethods(resourceAdapter));
    
    fiteagleSFAResource.setName(resourceAdapter.getType());
    
    HashMap<String, Object> resourceAdapterProperties = resourceAdapter.getProperties();
    
    if (resourceAdapterProperties != null) {
      
      Set<String> propKeys = resourceAdapterProperties.keySet();
      
      for (Iterator<String> iterator = propKeys.iterator(); iterator.hasNext();) {
        Property tmpProperty = new Property();
        String key = iterator.next();
        tmpProperty.setName(key);
        // The resource adaptor properties must have a string
        // representation.
        tmpProperty.setValue(resourceAdapterProperties.get(key).toString());
        fiteagleSFAResource.getProperty().add(tmpProperty);
      }
      
    }
    
    Property typeProperty = new Property();
    typeProperty.setName("type");
    // idProperty.setType("string");
    typeProperty.setValue(resourceAdapter.getType());
    fiteagleSFAResource.getProperty().add(typeProperty);
    
    Property idProperty = new Property();
    idProperty.setName("id");
    // idProperty.setType("string");
    idProperty.setValue(resourceAdapter.getId());
    fiteagleSFAResource.getProperty().add(idProperty);
    
    // Property statusProperty = new Property();
    // statusProperty.setName("status");
    // // statusProperty.setType("string");
    // statusProperty.setValue(resourceAdapter.getStatus());
    // fiteagleSFAResource.getProperty().add(statusProperty);
    
    return new ObjectFactory().createResource(fiteagleSFAResource);
  }
  
  private List<Method> getResourceAdapterRspecMethods(ResourceAdapter resourceAdapter) {
    ArrayList<Method> result = new ArrayList<Method>();
    java.lang.reflect.Method[] resourceAdapterMethods = resourceAdapter.getClass().getDeclaredMethods();
    for (int i = 0; i < resourceAdapterMethods.length; i++) {
      Method tmpRspecMethod = new Method();
      tmpRspecMethod.setName(resourceAdapterMethods[i].getName());
      tmpRspecMethod.setReturnType(resourceAdapterMethods[i].getReturnType().getName());
      if (!getRspecParametersFromResourceAdapterMethod(resourceAdapterMethods[i]).isEmpty()) {
        tmpRspecMethod.getParameter().addAll(getRspecParametersFromResourceAdapterMethod(resourceAdapterMethods[i]));
      }
      result.add(tmpRspecMethod);
    }
    return result;
    
  }
  
  private ArrayList<Parameter> getRspecParametersFromResourceAdapterMethod(java.lang.reflect.Method method) {
    
    ArrayList<Parameter> result = new ArrayList<Parameter>();
    Class<?>[] paramTypes = method.getParameterTypes();
    for (int i = 0; i < paramTypes.length; i++) {
      Parameter rspecParameter = new Parameter();
      rspecParameter.setType(paramTypes[0].getName());
      // get the method names.
      rspecParameter.setName("arg0" + paramTypes[0].getName());
      result.add(rspecParameter);
    }
    return result;
  }
  
  public ResourceAdapter translateResourceToResourceAdapter(Resource object) {
    List<Property> properties = object.getProperty();
    String type = "";
    ResourceAdapter resource;
    HashMap<String, Object> resourceProperties = new HashMap<String, Object>();
    
    for (Iterator iterator = properties.iterator(); iterator.hasNext();) {
      Property property = (Property) iterator.next();
      if (property.getName().compareToIgnoreCase("type") == 0) {
        type = property.getValue();
        
        break;
      }
    }
    try {
      Class<?> clazz = Class.forName(type);
      resource = (ResourceAdapter) clazz.newInstance();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException();// TODO: change this.
    } catch (InstantiationException e) {
      e.printStackTrace();
      throw new RuntimeException();// TODO: change this.
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      throw new RuntimeException();// TODO: change this.
    }
    
    for (Iterator iterator = properties.iterator(); iterator.hasNext();) {
      Property property = (Property) iterator.next();
      if (property.getName().compareToIgnoreCase("id") == 0) {
        resource.setId(property.getValue());
        continue;
      }
      if (property.getName().compareToIgnoreCase("type") == 0) {
        resource.setType(property.getValue());
        continue;
      }
      if (property.getName().compareToIgnoreCase("status") == 0) {
        // resource.setStatus(property.getValue());
        continue;
      }
      resourceProperties.put(property.getName(), property.getValue());
    }
    resource.setProperties(resourceProperties);
    return resource;
  }
  
  public String translateResourceIdToSliverUrn(String id, String sliceUrn) {
    String[] str = sliceUrn.split("\\+slice\\+");
    String response = str[0] + "+sliver+" + id;
    return response;
  }
  
  public String getIdFromSliverUrn(String urn) {
    String[] str = urn.split("\\+sliver\\+");
    return str[1];
  }
  
  // TODO: TO TEST ONLY
  public Object translateToNode(ResourceAdapter resourceAdapter) {
    NodeContents node = new NodeContents();
    HashMap<String, Object> resourceAdapterProperties = resourceAdapter.getProperties();
    
    if (resourceAdapterProperties != null) {
      
      Set<String> propKeys = resourceAdapterProperties.keySet();
      
      for (Iterator<String> iterator = propKeys.iterator(); iterator.hasNext();) {
        String key = iterator.next();
        // getLoginInfoBla
        List<Object> services = node.getAnyOrRelationOrLocation();
        ServiceContents service = new ServiceContents();
        List<Object> logins = service.getAnyOrLoginOrInstall();
        LoginServiceContents login = new LoginServiceContents();
        
        login.setAuthentication("ssh-keys");
        login.setHostname("testLappy.fiteagle-fuseco.fokus.fraunhofer.de");
        login.setPort("22");
        
        logins.add(new ObjectFactory().createLogin(login));
        
        // service.getAnyOrLoginOrInstall()
        services.add(new ObjectFactory().createServices(service));
        // TODO: add node properties
        
      }
      
    }
    
    return new ObjectFactory().createNode(node);
  }
  

  public Object translateSSHAccesableToAdvertisementNode(ResourceAdapter resourceAdapter) {
    NodeContents node = new NodeContents();
    
    HashMap<String, Object> resourceAdapterProperties = resourceAdapter.getProperties();
    
    if (resourceAdapterProperties != null) {
      
      ObjectFactory factory = new ObjectFactory();

      HashMap<String, Object> resourceProperties = resourceAdapter.getProperties();
      node.setComponentId("urn:publicid:IDN+fiteagle.fuseco.fokus.fraunhofer.de+"+resourceProperties.get("id"));
      node.setComponentManagerId(COMPONENT_MANAGER_ID);
      node.setExclusive(resourceAdapter.isExclusive());
      
      List<Object> nodeContent = node.getAnyOrRelationOrLocation();
      
      AvailableContents available = new AvailableContents();
//      available.setNow((boolean)resourceAdapterProperties.get("available"));
      available.setNow(resourceAdapter.isAvailable());
      nodeContent.add(factory.createAvailable(available));
      
      LocationContents location = new LocationContents();
      location.setCountry((String)resourceAdapterProperties.get("country"));
      location.setLatitude((String)resourceAdapterProperties.get("latitude"));
      location.setLongitude((String)resourceAdapterProperties.get("longitude"));
      nodeContent.add(factory.createLocation(location));
      
      SSHAccessable sshAccesableResource = (SSHAccessable)resourceAdapter;
      
      HardwareTypeContents hardwareType = new HardwareTypeContents();
//      hardwareType.setName((String)resourceAdapterProperties.get("hardwareType"));
      hardwareType.setName(sshAccesableResource.getHardwareType());
      nodeContent.add(factory.createHardwareType(hardwareType));
      
      
    }
    
    return new ObjectFactory().createNode(node);
  }
}
