package teagle.vct.model.editor;

/**
 * Common exception that is thrown when an error occurs when
 * accessing/manipulating an object defined in the model (typically
 * a reflection error).
 */
public class ModelError extends Exception {
	public ModelError(Throwable t) { super(t); }
	public ModelError(String msg) { super(msg); }
}
