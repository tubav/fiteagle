package teagle.vct.model;

public class ModelError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModelError() {
	}

	public ModelError(String message) {
		super(message);
	}

	public ModelError(Throwable cause) {
		super(cause);
	}

	public ModelError(String message, Throwable cause) {
		super(message, cause);
	}

}
