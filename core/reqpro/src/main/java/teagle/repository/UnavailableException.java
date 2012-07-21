package teagle.repository;

public class UnavailableException extends RepositoryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnavailableException(Throwable cause) {
		super(cause);
	}

	public UnavailableException(String string) {
		super(string);
	}

	public UnavailableException(String string, Throwable cause) {
		super(string, cause);
	}

}
