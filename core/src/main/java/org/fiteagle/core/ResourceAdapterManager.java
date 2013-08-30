package org.fiteagle.core;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.clapper.util.classutil.ClassFilter;
import org.clapper.util.classutil.ClassFinder;
import org.clapper.util.classutil.ClassInfo;
import org.clapper.util.classutil.SubclassClassFilter;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapterStatus;
import org.fiteagle.core.groupmanagement.GroupDBManager;

public class ResourceAdapterManager {
  
  private static final String packageName = "org.fiteagle.adapter";
  private static final String directoryName = System.getProperty("user.home")+"/fiteagle/adapters/";
private static ResourceAdapterManager manager=null;


  private ResourceAdapterDatabase adapterInstancesDatabase;
  private ResourceAdapterDatabase adapterDatabase;
  private ScheduledExecutorService executor;
  private HashMap<String, ScheduledFuture<?>> expirationMap;
private URLClassLoader sysloader;
  private ResourceAdapterManager() {
    if (manager!=null) return;
    sysloader = (URLClassLoader) this.getClass().getClassLoader();
    adapterInstancesDatabase = new InMemoryResourceAdapterDatabase();
    adapterDatabase = new InMemoryResourceAdapterDatabase();
    executor = Executors.newScheduledThreadPool(2);
    expirationMap = new HashMap<>();
    List<Class> allClassesInPackage=null;
    
    try {
    	
    	File directory = new File(directoryName);
		allClassesInPackage = findClassesInDirectory(directory, packageName);
	} catch (ClassNotFoundException | IOException e) {
		throw new RuntimeException();//TODO: give more information in exception
	} catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    if(allClassesInPackage!=null){
    	for (int i = 0; i < allClassesInPackage.size(); i++) {
    		
    		try {
    			Class adapterClass = sysloader.loadClass(allClassesInPackage.get(i).getName());
				if(ResourceAdapter.class.isAssignableFrom(adapterClass) && !(adapterClass.equals(ResourceAdapter.class))){
					
					java.lang.reflect.Method method =adapterClass.getDeclaredMethod("getJavaInstances", null);
					List<ResourceAdapter> resourceAdapters = (List<ResourceAdapter>) method.invoke(null, null);
					adapterInstancesDatabase.addResourceAdapters(resourceAdapters);
//					if(resourceAdapter.isExclusive())
//						adapterInstancesDatabase.addResourceAdapters(resourceAdapters);
				}
			} catch (IllegalAccessException e) {
				throw new RuntimeException();//TODO: give more information in exception
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
  
  
//  private static Class[] getAllClassesInPackage(String packageName)
//			throws ClassNotFoundException, IOException {
//		ClassLoader classLoader = Thread.currentThread()
//				.getContextClassLoader();
//		if(classLoader == null) throw new RuntimeException();//TODO: give more information in exception
//		String path = packageName.replace('.', '/');
//		Enumeration<URL> resources = classLoader.getResources(path);
//		List<File> dirs = new ArrayList<File>();
//		while (resources.hasMoreElements()) {
//			URL resource = resources.nextElement();
//			dirs.add(new File(resource.getFile()));
//		}
//		ArrayList<Class> classes = new ArrayList<Class>();
//		for (File directory : dirs) {
//			classes.addAll(findClassesInDirectory(directory, packageName));
//		}
//		
//		//this has duplicate entries in classes!
//		return classes.toArray(new Class[classes.size()]);
//	}

	private  List<Class> findClassesInDirectory(File directory, String packageName)
			throws ClassNotFoundException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			
					throw new RuntimeException(); //TODO: change this and give appropriate error info!
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if(file.exists() && file.getName().endsWith(".jar")){
			
			classes.addAll(findClassesInJar(file));
			}
		}
		return classes;
	}

	private  List<Class> findClassesInJar(File jarFile) throws ClassNotFoundException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		List<Class> classes = new ArrayList<Class>();
		addURLToSystemClassLoader(jarFile.toURI().toURL());

//		JarFile jar = new JarFile(jarFile);
//		Enumeration<JarEntry> jarEntries = jar.entries();
//		while (jarEntries.hasMoreElements()) {
//			JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
//			if(jarEntry.getName().endsWith(".class")){
//				String clazzName = jarEntry.getName().replace('/', '.');
//				clazzName =  clazzName.substring(0, clazzName.length()-6);
//				Class clazz = Class.forName(clazzName);
//				if(ResourceAdapter.class.isAssignableFrom(clazz))
//					classes.add(clazz);
//			}
//			
//		}
		ClassFilter filter = new SubclassClassFilter(ResourceAdapter.class);
		ClassFinder classFinder = new ClassFinder();
		classFinder.add(jarFile);
		Collection<ClassInfo> foundAdapters = new ArrayList<>();
		classFinder.findClasses(foundAdapters, filter);
		for(ClassInfo info: foundAdapters){
			Class clazz = sysloader.loadClass(info.getClassName());
			classes.add(clazz);
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

	public void setExpires(String resourceId, Date allocationExpirationTime) {
		ScheduledFuture<?> scheduler = executor.schedule(new ExpirationCallback(resourceId), allocationExpirationTime.getTime()-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		expirationMap.put(resourceId,scheduler);
	}
  
	private class ExpirationCallback implements Runnable {

		private String resourceId;
		private String groupId;
		public ExpirationCallback(String resourceId){
			this.resourceId = resourceId;
			
		}
		@Override
		public void run() {
			ResourceAdapter expiredAdapter = adapterInstancesDatabase.getResourceAdapter(resourceId);
			removeAdapterFromGroup(resourceId);
		
			expiredAdapter.setStatus(ResourceAdapterStatus.Available);
			
			
		}
		
	}

	public void removeAdapterFromGroup(String resourceId) {
		GroupDBManager.getInstance().deleteResourceFromGroup(resourceId);
		
		
	}

	public void renewExpirationTime(String resourceId, Date expirationTime) {
	
		ScheduledFuture<?> existentTimer = expirationMap.get(resourceId);
		existentTimer.cancel(false);
		setExpires(resourceId, expirationTime);
		
	}
	
	private void addURLToSystemClassLoader(URL url) { 
		 
		  Class<URLClassLoader> classLoaderClass = URLClassLoader.class; 
		  
		  try { 
		    Method method = classLoaderClass.getDeclaredMethod("addURL", new Class[]{URL.class}); 
		    method.setAccessible(true); 
		    method.invoke(sysloader, new Object[]{url}); 
		  } catch (Throwable t) { 
		    t.printStackTrace(); 
		    
		  } 
		}
  

//  public List<ResourceAdapter> getGroupResources(String groupId) {
//    return groups.get(groupId);
//  }
//
//  public void addGroup(String groupId, List<ResourceAdapter> adapters) {
//    this.groups.put(groupId, adapters);
//  }
  
}
