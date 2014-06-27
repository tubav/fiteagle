package org.fiteagle.interactors.sfa.allocate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.fiteagle.adapter.common.NodeAdapterInterface;
import org.fiteagle.adapter.common.OpenstackResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapterStatus;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.config.InterfaceConfiguration;
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
import org.fiteagle.interactors.sfa.common.GeniEndTimeoption;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.rspec.ext.Property;
import org.fiteagle.interactors.sfa.rspec.ext.Resource;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.OpenstackResource;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.VmToInstantiate;
import org.fiteagle.interactors.sfa.rspec.manifest.ManifestRspecTranslator;
import org.fiteagle.interactors.sfa.rspec.request.DiskImageContents;
import org.fiteagle.interactors.sfa.rspec.request.NodeContents;
import org.fiteagle.interactors.sfa.rspec.request.NodeContents.SliverType;
import org.fiteagle.interactors.sfa.rspec.request.RSpecContents;
import org.fiteagle.interactors.sfa.rspec.request.RequestRspecTranslator;
import org.fiteagle.interactors.sfa.util.DateUtil;

public class AllocateRequestProcessor extends SFAv3RequestProcessor {

	private ResourceAdapterManager resourceManager;

	public AllocateResult processRequest(String urn,
			ListCredentials credentials, RSpecContents requestRspec,
			AllocateOptions allocateOptions) {
		// TODO:get user name from credentials and correct this
		AllocateResult result = getResult(urn, credentials, requestRspec,
				allocateOptions);
		return result;
	}

	private AllocateResult getResult(String urn, ListCredentials credentials,
			RSpecContents requestRspec, AllocateOptions options) {
		String value = "";
		String output = "";
		AMCode returnCode = null;
		AllocateValue allocateValue = new AllocateValue();
		AllocateResult result = new AllocateResult();
		boolean validArguments = true;
		Date expirationDate = getExpirationDate(options);
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

							Resource jaxBResource = (Resource) jaxbElem
									.getValue();

							String instanceId = "";
							for (Property p : jaxBResource.getProperty()) {
								if (p.getName().equalsIgnoreCase("id")) {
									instanceId = p.getValue();
								}
							}
							resource = resourceManager
									.getResourceAdapterInstance(instanceId);
						}

						if (OpenstackResource.class.isAssignableFrom(jaxbElem
								.getValue().getClass())) {

							OpenstackResource openstackResource = (OpenstackResource) jaxbElem
									.getValue();

							VmToInstantiate vmToInstantiate = openstackResource
									.getVmToInstantiate();

							OpenstackResourceAdapter openstackResourceAdapter = (OpenstackResourceAdapter) resourceManager
									.getResourceAdapterInstance(openstackResource
											.getResourceId());
							// resource = (ResourceAdapter)
							// openstackResourceAdapter.create(vmToInstantiate.getImageId(),
							// vmToInstantiate.getFlavorId(),
							// vmToInstantiate.getVmName(),vmToInstantiate.getKeyPairName()
							// , this.getUserCertificate());
							resource = (ResourceAdapter) openstackResourceAdapter
									.create(vmToInstantiate.getImageId(),
											vmToInstantiate.getFlavorId(),
											vmToInstantiate.getVmName(),
											vmToInstantiate.getKeyPairName());

							resourceManager
									.addResourceAdapterInstance(resource);// TODO:
																			// check
																			// this
																			// if
																			// this
																			// is
																			// needed
																			// or
																			// extra!!
						}

