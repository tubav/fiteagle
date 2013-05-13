package org.fiteagle.interactors.sfa.common;

import org.fiteagle.interactors.sfa.allocate.AllocateRequestProcessor;
import org.fiteagle.interactors.sfa.delete.DeleteRequestProcessor;
import org.fiteagle.interactors.sfa.describe.DescribeRequestProcessor;

import org.fiteagle.interactors.sfa.getSelfCredential.GetSelfCredentialRequestProcessor;
import org.fiteagle.interactors.sfa.getversion.GetVersionRequestProcessor;
import org.fiteagle.interactors.sfa.listresources.ListResourceRequestProcessor;
import org.fiteagle.interactors.sfa.provision.ProvisionRequestProcessor;
import org.fiteagle.interactors.sfa.status.StatusRequestProcessor;

public class SFARequestProcessorFactory {

	private static SFARequestProcessorFactory factory = new SFARequestProcessorFactory();
	
	public static SFARequestProcessorFactory getInstance(){
		return factory;
	}
	
	@SuppressWarnings("unchecked")
	public <E extends SFAv3RequestProcessor> E createRequestProcessor(SFAv3MethodsEnum method){
		
		E result = null;
		switch(method){
		case ALLOCATE:
		  result = (E) new AllocateRequestProcessor();
			break;
		case DELETE:
		  result = (E) new DeleteRequestProcessor();
			break;
		case DESCRIBE:
			result = (E) new DescribeRequestProcessor();
			break;
		case LIST_RESOURCES:
			result = (E) new ListResourceRequestProcessor();
			break;
		case PERFORM_OPERATIONAL_ACTION:
			break;
		case PROVISION:
		  result = (E) new ProvisionRequestProcessor();
			break;
		case RENEW:
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
		default:
			break;
			
		}
		
		return result;
	}
}
