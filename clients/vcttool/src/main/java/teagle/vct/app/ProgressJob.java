package teagle.vct.app;

/**
 * Like a runnable, but this can throw exceptions.
 */
public interface ProgressJob {
	public void run() throws Exception;
}
