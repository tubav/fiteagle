package org.fiteagle.interactors.sfa.allocate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBElement;

import org.fiteagle.adapter.common.OpenstackResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapterStatus;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.config.InterfaceConfiguration;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.groupmanagement.JPAGroupDB.CouldNotFindGroup;
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
import org.fiteagle.interactors.sfa.common.Sliver;
import org.fiteagle.interactors.sfa.common.SliverManagement;
import org.fiteagle.interactors.sfa.rspec.ext.Property;
import org.fiteagle.interactors.sfa.rspec.ext.Resource;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.OpenstackResource;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.VmToInstantiate;
import org.fiteagle.interactors.sfa.rspec.manifest.ManifestRspecTranslator;
import org.fiteagle.interactors.sfa.rspec.request.NodeContents;
import org.fiteagle.interactors.sfa.rspec.request.RSpecContents;
import org.fiteagle.interactors.sfa.util.DateUtil;

public class AllocateRequestProcessor extends SFAv3RequestProcessor {

	private ResourceAdapterManager resourceManager;
	private GroupDBManager groupDBManager;
	private SliverManagement sliverManager;
	
	public AllocateResult processRequest(String urn,
			ListCredentials credentials, RSpecContents requestRspec,
			AllocateOptions allocateOptions) {
		// TODO:get user name from credentials and correct this
		sliverManager = SliverManagement.getInstance();
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
				group = groupDBManager.getGroup(
						sliceURN.getSubjectAtDomain());

			} catch (CouldNotFindGroup e) {
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

				ArrayList<Sliver> resourcesList = new ArrayList<Sliver>();

				boolean allocationSuccess = true;
				for (Iterator iterator = rspecRequestedResources.iterator(); iterator
						.hasNext();) {
					Sliver sliver = new Sliver(createSliverURN(), group);
					Object object = (Object) iterator.next();
					if (JAXBElement.class.isAssignableFrom(object.getClass())) {
						JAXBElement jaxbElem = (JAXBElement) object;
						ResourceAdapter resource = null;
						if (Resource.class.isAssignableFrom(jaxbElem.getValue()
								.getClass())) {
							
							Resource jaxBResource = (Resource) jaxbElem.getValue();
							
							String resourceId = "";
							for(Property p: jaxBResource.getProperty()){
								if(p.getName().equalsIgnoreCase("id")){
									resourceId = p.getValue();
								}
							}
							resource = resourceManager.getResourceAdapterById(resourceId);
							
						}
						
						if (OpenstackResource.class.isAssignableFrom(jaxbElem.getValue()
								.getClass())) {
							
							OpenstackResource openstackResource = (OpenstackResource) jaxbElem.getValue();
							
							VmToInstantiate vmToInstantiate = openstackResource.getVmToInstantiate();
							
							OpenstackResourceAdapter openstackResourceAdapter = (OpenstackResourceAdapter)resourceManager.getResourceAdapterById(openstackResource.getResourceId());
//							resource = (ResourceAdapter) openstackResourceAdapter.create(vmToInstantiate.getImageId(), vmToInstantiate.getFlavorId(), vmToInstantiate.getVmName());
//							resource = (ResourceAdapter) openstackResourceAdapter.create(vmToInstantiate.getImageId(), vmToInstantiate.getFlavorId(), vmToInstantiate.getVmName(), this.getUserCertificate());
							resource = (ResourceAdapter) openstackResourceAdapter.create(vmToInstantiate.getImageId(), vmToInstantiate.getFlavorId(), vmToInstantiate.getVmName(),vmToInstantiate.getKeyPairName() , this.getUserCertificate());
							
//							resourceManager.addResourceAdapter(resource);//TODO: adapter instance!!===!?!!??
							//resourceManager.addResourceAdapterInstance(resource);
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
									.getResourceAdapterById(id);
						}
						
						if (resource != null
								&& !(resource.isExclusive() && !resource
										.getStatus().equals(ResourceAdapterStatus.Available))) {
//						
							sliver.setResource(resource);
							sliver.setOperationalState(GENISliverOperationalState.geni_pending_allocation);
							sliver.setAllocationState(GENISliverAllocationState.geni_allocated);
							resource.setStatus(ResourceAdapterStatus.Reserved);
							Date allocationExpirationTime = Calendar.getInstance().getTime();
							allocationExpirationTime.setTime(allocationExpirationTime.getTime() + 10 * 1000 * 60);
							sliver.setExpirationDate(allocationExpirationTime);
							
							sliverManager.addSliver(sliver);
							//resourceManager.setExpires(resource.getId(), allocationExpirationTime);
							resourcesList.add(sliver);
						} else {
							allocationSuccess = false;
							break;
						}
					}

				}
				if (allocationSuccess) {

					for (Sliver rs : resourcesList) {
						group.addResource(rs.getId());
						groupDBManager.updateGroup(group);
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

	private URN createSliverURN() {
		String uuid = UUID.randomUUID().toString();
		String aMDomain = InterfaceConfiguration.getInstance().getDomain();
		String urnString = "urn:publicid:IDN+"+aMDomain+"+sliver+"+uuid;
		return new URN(urnString);
	}

	private AllocateValue getValue(String urn) {
		AllocateValue resultValue = new AllocateValue();
		Group group = groupDBManager.getGroup(
				new URN(urn).getSubjectAtDomain());
		ArrayList<GeniSlivers> slivers = new ArrayList<GeniSlivers>();

		List<String> resourceIds = group.getResources();
		List<Sliver> bookedSlivers = sliverManager.getSlivers(resourceIds);
		for (Iterator iterator = bookedSlivers.iterator(); iterator.hasNext();) {
			Sliver sliver = (Sliver) iterator.next();
//			if(resourceAdapter==null) continue;
			GeniSlivers tmpSliver = new GeniSlivers();
			tmpSliver.setGeni_sliver_urn(sliver.getId());
			tmpSliver.setGeni_allocation_status(sliver.getAllocationState().toString());
			tmpSliver
					.setGeni_expires(DateUtil.getFormatedDate(sliver.getExpirationDate()));
			slivers.add(tmpSliver);
		}
		resultValue.setGeni_slivers(slivers);

		ManifestRspecTranslator translator = new ManifestRspecTranslator();
		org.fiteagle.interactors.sfa.rspec.manifest.RSpecContents manifestRSpec = translator.getManifestRSpec(bookedSlivers);
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

	public void setGroupDBManager(GroupDBManager groupDBManager) {
		this.groupDBManager = groupDBManager;
		
	}

	

//	



}
