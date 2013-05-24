package org.fiteagle.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.stopwatch.StopwatchAdapter;

public class ResourceAdapterManager {
  
  private static ResourceAdapterManager manager=null;
  
  private ResourceAdapterDatabase adapterInstancesDatabase;//TODO: This will be filled while creating resources
  private ResourceAdapterDatabase adapterDatabase;//TODO: this will be filled, while adding a resource adapter
  private GroupDatabase groups;
//  private HashMap<String, List<ResourceAdapter>> groups;
  
  
  public ResourceAdapterManager() {
    adapterInstancesDatabase = new InMemoryResourceAdapterDatabase();
    adapterDatabase = new InMemoryResourceAdapterDatabase();
    groups=new InMemoryGroupDatabase();
//    groups = new HashMap<String, List<ResourceAdapter>>();
    
    //TODO: add the resource adapters with their groups over registry
    ResourceAdapter dummyResourceAdapter = new StopwatchAdapter();
    ArrayList<ResourceAdapter> adapterList= new ArrayList<ResourceAdapter>();
    String sliceURN= "urn:publicid:IDN+fiteagle:av+slice+myslice";
    adapterDatabase.addResourceAdapter(dummyResourceAdapter);
    String ownerURN = "urn:publicid:IDN+fiteagle:av+user+testUser";
//    this.addGroup(ownerURN, sliceURN, adapterList);
    
  }
  
  public List<ResourceAdapter> getResourceAdapters() {
    
    return adapterDatabase.getResourceAdapters();
    
  }
  
  public void addResourceAdapter(ResourceAdapter resourceAdapter) {
    adapterDatabase.addResourceAdapter(resourceAdapter);
  }
  
  public void addResourceAdapterInstance(ResourceAdapter resourceAdapter) {
    adapterInstancesDatabase.addResourceAdapter(resourceAdapter);
  }
  
  public List<ResourceAdapter> getResourceAdapterInstances() {
    return adapterInstancesDatabase.getResourceAdapters();
    
  }
  
  public void addGroup(Group group){
    groups.addGroup(group);
  }
  
  public Group getGroup(String groupId){
    return groups.getGroup(groupId);
  }
  
  

//  public List<ResourceAdapter> getGroupResources(String groupId) {
//    return groups.get(groupId);
//  }
//
//  public void addGroup(String groupId, List<ResourceAdapter> adapters) {
//    this.groups.put(groupId, adapters);
//  }
  
}
