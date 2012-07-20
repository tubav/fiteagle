package teagle.vct.model;

public class RepositoryModelError extends RepositoryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepositoryModelError() {
	}

	public RepositoryModelError(final String message) {
		super(message);
	}

	public RepositoryModelError(final Throwable cause) {
		super(cause);
	}

	public RepositoryModelError(final String message, final Throwable cause) {
		super(message, cause);
	}

}
