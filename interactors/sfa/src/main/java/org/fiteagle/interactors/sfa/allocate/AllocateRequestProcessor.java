package org.fiteagle.interactors.sfa.allocate;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.stopwatch.StopwatchAdapter;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.GENISliverAllocationState;
import org.fiteagle.interactors.sfa.common.GENISliverOperationalState;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.describe.DescribeResult;
import org.fiteagle.interactors.sfa.describe.DescribeValue;
import org.fiteagle.interactors.sfa.rspec.NodeContents;
import org.fiteagle.interactors.sfa.rspec.ObjectFactory;
import org.fiteagle.interactors.sfa.rspec.RSpecContents;
import org.fiteagle.interactors.sfa.rspec.Resource;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class AllocateRequestProcessor extends SFAv3RequestProcessor {

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
		URN sliceURN = new URN(urn);
		Authorization auth = new Authorization();

		auth.checkCredentialsList(credentials);

		AllocateResult result = new AllocateResult();

		if (!auth.areCredentialTypeAndVersionValid()) {
			returnCode = auth.getReturnCode();
			output = auth.getAuthorizationFailMessage();
			result.setCode(returnCode);
			result.setOutput(output);
			return result;
		}
		// check options is optional!

		// process the correct request..

		SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
		List<Object> rspecRequestedResources = requestRspec
				.getAnyOrNodeOrLink();
		ResourceAdapterManager resourceManager = ResourceAdapterManager
				.getInstance();
		ArrayList<ResourceAdapter> resourcesList = new ArrayList<ResourceAdapter>();

		for (Iterator iterator = rspecRequestedResources.iterator(); iterator
				.hasNext();) {
			Object object = (Object) iterator.next();
			// if(Resource.class.isAssignableFrom(object.getClass())){
			// ResourceAdapter resource =
			// translator.translateResourceToResourceAdapter((Resource)object);
			// resourceManager.addResourceAdapterInstance(resource);
			// resourcesList.add(resource);
			// }
			if (JAXBElement.class.isAssignableFrom(object.getClass())) {
				JAXBElement jaxbElem = (JAXBElement) object;
				ResourceAdapter resource = null;
				if (Resource.class.isAssignableFrom(jaxbElem.getValue()
						.getClass())) {
					resource = translator
							.translateResourceToResourceAdapter((Resource) jaxbElem
									.getValue());
					resourceManager.addResourceAdapterInstance(resource);
				}
				if (NodeContents.class.isAssignableFrom(jaxbElem.getValue()
						.getClass())) {
					NodeContents node = (NodeContents) jaxbElem.getValue();
					String[] splitted = node.getComponentId().split("\\+");
					String id = splitted[splitted.length - 1];
					resource = resourceManager.getResourceAdapterInstance(id);
				}
				if (resource != null) {
					resource.getProperties().put(
							"operational_status",
							GENISliverOperationalState.geni_pending_allocation
									.toString());
					resource.getProperties()
							.put("allocation_status",
									GENISliverAllocationState.geni_allocated
											.toString());
					resourcesList.add(resource);
				}
			}
			// TODO: implement if it is node or link for resource adapter
			// virtual machine

		}

		Group group = GroupDBManager.getInstance().getGroup(
				sliceURN.getSubjectAtDomain());
		for (ResourceAdapter rs : resourcesList) {
			group.addResource(rs);
			GroupDBManager.getInstance().updateGroup(group);
		}

		returnCode = getSuccessReturnCode();

		result.setCode(returnCode);
		result.setValue(getValue(urn));
		return result;
	}

	private AllocateValue getValue(String urn) {
		SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
		AllocateValue resultValue = new AllocateValue();
		ResourceAdapterManager resourceManager = ResourceAdapterManager
				.getInstance();
		Group group = GroupDBManager.getInstance().getGroup(
				new URN(urn).getSubjectAtDomain());
		ArrayList<GeniSlivers> slivers = new ArrayList<GeniSlivers>();

		List<String> resourceIds = group.getResources();
		List<ResourceAdapter> resources = resourceManager
				.getResourceAdapterInstancesById(resourceIds);
		for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
			ResourceAdapter resourceAdapter = (ResourceAdapter) iterator.next();
			GeniSlivers tmpSliver = new GeniSlivers();
			tmpSliver.setGeni_sliver_urn(translator
					.translateResourceIdToSliverUrn(resourceAdapter.getId(),
							urn));
			tmpSliver.setGeni_allocation_status((String) resourceAdapter
					.getProperties().get("allocation_status"));
			tmpSliver
					.setGeni_expires("somehow somewhere everything must end...");
			slivers.add(tmpSliver);
		}
		resultValue.setGeni_slivers(slivers);

		RSpecContents manifestRSpec = getManifestRSpec(resources);
		String geni_rspec = getRSpecString(manifestRSpec);
		resultValue.setGeni_rspec(geni_rspec);

		return resultValue;
	}

	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		// TODO Auto-generated method stub
		return null;
	}

}
