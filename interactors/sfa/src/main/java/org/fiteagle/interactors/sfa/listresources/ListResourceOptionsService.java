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
  private boolean availableSet = false;
  
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
  
  private boolean isExpectedRSpecVersion() {
    double sentVersion  = Double.parseDouble(this.options.getGeni_rspec_version().getVersion());
//    double myVersion =  Double.parseDouble(new SFAv3RspecTranslator().getVersion());
//    return sentVersion == myVersion;
   return sentVersion == 3.0;
  }
  
  private boolean IsAvailableOptionSupported() {
    // GeniAvailableOption availableOption = (GeniAvailableOption) options.getOptions().get(0);
    // boolean returnValue = true;
    // if(availableOption != null)
    // returnValue = !availableOption.getValue();
    //
    // return returnValue;
    
    return true;
    
  }
  
  public boolean areOptionsValid() {
    return optionsValid;
  }
  
  public void checkOptions() {
    amCode = new AMCode();
    if (!isExpectedRSpecVersion()) {
      amCode.setGeni_code(GENI_CodeEnum.BADVERSION);
      optionsValid = false;
      errorOutput = GENI_CodeEnum.BADVERSION.getDescription();
    }
    GeniAvailableOption availableOption = (GeniAvailableOption) options.getOptions().get(0);
    if (availableOption !=null)
      setAvailableSet(availableOption.getValue());
    
    if (availableOption != null && availableOption.getValue()){
      if (!IsAvailableOptionSupported())  {
        amCode.setGeni_code(GENI_CodeEnum.UNSUPPORTED);
        optionsValid = false;
        errorOutput = "Geni available option is not supported yet!";
      }
    }
    
    GeniCompressedOption compressOption = (GeniCompressedOption) options.getOptions().get(1);
    
    if (compressOption != null && compressOption.getValue() && !this.isGeniCompressedOptionSupported()) {
      amCode.setGeni_code(GENI_CodeEnum.UNSUPPORTED);
      optionsValid = false;
      errorOutput = "Geni compressed option is not supported yet!";
    }
    
    if (compressOption != null && compressOption.getValue() && this.isGeniCompressedOptionSupported()) {
      this.setCompressed(true);
    }
    
    if (!optionsAreValid() && !optionsComplete()) {
      amCode.setGeni_code(GENI_CodeEnum.BADARGS);
      optionsValid = false;
      errorOutput = GENI_CodeEnum.BADARGS.getDescription();
    }
    
  }
  
  // private boolean IsGeniCompressedOptionSupported() {
  // GeniCompressedOption compressOption = (GeniCompressedOption) options.getOptions().get(1);
  // boolean response = true;
  // if(compressOption != null)
  // response = !compressOption.getValue();
  // return response;
  // }
  
  public AMCode getErrorCode() {
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

  public boolean isAvailableSet() {
    return availableSet;
  }

  public void setAvailableSet(boolean availableSet) {
    this.availableSet = availableSet;
  }
  
}
