package org.fiteagle.interactors.sfa.common;

import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.aaa.KeyStoreManagement;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.interactors.sfa.allocate.AllocateRequestProcessor;
import org.fiteagle.interactors.sfa.delete.DeleteRequestProcessor;
import org.fiteagle.interactors.sfa.describe.DescribeRequestProcessor;
import org.fiteagle.interactors.sfa.getSelfCredential.GetSelfCredentialRequestProcessor;
import org.fiteagle.interactors.sfa.getversion.GetVersionRequestProcessor;
import org.fiteagle.interactors.sfa.listresources.ListResourceRequestProcessor;
import org.fiteagle.interactors.sfa.performoperationalaction.PerformOperationalActionRequestProcessor;
import org.fiteagle.interactors.sfa.provision.ProvisionRequestProcessor;
import org.fiteagle.interactors.sfa.register.RegisterRequestProcessor;
import org.fiteagle.interactors.sfa.renew.RenewRequestProcessor;
import org.fiteagle.interactors.sfa.renewSlice.RenewSliceRequestProcessor;
import org.fiteagle.interactors.sfa.resolve.ResolveRequestProcessor;
import org.fiteagle.interactors.sfa.status.StatusRequestProcessor;

public class SFARequestProcessorFactory {

	private static SFARequestProcessorFactory factory = new SFARequestProcessorFactory();

	public static SFARequestProcessorFactory getInstance() {
		return factory;
	}

	@SuppressWarnings("unchecked")
	public <E extends SFAv3RequestProcessor> E createRequestProcessor(
			SFAv3MethodsEnum method) {

		E result = null;
		switch (method) {
		case ALLOCATE:
			AllocateRequestProcessor allocateRequestProcessor = new AllocateRequestProcessor();
			allocateRequestProcessor.setResourceManager(ResourceAdapterManager
					.getInstance());
			result = (E) allocateRequestProcessor;

			break;
		case DELETE:
			DeleteRequestProcessor delProc = new DeleteRequestProcessor();
			delProc.setResourceManager(ResourceAdapterManager.getInstance());
			delProc.setGroupDBManager(GroupDBManager.getInstance());
			result = (E) delProc;
			break;
		case DESCRIBE:
			result = (E) new DescribeRequestProcessor();
			break;
		case LIST_RESOURCES:
			result = (E) new ListResourceRequestProcessor();
			break;
		case PERFORM_OPERATIONAL_ACTION:
			result = (E) new PerformOperationalActionRequestProcessor();
			break;
		case PROVISION:
			ProvisionRequestProcessor provisionRequestProcessor = new ProvisionRequestProcessor();
			provisionRequestProcessor.setResourceManager(ResourceAdapterManager
					.getInstance());
			result = (E) provisionRequestProcessor;
			break;
		case RENEW_SLICE:
			RenewSliceRequestProcessor renewSliceRequestProcessor = new RenewSliceRequestProcessor(KeyStoreManagement.getInstance(), GroupDBManager.getInstance());
			renewSliceRequestProcessor.setResourceManager(ResourceAdapterManager.getInstance());
			result = (E) renewSliceRequestProcessor;
			break;
		case RENEW:
//			RenewRequestProcessor renewRequestProcessor = new RenewSliceRequestProcessor(KeyStoreManagement.getInstance(), GroupDBManager.getInstance());
			RenewRequestProcessor renewRequestProcessor = new RenewRequestProcessor(ResourceAdapterManager.getInstance(), GroupDBManager.getInstance());
			result = (E) renewRequestProcessor;
			break;
		case SHUTDOWN:
			break;
		case GET_SELF_CREDENTIAL:
			result = (E) new GetSelfCredentialRequestProcessor();
			break;
		case STATUS:
			result = (E) new StatusRequestProcessor();
			break;
		case GET_VERSION:
			result = (E) new GetVersionRequestProcessor();
			break;
		case REGISTER:
			RegisterRequestProcessor registerRequestProcessor = new RegisterRequestProcessor(
					KeyStoreManagement.getInstance(),
					GroupDBManager.getInstance());
			result = (E) registerRequestProcessor;
			break;
		case RESOLVE:
			result = (E) new ResolveRequestProcessor();
			break;
		default:
			break;

		}

		return result;
	}
}
