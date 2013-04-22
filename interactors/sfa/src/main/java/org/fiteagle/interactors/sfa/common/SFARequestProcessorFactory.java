package org.fiteagle.interactors.sfa.common;

import org.fiteagle.interactors.sfa.describe.DescribeRequestProcessor;
import org.fiteagle.interactors.sfa.getversion.GetVersionRequestProcessor;
import org.fiteagle.interactors.sfa.listresources.ListResourceRequestProcessor;

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
			break;
		case DELETE:
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
			break;
		case RENEW:
			break;
		case SHUTDOWN:
			break;
		case STATUS:
			break;
		case GET_VERSION:
			result = (E) new GetVersionRequestProcessor();
		default:
			break;
			
		}
		
		return result;
	}
}
