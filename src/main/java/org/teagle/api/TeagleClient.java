package org.teagle.api;

import java.io.BufferedReader;
import java.io.File;
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

import org.teagle.vcttool.control.OrchestrateReturn;
import org.teagle.vcttool.control.VctToolConfig;

import teagle.vct.model.ModelManager;
import teagle.vct.model.RepoClientConfig;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.ResourceInstanceState.State;
import teagle.vct.model.Vct;
import teagle.vct.model.VctState;
import teagle.vct.tssg.impl.TSSGVct;
import teagle.vct.util.Util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class TeagleClient {

	private final URL reqProcUrl;
	private final String username;
	private final ModelManager repoClient;
	private OrchestrateReturn result = new OrchestrateReturn(-1,
			"not initialized");
	
	public TeagleClient(String username, String password, URL reqUrl,
			URL repoUrl) {
		boolean doPrefetching = false;
		ModelManager.getInstance()
				.config(new RepoClientConfig(repoUrl, username, password,
						doPrefetching));

		this.reqProcUrl = reqUrl;
		this.repoClient = ModelManager.getInstance();
		this.username = username;
	}

	public TeagleClient(VctToolConfig config) {
		this(config.getUsername(), config.getPassword(),
				config.getReqprocUrl(), config.getRepoUrl());
	}

	public TeagleClient(String user, String password, String reqUrl,
			String repoUrl) throws MalformedURLException {
		this(user, password, new URL(reqUrl), new URL(repoUrl));
	}

	public void execVctCommand(final Vct vct, final String[] result,
			final String command) {
		this.execVctCommand(vct.getPerson().getUserName(), vct.getCommonName(), result, command);
	}
	
	public void execVctCommand(final String username, final String vctname, final String command) {
		String[] results = { null };
		this.execVctCommand(username, vctname, results , command);
		this.result = TeagleClient.toResult(results[0]);
	}

	
	public void execVctCommand(final String username, final String vctname, final String[] result,
			final String command) {
		try {
			URLConnection conn = reqProcUrl.openConnection();
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "text/plain");

			String req = genRequest(username, vctname, command);
			conn.getOutputStream().write(req.getBytes());
			result[0] = Util.readStream(conn.getInputStream());
			Util.debug("Answer from reqproc: " + result[0]);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private String genRequest(final String username, final String vctname, final String command) {
		String req = "<string-array><string>VctRegistry</string><string>"
				+ command + "</string><string>"
				+ username + "</string><string>"
				+ vctname + "</string></string-array>";
		return req;
	}

	public void bookVct(Vct vct, String[] answer) {
		vct.setState(VctState.State.INPROGRESS_SYNC);
		vct = (Vct) vct.persist();
		System.out.println("DEBUG: Booking: " + TeagleClient.toString(vct));
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
	
	public void deleteVct(String username, String vctname) {
		System.out.println("DEBUG: Deleting: " + vctname);
		this.execVctCommand(username, vctname, "deleteVct");		
	}

	
	public void bookVct(File vctFile, String[] answer) throws IOException {
		String vctString = fileToString(vctFile);
		this.bookVct(vctString, answer);
	}

	public void bookVct(String vctString, String[] answer) {
		Vct vct = TeagleClient.toVct(vctString);
		this.bookVct(vct, answer);
	}

	private static String fileToString(File file) throws IOException {
		BufferedReader fileReader = new BufferedReader(new FileReader(file));
		String line;
		String result = "";
		while ((line = fileReader.readLine()) != null) {
			result += line;
		}
		fileReader.close();
		return result;
	}

	private Collection<ResourceInstance> findResourceInstancesByUserName(
			String username) {
		System.out.println("Calling modelmanager");

		Collection<ResourceInstance> origInstances = this.repoClient
				.findResourceInstancesByUserName(username);
		LinkedList<ResourceInstance> instances = new LinkedList<ResourceInstance>();

		for (ResourceInstance a : origInstances) {
			if (a.getState().equals(State.PROVISIONED)
					&& !a.getDescription().contains("VNode")
					&& !a.getDescription().contains("iperf")) {
				System.out.print(" * " + a.getCommonName() + " : "
						+ a.getState());
				System.out.print(" : " + a.getDescription());
				System.out.println();
				instances.add(a);
			}
		}
		// Collections.sort(instances);
		return instances;
	}

	public static String toString(Vct data) {
		String xmlString = "";
		try {
			Marshaller marshaller = JAXBContext.newInstance(TSSGVct.class)
					.createMarshaller();
			StringWriter sw = new StringWriter();
			marshaller.marshal(data, sw);
			xmlString = sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlString;
	}

	public static Vct toVct(String vctString) {
		Vct vct = null;
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance(TSSGVct.class)
					.createUnmarshaller();
			vct = (Vct) unmarshaller.unmarshal(new StringReader(vctString));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return vct;
	}

	public static OrchestrateReturn toResult(String resultString) {
		XStream xs = newXstream();
		xs.alias("idmapping", OrchestrateReturn.Result.Mapping[].class);
		xs.alias("logentry", String.class);
		xs.processAnnotations(OrchestrateReturn.class);
		return (OrchestrateReturn) xs.fromXML(new StringReader(resultString));
	}

	public static XStream newXstream() {
		return new XStream(new DomDriver(null, new XmlFriendlyNameCoder(
				"SYMDOLLAR", "_"))) {
			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {
				return new MapperWrapper(next) {
					@Override @SuppressWarnings("rawtypes")
					public boolean shouldSerializeMember(Class definedIn,
							String fieldName) {
						if (definedIn == Object.class) {
							return false;
						}
						return super
								.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};

		// return new XStream(new DomDriver(null, new
		// XmlFriendlyReplacer("SYMDOLLAR", "_")));
	}

	public void bookVct(File file) throws IOException {
		String[] resultString = { null };
		this.bookVct(file, resultString);
		this.result = TeagleClient.toResult(resultString[0]);
	}

	public OrchestrateReturn getResult() {
		return this.result;
	}

	public List<ResourceInstance> getResourceInstances() {
		List<ResourceInstance> instances = (List<ResourceInstance>) this.findResourceInstancesByUserName(this.username);
		Collections.sort(instances, new Comparator<ResourceInstance>() {
			public int compare(ResourceInstance o1, ResourceInstance o2) {
				return o1.getCommonName().toLowerCase().compareTo(o2.getCommonName().toLowerCase());
			}
		});
		return instances;
	}

	public List<Vct> getVCTs() {
		List<Vct> vcts = this.repoClient.findVctsByUserName(this.username);
		Collections.sort(vcts, new Comparator<Vct>() {
			public int compare(Vct o1, Vct o2) {
				return o1.getCommonName().toLowerCase().compareTo(o2.getCommonName().toLowerCase());
			}
		});
		return vcts;
	}

}
