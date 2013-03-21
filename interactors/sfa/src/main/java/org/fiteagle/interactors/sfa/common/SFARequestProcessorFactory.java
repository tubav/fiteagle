package org.fiteagle.interactors.sfa.common;

import org.fiteagle.interactors.sfa.listresources.ListResourceRequestProcessor;

public class SFARequestProcessorFactory {

	
	@SuppressWarnings("unchecked")
	public <E extends SFAv3RequestProcessor> E createRequestProcessor(SFAv3MethodsEnum method){
		
		E result = null;
		switch(method){
		case ALLOCATE:
			break;
		case DELETE:
			break;
		case DESCRIBE:
			break;
		case LIST_RESOURCES:
			result = (E) new ListResourceRequestProcessor();
			break;
		case PERFORM_OPERATIONAL_ACTION:
			break;
		case PROVISION:
			break;
		case RENEW:
			break;
		case SHUTDOWN:
			break;
		case STATUS:
			break;
		default:
			break;
			
		}
		
		return result;
	}
}
