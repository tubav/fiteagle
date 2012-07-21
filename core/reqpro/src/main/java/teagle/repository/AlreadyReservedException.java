package teagle.repository;

public class AlreadyReservedException extends RepositoryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyReservedException(Throwable cause) {
		super(cause);
	}

	public AlreadyReservedException(String string) {
		super(string);
	}

	public AlreadyReservedException(String string, Throwable cause) {
		super(string, cause);
	}

}
