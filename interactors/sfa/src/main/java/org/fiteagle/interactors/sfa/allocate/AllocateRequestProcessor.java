package org.fiteagle.interactors.sfa.allocate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.fiteagle.adapter.common.OpenstackResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapterStatus;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.groupmanagement.GroupDBManager.GroupNotFound;
import org.fiteagle.core.util.URN;
import org.fiteagle.core.util.URN.URNParsingException;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.GENISliverAllocationState;
import org.fiteagle.interactors.sfa.common.GENISliverOperationalState;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.OpenstackResource;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.VmToInstantiate;
import org.fiteagle.interactors.sfa.rspec.ext.Property;
import org.fiteagle.interactors.sfa.rspec.ext.Resource;
import org.fiteagle.interactors.sfa.rspec.manifest.ManifestRspecTranslator;
import org.fiteagle.interactors.sfa.rspec.request.NodeContents;
import org.fiteagle.interactors.sfa.rspec.request.RSpecContents;
import org.fiteagle.interactors.sfa.util.DateUtil;

public class AllocateRequestProcessor extends SFAv3RequestProcessor {

	private ResourceAdapterManager resourceManager;

	public AllocateResult processRequest(String urn,
			ListCredentials credentials, RSpecContents requestRspec,
			AllocateOptions allocateOptions) {
		// TODO:get user name from credentials and correct this
		AllocateResult result = getResult(urn, credentials, requestRspec,
				"testUser");
		return result;
	}

	private AllocateResult getResult(String urn, ListCredentials credentials,
			RSpecContents requestRspec, String groupOwnerId) {
		String value = "";
		String output = "";
		AMCode returnCode = null;
		AllocateValue allocateValue = new AllocateValue();
		AllocateResult result = new AllocateResult();
		boolean validArguments = true;
		URN sliceURN = null;
		try {
			sliceURN = new URN(urn);
		} catch (URNParsingException e) {
			
			returnCode = getReturnCode(GENI_CodeEnum.BADARGS);
			validArguments = false;
		}

		// TODO CHECK OTHER ARGS
		if (validArguments) {
			Group group = null;
			boolean groupFound = true;
			try {
				group = GroupDBManager.getInstance().getGroup(
						sliceURN.getSubjectAtDomain());

			} catch (GroupNotFound e) {
				groupFound = false;
				returnCode = getReturnCode(GENI_CodeEnum.SEARCHFAILED);
				
			}
			if (groupFound) {
				Authorization auth = new Authorization();

				auth.checkCredentialsList(credentials);

				if (!auth.areCredentialTypeAndVersionValid()) {
					returnCode = auth.getReturnCode();
					output = auth.getAuthorizationFailMessage();
					result.setCode(returnCode);
					result.setOutput(output);
					return result;
				}

				List<Object> rspecRequestedResources = requestRspec
						.getAnyOrNodeOrLink();

				ArrayList<ResourceAdapter> resourcesList = new ArrayList<ResourceAdapter>();

				boolean allocationSuccess = true;
				for (Iterator iterator = rspecRequestedResources.iterator(); iterator
						.hasNext();) {
					Object object = (Object) iterator.next();
					if (JAXBElement.class.isAssignableFrom(object.getClass())) {
						JAXBElement jaxbElem = (JAXBElement) object;
						ResourceAdapter resource = null;
						if (Resource.class.isAssignableFrom(jaxbElem.getValue()
								.getClass())) {
							
							Resource jaxBResource = (Resource) jaxbElem.getValue();
							
							String instanceId = "";
							for(Property p: jaxBResource.getProperty()){
								if(p.getName().equalsIgnoreCase("id")){
									instanceId = p.getValue();
								}
							}
							resource = resourceManager.getResourceAdapterInstance(instanceId);
						}
						
						if (OpenstackResource.class.isAssignableFrom(jaxbElem.getValue()
								.getClass())) {
							
							OpenstackResource openstackResource = (OpenstackResource) jaxbElem.getValue();
							
							VmToInstantiate vmToInstantiate = openstackResource.getVmToInstantiate();
							
							OpenstackResourceAdapter openstackResourceAdapter = (OpenstackResourceAdapter)resourceManager.getResourceAdapterInstance(openstackResource.getResourceId());
//							resource = (ResourceAdapter) openstackResourceAdapter.create(vmToInstantiate.getImageId(), vmToInstantiate.getFlavorId(), vmToInstantiate.getVmName(),vmToInstantiate.getKeyPairName() , this.getUserCertificate());
							resource = (ResourceAdapter) openstackResourceAdapter.create(vmToInstantiate.getImageId(), vmToInstantiate.getFlavorId(), vmToInstantiate.getVmName(),vmToInstantiate.getKeyPairName());
							
							resourceManager.addResourceAdapterInstance(resource);
						}
						
						
						if (NodeContents.class.isAssignableFrom(jaxbElem
								.getValue().getClass())) {
							NodeContents node = (NodeContents) jaxbElem
									.getValue();
							String id = node.getClientId();
//							if (node.getComponentId().contains("+")) {
//								String[] splitted = node.getComponentId()
//										.split("\\+");
//								id = splitted[splitted.length - 1];
//							} else {
//								id = node.getComponentId();
//							}
							
							resource = resourceManager
									.getResourceAdapterInstance(id);
						}
						
						if (resource != null
								&& !(resource.isExclusive() && !resource
										.getStatus().equals(ResourceAdapterStatus.Available))) {
							resource.getProperties()
									.put("operational_status",
											GENISliverOperationalState.geni_pending_allocation
													.toString());
							resource.getProperties().put(
									"allocation_status",
									GENISliverAllocationState.geni_allocated
											.toString());
							resource.setStatus(ResourceAdapterStatus.Reserved);
							Date allocationExpirationTime = Calendar.getInstance().getTime();
							allocationExpirationTime.setTime(allocationExpirationTime.getTime() + 10 * 1000 * 60);
							resource.setExpirationTime(allocationExpirationTime);
							resourceManager.setExpires(resource.getId(), allocationExpirationTime);
							resourcesList.add(resource);
						} else {
							allocationSuccess = false;
							break;
						}
					}

				}
				if (allocationSuccess) {

					for (ResourceAdapter rs : resourcesList) {
						group.addResource(rs);
						GroupDBManager.getInstance().updateGroup(group);
					}

					returnCode = getReturnCode(GENI_CodeEnum.SUCCESS);
					allocateValue = getValue(urn);

				} else {
					returnCode = getReturnCode(GENI_CodeEnum.BUSY);

				}
			}
		}
		result.setCode(returnCode);
		result.setValue(allocateValue);
		
		return result;
	}

