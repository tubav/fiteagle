package org.fiteagle.interactors.sfa;

import java.io.IOException;
import java.util.List;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class SFAInteractorTest {
	public static final String EXPECTED_TYPE = "GENI";
	public static final String EXPECTED_VERSION = "2";
	public static final String EXPECTED_API_URL = "https://fiteagle.org/api/sfa/v2/xmlrpc/am";

	private ISFA sfaInteractor;

	@Before
	public void setup() {
		this.sfaInteractor = new SFAInteractor();
	}

	@Test
	public void testGetVersion() throws IOException {
		Map<String, Object> version = getGeniVersion();
		validateGeniCode(version);

		Map<String, Object> value = getGeniValue(version);
		validateGeniValue(value);
		valudateGeniAPIs(value);
		validateRSpecVersions(value);
	}

	@SuppressWarnings("unchecked")
	private void validateRSpecVersions(Map<String, Object> value) {
		List<Map<String, Object>> rspec_versions = (List<Map<String, Object>>) value
				.get(ISFA.KEY_RSPEC_VERSIONS);
		Assert.assertNotNull(rspec_versions);
		Assert.assertTrue(rspec_versions.size() > 0);
		Assert.assertEquals(rspec_versions.get(0).get(ISFA.KEY_TYPE),
				EXPECTED_TYPE);
	}

	@SuppressWarnings("unchecked")
	private void valudateGeniAPIs(Map<String, Object> value) {
		Map<String, Object> api_versions = (Map<String, Object>) value
				.get(ISFA.KEY_API_VERSIONS);
		Assert.assertNotNull(api_versions);
		Assert.assertEquals(EXPECTED_API_URL,
				api_versions.get(EXPECTED_VERSION));
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getGeniValue(Map<String, Object> version) {
		Map<String, Object> value = (Map<String, Object>) version
				.get(ISFA.KEY_VALUE);
		return value;
	}

	private void validateGeniValue(Map<String, Object> value) {
		Assert.assertNotNull(value);
		Assert.assertEquals(EXPECTED_VERSION, value.get(ISFA.KEY_GENI_API));
	}

	@SuppressWarnings("unchecked")
	private void validateGeniCode(Map<String, Object> version) {
		Map<String, Object> code = (Map<String, Object>) version
				.get(ISFA.KEY_CODE);
		Assert.assertNotNull(code);
		Assert.assertEquals(ISFA.ERRORCODE_SUCCESS,
				code.get(ISFA.KEY_GENI_CODE));
	}

	private Map<String, Object> getGeniVersion() throws IOException {
		Map<String, Object> version = sfaInteractor.getVersion();
		Assert.assertNotNull(version);
		Assert.assertEquals(EXPECTED_VERSION, version.get(ISFA.KEY_GENI_API));
		return version;
	}
}
