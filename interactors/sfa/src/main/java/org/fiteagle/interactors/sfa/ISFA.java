package org.fiteagle.interactors.sfa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.fiteagle.interactors.sfa.allocate.AllocateOptions;
import org.fiteagle.interactors.sfa.allocate.AllocateResult;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.delete.DeleteOptions;
import org.fiteagle.interactors.sfa.delete.DeleteResult;
import org.fiteagle.interactors.sfa.describe.DescribeOptions;
import org.fiteagle.interactors.sfa.describe.DescribeResult;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.SignedCredential;
import org.fiteagle.interactors.sfa.getversion.GetVersionResult;
import org.fiteagle.interactors.sfa.listresources.ListResourceOptions;
import org.fiteagle.interactors.sfa.listresources.ListResourcesResult;
import org.fiteagle.interactors.sfa.provision.ProvisionOptions;
import org.fiteagle.interactors.sfa.provision.ProvisionResult;
import org.fiteagle.interactors.sfa.rspec.RSpecContents;
import org.fiteagle.interactors.sfa.status.StatusOptions;
import org.fiteagle.interactors.sfa.status.StatusResult;

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
  AllocateResult allocate(String urn, ListCredentials credentials, RSpecContents requestRspec,
      AllocateOptions allocateOptions) throws IOException;
  ProvisionResult provision(ArrayList<String> urns, ListCredentials credentials, ProvisionOptions provisionOptions)
      throws IOException;
  StatusResult status(ArrayList<String> urns, ListCredentials credentials, StatusOptions statusOptions)
      throws IOException;
  DeleteResult delete(ArrayList<String> urns, ListCredentials credentials, DeleteOptions deleteOptions)
      throws IOException;

}
