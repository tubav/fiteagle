package org.fiteagle.interactors.sfa;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

public class SFAInteractorTest {

	@Test
	public void testGetVersion() {
		SFAInteractor sfaInteractor = new SFAInteractor();
		
		Map<String, ?> version = sfaInteractor.getVersion();
		Assert.assertNotNull(version);
		Assert.assertEquals(SFAInteractor.GENI_VERSION, version.get(SFAInteractor.GENI_API));
		
		Map<?, ?> code = (Map<?, ?>) version.get(SFAInteractor.CODE);
		Assert.assertNotNull(code);
		Assert.assertEquals(SFAInteractor.ERRORCODE_SUCCESS, code.get(SFAInteractor.GENI_CODE));
		
		Map<?, ?> value = (Map<?, ?>) version.get(SFAInteractor.VALUE);
		Assert.assertNotNull(value);
		Assert.assertEquals(SFAInteractor.GENI_VERSION, value.get(SFAInteractor.GENI_API));
		
		Map<?, ?> api_versions = (Map<?, ?>) value.get(SFAInteractor.API_VERSIONS);
		Assert.assertNotNull(api_versions);
		Assert.assertEquals(SFAInteractor.API_URL, api_versions.get(SFAInteractor.GENI_VERSION));
		
		Map<?, ?>[] rspec_versions = (Map[]) value.get(SFAInteractor.RSPEC_VERSIONS);
		Assert.assertTrue(rspec_versions.length > 0);
		Assert.assertEquals(rspec_versions[0].get(SFAInteractor.KEY_TYPE), SFAInteractor.TYPE);
		
		System.out.println(version); 
	}
}
