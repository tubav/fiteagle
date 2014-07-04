package org.fiteagle.interactors.sfa.renew;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.eclipse.persistence.jpa.jpql.parser.ResultVariable;
import org.fiteagle.adapter.common.NodeAdapterInterface;
import org.fiteagle.adapter.common.OpenstackResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.ResourceAdapterManager.ResourceNotFound;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.groupmanagement.GroupDBManager.GroupNotFound;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.GENISliverAllocationState;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.delete.DeleteOptions;
import org.fiteagle.interactors.sfa.delete.DeleteResult;
import org.fiteagle.interactors.sfa.provision.ProvisionRequestProcessor;
import org.fiteagle.interactors.sfa.renewSlice.RenewSliceRequestProcessor;
import org.fiteagle.interactors.sfa.rspec.manifest.ManifestRspecTranslator;
import org.fiteagle.interactors.sfa.rspec.manifest.RSpecContents;

public class RenewRequestProcessor extends SFAv3RequestProcessor {
	
	private ResourceAdapterManager resourceManager;
	private GENI_CodeEnum code = GENI_CodeEnum.SUCCESS;
	private GroupDBManager groupManager;
	
	public RenewRequestProcessor(ResourceAdapterManager rm, GroupDBManager gdbm) {
		this.resourceManager = rm;
		this.groupManager = gdbm;
	}

	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		return null;
	}
	
	public RenewResult processRequest(List<String> urns, String expirationTime, ListCredentials credentials, RenewOptions renewOptions) {
		//TODO: get the slivers over urns if openstack => setExpTime
		AMCode returnCode = getReturnCode(code);
		RenewResult result = new RenewResult();
		ArrayList<GeniSlivers> slivers = new ArrayList<GeniSlivers>();
		
		ProvisionRequestProcessor helperRequestProcessr = new ProvisionRequestProcessor();
		
		List<URN> urnList = helperRequestProcessr.parseURNS(urns);
		
		
		
		if (urnList.size() == 1
				&& urnList.get(0).getType().equalsIgnoreCase("slice")) {
			
			Group group = null;

			try {
				group = GroupDBManager.getInstance().getGroup(
						urnList.get(0).getSubjectAtDomain());
			} catch (GroupNotFound e) {

				code = GENI_CodeEnum.SEARCHFAILED;
				returnCode = getReturnCode(code);
				result.setCode(returnCode);
				result.setValue(slivers);
				return result;
			}

			List<String> resourceIds = group.getResources();
			List<ResourceAdapter> resources = resourceManager
					.getResourceAdapterInstancesById(resourceIds);

			for (ResourceAdapter resourceAdapter : resources) {
				if (NodeAdapterInterface.class.isAssignableFrom(resourceAdapter
						.getClass())) {
					
					NodeAdapterInterface nodeAdapter = (NodeAdapterInterface)resourceAdapter;
					List<OpenstackResourceAdapter> openstackResourceAdapters = nodeAdapter.getVms();
					for (Iterator iterator = openstackResourceAdapters
							.iterator(); iterator.hasNext();) {
						OpenstackResourceAdapter openstackResourceAdapter = (OpenstackResourceAdapter) iterator
								.next();
						
						Date expirationTimeDate=new RenewSliceRequestProcessor(null, groupManager).getDateFromString(expirationTime);
						openstackResourceAdapter.setExpirationTime(expirationTimeDate);
						resourceManager.renewExpirationTime(openstackResourceAdapter.getId(), expirationTimeDate);
						
					}
					
				}
				

				if (NodeAdapterInterface.class.isAssignableFrom(resourceAdapter
						.getClass())) {
					NodeAdapterInterface nodeAdapter = (NodeAdapterInterface) resourceAdapter;
					List<GeniSlivers> sliverList = helperRequestProcessr.buildSliversForNodeAdapter(nodeAdapter);
					if (sliverList != null)
						slivers.addAll(sliverList);
				} else {
					GeniSlivers sliver = helperRequestProcessr.buildSliver(resourceAdapter);
					if (sliver != null)
						slivers.add(sliver);
				}
			}
			
			
//			ManifestRspecTranslator translator = new ManifestRspecTranslator();
//			RSpecContents manifestRSpec = translator.getManifestRSpec(resources);
//			String geni_rspec = translator.getRSpecString(manifestRSpec);
//			resultValue.setGeni_rspec(geni_rspec);
			
		} else {
			
			//urns are sliver urns
			//TODO: test this part!
			
			
			
			for (String urn : urns) {
				URN u = new URN(urn);
				String id = u.getSubject();
				try {
					// TODO: get the node if its openstack
					ArrayList<String> adapterIds = new ArrayList<String>();
					adapterIds.add(id);
					List<ResourceAdapter> resAdapterAsList = resourceManager.getResourceAdapterInstancesById(adapterIds);
					
					//TODO: get from string the expi time: 
					
					Date expirationTimeDate=new RenewSliceRequestProcessor(null, groupManager).getDateFromString(expirationTime);
					resAdapterAsList.get(0).setExpirationTime(expirationTimeDate);
					resourceManager.renewExpirationTime(id, expirationTimeDate);
					
					
				} catch (ResourceNotFound e) {
					code = GENI_CodeEnum.SEARCHFAILED;
					returnCode = getReturnCode(code);
					result.setCode(returnCode);
					result.setValue(slivers);
					return result;
				}
				
				
				//--------
				ArrayList<String> adapterIdStrings = new ArrayList<String>();
				adapterIdStrings.add(id);
				List<ResourceAdapter> resources = resourceManager.getResourceAdapterInstancesById(adapterIdStrings);
				
				ProvisionRequestProcessor helperProcessor = new ProvisionRequestProcessor();
				
				for (ResourceAdapter resourceAdapter : resources) {

					if (NodeAdapterInterface.class.isAssignableFrom(resourceAdapter
							.getClass())) {
						NodeAdapterInterface nodeAdapter = (NodeAdapterInterface) resourceAdapter;
						List<GeniSlivers> sliverList = helperProcessor.buildSliversForNodeAdapter(nodeAdapter);
						if (sliverList != null)
							slivers.addAll(sliverList);
					} else if (OpenstackResourceAdapter.class.isAssignableFrom(resourceAdapter
							.getClass())) {
						OpenstackResourceAdapter openstackAdapter = (OpenstackResourceAdapter) resourceAdapter;
						String nodeId = openstackAdapter.getParentNodeId();
						
						if (nodeId == null) {
							GeniSlivers sliver = helperProcessor.buildSliver(resourceAdapter);
							if (sliver != null)
								slivers.add(sliver);
							continue;
						}
						
						ArrayList<String> nodeIdAsList = new ArrayList<String>();
						nodeIdAsList.add(nodeId);
						List<ResourceAdapter> nodeAdapterAsList = resourceManager.getResourceAdapterInstancesById(nodeIdAsList);
						NodeAdapterInterface nodeAdapter = (NodeAdapterInterface) nodeAdapterAsList.get(0);
						List<GeniSlivers> sliverList = helperProcessor.buildSliversForNodeAdapter(nodeAdapter);
						if (sliverList != null)
							slivers.addAll(sliverList);
						
					} else {
						GeniSlivers sliver = helperProcessor.buildSliver(resourceAdapter);
						if (sliver != null)
							slivers.add(sliver);
					}
				}
				//--------
				
				
			}
			
			ArrayList<String> adapterIds = new ArrayList<String>();
			adapterIds.add(urnList.get(0).getSubject());
			List<ResourceAdapter> resAdapterAsList = resourceManager.getResourceAdapterInstancesById(adapterIds);//TODO: check this!!!!!!!
			
//			resultValue.setGeni_slivers(slivers);
//			ManifestRspecTranslator translator = new ManifestRspecTranslator();
//			RSpecContents manifestRSpec = translator.getManifestRSpec(resAdapterAsList); //TODO: check this!!!!!!!!!!
//			String geni_rspec = translator.getRSpecString(manifestRSpec);
//			resultValue.setGeni_rspec(geni_rspec);
			
			
		}
		
			
			
			//TODO: get slivers
			//TODO: get the rspec and send it back
			//------
//			getSlivers
//			getResources
			
			
			//------
			
			
			
//			GeniSlivers tmpSliver = new GeniSlivers();
//
//			tmpSliver.setGeni_sliver_urn(urn);
//			tmpSliver
//					.setGeni_allocation_status(GENISliverAllocationState.geni_unallocated
//							.toString());
//			// tmpSliver.setGeni_expires(geni_expires);
//			slivers.add(tmpSliver);
		
		returnCode = getReturnCode(code);
		result.setCode(returnCode);
		result.setValue(slivers);
		return result;

	}

	private void addSliverToSliversForResourceId(String id) {
		// TODO Auto-generated method stub
		
	}

}
