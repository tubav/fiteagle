package org.fiteagle.interactors.sfa.provision;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.fiteagle.adapter.common.AdapterConfiguration;
import org.fiteagle.adapter.common.AdapterUser;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapterStatus;
import org.fiteagle.core.ResourceAdapterManager;
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
import org.fiteagle.interactors.sfa.common.GeniUser;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.common.Sliver;
import org.fiteagle.interactors.sfa.common.SliverManagement;
import org.fiteagle.interactors.sfa.rspec.manifest.ManifestRspecTranslator;
import org.fiteagle.interactors.sfa.rspec.manifest.RSpecContents;
import org.fiteagle.interactors.sfa.util.DateUtil;

public class ProvisionRequestProcessor extends SFAv3RequestProcessor {

	private ResourceAdapterManager resourceManager;

	GENI_CodeEnum code = GENI_CodeEnum.SUCCESS;

	private ProvisionValue resultValue;

	private ProvisionOptions provisionOptions;

	private ArrayList<GeniSlivers> slivers;

	private List<ResourceAdapter> resources;
	private List<Sliver> bookedSlivers;

	private GroupDBManager groupDBManager;

	public ResourceAdapterManager getResourceManager() {
		return resourceManager;
	}

	public void setResourceManager(ResourceAdapterManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public void setGroupDBManager(GroupDBManager groupDBManager) {
		this.groupDBManager = groupDBManager;

	}

	public ProvisionResult processRequest(List<String> urns,
			ListCredentials credentials, ProvisionOptions provisionOptions) {
		this.provisionOptions = provisionOptions;
		ProvisionResult result = getResult(urns, credentials, provisionOptions);
		return result;
	}

	private ProvisionResult getResult(List<String> urns,
			ListCredentials credentials, ProvisionOptions provisionOptions) {
		String value = "";
		String output = "";
		AMCode returnCode = null;

		resultValue = new ProvisionValue();
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
		List<URN> urnList = parseURNS(urns);

		provisionURNS(urnList);

		returnCode = getReturnCode(code);
		result.setCode(returnCode);
		result.setValue(resultValue);
		return result;

	}

	private void provisionURNS(List<URN> urnList) {
		if (urnList.size() == 0) {
			code = GENI_CodeEnum.BADARGS;
			return;
		}
		slivers = new ArrayList<GeniSlivers>();
		resources = new LinkedList<>();

		if (urnList.size() == 1
				&& urnList.get(0).getType().equalsIgnoreCase("slice")) {
			provisionSlice(urnList.get(0));
		} else {
			try {
				bookedSlivers = new LinkedList<>();
				for (URN urn : urnList) {

					provsionSliver(urn);

				}
			} catch (BadArgumentsException e) {
				code = GENI_CodeEnum.BADARGS;
			}
		}
		if (code.equals(GENI_CodeEnum.SUCCESS)) {
			resultValue.setGeni_slivers(slivers);
			ManifestRspecTranslator translator = new ManifestRspecTranslator();
			RSpecContents manifestRSpec = translator
					.getManifestRSpec(bookedSlivers);
			String geni_rspec = translator.getRSpecString(manifestRSpec);
			resultValue.setGeni_rspec(geni_rspec);
		}
	}

	private void provsionSliver(URN urn) {
		if (urn.getType().equals("sliver")) {
			SliverManagement sliverManager = SliverManagement.getInstance();
			Sliver bookedSliver = sliverManager.getSliver(urn);
			bookedSlivers.add(bookedSliver);

			provsionBookedSliver(bookedSliver);
			GeniSlivers sliver = buildSliver(bookedSliver);
			slivers.add(sliver);

		} else {
			throw new BadArgumentsException();
		}
	}

	private void provisionSlice(URN sliceURN) {

		Group group = null;

		try {
			group = groupDBManager.getGroup(sliceURN.getSubjectAtDomain());
		} catch (CouldNotFindGroup e) {

			code = GENI_CodeEnum.SEARCHFAILED;
			return;
		}

		List<String> resourceIds = group.getResources();
		SliverManagement sliverManager = SliverManagement.getInstance();
		bookedSlivers = sliverManager.getSlivers(resourceIds);
		// resources = resourceManager
		// .getResourceAdapterInstancesById(resourceIds);
		//
		for (Sliver bookedsliver : bookedSlivers) {

			provsionBookedSliver(bookedsliver);
			GeniSlivers sliver = buildSliver(bookedsliver);

			if (sliver != null)
				slivers.add(sliver);
		}

	}

	private void provsionBookedSliver(Sliver sliver) {

		if (sliver.getAllocationState().equals(
				GENISliverAllocationState.geni_allocated)) {
			AdapterConfiguration config = buildAdapterConfig(provisionOptions);
			sliver.getResource().configure(config);
			sliver.setOperationalState(GENISliverOperationalState.geni_ready);
			sliver.setExpirationDate(getExpirationDate(provisionOptions));
			sliver.setAllocationState(GENISliverAllocationState.geni_provisioned);
		}

	}

	private GeniSlivers buildSliver(Sliver sliver) {

		GeniSlivers tmpSliver = new GeniSlivers();
		tmpSliver.setGeni_sliver_urn(sliver.getId());
		tmpSliver.setGeni_allocation_status(sliver.getAllocationState()
				.toString());
		tmpSliver.setGeni_operational_status(sliver.getOperationalState()
				.toString());
		tmpSliver.setGeni_expires(DateUtil.getFormatedDate(sliver
				.getExpirationDate()));

		return tmpSliver;
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

	private AdapterConfiguration buildAdapterConfig(
			ProvisionOptions provisionOptions) {
		AdapterConfiguration config = new AdapterConfiguration();
		if (provisionOptions.getGeni_users() != null)
			config.setUsers(getAdapterUsers(provisionOptions.getGeni_users()
					.getGeniUserList()));

		return config;
	}

	private Date getExpirationDate(ProvisionOptions options) {
		if (options.getGeni_end_time() != null) {
			// TOOD parse GENI END TIME and createDATE
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

	public class BadArgumentsException extends RuntimeException {

	}

}
