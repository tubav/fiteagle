package org.fiteagle.interactors.sfa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.describe.DescribeOptions;
import org.fiteagle.interactors.sfa.describe.DescribeResult;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.SignedCredential;
import org.fiteagle.interactors.sfa.getversion.GetVersionResult;
import org.fiteagle.interactors.sfa.listresources.ListResourceOptions;
import org.fiteagle.interactors.sfa.listresources.ListResourcesResult;

public interface ISFA {
	public static final int ERRORCODE_SUCCESS = 0;

	public static final String KEY_GENI_API = "geni_api";
	public static final String KEY_CODE = "code";
	public static final String KEY_GENI_CODE = "geni_code";
	public static final String KEY_VALUE = "value";
	public static final String KEY_API_VERSIONS = "api_versions";
	public static final String KEY_RSPEC_VERSIONS = "geni_request_rspec_versions";
	public static final String KEY_VERSION = "version";
	public static final String KEY_TYPE = "type";

	GetVersionResult getVersion() throws IOException;
	ListResourcesResult listResources(ListCredentials credentials, ListResourceOptions listResourceOptions) throws IOException;

	String resolve(Object o1, Object o2);
	String getSelfCredential(String certificate, String xrn, String type);
//	String getCredential(SignedCredential credential, String xrn, String type);
	String getCredential(String credential, String xrn, String type);

	DescribeResult describe(ArrayList<String> urns, ListCredentials credentials,
			DescribeOptions describeOptions) throws IOException;

}
