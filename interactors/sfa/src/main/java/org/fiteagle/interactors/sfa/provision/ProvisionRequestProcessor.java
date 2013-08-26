package org.fiteagle.interactors.sfa.provision;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.fiteagle.adapter.common.AdapterConfiguration;
import org.fiteagle.adapter.common.AdapterUser;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapterStatus;
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
import org.fiteagle.interactors.sfa.common.GeniEndTimeoption;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.common.GeniUser;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.rspec.RSpecContents;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class ProvisionRequestProcessor extends SFAv3RequestProcessor {

	public ProvisionResult processRequest(ArrayList<String> urns,
			ListCredentials credentials, ProvisionOptions provisionOptions) {
		ProvisionResult result = getResult(urns, credentials, provisionOptions);
		return result;
	}

	private ProvisionResult getResult(ArrayList<String> urns,
			ListCredentials credentials, ProvisionOptions provisionOptions) {
		String value = "";
		String output = "";
		AMCode returnCode = null;

		Authorization auth = new Authorization();

		auth.checkCredentialsList(credentials);

		ProvisionResult result = new ProvisionResult();

		if (!auth.areCredentialTypeAndVersionValid()) {
			returnCode = auth.getReturnCode();
			output = auth.getAuthorizationFailMessage();
			result.setCode(returnCode);
			result.setOutput(output);
			return result;
		}
		// TODO: check options

		// process the correct request..

		if (urns.size() > 1)
			;
		if (urns.size() == 1 && urns.get(0).contains("\\+sliver\\+"))
			;
		// TODO: implement if there are one or multiple sliver urns not only one
		// slice urn

		if (urns.size() == 1 && urns.get(0).contains("+slice+")) {
			SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
			ResourceAdapterManager resourceManager = ResourceAdapterManager
					.getInstance();
			Group group = GroupDBManager.getInstance().getGroup(
					new URN(urns.get(0)).getSubjectAtDomain());

			ArrayList<GeniSlivers> slivers = new ArrayList<GeniSlivers>();
			ProvisionValue resultValue = new ProvisionValue();

			List<String> resourceIds = group.getResources();
			List<ResourceAdapter> resources = resourceManager
					.getResourceAdapterInstancesById(resourceIds);
			for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
				ResourceAdapter resourceAdapter = (ResourceAdapter) iterator
						.next();
				if (resourceAdapter.getStatus().equals(ResourceAdapterStatus.Reserved)) {

					HashMap<String, Object> props = resourceAdapter
							.getProperties();
					props.put("operational_status",
							GENISliverOperationalState.geni_configuring
									.toString());
					resourceAdapter.setProperties(props);
					AdapterConfiguration config = buildAdapterConfig(provisionOptions);
					resourceAdapter.configure(config);
					
					resourceAdapter.setExpirationTime(getExpirationDate(provisionOptions));
					resourceManager.renewExpirationTime(resourceAdapter.getId(), resourceAdapter.getExpirationTime());
		
					props.put("operational_status",
							GENISliverOperationalState.geni_ready.toString());
					props.put("allocation_status",
							GENISliverAllocationState.geni_provisioned
									.toString());
					resourceAdapter.setProperties(props);

					
					GeniSlivers tmpSliver = new GeniSlivers();
					tmpSliver.setGeni_sliver_urn(translator
							.translateResourceIdToSliverUrn(
									resourceAdapter.getId(), urns.get(0)));
					tmpSliver
							.setGeni_allocation_status((String) resourceAdapter
									.getProperties().get("allocation_status"));
					tmpSliver
							.setGeni_operational_status((String) resourceAdapter
									.getProperties().get("operational_status"));
					tmpSliver.setGeni_expires("somewhere over the rainbow");
					
					slivers.add(tmpSliver);

				}

			}
			resultValue.setGeni_slivers(slivers);

			RSpecContents manifestRSpec = getManifestRSpec(resources);
			String geni_rspec = getRSpecString(manifestRSpec);
			resultValue.setGeni_rspec(geni_rspec);
			returnCode = getReturnCode(GENI_CodeEnum.SUCCESS);

			result.setCode(returnCode);
			result.setValue(resultValue);
		}

		return result;

	}

	private AdapterConfiguration buildAdapterConfig(
			ProvisionOptions provisionOptions) {
		AdapterConfiguration config = new AdapterConfiguration();
		if (provisionOptions.getGeni_users() != null)
			config.setUsers(getAdapterUsers(provisionOptions.getGeni_users()
					.getGeniUserList()));
		
		return config;
	}

	private Date getExpirationDate(ProvisionOptions options) {
		if(options.getGeni_end_time() != null){
			//TOOD parse GENI END TIME and createDATE
		}
			return new Date(Calendar.getInstance().getTimeInMillis()
					+ (1000 * 60 * 60));
	}

	private List<AdapterUser> getAdapterUsers(ArrayList<GeniUser> geniUserList) {
		List<AdapterUser> userList = new LinkedList<>();

		for (GeniUser user : geniUserList) {
			
			AdapterUser adapterUser = new AdapterUser();
			adapterUser.setUsername(user.getUrn().getSubject());
			adapterUser.setSshPublicKeys(user.getKeys());
			userList.add(adapterUser);
		}

		return userList;
	}

	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		// TODO Auto-generated method stub
		return null;
	}

}
