package org.fiteagle.core.framework.requestprocessor;

import javax.xml.bind.JAXBException;

import org.apache.tools.ant.filters.StringInputStream;

import teagle.vct.util.OrchestrateReturn;
import teagle.vct.util.Util;

import com.thoughtworks.xstream.XStream;

public class RequestProcessor {
	public static final int STATUS_SUCCESS = 0;
	private static final int VCT_NAME = 3;
	private static final int VCT_USER = 2;
	private static final int VCT_COMMAND = 1;
	private final XStream xs;
	private OrchestrateReturn result;

	public OrchestrateReturn getResult() {
		return result;
	}

	public String getResultAsString() {
		return this.xs.toXML(result);
	}

	public RequestProcessor() {
		this.xs = Util.newXstream();
		xs.alias("idmapping", OrchestrateReturn.Result.Mapping[].class);
		xs.alias("logentry", String.class);
		xs.processAnnotations(OrchestrateReturn.class);
	}

	public void handle(final String request) throws JAXBException {
		Util.debug("Handling: " + request);
		final String[] arguments = convertToObject(request);
		this.handle(arguments);
	}

	public void handle(final String[] request) {
		logDebugMessages(request);
		final OrchestrateReturn orchestrateReturn = new OrchestrateReturn(
				STATUS_SUCCESS, request[VCT_NAME]);
		orchestrateReturn.message = "From Java RP";
		orchestrateReturn.log = "Some log";
		orchestrateReturn.logbook = orchestrateReturn.new LogBook();
		orchestrateReturn.logbook.component = "Java RP";
		orchestrateReturn.logbook.name = "Da Java RP";
		orchestrateReturn.logbook.entries = new Object[] { "entry" };

		Util.debug("Returning with status: " + orchestrateReturn.status);
		this.result = orchestrateReturn;
	}

	private String[] convertToObject(final String request) {
		final String[] arguments = (String[]) xs.fromXML(new StringInputStream(
				request));
		return arguments;
	}

	private void logDebugMessages(final String[] request) {
		Util.debug(" * User: " + request[VCT_USER]);
		Util.debug(" * Name: " + request[VCT_NAME]);
		Util.debug(" * Command: " + request[VCT_COMMAND]);
	}

}
