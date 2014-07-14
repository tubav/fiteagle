package org.fiteagle.interactors.sfa.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;
import org.fiteagle.interactors.sfa.util.DateUtil;

public class StatusRequestProcessor extends SFAv3RequestProcessor {

	private StatusOptionsService optionsService;
	private GENI_CodeEnum code = GENI_CodeEnum.SUCCESS;
	private String output = "";

	public StatusResult processRequest(List<String> urns,
			ListCredentials credentials, Object... specificArgs) {
		StatusOptions options = (StatusOptions) specificArgs[0];
		StatusResult result = getResult(urns, credentials, options);
		return result;
	}

	private StatusResult getResult(List<String> urns,
			ListCredentials credentials, StatusOptions options) {
		String value = "";
		AMCode returnCode = null;

		Authorization auth = new Authorization();

		auth.checkCredentialsList(credentials);

		StatusResult result = new StatusResult();

		if (!auth.areCredentialTypeAndVersionValid()) {

			returnCode = auth.getReturnCode();
			output = auth.getAuthorizationFailMessage();
			result.setCode(returnCode);
			result.setOutput(output);
			return result;
		}

		this.optionsService = new StatusOptionsService(options);

		optionsService.checkOptions();
		if (!optionsService.areOptionsValid()) {
			// TODO: check options
		}

		// process the correct request..

		result.setValue(getResultStatusValue(urns));
		returnCode = getReturnCode(code);
		result.setOutput(output);
		result.setCode(returnCode);
		return result;
	}

	private StatusValue getResultStatusValue(List<String> urns) {
		//TODO: check status also for slivers. call checkStatus on RAs

		SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
		ResourceAdapterManager resourceManager = ResourceAdapterManager
				.getInstance();
		List<URN> urnList = buildURNS(urns);
		String geni_urn = "";
		if (urnList == null) {
			return new StatusValue();
		}
		ArrayList<GeniSlivers> slivers = null;
		for (URN u : urnList) {
			if (u.getType().equalsIgnoreCase("slice")) {
				geni_urn = u.toString();
				Group g = null;
				try {
					g = GroupDBManager.getInstance().getGroup(
							u.getSubjectAtDomain());
				} catch (GroupNotFound e) {
					code = GENI_CodeEnum.SEARCHFAILED;
					output = GENI_CodeEnum.SEARCHFAILED.getDescription();
					return new StatusValue();
				}
				List<String> groupResources = g.getResources();
				slivers = new ArrayList<GeniSlivers>();
				for (String resourceID : groupResources) {
					ResourceAdapter ra = resourceManager
							.getResourceAdapterInstance(resourceID);
					
					ra.checkStatus();
					
					if (ra instanceof NodeAdapterInterface) {
						NodeAdapterInterface nodeAdapter = (NodeAdapterInterface) ra;
						for (OpenstackResourceAdapter vm : nodeAdapter.getVms()) {
							GeniSlivers tmpSliver = new GeniSlivers();
							tmpSliver.setGeni_sliver_urn(new URN(
									"urn:publicid:IDN"
											+ "+"
											+ InterfaceConfiguration
													.getInstance().getDomain()
											+ "+sliver+" + vm.getId())
									.toString());
							tmpSliver.setGeni_allocation_status((String) vm
									.getProperties().get("allocation_status"));
							tmpSliver.setGeni_operational_status(vm.getProperties().get("operational_status").toString());
							tmpSliver.setGeni_expires(DateUtil
									.getFormatedDate(vm.getExpirationTime()));
							slivers.add(tmpSliver);
						}

					} else {
						GeniSlivers tmpSliver = new GeniSlivers();
						tmpSliver.setGeni_sliver_urn(URN
								.getURNFromResourceAdapter(ra).toString());
						tmpSliver.setGeni_allocation_status((String) ra
								.getProperties().get("allocation_status"));
						tmpSliver.setGeni_operational_status((String) ra
								.getProperties().get("operational_status"));
						tmpSliver.setGeni_expires(DateUtil.getFormatedDate(ra
								.getExpirationTime()));
						slivers.add(tmpSliver);

					}
				}
			} else {
				code = GENI_CodeEnum.SERVERERROR;
				output = GENI_CodeEnum.SERVERERROR.getDescription();

				return new StatusValue();
			}
		}

		StatusValue statusResultValue = new StatusValue();

		statusResultValue.setGeni_slivers(slivers);
	
		
		statusResultValue.setGeni_urn(geni_urn);

		return statusResultValue;
	}

	private List<URN> buildURNS(List<String> urns) {
		List<URN> urnList = new LinkedList<>();
		for (String s : urns) {
			try {
				URN u = new URN(s);
				urnList.add(u);
			} catch (URNParsingException e) {
				code = GENI_CodeEnum.BADARGS;
				output = GENI_CodeEnum.BADARGS.getDescription();
				return null;
			}

		}
		return urnList;
	}

	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		// TODO Auto-generated method stub
		return null;
	}

}
