package org.fiteagle.interactors.sfa.provision;

import java.util.List;

import org.fiteagle.interactors.sfa.common.GeniBestEffortOption;
import org.fiteagle.interactors.sfa.common.GeniEndTimeoption;
import org.fiteagle.interactors.sfa.common.GeniUserList;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.common.SFAOption;
import org.fiteagle.interactors.sfa.common.SFAOptionsStruct;

public class ProvisionOptions extends SFAOptionsStruct{
  
  private Geni_RSpec_Version geni_rspec_version;
  private GeniBestEffortOption geni_best_effort;
  private GeniEndTimeoption geni_end_time;
  private GeniUserList geni_users;
  

  @Override
  public List<? extends SFAOption> getOptions() {
    // TODO Auto-generated method stub
    return null;
  }

  public Geni_RSpec_Version getGeni_rspec_version() {
    return geni_rspec_version;
  }

  public void setGeni_rspec_version(Geni_RSpec_Version geni_rspec_version) {
    this.geni_rspec_version = geni_rspec_version;
  }

  public GeniBestEffortOption getGeni_best_effort() {
    return geni_best_effort;
  }

  public void setGeni_best_effort(GeniBestEffortOption geni_best_effort) {
    this.geni_best_effort = geni_best_effort;
  }

  public GeniEndTimeoption getGeni_end_time() {
    return geni_end_time;
  }

  public void setGeni_end_time(GeniEndTimeoption geni_end_time) {
    this.geni_end_time = geni_end_time;
  }

  public GeniUserList getGeni_users() {
    return geni_users;
  }

  public void setGeni_users(GeniUserList geni_users) {
    this.geni_users = geni_users;
  }
  
}
