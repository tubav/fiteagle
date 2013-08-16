package org.fiteagle.ui.infinity;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import org.fiteagle.ui.infinity.model.InfinityArrayList;
import org.fiteagle.ui.infinity.model.InfinityInfrastructure;
import org.fiteagle.ui.infinity.model.InfinityValueID;

public abstract class InfinityClient {
	
	static int i = 0;

	protected InfinityParser parser;

	protected enum Methods {
		GET_INFRA_BY_ID("getInfrastructuresById"), GET_COMPONENT_BY_ID(
				"getComponentById"), SEARCH_INFRASTRUCTURES(
				"searchInfrastructures"),
				GET_TECHNICAL_COMPONENTS("getTechnicalComponents"),
				GET_COMPONENT_DETAIL("getComponentDetail");
		private String value;

		private Methods(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public InfinityClient() {
		this.parser = new InfinityParser();
	}
	
	

	public abstract InfinityInfrastructure getInfrastructuresById(Number i);

	public abstract ArrayList<InfinityValueID> searchInfrastructures();
	
	public abstract ArrayList<InfinityValueID> getTechnicalComponents();
	public abstract ArrayList<InfinityArrayList> getComponentDetail(String infrastructureId, String componentId);
	

	public InputStream fixXIPIEncoding(InputStream in) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		try {
			while ((len = in.read(buffer)) > -1) {
				out.write(buffer, 0, len);
			}
		} catch (IOException e2) {
			throw new RuntimeException(e2.getMessage());
		}
		try {
			out.flush();
		} catch (IOException e1) {
			throw new RuntimeException(e1.getMessage());
		}

		byte[] utf8 = null;
		try {
			utf8 = new String(out.toByteArray(), "ISO-8859-1").getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
		
//		FileOutputStream fileOutput = null;
//		try {
//			fileOutput = new FileOutputStream(new File("/home/ozanoo/testSil/responseFromXIPI"+i));
//			i++;
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			out.writeTo(fileOutput);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return new ByteArrayInputStream(utf8);
	}

	public String convertStreamToString(InputStream is) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}


}
