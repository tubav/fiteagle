package org.fiteagle.adapter.nodeadapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.fiteagle.adapter.common.AdapterConfiguration;
import org.fiteagle.adapter.common.NodeAdapterInterface;
import org.fiteagle.adapter.common.OpenstackResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.nodeadapter.client.model.Image;
import org.fiteagle.adapter.nodeadapter.client.model.Images;

import com.woorea.openstack.nova.model.Flavor;
import com.woorea.openstack.nova.model.Flavors;

public class NodeAdapter extends ResourceAdapter implements NodeAdapterInterface{

	private String id=this.nodeName;//TODO: find out a better name
	
	private List<OpenstackResourceAdapter> images = null;

	private List<Flavor> flavorsList = null;

	private List<OpenstackResourceAdapter> vms = null;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

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

}