						if (NodeContents.class.isAssignableFrom(jaxbElem
								.getValue().getClass())) {

							NodeContents node = (NodeContents) jaxbElem
									.getValue();

							String nodeName = new RequestRspecTranslator()
									.getNodeNameFromNodeComponentId(node
											.getComponentId());

							if (nodeName == null
									|| nodeName
											.compareToIgnoreCase(NodeAdapterInterface.nodeName) == 0) {
								// this is an openstack node resource

								if(nodeName == null) 
									nodeName = NodeAdapterInterface.nodeName;
								
								NodeAdapterInterface openstackNodeResourceAdapter = (NodeAdapterInterface) resourceManager
										.getResourceAdapterInstance(NodeAdapterInterface.nodeName);

								List<OpenstackResourceAdapter> allocatedImages = new ArrayList<OpenstackResourceAdapter>();

								List<Object> nodeContentsList = node
										.getAnyOrRelationOrLocation();
								// List<OpenstackResourceAdapter>
								// listOfOpenstackImages =
								// openstackNodeResourceAdapter.getImages();
								
								//TODO: if node content list is empty.. get first image first flavor. add a sliver type with these

								for (Iterator iterator2 = nodeContentsList
										.iterator(); iterator2.hasNext();) {
									// if assignable..
									// Object object2 = (Object)
									// iterator2.next();
									JAXBElement object2 = (JAXBElement) iterator2
											.next();
									if (org.fiteagle.interactors.sfa.rspec.request.NodeContents.SliverType.class
											.isAssignableFrom(object2
													.getValue().getClass())) {
										// add the openstack resources one by
										// one into a list

										org.fiteagle.interactors.sfa.rspec.request.NodeContents.SliverType sliverTypeTmp = (org.fiteagle.interactors.sfa.rspec.request.NodeContents.SliverType) object2
												.getValue();
										List<OpenstackResourceAdapter> tmpAllocatedImages = this
												.allocateOpenstackResourceAdaptersMatchingSliverType(
														sliverTypeTmp,
														openstackNodeResourceAdapter
																.getImages());
										allocatedImages
												.addAll(tmpAllocatedImages);

										// getSliverId over sliver name!!
										// TODO: get vms and add the
										// allocatedImages
										// TODO: set the node is from client_id
										// in request node!
									}
								}

								// it can be ok to set the id like
								// node.client_id?
								NodeAdapterInterface allocatedNode = openstackNodeResourceAdapter
										.create(null, allocatedImages);
								ResourceAdapter result1 = (ResourceAdapter) allocatedNode;
								resource = result1;
								resourceManager
										.addResourceAdapterInstance(resource);// TODO:
																				// check
																				// this
																				// if
																				// this
																				// is
																				// needed
																				// or
																				// extra!!
								
								List<OpenstackResourceAdapter> allocatedVMs = allocatedNode.getVms();
								for (Iterator iterator2 = allocatedVMs
										.iterator(); iterator2.hasNext();) {
									ResourceAdapter openstackResourceAdapter = (ResourceAdapter) iterator2.next();
									resourceManager.addResourceAdapterInstance(openstackResourceAdapter);
								}

							}

							// String id = node.getClientId();

						}

