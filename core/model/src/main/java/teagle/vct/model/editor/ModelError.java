package teagle.vct.model.editor;

/**
 * Common exception that is thrown when an error occurs when
 * accessing/manipulating an object defined in the model (typically a reflection
 * error).
 */
public class ModelError extends Exception {
	private static final long serialVersionUID = 6919314123315872593L;

	public ModelError(final Throwable t) {
		super(t);
	}

	public ModelError(final String msg) {
		super(msg);
	}
}
