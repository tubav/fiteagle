package teagle.vct.model;

public class RepositoryModelError extends RepositoryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepositoryModelError() {
	}

	public RepositoryModelError(String message) {
		super(message);
	}

	public RepositoryModelError(Throwable cause) {
		super(cause);
	}

	public RepositoryModelError(String message, Throwable cause) {
		super(message, cause);
	}

}
