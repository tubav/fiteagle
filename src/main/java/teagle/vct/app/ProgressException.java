package teagle.vct.app;

/**
 * Exception that wraps exceptions thrown in ProgressJobs
 */
public class ProgressException extends Exception {
	public ProgressException(Exception e) { super(e); }
	public ProgressException(String msg) { super(msg); }
}
