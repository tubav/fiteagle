package org.fiteagle.interactors.sfa;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.fiteagle.interactors.sfa.getversion.GetVersionResult;
import org.fiteagle.interactors.sfa.getversion.GetVersionValue;
import org.fiteagle.interactors.sfa.types.AMCode;
import org.fiteagle.interactors.sfa.types.GENI_CodeEnum;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SFAInteractor_v2 implements ISFA {

	final static int GENI_API_VERSION = 2;
	
	@Override
	public GetVersionResult getVersion() throws IOException {
		final GetVersionResult getVersionResult = new GetVersionResult();
	
		//TODO set Value according to version, etc...
		
		GetVersionValue value = new GetVersionValue();
		value.setGeni_api(SFAInteractor_v2.GENI_API_VERSION);	
		getVersionResult.setValue(value);
		
		// TODO set Code according to occuring exceptions etc
		AMCode code = new AMCode();
		code.setGeni_code(GENI_CodeEnum.SUCCESS);
		getVersionResult.setCode(code);
		
		//TODO set Output 
		
		getVersionResult.setOutput("");
		
		return getVersionResult;
	}

	@Override
	public Map<String, Object> listResources() throws IOException {
		return null;
	}

	
}
