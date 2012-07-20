package teagle.vct.model;

public class ConstraintViolation extends RepositoryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConstraintViolation() {
		// TODO Auto-generated constructor stub
	}

	public ConstraintViolation(final String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ConstraintViolation(final Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ConstraintViolation(final String message, final Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
