package teagle.vct.app;

/**
 * Exception that wraps exceptions thrown in ProgressJobs
 */
public class ProgressException extends Exception {
	private static final long serialVersionUID = -2740856714977024825L;

	public ProgressException(final Exception e) {
		super(e);
	}

	public ProgressException(final String msg) {
		super(msg);
	}
}
