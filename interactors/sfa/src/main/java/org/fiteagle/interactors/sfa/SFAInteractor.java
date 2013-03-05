package org.fiteagle.interactors.sfa;

import java.util.HashMap;
import java.util.Map;

public class SFAInteractor implements ISFA {

	public static final String API_URL = "https://fiteagle.org/api/sfa/v2/xmlrpc/am";
	public static final String GENI_VERSION = "2";
	public static final Integer ERRORCODE_SUCCESS = 0;
	public static final String GENI_API = "geni_api";
	public static final String CODE = "code";
	public static final String GENI_CODE = "geni_code";
	public static final String VALUE = "value";
	public static final String API_VERSIONS = "api_versions";
	public static final String RSPEC_VERSIONS = "geni_request_rspec_versions";
	public static final String TYPE = "GENI";
	public static final Integer VERSION = 3;
    public static final String KEY_VERSION = "version";
	public static final String KEY_TYPE = "type";

	@Override
	public Map<String, ?> getVersion() {
		Map<String, Integer> code = generateCode();
		Map<String, Object> value = generateValue();
		Map<String, ?> result = generateResult(code, value);
		return result ;
	}

	private Map<String, Object> generateValue() {
		HashMap<String, Object> value = new HashMap<String, Object>();
		value.put(GENI_API, GENI_VERSION);
		value.put(API_VERSIONS, generateAPIVersions());
		value.put(RSPEC_VERSIONS, generateRSpecVersions());
		return value;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object>[] generateRSpecVersions() {
		HashMap<String, Object> apiVersion = new HashMap<String, Object>();
		apiVersion.put(KEY_TYPE, TYPE);
		apiVersion.put(KEY_VERSION, VERSION.toString());
		
		HashMap<?, ?> apiVersions[] = {apiVersion};
		
		return (Map<String, Object>[]) apiVersions;
	}

	private Map<String, String> generateAPIVersions() {
		Map<String, String> apiVersions = new HashMap<String, String>();
		apiVersions.put(GENI_VERSION, API_URL);
		return apiVersions;
	}

	private Map<String, Object> generateResult(Map<String, Integer> code, Map<String, Object> value) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put(GENI_API, GENI_VERSION);
		result.put(CODE, code);
		result.put(VALUE, value);
		return result;
	}

	private Map<String, Integer> generateCode() {
		Map<String, Integer> code = new HashMap<String, Integer>();
		code.put(GENI_CODE, ERRORCODE_SUCCESS);
		return code;
	}

}
