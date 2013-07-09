package org.fiteagle.interactors.sfa.util;

public class URN {

  private String subject;
  private String domain;
  private String type;
  private String prefix = "urn:publicid:IDN";
  
  public URN(String urnString) {
    parseURNString(urnString);
  }
  private void parseURNString(String urnString) {
   String[] splitted =  urnString.split("\\+");
   if(isCorrectLength(splitted)){
     if(isCorrectPrefix(splitted[0])){
       this.domain = splitted[1];
       this.type = splitted[2];
       this.subject = splitted[3];
     }else{
       throw new URNParsingException();
     }
       
   }else{
     throw new URNParsingException();
   }
  }
  private boolean isCorrectPrefix(String prefix) {
      return this.prefix.equals(prefix);
  }
  private boolean isCorrectLength(String[] splitted) {
    return splitted.length == 4;
  }
  public void setSubject(String subject){
    this.subject = subject;
  }
  public String getSubject() {
    return subject;
  }
  public void setDomain(String domain) {
    this.domain = domain;
  }
  public String getDomain() {
    return domain;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getType() {
    return type;
  }
  @Override
  public String toString(){
    return "urn:publicid:IDN+av.tu-berlin.de+user+test";
  }
  
  
  public class URNParsingException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    
  }
}
