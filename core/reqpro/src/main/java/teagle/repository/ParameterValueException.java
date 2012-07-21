package teagle.repository;

public class ParameterValueException extends RepositoryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParameterValueException(Throwable cause) {
		super(cause);
	}

	public ParameterValueException(String string) {
		super(string);
	}

	public ParameterValueException(String string, Throwable cause) {
		super(string, cause);
	}

}
