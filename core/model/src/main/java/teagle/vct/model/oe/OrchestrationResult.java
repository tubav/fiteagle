package teagle.vct.model.oe;

import java.net.URL;

public interface OrchestrationResult {
	public int getStatus();

	public String getMessage();

	public URL getLogLocation();
}
