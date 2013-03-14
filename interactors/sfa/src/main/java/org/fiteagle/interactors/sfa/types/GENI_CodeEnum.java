package org.fiteagle.interactors.sfa.types;

public enum GENI_CodeEnum {

	SUCCESS(0, "Success"),
	BADARGS(1, "Bad Arguments: malformed arguments"),
	ERROR(2, "Error (other)"),
	FORBIDDEN(3, "Operation Forbidden: eg supplied credentials do not provide sufficient privileges (on given slice)"),
	BADVERSION(4, "Bad Version (eg of RSpec)"),
	SERVERERROR(5, "Server Error"),
	TOOBIG(6, "Too Big (eg request RSpec)"),
	REFUSED(7, "Operation Refused"),
	TIMEDOUT(8, "Operation Timed Out"),
	DBERROR(9, "Database Error"),
	RPCERROR(10,"RPC Error"),
	UNAVAILABLE(11, "Unavailable (eg server in lockdown)"),
	SEARCHFAILED(12," Search Failed (eg for slice)"),
	UNSUPPORTED(13, "Operation Unsupported"),
	BUSY(14, "Busy (resource, slice); try again later"),
	EXPIRED(15, "Expired (eg slice)"),
	INPROGRESS(16, "In Progress"),
	ALREADYEXISTS(17, "Already Exists (eg the slice)"),
	VLAN_UNAVAILABLE(24, "VLAN tag(s) requested not available (likely stitching failure)");
	
	
	int valueOf;
	String description;
	private GENI_CodeEnum(int value,String description){
		this.valueOf = value;
		this.description = description;
	}
	
	public int getValue(){
		return this.valueOf;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	
}
