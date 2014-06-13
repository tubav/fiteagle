package org.fiteagle.adapter.nodeadapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.fiteagle.adapter.common.AdapterConfiguration;
import org.fiteagle.adapter.common.NodeAdapterInterface;
import org.fiteagle.adapter.common.OpenstackResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapterStatus;
import org.fiteagle.adapter.nodeadapter.client.model.Image;
import org.fiteagle.adapter.nodeadapter.client.model.Images;

import com.woorea.openstack.nova.model.Flavor;
import com.woorea.openstack.nova.model.Flavors;

public class NodeAdapter extends ResourceAdapter implements NodeAdapterInterface{

	private String id=this.nodeName;//TODO: find out a better name
	
	private List<OpenstackResourceAdapter> images = null;

	private List<Flavor> flavorsList = null;

	private List<OpenstackResourceAdapter> vms = null;
	
	private ScheduledExecutorService executor;
	  private HashMap<OpenstackResourceAdapter, ScheduledFuture<?>> expirationMap;

	public static List<ResourceAdapter> getJavaInstances() {

		NodeAdapter nodeAdapter = new NodeAdapter();

		List<OpenstackResourceAdapter> openstackVMAdapters = OpenstackVMAdapter
				.getOpenstackVMAdapters();

		nodeAdapter.setFlavorsList(((OpenstackVMAdapter)openstackVMAdapters.get(0)).getFlavorsList());

		nodeAdapter.setImages(openstackVMAdapters);

		ArrayList<ResourceAdapter> nodeAdapterlist = new ArrayList<ResourceAdapter>();
		nodeAdapterlist.add(nodeAdapter);

		return nodeAdapterlist;

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void configure(AdapterConfiguration configuration) {
		
		for (Iterator iterator = vms.iterator(); iterator.hasNext();) {
			OpenstackResourceAdapter vm = (OpenstackResourceAdapter) iterator.next();
			vm.configure(configuration);
		}

	}

	@Override
	public void release() {
		for (Iterator iterator = vms.iterator(); iterator.hasNext();) {
			OpenstackResourceAdapter vm = (OpenstackResourceAdapter) iterator.next();
			vm.release();
		}
	}

	@Override
	public boolean isLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLoaded(boolean loaded) {
		// TODO Auto-generated method stub

	}

	
	public List<OpenstackResourceAdapter> getImages() {
		if(images==null) images = new ArrayList<OpenstackResourceAdapter>();
		return images;
	}

	public void setImages(List<OpenstackResourceAdapter> images) {
		this.images = images;
	}

	public List<Flavor> getFlavorsList() {
		return flavorsList;
	}

	public void setFlavorsList(List<Flavor> flavorsList) {
		this.flavorsList = flavorsList;
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public NodeAdapter create(String id, List<OpenstackResourceAdapter> vms) {
		
		NodeAdapter result = new NodeAdapter();
		if(id==null || id.compareTo("")==0) id = UUID.randomUUID().toString();
		result.setId(id);
		result.setVms(vms);
		result.setImages(this.getImages());
		result.setFlavorsList(this.getFlavorsList());
		return result;
	}

	public List<OpenstackResourceAdapter> getVms() {
		if (vms==null) return new ArrayList<OpenstackResourceAdapter>();
		return vms;
	}

	public void setVms(List<OpenstackResourceAdapter> vms) {
		this.vms = vms;
	}
	
	public void setExpires(OpenstackResourceAdapter openstackResource, Date allocationExpirationTime) {
		ScheduledFuture<?> scheduler = executor.schedule(new ExpirationCallback(openstackResource), allocationExpirationTime.getTime()-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		expirationMap.put(openstackResource,scheduler);
	}
  
	private class ExpirationCallback implements Runnable {

		private OpenstackResourceAdapter openstackResource;
		public ExpirationCallback(OpenstackResourceAdapter openstackResource){
			this.openstackResource = openstackResource;
			
		}
		@Override
		public void run() {
			
//			for (int i = 0; i < vms.size(); i++) {
//				if(openstackResource.getId().compareToIgnoreCase(vms.get(i).getId())==0)
//				vms.remove(i);
//			}
			
		}
		
	}

}
