package org.teagle.clients.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import teagle.vct.model.ModelManager;
import teagle.vct.model.RepoClientConfig;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.ResourceInstanceState.State;
import teagle.vct.model.Vct;
import teagle.vct.model.VctState;
import teagle.vct.tssg.impl.TSSGVct;
import teagle.vct.util.FileUtilities;
import teagle.vct.util.OrchestrateReturn;
import teagle.vct.util.Util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.mapper.MapperWrapper;


public class LegacyTeagleClient {

	private final URL reqProcUrl;
	private final String username;
	private final ModelManager repoClient;
	private OrchestrateReturn result = new OrchestrateReturn(-1,
			"not initialized");

	public LegacyTeagleClient(final String username, 
			final URL reqUrl, final ModelManager repoClient) {
		this.reqProcUrl = reqUrl;
		this.repoClient = repoClient;
		this.username = username;
	}
	
	public LegacyTeagleClient(final String username, final String password,
			final URL reqUrl, final URL repoUrl) {
		final boolean doPrefetching = false;
		ModelManager.getInstance()
				.config(new RepoClientConfig(repoUrl, username, password,
						doPrefetching));

		this.reqProcUrl = reqUrl;
		this.repoClient = ModelManager.getInstance();
		this.username = username;
	}

	public LegacyTeagleClient(final String user, final String password,
			final String reqUrl, final String repoUrl)
			throws MalformedURLException {
		this(user, password, new URL(reqUrl), new URL(repoUrl));
	}

	public void execVctCommand(final Vct vct, final String[] result,
			final String command) {
		this.execVctCommand(vct.getPerson().getUserName(), vct.getCommonName(),
				result, command);
	}

	public void execVctCommand(final String username, final String vctname,
			final String command) {
		final String[] results = { null };
		this.execVctCommand(username, vctname, results, command);
		this.result = LegacyTeagleClient.toResult(results[0]);
	}

