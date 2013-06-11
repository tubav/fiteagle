package org.fiteagle.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.SSHAccessable;
import org.fiteagle.adapter.stopwatch.StopwatchAdapter;
import org.fiteagle.adapter.sshdeployadapter.SSHDeployAdapter;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDatabase;
import org.fiteagle.core.groupmanagement.InMemoryGroupDatabase;

public class ResourceAdapterManager {
  
  private static ResourceAdapterManager manager=null;
  
  private ResourceAdapterDatabase adapterInstancesDatabase;//TODO: This will be filled while creating resources
  private ResourceAdapterDatabase adapterDatabase;//TODO: this will be filled, while adding a resource adapter
  private GroupDatabase groups;
//  private HashMap<String, List<ResourceAdapter>> groups;
  
  
  private ResourceAdapterManager() {
    if (manager!=null) return;
    
    adapterInstancesDatabase = new InMemoryResourceAdapterDatabase();
    adapterDatabase = new InMemoryResourceAdapterDatabase();
    groups=new InMemoryGroupDatabase();
//    groups = new HashMap<String, List<ResourceAdapter>>();
    
    //TODO: add the resource adapters over registry
    ResourceAdapter dummyResourceAdapter = new StopwatchAdapter();
    adapterDatabase.addResourceAdapter(dummyResourceAdapter);
    
    ResourceAdapter dummyResourceAdapterHardware = new SSHDeployAdapter();
//    dummyResourceAdapter.setAvailable(false);
//    dummyResourceAdapterHardware.setAvailable(false);
    ((SSHAccessable)dummyResourceAdapterHardware).setHardwareType("demolaptop");
    
    dummyResourceAdapterHardware.setExclusive(true);
    
    HashMap<String, Object> dummyHardwareProps = dummyResourceAdapterHardware.getProperties();
//    dummyHardwareProps.put("exclusive", true);
    dummyHardwareProps.put("id","laptop");
    adapterDatabase.addResourceAdapter(dummyResourceAdapterHardware);

    manager=this;
  }
  
  public static ResourceAdapterManager getInstance(){
    if (manager!=null) return manager;
    return new ResourceAdapterManager();
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
  
  public ResourceAdapter getResourceAdapterInstance(String instanceId){
    return adapterInstancesDatabase.getResourceAdapter(instanceId);
  }
  
  public Group getGroupOfInstance(String instanceId){
    
    List<Group> groupList = groups.getGroups();
    
    for (Iterator iterator = groupList.iterator(); iterator.hasNext();) {
      Group group = (Group) iterator.next();
      List<ResourceAdapter> instances = group.getResources();
      for (Iterator iterator2 = instances.iterator(); iterator2.hasNext();) {
        ResourceAdapter resourceAdapter = (ResourceAdapter) iterator2.next();
        if(resourceAdapter.getId().compareTo(instanceId)==0)
          return group;
      }
    }
    
    return null;
    
  }

  public void deleteResource(String resourceAdapterId) {
    adapterInstancesDatabase.deleteResourceAdapter(resourceAdapterId);
    Group groupOfResourceAdapter = getGroupOfInstance(resourceAdapterId);
    groupOfResourceAdapter.deleteResource(resourceAdapterId);
  }
  
  

//  public List<ResourceAdapter> getGroupResources(String groupId) {
//    return groups.get(groupId);
//  }
//
//  public void addGroup(String groupId, List<ResourceAdapter> adapters) {
//    this.groups.put(groupId, adapters);
//  }
  
}
