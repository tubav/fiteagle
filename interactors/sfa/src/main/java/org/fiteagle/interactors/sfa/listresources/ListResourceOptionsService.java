package org.fiteagle.interactors.sfa.listresources;

import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniAvailableOption;
import org.fiteagle.interactors.sfa.common.GeniCompressedOption;
import org.fiteagle.interactors.sfa.common.SFAOptionsService;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class ListResourceOptionsService extends SFAOptionsService {

	private ListResourceOptions options;
	private boolean optionsValid = true;
	private String errorOutput = "";
	private boolean compressed = false;
	
	private AMCode amCode;
	public ListResourceOptionsService(ListResourceOptions options) {
		this.options = options;
	}

	
	 private boolean optionsAreValid() {

		return true;
	}

	
	 private boolean optionsComplete() {
		
		return true;
	}

	private  boolean isExpectedRSpecVersion() {
		return this.options.getGeni_rspec_version().getVersion().compareTo(new SFAv3RspecTranslator().getVersion())==0;
	}

	private boolean IsAvailableOptionSupported(){
		GeniAvailableOption availableOption = (GeniAvailableOption) options.getOptions().get(0);
		boolean returnValue = true;
		if(availableOption != null)
			returnValue = !availableOption.getValue();
		
		return returnValue;
		
	}
	


	public boolean areOptionsValid() {
		return optionsValid;
	}


	public void checkOptions() {
		amCode = new AMCode();
		if(!isExpectedRSpecVersion()){
			amCode.setGeni_code(GENI_CodeEnum.BADVERSION);
			optionsValid = false;
			errorOutput = GENI_CodeEnum.BADVERSION.getDescription();
		}
		if(!IsAvailableOptionSupported()){
			amCode.setGeni_code(GENI_CodeEnum.UNSUPPORTED);
			optionsValid = false;
			errorOutput = "Geni available option is not supported yet!";
		}
		
		GeniCompressedOption compressOption = (GeniCompressedOption) options.getOptions().get(1);
		
		if(compressOption!=null && compressOption.getValue() && !this.isGeniCompressedOptionSupported()){
			amCode.setGeni_code(GENI_CodeEnum.UNSUPPORTED);
			optionsValid = false;
			errorOutput = "Geni compressed option is not supported yet!";
		}
		
		if(compressOption!=null && compressOption.getValue() && this.isGeniCompressedOptionSupported()){
			this.setCompressed(true);
		}
		
		if(!optionsAreValid() && !optionsComplete()){
			amCode.setGeni_code(GENI_CodeEnum.BADARGS);
			optionsValid = false;
			errorOutput  = GENI_CodeEnum.BADARGS.getDescription();
		}
		
		
	}
	
//	private boolean IsGeniCompressedOptionSupported() {
//		GeniCompressedOption compressOption = (GeniCompressedOption) options.getOptions().get(1);
//		boolean response = true;
//		if(compressOption != null)
//			response = !compressOption.getValue();
//		return response;
//	}


	public AMCode getErrorCode(){
		return this.amCode;
	}


	public String getErrorOutput() {
		
		return errorOutput;
	}


	public boolean isCompressed() {
		return compressed;
	}


	public void setCompressed(boolean compressed) {
		this.compressed = compressed;
	}

	
	
}
