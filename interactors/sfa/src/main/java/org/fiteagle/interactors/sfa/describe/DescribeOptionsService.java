package org.fiteagle.interactors.sfa.describe;

import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniCompressedOption;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.common.SFAOptionsService;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class DescribeOptionsService extends SFAOptionsService {

	private DescribeOptions options;
	private boolean optionsValid = true;
	private String errorOutput = "";
	private AMCode amCode;

	public DescribeOptionsService(DescribeOptions options) {
		this.options = options;
	}

	private boolean isRspecVersionValid() {

		SFAv3RspecTranslator sfaV3RspecTranslator = new SFAv3RspecTranslator();
		Geni_RSpec_Version geniRspecVersion = this.options
				.getGeni_rspec_version();

		return geniRspecVersion.getVersion().compareToIgnoreCase(
				sfaV3RspecTranslator.getVersion()) == 0
				&& geniRspecVersion.getType().compareToIgnoreCase(
						sfaV3RspecTranslator.getType()) == 0;

	}

	public void checkOptions() {
		amCode = new AMCode();
		GeniCompressedOption compressOption = (GeniCompressedOption) options
				.getOptions().get(0);

		if (compressOption.getValue()
				&& !this.isGeniCompressedOptionSupported()) {
			amCode.setGeni_code(GENI_CodeEnum.UNSUPPORTED);
			optionsValid = false;
			errorOutput = "Geni compressed option is not supported yet!";
		}

		if (areOptionsValid() && !isRspecVersionValid()) {
			amCode.setGeni_code(GENI_CodeEnum.BADVERSION);
			optionsValid = false;
			errorOutput = GENI_CodeEnum.BADVERSION.getDescription();
		}

	}
	
	public AMCode getErrorCode(){
		return this.amCode;
	}


	public String getErrorOutput() {
		
		return errorOutput;
	}

	@Override
	public boolean areOptionsValid() {
		return optionsValid;
	}

}
