package org.fiteagle.interactors.sfa.performoperationalaction;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.mail.MethodNotSupportedException;

import org.fiteagle.adapter.common.NodeAdapterInterface;
import org.fiteagle.adapter.common.OpenstackResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.config.InterfaceConfiguration;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.GENISliverAllocationState;
import org.fiteagle.interactors.sfa.common.GENISliverOperationalState;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.common.GeniSliversOperationalStatus;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.performoperationalaction.Action.MethodNotFound;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;
import org.fiteagle.interactors.sfa.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerformOperationalActionRequestProcessor extends
		SFAv3RequestProcessor {

	Logger log = LoggerFactory.getLogger(getClass());
	GENI_CodeEnum code = GENI_CodeEnum.SUCCESS;

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

		result.setValue(getPerformOperationalActionResultValue(urns, action));
		returnCode = getReturnCode(code);
		result.setCode(returnCode);
		result.setOutput(returnCode.retrieveGeni_code().getDescription());
		return result;
	}

	private ArrayList<GeniSliversOperationalStatus> getPerformOperationalActionResultValue(
			ArrayList<String> urns, String action) {

		if (urns == null || urns.size() == 0)
			throw new RuntimeException();

		SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
		ResourceAdapterManager resourceManager = ResourceAdapterManager
				.getInstance();
		ArrayList<GeniSliversOperationalStatus> slivers = new ArrayList<GeniSliversOperationalStatus>();
		
		
		
		if (urns.get(0).contains("+slice+")) {
			Group group = GroupDBManager.getInstance().getGroup(
					new URN(urns.get(0)).getSubjectAtDomain());
			List<String> resourceAdapterInstanceIds = group.getResources();
			List<ResourceAdapter> resourceAdapterInstances = resourceManager
					.getResourceAdapterInstancesById(resourceAdapterInstanceIds);

			for (Iterator iterator = resourceAdapterInstances.iterator(); iterator
					.hasNext();) {
				ResourceAdapter resourceAdapter = (ResourceAdapter) iterator
						.next();

				performActionOnAdapter(action, resourceAdapter);

				if (isStillSuccessfull()) {
					// TODO: if node than get the openstack adapters and set the
					// sliver according to these!

					
					
					if (NodeAdapterInterface.class
							.isAssignableFrom(resourceAdapter.getClass())) {

						// ArrayList<GeniSlivers> resultSlivers = new
						// ArrayList<GeniSlivers>();

						List<OpenstackResourceAdapter> vms = ((NodeAdapterInterface) resourceAdapter)
								.getVms();

						for (Iterator iterator1 = vms.iterator(); iterator1.hasNext();) {
							OpenstackResourceAdapter openstackResourceAdapter = (OpenstackResourceAdapter) iterator1
									.next();

							HashMap<String, Object> props = openstackResourceAdapter
									.getProperties();
//							GeniSliversOperationalStatus tmpSliver = new GeniSliversOperationalStatus();
//							tmpSliver.setGeni_sliver_urn(new URN("urn:publicid:IDN"+ "+"+ InterfaceConfiguration.getInstance().getDomain()+ "+sliver+"+ openstackResourceAdapter.getId()).toString());
//							tmpSliver.setGeni_operational_status((String) resourceAdapter.getProperties().get("operational_status"));
////							tmpSliver.setGeni_allocation_status((String) resourceAdapter.getProperties().get("allocation_status")); //TODO
//							// tmpSliver.setGeni_operational_status((String)
//							// openstackResourceAdapter.getProperties().get("operational_status"));
//							tmpSliver.setGeni_expires(DateUtil
//									.getFormatedDate(openstackResourceAdapter
//											.getExpirationTime()));
//							slivers.add(tmpSliver);
							
							
							
							GeniSliversOperationalStatus tmpSliver = new GeniSliversOperationalStatus();
							tmpSliver.setGeni_sliver_urn(new URN("urn:publicid:IDN"+ "+"+ InterfaceConfiguration.getInstance().getDomain()+ "+sliver+"+ openstackResourceAdapter.getId()).toString());
							tmpSliver.setGeni_allocation_status((String) openstackResourceAdapter.getProperties().get("allocation_status"));
							tmpSliver.setGeni_operational_status(openstackResourceAdapter.getProperties().get("operational_status").toString());
							tmpSliver.setGeni_expires(DateUtil.getFormatedDate(openstackResourceAdapter.getExpirationTime()));
							slivers.add(tmpSliver);
							
							
							
							
							
							
							
							
							
							
						}
					}else {
						GeniSliversOperationalStatus tmpSliver = new GeniSliversOperationalStatus();
						String urn = URN.getURNFromResourceAdapter(resourceAdapter)
								.toString();
						tmpSliver.setGeni_sliver_urn(urn);
						tmpSliver
						.setGeni_allocation_status((String) resourceAdapter
								.getProperties().get("allocation_status"));
						slivers.add(tmpSliver);
					}

					
					
				} else {
					break;
				}

			}
			
			
			
			
		} else {
			//the request is not a slice
			for (Iterator iterator = urns.iterator(); iterator.hasNext();) {
				String urn = (String) iterator.next();
				URN u = new URN(urn);
				String id = u.getSubject();

				ResourceAdapter resourceAdapter = resourceManager
						.getResourceAdapterInstance(id);
				performActionOnAdapter(action, resourceAdapter);
				if (isStillSuccessfull()) {

					// TODO: if node get the openstack adapter and do this
					// according to them

					GeniSliversOperationalStatus tmpSliver = new GeniSliversOperationalStatus();
					tmpSliver.setGeni_sliver_urn(urn);
					tmpSliver
							.setGeni_allocation_status((String) resourceAdapter
									.getProperties().get("allocation_status"));
					
					tmpSliver.setGeni_operational_status(resourceAdapter.getProperties().get("operational_status").toString());
					tmpSliver.setGeni_expires(DateUtil.getFormatedDate(resourceAdapter.getExpirationTime()));
					slivers.add(tmpSliver);
				} else {
					break;
				}
			}

		}

		return slivers;

	}

	private boolean isStillSuccessfull() {
		return code.equals(GENI_CodeEnum.SUCCESS);
	}

	private void performActionOnAdapter(String action,
			ResourceAdapter resourceAdapter) {
		
		if (NodeAdapterInterface.class.isAssignableFrom(resourceAdapter.getClass())){
			List<OpenstackResourceAdapter> vms = ((NodeAdapterInterface) resourceAdapter).getVms();
			
			for (Iterator iterator = vms.iterator(); iterator.hasNext();) {
				ResourceAdapter vmAdapter = (ResourceAdapter) iterator
						.next();
				
				try {
					if (action.equalsIgnoreCase("geni_start")) {
						vmAdapter.start();
						vmAdapter.getProperties().put("operational_status", GENISliverOperationalState.geni_ready);
						return;
					}
					
					else if (action.equalsIgnoreCase("geni_stop")) {
						vmAdapter.getProperties().put("operational_status", GENISliverOperationalState.geni_stopping);
						vmAdapter.stop();
						vmAdapter.getProperties().put("operational_status", GENISliverOperationalState.geni_notready);
					} else {
						Action requestedAction = new Action(action, resourceAdapter);
						requestedAction.doAction();
					}
				} catch (IllegalArgumentException argumentError) {
					log.error(argumentError.getMessage(), argumentError);
					code = GENI_CodeEnum.BADARGS;
				} catch (MethodNotFound methodNotFound) {
					log.error(methodNotFound.getMessage(), methodNotFound);
					code = GENI_CodeEnum.UNSUPPORTED;
				} catch (InvocationTargetException e) {
					log.error(e.getMessage(), e);
					code = GENI_CodeEnum.SERVERERROR;
				} catch (IllegalAccessException e) {
					log.error(e.getMessage(), e);
					code = GENI_CodeEnum.FORBIDDEN;
				}
				
			}
			
		}else {
			try {
				if (action.equalsIgnoreCase("geni_start")) {
					resourceAdapter.start();
//					resourceAdapter.getProperties().put("operational_status", GENISliverOperationalState.geni_ready);
					return;
				}
				
				else if (action.equalsIgnoreCase("geni_stop")) {
					resourceAdapter.getProperties().put("operational_status", GENISliverOperationalState.geni_stopping);
					resourceAdapter.stop();
					resourceAdapter.getProperties().put("operational_status", GENISliverOperationalState.geni_notready);
				} else {
					Action requestedAction = new Action(action, resourceAdapter);
					requestedAction.doAction();
				}
			} catch (IllegalArgumentException argumentError) {
				log.error(argumentError.getMessage(), argumentError);
				code = GENI_CodeEnum.BADARGS;
			} catch (MethodNotFound methodNotFound) {
				log.error(methodNotFound.getMessage(), methodNotFound);
				code = GENI_CodeEnum.UNSUPPORTED;
			} catch (InvocationTargetException e) {
				log.error(e.getMessage(), e);
				code = GENI_CodeEnum.SERVERERROR;
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
				code = GENI_CodeEnum.FORBIDDEN;
			}
		}
		
		
	}

	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		// TODO Auto-generated method stub
		return null;
	}

}