	public void execVctCommand(final String username, final String vctname,
			final String[] result, final String command) {
		try {
			final URLConnection conn = this.reqProcUrl.openConnection();
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "text/plain");

			final String req = this.genRequest(username, vctname, command);
			Util.debug("Sending to reqproc: " + req);
			conn.getOutputStream().write(req.getBytes());
			result[0] = Util.readStream(conn.getInputStream());
			Util.debug("Answer from reqproc: " + result[0]);
		} catch (final IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private String genRequest(final String username, final String vctname,
			final String command) {
		final String req = "<string-array><string>VctRegistry</string><string>"
				+ command + "</string><string>" + username
				+ "</string><string>" + vctname + "</string></string-array>";
		return req;
	}

	public void bookVct(Vct vct, final String[] answer) {
		vct.setState(VctState.State.INPROGRESS_SYNC);
		vct = (Vct) vct.persist();
		Util.debug("Booking: " + LegacyTeagleClient.toString(vct));
		this.execVctCommand(vct, answer, "setVct");
	}

	public void startVct(final String username, final String vctname) {
		System.out.println("DEBUG: Starting: " + vctname);
		this.execVctCommand(username, vctname, "startVct");
	}

	public void stopVct(final String username, final String vctname) {
		System.out.println("DEBUG: Stopping: " + vctname);
		this.execVctCommand(username, vctname, "stopVct");
	}

	public void deleteVct(final String username, final String vctname) {
		System.out.println("DEBUG: Deleting: " + vctname);
		this.execVctCommand(username, vctname, "deleteVct");
	}

	public void bookVct(final File vctFile, final String[] answer)
			throws IOException {
		final String vctString = LegacyTeagleClient.fileToString(vctFile);
		this.bookVct(vctString, answer);
	}

	public void bookVct(final String vctString, final String[] answer) {
		final Vct vct = LegacyTeagleClient.toVct(vctString);
		this.bookVct(vct, answer);
	}

	private static String fileToString(final File file) throws IOException {
		final BufferedReader fileReader = new BufferedReader(new FileReader(
				file));
		String line;
		String result = "";
		while ((line = fileReader.readLine()) != null)
			result += line;
		fileReader.close();
		return result;
	}

	private Collection<ResourceInstance> findResourceInstancesByUserName(
			final String username) {
		System.out.println("Calling modelmanager");

		final Collection<ResourceInstance> origInstances = this.repoClient
				.findResourceInstancesByUserName(username);
		final LinkedList<ResourceInstance> instances = new LinkedList<ResourceInstance>();

		for (final ResourceInstance a : origInstances)
			if (a.getState().equals(State.PROVISIONED)
					&& !a.getDescription().contains("VNode")
					&& !a.getDescription().contains("iperf")) {
				System.out.print(" * " + a.getCommonName() + " : "
						+ a.getState());
				System.out.print(" : " + a.getDescription());
				System.out.println();
				instances.add(a);
			}
		// Collections.sort(instances);
		return instances;
	}

	public static String toString(final Vct data) {
		String xmlString = "";
		try {
			final Marshaller marshaller = JAXBContext
					.newInstance(TSSGVct.class).createMarshaller();
			final StringWriter sw = new StringWriter();
			marshaller.marshal(data, sw);
			xmlString = sw.toString();
		} catch (final JAXBException e) {
			e.printStackTrace();
		}
		return xmlString;
	}

	public static Vct toVct(final String vctString) {
		Vct vct = null;
		try {
			final Unmarshaller unmarshaller = JAXBContext.newInstance(
					TSSGVct.class).createUnmarshaller();
			vct = (Vct) unmarshaller.unmarshal(new StringReader(vctString));
		} catch (final JAXBException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return vct;
	}
	
	public static Vct toVct(File file) throws FileNotFoundException, IOException {
		return toVct(FileUtilities.readFileAsString(file));
	}


	public static OrchestrateReturn toResult(final String resultString) {
		final XStream xs = LegacyTeagleClient.newXstream();
		xs.alias("idmapping", OrchestrateReturn.Result.Mapping[].class);
		xs.alias("logentry", String.class);
		xs.processAnnotations(OrchestrateReturn.class);
		return (OrchestrateReturn) xs.fromXML(new StringReader(resultString));
	}

	public static XStream newXstream() {
		return new XStream(new DomDriver(null, new XmlFriendlyNameCoder(
				"SYMDOLLAR", "_"))) {
			@Override
			protected MapperWrapper wrapMapper(final MapperWrapper next) {
				return new MapperWrapper(next) {
					@Override
					@SuppressWarnings("rawtypes")
					public boolean shouldSerializeMember(final Class definedIn,
							final String fieldName) {
						if (definedIn == Object.class)
							return false;
						return super
								.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};

		// return new XStream(new DomDriver(null, new
		// XmlFriendlyReplacer("SYMDOLLAR", "_")));
	}

	public void bookVct(final File file) throws IOException {
		final String[] resultString = { null };
		this.bookVct(file, resultString);
		this.result = LegacyTeagleClient.toResult(resultString[0]);
	}

	public OrchestrateReturn getResult() {
		return this.result;
	}

	public List<ResourceInstance> getResourceInstances() {
		final List<ResourceInstance> instances = (List<ResourceInstance>) this
				.findResourceInstancesByUserName(this.username);
		Collections.sort(instances, new Comparator<ResourceInstance>() {
			@Override
			public int compare(final ResourceInstance o1,
					final ResourceInstance o2) {
				return o1.getCommonName().toLowerCase()
						.compareTo(o2.getCommonName().toLowerCase());
			}
		});
		return instances;
	}

	public List<Vct> getVCTs() {
		final List<Vct> vcts = this.repoClient
				.findVctsByUserName(this.username);
		Collections.sort(vcts, new Comparator<Vct>() {
			@Override
			public int compare(final Vct o1, final Vct o2) {
				return o1.getCommonName().toLowerCase()
						.compareTo(o2.getCommonName().toLowerCase());
			}
		});
		return vcts;
	}

}
