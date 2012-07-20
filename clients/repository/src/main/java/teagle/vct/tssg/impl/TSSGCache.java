/**
 * 
 */
package teagle.vct.tssg.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;

import teagle.vct.model.ConstraintViolation;
import teagle.vct.model.RepositoryException;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.AsyncWebResource.Builder;

/**
 * @author sim
 *
 */
public class TSSGCache<T extends TSSGObject> {

	private String typePath;
	
	private Map<String, T> cache = new HashMap<String, T>();

	public Map<String, T> getCache() {
		return cache;
	}

	private T[] dummy;

	private boolean prefetching = true;
	
	protected TSSGCache(String typePath, T[] dummy) {
		this.typePath = typePath;
		this.dummy = dummy;
	}
	
	protected void clear() {
		cache.clear();
	}
	
	protected T find(String id) {

		if (cache.isEmpty() && prefetching) {
			loadAll();
		}
		
		T element = null;
		if (id != null) {
			element = cache.get(id);
			if (element == null && !cache.isEmpty()) {
				try {
					element = (T)TSSGClient.getWebResource().path("/" + typePath + "/" + id).type(MediaType.TEXT_XML).get(cache.values().iterator().next().getClass());
				} catch (UniformInterfaceException e) {
					e.printStackTrace();
				}
			}
		}
		return element;
	}

	protected List<T> list() {
		if (cache.isEmpty()) {
			loadAll();
		}
		return new ArrayList<T>(cache.values());
	}

	@SuppressWarnings("unchecked")
	protected T persist(T element) {
		
/*		if ((element.getClass().getName() == "teagle.vct.tssg.impl.TSSGVct")){
			if (((TSSGVct)element).exist(((TSSGVct)element).commonName)){
				((TSSGVct)element).setId(((TSSGVct)element).existingVct(((TSSGVct)element).commonName).getId());
				Object instance = element.getInstance();
				System.out.print("storing " + typePath + " element with id " + element.getId() + "...");
				TSSGClient.getWebResource().path("/" + typePath + "/" + element.getId()).type(MediaType.TEXT_XML).put(instance);
				System.out.println("done");
				element.flag = false;
			} else {
				Object instance = element.getInstance();
				System.out.print("...creating element of type " + typePath + "...");
				try{
					element = (T)TSSGClient.getWebResource().path("/" + typePath).type(MediaType.TEXT_XML).post(element.getClass(), instance);
				} catch (UniformInterfaceException e) {
					e.printStackTrace();
				}
				
				System.out.println("done. assigned id is " + element.getId());
				cache.put(element.getId(), element);
				
			}
		}*/
		T newElement = element;
		if (element.getId() != null) {
			if (element.flag) {
				Object instance = element.getInstance();
				System.out.print("storing " + typePath + " element with id " + element.getId() + "...");
				TSSGClient.getWebResource().path("/" + typePath + "/" + element.getId()).type(MediaType.TEXT_XML).put(instance);
				System.out.println("done");
				element.flag = false;
			}
			
		} else {
			Object instance = element.getInstance();
			System.out.print("creating element of type " + typePath + "...");
		
			try{
				newElement = (T)TSSGClient.getWebResource().path("/" + typePath).type(MediaType.TEXT_XML).post(element.getClass(), instance);
			} catch (UniformInterfaceException e) {
				e.printStackTrace();
				
			}
			
			element.setId(newElement.getId());
			System.out.println("done. assigned id is " + element.getId());
			cache.put(element.getId(), newElement);
			
		}
		return newElement;
	}
	
	
	protected void delete(T element) throws RepositoryException {
		if (element.getId() != null) {
			cache.remove(element.getId());
			System.out.print("deleting " + typePath + " element with id " + element.getId() + "...");
			try
			{
				TSSGClient.getWebResource().path("/" + typePath + "/" + element.getId()).type(MediaType.TEXT_XML).delete();
			}
			catch (UniformInterfaceException e)
			{
				if (e.toString().contains("returned a response status of 405"))
					throw new ConstraintViolation("Repo refused deletion of " + typePath + " with id " + element.getId(), e);
				throw new RepositoryException(e);
			}
			System.out.println("done");
		}
	}
	
	private synchronized void loadAll() {
		if (!cache.isEmpty()) {
			return;
		}
		
		try {
			System.out.println("loading " + typePath);
			long watch = System.currentTimeMillis();
			T[] array = (T[])TSSGClient.getWebResource().path("/" + typePath).type(MediaType.TEXT_XML).get(dummy.getClass());			
			System.out.println("list of " + typePath + " loaded in " + (System.currentTimeMillis() - watch) + " ms, containing " + array.length + " elements");
			
			for (T resource : array) {
				cache.put(resource.id, resource);
			}		
		} catch (UniformInterfaceException e) {
			System.out.println("UniformInterfaceException");
			e.printStackTrace();
		}
		catch (ClientHandlerException e)
		{
			System.out.println("ClientHandlerException");
			e.printStackTrace();
		}
		
		System.out.println("load " + typePath + " out");
	}

}
