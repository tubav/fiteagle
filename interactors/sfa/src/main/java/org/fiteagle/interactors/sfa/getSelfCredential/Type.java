package org.fiteagle.interactors.sfa.getSelfCredential;

public enum Type {
//	USER("user"),
//    SLICE("slice"),
//    SA("sa"),
//    MA("ma"),
//    NODE("node");
//	
//	private String type;
//
//	private Type(String type) {
//		this.type=type;
//	}
//
//	public String getType() {
//		return type;
//	}
	
	user,slice,sa,ma,node;
	
	public static boolean contains(String str) {
	    for (Type t : Type.values()) {
	        if (t.name().equals(str)) {
	            return true;
	        }
	    }
	    return false;
	}

}