	private AllocateValue getValue(String urn) {
		AllocateValue resultValue = new AllocateValue();
		Group group = GroupDBManager.getInstance().getGroup(
				new URN(urn).getSubjectAtDomain());
		ArrayList<GeniSlivers> slivers = new ArrayList<GeniSlivers>();

		List<String> resourceIds = group.getResources();
		List<ResourceAdapter> resources = resourceManager
				.getResourceAdapterInstancesById(resourceIds);
		for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
			
			//TODO: for every resource(as node) get the slivers(openstackVMAdapter) in them and set these as slivers.
			ResourceAdapter resourceAdapter = (ResourceAdapter) iterator.next();
//			if(resourceAdapter==null) continue;
			GeniSlivers tmpSliver = new GeniSlivers();
			tmpSliver.setGeni_sliver_urn(URN.getURNFromResourceAdapter(resourceAdapter).toString());
			tmpSliver.setGeni_allocation_status((String) resourceAdapter
					.getProperties().get("allocation_status"));
			tmpSliver
					.setGeni_expires(DateUtil.getFormatedDate(resourceAdapter.getExpirationTime()));
			slivers.add(tmpSliver);
		}
		resultValue.setGeni_slivers(slivers);

		ManifestRspecTranslator translator = new ManifestRspecTranslator();
		org.fiteagle.interactors.sfa.rspec.manifest.RSpecContents manifestRSpec = translator.getManifestRSpec(resources);
		String geni_rspec = translator.getRSpecString(manifestRSpec);
		resultValue.setGeni_rspec(geni_rspec);

		return resultValue;
	}

	

	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResourceAdapterManager getResourceManager() {
		return resourceManager;
	}

	public void setResourceManager(ResourceAdapterManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	

//	



}
