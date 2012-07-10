package org.teagle.client;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.LinkedList;

import org.teagle.vcttool.control.VctToolConfig;

import teagle.vct.model.ModelManager;
import teagle.vct.model.RepoClientConfig;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.Vct;
import teagle.vct.model.VctState;
import teagle.vct.model.ResourceInstanceState.State;
import teagle.vct.util.Util;

public class TeagleClient {

	private final URL reqProcUrl;
	private ModelManager repoClient;

	public TeagleClient (String username, String password, URL reqUrl, URL repoUrl) {
		boolean doPrefetching = false;
		ModelManager.getInstance().config(new RepoClientConfig(repoUrl, username, password, doPrefetching));
		
		this.reqProcUrl = reqUrl;
		this.repoClient = ModelManager.getInstance();
	}
	
	public TeagleClient (VctToolConfig config) {
		this(config.getUsername(), config.getPassword(), config.getReqprocUrl(), config.getRepoUrl());
	}
	
	public void execVctCommand(final Vct vct, final String[] result, final String command) {
		try {
			URLConnection conn = reqProcUrl.openConnection();
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "text/plain");
	
			String req = "<string-array><string>VctRegistry</string><string>"
					+ command
					+ "</string><string>"
					+ vct.getPerson().getUserName()
					+ "</string><string>"
					+ vct.getCommonName()
					+ "</string></string-array>";
			conn.getOutputStream().write(req.getBytes());
			result[0] = Util.readStream(conn.getInputStream());
			Util.debug("Answer from reqproc: " + result[0]);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void bookVct(Vct vct, String[] answer) {
		vct.setState(VctState.State.INPROGRESS_SYNC);
		vct.persist();
		this.execVctCommand(vct, answer, "setVct");
	}

	public Collection<ResourceInstance> findResourceInstancesByUserName(
			String username) {
		System.out.println("Calling modelmanager");
		
		Collection<ResourceInstance> origInstances = this.repoClient.findResourceInstancesByUserName(username);
		LinkedList<ResourceInstance> instances = new LinkedList<ResourceInstance>();
		
		for (ResourceInstance a : origInstances) {
			if (a.getState().equals(State.PROVISIONED) &&
							! a.getDescription().contains("VNode") &&
							! a.getDescription().contains("iperf")) {
				System.out.print(" * " + a.getCommonName() + " : " + a.getState());
	 			System.out.print(" : " + a.getDescription());
	 			System.out.println();
				instances.add(a);
			}
		}
		//Collections.sort(instances);
		return instances;		
	}
}
