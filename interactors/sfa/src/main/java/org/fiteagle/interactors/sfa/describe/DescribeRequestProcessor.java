package org.fiteagle.interactors.sfa.describe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.fiteagle.adapter.common.NodeAdapterInterface;
import org.fiteagle.adapter.common.OpenstackResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.config.InterfaceConfiguration;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.groupmanagement.GroupDBManager.GroupNotFound;
import org.fiteagle.core.util.URN;
import org.fiteagle.core.util.URN.URNParsingException;
import org.fiteagle.interactors.sfa.allocate.AllocateRequestProcessor;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.Credentials;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.SignedCredential;
import org.fiteagle.interactors.sfa.rspec.manifest.ManifestRspecTranslator;
import org.fiteagle.interactors.sfa.rspec.manifest.RSpecContents;
import org.fiteagle.interactors.sfa.util.DateUtil;

public class DescribeRequestProcessor extends SFAv3RequestProcessor {

	private ResourceAdapterManager resourceManager;
	private DescribeOptionsService optionsService;
	private String outPutString = "";
	private GENI_CodeEnum code = GENI_CodeEnum.SUCCESS;
	AMCode returnCode = null;

	public DescribeRequestProcessor() {
		resourceManager = ResourceAdapterManager.getInstance();
	}

	public DescribeResult processRequest(List<String> urns,
			ListCredentials credentials, Object... specificArgs) {
		DescribeOptions options = (DescribeOptions) specificArgs[0];
		DescribeResult result = getResult(urns, credentials, options);
		return result;
	}

	private DescribeResult getResult(List<String> urns,
			ListCredentials credentials, DescribeOptions options) {
		String value = "";
		String output = "";

		Authorization auth = new Authorization();

		auth.checkCredentialsList(credentials);

		DescribeResult result = new DescribeResult();

		if (!auth.areCredentialTypeAndVersionValid()) {
			returnCode = auth.getReturnCode();
			output = auth.getAuthorizationFailMessage();
			result.setCode(returnCode);
			result.setOutput(output);
			return result;
		}

		this.optionsService = new DescribeOptionsService(options);

		optionsService.checkOptions();
		if (!optionsService.areOptionsValid()) {
			returnCode = optionsService.getErrorCode();
			output = optionsService.getErrorOutput();

			result.setCode(returnCode);
			result.setOutput(output);
			return result;
		}

		returnCode = getReturnCode(GENI_CodeEnum.SUCCESS);
		result.setOutput(output);
		result.setCode(returnCode);
		result.setValue(getResultValue(urns, credentials));
		return result;

	}

	private DescribeValue getResultValue(List<String> urns, ListCredentials credentials) {
		

		List<URN> urnList = parseURNS(urns);

		DescribeValue resultValue = new DescribeValue();
		
		ArrayList<GeniSlivers> slivers = new ArrayList<GeniSlivers>();
		List<ResourceAdapter> resources = null;
		
		if (urnList.size() == 1	&& urnList.get(0).getType().equalsIgnoreCase("slice")) {
			ResourceAdapterManager resourceManager = ResourceAdapterManager
					.getInstance();
			//TODO: if group does not exist => set in output the error??
			
			
			
//			Group group = GroupDBManager.getInstance().getGroup(
//					new URN(urns.get(0)).getSubjectAtDomain());
			
			//credentials are valid and slice is created somewhere else, so creating the slice on demand..
			Group group = null;
			boolean groupFound = true;
			try {
				group = GroupDBManager.getInstance().getGroup(
						new URN(urns.get(0)).getSubjectAtDomain());
				
			} catch (GroupNotFound e) {
				groupFound = false;
				
			}
			
			if(!groupFound){
//				create group according to the credentials!!
				Credentials credential = credentials.getCredentialsList().get(0);
				String credString = credential.getGeni_value();
				SignedCredential cred=null;
				try {
					cred = new AllocateRequestProcessor().buildCredential(credString);
				} catch (JAXBException e) {
					e.printStackTrace();
				}
				URN userURN = new URN(cred.getCredential().getOwnerURN());
				URN slcURN = urnList.get(0);
				
				try {
					group = new Group(slcURN.getSubjectAtDomain(), userURN.getSubjectAtDomain());
				} catch (Exception e) {
					returnCode = getReturnCode(GENI_CodeEnum.SEARCHFAILED);
					groupFound=false;
				}
		    	GroupDBManager groupDBManager = GroupDBManager.getInstance();
		    	groupDBManager.addGroup(group);
			}
			
			
			
			
			resultValue.setGeni_urn(URN.getURNFromGroup(group).toString());
			
			List<String> resourceIds = group.getResources();
			resources = resourceManager
					.getResourceAdapterInstancesById(resourceIds);
			for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
				ResourceAdapter resourceAdapter = (ResourceAdapter) iterator
						.next();
				if(NodeAdapterInterface.class.isAssignableFrom(resourceAdapter.getClass())){
					NodeAdapterInterface nodeAdapter = (NodeAdapterInterface)resourceAdapter;
					List<GeniSlivers> sliverList = buildSliversForNodeAdapter(nodeAdapter);
					if (sliverList != null)
						slivers.addAll(sliverList);
					continue;
				}
				
				GeniSlivers tmpSliver = new GeniSlivers();
				tmpSliver.setGeni_sliver_urn(URN.getURNFromResourceAdapter(
						resourceAdapter).toString());
				tmpSliver.setGeni_allocation_status((String) resourceAdapter
						.getProperties().get("allocation_status"));
				tmpSliver.setGeni_operational_status(resourceAdapter.getProperties().get("operational_status").toString());
				// TODO: expires????!!!
				// TODO: geni_error (optional)
				slivers.add(tmpSliver);
			}
		} else {
			
			for (Iterator iterator = urnList.iterator(); iterator.hasNext();) {
				URN urn = (URN) iterator.next();
				List<String> resourceIds = new ArrayList<String>();
				resourceIds.add(urn.getSubject());
				resources = resourceManager
						.getResourceAdapterInstancesById(resourceIds);
				

				for (Iterator iterator2 = resources.iterator(); iterator2
						.hasNext();) {
					ResourceAdapter resourceAdapter = (ResourceAdapter) iterator2.next();
					
					
					if (OpenstackResourceAdapter.class.isAssignableFrom(resourceAdapter
							.getClass())) {
						OpenstackResourceAdapter openstackAdapter = (OpenstackResourceAdapter) resourceAdapter;
						String nodeId = openstackAdapter.getParentNodeId();
						
						if (nodeId == null) {
							GeniSlivers sliver = buildSliver(resourceAdapter);
							if (sliver != null)
								slivers.add(sliver);
							continue;
						}
						
						ArrayList<String> nodeIdAsList = new ArrayList<String>();
						nodeIdAsList.add(nodeId);
						List<ResourceAdapter> nodeAdapterAsList = resourceManager.getResourceAdapterInstancesById(nodeIdAsList);
						NodeAdapterInterface nodeAdapter = (NodeAdapterInterface) nodeAdapterAsList.get(0);
						
//						resultValue.setGeni_urn(URN.getURNFromResourceAdapter((ResourceAdapter)nodeAdapter).toString());
//						resultValue.setGeni_urn(URN.getURNFromGroup(((ResourceAdapter)nodeAdapter).getGroupId());
//						String sliceUrn = "urn:publicid:IDN+" + InterfaceConfiguration.getInstance().getDomain() + "+slice+" +((ResourceAdapter)nodeAdapter).getGroupId()+"@"+InterfaceConfiguration.getInstance().getDomain();
						Group group = GroupDBManager.getInstance().getGroup(((ResourceAdapter)nodeAdapter).getGroupId());
						resultValue.setGeni_urn(URN.getURNFromGroup(group).toString());
						List<GeniSlivers> sliverList = buildSliversForNodeAdapter(nodeAdapter);
						if (sliverList != null)
							slivers.addAll(sliverList);
						
					}else {
						if(resourceAdapter.getGroupId()!=null){
							Group group = GroupDBManager.getInstance().getGroup(((ResourceAdapter)resourceAdapter).getGroupId());
							resultValue.setGeni_urn(URN.getURNFromGroup(group).toString());
						}
						GeniSlivers tmpSliver = buildSliver(resourceAdapter);
						// TODO: expires????!!!
						// TODO: geni_error (optional)
						slivers.add(tmpSliver);
					}
					
					
				}

			}
		}
		
		
		
		
		
		
		
		

