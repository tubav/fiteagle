/**
 * 
 */
package teagle.vct.tssg.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import teagle.vct.model.ConstraintViolation;
import teagle.vct.model.RepositoryException;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

/**
 * @author sim
 * 
 */
public class TSSGCache<T extends TSSGObject> {

	private final String typePath;

	private final Map<String, T> cache = new HashMap<String, T>();

	public Map<String, T> getCache() {
		return this.cache;
	}

	private final T[] dummy;

	private final boolean prefetching = true;

	protected TSSGCache(final String typePath, final T[] dummy) {
		this.typePath = typePath;
		this.dummy = dummy;
	}

	protected void clear() {
		this.cache.clear();
	}

	protected T find(final String id) {

		if (this.cache.isEmpty() && this.prefetching)
			this.loadAll();

		T element = null;
		if (id != null) {
			element = this.cache.get(id);
			if ((element == null) && !this.cache.isEmpty())
				try {
					element = (T) TSSGClient
							.getWebResource()
							.path("/" + this.typePath + "/" + id)
							.type(MediaType.TEXT_XML)
							.get(this.cache.values().iterator().next()
									.getClass());
				} catch (final UniformInterfaceException e) {
					e.printStackTrace();
				}
		}
		return element;
	}

	protected List<T> list() {
		if (this.cache.isEmpty())
			this.loadAll();
		return new ArrayList<T>(this.cache.values());
	}

	@SuppressWarnings("unchecked")
	protected T persist(final T element) {

		/*
		 * if ((element.getClass().getName() ==
		 * "teagle.vct.tssg.impl.TSSGVct")){ if
		 * (((TSSGVct)element).exist(((TSSGVct)element).commonName)){
		 * ((TSSGVct)element
		 * ).setId(((TSSGVct)element).existingVct(((TSSGVct)element
		 * ).commonName).getId()); Object instance = element.getInstance();
		 * System.out.print("storing " + typePath + " element with id " +
		 * element.getId() + "..."); TSSGClient.getWebResource().path("/" +
		 * typePath + "/" +
		 * element.getId()).type(MediaType.TEXT_XML).put(instance);
		 * System.out.println("done"); element.flag = false; } else { Object
		 * instance = element.getInstance();
		 * System.out.print("...creating element of type " + typePath + "...");
		 * try{ element = (T)TSSGClient.getWebResource().path("/" +
		 * typePath).type(MediaType.TEXT_XML).post(element.getClass(),
		 * instance); } catch (UniformInterfaceException e) {
		 * e.printStackTrace(); }
		 * 
		 * System.out.println("done. assigned id is " + element.getId());
		 * cache.put(element.getId(), element);
		 * 
		 * } }
		 */
		T newElement = element;
		if (element.getId() != null) {
			if (element.flag) {
				final Object instance = element.getInstance();
				System.out.print("storing " + this.typePath
						+ " element with id " + element.getId() + "...");
				TSSGClient.getWebResource()
						.path("/" + this.typePath + "/" + element.getId())
						.type(MediaType.TEXT_XML).put(instance);
				System.out.println("done");
				element.flag = false;
			}

		} else {
			final Object instance = element.getInstance();
			System.out.print("creating element of type " + this.typePath
					+ "...");

			try {
				newElement = (T) TSSGClient.getWebResource()
						.path("/" + this.typePath).type(MediaType.TEXT_XML)
						.post(element.getClass(), instance);
			} catch (final UniformInterfaceException e) {
				e.printStackTrace();

			}

			element.setId(newElement.getId());
			System.out.println("done. assigned id is " + element.getId());
			this.cache.put(element.getId(), newElement);

		}
		return newElement;
	}

	protected void delete(final T element) throws RepositoryException {
		if (element.getId() != null) {
			this.cache.remove(element.getId());
			System.out.print("deleting " + this.typePath + " element with id "
					+ element.getId() + "...");
			try {
				TSSGClient.getWebResource()
						.path("/" + this.typePath + "/" + element.getId())
						.type(MediaType.TEXT_XML).delete();
			} catch (final UniformInterfaceException e) {
				if (e.toString().contains("returned a response status of 405"))
					throw new ConstraintViolation("Repo refused deletion of "
							+ this.typePath + " with id " + element.getId(), e);
				throw new RepositoryException(e);
			}
			System.out.println("done");
		}
	}

	private synchronized void loadAll() {
		if (!this.cache.isEmpty())
			return;

		try {
			System.out.println("loading " + this.typePath);
			final long watch = System.currentTimeMillis();
			final T[] array = (T[]) TSSGClient.getWebResource()
					.path("/" + this.typePath).type(MediaType.TEXT_XML)
					.get(this.dummy.getClass());
			System.out.println("list of " + this.typePath + " loaded in "
					+ (System.currentTimeMillis() - watch) + " ms, containing "
					+ array.length + " elements");

			for (final T resource : array)
				this.cache.put(resource.id, resource);
		} catch (final UniformInterfaceException e) {
			System.out.println("UniformInterfaceException");
			e.printStackTrace();
		} catch (final ClientHandlerException e) {
			System.out.println("ClientHandlerException");
			e.printStackTrace();
		}

		System.out.println("load " + this.typePath + " out");
	}

}
