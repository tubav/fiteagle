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

	private transient ISFA sfaInteractor;

	@Before
	public void setUp() {
		this.sfaInteractor = new SFAInteractor();
	}

	@Test
	public void testGetVersion() throws IOException {
		final Map<String, Object> version = this.getGeniVersion();
		this.validateGeniCode(version);

		final Map<String, Object> value = this.getGeniValue(version);
		this.validateGeniValue(value);
		this.valudateGeniAPIs(value);
		this.validateRSpecVersions(value);
	}

	@SuppressWarnings("unchecked")
	private void validateRSpecVersions(final Map<String, Object> value) {
		final List<Map<String, Object>> rspec_versions = (List<Map<String, Object>>) value
				.get(ISFA.KEY_RSPEC_VERSIONS);
		Assert.assertNotNull(rspec_versions);
		Assert.assertFalse(rspec_versions.isEmpty());
		Assert.assertEquals(rspec_versions.get(0).get(ISFA.KEY_TYPE),
				SFAInteractorTest.EXPECTED_TYPE);
	}

	@SuppressWarnings("unchecked")
	private void valudateGeniAPIs(final Map<String, Object> value) {
		final Map<String, Object> api_versions = (Map<String, Object>) value
				.get(ISFA.KEY_API_VERSIONS);
		Assert.assertNotNull(api_versions);
		Assert.assertEquals(SFAInteractorTest.EXPECTED_API_URL,
				api_versions.get(SFAInteractorTest.EXPECTED_VERSION));
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getGeniValue(final Map<String, Object> version) {
		final Map<String, Object> value = (Map<String, Object>) version
				.get(ISFA.KEY_VALUE);
		return value;
	}

	private void validateGeniValue(final Map<String, Object> value) {
		Assert.assertNotNull(value);
		Assert.assertEquals(SFAInteractorTest.EXPECTED_VERSION,
				value.get(ISFA.KEY_GENI_API));
	}

	@SuppressWarnings("unchecked")
	private void validateGeniCode(final Map<String, Object> version) {
		final Map<String, Object> code = (Map<String, Object>) version
				.get(ISFA.KEY_CODE);
		Assert.assertNotNull(code);
		Assert.assertEquals(ISFA.ERRORCODE_SUCCESS,
				code.get(ISFA.KEY_GENI_CODE));
	}

	private Map<String, Object> getGeniVersion() throws IOException {
		final Map<String, Object> version = this.sfaInteractor.getVersion();
		Assert.assertNotNull(version);
		Assert.assertEquals(SFAInteractorTest.EXPECTED_VERSION,
				version.get(ISFA.KEY_GENI_API));
		return version;
	}
}
