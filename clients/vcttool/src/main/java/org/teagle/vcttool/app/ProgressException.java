package org.teagle.vcttool.app;

/**
 * Exception that wraps exceptions thrown in ProgressJobs
 */
public class ProgressException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProgressException(Exception e) { super(e); }
	public ProgressException(String msg) { super(msg); }
}
