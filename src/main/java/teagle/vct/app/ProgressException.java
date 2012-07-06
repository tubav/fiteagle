package teagle.vct.app;

/**
 * Exception that wraps exceptions thrown in ProgressJobs
 */
public class ProgressException extends Exception {
	private static final long serialVersionUID = -2740856714977024825L;

	public ProgressException(Exception e) { super(e); }
	public ProgressException(String msg) { super(msg); }
}
