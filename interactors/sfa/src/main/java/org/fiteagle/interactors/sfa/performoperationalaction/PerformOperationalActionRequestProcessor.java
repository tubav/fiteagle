package org.fiteagle.interactors.sfa.performoperationalaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.GENISliverAllocationState;
import org.fiteagle.interactors.sfa.common.GENISliverOperationalState;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniSliversOperationalStatus;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class PerformOperationalActionRequestProcessor extends
		SFAv3RequestProcessor {

	public PerformOperationalActionResult processRequest(
			ArrayList<String> urns, ListCredentials credentials, String action,
			PerformOperationalActionOptions performOpOptions) {
		PerformOperationalActionResult result = getResult(urns, credentials,
				action, performOpOptions);
		return result;
	}

	private PerformOperationalActionResult getResult(ArrayList<String> urns,
			ListCredentials credentials, String action,
			PerformOperationalActionOptions performOpOptions) {

		String output = "";
		AMCode returnCode = null;

		Authorization auth = new Authorization();

		auth.checkCredentialsList(credentials);

		PerformOperationalActionResult result = new PerformOperationalActionResult();

		if (!auth.areCredentialTypeAndVersionValid()) {
			returnCode = auth.getReturnCode();
			output = auth.getAuthorizationFailMessage();
			result.setCode(returnCode);
			result.setOutput(output);
			return result;
		}
		// TODO: check options!!!

		// TODO: process the correct request..

		returnCode = getReturnCode(GENI_CodeEnum.SUCCESS);

		result.setCode(returnCode);
		result.setValue(getPerformOperationalActionResultValue(urns, action));
		return result;
	}

	private ArrayList<GeniSliversOperationalStatus> getPerformOperationalActionResultValue(
			ArrayList<String> urns, String action) {
		
		if(urns==null || urns.size()==0) throw new RuntimeException();

		SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
		ResourceAdapterManager resourceManager = ResourceAdapterManager
				.getInstance();
		ArrayList<GeniSliversOperationalStatus> slivers = new ArrayList<GeniSliversOperationalStatus>();
		if (urns.get(0).contains("+slice+")) {
			Group group = GroupDBManager.getInstance().getGroup(new URN(urns.get(0)).getSubjectAtDomain());
			List<String> resourceAdapterInstanceIds = group.getResources();
			List<ResourceAdapter> resourceAdapterInstances = resourceManager
					.getResourceAdapterInstancesById(resourceAdapterInstanceIds);

			for (Iterator iterator = resourceAdapterInstances.iterator(); iterator
					.hasNext();) {
				ResourceAdapter resourceAdapter = (ResourceAdapter) iterator.next();
				
				if(action.compareToIgnoreCase("geni_start")==0){
					resourceAdapter.start();
					//TODO: change the state!!!
				}
				
				if(action.compareToIgnoreCase("geni_stop")==0){
					resourceAdapter.stop();
					//TODO: change the state!!!
				}else{
					Action requestedAction = new Action(action, resourceAdapter); 
					requestedAction.doAction();
				}
				//TODO: handle other cases here
				GeniSliversOperationalStatus tmpSliver = new GeniSliversOperationalStatus();
				String urn = translator.translateResourceIdToSliverUrn(resourceAdapter.getId(), urns.get(0));
				tmpSliver.setGeni_sliver_urn(urn);
				tmpSliver.setGeni_allocation_status((String)resourceAdapter.getProperties().get("allocation_status"));
				// TODO: expires????!!!
				// TODO error(optional)??
				slivers.add(tmpSliver);
			}
		} else {

			for (Iterator iterator = urns.iterator(); iterator.hasNext();) {
				String urn = (String) iterator.next();
				String id = translator.getIdFromSliverUrn(urn);
				
				ResourceAdapter resourceAdapter = resourceManager.getResourceAdapterInstance(id);
				
				if(action.compareToIgnoreCase("geni_start")==0){
					resourceAdapter.start();
					resourceAdapter.addProperty("operational_status", GENISliverOperationalState.geni_ready);
				}
				
				if(action.compareToIgnoreCase("geni_stop")==0){
					resourceAdapter.addProperty("operational_status", GENISliverOperationalState.geni_stopping);
					resourceAdapter.stop();
					resourceAdapter.addProperty("operational_status", GENISliverOperationalState.geni_notready);
				}
				
				
				GeniSliversOperationalStatus tmpSliver = new GeniSliversOperationalStatus();
				tmpSliver.setGeni_sliver_urn(urn);
				tmpSliver.setGeni_allocation_status((String)resourceAdapter.getProperties().get("allocation_status"));
				// TODO: expires????!!!
				// TODO error(optional)??
				slivers.add(tmpSliver);

			}

		}

		return slivers;

	}

	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		// TODO Auto-generated method stub
		return null;
	}

}