		resultValue.setGeni_slivers(slivers);
		ManifestRspecTranslator translator = new ManifestRspecTranslator();
		RSpecContents manifestRSpec = translator.getManifestRSpec(resources);
		String geni_rspec = translator.getRSpecString(manifestRSpec);

		if (this.optionsService.isCompressed()) {
			resultValue.setGeni_rspec(this.compress(geni_rspec));
		} else
			resultValue.setGeni_rspec(geni_rspec);

		return resultValue;

	}

	private List<GeniSlivers> buildSliversForNodeAdapter(
			NodeAdapterInterface nodeAdapter) {
		
		ArrayList<GeniSlivers> resultSlivers = new ArrayList<GeniSlivers>();
		
		List<OpenstackResourceAdapter> vms = nodeAdapter.getVms();
		for (Iterator iterator = vms.iterator(); iterator.hasNext();) {
			OpenstackResourceAdapter openstackResourceAdapter = (OpenstackResourceAdapter) iterator
					.next();
			
			HashMap<String, Object> props = openstackResourceAdapter.getProperties();
			// TODO: change this add the ip and port to access the nodes maybe?!!!!
			GeniSlivers tmpSliver = new GeniSlivers();
			
			tmpSliver.setGeni_sliver_urn(new URN("urn:publicid:IDN"+ "+" + InterfaceConfiguration.getInstance().getDomain() + "+sliver+" +openstackResourceAdapter.getId()).toString());
			tmpSliver.setGeni_allocation_status((String) openstackResourceAdapter.getProperties().get("allocation_status"));
			tmpSliver.setGeni_operational_status(openstackResourceAdapter.getProperties().get("operational_status").toString());
			tmpSliver.setGeni_expires(DateUtil.getFormatedDate(openstackResourceAdapter.getExpirationTime()));
			resultSlivers.add(tmpSliver);
		}

		return resultSlivers;
	}

	private GeniSlivers buildSliver(ResourceAdapter resourceAdapter) {
		GeniSlivers tmpSliver = new GeniSlivers();
		tmpSliver.setGeni_sliver_urn(URN.getURNFromResourceAdapter(resourceAdapter).toString());
		tmpSliver.setGeni_allocation_status((String) resourceAdapter.getProperties().get("allocation_status"));
		tmpSliver.setGeni_operational_status(resourceAdapter.getProperties().get("operational_status").toString());
		tmpSliver.setGeni_expires(DateUtil.getFormatedDate(resourceAdapter.getExpirationTime()));
		return tmpSliver;
	}

	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<URN> parseURNS(List<String> urns) {
		List<URN> urnList = new LinkedList<>();
		try {

			for (String urnString : urns) {
				URN urn = new URN(urnString);
				urnList.add(urn);
			}
		} catch (URNParsingException e) {
			code = GENI_CodeEnum.BADARGS;
		}
		return urnList;
	}

}
