package org.fiteagle.core;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupPersistable;
import org.fiteagle.core.groupmanagement.InMemoryGroupDatabase;

public class ResourceAdapterManager {
  
  private static final String packageName = "org.fiteagle.adapter";

private static ResourceAdapterManager manager=null;


  private ResourceAdapterDatabase adapterInstancesDatabase;
  private ResourceAdapterDatabase adapterDatabase;
 
  
  private ResourceAdapterManager() {
    if (manager!=null) return;
    
    adapterInstancesDatabase = new InMemoryResourceAdapterDatabase();
    adapterDatabase = new InMemoryResourceAdapterDatabase();
    
    
    Class[] allClassesInPackage=null;
    try {
		allClassesInPackage = getAllClassesInPackage(packageName);
	} catch (ClassNotFoundException e) {
		throw new RuntimeException();//TODO: give more information in exception
	} catch (IOException e) {
		throw new RuntimeException();//TODO: give more information in exception
	}
    
    if(allClassesInPackage!=null){
    	for (int i = 0; i < allClassesInPackage.length; i++) {
    		ResourceAdapter resourceAdapter=null;
    		try {
				if(allClassesInPackage[i].newInstance() instanceof ResourceAdapter){
					resourceAdapter = (ResourceAdapter) allClassesInPackage[i].newInstance();
					if(resourceAdapter.isLoaded())
						continue;
					
					List<ResourceAdapter> resourceAdapters = resourceAdapter.getJavaInstances();
					resourceAdapter.setLoaded(true);
					adapterDatabase.addResourceAdapters(resourceAdapters);
				}
			} catch (InstantiationException e) {
				continue;
			} catch (IllegalAccessException e) {
				throw new RuntimeException();//TODO: give more information in exception
			}
		}
    }
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
  

  public ResourceAdapter getResourceAdapterInstance(String instanceId){
    return adapterInstancesDatabase.getResourceAdapter(instanceId);
  }
  
  
 

  public void deleteResource(String resourceAdapterId) {
    adapterInstancesDatabase.deleteResourceAdapter(resourceAdapterId);
  }
  
  
  private static Class[] getAllClassesInPackage(String packageName)
			throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if(classLoader == null) throw new RuntimeException();//TODO: give more information in exception
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClassesInDirectory(directory, packageName));
		}
		
		//this has duplicate entries in classes!
		return classes.toArray(new Class[classes.size()]);
	}

	private static List<Class> findClassesInDirectory(File directory, String packageName)
			throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			if(directory.getPath().contains("!")){
				String[] tmp = directory.getPath().split("!");
				if(tmp[0].startsWith("file:"))
					tmp[0]=tmp[0].substring(5);
				JarFile jarFile=null;
				try {
					jarFile = new JarFile(tmp[0]);
				} catch (IOException e) {
					throw new RuntimeException(); //TODO: change this and give appropriate error info!
				}
				return findClassesInJar(jarFile, tmp[1]);
			}
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				if(file.getName().contains(".")) throw new RuntimeException();//TODO: give more information in exception
				classes.addAll(findClassesInDirectory(file,
						packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName
						+ '.'
						+ file.getName().substring(0,
								file.getName().length() - 6)));
			}
		}
		return classes;
	}

	private static List<Class> findClassesInJar(JarFile jarFile, String pathInJar) throws ClassNotFoundException {
		if(jarFile==null) return null;
		List<Class> classes = new ArrayList<Class>();
		if(pathInJar.startsWith("/"))
			pathInJar=pathInJar.substring(1);
		
		if(!pathInJar.endsWith("/"))
			pathInJar=pathInJar+"/";
		
		Enumeration<JarEntry> jarEntries = jarFile.entries();
		while (jarEntries.hasMoreElements()) {
			JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
			if(jarEntry.getName().contains(pathInJar)&&jarEntry.getName().endsWith(".class")){
				String clazzName = jarEntry.getName().replace('/', '.');
				Class clazz = (Class) Class.forName(clazzName.substring(0, clazzName.length()-6));
				classes.add(clazz);
			}
			
		}
		return classes;
	}

	public List<ResourceAdapter> getResourceAdapterInstancesById(
			List<String> resourceIds) {
		List<ResourceAdapter> resources = new LinkedList<>();
		for(String resourceId:resourceIds){
			resources.add(adapterInstancesDatabase.getResourceAdapter(resourceId));
		}
		return resources;
	}
  
  

//  public List<ResourceAdapter> getGroupResources(String groupId) {
//    return groups.get(groupId);
//  }
//
//  public void addGroup(String groupId, List<ResourceAdapter> adapters) {
//    this.groups.put(groupId, adapters);
//  }
  
}
