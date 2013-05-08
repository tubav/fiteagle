package org.fiteagle.interactors.sfa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFARequestProcessorFactory;
import org.fiteagle.interactors.sfa.common.SFAv3MethodsEnum;
import org.fiteagle.interactors.sfa.describe.DescribeOptions;
import org.fiteagle.interactors.sfa.describe.DescribeRequestProcessor;
import org.fiteagle.interactors.sfa.describe.DescribeResult;
import org.fiteagle.interactors.sfa.getSelfCredential.GetSelfCredentialRequestProcessor;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.SignedCredential;
import org.fiteagle.interactors.sfa.getversion.GetVersionRequestProcessor;
import org.fiteagle.interactors.sfa.getversion.GetVersionResult;
import org.fiteagle.interactors.sfa.listresources.ListResourceOptions;
import org.fiteagle.interactors.sfa.listresources.ListResourceRequestProcessor;
import org.fiteagle.interactors.sfa.listresources.ListResourcesResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SFAInteractor_v3 implements ISFA {

	
	private final SFARequestProcessorFactory requestProcessorFactor = SFARequestProcessorFactory.getInstance();
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	public GetVersionResult getVersion() throws IOException {
		
		GetVersionRequestProcessor getVersionRequestProcessor = requestProcessorFactor.createRequestProcessor(SFAv3MethodsEnum.GET_VERSION);
		
		final GetVersionResult getVersionResult = getVersionRequestProcessor.processRequest();		
		return getVersionResult;
	}

	@Override
	public ListResourcesResult listResources(ListCredentials credentials,
			ListResourceOptions listResourceOptions) throws IOException {
		
	
		ListResourceRequestProcessor listResourceRequestProcessor = requestProcessorFactor.createRequestProcessor(SFAv3MethodsEnum.LIST_RESOURCES);
		ListResourcesResult result = listResourceRequestProcessor.processRequest(credentials, listResourceOptions);
		
		AMCode returnCode = result.getCode();
		//Do something
		
		return result;
		
	}
	
	@Override
	public DescribeResult describe(ArrayList<String> urns, ListCredentials credentials,
			DescribeOptions describeOptions) throws IOException {
		
		SFARequestProcessorFactory sfaRequestProcFactory = new SFARequestProcessorFactory();
		DescribeRequestProcessor describeRequestProcessor = sfaRequestProcFactory.createRequestProcessor(SFAv3MethodsEnum.DESCRIBE);
		DescribeResult result = describeRequestProcessor.processRequest(urns, credentials, describeOptions);
		
		return result;
		
	}

  @Override
  public String resolve(Object o1, Object o2) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getSelfCredential(String certificate, String xrn, String type) {
	  SFARequestProcessorFactory sfaRequestProcFactory = new SFARequestProcessorFactory();
	  GetSelfCredentialRequestProcessor getSelfCredentialRequestProcessor = sfaRequestProcFactory.createRequestProcessor(SFAv3MethodsEnum.GET_SELF_CREDENTIAL);
	  String result = getSelfCredentialRequestProcessor.processRequest(certificate, xrn, type);
//	  String str= result.toString();
	  return result;
  }

  @Override
  public String getCredential(String credential, String xrn, String type) {
	  // TODO implement the get credentials. check access rights etc for the type.
	  return this.getSelfCredential("", xrn, type);
  }

  




}
