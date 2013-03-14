package org.fiteagle.interactors.sfa;

import java.io.IOException;
import java.util.Map;
import org.fiteagle.interactors.sfa.getversion.GetVersionResult;

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
	Map<String, Object> listResources() throws IOException;
}
