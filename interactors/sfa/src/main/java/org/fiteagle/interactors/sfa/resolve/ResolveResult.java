package org.fiteagle.interactors.sfa.resolve;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResolveResult {

  private String peer_authority;
  private Date last_updated;
  private List<String> regKeys;
  private List<String> regSlices;
  private String classtype;
  private String authority;
  private String regUrn;
  private List<String> regPiAuthorities;
  private String hrn;
  private Date date_created;
  private int record_id;
  private String gid;
  private String type;
  private String email;
  private int pointer;
  
  public Map<String, Object> toMap() throws JsonGenerationException, JsonMappingException, IOException {
    final ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(Include.NON_NULL);
    final StringWriter writer = new StringWriter();
    
    mapper.writeValue(writer, this);
    final Map<String, Object> response = mapper.readValue(writer.toString(), Map.class);
    
    return response;
  }

  public String getPeer_authority() {
    return peer_authority;
  }

  public void setPeer_authority(String peer_authority) {
    this.peer_authority = peer_authority;
  }

  public Date getLast_updated() {
    return last_updated;
  }

  public void setLast_updated(Date last_updated) {
    this.last_updated = last_updated;
  }

  public List<String> getReg_Keys() {
    return regKeys;
  }

  public void setReg_Keys(List<String> regKeys) {
    this.regKeys = regKeys;
  }

  public List<String> getReg_Slices() {
    return regSlices;
  }

  public void setReg_Slices(List<String> reqSlices) {
    this.regSlices = reqSlices;
  }

  public String getClasstype() {
    return classtype;
  }

  public void setClasstype(String classtype) {
    this.classtype = classtype;
  }

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  public String getReg_Urn() {
    return regUrn;
  }

  public void setReg_Urn(String regUrn) {
    this.regUrn = regUrn;
  }

  public List<String> getReg_Pi_Authorities() {
    return regPiAuthorities;
  }

  public void setReg_Pi_Authorities(List<String> regPiAuthorities) {
    this.regPiAuthorities = regPiAuthorities;
  }

  public String getHrn() {
    return hrn;
  }

  public void setHrn(String hrn) {
    this.hrn = hrn;
  }

  public Date getDate_created() {
    return date_created;
  }

  public void setDate_created(Date date_created) {
    this.date_created = date_created;
  }

  public int getRecord_id() {
    return record_id;
  }

  public void setRecord_id(int record_id) {
    this.record_id = record_id;
  }

  public String getGid() {
    return gid;
  }

  public void setGid(String gid) {
    this.gid = gid;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getPointer() {
    return pointer;
  }

  public void setPointer(int pointer) {
    this.pointer = pointer;
  }


  
}
