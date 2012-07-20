package teagle.vct.model.oe;

import teagle.vct.model.Vct;

public interface OEClient {
	public OrchestrationResult bookSync(Vct vct) throws OrchestrationException;
}
