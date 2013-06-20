package org.fiteagle.interactors.sfa.resolve;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResolveRequestProcessor extends SFAv3RequestProcessor {
  private Logger log = LoggerFactory.getLogger(getClass());
  @Override
  public AMResult processRequest(ListCredentials credentials, Object... specificArgs) {
   
   throw new RuntimeException("Not Implemented");
  }

  public List<Map<String, Object>> resolve(String urn, String credential) {
    List<Map<String, Object>> returnList = new LinkedList<>();
    ResolveResult result = buildResult(urn,credential);
    try {
      returnList.add(result.toMap());
    } catch (IOException e) {
      log.error(e.getMessage(),e);
      throw new RuntimeException(e);
    }
    return returnList;
  }

  private ResolveResult buildResult(String urn, String credential) {
    ResolveResult result = new ResolveResult();
    result.setAuthority(getAuthority());
    result.setClasstype(getClassType());
    result.setDate_created(getDateCreated());
    result.setEmail(getEmail());
    result.setGid(getGid());
    result.setHrn(getHRN());
    result.setLast_updated(getLastUpdated());
    result.setPeer_authority(getPeerAuthority());
    result.setPointer(getPointer());
    result.setRecord_id(getRecordID());
    result.setReg_Keys(getRegKeys());
    result.setReg_Pi_Authorities(getRegPiAuthorites());
    result.setReg_Slices(getRegSlices());
    result.setReg_Urn(getRegURN());
    result.setType(getType());
    return result;
  }

  private String getType() {
    // TODO Auto-generated method stub
    return null;
  }

  private String getRegURN() {
    // TODO Auto-generated method stub
    return null;
  }

  private List<String> getRegSlices() {
    // TODO Auto-generated method stub
    return null;
  }

  private List<String> getRegPiAuthorites() {
    // TODO Auto-generated method stub
    return null;
  }

  private List<String> getRegKeys() {
    // TODO Auto-generated method stub
    return null;
  }

  private int getRecordID() {
    // TODO Auto-generated method stub
    return 0;
  }

  private int getPointer() {
    // TODO Auto-generated method stub
    return 0;
  }

  private String getPeerAuthority() {
    return null;
  }

  private Date getLastUpdated() {
    // TODO Auto-generated method stub
    return null;
  }

  private String getHRN() {
    // TODO Auto-generated method stub
    return null;
  }

  private String getGid() {
    // TODO Auto-generated method stub
    return null;
  }

  private String getEmail() {
    // TODO Auto-generated method stub
    return null;
  }

  private Date getDateCreated() {
    // TODO Auto-generated method stub
    return null;
  }

  private String getClassType() {
    // TODO Auto-generated method stub
    return null;
  }

  private String getAuthority() {
    // TODO Auto-generated method stub
    return null;
  }
  
}