						if (resource != null
								&& !(resource.isExclusive() && !resource
										.getStatus()
										.equals(ResourceAdapterStatus.Available))
								&& NodeAdapterInterface.class
										.isAssignableFrom(resource.getClass())) {

							// TODO: set here for every slivertype
							// (openstackadapter) the status etc..

							NodeAdapterInterface nodeAdapter = (NodeAdapterInterface) resource;

							List<OpenstackResourceAdapter> allocatedImages = nodeAdapter
									.getVms();

							for (Iterator iterator2 = allocatedImages
									.iterator(); iterator2.hasNext();) {
								OpenstackResourceAdapter openstackResourceAdapter = (OpenstackResourceAdapter) iterator2
										.next();
								openstackResourceAdapter.getProperties()
										.put("operational_status",
												GENISliverOperationalState.geni_pending_allocation
														.toString());
								openstackResourceAdapter.getProperties()
										.put("allocation_status",
												GENISliverAllocationState.geni_allocated
														.toString());
								openstackResourceAdapter.setStatus(ResourceAdapterStatus.Reserved);
							
								openstackResourceAdapter.setExpirationTime(expirationDate);
								resourceManager.setExpires(openstackResourceAdapter.getId(),
										expirationDate);

							}
//
//							resource.getProperties()
//									.put("operational_status",
//											GENISliverOperationalState.geni_pending_allocation
//													.toString());
//							resource.getProperties().put(
//									"allocation_status",
//									GENISliverAllocationState.geni_allocated
//											.toString());
//							resource.setStatus(ResourceAdapterStatus.Reserved);
//							Date allocationExpirationTime = Calendar
//									.getInstance().getTime();
//							allocationExpirationTime
//									.setTime(allocationExpirationTime.getTime() + 10 * 1000 * 60);
//							resource.setExpirationTime(allocationExpirationTime);
							
							resourcesList.add(resource);
						} else if (resource != null
								&& !(resource.isExclusive() && !resource
										.getStatus()
										.equals(ResourceAdapterStatus.Available))) {

							// TODO: set here for every slivertype
							// (openstackadapter) the status etc..
							resource.getProperties()
									.put("operational_status",
											GENISliverOperationalState.geni_pending_allocation
													.toString());
							resource.getProperties().put(
									"allocation_status",
									GENISliverAllocationState.geni_allocated
											.toString());
							resource.setStatus(ResourceAdapterStatus.Reserved);
							Date allocationExpirationTime = Calendar
									.getInstance().getTime();
							allocationExpirationTime
									.setTime(allocationExpirationTime.getTime() + 10 * 1000 * 60);
							resource.setExpirationTime(allocationExpirationTime);
							resourceManager.setExpires(resource.getId(),
									allocationExpirationTime);
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

	private Date getExpirationDate(AllocateOptions options) {
		GeniEndTimeoption endTimeOption = options.getGeni_end_time();
		Date expirationDate = null;
		if(endTimeOption != null){
			String endTimeOptionValue = endTimeOption.getValue();
			try {
				expirationDate  = DateUtil.getDateFromString(endTimeOptionValue);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			expirationDate = new Date();
			expirationDate.setTime(expirationDate.getTime() + (5* 60 *1000));
			
		}
		return expirationDate;
	}

	private List<OpenstackResourceAdapter> allocateOpenstackResourceAdaptersMatchingSliverType(
			SliverType sliverTypeTmp, List<OpenstackResourceAdapter> images) {

		ArrayList<OpenstackResourceAdapter> result = new ArrayList<OpenstackResourceAdapter>();

		String flavorId = this.getFlavorIdOverFlavorName(
				sliverTypeTmp.getName(), images.get(0).getFlavorsProperties());

		List<Object> diskImages = sliverTypeTmp.getAnyOrDiskImage();
		if (diskImages == null || diskImages.isEmpty())
			return null;

		for (Iterator iterator = diskImages.iterator(); iterator.hasNext();) {
			JAXBElement object = (JAXBElement) iterator.next();
			if (org.fiteagle.interactors.sfa.rspec.request.DiskImageContents.class
					.isAssignableFrom(object.getValue().getClass())) {

				DiskImageContents diskImgTmp = (org.fiteagle.interactors.sfa.rspec.request.DiskImageContents) object
						.getValue();
				OpenstackResourceAdapter openstackResourceAdapterTmp = this
						.getOpenstackResourceAdapterFromImageName(
								diskImgTmp.getName(), images);

				//TODO: check if it is ok to 
				if (openstackResourceAdapterTmp != null) {
					result.add(openstackResourceAdapterTmp.create((String)openstackResourceAdapterTmp.getProperties().get(OpenstackResourceAdapter.IMAGE_ID), flavorId,
							null, null));
//					result.add(openstackResourceAdapterTmp.create(openstackResourceAdapterTmp.getImageId(), flavorId,
//							null, null));
				}

				// get flavor id over flavor name(sliver type name) to find the
				// mathching flavor.
				// get image id over image name(disk image name)
			}

		}
		return result;
	}

	private String getFlavorIdOverFlavorName(String name,
			List<HashMap<String, String>> flavorsProperties) {

		for (Iterator iterator = flavorsProperties.iterator(); iterator
				.hasNext();) {
			HashMap<String, String> tmpHashMap = (HashMap<String, String>) iterator
					.next();
			if (tmpHashMap.get(OpenstackResourceAdapter.FLAVOR_NAME)
					.compareToIgnoreCase(name) == 0)
				return tmpHashMap.get(OpenstackResourceAdapter.FLAVOR_ID);
		}

		return null;
	}

	private OpenstackResourceAdapter getOpenstackResourceAdapterFromImageName(
			String name, List<OpenstackResourceAdapter> images) {

		for (Iterator iterator = images.iterator(); iterator.hasNext();) {
			OpenstackResourceAdapter tmpOpenstackResourceAdapter = (OpenstackResourceAdapter) iterator
					.next();

			if (tmpOpenstackResourceAdapter.getImageProperties()
					.get(OpenstackResourceAdapter.IMAGE_NAME)
					.compareToIgnoreCase(name) == 0)
				return tmpOpenstackResourceAdapter;

		}

		return null;
	}

	private AllocateValue getValue(String urn) {
		AllocateValue resultValue = new AllocateValue();
		Group group = GroupDBManager.getInstance().getGroup(
				new URN(urn).getSubjectAtDomain());
		ArrayList<GeniSlivers> slivers = new ArrayList<GeniSlivers>();

		List<String> resourceIds = group.getResources();
		List<ResourceAdapter> resources = resourceManager
				.getResourceAdapterInstancesById(resourceIds);

		// TODO: if resource adapter node adapter than copy paste the code here
		// and set for every ndoe resource the slivers(from openstack vms )
		
		

		for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
			
			ResourceAdapter resourceAdapter = (ResourceAdapter) iterator.next();
			if(NodeAdapterInterface.class.isAssignableFrom(resourceAdapter.getClass())){
				
				NodeAdapterInterface nodeOpenstackResourceAdapter = (NodeAdapterInterface) resourceAdapter; 
				List<OpenstackResourceAdapter> nodeOpenstackResourceVms = nodeOpenstackResourceAdapter.getVms();
				
				
				if(nodeOpenstackResourceVms!=null && nodeOpenstackResourceVms.size()!=0){
					for (Iterator iterator2 = nodeOpenstackResourceVms.iterator(); iterator2
							.hasNext();) {
						OpenstackResourceAdapter openstackResourceAdapter = (OpenstackResourceAdapter) iterator2
								.next();
						GeniSlivers tmpSliver1 = new GeniSlivers();
						
//						TODO: openstackResourceAdapter.getId() is null!!!!!!!!!!!!!!!
						tmpSliver1.setGeni_sliver_urn(new URN("urn:publicid:IDN" + "+" + InterfaceConfiguration.getInstance().getDomain() + "+sliver+" +openstackResourceAdapter.getId()).toString());
						tmpSliver1.setGeni_allocation_status((String) openstackResourceAdapter.getProperties().get("allocation_status"));
						tmpSliver1.setGeni_expires(DateUtil.getFormatedDate(openstackResourceAdapter
								.getExpirationTime()));
						slivers.add(tmpSliver1);
					}
				}
				
//				if(nodeOpenstackResourceAdapter!=null && nodeOpenstackResourceVms.size()!=0){
//					GeniSlivers tmpSliver1 = new GeniSlivers();
////						TODO: openstackResourceAdapter.getId() is null!!!!!!!!!!!!!!!
//					tmpSliver1.setGeni_sliver_urn(new URN("urn:publicid:IDN" + "+" + InterfaceConfiguration.getInstance().getDomain() + "+sliver+" +nodeOpenstackResourceAdapter.getId()).toString());
//					tmpSliver1.setGeni_allocation_status((String) nodeOpenstackResourceAdapter.getProperties().get("allocation_status"));
//					tmpSliver1.setGeni_expires(DateUtil.getFormatedDate(nodeOpenstackResourceAdapter
//							.getExpirationTime()));
//					slivers.add(tmpSliver1);
//				}
				
				
//				TODO: check this in this case a sliver is not representing only one VM. !!!!
				
				
				continue;
			}
			// if(resourceAdapter==null) continue;
			GeniSlivers tmpSliver = new GeniSlivers();
			tmpSliver.setGeni_sliver_urn(URN.getURNFromResourceAdapter(
					resourceAdapter).toString());
			tmpSliver.setGeni_allocation_status((String) resourceAdapter
					.getProperties().get("allocation_status"));
			tmpSliver.setGeni_expires(DateUtil.getFormatedDate(resourceAdapter
					.getExpirationTime()));
			slivers.add(tmpSliver);
		}
		resultValue.setGeni_slivers(slivers);

		ManifestRspecTranslator translator = new ManifestRspecTranslator();
		org.fiteagle.interactors.sfa.rspec.manifest.RSpecContents manifestRSpec = translator
				.getManifestRSpec(resources);
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
